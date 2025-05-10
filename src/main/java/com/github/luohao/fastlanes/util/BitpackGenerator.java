package com.github.luohao.fastlanes.util;

import com.palantir.javapoet.ArrayTypeName;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.TypeSpec;
import jdk.incubator.vector.VectorOperators;

import javax.lang.model.element.Modifier;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.luohao.fastlanes.util.GeneratorUtil.index;
import static java.lang.String.format;

public class BitpackGenerator
{
    public static JavaFile buildPacker(Class<?> primitiveType)
    {
        Class<?> boxedType = GeneratorUtil.PRIMITIVE_TO_BOXED.get(primitiveType);
        int unpackedBits = GeneratorUtil.PRIMITIVE_TYPE_BITS.get(primitiveType);

        List<FieldSpec> masks = new ArrayList<>(unpackedBits);
        for (int i = 0; i < unpackedBits; i++) {
            masks.add(FieldSpec.builder(primitiveType, format("MASK%d", i))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer("($T)((1L << $L) - 1)", primitiveType, i)
                    .build());
        }

        TypeSpec packer = TypeSpec.classBuilder(format("Vector%sPacker", boxedType.getSimpleName()))
                .addModifiers(Modifier.PUBLIC)
                .addFields(masks)
                .addMethods(buildPackMethods(primitiveType, unpackedBits))
                .addMethods(buildUnpackMethods(primitiveType, unpackedBits))
                .build();

        return JavaFile.builder("com.github.luohao.fastlanes.bitpack", packer)
                .indent("\t")
                .build();
    }

    public static List<MethodSpec> buildPackMethods(Class<?> primitiveType, int unpackedBits)
    {
        Class<?> vectorType = GeneratorUtil.PRIMITIVE_TO_VECTOR.get(primitiveType);
        int totalLanes = GeneratorUtil.FASTLANE_REGISTER_BITS / unpackedBits;

        List<MethodSpec> methods = new ArrayList<>(unpackedBits);
        for (int bitWidth = 0; bitWidth <= unpackedBits; bitWidth++) {
            MethodSpec.Builder builder = MethodSpec.methodBuilder(format("pack%d", bitWidth))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(ArrayTypeName.of(primitiveType), "input")
                    .addParameter(ArrayTypeName.of(primitiveType), "output");

            builder.addStatement("int stride = $T.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / $L", vectorType, unpackedBits);
            builder.beginControlFlow("for (int i = 0; i < $L / stride; i++)", totalLanes);

            if (bitWidth == 0) {
                // packed array should be zero bytes
            }
            else if (bitWidth == unpackedBits) {
                builder.addStatement("$T src", vectorType);
                for (int row = 0; row < unpackedBits; row++) {
                    builder.addStatement("src = $T.fromArray($T.SPECIES_PREFERRED, input, $L + i * stride)", vectorType, vectorType, index(row));
                    builder.addStatement("src.intoArray(output, $L + i * stride)", totalLanes * row);
                }
            }
            else {
                builder.addStatement("$T src", vectorType);
                builder.addStatement("$T tmp", vectorType);
                for (int row = 0; row < unpackedBits; row++) {
                    builder.addStatement("src = $T.fromArray($T.SPECIES_PREFERRED, input, $L + i * stride)", vectorType, vectorType, index(row));
                    builder.addStatement("src = src.and(MASK$L)", bitWidth);

                    if (row == 0) {
                        builder.addStatement("tmp = src");
                    }
                    else {
                        builder.addStatement("tmp = tmp.or(src.lanewise($T.LSHL, $L))", VectorOperators.class, (row * bitWidth) % unpackedBits);
                    }

                    int curr = (row * bitWidth) / unpackedBits;
                    int next = ((row + 1) * bitWidth) / unpackedBits;
                    if (next > curr) {
                        builder.addStatement("tmp.intoArray(output, $L + i * stride)", totalLanes * curr);
                        int remainingBits = ((row + 1) * bitWidth) % unpackedBits;
                        builder.addStatement("tmp = src.lanewise($T.LSHR, $L)", VectorOperators.class, bitWidth - remainingBits);
                    }
                }
            }
            builder.endControlFlow();
            methods.add(builder.build());
        }

        MethodSpec.Builder builder = MethodSpec.methodBuilder("pack")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(ArrayTypeName.of(primitiveType), "input")
                .addParameter(int.class, "width")
                .addParameter(ArrayTypeName.of(primitiveType), "output");

        builder.beginControlFlow("switch (width)");
        for (int i = 0; i <= unpackedBits; i++) {
            builder.addStatement("case $L: pack$L(input, output); break", i, i);
        }
        builder.addStatement("default: throw new IllegalArgumentException()")
                .endControlFlow();
        methods.add(builder.build());
        return methods;
    }

    public static List<MethodSpec> buildUnpackMethods(Class<?> primitiveType, int unpackedBits)
    {
        Class<?> vectorType = GeneratorUtil.PRIMITIVE_TO_VECTOR.get(primitiveType);
        int totalLanes = GeneratorUtil.FASTLANE_REGISTER_BITS / unpackedBits;

        List<MethodSpec> methods = new ArrayList<>(unpackedBits);
        for (int bitWidth = 0; bitWidth <= unpackedBits; bitWidth++) {
            MethodSpec.Builder builder = MethodSpec.methodBuilder(format("unpack%d", bitWidth))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(ArrayTypeName.of(primitiveType), "input")
                    .addParameter(ArrayTypeName.of(primitiveType), "output");

            builder.addStatement("int stride = $T.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / $L", vectorType, unpackedBits);
            builder.beginControlFlow("for (int i = 0; i < $L / stride; i++)", totalLanes);
            if (bitWidth == 0) {
                builder.addStatement("$T.fill(output, ($T) 0)", Arrays.class, primitiveType);
            }
            else if (bitWidth == unpackedBits) {
                builder.addStatement("$T src", vectorType);
                for (int row = 0; row < unpackedBits; row++) {
                    builder.addStatement("src = $T.fromArray($T.SPECIES_PREFERRED, input, $L + i * stride)", vectorType, vectorType, totalLanes * row);
                    builder.addStatement("src.intoArray(output, $L + i * stride)", index(row));
                }
            }
            else {
                builder.addStatement("$T src = $T.fromArray($T.SPECIES_PREFERRED, input, i * stride)", vectorType, vectorType, vectorType);
                builder.addStatement("$T tmp", vectorType);
                for (int row = 0; row < unpackedBits; row++) {
                    int curr = (row * bitWidth) / unpackedBits;
                    int next = ((row + 1) * bitWidth) / unpackedBits;
                    int shift = (row * bitWidth) % unpackedBits;
                    if (next > curr) {
                        int remainingBits = ((row + 1) * bitWidth) % unpackedBits;
                        int currentBits = bitWidth - remainingBits;

                        builder.addStatement("tmp = src.lanewise($T.LSHR, $L).and(MASK$L)", VectorOperators.class, shift, currentBits);
                        if (next < bitWidth) {
                            builder.addStatement("src = $T.fromArray($T.SPECIES_PREFERRED, input, $L + i * stride)", vectorType, vectorType, totalLanes * next);
                            builder.addStatement("tmp = tmp.or(src.and(MASK$L).lanewise($T.LSHL, $L))", remainingBits, VectorOperators.class, currentBits);
                        }
                    }
                    else {
                        builder.addStatement("tmp = src.lanewise($T.LSHR, $L).and(MASK$L)", VectorOperators.class, shift, bitWidth);
                    }
                    builder.addStatement("tmp.intoArray(output, $L + i * stride)", index(row));
                }
            }
            builder.endControlFlow();
            methods.add(builder.build());
        }

        MethodSpec.Builder builder = MethodSpec.methodBuilder("unpack")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(ArrayTypeName.of(primitiveType), "input")
                .addParameter(int.class, "width")
                .addParameter(ArrayTypeName.of(primitiveType), "output");

        builder.beginControlFlow("switch (width)");
        for (int i = 0; i <= unpackedBits; i++) {
            builder.addStatement("case $L: unpack$L(input, output); break", i, i);
        }
        builder.addStatement("default: throw new IllegalArgumentException()")
                .endControlFlow();
        methods.add(builder.build());
        return methods;
    }

    public static void main(String[] args)
            throws IOException
    {
        Path path = Path.of("src/main/java/");
        buildPacker(byte.class).writeTo(path);
        buildPacker(short.class).writeTo(path);
        buildPacker(int.class).writeTo(path);
        buildPacker(long.class).writeTo(path);
    }
}

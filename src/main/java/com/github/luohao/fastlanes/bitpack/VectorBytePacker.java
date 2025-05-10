package com.github.luohao.fastlanes.bitpack;

import java.util.Arrays;
import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.VectorOperators;

public class VectorBytePacker {
	public static final byte MASK0 = (byte)((1L << 0) - 1);

	public static final byte MASK1 = (byte)((1L << 1) - 1);

	public static final byte MASK2 = (byte)((1L << 2) - 1);

	public static final byte MASK3 = (byte)((1L << 3) - 1);

	public static final byte MASK4 = (byte)((1L << 4) - 1);

	public static final byte MASK5 = (byte)((1L << 5) - 1);

	public static final byte MASK6 = (byte)((1L << 6) - 1);

	public static final byte MASK7 = (byte)((1L << 7) - 1);

	public static void pack0(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
		}
	}

	public static void pack1(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			ByteVector tmp;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src = src.and(MASK1);
			tmp = src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src = src.and(MASK1);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 1));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src = src.and(MASK1);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 2));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src = src.and(MASK1);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 3));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src = src.and(MASK1);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src = src.and(MASK1);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 5));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src = src.and(MASK1);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 6));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src = src.and(MASK1);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 7));
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 1);
		}
	}

	public static void pack2(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			ByteVector tmp;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src = src.and(MASK2);
			tmp = src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src = src.and(MASK2);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 2));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src = src.and(MASK2);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src = src.and(MASK2);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src = src.and(MASK2);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 0));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src = src.and(MASK2);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 2));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src = src.and(MASK2);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src = src.and(MASK2);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2);
		}
	}

	public static void pack3(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			ByteVector tmp;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src = src.and(MASK3);
			tmp = src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src = src.and(MASK3);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 3));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src = src.and(MASK3);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src = src.and(MASK3);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 1));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src = src.and(MASK3);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src = src.and(MASK3);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 7));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 1);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src = src.and(MASK3);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 2));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src = src.and(MASK3);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 5));
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 3);
		}
	}

	public static void pack4(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			ByteVector tmp;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src = src.and(MASK4);
			tmp = src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src = src.and(MASK4);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src = src.and(MASK4);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 0));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src = src.and(MASK4);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src = src.and(MASK4);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 0));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src = src.and(MASK4);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src = src.and(MASK4);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 0));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src = src.and(MASK4);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4);
		}
	}

	public static void pack5(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			ByteVector tmp;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src = src.and(MASK5);
			tmp = src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src = src.and(MASK5);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 5));
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 3);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src = src.and(MASK5);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 2));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src = src.and(MASK5);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 7));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 1);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src = src.and(MASK5);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src = src.and(MASK5);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 1));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src = src.and(MASK5);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src = src.and(MASK5);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 3));
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 5);
		}
	}

	public static void pack6(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			ByteVector tmp;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src = src.and(MASK6);
			tmp = src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src = src.and(MASK6);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src = src.and(MASK6);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src = src.and(MASK6);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src = src.and(MASK6);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 0));
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src = src.and(MASK6);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src = src.and(MASK6);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src = src.and(MASK6);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6);
		}
	}

	public static void pack7(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			ByteVector tmp;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src = src.and(MASK7);
			tmp = src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src = src.and(MASK7);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 7));
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 1);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src = src.and(MASK7);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src = src.and(MASK7);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 5));
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 3);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src = src.and(MASK7);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src = src.and(MASK7);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 3));
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 5);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src = src.and(MASK7);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src = src.and(MASK7);
			tmp = tmp.or(src.lanewise(VectorOperators.LSHL, 1));
			tmp.intoArray(output, 768 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 7);
		}
	}

	public static void pack8(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src.intoArray(output, 0 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src.intoArray(output, 128 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src.intoArray(output, 256 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src.intoArray(output, 384 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src.intoArray(output, 512 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src.intoArray(output, 640 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src.intoArray(output, 768 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src.intoArray(output, 896 + i * stride);
		}
	}

	public static void pack(byte[] input, int width, byte[] output) {
		switch (width) {
			case 0: pack0(input, output); break;
			case 1: pack1(input, output); break;
			case 2: pack2(input, output); break;
			case 3: pack3(input, output); break;
			case 4: pack4(input, output); break;
			case 5: pack5(input, output); break;
			case 6: pack6(input, output); break;
			case 7: pack7(input, output); break;
			case 8: pack8(input, output); break;
			default: throw new IllegalArgumentException();
		}
	}

	public static void unpack0(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			Arrays.fill(output, (byte) 0);
		}
	}

	public static void unpack1(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, i * stride);
			ByteVector tmp;
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK1);
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 1).and(MASK1);
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2).and(MASK1);
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 3).and(MASK1);
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK1);
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 5).and(MASK1);
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6).and(MASK1);
			tmp.intoArray(output, 768 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 7).and(MASK1);
			tmp.intoArray(output, 896 + i * stride);
		}
	}

	public static void unpack2(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, i * stride);
			ByteVector tmp;
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK2);
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2).and(MASK2);
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK2);
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6).and(MASK2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			tmp = tmp.or(src.and(MASK0).lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK2);
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2).and(MASK2);
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK2);
			tmp.intoArray(output, 768 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6).and(MASK2);
			tmp.intoArray(output, 896 + i * stride);
		}
	}

	public static void unpack3(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, i * stride);
			ByteVector tmp;
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK3);
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 3).and(MASK3);
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6).and(MASK2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			tmp = tmp.or(src.and(MASK1).lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 1).and(MASK3);
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK3);
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 7).and(MASK1);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			tmp = tmp.or(src.and(MASK2).lanewise(VectorOperators.LSHL, 1));
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2).and(MASK3);
			tmp.intoArray(output, 768 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 5).and(MASK3);
			tmp.intoArray(output, 896 + i * stride);
		}
	}

	public static void unpack4(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, i * stride);
			ByteVector tmp;
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK4);
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			tmp = tmp.or(src.and(MASK0).lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK4);
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			tmp = tmp.or(src.and(MASK0).lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK4);
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			tmp = tmp.or(src.and(MASK0).lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK4);
			tmp.intoArray(output, 768 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK4);
			tmp.intoArray(output, 896 + i * stride);
		}
	}

	public static void unpack5(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, i * stride);
			ByteVector tmp;
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK5);
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 5).and(MASK3);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			tmp = tmp.or(src.and(MASK2).lanewise(VectorOperators.LSHL, 3));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2).and(MASK5);
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 7).and(MASK1);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			tmp = tmp.or(src.and(MASK4).lanewise(VectorOperators.LSHL, 1));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			tmp = tmp.or(src.and(MASK1).lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 1).and(MASK5);
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6).and(MASK2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			tmp = tmp.or(src.and(MASK3).lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 768 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 3).and(MASK5);
			tmp.intoArray(output, 896 + i * stride);
		}
	}

	public static void unpack6(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, i * stride);
			ByteVector tmp;
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK6);
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6).and(MASK2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			tmp = tmp.or(src.and(MASK4).lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			tmp = tmp.or(src.and(MASK2).lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2).and(MASK6);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			tmp = tmp.or(src.and(MASK0).lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK6);
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6).and(MASK2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			tmp = tmp.or(src.and(MASK4).lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			tmp = tmp.or(src.and(MASK2).lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 768 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2).and(MASK6);
			tmp.intoArray(output, 896 + i * stride);
		}
	}

	public static void unpack7(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, i * stride);
			ByteVector tmp;
			tmp = src.lanewise(VectorOperators.LSHR, 0).and(MASK7);
			tmp.intoArray(output, 0 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 7).and(MASK1);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			tmp = tmp.or(src.and(MASK6).lanewise(VectorOperators.LSHL, 1));
			tmp.intoArray(output, 128 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 6).and(MASK2);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			tmp = tmp.or(src.and(MASK5).lanewise(VectorOperators.LSHL, 2));
			tmp.intoArray(output, 256 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 5).and(MASK3);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			tmp = tmp.or(src.and(MASK4).lanewise(VectorOperators.LSHL, 3));
			tmp.intoArray(output, 384 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 4).and(MASK4);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			tmp = tmp.or(src.and(MASK3).lanewise(VectorOperators.LSHL, 4));
			tmp.intoArray(output, 512 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 3).and(MASK5);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			tmp = tmp.or(src.and(MASK2).lanewise(VectorOperators.LSHL, 5));
			tmp.intoArray(output, 640 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 2).and(MASK6);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			tmp = tmp.or(src.and(MASK1).lanewise(VectorOperators.LSHL, 6));
			tmp.intoArray(output, 768 + i * stride);
			tmp = src.lanewise(VectorOperators.LSHR, 1).and(MASK7);
			tmp.intoArray(output, 896 + i * stride);
		}
	}

	public static void unpack8(byte[] input, byte[] output) {
		int stride = ByteVector.SPECIES_PREFERRED.vectorByteSize() * Byte.SIZE / 8;
		for (int i = 0; i < 128 / stride; i++) {
			ByteVector src;
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 0 + i * stride);
			src.intoArray(output, 0 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 128 + i * stride);
			src.intoArray(output, 128 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 256 + i * stride);
			src.intoArray(output, 256 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 384 + i * stride);
			src.intoArray(output, 384 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 512 + i * stride);
			src.intoArray(output, 512 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 640 + i * stride);
			src.intoArray(output, 640 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 768 + i * stride);
			src.intoArray(output, 768 + i * stride);
			src = ByteVector.fromArray(ByteVector.SPECIES_PREFERRED, input, 896 + i * stride);
			src.intoArray(output, 896 + i * stride);
		}
	}

	public static void unpack(byte[] input, int width, byte[] output) {
		switch (width) {
			case 0: unpack0(input, output); break;
			case 1: unpack1(input, output); break;
			case 2: unpack2(input, output); break;
			case 3: unpack3(input, output); break;
			case 4: unpack4(input, output); break;
			case 5: unpack5(input, output); break;
			case 6: unpack6(input, output); break;
			case 7: unpack7(input, output); break;
			case 8: unpack8(input, output); break;
			default: throw new IllegalArgumentException();
		}
	}
}

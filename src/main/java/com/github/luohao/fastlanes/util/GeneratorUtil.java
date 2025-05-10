package com.github.luohao.fastlanes.util;

import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.LongVector;
import jdk.incubator.vector.ShortVector;

import java.util.Map;

public class GeneratorUtil
{
    public static final Map<Class<?>, Class<?>> PRIMITIVE_TO_BOXED = Map.of(
            byte.class, Byte.class,
            short.class, Short.class,
            int.class, Integer.class,
            long.class, Long.class);
    public static final Map<Class<?>, Class<?>> PRIMITIVE_TO_VECTOR = Map.of(
            byte.class, ByteVector.class,
            short.class, ShortVector.class,
            int.class, IntVector.class,
            long.class, LongVector.class);
    public static final Map<Class<?>, Integer> PRIMITIVE_TYPE_BITS = Map.of(
            byte.class, 8,
            short.class, 16,
            int.class, 32,
            long.class, 64);
    public static final int FASTLANE_REGISTER_BITS = 1024;
    public static final int[] FL_ORDER = new int[] {0, 4, 2, 6, 1, 5, 3, 7};

    private GeneratorUtil() {}

    public static int index(int row)
    {
        int o = row / 8;
        int s = row % 8;
        return FL_ORDER[o] * 16 + s * 128;
    }
}

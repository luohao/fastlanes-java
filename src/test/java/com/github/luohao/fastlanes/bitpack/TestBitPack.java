package com.github.luohao.fastlanes.bitpack;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class TestBitPack {
    public static final Random RANDOM = new Random();

    @Test
    public void testBytePackUnpack() {
        for (int width = 1; width <= Byte.SIZE; width++) {
            byte[] input = new byte[1024];
            byte[] packed = new byte[1024 * Byte.SIZE / width];
            byte[] unpacked = new byte[1024];
            byte mask = width < Byte.SIZE ? (byte) ((1 << width) - 1) : -1;

            for (int i = 0; i < 1024; i++) {
                input[i] = (byte) (RANDOM.nextInt() & mask);
            }
            VectorBytePacker.pack(input, width, packed);
            VectorBytePacker.unpack(packed, width, unpacked);
        }
    }

    @Test
    public void testShortPackUnpack() {
        for (int width = 1; width <= Short.SIZE; width++) {
            short[] input = new short[1024];
            short[] packed = new short[1024 * Short.SIZE / width];
            short[] unpacked = new short[1024];
            short mask = width < Short.SIZE ? (short) ((1 << width) - 1) : -1;

            for (int i = 0; i < 1024; i++) {
                input[i] = (short) (RANDOM.nextInt() & mask);
            }
            VectorShortPacker.pack(input, width, packed);
            VectorShortPacker.unpack(packed, width, unpacked);
        }
    }

    @Test
    public void testIntegerPackUnpack() {
        for (int width = 1; width <= Integer.SIZE; width++) {
            int[] input = new int[1024];
            int[] packed = new int[1024 * Integer.SIZE / width];
            int[] unpacked = new int[1024];
            int mask = width < Integer.SIZE ? ((1 << width) - 1) : -1;

            for (int i = 0; i < 1024; i++) {
                input[i] = RANDOM.nextInt() & mask;
            }
            VectorIntegerPacker.pack(input, width, packed);
            VectorIntegerPacker.unpack(packed, width, unpacked);
        }
    }

    @Test
    public void testLongPackUnpack() {
        for (int width = 1; width <= Long.SIZE; width++) {
            long[] input = new long[1024];
            long[] packed = new long[1024 * Long.SIZE / width];
            long[] unpacked = new long[1024];
            long mask = width < Long.SIZE ? ((1L << width) - 1) : -1L;

            for (int i = 0; i < 1024; i++) {
                input[i] = RANDOM.nextLong() & mask;
            }
            VectorLongPacker.pack(input, width, packed);
            VectorLongPacker.unpack(packed, width, unpacked);
        }
    }
}

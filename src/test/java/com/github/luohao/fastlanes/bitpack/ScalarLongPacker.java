package com.github.luohao.fastlanes.bitpack;

/**
 * A scalar bitpacking implementation for long array.
 */
public class ScalarLongPacker {
    public static final int R = 1024;
    public static final int T = 64;
    public static final int LANES = R / T;
    public static final int[] FL_ORDER = new int[]{0, 4, 2, 6, 1, 5, 3, 7};

    public static int index(int row, int lane) {
        int o = row / 8;
        int s = row % 8;
        return FL_ORDER[o] * 16 + s * 128 + lane;
    }

    public static long mask(int width) {
        if (width == T) {
            return -1L;
        } else {
            return (1L << (width % T)) - 1;
        }
    }

    public static void pack(long[] input, int width, long[] output) {
        for (int lane = 0; lane < LANES; lane++) {
            if (width == 0) {

            } else if (width == T) {
                for (int row = 0; row < T; row++) {
                    int idx = index(row, lane);
                    output[LANES * row + lane] = input[idx];
                }
            } else {
                long mask = mask(width);
                long tmp = 0;
                for (int row = 0; row < T; row++) {
                    int idx = index(row, lane);
                    long src = input[idx];
                    src = src & mask;
                    if (row == 0) {
                        tmp = src;
                    } else {
                        tmp = tmp | (src << ((row * width) % T));
                    }

                    int curr = (row * width) / T;
                    int next = ((row + 1) * width) / T;

                    if (next > curr) {
                        output[LANES * curr + lane] = tmp;
                        int remainingBits = ((row + 1) * width) % T;
                        tmp = src >> (width - remainingBits);
                    }
                }
            }
        }
    }

    public static void unpack(long[] input, int width, long[] output) {
        for (int lane = 0; lane < LANES; lane++) {
            long src = input[lane];
            long tmp;

            for (int row = 0; row < T; row++) {
                int curr = (row * width) / T;
                int next = ((row + 1) * width) / T;

                int shift = (row * width) % T;
                if (next > curr) {
                    int remainingBits = ((row + 1) * width) % T;
                    int currentBits = width - remainingBits;
                    tmp = (src >> shift) & mask(currentBits);
                    if (next < width) {
                        src = input[LANES * next + lane];
                        tmp |= (src & mask(remainingBits)) << currentBits;
                    }
                } else {
                    tmp = (src >> shift) & mask(width);
                }

                int idx = index(row, lane);
                output[idx] = tmp;
            }
        }
    }
}

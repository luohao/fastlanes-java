package com.github.luohao.fastlanes.bitpack;

/**
 * A scalar bitpacking implementation for byte array.
 */
public class ScalarBytePacker {
    public static final int R = 1024;
    public static final int T = 8;
    public static final int LANES = R / T;
    public static final int[] FL_ORDER = new int[]{0, 4, 2, 6, 1, 5, 3, 7};

    public static int index(int row, int lane) {
        int o = row / 8;
        int s = row % 8;
        return FL_ORDER[o] * 16 + s * 128 + lane;
    }

    public static byte mask(int width) {
        if (width == T) {
            return (byte) 0xFF;
        } else {
            return (byte) ((1 << (width % T)) - 1);
        }
    }

    public static void pack(byte[] input, int width, byte[] output) {
        for (int lane = 0; lane < LANES; lane++) {
            if (width == 0) {

            } else if (width == T) {
                for (int row = 0; row < T; row++) {
                    int idx = index(row, lane);
                    output[LANES * row + lane] = input[idx];
                }
            } else {
                byte mask = mask(width);
                byte tmp = 0;
                for (int row = 0; row < T; row++) {
                    int idx = index(row, lane);
                    byte src = input[idx];
                    src = (byte) (src & mask);
                    if (row == 0) {
                        tmp = src;
                    } else {
                        tmp = (byte) (tmp | (src << ((row * width) % T)));
                    }

                    int curr = (row * width) / T;
                    int next = ((row + 1) * width) / T;

                    if (next > curr) {
                        output[LANES * curr + lane] = tmp;
                        int remainingBits = ((row + 1) * width) % T;
                        tmp = (byte) (src >> (width - remainingBits));
                    }
                }
            }
        }
    }

    public static void unpack(byte[] input, int width, byte[] output) {
        for (int lane = 0; lane < LANES; lane++) {
            byte src = input[lane];
            byte tmp;

            for (int row = 0; row < T; row++) {
                int curr = (row * width) / T;
                int next = ((row + 1) * width) / T;

                int shift = (row * width) % T;
                if (next > curr) {
                    int remainingBits = ((row + 1) * width) % T;
                    int currentBits = width - remainingBits;
                    tmp = (byte) ((src >> shift) & mask(currentBits));
                    if (next < width) {
                        src = input[LANES * next + lane];
                        tmp |= (src & mask(remainingBits)) << currentBits;
                    }
                } else {
                    tmp = (byte) ((src >> shift) & mask(width));
                }

                int idx = index(row, lane);
                output[idx] = tmp;
            }
        }
    }
}

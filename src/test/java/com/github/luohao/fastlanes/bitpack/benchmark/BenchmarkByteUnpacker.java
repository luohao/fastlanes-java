package com.github.luohao.fastlanes.bitpack.benchmark;

import com.github.luohao.fastlanes.bitpack.ScalarBytePacker;
import com.github.luohao.fastlanes.bitpack.VectorBytePacker;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Fork(2)
@Warmup(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 50, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class BenchmarkByteUnpacker {
    public static void main(String[] args)
            throws Exception {
        BenchmarkData data = new BenchmarkData();
        data.setup();

        Options options = new OptionsBuilder()
                .verbosity(VerboseMode.NORMAL)
                .include(".*" + BenchmarkByteUnpacker.class.getSimpleName() + ".*")
                .build();

        new Runner(options).run();
    }

    private static byte mask(int bitWidth) {
        if (bitWidth == Byte.SIZE) {
            return -1;
        }
        return (byte) ((1 << bitWidth) - 1);
    }

    @Benchmark
    public void unpackScalar(BenchmarkData data) {
        ScalarBytePacker.unpack(data.getPacked(), data.getBitWidth(), data.getUnpacked());
    }

    @Benchmark
    public void unpackVector(BenchmarkData data) {
        VectorBytePacker.unpack(data.getPacked(), data.getBitWidth(), data.getUnpacked());
    }

    @State(Scope.Thread)
    public static class BenchmarkData {
        @Param({"1", "3", "5"})
        private int bitWidth;

        private final byte[] input = new byte[1024];
        private final byte[] packed = new byte[1024];
        private final byte[] unpacked = new byte[1024];
        private final Random random = new Random();

        @Setup
        public void setup() {
            for (int i = 0; i < input.length; i++) {
                input[i] = (byte) (random.nextInt() & mask(bitWidth));
            }
            ScalarBytePacker.pack(input, bitWidth, packed);
        }

        public byte[] getPacked() {
            return packed;
        }

        public byte[] getUnpacked() {
            return unpacked;
        }

        public int getBitWidth() {
            return bitWidth;
        }
    }
}
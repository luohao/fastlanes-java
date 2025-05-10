package com.github.luohao.fastlanes.bitpack.benchmark;

import com.github.luohao.fastlanes.bitpack.ScalarLongPacker;
import com.github.luohao.fastlanes.bitpack.VectorLongPacker;
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
public class BenchmarkLongUnpacker {
    public static void main(String[] args) throws Exception {
        BenchmarkByteUnpacker.BenchmarkData data = new BenchmarkByteUnpacker.BenchmarkData();
        data.setup();

        Options options = new OptionsBuilder().verbosity(VerboseMode.NORMAL).include(".*" + BenchmarkLongUnpacker.class.getSimpleName() + ".*").build();

        new Runner(options).run();
    }

    private static long mask(int bitWidth) {
        if (bitWidth == Byte.SIZE) {
            return -1L;
        }
        return (1L << bitWidth) - 1;
    }

    @Benchmark
    public void unpackScalar(BenchmarkData data) {
        ScalarLongPacker.unpack(data.getPacked(), data.getBitWidth(), data.getUnpacked());
    }

    @Benchmark
    public void unpackVector(BenchmarkData data) {
        VectorLongPacker.unpack(data.getPacked(), data.getBitWidth(), data.getUnpacked());
    }

    @State(Scope.Thread)
    public static class BenchmarkData {
        @Param({"3", "17", "35", "51", "63"})
        private int bitWidth;

        private final long[] input = new long[1024];
        private final long[] packed = new long[1024];
        private final long[] unpacked = new long[1024];
        private final Random random = new Random();

        @Setup
        public void setup() {
            for (int i = 0; i < input.length; i++) {
                input[i] = (byte) (random.nextInt() & mask(bitWidth));
            }
            ScalarLongPacker.pack(input, bitWidth, packed);
        }

        public long[] getPacked() {
            return packed;
        }

        public long[] getUnpacked() {
            return unpacked;
        }

        public int getBitWidth() {
            return bitWidth;
        }
    }
}

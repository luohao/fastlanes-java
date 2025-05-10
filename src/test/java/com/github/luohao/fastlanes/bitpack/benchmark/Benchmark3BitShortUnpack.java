package com.github.luohao.fastlanes.bitpack.benchmark;

import com.github.luohao.fastlanes.bitpack.ScalarShortPacker;
import com.github.luohao.fastlanes.bitpack.VectorShortPacker;
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
public class Benchmark3BitShortUnpack {
    public static final int bitWidth = 3;

    public static void main(String[] args) throws Exception {
        BenchmarkData data = new BenchmarkData();
        data.setup();

        Options options = new OptionsBuilder().verbosity(VerboseMode.NORMAL).include(".*" + Benchmark3BitShortUnpack.class.getSimpleName() + ".*").build();

        new Runner(options).run();
    }

    @Benchmark
    public void unpackVector(BenchmarkData data) {
        VectorShortPacker.unpack(data.getPacked(), bitWidth, data.getUnpacked());
    }

    @State(Scope.Thread)
    public static class BenchmarkData {
        private final short[] input = new short[1024];
        private final short[] packed = new short[1024];
        private final short[] unpacked = new short[1024];
        private final Random random = new Random();

        @Setup
        public void setup() {
            for (int i = 0; i < input.length; i++) {
                input[i] = (short) (random.nextInt() & ((short) ((1 << bitWidth) - 1)));
            }
            ScalarShortPacker.pack(input, bitWidth, packed);
        }

        public short[] getPacked() {
            return packed;
        }

        public short[] getUnpacked() {
            return unpacked;
        }
    }
}

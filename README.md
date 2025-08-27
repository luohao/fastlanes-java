# fastlanes-java

A Java implementation of the [FastLanes](https://github.com/cwida/FastLanes) bitpacking library.

> [!CAUTION]
> This library is **not binary compatible** with the original [FastLanes](https://github.com/cwida/FastLanes). Instead, it implements the same reordered layout as the [fastlanes Rust](https://github.com/spiraldb/fastlanes) library and is cross-validated against the [LanceDB fork](https://github.com/lancedb/lance/blob/main/rust/lance-encoding/src/encodings/physical/bitpack_fastlanes.rs).

## Example
```java
import com.github.luohao.fastlanes.bitpack.VectorBytePacker;

public class Example {
    public void roundTrip()
    {
        int width = 3;
        byte[] input = new byte[1024];
        byte[] packed = new byte[1024 * Byte.SIZE / width];
        VectorBytePacker.pack(input, width, packed);

        byte[] unpacked = new byte[1024];
        VectorBytePacker.unpack(packed, width, unpacked);
    }
}
```

## Benchmark
JMH benchmarks can be found at `test/java/com/github/luohao/fastlanes/bitpack/benchmark`.
The following benchmark results are collected from `MacBook Pro 2023`.
* Apple M2 Max
* 128-bit Neon SIMD instructions

### Unpack 3bit short[1024]

```text
Benchmark                              Mode  Cnt   Score   Error  Units
Benchmark3BitShortUnpack.unpackVector  avgt  100  29.437 ± 0.135  ns/op
```

as a comparison, the fastlanes-rust benchmark result on the same host
```text
Benchmarking unpack/unpack 16 <- 3 stack: Collecting 100 samples in estimated 5.0001 s (177M iteratio
unpack/unpack 16 <- 3 stack
                        time:   [28.087 ns 28.136 ns 28.191 ns]
                        change: [-86.516% -86.464% -86.418%] (p = 0.00 < 0.05)
                        Performance has improved.
Found 8 outliers among 100 measurements (8.00%)
  2 (2.00%) low mild
  6 (6.00%) high mild
```

### Other benchmark results to compare scalar and vector unpacker
* Unpack `byte[1024]`
```text
Benchmark                           (bitWidth)  Mode  Cnt     Score    Error  Units
BenchmarkByteUnpacker.unpackScalar           1  avgt  100  1658.538 ±  6.856  ns/op
BenchmarkByteUnpacker.unpackScalar           3  avgt  100  2193.363 ±  9.997  ns/op
BenchmarkByteUnpacker.unpackScalar           5  avgt  100  2737.992 ± 12.372  ns/op
BenchmarkByteUnpacker.unpackVector           1  avgt  100    16.254 ±  1.780  ns/op
BenchmarkByteUnpacker.unpackVector           3  avgt  100    24.112 ±  0.078  ns/op
BenchmarkByteUnpacker.unpackVector           5  avgt  100    20.636 ±  0.064  ns/op
```

* Unpack `long[1024]`
```text
Benchmark                           (bitWidth)  Mode  Cnt     Score    Error  Units
BenchmarkLongUnpacker.unpackScalar           3  avgt  100  1941.967 ± 16.094  ns/op
BenchmarkLongUnpacker.unpackScalar          17  avgt  100  2300.426 ± 18.682  ns/op
BenchmarkLongUnpacker.unpackScalar          35  avgt  100  2860.526 ± 36.820  ns/op
BenchmarkLongUnpacker.unpackScalar          51  avgt  100  3400.308 ± 15.749  ns/op
BenchmarkLongUnpacker.unpackScalar          63  avgt  100  3678.866 ± 25.722  ns/op
BenchmarkLongUnpacker.unpackVector           3  avgt  100   156.531 ±  0.644  ns/op
BenchmarkLongUnpacker.unpackVector          17  avgt  100   172.799 ±  6.136  ns/op
BenchmarkLongUnpacker.unpackVector          35  avgt  100   213.172 ± 17.468  ns/op
BenchmarkLongUnpacker.unpackVector          51  avgt  100   249.505 ± 11.350  ns/op
BenchmarkLongUnpacker.unpackVector          63  avgt  100   275.639 ±  1.112  ns/op
```
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
The following benchmark results are collected from `MacBook Air 2020`.
* Apple M1
* 128-bit Neon SIMD instructions

### Unpack 3bit short[1024]

```text
Benchmark                              Mode  Cnt   Score   Error  Units
Benchmark3BitShortUnpack.unpackVector  avgt  100  18.854 ± 0.862  ns/op
```

as a comparison, the fastlanes rust benchmark result on the same host
```text
$ ~/workspace/oss/fastlanes/ [tags/v0.1.8]  RUSTFLAGS='-C target-cpu=native' cargo bench --profile release -- unpack 
unpack/unpack 16 <- 3 stack
                        time:   [30.902 ns 31.050 ns 31.298 ns]
Found 15 outliers among 100 measurements (15.00%)
  10 (10.00%) high mild
  5 (5.00%) high severe
```

### Other benchmark results to compare scalar and vector unpacker
* Unpack `byte[1024]`
```text
Benchmark                           (bitWidth)  Mode  Cnt     Score    Error  Units
BenchmarkByteUnpacker.unpackScalar           1  avgt  100  2008.879 ± 25.600  ns/op
BenchmarkByteUnpacker.unpackScalar           3  avgt  100  2565.162 ± 16.936  ns/op
BenchmarkByteUnpacker.unpackScalar           5  avgt  100  3146.206 ± 16.993  ns/op
BenchmarkByteUnpacker.unpackVector           1  avgt  100    18.005 ±  1.937  ns/op
BenchmarkByteUnpacker.unpackVector           3  avgt  100    21.767 ±  1.501  ns/op
BenchmarkByteUnpacker.unpackVector           5  avgt  100    22.257 ±  0.119  ns/op
```

* Unpack `long[1024]`
```text
Benchmark                           (bitWidth)  Mode  Cnt     Score    Error  Units
BenchmarkLongUnpacker.unpackScalar           3  avgt  100  2272.985 ± 15.300  ns/op
BenchmarkLongUnpacker.unpackScalar          17  avgt  100  2681.418 ±  8.424  ns/op
BenchmarkLongUnpacker.unpackScalar          35  avgt  100  3253.380 ± 11.169  ns/op
BenchmarkLongUnpacker.unpackScalar          51  avgt  100  3881.627 ± 42.040  ns/op
BenchmarkLongUnpacker.unpackScalar          63  avgt  100  4168.655 ± 18.289  ns/op
BenchmarkLongUnpacker.unpackVector           3  avgt  100    19.669 ±  0.056  ns/op
BenchmarkLongUnpacker.unpackVector          17  avgt  100    21.417 ±  0.446  ns/op
BenchmarkLongUnpacker.unpackVector          35  avgt  100    24.695 ±  0.920  ns/op
BenchmarkLongUnpacker.unpackVector          51  avgt  100    31.461 ±  0.087  ns/op
BenchmarkLongUnpacker.unpackVector          63  avgt  100    36.302 ±  0.323  ns/op
```
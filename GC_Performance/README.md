||<argLine>$VALUE</argLine>||TestCase|Runtime.getRuntime().maxMemory() [MB]|| min | max | average |
|-Xmx128M -Xms128M -XX:+UseParallelOldGC|gcPerformanceWithFixedAllocation|129| 2 | 68 | 38.7 |
|-Xmx128M -Xms128M -XX:+UseParallelOldGC|gcPerformanceWithRandomAllocation|129| 0 | 35 | 19.08 |
|-Xmx128M -Xms128M -XX:+UseParallelOldGC|gcPerformanceWithFixedAllocationMultithread|129| 0 | 216 | 97.46 |
|-Xmx128M -Xms128M -XX:+UseParallelOldGC|gcPerformanceWithRandomAllocationMultithread|129| 1 | 88 | 35.96 |
| | | | | |
|-Xmx128M -Xms128M -XX:+UseParallelOldGC|gcPerformanceWithFixedAllocation|514| 2 | 168 | 94.16 |
|-Xmx512M -Xms512M -XX:+UseParallelOldGC|gcPerformanceWithRandomAllocation|514| 0 | 29 | 15.94 |
|-Xmx512M -Xms512M -XX:+UseParallelOldGC|gcPerformanceWithFixedAllocationMultithread|514| 0 | 69 | 30.16 |
|-Xmx512M -Xms512M -XX:+UseParallelOldGC|gcPerformanceWithRandomAllocationMultithread|514| 0 | 71 | 40.74 |
| | | | | |
|-Xmx1024M -Xms1024M -XX:+UseParallelOldGC|gcPerformanceWithFixedAllocation|514| 7 | 380 | 169.36 |
|-Xmx1024M -Xms1024M -XX:+UseParallelOldGC|gcPerformanceWithRandomAllocation|514| 0 | 61 | 18.04 |
|-Xmx1024M -Xms1024M -XX:+UseParallelOldGC|gcPerformanceWithFixedAllocationMultithread|514| 0 | 102 | 61.34 |
|-Xmx1024M -Xms1024M -XX:+UseParallelOldGC|gcPerformanceWithRandomAllocationMultithread|514| 0 | 58 | 36.98 |
| | | | | |
|-Xmx128M -Xms128M -XX:+UseG1GC|gcPerformanceWithFixedAllocation|134| 3 | 175 | 86.02 |
|-Xmx128M -Xms128M -XX:+UseG1GC|gcPerformanceWithRandomAllocation|134|  0 | 47 | 20.32 |
|-Xmx128M -Xms128M -XX:+UseG1GC|gcPerformanceWithFixedAllocationMultithread|134| 0 | 66 | 36.52 |
|-Xmx128M -Xms128M -XX:+UseG1GC|gcPerformanceWithRandomAllocationMultithread|134| 0 | 27 | 15.7 |
| | | | | |
|-Xmx512M -Xms512M -XX:+UseG1GC|gcPerformanceWithFixedAllocation|536| 12 | 2861 | 536.52 |
|-Xmx512M -Xms512M -XX:+UseG1GC|gcPerformanceWithRandomAllocation|536| 0 | 29 | 14.94 |
|-Xmx512M -Xms512M -XX:+UseG1GC|gcPerformanceWithFixedAllocationMultithread|536|  0 | 149 | 41.9 |
|-Xmx512M -Xms512M -XX:+UseG1GC|gcPerformanceWithRandomAllocationMultithread|536| 0 | 240 | 148.94 |
| | | | | |
|-Xmx512M -Xms512M -XX:+UseG1GC|gcPerformanceWithFixedAllocation|536| 12 | 2861 | 536.52 |
|-Xmx512M -Xms512M -XX:+UseG1GC|gcPerformanceWithRandomAllocation|536| 0 | 29 | 14.94 |
|-Xmx512M -Xms512M -XX:+UseG1GC|gcPerformanceWithFixedAllocationMultithread|536|  0 | 149 | 41.9 |
|-Xmx512M -Xms512M -XX:+UseG1GC|gcPerformanceWithRandomAllocationMultithread|536| 0 | 240 | 148.94|
| | | | | |
|-Xmx1024M -Xms1024M -XX:+UseG1GC|gcPerformanceWithFixedAllocation|1073| 34 | 169 | 87.2 |
|-Xmx1024M -Xms1024M -XX:+UseG1GC|gcPerformanceWithRandomAllocation|1073| 11 | 1446 | 402.58 |
|-Xmx1024M -Xms1024M -XX:+UseG1GC|gcPerformanceWithFixedAllocationMultithread|1073| 1 | 6841 | 2488.68 |
|-Xmx1024M -Xms1024M -XX:+UseG1GC|gcPerformanceWithRandomAllocationMultithread|1073| 0 | 101 | 45.24 |
| | | | | |
|-Xmx128M -Xms128M -XX:+UseConcMarkSweepGC|gcPerformanceWithFixedAllocation|129| 12 | 78 | 47.92 |
|-Xmx128M -Xms128M -XX:+UseConcMarkSweepGC|gcPerformanceWithRandomAllocation|129| 0 | 31 | 13.76 |
|-Xmx128M -Xms128M -XX:+UseConcMarkSweepGC|gcPerformanceWithFixedAllocationMultithread|129| 0 | 135 | 51.32 |
|-Xmx128M -Xms128M -XX:+UseConcMarkSweepGC|gcPerformanceWithRandomAllocationMultithread|129| 0 | 64 | 31.78 |
| | | | | |
|-Xmx512M -Xms512M -XX:+UseConcMarkSweepGC|gcPerformanceWithFixedAllocation|518| 3 | 67 | 34.62 |
|-Xmx512M -Xms512M -XX:+UseConcMarkSweepGC|gcPerformanceWithRandomAllocation|518| 0 | 28 | 14.54 |
|-Xmx512M -Xms512M -XX:+UseConcMarkSweepGC|gcPerformanceWithFixedAllocationMultithread|518| 0 | 75 | 31.9 |
|-Xmx512M -Xms512M -XX:+UseConcMarkSweepGC|gcPerformanceWithRandomAllocationMultithread|518| 1 | 34 | 17.18 |
| | | | | |
|-Xmx1024M -Xms1024M -XX:+UseConcMarkSweepGC|gcPerformanceWithFixedAllocation|1037| 3 | 139 | 48.48 |
|-Xmx1024M -Xms1024M -XX:+UseConcMarkSweepGC|gcPerformanceWithRandomAllocation|1037| 0 | 34 | 13.64 |
|-Xmx1024M -Xms1024M -XX:+UseConcMarkSweepGC|gcPerformanceWithFixedAllocationMultithread|1037| 0 | 70 | 39.76 |
|-Xmx1024M -Xms1024M -XX:+UseConcMarkSweepGC|gcPerformanceWithRandomAllocationMultithread|1037| 0 | 436 | 335.02 |











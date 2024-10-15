
package benchmarks;

import org.example.Matrix;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;

@BenchmarkMode({Mode.AverageTime, })
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
public class MyBenchmark {
    @Param({"100", "200", "300", "500", "1000"})
    public int size;
    private double[][] A;
    private double[][] B;
    private double[][] C;

    private List<Long> memUsedList = new ArrayList<>();

    @Setup(Level.Trial)
    public void setup() {
        Runtime runtime = Runtime.getRuntime();
        Random random = new Random();


        for (int q = 0; q < 5; q++) {
            long memBefore = runtime.totalMemory() - runtime.freeMemory();

            A = new double[size][size];
            B = new double[size][size];
            C = new double[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    A[i][j] = random.nextDouble();
                    B[i][j] = random.nextDouble();
                }
            }

            Matrix.matrixMultiplication(A, B, C);

            long memAfter = runtime.totalMemory() - runtime.freeMemory();

            long memUsed = memAfter - memBefore;
            if(memUsed>0){
                memUsedList.add(memUsed);
            }
        }

        A = new double[size][size];
        B = new double[size][size];
        C = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = random.nextDouble();
                B[i][j] = random.nextDouble();
            }
        }
    }

    @Benchmark
    public void testMethod() {
        Matrix.matrixMultiplication(A, B, C);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        long[] memUsedArray = new long[memUsedList.size()];

        for (int i = 0; i < memUsedList.size(); i++) {
            memUsedArray[i] = memUsedList.get(i);
        }

        double avgMemUsedBytes = Arrays.stream(memUsedArray).average().orElse(0.0);
        System.out.println("\nMemory used: " + avgMemUsedBytes + " Bytes for " + size + "*" + size + "matrix");
    }
}

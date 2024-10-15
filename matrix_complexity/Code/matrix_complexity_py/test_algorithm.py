from matrixMultiplicationAlgorithm import matrix_multiplication
from memory_profiler import memory_usage
import pytest
import statistics
import random


@pytest.mark.parametrize("size", [100, 200, 300, 500, 1000])
def test_matrix_multiplication(benchmark, size):
    mem_measure = []

    for _ in range(5):
        mem_before = memory_usage(-1, interval=0.1, timeout=1)[0]
        a = [[random.random() for _ in range(size)] for _ in range(size)]
        b = [[random.random() for _ in range(size)] for _ in range(size)]
        c = [[0 for _ in range(size)] for _ in range(size)]

        matrix_multiplication(a, b, c, size)
        mem_after = memory_usage(-1, interval=0.1, timeout=1)[0]
        mem_measure.append(mem_after - mem_before)
    mem_average = statistics.mean(mem_measure)

    a = [[random.random() for _ in range(size)] for _ in range(size)]
    b = [[random.random() for _ in range(size)] for _ in range(size)]
    c = [[0 for _ in range(size)] for _ in range(size)]

    benchmark.pedantic(matrix_multiplication, args=(a, b, c, size,), warmup_rounds=5, iterations=5, rounds=5)

    print(f"\nAverage memory used: {mem_average} MB for {size}*{size} matrix")
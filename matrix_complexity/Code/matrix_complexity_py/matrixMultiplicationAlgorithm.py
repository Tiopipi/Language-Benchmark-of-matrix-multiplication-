def matrix_multiplication(a, b, c, n):
    for i in range(n):
        for j in range(n):
            for k in range(n):
                c[i][j] += a[i][k] * b[k][j]

    return c
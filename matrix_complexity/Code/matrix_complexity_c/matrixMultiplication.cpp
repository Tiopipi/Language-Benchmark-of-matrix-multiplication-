#include <stdio.h>
#include <stdlib.h>






void matrixMultiplication(int n) {

    double a[n][n];
    double b[n][n];
    double c[n][n];

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            a[i][j] = (double) rand() / RAND_MAX;
            b[i][j] = (double) rand() / RAND_MAX;
            c[i][j] = 0;
        }
    }

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            for (int k = 0; k < n; ++k) {
                c[i][j] = a[i][k] * b[k][j];
             }
        }
    }
}
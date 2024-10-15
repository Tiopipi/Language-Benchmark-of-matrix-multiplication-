package org.example;

import java.util.Random;

public class Matrix {
    public static double[][] matrixMultiplication(double[][] A, double[][] B, double[][] C) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
}

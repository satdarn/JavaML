package matrixTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import matrix.Matrix;

public class MuliplicationTest {
    // Test for multiplying two matrices with raw 2D arrays
    @Test
    public void testMultiplyMatrixArray() {
        double[][] matrixA = {
            {1, 2},
            {3, 4}
        };
        double[][] matrixB = {
            {5, 6},
            {7, 8}
        };
        double[][] expected = {
            {19, 22},
            {43, 50}
        };

        double[][] result = Matrix.multiply(matrixA, matrixB);

        assertArrayEquals(expected, result, "Matrix multiplication with raw 2D arrays failed");
    }

    // Test for multiplying two Matrix objects
    @Test
    public void testMultiplyMatrixObjects() {
        Matrix matrixA = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix matrixB = new Matrix(new double[][]{
            {5, 6},
            {7, 8}
        });
        Matrix expected = new Matrix(new double[][]{
            {19, 22},
            {43, 50}
        });

        Matrix result = Matrix.multiply(matrixA, matrixB);

        assertArrayEquals(expected.getMatrix(), result.getMatrix(), "Matrix multiplication with Matrix objects failed");
    }

    // Test for multiplying incompatible matrices (should throw an exception)
    @Test
    public void testMultiplyIncompatibleMatrices() {
        double[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };
        double[][] matrixB = {
            {1, 2},
            {3, 4}
        };

        // Expect an IllegalArgumentException due to incompatible matrix dimensions
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.multiply(matrixA, matrixB);
        }, "Matrix multiplication should fail for incompatible matrices");
    }
}

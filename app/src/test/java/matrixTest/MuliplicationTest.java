package matrixTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import matrix.Matrix;

public class MuliplicationTest {
    public static void assertMatrixEquals(double[][] expected, double[][] actual, double epsilon) {
        assertNotNull(expected, "Expected matrix is null");
        assertNotNull(actual, "Actual matrix is null");
        assertEquals(expected.length, actual.length, "Matrix row counts differ");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, actual[i].length, "Matrix column counts differ in row " + i);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j], epsilon, 
                    String.format("Values differ at position [%d][%d]: expected %f, but got %f", i, j, expected[i][j], actual[i][j]));
            }
        }
    }
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

        assertMatrixEquals(expected, result, 1e-9);
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

        assertMatrixEquals(expected.get(), result.get(), 1e-9);
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
    @Test
    public void testElementWiseMultiply_ValidMatrices() {
        double[][] matrix1 = {
            {1.0, 2.0},
            {3.0, 4.0}
        };

        double[][] matrix2 = {
            {5.0, 6.0},
            {7.0, 8.0}
        };

        Matrix mat1 = new Matrix(matrix1);
        Matrix mat2 = new Matrix(matrix2);

        Matrix result = Matrix.elementWiseMultiply(mat1, mat2);

        double[][] expected = {
            {5.0, 12.0},
            {21.0, 32.0}
        };

        assertMatrixEquals(expected, result.get(), 1e-9);
    }

    @Test
    public void testElementWiseMultiply_MatricesWithDifferentDimensions() {
        double[][] matrix1 = {
            {1.0, 2.0},
            {3.0, 4.0}
        };

        double[][] matrix2 = {
            {5.0, 6.0}
        };

        Matrix mat1 = new Matrix(matrix1);
        Matrix mat2 = new Matrix(matrix2);

        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.elementWiseMultiply(mat1, mat2);
        }, "Matrices must have the same dimensions for element-wise multiplication.");
    }

    @Test
    public void testElementWiseMultiply_ZeroMatrix() {
        double[][] matrix1 = {
            {0.0, 2.0},
            {3.0, 4.0}
        };

        double[][] matrix2 = {
            {5.0, 6.0},
            {7.0, 8.0}
        };

        Matrix mat1 = new Matrix(matrix1);
        Matrix mat2 = new Matrix(matrix2);

        Matrix result = Matrix.elementWiseMultiply(mat1, mat2);

        double[][] expected = {
            {0.0, 12.0},
            {21.0, 32.0}
        };

        assertMatrixEquals(expected, result.get(), 1e-9);
    }

    @Test
    public void testElementWiseMultiply_IdentityMatrix() {
        double[][] matrix1 = {
            {1.0, 1.0},
            {1.0, 1.0}
        };

        double[][] matrix2 = {
            {2.0, 3.0},
            {4.0, 5.0}
        };

        Matrix mat1 = new Matrix(matrix1);
        Matrix mat2 = new Matrix(matrix2);

        Matrix result = Matrix.elementWiseMultiply(mat1, mat2);

        double[][] expected = {
            {2.0, 3.0},
            {4.0, 5.0}
        };

        assertMatrixEquals(expected, result.get(), 1e-9);
    }
}

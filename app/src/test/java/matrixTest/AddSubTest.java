package matrixTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import matrix.Matrix;

public class AddSubTest {

    // Test for adding two matrices with raw 2D arrays
    @Test
    public void testAddMatrixArray() {
        double[][] matrixA = {
            {1, 2},
            {3, 4}
        };
        double[][] matrixB = {
            {5, 6},
            {7, 8}
        };
        double[][] expected = {
            {6, 8},
            {10, 12}
        };

        double[][] result = Matrix.add(matrixA, matrixB);

        assertArrayEquals(expected, result, "Matrix addition with raw 2D arrays failed");
    }

    // Test for adding two Matrix objects
    @Test
    public void testAddMatrixObjects() {
        Matrix matrixA = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix matrixB = new Matrix(new double[][]{
            {5, 6},
            {7, 8}
        });
        Matrix expected = new Matrix(new double[][]{
            {6, 8},
            {10, 12}
        });

        Matrix result = Matrix.add(matrixA, matrixB);

        assertArrayEquals(expected.getMatrix(), result.getMatrix(), "Matrix addition with Matrix objects failed");
    }

    // Test for adding incompatible matrices (should throw an exception)
    @Test
    public void testAddIncompatibleMatrices() {
        double[][] matrixA = {
            {1, 2},
            {3, 4}
        };
        double[][] matrixB = {
            {5, 6}
        };

        // Expect an IllegalArgumentException due to incompatible matrix dimensions
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.add(matrixA, matrixB);
        }, "Matrix addition should fail for incompatible matrices");
    }

    // Test for subtracting two matrices with raw 2D arrays
    @Test
    public void testSubtractMatrixArray() {
        double[][] matrixA = {
            {5, 6},
            {7, 8}
        };
        double[][] matrixB = {
            {1, 2},
            {3, 4}
        };
        double[][] expected = {
            {4, 4},
            {4, 4}
        };

        double[][] result = Matrix.subtract(matrixA, matrixB);

        assertArrayEquals(expected, result, "Matrix subtraction with raw 2D arrays failed");
    }

    // Test for subtracting two Matrix objects
    @Test
    public void testSubtractMatrixObjects() {
        Matrix matrixA = new Matrix(new double[][]{
            {5, 6},
            {7, 8}
        });
        Matrix matrixB = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        Matrix expected = new Matrix(new double[][]{
            {4, 4},
            {4, 4}
        });

        Matrix result = Matrix.subtract(matrixA, matrixB);

        assertArrayEquals(expected.getMatrix(), result.getMatrix(), "Matrix subtraction with Matrix objects failed");
    }

    // Test for subtracting incompatible matrices (should throw an exception)
    @Test
    public void testSubtractIncompatibleMatrices() {
        double[][] matrixA = {
            {5, 6},
            {7, 8}
        };
        double[][] matrixB = {
            {1, 2}
        };

        // Expect an IllegalArgumentException due to incompatible matrix dimensions
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.subtract(matrixA, matrixB);
        }, "Matrix subtraction should fail for incompatible matrices");
    }
}

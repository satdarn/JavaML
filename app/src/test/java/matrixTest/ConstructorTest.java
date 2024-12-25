package matrixTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import matrix.Matrix;

public class ConstructorTest {
    // Test for Matrix(double[][] matrix)
    @Test
    public void testMatrixConstructorWith2DArray() {
        double[][] matrixData = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        Matrix matrix = new Matrix(matrixData);
        
        assertEquals(2, matrix.getRows());
        assertEquals(2, matrix.getColumns());
        assertArrayEquals(matrixData, matrix.getMatrix());
    }

    // Test for Matrix(double[][] matrix, int rows, int columns)
    @Test
    public void testMatrixConstructorWith2DArrayAndShape() {
        double[][] matrixData = {
            {1.0, 2.0},
            {3.0, 4.0}
        };
        Matrix matrix = new Matrix(matrixData, 2, 2);

        assertEquals(2, matrix.getRows());
        assertEquals(2, matrix.getColumns());
        assertArrayEquals(matrixData, matrix.getMatrix());
    }

    // Test for Matrix(int rows, int columns) - creates a matrix of zeros
    @Test
    public void testMatrixConstructorWithZeros() {
        Matrix matrix = new Matrix(2, 3); // 2 rows, 3 columns

        assertEquals(2, matrix.getRows());
        assertEquals(3, matrix.getColumns());
        assertNotNull(matrix.getMatrix());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                assertEquals(0, matrix.getMatrix()[i][j]);
            }
        }
    }

    // Test for Matrix(int rows, int columns, double value)
    @Test
    public void testMatrixConstructorWithValue() {
        Matrix matrix = new Matrix(2, 2, 5.0); // 2x2 matrix filled with 5.0

        assertEquals(2, matrix.getRows());
        assertEquals(2, matrix.getColumns());
        assertNotNull(matrix.getMatrix());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                assertEquals(5.0, matrix.getMatrix()[i][j]);
            }
        }
    }

    // Test for Matrix(int rows, int columns, double min, double max) - uniform random values
    @Test
    public void testMatrixConstructorWithRandomValues() {
        Matrix matrix = new Matrix(2, 2, 1.0, 10.0); // 2x2 matrix with random values between 1.0 and 10.0

        assertEquals(2, matrix.getRows());
        assertEquals(2, matrix.getColumns());
        assertNotNull(matrix.getMatrix());

        // Check that all values are within the given range
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                double value = matrix.getMatrix()[i][j];
                assertTrue(value >= 1.0 && value <= 10.0, 
                        "Value " + value + " is out of the specified range.");
            }
        }
    }

    // Test for invalid Matrix(double[][] matrix) where matrix is null
    @Test
    public void testMatrixConstructorWithNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Matrix(null);
        });
    }

    // Test for Matrix(double[][] matrix, int rows, int columns) - Invalid row/column mismatch
    @Test
    public void testMatrixConstructorWithInvalidShape() {
        double[][] matrixData = {
            {1.0, 2.0},
            {3.0, 4.0}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            new Matrix(matrixData, 3, 3); // Mismatch between data and shape
        });
    }

    // Test for Matrix(int rows, int columns, double value) - Test invalid dimensions
    @Test
    public void testMatrixConstructorWithInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Matrix(0, 0); // Invalid 0x0 matrix
        });
    }
}

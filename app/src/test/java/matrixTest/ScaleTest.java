package matrixTest;

import org.junit.jupiter.api.Test;

import matrix.Matrix;

import static org.junit.jupiter.api.Assertions.*;

public class ScaleTest {
    // Test for scaling a 2D array matrix by a scalar value
    @Test
    public void testScaleMatrixArray() {
        double[][] matrix = {
            {1, 2},
            {3, 4}
        };
        double scaler = 2.0;
        double[][] expected = {
            {2, 4},
            {6, 8}
        };

        double[][] result = Matrix.scale(matrix, scaler);

        assertArrayEquals(expected, result, "Scaling with raw 2D array failed");
    }

    // Test for scaling a Matrix object by a scalar value
    @Test
    public void testScaleMatrixObject() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        double scaler = 2.0;
        Matrix expected = new Matrix(new double[][]{
            {2, 4},
            {6, 8}
        });

        Matrix result = Matrix.scale(matrix, scaler);

        assertArrayEquals(expected.get(), result.get(), "Scaling with Matrix object failed");
    }

    // Test for scaling a Matrix object using the instance method
    @Test
    public void testInstanceScaleMatrixObject() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        double scaler = 3.0;
        Matrix expected = new Matrix(new double[][]{
            {3, 6},
            {9, 12}
        });

        Matrix result = matrix.scale(scaler);

        assertArrayEquals(expected.get(), result.get(), "Instance scaling with Matrix object failed");
    }

    // Test for scaling with a scalar of 0 (should result in a matrix of zeros)
    @Test
    public void testScaleWithZero() {
        double[][] matrix = {
            {1, 2},
            {3, 4}
        };
        double scaler = 0.0;
        double[][] expected = {
            {0, 0},
            {0, 0}
        };

        double[][] result = Matrix.scale(matrix, scaler);

        assertArrayEquals(expected, result, "Scaling by 0 failed");
    }

    // Test for scaling a null matrix (should return null)
    @Test
    public void testScaleWithNullMatrix() {
        double[][] matrix = null;
        double scaler = 2.0;
        
        assertThrows(NullPointerException.class,() -> {Matrix.scale(matrix, scaler);});
    }

    // Test for scaling a Matrix object with a scalar of 0 (should result in a matrix of zeros)
    @Test
    public void testScaleMatrixObjectWithZero() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        double scaler = 0.0;
        Matrix expected = new Matrix(new double[][]{
            {0, 0},
            {0, 0}
        });

        Matrix result = matrix.scale(scaler);

        assertArrayEquals(expected.get(), result.get(), "Scaling a Matrix object by 0 failed");
    }

    // Test for scaling a Matrix with a negative scalar
    @Test
    public void testScaleMatrixObjectWithNegativeScalar() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2},
            {3, 4}
        });
        double scaler = -2.0;
        Matrix expected = new Matrix(new double[][]{
            {-2, -4},
            {-6, -8}
        });

        Matrix result = matrix.scale(scaler);

        assertArrayEquals(expected.get(), result.get(), "Scaling a Matrix object with a negative scalar failed");
    }
}

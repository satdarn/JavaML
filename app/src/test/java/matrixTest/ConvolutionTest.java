package matrixTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import matrix.Matrix;

public class ConvolutionTest {

    // Test for cross-correlation with 2D array matrix and kernel
    @Test
    public void testConvolutionMatrixArray() {
        double[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[][] kernel = {
            {1, 0},
            {0, -1}
        };
        double[][] expected = {
            {4, 4},
            {4, 4}
        };

        double[][] result = Matrix.convolution(matrix, kernel);

        assertArrayEquals(expected, result, "Cross-correlation with raw 2D array failed");
    }

    // Test for cross-correlation with Matrix object and kernel
    @Test
    public void testConvolutionMatrixObject() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        Matrix kernel = new Matrix(new double[][]{
            {1, 0},
            {0, -1}
        });
        Matrix expected = new Matrix(new double[][]{
            {4, 4},
            {4, 4}
        });

        Matrix result = Matrix.convolution(matrix, kernel);

        assertArrayEquals(expected.getMatrix(), result.getMatrix(), "Cross-correlation with Matrix object failed");
    }

    // Test for cross-correlation with Matrix instance and kernel (using instance method)
    @Test
    public void testInstanceConvolutionMatrixObject() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        Matrix kernel = new Matrix(new double[][]{
            {1, 0},
            {0, -1}
        });
        Matrix expected = new Matrix(new double[][]{
            {4, 4},
            {4, 4}
        });

        Matrix result = matrix.convolution(kernel);

        assertArrayEquals(expected.getMatrix(), result.getMatrix(), "Instance cross-correlation with Matrix object failed");
    }

    // Test for cross-correlation with mismatched dimensions (should throw an exception or return null)
    @Test
    public void testConvolutionWithMismatchedDimensions() {
        double[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };
        double[][] kernel = {
            {1, 0},
            {0, -1},
            {1, 1}
        };
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.convolution(matrix, kernel);
        }, "Kernel size is larger than the matrix size.");
    }

    // Test for cross-correlation with kernel larger than the matrix (should throw an exception or return null)
    @Test
    public void testConvolutionWithLargeKernel() {
        double[][] matrix = {
            {1, 2},
            {3, 4}
        };
        double[][] kernel = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            Matrix.convolution(matrix, kernel);
        }, "Kernel size is larger than the matrix size.");
    }

    // Test for cross-correlation with a zero kernel (should return a matrix of zeros)
    @Test
    public void testConvolutionWithZeroKernel() {
        double[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[][] kernel = {
            {0, 0},
            {0, 0}
        };
        double[][] expected = {
            {0, 0},
            {0, 0}
        };

        double[][] result = Matrix.convolution(matrix, kernel);

        assertArrayEquals(expected, result, "Cross-correlation with zero kernel failed");
    }

    // Test for cross-correlation with a kernel of all ones
    @Test
    public void testConvolutionWithAllOnesKernel() {
        double[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[][] kernel = {
            {1, 1},
            {1, 1}
        };
        double[][] expected = {
            {12, 16},
            {24, 28}
        };

        double[][] result = Matrix.convolution(matrix, kernel);

        assertArrayEquals(expected, result, "Cross-correlation with all ones kernel failed");
    }

    // Test for cross-correlation with an all-negative kernel (should invert values)
    @Test
    public void testConvolutionWithNegativeKernel() {
        double[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        double[][] kernel = {
            {-1, -1},
            {-1, -1}
        };
        double[][] expected = {
            {-12, -16},
            {-24, -28}
        };

        double[][] result = Matrix.convolution(matrix, kernel);

        assertArrayEquals(expected, result, "Cross-correlation with negative kernel failed");
    }

    // Test for cross-correlation with a Matrix object containing a zero kernel
    @Test
    public void testConvolutionMatrixObjectWithZeroKernel() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        Matrix kernel = new Matrix(new double[][]{
            {0, 0},
            {0, 0}
        });
        Matrix expected = new Matrix(new double[][]{
            {0, 0},
            {0, 0}
        });

        Matrix result = matrix.convolution(kernel);

        assertArrayEquals(expected.getMatrix(), result.getMatrix(), "Cross-correlation with zero kernel failed");
    }

    // Test for cross-correlation with mismatched dimensions in Matrix objects
    @Test
    public void testConvolutionMatrixObjectsWithMismatchedDimensions() {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        });
        Matrix kernel = new Matrix(new double[][]{
            {1, 0},
            {0, -1},
            {1, 1}
        });

        assertThrows(IllegalArgumentException.class, () -> {
            matrix.convolution(kernel);
        }, "Kernel size is larger than the matrix size.");
    }
}

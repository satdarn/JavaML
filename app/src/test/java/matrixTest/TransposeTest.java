package matrixTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import matrix.Matrix;

public class TransposeTest {
    @Test
    public void testTranspose1() {
        // Test case 1
        Matrix matrix1 = new Matrix(new double[][]{
            {1, 2},
            {3, 4},
            {5, 6}
        });
        Matrix expectedResult1 = new Matrix(new double[][]{
            {1, 3, 5}, 
            {2, 4, 6}
        });
        Matrix result1 = matrix1.T();
        assertArrayEquals(expectedResult1.get(), result1.get(), "Transposition test 1 failed");
    }

    @Test
    public void testTranspose2() {
        // Test case 2
        Matrix matrix2 = new Matrix(new double[10][20]);
        Matrix expectedResult2 = new Matrix(new double[20][10]);
        Matrix result2 = matrix2.T();
        assertArrayEquals(expectedResult2.get(), result2.get(), "Transposition test 2 failed");
    }

    @Test
    public void testTranspose3() {
        // Test case 3
        double[][] matrix3 = new double[][]{
            {1, 3, 5}, 
            {2, 4, 6}
        };
        double[][] expectedResult3 = new double[][]{
            {1, 2},
            {3, 4},
            {5, 6}
        };
        double[][] result3 = Matrix.transpose(matrix3);
        assertArrayEquals(expectedResult3, result3, "Transposition test 3 failed");
    }
}
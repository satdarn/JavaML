package vectorTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import matrix.Vector;

public class DotProductTest {
    // Test for dotProduct(double[][], double[][]) - Valid input
    @Test
    public void testDotProductWithDoubleArrays_ValidInput() {
        double[][] vector1 = {{1.0}, {2.0}, {3.0}};
        double[][] vector2 = {{4.0}, {5.0}, {6.0}};

        double expected = 1.0 * 4.0 + 2.0 * 5.0 + 3.0 * 6.0; // 32.0
        double result = Vector.dotProduct(vector1, vector2);

        assertEquals(expected, result, 1e-9, "Dot product should match expected value");
    }

    // Test for dotProduct(double[][], double[][]) - Mismatched lengths
    @Test
    public void testDotProductWithDoubleArrays_MismatchedLengths() {
        double[][] vector1 = {{1.0}, {2.0}};
        double[][] vector2 = {{3.0}, {4.0}, {5.0}};

        assertThrows(IllegalArgumentException.class, () -> Vector.dotProduct(vector1, vector2));
    }

    // Test for dotProduct(double[][], double[][]) - Empty vectors
    @Test
    public void testDotProductWithDoubleArrays_EmptyVectors() {
        double[][] vector1 = {};
        double[][] vector2 = {};

        double result = Vector.dotProduct(vector1, vector2);

        assertEquals(0.0, result, "Dot product of empty vectors should be 0.0");
    }

    // Test for dotProduct(Vector, Vector) - Valid input
    @Test
    public void testDotProductWithVectors_ValidInput() {
        Vector vector1 = new Vector(new double[][]{{1.0}, {2.0}, {3.0}});
        Vector vector2 = new Vector(new double[][]{{4.0}, {5.0}, {6.0}});

        double expected = 1.0 * 4.0 + 2.0 * 5.0 + 3.0 * 6.0; // 32.0
        double result = Vector.dotProduct(vector1, vector2);

        assertEquals(expected, result, 1e-9, "Dot product should match expected value");
    }

    // Test for dotProduct(Vector, Vector) - Mismatched lengths
    @Test
    public void testDotProductWithVectors_MismatchedLengths() {
        Vector vector1 = new Vector(new double[][]{{1.0}, {2.0}});
        Vector vector2 = new Vector(new double[][]{{3.0}, {4.0}, {5.0}});

        assertThrows(IllegalArgumentException.class, () -> Vector.dotProduct(vector1, vector2));
    }

    // Test for dotProduct(Vector, Vector) - Single-element vectors
    @Test
    public void testDotProductWithVectors_SingleElement() {
        Vector vector1 = new Vector(new double[][]{{7.0}});
        Vector vector2 = new Vector(new double[][]{{3.0}});

        double expected = 7.0 * 3.0; // 21.0
        double result = Vector.dotProduct(vector1, vector2);

        assertEquals(expected, result, 1e-9, "Dot product of single-element vectors should match expected value");
    }
}

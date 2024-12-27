package vectorTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import vector.Vector;

public class ConstructerTest {
    // Test for Vector(double[] vector) - Create a column vector from an array
    @Test
    public void testVectorFrom1DArray() {
        double[] input = {1.0, 2.0, 3.0};
        Vector vector = new Vector(input);

        assertEquals(3, vector.getRows()); // Check the number of rows
        assertEquals(1, vector.getColumns()); // Check the number of columns
        double[][] matrix = vector.get();
        for (int i = 0; i < input.length; i++) {
            assertEquals(input[i], matrix[i][0]); // Check each element
        }
    }

    // Test for Vector(double[][] vector) - Valid nx1 2D array
    @Test
    public void testVectorFrom2DArray() {
        double[][] input = {{1.0}, {2.0}, {3.0}};
        Vector vector = new Vector(input);

        assertEquals(3, vector.getRows()); // Check the number of rows
        assertEquals(1, vector.getColumns()); // Check the number of columns
        double[][] matrix = vector.get();
        for (int i = 0; i < input.length; i++) {
            assertEquals(input[i][0], matrix[i][0]); // Check each element
        }
    }

    // Test for Vector(double[][] vector) - Invalid 2D array (not nx1)
    @Test
    public void testVectorFromInvalid2DArray() {
        double[][] input = {{1.0, 2.0}, {3.0, 4.0}}; // Invalid, not nx1
        assertThrows(IllegalArgumentException.class, () -> new Vector(input));
    }

    // Test for Vector(int length, double value) - Create vector of fixed length
    @Test
    public void testVectorFromLengthAndValue() {
        int length = 4;
        double value = 5.0;
        Vector vector = new Vector(length, value);

        assertEquals(length, vector.getRows()); // Check the number of rows
        assertEquals(1, vector.getColumns()); // Check the number of columns
        double[][] matrix = vector.get();
        for (int i = 0; i < length; i++) {
            assertEquals(value, matrix[i][0]); // Check each element
        }
    }

    // Test for Vector(double[] vector) - Empty input array
    @Test
    public void testVectorFromEmpty1DArray() {
        double[] input = {};
        assertThrows(IllegalArgumentException.class, () -> new Vector(input));
    }

    // Test for Vector(double[][] vector) - Empty 2D array
    @Test
    public void testVectorFromEmpty2DArray() {
        double[][] input = {{}};
        assertThrows(IllegalArgumentException.class, () -> new Vector(input));
    }

    // Test for Vector(int length, double value) - Zero length
    @Test
    public void testVectorWithZeroLength() {
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 3.0));
    }
}

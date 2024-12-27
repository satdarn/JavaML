package denseTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dense.Dense;
import matrix.Matrix;
import vector.Vector;

public class ConstructorTest {
    // Test for Dense(int inputSize, int outputSize) - Valid sizes
    @Test
    public void testDenseWithValidSizes() {
        int inputSize = 4;
        int outputSize = 3;
        Dense dense = new Dense(inputSize, outputSize);

        assertEquals(inputSize, dense.getInputSize()); // Check input size
        assertEquals(outputSize, dense.getOutputSize()); // Check output size

        double[][] weights = dense.getWeights().get();
        assertEquals(outputSize, weights.length); // Weights rows match output size
        assertEquals(inputSize, weights[0].length); // Weights columns match input size

        double[][] biases = dense.getBias().get();
        assertEquals(outputSize, biases.length); // Biases length matches output size
    }

    // Test for Dense(int inputSize, int outputSize, double[][] weights, double[] biases) - Valid custom weights and biases
    @Test
    public void testDenseWithCustomWeightsAndBiases() {
        int inputSize = 3;
        int outputSize = 2;
        double[][] weights = {
            {0.1, 0.2, 0.3},
            {0.4, 0.5, 0.6}
        };
        double[] biases = {0.7, 0.8};

        Dense dense = new Dense(inputSize, outputSize);
        dense.setWeights(new Matrix(weights));
        dense.setBias(new Vector(biases));

        assertEquals(inputSize, dense.getInputSize());
        assertEquals(outputSize, dense.getOutputSize());

        double[][] denseWeights = dense.getWeights().get();
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                assertEquals(weights[i][j], denseWeights[i][j]); // Check weights
            }
        }

        double[][] denseBiases = dense.getBias().get();
        for (int i = 0; i < biases.length; i++) {
            assertEquals(biases[i], denseBiases[i][0]); // Check biases
        }
    }

    // Test for Dense(int inputSize, int outputSize) - Zero input size
    @Test
    public void testDenseWithZeroInputSize() {
        int inputSize = 0;
        int outputSize = 3;
        assertThrows(IllegalArgumentException.class, () -> new Dense(inputSize, outputSize));
    }

    // Test for Dense(int inputSize, int outputSize) - Zero output size
    @Test
    public void testDenseWithZeroOutputSize() {
        int inputSize = 3;
        int outputSize = 0;
        assertThrows(IllegalArgumentException.class, () -> new Dense(inputSize, outputSize));
    }

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
}

package denseTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import layer.Dense;
import matrix.Matrix;
import matrix.Vector;
public class ForwardPropTest {

    // Custom assertion for matrix equality
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

    // Test for valid forward propagation (correct input size)
    @Test
    public void testForwardPropValidInput() {
        // Define the Dense layer parameters
        int inputSize = 2;   // 2 input neurons
        int outputSize = 3;  // 3 output neurons

        // Define weights (3x2 matrix) and biases (3x1 vector)
        double[][] weightArray = {
            {0.1, 0.2},
            {0.3, 0.4},
            {0.5, 0.6}
        };
        double[] biasArray = {0.7, 0.8, 0.9};

        // Create Matrix for weights and Vector for biases
        Matrix weights = new Matrix(weightArray);
        Vector biases = new Vector(biasArray);

        // Input data (2x1 matrix)
        double[][] inputArray = {
            {0.5},
            {0.6}
        };

        // Create the Dense layer
        Dense denseLayer = new Dense(inputSize, outputSize, weights, biases);

        // Create the input vector
        Vector inputVector = new Vector(inputArray);

        // Perform forward propagation
        Vector outputVector = denseLayer.forwardProp(inputVector);

        // Calculate expected output manually (for testing)
        double[][] expectedOutput = new double[outputSize][1];

        for (int i = 0; i < outputSize; i++) {
            double sum = 0.0;
            for (int j = 0; j < inputSize; j++) {
                sum += weightArray[i][j] * inputArray[j][0]; // Matrix multiplication
            }
            sum += biasArray[i]; // Add bias
            expectedOutput[i][0] = sum; // Store result
        }

        // Convert expected output to Vector
        Vector expectedVector = new Vector(expectedOutput);

        // Compare the expected and actual output
        assertMatrixEquals(expectedVector.get(), outputVector.get(), 1e-9); // Test output matrix
    }

    // Test for invalid input dimensions (should throw IllegalArgumentException)
    @Test
    public void testForwardPropInvalidInput() {
        // Define the Dense layer parameters
        int inputSize = 2;   // 2 input neurons
        int outputSize = 3;  // 3 output neurons

        // Define weights (3x2 matrix) and biases (3x1 vector)
        double[][] weightArray = {
            {0.1, 0.2},
            {0.3, 0.4},
            {0.5, 0.6}
        };
        double[] biasArray = {0.7, 0.8, 0.9};

        // Create Matrix for weights and Vector for biases
        Matrix weights = new Matrix(weightArray);
        Vector biases = new Vector(biasArray);

        // Input data (3x1 matrix) which does not match the expected input size (2x1)
        double[][] inputArray = {
            {0.5},
            {0.6},
            {0.7}
        };

        // Create the Dense layer
        Dense denseLayer = new Dense(inputSize, outputSize, weights, biases);

        // Create the input vector
        Vector inputVector = new Vector(inputArray);

        // Perform forward propagation and expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            denseLayer.forwardProp(inputVector);
        });
    }
}

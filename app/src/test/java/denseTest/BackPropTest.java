package denseTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import matrix.Matrix;
import matrix.Vector;

import layer.Dense;
import layer.Layer;

public class BackPropTest {
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
    @Test
    public void testBackPropValidGradient() {
        System.out.println("Vaild Gradient Test: ");
        int inputSize = 2;
        int outputSize = 3;
        double learningRate = 0.1;

        double[][] weightArray = {
            {0.1, 0.2},
            {0.3, 0.4},
            {0.5, 0.6}
        };
        double[] biasArray = {0.7, 0.8, 0.9};
        double[][] inputArray = {
            {0.5},
            {0.6}
        };
        double[][] gradArray = {
            {0.1},
            {0.2},
            {0.3}
        };

        Matrix weights = new Matrix(weightArray);
        Vector biases = new Vector(biasArray);
        Vector inputVector = new Vector(inputArray);
        Vector outputGrad = new Vector(gradArray);

        Dense denseLayer = new Dense(inputSize, outputSize, weights, biases);
        Layer.setLearningRate(learningRate);
        denseLayer.forwardProp(inputVector);

        Vector inputGrad = denseLayer.backProp(outputGrad);

        // Expected weights and biases after update
        double[][] expectedWeights = {
            {0.095, 0.194},
            {0.29, 0.388},
            {0.485, 0.582}
        };
        double[][] expectedBiases = {
            {0.69}, 
            {0.78}, 
            {0.87}
        };

        assertMatrixEquals(expectedWeights, denseLayer.getWeights().get(), 1e-6);
        assertMatrixEquals(expectedBiases, denseLayer.getBias().get(), 1e-6);
        assertNotNull(inputGrad);
    }

    @Test
    public void testBackPropInvalidGradientSize() {
        int inputSize = 2;
        int outputSize = 3;
        double[][] weightArray = {
            {0.1, 0.2},
            {0.3, 0.4},
            {0.5, 0.6}
        };
        double[] biasArray = {0.7, 0.8, 0.9};
        double[][] inputArray = {
            {0.5},
            {0.6}
        };
        double[][] gradArray = {
            {0.1}, 
            {0.2} // Invalid size (2 instead of 3)
        };

        Matrix weights = new Matrix(weightArray);
        Vector biases = new Vector(biasArray);
        Vector inputVector = new Vector(inputArray);
        Vector outputGrad = new Vector(gradArray);

        Dense denseLayer = new Dense(inputSize, outputSize, weights, biases);
        denseLayer.forwardProp(inputVector);

        assertThrows(IllegalArgumentException.class, () -> {
            denseLayer.backProp(outputGrad);
        });
    }

    @Test
    public void testBackPropNullGradient() {
        int inputSize = 2;
        int outputSize = 3;
        double[][] weightArray = {
            {0.1, 0.2},
            {0.3, 0.4},
            {0.5, 0.6}
        };
        double[] biasArray = {0.7, 0.8, 0.9};
        double[][] inputArray = {
            {0.5},
            {0.6}
        };

        Matrix weights = new Matrix(weightArray);
        Vector biases = new Vector(biasArray);
        Vector inputVector = new Vector(inputArray);

        Dense denseLayer = new Dense(inputSize, outputSize, weights, biases);
        denseLayer.forwardProp(inputVector);

        assertThrows(NullPointerException.class, () -> {
            denseLayer.backProp(null);
        });
    }

    @Test
    public void testBackPropZeroGradient() {
        int inputSize = 2;
        int outputSize = 3;
        double learningRate = 0.1;

        double[][] weightArray = {
            {0.1, 0.2},
            {0.3, 0.4},
            {0.5, 0.6}
        };
        double[][] biasArray = {
            {0.7}, 
            {0.8}, 
            {0.9}
        };
        double[][] inputArray = {
            {0.5},
            {0.6}
        };
        double[][] gradArray = {
            {0.0}, 
            {0.0}, 
            {0.0}
        };

        Matrix weights = new Matrix(weightArray);
        Vector biases = new Vector(biasArray);
        Vector inputVector = new Vector(inputArray);
        Vector outputGrad = new Vector(gradArray);

        Dense denseLayer = new Dense(inputSize, outputSize, weights, biases);
        Layer.setLearningRate(learningRate);
        denseLayer.forwardProp(inputVector);

        Vector inputGrad = denseLayer.backProp(outputGrad);

        // Weights and biases should remain unchanged
        assertMatrixEquals(weightArray, denseLayer.getWeights().get(), 1e-6);
        assertMatrixEquals(biasArray, denseLayer.getBias().get(), 1e-6);
    }

    @Test
    public void testBackPropLargeGradient() {
        int inputSize = 2;
        int outputSize = 3;
        double learningRate = 0.1;

        double[][] weightArray = {
            {0.1, 0.2},
            {0.3, 0.4},
            {0.5, 0.6}
        };
        double[] biasArray = {0.7, 0.8, 0.9};
        double[][] inputArray = {
            {0.5},
            {0.6}
        };
        double[][] gradArray = {
            {1e6}, 
            {1e6}, 
            {1e6}
        };

        Matrix weights = new Matrix(weightArray);
        Vector biases = new Vector(biasArray);
        Vector inputVector = new Vector(inputArray);
        Vector outputGrad = new Vector(gradArray);

        Dense denseLayer = new Dense(inputSize, outputSize, weights, biases);
        Layer.setLearningRate(learningRate);
        denseLayer.forwardProp(inputVector);

        Vector inputGrad = denseLayer.backProp(outputGrad);

        assertNotNull(inputGrad);
        // Check updates but avoid strict numeric checks for large gradients
    }
}

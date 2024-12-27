package activationTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import matrix.Vector;
import layer.activation.ReLu;
import layer.Layer;

public class ActivationTest {

    @Test
    public void testForwardPropagationPositiveValues() {
        ReLu relu = new ReLu(3); // 3 input/output size
        Vector input = new Vector(new double[]{1.0, 2.0, 3.0});
        Vector output = relu.forwardProp(input);

        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, output.toArray(), 1e-6);
    }

    @Test
    public void testForwardPropagationNegativeValues() {
        ReLu relu = new ReLu(3);
        Vector input = new Vector(new double[]{-1.0, -2.0, -3.0});
        Vector output = relu.forwardProp(input);

        assertArrayEquals(new double[]{-0.5, -1.0, -1.5}, output.toArray(), 1e-6); // Adjusted for leakySlope = 0.5
    }

    @Test
    public void testForwardPropagationMixedValues() {
        ReLu relu = new ReLu(3);
        Vector input = new Vector(new double[]{-1.0, 0.0, 2.0});
        Vector output = relu.forwardProp(input);

        assertArrayEquals(new double[]{-0.5, 0.0, 2.0}, output.toArray(), 1e-6);
    }

    @Test
    public void testBackwardPropagationPositiveValues() {
        ReLu relu = new ReLu(3);
        Vector input = new Vector(new double[]{1.0, 2.0, 3.0});
        relu.forwardProp(input); // Set inputVector in activation

        Vector outputGrad = new Vector(new double[]{0.1, 0.2, 0.3});
        Vector inputGrad = relu.backProp(outputGrad);

        assertArrayEquals(new double[]{0.1, 0.2, 0.3}, inputGrad.toArray(), 1e-6); // Gradient should pass through as is
    }

    @Test
    public void testBackwardPropagationNegativeValues() {
        ReLu relu = new ReLu(3);
        Vector input = new Vector(new double[]{-1.0, -2.0, -3.0});
        relu.forwardProp(input);

        Vector outputGrad = new Vector(new double[]{0.1, 0.2, 0.3});
        Vector inputGrad = relu.backProp(outputGrad);

        assertArrayEquals(new double[]{0.05, 0.1, 0.15}, inputGrad.toArray(), 1e-6); // Adjusted for leakySlope = 0.5
    }

    @Test
    public void testForwardAndBackwardChaining() {
        ReLu relu = new ReLu(3);
        Vector input = new Vector(new double[]{-2.0, 0.0, 4.0});
        Vector output = relu.forwardProp(input);

        Vector outputGrad = new Vector(new double[]{1.0, 1.0, 1.0});
        Vector inputGrad = relu.backProp(outputGrad);

        // Verify the chaining results
        assertArrayEquals(new double[]{-1.0, 0.0, 4.0}, output.toArray(), 1e-6); // Forward prop output
        assertArrayEquals(new double[]{0.5, 0.5, 1.0}, inputGrad.toArray(), 1e-6); // Backprop with leaky slope
    }

    @Test
    public void testInvalidVectorSize() {
        ReLu relu = new ReLu(3);
        Vector input = new Vector(new double[]{1.0, 2.0}); // Wrong size

        assertThrows(IllegalArgumentException.class, () -> relu.forwardProp(input));
    }

    @Test
    public void testLearningRateIndependence() {
        ReLu relu = new ReLu(3);
        Layer.setLearningRate(0.5); // Set learning rate globally

        Vector input = new Vector(new double[]{-1.0, 0.0, 1.0});
        Vector output = relu.forwardProp(input);

        assertArrayEquals(new double[]{-0.5, 0.0, 1.0}, output.toArray(), 1e-6); // Learning rate should not affect activation
    }
}

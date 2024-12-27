package layer.activation;

import layer.Layer;
import matrix.Vector;
import matrix.Matrix;

/**
 * This class represents an abstract activation function for a layer in a neural
 * network
 * 
 * @author Joseph Bronsten
 */
public abstract class Activation extends Layer{

    /**
     * Create an activation function with a given input
     * @param inputSize the given input
     */
    public Activation(int inputSize){
        super(inputSize, inputSize);
    }

    /**
     * The forward propegation through the layer
     * @param input the input to the forward prop
     * @return the result of the forward prop
     */
    public Vector forwardProp(Vector input){
        setInputVector(input);
        for(int index = 0; index < input.getRows(); index++){
            double value = input.get(index);
            value = activationFunc(value);
            input.set(index, value);
        }
        return input;
    }

    /**
     * The backpropagation through the layer
     * @param outputGrad the gradient of the output layer
     * @return the gradient of the input layer
     */
    public Vector backProp(Vector outputGrad){
        Vector inputGrad = new Vector(getInputVector().getRows(), 0);
        for(int index = 0; index < getInputVector().getRows(); index++){
            double value = getInputVector().get(index);
            value = activationPrime(value);
            inputGrad.set(index, value);
        }
        inputGrad = (Vector) Matrix.multiply(outputGrad, inputGrad);
        return inputGrad;
    }
    /**
     * The base activation function
     * @param input the input of the function
     * @return the output of the function
     */
    protected abstract double activationFunc(double input);

    /**
     * The derivative of the base activation function
     * @param input the input of the function
     * @return the derivative of the function
     */
    protected abstract double activationPrime(double input);
}

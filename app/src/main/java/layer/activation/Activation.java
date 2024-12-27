package layer.activation;

import layer.Layer;
import matrix.Vector;
import matrix.Matrix;

public abstract class Activation extends Layer{

    public Activation(int inputSize){
        super(inputSize, inputSize);
    }
    public Vector forwardProp(Vector input){
        setInputVector(input);
        for(int index = 0; index < input.getRows(); index++){
            double value = input.get(index);
            value = activationFunc(value);
            input.set(index, value);
        }
        return input;
    }
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
    protected abstract double activationFunc(double input);
    protected abstract double activationPrime(double input);
}

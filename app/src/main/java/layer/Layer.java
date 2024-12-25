package layer;

import vector.Vector;
/**
 * This class is an abstract implementation of a layer in a network,
 * it has a number of input nodes and a number of output nodes as well as a 
 * function that propegates forward and backwards
 * 
 * @author Joseph Bronsten
 */
public abstract class Layer{
    private int inputSize;
    private int outputSize;

    private static double learningRate;

    /**
     * Creates a layer with no input nodes and no output nodes
     */
    public Layer(){
        this.inputSize = 0;
        this.outputSize = 0;
    }
    /**
     * Creates a layer with a given input size and given output size
     * @param inputSize number of input nodes
     * @param outputSize number of output nodes
     */
    public Layer(int inputSize, int outputSize){
        this.inputSize = inputSize;
        this.outputSize = outputSize;
    }
    /**
     * Returns the number of input features
     * @return the number of input features
     */
    public int getInputSize(){
        return this.inputSize;
    }

    /**
     * Returns the number of output features
     * @return the number of output features
     */
    public int getOutputSize(){ 
        return this.outputSize;
    }

    /**
     * Returns the learning rate of all layers
     * @return the learning rate
     */
    public static double getLearningRate(){
        return learningRate;
    }

    /**
     * Sets the learning rate of all layers
     * @param rate the learning rate
     */
    public static void setLearningRate(double rate){
        learningRate = rate;
    }
    /**
     * The forward propegation through the layer
     * @param input the input to the forward prop
     * @return the result of the forward prop
     */
    public abstract Vector forwardProp(Vector input);

    /**
     * The backwards propegation through the layer 
     * @param outputGrad the gradient for the back prop
     * @return the result of the back prp
     */
    public abstract Vector backProp(Vector outputGrad);
}

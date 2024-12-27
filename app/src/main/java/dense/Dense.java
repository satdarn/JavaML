package dense;
import layer.Layer;
import matrix.Matrix;
import vector.Vector;

public class Dense extends Layer {

    private Matrix weights;
    private Vector bias;

    private Vector inputVector;

    /**
     * Creates a dense layer with a matrix of weights initialized with He 
     * initialization and a bias vector initialized with 0's
     * @param inputSize the size of the input vector
     * @param outputSize the size of the output vector
     */
    public Dense(int inputSize, int outputSize){
        super(inputSize, outputSize);

        double max = Math.sqrt(6/inputSize); // He init 
        // double max = Math.sqrt(6/(inputSize + outputSize)) // Xavier init
        double min = -max;

        this.weights = new Matrix(outputSize, inputSize, min, max);
        this.bias = new Vector(outputSize);
    }

    public Dense(int inputSize, int outputSize, Matrix weights, Vector bias){
        super(inputSize, outputSize);
        this.setWeights(weights);
        this.setBias(bias);
    }
    public void setWeights(Matrix weights) {
        if(weights.getRows() != this.getOutputSize() || 
            weights.getColumns() != this.getInputSize()){
            throw new IllegalArgumentException("Invalid weights dimensions");
        }
        this.weights = weights;
    }

    public void setBias(Vector bias){
        if(bias.getRows() != this.getOutputSize()){
            throw new IllegalArgumentException("Invalid biases length");
        }
        this.bias = bias;
    }

    public Matrix getWeights(){
        return this.weights;
    }

    public Vector getBias(){
        return this.bias;
    }
    
    /**
     * The forward propagation through the layer.
     * @param input the input vector for forward propagation.
     * @return the result of the forward propagation.
     */
    public Vector forwardProp(Vector input) {
        if(input.getRows() != this.getInputSize()){
            throw new IllegalArgumentException("Invalid input dimensions");
        }
        // Store the input vector for use in backpropagation.
        this.inputVector = input;

        // Compute the weighted sum (Z = W * X).
        Matrix output = Matrix.multiply(weights, input);

        // Add the bias to the weighted sum (Z + b).
        output = Matrix.add(output, this.bias);

        // Convert the result to a Vector and return it.
        return new Vector(output.get());
    }

    /**
     * The backward propagation through the layer.
     * @param outputGrad the gradient of the output with respect to the loss function.
     * @return the gradient of the input with respect to the loss function.
     */
    public Vector backProp(Vector outputGrad) {
        if(outputGrad.getRows() != this.getOutputSize()){
            throw new IllegalArgumentException("Invaild output gradient dimension");
        }
        // Compute the gradient of the weights (dW = dL/dY * X^T).
        Matrix weightsGrad = Matrix.multiply(outputGrad, this.inputVector.T());

        // Scale the weights gradient by the learning rate.
        weightsGrad.scale(super.getLearningRate());

        // Scale the output gradient by the learning rate for bias adjustment.
        outputGrad.scale(super.getLearningRate());

        // Update the weights by subtracting the scaled gradient (W = W - dW).
        this.weights = Matrix.subtract(this.weights, weightsGrad);

        // Update the bias by subtracting the scaled output gradient (b = b - dL/dY).
        this.bias = (Vector) Matrix.subtract(this.bias, outputGrad);

        // Calculate the input gradient for the next layer (dX = W^T * dL/dY).
        Vector inputGrad = (Vector) Matrix.multiply(this.weights.T(), outputGrad);

        // Return the input gradient for the next layer's backpropagation.
        return inputGrad;
    }
}

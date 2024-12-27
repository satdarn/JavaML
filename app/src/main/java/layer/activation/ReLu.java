package src;
public class ReLu extends Activation {
    private static double leakySlope = 0.5;
    public ReLu(int inputSize){
        super(inputSize);
    }
    public double activationFunc(double input){
        if(input >= 0){
            return input;
        }
        return input * leakySlope;
    }
    public double activationPrime(double outputGrad){
        if(outputGrad >= 0){
            return 1;
        }
        return leakySlope;
    }
}

package loss;

import loss.Loss;
import matrix.Vector;

public class CatCross implements Loss {
    @Override
    public Vector loss(Vector predicted, Vector actual) {
        Vector output = new Vector(actual.getRows(), 0);
        for(int i = 0; i < actual.getRows(); i++){
            double out = loss(predicted.get(i), actual.get(i));
            output.set(i, out);
        }
        
        return output;
    }
    @Override
    public Vector lossPrime(Vector predicted, Vector actual) {
        Vector output = new Vector(actual.getRows(), 0);
        for(int i = 0; i < actual.getRows(); i++){
            double out = lossPrime(predicted.get(i), actual.get(i));
            output.set(i, out);
        }
        return output;
    }
    
    @Override
    public double loss(double predicted, double actual) {
        return 0;
    }
    
    @Override
    public double lossPrime(double predicted, double actual) {
        return 0;
    }
}

package loss;

import matrix.Vector;

public class CatCross implements Loss {

    private void checkVec(Vector vect1, Vector vect2) {
        if(vect1.getRows() != vect2.getRows()){
            throw new IllegalArgumentException("Both vectors must have the same length.");
        }
    }
    @Override
    public double loss(Vector predicted, Vector actual) {
        checkVec(predicted, actual);
        double sum = 0;
        for(int i = 0; i < predicted.getRows(); i ++){
            sum += (actual.get(i)) * Math.log(actual.get(i));
        }   
        return -sum;     
    }

    @Override
    public Vector lossPrime(Vector predicted, Vector actual) {
        checkVec(predicted, actual);
        Vector output = new Vector(actual.getRows(), 0);
        return output;
    }

}

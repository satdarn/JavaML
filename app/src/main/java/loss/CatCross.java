package loss;

import matrix.Vector;

public class CatCross implements Loss {
    @Override
    public Vector loss(Vector predicted, Vector actual) {
        Vector output = new Vector(actual.getRows(), 0);
        for (int i = 0; i < actual.getRows(); i++) {
            double out = -actual.get(i) * Math.log(predicted.get(i)); // Applying the cross-entropy formula
            output.set(i, out);
        }
        return output;
    }

    @Override
    public Vector lossPrime(Vector predicted, Vector actual) {
        Vector output = new Vector(actual.getRows(), 0);
        for (int i = 0; i < actual.getRows(); i++) {
            // Derivative of categorical cross-entropy: p - y
            double out = predicted.get(i) - actual.get(i);
            output.set(i, out);
        }
        return output;
    }

    @Override
    public double loss(double predicted, double actual) {
        return -actual * Math.log(predicted); // Cross-entropy for a single prediction
    }

    @Override
    public double lossPrime(double predicted, double actual) {
        return predicted - actual; // Derivative for a single prediction
    }
}

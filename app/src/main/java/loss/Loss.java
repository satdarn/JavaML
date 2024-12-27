package loss;

import matrix.Vector;

public interface Loss {
    double loss(double predicted, double actual);
    Vector loss(Vector predicted, Vector actual);
    double lossPrime(double predicted, double actual);
    Vector lossPrime(Vector predicted, Vector actual);
}
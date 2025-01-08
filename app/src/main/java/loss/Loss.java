package loss;

import matrix.Vector;

public interface Loss {
    double loss(Vector predicted, Vector actual);
    Vector lossPrime(Vector predicted, Vector actual);
}
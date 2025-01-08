package lossTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import matrix.Vector;
import loss.CatCross;

public class CatCrossTest {

    // Test loss with valid input
    @Test
    public void testLossValidInput() {
        CatCross catCross = new CatCross();
        
        // Predicted vector
        double[] predictedValues = {0.1, 0.5, 0.3, 0.1};
        Vector predicted = new Vector(predictedValues);
        
        // Actual vector
        double[] actualValues = {0, 1, 0, 0};
        Vector actual = new Vector(actualValues);
        
        // Expected result (cross-entropy loss)
        Vector loss = catCross.loss(predicted, actual);
        
        assertNotNull(loss, "Loss should not be null.");
        assertEquals(1, loss.getColumns(), "Loss should have 1 row.");
        assertEquals(0.5, loss.get(0), 0.01, "Loss value is incorrect.");
    }

    // Test loss with all zero actual values (edge case)
    @Test
    public void testLossAllZeroActual() {
        CatCross catCross = new CatCross();
        
        // Predicted vector
        double[] predictedValues = {0.1, 0.5, 0.3, 0.1};
        Vector predicted = new Vector(predictedValues);
        
        // Actual vector with all zeros
        double[] actualValues = {0, 0, 0, 0};
        Vector actual = new Vector(actualValues);
        
        Vector loss = catCross.loss(predicted, actual);
        
        assertNotNull(loss, "Loss should not be null.");
        assertEquals(1, loss.getColumns(), "Loss should have 1 column.");
        assertEquals(Double.MAX_VALUE, loss.get(0), "Loss should be infinity when actual is all zeros.");
    }

    // Test lossPrime with valid input
    @Test
    public void testLossPrimeValidInput() {
        CatCross catCross = new CatCross();
        
        // Predicted vector
        double[] predictedValues = {0.1, 0.5, 0.3, 0.1};
        Vector predicted = new Vector(predictedValues);
        
        // Actual vector
        double[] actualValues = {0, 1, 0, 0};
        Vector actual = new Vector(actualValues);
        
        // Expected result for derivative of cross-entropy loss
        Vector lossPrime = catCross.lossPrime(predicted, actual);
        
        assertNotNull(lossPrime, "Loss prime should not be null.");
        assertEquals(4, lossPrime.getRows(), "Loss prime should have 4 rows.");
        assertEquals(-1.0, lossPrime.get(1), 0.01, "Loss prime for index 1 should be -1.0.");
        assertEquals(0.25, lossPrime.get(0), 0.01, "Loss prime for index 0 should be 0.25.");
    }

    // Test lossPrime with all zero actual values (edge case)
    @Test
    public void testLossPrimeAllZeroActual() {
        CatCross catCross = new CatCross();
        
        // Predicted vector
        double[] predictedValues = {0.1, 0.5, 0.3, 0.1};
        Vector predicted = new Vector(predictedValues);
        
        // Actual vector with all zeros
        double[] actualValues = {0, 0, 0, 0};
        Vector actual = new Vector(actualValues);
        
        Vector lossPrime = catCross.lossPrime(predicted, actual);
        
        assertNotNull(lossPrime, "Loss prime should not be null.");
        assertEquals(4, lossPrime.getRows(), "Loss prime should have 4 rows.");
        assertEquals(Double.POSITIVE_INFINITY, lossPrime.get(0), "Loss prime should be infinity when actual is all zeros.");
    }

    // Test loss when predicted and actual are the same (should be 0)
    @Test
    public void testLossSamePredictedActual() {
        CatCross catCross = new CatCross();
        
        // Predicted and actual vector are the same
        double[] predictedValues = {0.2, 0.5, 0.2, 0.1};
        Vector predicted = new Vector(predictedValues);
        Vector actual = new Vector(predictedValues);
        
        Vector loss = catCross.loss(predicted, actual);
        
        assertNotNull(loss, "Loss should not be null.");
        assertEquals(0, loss.get(0), 0.01, "Loss should be zero when predicted and actual are the same.");
    }

    // Test lossPrime when predicted and actual are the same
    @Test
    public void testLossPrimeSamePredictedActual() {
        CatCross catCross = new CatCross();
        
        // Predicted and actual vector are the same
        double[] predictedValues = {0.2, 0.5, 0.2, 0.1};
        Vector predicted = new Vector(predictedValues);
        Vector actual = new Vector(predictedValues);
        
        Vector lossPrime = catCross.lossPrime(predicted, actual);
        
        assertNotNull(lossPrime, "Loss prime should not be null.");
        assertEquals(0, lossPrime.get(0), 0.01, "Loss prime should be zero when predicted and actual are the same.");
    }

    // Test lossPrime with invalid predicted/actual lengths
    @Test
    public void testLossPrimeInvalidLength() {
        CatCross catCross = new CatCross();
        
        // Predicted vector has 4 elements
        double[] predictedValues = {0.1, 0.5, 0.3, 0.1};
        Vector predicted = new Vector(predictedValues);
        
        // Actual vector has 3 elements (length mismatch)
        double[] actualValues = {0, 1, 0};
        Vector actual = new Vector(actualValues);
        
        assertThrows(IllegalArgumentException.class, () -> {
            catCross.lossPrime(predicted, actual);
        });
    }

    // Test loss with invalid predicted/actual lengths
    @Test
    public void testLossInvalidLength() {
        CatCross catCross = new CatCross();
        
        // Predicted vector has 4 elements
        double[] predictedValues = {0.1, 0.5, 0.3, 0.1};
        Vector predicted = new Vector(predictedValues);
        
        // Actual vector has 3 elements (length mismatch)
        double[] actualValues = {0, 1, 0};
        Vector actual = new Vector(actualValues);
        
        assertThrows(IllegalArgumentException.class, () -> {
            catCross.loss(predicted, actual);
        });
    }
}

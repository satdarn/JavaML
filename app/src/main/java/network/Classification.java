package layer;

import matrix.Matrix;
import matrix.Vector;
import layer.Layer;
import loss.Loss;

public class Classification extends Layer implements Loss{
    private String[] classes;
    
    public Classification(int numberOfClasses, String[] classes){
        super(numberOfClasses, numberOfClasses);
        this.classes = classes;
    }

    public Vector forwardProp(Vector input) {
        return input;
    }

    public Vector backProp(Vector input) {
        return input;
    }

    public Vector oneHotEncode(String name){
        Vector result = new Vector(classes.length);
        int index = 0;
        for(String className : classes){
            if(className.equals(name)){
                result.set(index, 1);
                break;
            }
            index++;
        }
        return result;
    }

    public double loss(Vector predicted, Vector actual) {
        return 0.0;
    }

    public Vector lossPrime(Vector predicted, Vector actual) {
        return new Vector(actual.getRows(), 0);
    }

}

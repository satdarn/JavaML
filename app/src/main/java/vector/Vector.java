package vector;

import matrix.Matrix;
/**
 * This class implements a column vector as a Matrix of size n and 1, and allows
 * the dot product of two vectors to be computed 
 * 
 * @author Joseph Bronsten
 */
public class Vector extends Matrix {
    /**
     * Creates a vector object from an array of doubles, with a height of the 
     * length of the array and a width of 1
     * @param vector the array of doubles that becomes the column vector
     */
    public Vector(double[] vector) {
        super(new double[][]{vector});
        if(vector.length == 0){
            throw new IllegalArgumentException(
                "Vectors must have a size greater than 0");
        }
        double[][] column_vector = new double[vector.length][1];

        for(int i = 0; i < vector.length; i++) {
            column_vector[i][0] = vector[i];
        }
        super.set(column_vector);
    }

    /**
     * Creates a vector object from an 2d array of doubles, with a height of the 
     * length of the array and a width of 1
     * @param vector the array of doubles that becomes the column vector
     */
    public Vector(double[][] vector){
        super(vector, vector.length, 1);
        if(vector[0].length != 1){
            throw new IllegalArgumentException(
                "Vectors must have a size of nx1");
        }
    }
    /**
     * Creates a vector of a given length with each entry with a given value
     * @param length the length of the column vector
     * @param value the value at each entry
     */
    public Vector(int length, double value){
        super(length, 1, value);
    }

    /**
     * Creates a vector of a given length with each entry with a value of 0
     * @param length the length of the column vector
     */
    public Vector(int length){
        super(length, 1);
    }

    public double get(int row){
        return this.get(row, 1);
    }
    public void set(int row, double value){
        this.set(row, 1, value);
    }
    /**
     * Returns if every entry in the vector has a value of zero
     * @return true if every entry in the vector has a value of zero
     */
    public boolean isZeroVector(){
        for(double value : this.get()[0]){
            if(value!= 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the dot product of two given vectors as the sum of the product
     * of each corresponding entry
     * @param vector1 the first vector
     * @param vector2 the second vector
     * @return the dot product of the given vectors
     */
    public static double dotProduct(double[][] vector1, double[][] vector2){
        if(vector1.length != vector2.length){
            throw new IllegalArgumentException(
                    "Vectors must have the same length for dot product");
        }
        double sum = 0;
        for(int i = 0; i < vector1.length; i++){
            sum += vector1[i][0] * vector2[i][0];
        }
        return sum;
    }

    /**
     * Calculates the dot product of two given vectors as the sum of the product
     * of each corresponding entry
     * @param vector1 the first vector
     * @param vector2 the second vector
     * @return the dot product of the given vectors
     */
    public static double dotProduct(Vector vector1, Vector vector2){
        if(vector1.getRows()!= vector2.getRows()){
            throw new IllegalArgumentException(
                    "Vectors must have the same length for dot product");
        }
        double result = dotProduct(vector1.get(), 
            vector2.get());
        return result;
    }
}


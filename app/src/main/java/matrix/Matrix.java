package matrix;

import java.util.*;
/**
 * This class allows the operation of two matices through multiplication and 
 * addition as well as manipulation of a singular matrix
 * 
 * @author Joseph Bronsten
 */
public class Matrix {
    private double[][] matrix;
    private int numberOfRows;
    private int numberOfColumns;

    /**
     * Create a new matrix object given a 2d array of doubles
     * @param matrix a 2d array of doubles
     * @throws IllegalArgumentException if the matrix is null
     */
    public Matrix(double[][] matrix) {
        if(isNull(matrix)){
            throw new IllegalArgumentException(
                    "Matrix cannot be null");
        }
        this.matrix = matrix;
        this.numberOfRows = matrix.length;
        this.numberOfColumns = matrix[0].length;

    }

    /**
     * Creates a new matrix object given a 2d array of doubles and the shape of
     * the matrix
     * @param matrix a 2d array of doubles
     * @param rows an int for the number of rows in the matrix
     * @param columns an int for the number of columns in the matrix
     */
    public Matrix(double[][] matrix, int rows, int columns){
        if((matrix.length != rows) || (matrix[0].length != columns)){
            throw new IllegalArgumentException(
                    "Invalid number of rows or columns");
        }
        else {
            this.matrix = matrix;
            this.numberOfRows = rows;
            this.numberOfColumns = columns;
        }
    }

    /**
     * Create a new matrix object with a 2d array of zeros with the given shape
     * @param rows an int for the number of rows in the matrix
     * @param columns an int for the number of columns in the matrix
     */
    public Matrix(int rows, int columns){
        if(rows == 0 || columns == 0){
            throw new IllegalArgumentException(
                    "Rows and columns cannot both be zero");
        }
        this.matrix = new double[rows][columns];
        this.numberOfRows = rows;
        this.numberOfColumns = columns;
    }

    /**
     * Create a new matrix object with a 2d array of a given value with the 
     * given shape
     * @param rows an int for the number of rows in the matrix
     * @param columns an int for the number of columns in the matrix
     * @param value a double which each index of the matrix is set to
     */
    public Matrix(int rows, int columns, double value){
        if(rows == 0 || columns == 0){
            throw new IllegalArgumentException(
                    "Rows and columns cannot both be zero");
        }
        this.matrix = new double[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                this.matrix[i][j] = value;
            }
        }
        this.numberOfRows = rows;
        this.numberOfColumns = columns;
    }

    /**
     * Initializes the matrix with values drawn from a uniform distribution
     * between the given minimum and maximum values.
     * @param rows an int for the number of rows in the matrix
     * @param columns an int for the number of columns in the matrix
     * @param min the minimum value of the uniform distribution
     * @param max the maximum value of the uniform distribution
     */
    public Matrix(int rows, int columns, double min, double max) {
        this.numberOfRows = rows;
        this.numberOfColumns = columns;
        if (min >= max) {
            throw new IllegalArgumentException("Min must be less than Max.");
        }
        this.matrix = new double[rows][columns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                this.matrix[i][j] = min + (max - min) * Math.random();
            }
        }
    }

    /**
     * A helper method to check if the matrix is null
     * @param matrix 
     * @return a boolean indicating if the matrix is null
     */    
    private static boolean isNull(double[][] matrix){
        return (matrix == null);
    }

    /**
     * A helper method to check if the matrix is null
     * @param matrixA
     * @param matrixB
     * @return a boolean indicating if either matrix is null
     */
    private static boolean isNull(double[][] matrixA, double[][] matrixB){
        return (isNull(matrixA) || isNull(matrixB));
    }

    /**
     * A helper method to check if the matrix is of the right size to multiply
     * @param matrixA the first matrix to multiply
     * @param matrixB the second matrix to multipy
     */
    private static void multiplyCheck(double[][] matrixA, double[][] matrixB){
        if((matrixA[0].length != matrixB.length)) {
            throw new IllegalArgumentException(
                "Invalid shapes for matrix multiplication");
        }
    } 
    /**
     * A helper method to check if the matrix is of the right size to multiply
     * @param matrixA the first matrix to multiply
     * @param matrixB the second matrix to multiply
     */
    private static void multiplyCheck(Matrix matrixA, Matrix matrixB){
        multiplyCheck(matrixA.matrix, matrixB.matrix);
    }

    /**
     * A helper method to check if the matrix is of the right size to add
     * @param matrixA the first matrix to add
     * @param matrixB the second matrix to add
     */
    private static void addCheck(double[][] matrixA, double[][] matrixB){
        if((matrixA.length != matrixB.length) || 
            (matrixA[0].length != matrixB[0].length)){
                throw new IllegalArgumentException(
                    "Invalid shapes for matrix addition");
            }
    }

    /**
     * A helper method to check if the matrix is of the right size to add
     * @param matrixA the first matrix to add
     * @param matrixB the second matrix to add
     */
    private static void addCheck(Matrix matrixA, Matrix matrixB){
        addCheck(matrixA.matrix, matrixB.matrix);
    }

    /**
     * Returns the number of rows in the matrix
     * @return an int with the number of rows
     */
    public int getRows(){
        return this.numberOfRows;
    }

    /**
     * Returns the number of columns in the matrix
     * @return an int with the number of columns
     */
    public int getColumns(){
        return this.numberOfColumns;
    }

    /**
     * Gets the number of elements in the matrix
     * @return an int with the number of elements
     */
    public int getElementCount(){
        return this.numberOfRows * this.numberOfColumns;
    }

    /**
     * Returns a 2d array representing the matrix
     * @return a 2d array of doubles representing the matrix
     */
    public double[][] get() {
        return this.matrix;
    }

    /**
     * Sets the matrix to the given matrix
     * @param matrix the new matrix
     */
    public void set(double[][] matrix){
        if(isNull(matrix)){
            throw new IllegalArgumentException(
                    "Matrix cannot be null");
        }
        this.matrix = matrix;
        this.numberOfRows = matrix.length;
        this.numberOfColumns = matrix[0].length;
    } 

    /**
     * Returns the given row and returns it as an array
     * @param row an int with the row to return
     * @return an array of doubles representing the row
     */
    public double[] getRow(int row){
        if(row >= 0 && row < this.numberOfRows){
            return this.matrix[row];
        }
        else {
            throw new IllegalArgumentException(
                    "Invalid row index");
        }
    }
    /**
     * Returns the given column and returns it as an array
     * @param column an int with the column to return
     * @return an array of doubles representing the column
     * 
     */
    public double[] getColumn(int column){
        if(column >= 0 && column < this.numberOfColumns){
            double[] columnArray = new double[this.numberOfRows];
            for(int i = 0; i < this.numberOfRows; i++){
                columnArray[i] = this.matrix[i][column];
            }
            return columnArray;
        }
        else {
            throw new IllegalArgumentException(
                    "Invalid column index");
        }
    }

    /**
     * Gets a value at the specified index in the matrix
     * @param row the row index
     * @param column the column index
     * @return the value at the index
     */
    public double get(int row, int column){
        if((row >= 0 && row < this.numberOfRows) && 
                (column >= 0 && column < this.numberOfColumns)){
            return this.matrix[row][column];

        } 
        else {
            throw new IllegalArgumentException(
                    "Invalid row or column index");
        } 
    }

    /**
     * Sets a value at the specified index in the matrix
     * @param row the row index
     * @param column the column index
     * @param value the new value to set
     */
    public void set(int row, int column, double value){
        if((row >= 0 && row < this.numberOfRows) &&     
                (column >= 0 && column < this.numberOfColumns)){
            this.matrix[row][column] = value;
        } 
        else {
            throw new IllegalArgumentException(
                    "Invalid row or column index");
        }
    }

    /**
     * Return the transpose of the matrix
     * @param matrix the matrix to transpose
     * @return the transpose of the matrix
     */
    public static double[][] transpose(double[][] matrix){
        if(isNull(matrix)){
            return null;
        }

        // Initialize the result matrix
        double[][] result = new double[matrix[0].length][matrix.length];
        
        // Perform the transpose operation                               
        for(int i = 0; i < matrix[0].length; i++){
            for(int j = 0; j < matrix.length; j++){
                result[i][j] = matrix[j][i];
            }
        }

        return result;
    }
    
    /**
     * Returns the transpose of the matrix
     * @param matrix the matrix to transpose
     * @return the transpose of the matrix
     */
    public static Matrix transpose(Matrix matrix){
        double[][] result = transpose(matrix.get());
        return new Matrix(result);
    }

    public Matrix T(){
        return new Matrix(transpose(this.matrix));
    }

    /**
     * Returns the product of the two matrices
     * @param matrixA the first matrix in the multiplication
     * @param matrixB the second matrix in the multiplication
     * @return the product of both matrices
     */
    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        // Check if matrices are valid for multiplication
        multiplyCheck(matrixA, matrixB);

        int rows = matrixA.length;
        int columns = matrixB[0].length;
        int commonDim = matrixA[0].length;

        // Initialize the result matrix
        double[][] result = new double[rows][columns];

        // Perform matrix multiplication
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < commonDim; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }

    /**
     * Returns the product of the two matrices
     * @param matrixA the first matrix in the multiplication
     * @param matrixB the second matrix in the multiplication
     * @return the product of both matrices as a Matrix object
     */
    public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
        double[][] result = multiply(matrixA.get(), matrixB.get());
        return new Matrix(result);
    }

    /**
     * Adds two matrices and finds the sum
     * @param matrixA the first matrix to add
     * @param matrixB the second matrix to add
     * @return the sum of the matrices
     */
    public static double[][] add(double[][] matrixA, double[][] matrixB){
        // Return null if both matrices are null
        if(isNull(matrixA, matrixB)){
            return null;
        }
    
        // Check the size of the matrices and make sure they are the same size
        addCheck(matrixA, matrixB);
        
        // Iterate through the elements of the matrix and add the corresponding 
        // element together

        double[][] result = new double[matrixA.length][matrixA[0].length];
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j < matrixA[0].length; j++){
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        return result;
    }

    /**
     * Adds two matrices and returns the sum
     * @param matrixA the first matrix to add
     * @param matrixB the second matrix to add
     * @return the sum of the matrices
     */
    public static Matrix add(Matrix matrixA, Matrix matrixB){

        // Check the size of the matrices and make sure they are the same size
        addCheck(matrixA, matrixB);

        double[][] result = add(matrixA.get(), matrixB.get());
        return new Matrix(result);
    }


    /**
     * Subtracts two matrices and returns the difference
     * @param matrixA the first matrix 
     * @param matrixB the second matrix
     * @return the diffence between the first and second matrix
     */
    public static double[][] subtract(double[][] matrixA, double[][] matrixB){
        // Return null if both matrices are null
        if(isNull(matrixA, matrixB)){
            return null;
        }

        // Check the size of the matrices and make sure they are the same size
        addCheck(matrixA, matrixB);
        
        double[][] result = new double[matrixA.length][matrixA[0].length];
        for(int i = 0; i < matrixA.length; i++){
            for(int j = 0; j < matrixA[0].length; j++){
                result[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }
        
        return result;
    }
    /**
     * Subtracts two matrices and returns the difference
     * @param matrixA the first matrix 
     * @param matrixB the second matrix
     * @return the diffence between the first and second matrix
     */
    public static Matrix subtract(Matrix matrixA, Matrix matrixB){

        // Check the size of the matrix and make sure theh are of the same size
        addCheck(matrixA, matrixB);
        
        double[][] result = subtract(matrixA.get(), matrixB.get());
        return new Matrix(result);
    }

    public static double[][] scale(double[][] matrix, double scaler){
        if(isNull(matrix)){
            return null;
        }
        double[][] result = new double[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                result[i][j] = matrix[i][j] * scaler;
            }
        }
        return result;
    }

    /**
     * Scales the matrix by a given scaler
     * @param matrix the matrix to be scaled
     * @param scaler the scaling value
     * @return scaled matrix
     */
    public static Matrix scale(Matrix matrix, double scaler){
        if(isNull(matrix.get())){
            return null;
        }
        double[][] result = scale(matrix.get(), scaler);
        return new Matrix(result);
    }
    
    /**
     * Scales the matrix by a given scaler
     * @param scaler the scaling value
     * @return scaled matrix
     */
    public Matrix scale(double scaler){
        return new Matrix(scale(this.matrix, scaler));
    }

    /**
     * Computes the cross correlation between a given matrix and kernel and 
     * returns the computed matrix
     * @param matrix the matrix for the cross correleation
     * @param kernel the kernal for the cross correlation
     * @return the cross correlated matrix of the kernel and given matrix
     */
    public static double[][] convolution(double[][] matrix, double[][] kernel) {
        int matrixRows = matrix.length; // number of rows in matrix
        int matrixColumns = matrix[0].length; // number of columns in matrix
    
        int kernelRows = kernel.length; // number of rows in kernel
        int kernelColumns = kernel[0].length; // number of columns in kernel
        
        if(matrixRows < kernelRows || matrixColumns < kernelColumns) {
            throw new IllegalArgumentException("Kernel size is larger than the matrix size.");
        }

        int outputRows = matrixRows - kernelRows + 1;
        int outputColumns = matrixColumns - kernelColumns + 1;
    
        double[][] output = new double[outputRows][outputColumns];
        
        double[][] flippedKernel = new double[kernelRows][kernelColumns];
        for (int i = 0; i < kernelRows; i++) {
            for (int j = 0; j < kernelColumns; j++) {
                flippedKernel[i][j] = kernel[kernelRows - 1 - i][kernelColumns - 1 - j];
            }
        }

        for (int i = 0; i < outputRows; i++) { // Iterate over the output rows
            for (int j = 0; j < outputColumns; j++) { // Iterate over the output columns
                double sum = 0;
                // Weighted sum of element-wise multiplication between matrix and kernel
                for (int m = 0; m < kernelRows; m++) { // Iterate over kernel rows
                    for (int n = 0; n < kernelColumns; n++) { // Iterate over kernel columns
                        sum += matrix[i + m][j + n] * flippedKernel[m][n];
                    }
                }
                output[i][j] = sum;
            }
        }
        return output;
    }

    /**
     * Computes the cross correlation between a given matrix and kernel and 
     * returns the computed matrix
     * @param matrix the matrix for the cross correleation
     * @param kernel the kernal for the cross correlation
     * @return the cross correlated matrix of the kernel and given matrix
     */
    public static Matrix convolution(Matrix matrix, Matrix kernel){
        return new Matrix(
            convolution(matrix.get(), kernel.get()));
    }

    public double[][] convolution(double[][] kernal){
        return convolution(this.matrix, kernal);
    }

    public Matrix convolution(Matrix kernal){
        return new Matrix(convolution(this.matrix, kernal.get()));
    }
}


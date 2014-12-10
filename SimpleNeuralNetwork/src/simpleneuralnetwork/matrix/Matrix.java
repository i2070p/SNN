/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleneuralnetwork.matrix;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author i2070p
 */
public class Matrix extends AbstractMatrix<SimpleMatrix, Matrix> {

    public Matrix(int rows, int columns) {
        this.matrix = new SimpleMatrix(rows, columns);
    }

    public Matrix(Matrix matrix) {
        this.matrix=matrix.getOriginalMatrix();
    }

    @Override
    public Matrix add(Matrix m) {
        this.matrix = this.matrix.plus(m.getOriginalMatrix());
        return this;
    }

    @Override
    public Matrix add(double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Matrix mult(Matrix m) {
        this.matrix = this.matrix.mult(m.getOriginalMatrix());
        return this;
    }

    @Override
    public Matrix mult(double value) {
        this.matrix = this.matrix.scale(value);
        return this;
    }

    @Override
    public Matrix sub(Matrix m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Matrix sub(double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Matrix tanspose() {
        this.matrix = this.matrix.transpose();
        return this;
    }

    @Override
    public int getRowsCount() {
        return this.matrix.numRows();
    }

    @Override
    public int getColumnsCount() {
        return this.matrix.numCols();
    }

    @Override
    public String toString() {
        return this.matrix.toString();
    }

    @Override
    public void set(int row, int column, double value) {
        this.matrix.set(row, column, value);
    }

    @Override
    public double get(int row, int column) {
        return this.matrix.get(row, column);
    }

    @Override
    public SimpleMatrix copy() {
        return this.matrix.copy();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleneuralnetwork.matrix;

/**
 *
 * @author i2070p
 */
public abstract class AbstractMatrix<T, M> {

    protected T matrix;

    public T getOriginalMatrix() {
        return this.matrix;
    }

    public abstract void set(int row, int column, double value);

    public abstract double get(int row, int column);

    public abstract M add(M m);

    public abstract M add(double value);

    public abstract M mult(M m);

    public abstract M mult(double value);

    public abstract M sub(M m);

    public abstract M sub(double value);

    public abstract M tanspose();

    public abstract T copy();

    public abstract int getRowsCount();

    public abstract int getColumnsCount();

}

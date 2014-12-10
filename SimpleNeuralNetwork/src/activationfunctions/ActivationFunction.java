/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activationfunctions;

/**
 *
 * @author i2070p
 */
public abstract class ActivationFunction {

    public abstract double getValue(double arg);

    public abstract double getDerivativeValue(double arg);

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}

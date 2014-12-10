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
public class SigmFunction extends ActivationFunction {

    @Override
    public double getValue(double arg) {
        return 1/(1+Math.exp(-arg));
    }

    @Override
    public double getDerivativeValue(double arg) {
        double value = getValue(arg);
        return value * (1 - value);
    }

}

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
public class HyperbolicTangentFunction extends ActivationFunction {

    @Override
    public double getValue(double arg) {
        return Math.tanh(arg);
    }

    @Override
    public double getDerivativeValue(double arg) {
        double value = getValue(arg);
        return 1 - value * value;
    }

}

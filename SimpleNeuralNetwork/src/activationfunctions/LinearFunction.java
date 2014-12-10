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
public class LinearFunction extends ActivationFunction{

    @Override
    public double getValue(double arg) {
        return arg;
    }

    @Override
    public double getDerivativeValue(double arg) {
        return 1;
    }
    
}

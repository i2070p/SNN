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
public class BipolarFunction extends ActivationFunction{

    @Override
    public double getValue(double arg) {
        if (arg > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public double getDerivativeValue(double arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

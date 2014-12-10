/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleneuralnetwork;

/**
 *
 * @author i2070p
 */
public class SimpleInput implements InputInterface {

    private double value;

    public SimpleInput() {
        this.value = 0.0;
    }

    public SimpleInput(double value) {
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{hashCode=" + this.hashCode() + ", value=" + this.value + '}';
    }
    
    

}

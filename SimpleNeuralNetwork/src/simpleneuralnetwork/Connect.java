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
public class Connect {
    private InputInterface from;
    private InputInterface to;
    private double weight;

    public Connect(InputInterface  from, InputInterface to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public InputInterface getFrom() {
        return this.from;
    }

    public InputInterface getTo() {
        return this.to;
    }

    public double getFromValue() {
        return this.from.getValue();
    }
    
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    
}

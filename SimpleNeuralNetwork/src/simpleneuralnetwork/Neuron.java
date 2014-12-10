/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleneuralnetwork;

import activationfunctions.ActivationFunction;
import exceptions.NeuronException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author i2070p
 */
public class Neuron implements InputInterface {

    protected List<InputInterface> inputs;
    protected List<InputInterface> outputs;
    protected Double[] weights;
    private Double error, phi;

    protected ActivationFunction aFunction;

    public void reset() {
        this.phi = null;
    }

    public void setInputs(List<InputInterface> connects) {
        this.inputs = connects;
        this.reset();
    }

    public void setOutputs(List<InputInterface> connects) {
        this.outputs = connects;
    }

    public Neuron(List<InputInterface> inputs, ActivationFunction aFunction) {
        this.error = 0.0;
        this.aFunction = aFunction;
        this.inputs = inputs;
        this.weights = new Double[inputs.size() + 1];
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] = new Random().nextDouble();
        }
    }

    public Neuron(List<InputInterface> inputs, Double[] weights, ActivationFunction aFunction) {
        this.error = 0.0;

        this.aFunction = aFunction;
        this.inputs = inputs;
        this.weights = weights;
    }

    public Double[] getWeights() {
        return this.weights;
    }

    public Double getError() {
        return this.error;
    }

    public void setError(Double error) {
        this.error = error;
    }

    public ActivationFunction getAFunction() {
        return aFunction;
    }

    public void setWeights(Double[] weights) throws NeuronException {
        if (weights.length != (this.inputs.size() + 1)) {
            throw new NeuronException("The number of weights must be equal to the number of inputs increased by one.");
        }
        this.weights = weights;
        this.reset();
    }

    public List<InputInterface> getOutputs() {
        return this.outputs;
    }

    public List<InputInterface> getInputs() {
        return this.inputs;
    }

    @Override
    public double getValue() {
        if (this.phi == null) {
            double sum = this.weights[0];
            int i = 1;
            for (InputInterface connect : this.inputs) {
                sum += connect.getValue() * this.weights[i++];
            }
            this.phi=sum;
        }
        return this.aFunction.getValue(this.phi);
    }

    public Double getPhi() {
        return this.phi;
    }

    @Override
    public String toString() {
        StringBuilder connectsHashs = new StringBuilder();
        int i = 0;
        for (InputInterface connect : this.inputs) {
            connectsHashs.append(i++).append(": ").append(connect.hashCode()).append(" ");
        }
        String output = "";
        if (this.phi != null) {
            output = ",\noutput=" + this.aFunction.getValue(this.phi) + ",\nerror=" + this.error;
        }
        return this.getClass().getSimpleName() + "{" + "\nhashCode=" + this.hashCode() + ", \nconnects{ " + connectsHashs.toString() + "},\nweights=" + Arrays.toString(this.weights) + ", \naFunction=" + this.aFunction + output + "\n}";
    }

}

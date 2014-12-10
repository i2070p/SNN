/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nets;

import activationfunctions.ActivationFunction;
import activationfunctions.LinearFunction;
import exceptions.NetException;
import exceptions.NeuronException;
import java.util.ArrayList;
import java.util.List;
import simpleneuralnetwork.InputInterface;
import simpleneuralnetwork.Neuron;

/**
 *
 * @author i2070p
 */
public class MultilayerPerceptron extends AbstractNet {

    private InputLayer inputs;
    private List<List<InputInterface>> layers;
    private final ActivationFunction defaultAFunc;

    public MultilayerPerceptron(int[] neuronsOnLayers, ActivationFunction... aFuncs) throws NetException {
        this.defaultAFunc = new LinearFunction();
        boolean isValid = true;
        if (neuronsOnLayers.length > 1) {
            for (int neurons : neuronsOnLayers) {
                if (neurons <= 0) {
                    isValid = false;
                }
            }
        } else {
            isValid = false;
        }

        if (!isValid) {
            throw new NetException("Net must have much neurons on layers.");
        }

        this.layers = new ArrayList<>();

        createLayers(neuronsOnLayers, aFuncs);
    }

    private void createLayers(int[] neuronsOnLayers, ActivationFunction... aFuncs) {
        this.inputs = new InputLayer(neuronsOnLayers[0]);
        this.layers.add(inputs.getLayer());
        for (int i = 1; i < neuronsOnLayers.length; i++) {
            int neurons = neuronsOnLayers[i];
            ActivationFunction aFunc = this.defaultAFunc;
            if (i - 1 < aFuncs.length) {
                aFunc = aFuncs[i - 1];
            }
            List<InputInterface> prevLayer = this.layers.get(i - 1);
            List<InputInterface> newLayer = createLayer(neurons, prevLayer, aFunc);
            this.layers.add(newLayer);
            for (InputInterface neuron : prevLayer) {
                if (neuron instanceof Neuron) {
                    ((Neuron) neuron).setOutputs(newLayer);
                }
            }
        }
    }

    public void setWeights(List<Double[]> weights) throws NeuronException {
        int k = 0;
        for (int i = 1; i < this.layers.size(); i++) {
            List<InputInterface> layer = this.layers.get(i);
            for (InputInterface neuron : layer) {
                if (neuron instanceof Neuron) {
                    ((Neuron) neuron).setWeights(weights.get(k++));
                }
            }
        }
    }

    private List<InputInterface> createLayer(int count, List<InputInterface> prevLayer, ActivationFunction aFunc) {
        List<InputInterface> layer = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            layer.add(new Neuron(prevLayer, aFunc));
        }
        return layer;
    }

    public Double[] getValues() {
        this.netReset();
        int outCount = this.layers.size();
        Double[] results = new Double[this.layers.get(outCount - 1).size()];
        int i = 0;
        for (InputInterface neuron : this.layers.get(outCount - 1)) {
            results[i++] = neuron.getValue();
        }
        return results;
    }

    public void setInputValues(Double[] values) throws NetException {
        this.inputs.setInputValues(values);
    }

    protected void netReset() {
        for (List<InputInterface> layer : this.layers) {
            for (InputInterface neuron : layer) {
                if (neuron instanceof Neuron) {
                    ((Neuron) neuron).reset();
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sLayers = new StringBuilder();
        int layerId = 0;
        for (List<InputInterface> layer : this.layers) {
            sLayers.append("Layer: ").append(layerId++).append("\n");
            for (InputInterface neuron : layer) {
                sLayers.append(neuron.toString()).append("\n");
            }
            sLayers.append("\n");
        }
        return this.getClass().getSimpleName() + "{\n" + sLayers.toString() + "}";
    }

/*    public void traverse(NeuronOperation operation) {
        for (int i = this.layers.size()-1; i > 0 ; i--) {
            List<InputInterface> layer = this.layers.get(i);
            int j = 0;
            for (InputInterface neuron : layer) {
                if (neuron instanceof Neuron) {
                    operation.operation(i, j++, (Neuron) neuron, i == (this.layers.size() - 1));
                }
            }
        }
    }
*/

    public List<List<InputInterface>> getLayers() {
        return this.layers;
    }

}

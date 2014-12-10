/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import activationfunctions.ActivationFunction;
import activationfunctions.BipolarFunction;
import activationfunctions.LinearFunction;
import activationfunctions.UnipolarFunction;
import exceptions.NetException;
import exceptions.NeuronException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import nets.InputLayer;
import nets.MultilayerPerceptron;
import simpleneuralnetwork.InputInterface;
import simpleneuralnetwork.Neuron;
import utils.DataProcessor;
import utils.DataSet;

/**
 *
 * @author i2070p
 */
public class LearningUtils {

    private LearningUtils() {

    }

    public static Neuron deltaRule(DataSet data, double eta, int epochs, ActivationFunction aFunc) throws NetException, NeuronException {
        Double[] inputs = new Double[data.get(0).get(DataSet.inId).length];
        InputLayer iLayer = new InputLayer(inputs.length);
        Double[] w = new Double[inputs.length + 1];
        Arrays.fill(w, 0.0);
        Neuron learner = new Neuron(iLayer.getLayer(), w, aFunc);
        int n = 1, epoch = 0;
        while (n > 0 && epoch < epochs) {
            n = 0;
            data.shuffle();
            for (int i = 0; i < data.size(); i++) {
                inputs = data.get(i).get(DataSet.inId);
                double d = data.get(i).get(DataSet.outId)[0];

                iLayer.setInputValues(inputs);
                learner.setInputs(iLayer.getLayer());

                double y = learner.getValue();
                double err = d - y;
                if (err != 0) {
                    w[0] = w[0] + eta * err;
                    for (int j = 1; j < w.length; j++) {
                        w[j] = w[j] + eta * err * inputs[j - 1];
                    }
                    learner.setWeights(w);
                    n++;
                }
            }
            epoch++;
        }
        return learner;
    }
    //Parametry data - zbior danych wejsciowych/wyjsciowych, epochs - ilosc epok, eta - wspolczynnik szybkosci uczenia, aFuncs - funkcje aktywacji na poszczegolnych warstwach sieci
    //funkcja zwraca nauczona siec MLP
    public static MultilayerPerceptron backPropagation(DataSet data, int[] neuronsOnLayers, int epochs, double eta, ActivationFunction... aFuncs) throws NetException, NeuronException {
        
        //tworzenie nowej sieci MLP (ona bedzie uczona)
        MultilayerPerceptron result = new MultilayerPerceptron(neuronsOnLayers, aFuncs);
        for (int epoch = 0; epoch < epochs; epoch++) {
            //dla kolejnych probek
            for (int p = 0; p < data.size(); p++) {
                //ustawiam na wejscia sieci dane wejsciowe probki p
                result.setInputValues(data.get(p).get(DataSet.inId));
                //pobieram liste warstw sieci MLP
                List<List<InputInterface>> layers = result.getLayers();
                //iteruje po liscie warstw od warstwy wyjsciowej do wejsciowej
                for (int i = layers.size() - 1; i > 0; i--) {
                    List<InputInterface> layer = layers.get(i);
                    int j = 0;
                    /*nie jest konieczne osobne wyliczanie wyjsc neuronow na warstwach 
                    poniewaz jest to zalatwione wywolaniem metody getValue() na konkretnym neuronie*/
                    
                    //iteruje po kolejnych neuronach warstwy
                    for (InputInterface input : layer) {
                        if (input instanceof Neuron) {
                            Neuron neuron = (Neuron) input;
                            //jezeli neuron znajduje sie na warstwie wyjsciowej to licze jego blad zgodnie z f'(phi)(dp-yp)
                            if (i == (layers.size() - 1)) {
                                neuron.setError((data.get(p).get(DataSet.outId)[j] - neuron.getValue()) * neuron.getAFunction().getDerivativeValue(neuron.getPhi()));
                            } else {
                                //jezeli neuron znajduje sie na warstwach ukrytych to wyznaczam jego blad zgodnie z wzorem (3) 81 slajd prezentacji
                                double err = 0;
                                for (InputInterface output : neuron.getOutputs()) {
                                    if (output instanceof Neuron) {
                                        err += ((Neuron) output).getWeights()[j + 1] * ((Neuron) output).getError() * neuron.getAFunction().getDerivativeValue(neuron.getPhi());
                                    }
                                }
                                neuron.setError(err);
                            }
                        }
                        j++;
                    }
                }
                //majac wyznaczone wszystkie bledy przystepuje do korekty
                
                //znow iteruje po warstwach od wyjsciowej do wejsciowej
                for (int i = layers.size() - 1; i > 0; i--) {
                    List<InputInterface> layer = layers.get(i);
                    
                    //dla kolejnych neurnow na warstwie:
                    for (InputInterface input : layer) {
                        if (input instanceof Neuron) {
                            Neuron neuron = (Neuron) input;
                            //pobieram tablice wag neuronu
                            Double[] w = neuron.getWeights();
                            
                            //poprawka dla w[0] osobna poniewaz to waga dla biasu
                            w[0] += neuron.getError() * eta;
                            //poprawka pozostalych wag
                            for (int o = 1; o < w.length; o++) {
                                w[o] += neuron.getError() * eta * neuron.getInputs().get(o - 1).getValue();
                            }
                            //ustawienie nowych wag
                            neuron.setWeights(w);
                        }
                    }
                }
            }
        }
        return result;
    }

}

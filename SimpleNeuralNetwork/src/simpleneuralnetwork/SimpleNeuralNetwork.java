/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleneuralnetwork;

import activationfunctions.BipolarFunction;
import activationfunctions.HyperbolicTangentFunction;
import activationfunctions.LinearFunction;
import activationfunctions.SigmFunction;
import activationfunctions.UnipolarFunction;
import exceptions.NetException;
import exceptions.NeuronException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import learning.LearningUtils;
import nets.InputLayer;
import nets.MultilayerPerceptron;
import simpleneuralnetwork.matrix.Matrix;
import utils.DataProcessor;
import utils.DataSet;

/**
 *
 * @author i2070p
 */
public class SimpleNeuralNetwork {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Simple Neuron
            InputLayer iLayer = new InputLayer(new Double[]{1.0, 2.0});
            Double[] w = new Double[]{0.5, 2.5, 1.5};

            Neuron neuron = new Neuron(iLayer.getLayer(), w, new BipolarFunction());

            //  System.out.println(neuron);
            // System.out.println(neuron.getValue());
            //MLP Example
            MultilayerPerceptron mlp = new MultilayerPerceptron(new int[]{2, 2, 3, 1}, new LinearFunction(), new LinearFunction(), new LinearFunction());

            List<Double[]> weights = new ArrayList<>();
            weights.add(new Double[]{0.1, -0.1, 0.1});
            weights.add(new Double[]{0.2, -0.2, 0.2});
            weights.add(new Double[]{-0.1, 0.2, 0.3});
            weights.add(new Double[]{0.1, 0.1, 0.2});
            weights.add(new Double[]{-0.2, 0.1, 0.2});
            weights.add(new Double[]{-0.1, 0.3, -0.2, 0.1});
            mlp.setWeights(weights);

            mlp.setInputValues(new Double[]{1.0, 2.0});

            //  System.out.println(Arrays.toString(mlp.getValues()));
            //  System.out.println(mlp.toString());
            //DeltaRule Example
            double eta = 0.2;
            int epochs = 10000;
            DataSet dSet = DataSet.loadDataFromFiles("C:\\Temp\\SN\\dane8d_a_i.txt", "C:\\Temp\\SN\\dane8d_a_o.txt", "  ");
           // System.out.println(LearningUtils.deltaRule(dSet, eta, epochs, new UnipolarFunction()));

            //Back propagation example
            dSet = DataSet.loadDataFromFiles("C:\\Temp\\SN\\dane_1D_sin3_i.txt", "C:\\Temp\\SN\\dane_1D_sin3_o.txt", "  ");

            MultilayerPerceptron result = LearningUtils.backPropagation(dSet, new int[]{1, 10, 1}, epochs, eta, new SigmFunction(), new HyperbolicTangentFunction());
            StringBuilder sb = new StringBuilder();

            //vector for matlab...
            sb.append("x=[");
            for (int p = 0; p < dSet.size(); p++) {
                result.setInputValues(dSet.get(p).get(DataSet.inId));
                sb.append(result.getValues()[0]).append(" ");
            }
            sb.append("];");
            System.out.println(sb.toString());

        } catch (NetException | NeuronException | IOException ex) {
            Logger.getLogger(SimpleNeuralNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

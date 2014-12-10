/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author i2070p
 */
public class DataProcessor {

    private DataSet data;


    public DataProcessor(String inPath, String outPath, String delimiter) throws IOException {
        this.data = DataSet.loadDataFromFiles(inPath, outPath, delimiter);
    }
    
    public DataProcessor(DataSet data) {
        this.data = data;

    }
   
    public int getInputsCount() {
        return this.data.get(0).get(DataSet.inId).length;
    }

    public int getOutputsCount() {
        return this.data.get(0).get(DataSet.outId).length;
    }

    public void startProcessing(ProcessOperation operation) {
        Map<String, Double[]> row;
        for (int i = 0; i < this.data.size(); i++) {
            row = this.data.get(i);
            operation.operation(i++, row.get(DataSet.inId), row.get(DataSet.outId));
        }
    }

    public static void main(String[] args) {
        try {
            DataProcessor dp = new DataProcessor("C:\\Temp\\SN\\dane8d_a_i.txt", "C:\\Temp\\SN\\dane8d_a_o.txt", "  ");
            dp.startProcessing(new ProcessOperation() {
                @Override
                public void operation(int id, Double[] inputs, Double[] outputs) {
                    System.out.println("Id: " + id);
                    System.out.println("Inputs: " + Arrays.toString(inputs));
                    System.out.println("Outputs: " + Arrays.toString(outputs));
                    System.out.println();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DataProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

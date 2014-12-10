/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author i2070p
 */
public class DataSet {

    public static final String inId = "in";
    public static final String outId = "out";
    
    private List<Map<String, Double[]>> data;

    public DataSet() {
        this.data = new ArrayList<>();
    }

    public int size() {
        return this.data.size();
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public boolean add(Map<String, Double[]> e) {
        return this.data.add(e);
    }

    public void clear() {
        this.data.clear();
    }

    public Map<String, Double[]> get(int i) {
        return this.data.get(i);
    }

    public Map<String, Double[]> set(int i, Map<String, Double[]> e) {
        return this.data.set(i, e);
    }

    public void add(int i, Map<String, Double[]> e) {
        this.data.add(i, e);
    }

    public void shuffle() {
        Collections.shuffle(this.data);
    }

    public static DataSet loadDataFromFiles(String inPath, String outPath, String delimiter) throws IOException {
        DataSet result = new DataSet();
        processFile(DataSet.inId, inPath, result, delimiter);
        processFile(DataSet.outId, outPath, result, delimiter);
        return result;
    }

    private static void processFile(String id, String path, DataSet data, String delimiter) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        final Double[] arr = new Double[0];
        int i = 0;
        while ((line = br.readLine()) != null) {
            if (data.size() == i) {
                data.add(new HashMap<String, Double[]>());
            }
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(delimiter);
            List<Double> values = new ArrayList<>();
            while (scanner.hasNext()) {
                values.add(Double.valueOf(scanner.next()));
            }
            data.get(i).put(id, values.toArray(arr));
            i++;
        }
        br.close();

    }

}

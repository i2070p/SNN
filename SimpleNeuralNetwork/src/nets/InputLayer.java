/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nets;

import exceptions.NetException;
import java.util.ArrayList;
import java.util.List;
import simpleneuralnetwork.InputInterface;
import simpleneuralnetwork.SimpleInput;

/**
 *
 * @author i2070p
 */
public class InputLayer extends AbstractNet {
    
    private List<InputInterface> layer;

    public InputLayer(int count) {
        this.layer = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            this.layer.add(new SimpleInput(i));
        }
    }
    
    public InputLayer(Double[] values) throws NetException {
        this.layer = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            this.layer.add(new SimpleInput(i));
        }
        setInputValues(values);
    }
    
    public List<InputInterface> getLayer() {
        return this.layer;
    }
    
    public final void setInputValues(Double[] values) throws NetException {
        int i = 0;
        if (values.length != this.layer.size()) {
            throw new NetException("Length of array with weights must be equal to the number of inputs on first layer");
        }
        for (InputInterface input : this.layer) {
            if (input instanceof SimpleInput) {
                ((SimpleInput) input).setValue(values[i++]);
            }
        }
    }
    
}

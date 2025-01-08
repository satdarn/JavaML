package network;
import java.util.ArrayList;
import java.util.Collections;

import layer.Layer;
import layer.activation.ReLu;
import layer.Dense;


public class Multilayer {
    private ArrayList<Layer> layers;

    public Multilayer(Layer[] layers){
        this.layers = new ArrayList<Layer>();
        Collections.addAll(this.layers, layers);
    }

    public Multilayer() {
        this.layers = new ArrayList<Layer>();
    }

    public void addLayer(Layer layer){
        this.layers.add(layer);
    }
    
    public Layer removLayer(int index){
        return this.layers.remove(index);
    }

    public Layer getLayer(int index){
        return this.layers.get(index);
    }
    public static void main(String[] args){
        Multilayer network = new Multilayer();
        // Add layers to the network...
        // Example:
        network.addLayer(new Dense(100, 50));
        network.addLayer(new ReLu(50));
        network.addLayer(new Dense(50, 10));
    }
    
}

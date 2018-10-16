package com.company;

import java.util.Arrays;

public class Network {

    // Hyper parameters (with defaults)
    double learningRate = 0.04;

    // Training Examples
    double[][] inputs;
    double[][] targets;

    // Network Structure
    int[] structure;
    Layer[] layers;

    Network(int[] structure, double[][] inputs, double[][] targets){
        this.inputs  = inputs;
        this.targets = targets;
        this.structure = structure;

        initializeLayers();
    }

    void showOutput(){
        System.out.println("\nOutputs: ");
        for (int i = 0; i < targets.length; i++){
            System.out.println(Arrays.toString(feedForward(inputs[i])));
        }
    }

    // ------------------------- Network Starts Here --------------------------

    void initializeLayers(){
        layers = new Layer[structure.length-1];
        for (int i = 0; i < layers.length; i++){
            layers[i] = new Layer(structure[i], structure[i+1]);
        }
    }

    void train(int iterations, boolean printError){

        // Set starting time
        long time = System.currentTimeMillis();

        for (int i = 0; i < iterations; i++){
            for (int j = 0; j < inputs.length; j++){
                feedForward(inputs[j]);
                backPropagate(targets[j]);
            }

            // Get training time
            if (i == iterations-1){
                time = System.currentTimeMillis() - time;
                System.out.println("Trained In: " + time/1000f + " seconds (printing makes it look as if it takes longer)");
            }

            if (printError && i % (iterations/10) == 0){
                System.out.println(Arrays.toString(layers[layers.length-1].error));
            }
        }
    }


    double[] feedForward(double[] inputs){
        layers[0].feedForward(inputs);

        for (int i = 1; i < layers.length; i++){
            layers[i].feedForward(layers[i-1].outputs); // Feed forward each layer using the previous layer's outputs
        }

        return layers[layers.length-1].outputs; // Return the prediction
    }

    void backPropagate(double[] targets){
        for (int i = layers.length-1; i >= 0; i--){

            if (i == layers.length-1){
                layers[i].backPropagateOutput(targets); // Back propagate the output layer with the targets
            }
            else{
                // Back propagate the hidden layer with the forward layer's weights and gamma
                layers[i].backPropagateHidden(layers[i+1].weights, layers[i+1].gamma);
            }
        }

        for (int i = 0; i < layers.length; i++){
            layers[i].updateWeights(learningRate);
        }
    }

    void setRate(double learningRate){
        this.learningRate = learningRate;
    }
}
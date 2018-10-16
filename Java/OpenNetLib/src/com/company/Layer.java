package com.company;

public class Layer {
    int numberOfInputs;
    int numberOfOutputs;

    double[] inputs;
    double[] outputs;

    double[][] weights;
    double[][] weightsDelta;

    double[] error;
    double[] gamma;

    Layer(int numberOfInputs, int numberOfOutputs){
        this.numberOfInputs  = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;

        this.inputs  = new double[numberOfInputs];
        this.outputs = new double[numberOfOutputs];

        weights = new double [numberOfOutputs][numberOfInputs];
        weightsDelta = new double [numberOfOutputs][numberOfInputs];

        error = new double[numberOfOutputs];
        gamma = new double[numberOfOutputs];

        initializeWeights();
    }

    void initializeWeights(){
        for (int i = 0; i < numberOfOutputs; i++){
            for (int j = 0; j < numberOfInputs; j++){
                weights[i][j] = Math.random() - 0.5; // Random double between -0.5 and 0.5
            }
        }
    }

    void feedForward(double[] inputs){
        this.inputs = inputs;
        for (int i = 0; i < numberOfOutputs; i++){
            outputs[i] = 0;

            for (int j = 0; j < numberOfInputs; j++){
                outputs[i] += weights[i][j] * inputs[j];
            }
            outputs[i] = Activations.tanh(outputs[i]);
        }
    }

    void backPropagateOutput(double[] targets){
        for (int i = 0; i < numberOfOutputs; i++){
            error[i] = outputs[i] - targets[i]; // Calculating error
            gamma[i] = error[i] * Activations.tanhDeriv(outputs[i]);  // Calculating gamma
        }

        for (int i = 0; i < numberOfOutputs; i++) {
            for (int j = 0; j < numberOfInputs; j++) {
                weightsDelta[i][j] = gamma[i]*inputs[j]; // Calculating the derivative of the weights
            }
        }
    }

    void backPropagateHidden(double[][] forwardWeights, double[] forwardGamma){

        for (int i = 0; i < outputs.length; i++){
            error[i] = 0;
            for (int j = 0; j < forwardGamma.length; j++){
                error[i] += forwardGamma[j] * forwardWeights[j][i]; // Calculating the error of each node
            }

            gamma[i] = error[i] * Activations.tanhDeriv(outputs[i]); // Calculate gamma of each node
        }

        for (int i = 0; i < numberOfOutputs; i++){
            for (int j = 0; j < numberOfInputs; j++){
                weightsDelta[i][j] = gamma[i]*inputs[j]; // Calculate weights delta (derivative)
            }
        }

    }

    void updateWeights(double learningRate){
        for (int i = 0; i < numberOfOutputs; i++){
            for (int j = 0; j < numberOfInputs; j++){
                weights[i][j] -= weightsDelta[i][j]*learningRate;
            }
        }
    }
}

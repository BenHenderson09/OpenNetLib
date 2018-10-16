package com.company;

public class Main {

    public static void main(String[] args) {
        // Hyper Parameters
        double learningRate = 0.001;
        int    iterations   = 500;

        // Define Network
        Network net = new Network(new int[] {3, 25, 25, 1}, inputs, outputs);
        net.train(50000, true);
        net.showOutput();
    }

    static double[][] inputs = new double[][]
                    {{0, 0, 1 },
                    { 0, 1, 1},
                    { 1,0,1 },
                    { 1, 1, 1} };

    static double[][] outputs = new double[][]
                    {{0 },
                    { 1 },
                    { 1 },
                    { 0 }};
}

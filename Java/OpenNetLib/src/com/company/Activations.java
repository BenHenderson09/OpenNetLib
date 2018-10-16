package com.company;

public class Activations {

    // -----------------------------  Hyperbolic Tangent ------------------------------------

    static double tanh (double x){
        return Math.tanh(x);
    }

    static double tanhDeriv(double x){
        return x = 1-(Math.pow(x, 2));
    }
}

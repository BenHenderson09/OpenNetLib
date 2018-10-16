package com.company;

public class Activations {

    // -----------------------------  Hyperbolic Tangent ------------------------------------
    static double tanh (double x){
        return Math.tanh(x);
    }

    static double tanhDeriv(double x){
        return x = 1-(Math.pow(x, 2));
    }
    
    // -----------------------------  Sigmoid ------------------------------------
    static double sigmoid (double x){ 
        return 1/(1+Math.exp(-x));
    }

    static double sigDeriv (double x){ 
        return  x*(1-x);
    }
}

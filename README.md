# OpenNetLib (ONL)

OpenMatrixLib is an open-source library created for development with dynamically sized neural networks. It includes:

  - Completely dynamic network structure
  - As many layers as you like
  - Completely customizable 

# Setup
Currently, ONL only supports Java. Usage is very easy, simply navigate inside the "Java" directory, and use the .JAR file. 
This .JAR file can be easily imported for use in any java project.

# Usage
ONL was created to be a simple way to quickly include a neural network into some code, the syntax is quite simple, without
unnecessary jargon. Here is an example,

```Java
package com.company;
import com.company.Network;

public class Main {

    public static void main(String[] args) {
        // Hyper Parameters
        double learningRate = 0.04;
        int    iterations   = 50000;

        // Define Network (structure, inputs, outputs)
        Network net = new Network(new int[] {3, 25, 25, 1}, Data.inputs, Data.outputs);
        
        // Set the learning rate (optional, but reccomended)
        net.setRate(learningRate);
        
        // Train the network (iterations, print the error)
        net.train(iterations, true);
        net.showOutput();
    }
```
Data Class:
```Java
public class Data {
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
```
Output:
```
Error:
[0.27238434922075355]
[2.181088143010339E-4]
[9.948002634360045E-5]
[6.329391320218121E-5]
[4.603214320134334E-5]
[3.59982632165207E-5]
[2.9465908444402467E-5]
[2.488717632456392E-5]
[2.1506312431266965E-5]
[1.891142141564253E-5]
Trained In: 1.454 seconds (printing makes it look as if it takes longer)

Outputs: 
[9.721295395558516E-6]
[0.9968911729767921]
[0.996830284729961]
[-5.506876616920131E-6]
```

As you can see from the example above, the network successfully predicts the output value of an XOR table dataset. You will also notice
how some values are given in scientific notation, E.G (-5.506876616920131E-6). This simply means a value very close to zero.

### Problem Description

Consider a program with two main functions listed below:

```
 int gateway(int num_replicas)
 int request()
```

The request function should draw a random number between 1 and 30, sleep for a number of seconds given by the random number drawn, and return the number value. In turn, the gateway function must start num_replicas threads, and each thread will execute the request function.

#### First Problem

Write in Java and C a version of the program where the gateway function returns -1 if no threads have terminated, before 8 seconds, the request function (without waiting for the other threads to finish).


* The solution code in C is [here](https://github.com/dalesEwerton/PC-Lista1/blob/master/4/C/solucaoAC.c).
* The solution code in Java is [here](https://github.com/dalesEwerton/PC-Lista1/blob/master/4/C/solucaoBC.c).

#### Second Problem

Write, in Java and C, the version of the program where the gateway function returns -1 if any threads are still running, after 16 seconds, the request function for all created replicas.

* The solution code in C is [here](https://github.com/dalesEwerton/PC-Lista1/tree/master/4/Java/Questao-4/src/respostaA).
* The solution code in Java is [here](https://github.com/dalesEwerton/PC-Lista1/tree/master/4/Java/Questao-4/src/respostaB).

#### Notes:

1. Minor changes to the indicated API can be made so that the code can be adapted to the indicated languages. Contact the teacher if you want to make any changes.

2. In Java, do not use any libraries other than the SDK. Also, do not consider any advanced concurrency control mechanisms (only Runnable and Thread classes / interfaces, as well as synchronized, volatile, and wait / notify primitives). In C, use pthreads.

#### Command Used for Compiling (in C) on Linux

```
gcc -pthread -o solucao solucao.c
```

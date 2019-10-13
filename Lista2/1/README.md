### Problem Description

Consider a program with two main functions listed below:

```
int gateway (int num_replicas)
int request ()
```

The request function should draw a random number between 1 and 30, sleep for a number of seconds given by the random number drawn, and return the number value. In turn, the gateway function must start num_replicas goroutines, and each thread goroutines the request function.

#### First Problem 

Write a version of the program where the gateway function returns the value of the first goroutine that finishes executing the request function (without waiting for the other goroutines to finish).

#### Second Problem 

Write a version of the program where the gateway function returns the sum of the values returned in the execution of the request function for all created replicas.

Note: Minor changes to the indicated API can be made so that the code can be adapted to the indicated language. Contact the teacher if you want to make any changes.

### Solutions

* The resolution for the first situation is [here](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/1/1a/questao1a.go).
* The resolution for the second situation is [here](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/1/1b/questao1b.go).
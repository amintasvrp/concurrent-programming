### Problem Description

Experimentally evaluate how light Go goroutines are relative to Java threads. In particular, you should evaluate the memory consumption of equivalent programs in their languages.

### Resolution

#### Methodology

To develop the experiment, we designed a task that should be implemented in both languages, go and java, to measure the memory consumption of Threads and Goroutines. The chosen task was to execute `n` threads/goroutines that iterated in a` for` command 100 times and each iteration slept for 0.1 second. In this way, we ensure that the tasks performed are not a major source of memory consumption, which helps us measure memory consumption by threads/goroutines more accurately.

[Click here to see the GO implementation](./MultiCounter.go)

[Click here to see the Java Class implementation](./MultiCounter.java)

[Click here to see the Java Class execution](./RunCounter.java)

Both the `go` implementation and the` java` implementation take as argument the number of threads / goroutines that must be created to perform the task.

#### Test Execution

The tests were performed as follows:

* A Shell Script has been created to iterate in the sequence `{1..5000..5}`
* Sequence index was passed as task execution parameter in each iteration

[Click to see a script has been created to execute the GO tests](./rungo.sh)

[Click to see a script has been created to execute the Java tests](./runjava.sh)

### Results

#### Memory Consumption Growth Trend

The graph below shows the maximum memory consumption **in Kilobytes** of each execution for threads and goroutines. [You can also view the higher quality chart on Flourish](https://public.flourish.studio/visualisation/460244/).

<p align="center">
  <img src="./outputs/Threads%20vs%20Goroutines%20-%20Consumo%20de%20memoria%20maximo.png">
</p>

In it we can observe that, as expected, the memory consumption of Goroutines is always lower than the Java Threads.
One thing that catches our attention and that can be seen in the graph is that the Java Threads maximum memory consumption remains constant for a certain range of Threads, unlike Goroutines, where the maximum consumption grows linearly as its numbers increase.

The graph below shows the average memory consumption **in Kilobytes** for each run. The average was calculated by collecting the amount of memory consumed every second of execution.[Também é possível ver o gráfico com mais qualidade no Flourish](https://public.flourish.studio/visualisation/460238/).

<p align="center">
  <img src="./outputs/Threads%20vs%20Goroutines%20-%20Consumo%20de%20memoria%20medio@2x.png">
</p>


Even more asymmetrically, it is possible to observe in this graph the same patterns observed in the previous graph.

#### Scatter Observed in Memory Cost per Thread / Goroutine

The graph below shows the dispersion of memory consumption ** (in Kilobytes) ** by Thread / Goroutine while running the experiment.[Também é possível ver o gráfico com mais qualidade no Flourish](https://public.flourish.studio/visualisation/460267/).The y axis is in logarithmic scale for easy viewing.

![](./outputs/Gráfico%20de%20dispersão%20do%20custo%20de%20memória%20por%20thread_goroutine@2x.png)

Data for this graph were generated from [experiment results](./outputs/results.csv), calculating for each run memory consumed value divided by the number of Threads/Goroutines of the execution, disregarding the value of the initial execution and can be found [in this file](./outputs/cost.csv).

We can see that the Goroutines memory consumption dispersion, left column in the graph, was much less pronounced than the Java Threads dispersion.
With the exception of `outliers`, the memory cost per Goroutine in the experiments ranged from **0.6Kb** to **0.7Kb**, and from **0.8Kb** to **2Kb** for Threads.


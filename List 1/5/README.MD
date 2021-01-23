### Problem Description

Perform a performance comparison between the following pairs of alternate data structures. Your answer should contain a description of the experiments performed, the code to perform the experiments, as well as the code to analyze the experimental data. In addition, a brief description of the results achieved with plots illustrating them.

It is important to evaluate performance differences as the number of threads increases (ie, as the content increases). Also, it is important to evaluate the workload ratio (between operations that change data structure and operations that do not). In particular, the size of structures can also be an important factor (this is the case of CopyOnWriteArrayList).

### Resolution

#### Methodology

The methodology used for both item A and item B was the same.

#### Tests

Initially classes were created that implement the Java Runnable interface. Such classes received as a parameter in their builders
* Instance of concurrent collection where tests would be run;
* The reference of a Latch used to ensure complete execution of all threads;
* An id to designate the thread;
* The total amount of insert, remove, and read operations to perform;
* Sets under which parameters are taken for insertions, removals and readings.

We then create test execution classes in the collections, one class for each question item, [Q05A.java](./Q05A.java) and [Q05B.java](./Q05B.java).

These classes are given as a parameter (via command line).
* A number representing the collection to be used in the test.
* The number of read operations to perform
* The number of write operations to perform
* Maximum number of concurrent threads to be used in the test

Each test was then performed in isolation to prevent the 'remains' of previous operations from interfering with the outcome of subsequent tests.

Under these conditions, the following tests were performed for each of the 4 collections.
* 50% read operations and 50% write operations;
* 90% read operations and 10% write operations;
* 10% read operations and 90% write operations;
* 1% read operations and 99% write operations.

Each test was run for a maximum number of threads ranging from 1 to 60.

[Outputs for tests performed can be found here](./outputs).

#### Graphics

In the graphs chosen to view the test results for this question each point indicates on the `x` axis the test execution time in nanoseconds and on the` y` axis the number of threads created for the test. We also plotted a trend curve of the results based on the points obtained from the experiment. The color of each trendline point indicates the collection used in the test, and the bold number in the upper left corner of each graph shows the number of operations performed on each of the tests.

### Results

#### ConcurrentHashMap vs Collections.synchronizedMap

Test chart that uses 50% read operations and 50% interactive, quality write operations [here](https://public.flourish.studio/visualisation/394146/).

Test chart using 10% read operations and 90% interactive, quality write operations [here](https://public.flourish.studio/visualisation/394174/).

Test chart that uses 90% read operations and 10% interactive, quality write operations [here](https://public.flourish.studio/visualisation/394177/).

In our tests, the `ConcurrentHashMap` collection runtime was shorter than the` SynchronizedMap` collection on all tests for a high number of threads. However, it is important to note that sometimes, for operations using a low number of threads, the `SynchronizedMap` collection obtained a better result, especially when we were also considering a smaller number of operations.
We also note that the higher the read operations ratio, the more performance the `ConcurrentHashMap` collection increases.

#### CopyOnWriteArrayList vs Collections.synchronizedList

Test chart that uses 50% of write operations and 50% of read operations, quality and interactively, [here](https://public.flourish.studio/visualisation/397240/).

Test chart that uses 10% read operations and 90% interactive, quality write operations [here](https://public.flourish.studio/visualisation/397241/).

Graph for the test using 90% read operations and 10% interactive, quality write operations [here](https://public.flourish.studio/visualisation/397242/).

Test chart that uses 99% read operations and 1% interactive, quality write operations [here](https://public.flourish.studio/visualisation/397243/).


For item B the `SynchronizedList` collection obtained a better result in the vast majority of the experiment. In much of the experiment its execution time was so shorter than the `CopyOnWriteArrayList` collection that we had to put the` x` axis of the logarithmic scale of this item, otherwise its results would be so close to the `y` axis that it would be difficult the visualization.

In this item we add an extra test, 99% of read operations and 1% of write operations, assuming that the `CopyOnWriteArrayList` collection is impaired as the number of write operations increases in the collection. However, even in this case, and for the case where only 10% of operations were write, such a collection only achieves performance equivalent to the `SynchronizedList` collection for a low number of operations and relatively small size collections.

It is obvious to conclude that the performance of the `CopyOnWriteArrayList` collection is better as the proportion of write operations performed is smaller. However, in our tests, even when only 1% of the operations are write, such a collection can only give results equivalent to the `SynchronizedList` collection, having inferior results in all other test cases.
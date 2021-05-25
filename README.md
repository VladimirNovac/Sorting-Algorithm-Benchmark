# Sorting-Algorithm-Benchmark
A Java command line application which benchmarks the performance of sorting algorithms when sorting integer arrays.

# Implementation & Structure
In this project, I chose to test the following algorithms: bubble sort, insertion sort, selection sort, merge sort and counting sort. I set the number of iterations for each algorithm to 10 and the data set number to 13.
```java
// number of iterations for each algorithm int iterations = 10; 
// number of random sets for each iteration int setNumber = 13;
```
To hold the random data, I generated 5 independent array lists which I filled with random integers according to the data sets.
```java
for (int i = 0; i < iterations; i++) { 
arrListBubble.add(generateRandomArray(new int[setNumber][])); 
arrListSelection.add(generateRandomArray(new int[setNumber][])); 
arrListInsert.add(generateRandomArray(new int[setNumber][])); 
arrListCounting.add(generateRandomArray(new int[setNumber][])); 
arrListMerge.add(generateRandomArray(new int[setNumber][])); 
}
```
I created 5 arrays of arrays of type double to store the time for each algorithm. After this, I ran each algorithm with its own random data ten times. 
Each time the algorithm would run, a different set of random data would be executed.
```java
for (int i = 0; i < iterations; i++) { 
timeElapsedBubble[i] = runAlgorithm(arrListBubble.get(i), 1); 
timeElapsedSelection[i] = runAlgorithm(arrListSelection.get(i), 2); 
timeElapsedInsert[i] = runAlgorithm(arrListInsert.get(i), 3); 
timeElapsedCounting[i] = runAlgorithm(arrListCounting.get(i), 4); 
timeElapsedMerge[i] = runAlgorithm(arrListMerge.get(i), 5); }
```
When all the tests were finished, I averaged the times stored in each array to get the final times for each data set.
```java
//get an average number for each algorithm from the 10 iterations 
double[] bubble = averageArray(timeElapsedBubble); 
double[] selection = averageArray(timeElapsedSelection); 
double[] insert = averageArray(timeElapsedInsert); 
double[] counting = averageArray(timeElapsedCounting); 
double[] merge = averageArray(timeElapsedMerge);
```
The results were then printed to the console.
![alt text](https://github.com/VladimirNovac/Sorting-Algorithm-Benchmark/blob/main/jpg/results.jpg "results")

# Benchmark Results
Sample results table – all values are in milliseconds, and are the average of 10 repeated runs.
The top row shows the number of random integers tested.
| Size           | 100 | 250 | 500 | 750 | 1000 | 1250 | 2500 | 3750 | 5000 | 6250 | 7500 | 8750 | 10000 |
| -------------- |----:|----:|----:|----:|-----:|-----:|-----:|-----:|-----:|-----:|-----:|-----:|------:|
| Bubble Sort    |3.044|2.114|5.913|7.953|9.810|12.699|46.091|103.267|194.433|306.132|505.372|720.195|938.881|
| Selection Sort |0.236|0.705|1.287|2.484|3.932|4.942|16.861|35.278|59.727|89.141|125.985|171.053|222.291|
| Insertion Sort |0.092|0.280|0.701|1.328|2.175|2.939|11.071|18.178|27.610|42.827|64.213|87.376|111.704|
| Counting Sort  |0.060|0.135|0.261|0.392|0.521|0.668|1.330|2.051|2.759|3.494|4.177|4.917|5.640|
| Merge Sort     |0.244|0.303|0.577|0.744|0.899|1.137|2.312|3.552|4.740|5.897|7.388|8.405|9.380|

![alt text](https://github.com/VladimirNovac/Sorting-Algorithm-Benchmark/blob/main/jpg/graph.jpg "graph")

# Summary
All algorithms behave more or less the same when sorting a small number of input data. There is very little difference between them up until the 1500 mark. From then on, we can see a definite difference in their growth.
As shown in the graph, bubble sort grows at the fastest rate then the other algorithms tested.
Selection sort comes in second and insertion sort is third.
Lastly, the counting sort is obscured by merge sort but both show very similar results.
As it was to be expected, counting sort is the fastest of the five. Due to the small number of input integers (max 10000), merge sort comes in very close to counting sort.
This test has shown results that are in line with other, more comprehensive tests carried out.

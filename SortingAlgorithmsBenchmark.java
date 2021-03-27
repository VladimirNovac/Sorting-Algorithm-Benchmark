


import java.util.ArrayList;


public class SortingAlgorithmsBenchmark {

    public static void main(String[] args) {
        // number of iterations for each algorithm
        int iterations = 10;
        // number of random sets for each iteration
        int setNumber = 13;

        System.out.println("---------- Sorting Algorithms Benchmark ----------");
        System.out.println("-           Generating Random Arrays...          -");

        //generate 5 independent arrayLists to hold the random data
        ArrayList<int[][]> arrListBubble = new ArrayList<>();
        ArrayList<int[][]> arrListSelection = new ArrayList<>();
        ArrayList<int[][]> arrListInsert = new ArrayList<>();
        ArrayList<int[][]> arrListCounting = new ArrayList<>();
        ArrayList<int[][]> arrListMerge = new ArrayList<>();

        // add the random integers to each arrayList
        for (int i = 0; i < iterations; i++) {
            arrListBubble.add(generateRandomArray(new int[setNumber][]));
            arrListSelection.add(generateRandomArray(new int[setNumber][]));
            arrListInsert.add(generateRandomArray(new int[setNumber][]));
            arrListCounting.add(generateRandomArray(new int[setNumber][]));
            arrListMerge.add(generateRandomArray(new int[setNumber][]));
        }

        System.out.println("-     Running Algorithms with test data...       -");

        // create 5 array of arrays to store the times of the running algorithms
        double[][] timeElapsedBubble = new double[iterations][];
        double[][] timeElapsedSelection = new double[iterations][];
        double[][] timeElapsedInsert = new double[iterations][];
        double[][] timeElapsedCounting = new double[iterations][];
        double[][] timeElapsedMerge = new double[iterations][];

        // run each algorithm with it's own random array set and store times in the allocated arrays
        for (int i = 0; i < iterations; i++) {
            timeElapsedBubble[i] = runAlgorithm(arrListBubble.get(i), 1);
            timeElapsedSelection[i] = runAlgorithm(arrListSelection.get(i), 2);
            timeElapsedInsert[i] = runAlgorithm(arrListInsert.get(i), 3);
            timeElapsedCounting[i] = runAlgorithm(arrListCounting.get(i), 4);
            timeElapsedMerge[i] = runAlgorithm(arrListMerge.get(i), 5);
        }
        System.out.println("-                      Done                      -");
        System.out.println("--------------------------------------------------");
        System.out.println();

        //get an average number for each algorithm from the 10 iterations
        double[] bubble = averageArray(timeElapsedBubble);
        double[] selection = averageArray(timeElapsedSelection);
        double[] insert = averageArray(timeElapsedInsert);
        double[] counting = averageArray(timeElapsedCounting);
        double[] merge = averageArray(timeElapsedMerge);

        //print the results to the console
        printResults(bubble, selection, insert, counting, merge);
    }

    //method to generate random number with values from 0 to 100
    public static int[] randomArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }
    //method to fill the input array with random int values
    public static int[][] generateRandomArray(int[][] inputArray) {
        inputArray[0] = randomArray(100);
        int arrayCount = 1;
        for (int i = 250; i <= 1250; i += 250) {
            inputArray[arrayCount] = randomArray(i);
            arrayCount++;
        }
        for (int i = 2500; i <= 10000; i += 1250) {
            inputArray[arrayCount] = randomArray(i);
            arrayCount++;
        }
        return inputArray;
    }
    //method to run an algorithm benchmark based on an input number
    public static double[] runAlgorithm(int[][] inputArray, int algorithmNumber) {
        //initialise Tester class that contains the sorting algorithms
        Tester tester = new Tester();
        double[] timeElapsed = new double[13];
        for (int i = 0; i < 13; i++) {
            long startTime = System.nanoTime();
            switch (algorithmNumber) {
                case 1:
                    tester.bubbleSort(inputArray[i]);
                case 2:
                    tester.selectionSort(inputArray[i]);
                case 3:
                    tester.insertionSort(inputArray[i]);
                case 4:
                    tester.countSort(inputArray[i]);
                case 5:
                    tester.mergeSort(inputArray[i], inputArray[i].length);
            }
            long endTime = System.nanoTime();
            long timeElapsedNanos = endTime - startTime;
            double timeElapsedMillis = timeElapsedNanos / 1000000.0;
            timeElapsed[i] = timeElapsedMillis;
        }
        return timeElapsed;
    }

    //method to create an average number from total entries
    public static double[] averageArray(double[][] timeElapsedInsertion) {
        double[] averagedArray = new double[13];
        for (int i = 0; i < timeElapsedInsertion[0].length; i++) {
            for (double[] doubles : timeElapsedInsertion) {
                averagedArray[i] += doubles[i];
            }
        }
        return averagedArray;
    }
    //method to print results to the console
    public static void printResults(double[] bubble, double[] selection, double[] insert, double[] counting, double[] merge){
        String leftAlignFormat = " %-9s  %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f   %-7.3f %n";
        String leftAlignFormatHeader = " %-14s  %-8s  %-8s  %-8s  %-8s  %-8s  %-8s  %-8s  %-8s  %-8s  %-8s  %-8s  %-8s  %-8s %n";
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format(leftAlignFormatHeader,"Size","100","250","500","750","1000","1250","2500","3750","5000","6250","7500","8750","10000");
        System.out.format(leftAlignFormat, "Bubble Sort   ", bubble[0], bubble[1], bubble[2], bubble[3], bubble[4], bubble[5] ,bubble[6] ,bubble[7] ,bubble[8] ,bubble[9] ,bubble[10] ,bubble[11] ,bubble[12]);
        System.out.format(leftAlignFormat, "Selection Sort", selection[0], selection[1], selection[2], selection[3], selection[4], selection[5] ,selection[6] ,selection[7] ,selection[8] ,selection[9] ,selection[10] ,selection[11] ,selection[12]);
        System.out.format(leftAlignFormat, "Insertion Sort", insert[0], insert[1], insert[2], insert[3], insert[4], insert[5] ,insert[6] ,insert[7] ,insert[8] ,insert[9] ,insert[10] ,insert[11] ,insert[12]);
        System.out.format(leftAlignFormat, "Counting Sort ", counting[0], counting[1], counting[2], counting[3], counting[4], counting[5] ,counting[6] ,counting[7] ,counting[8] ,counting[9] ,counting[10] ,counting[11] ,counting[12]);
        System.out.format(leftAlignFormat, "Merge Sort    ", merge[0], merge[1], merge[2], merge[3], merge[4], merge[5] ,merge[6] ,merge[7] ,merge[8] ,merge[9] ,merge[10] ,merge[11] ,merge[12]);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    }
}


class Tester {


    public void bubbleSort(int[] inputArray) {
        boolean swapped;
        for (int i = 0; i < inputArray.length - 1; i++)
        {
            swapped = false;
            for (int j = 0; j < inputArray.length - i - 1; j++)
            {
                if (inputArray[j] > inputArray[j + 1])
                {
                    // swap inputArray[j] and inputArray[j+1]
                    int temp = inputArray[j];
                    inputArray[j] = inputArray[j + 1];
                    inputArray[j + 1] = temp;
                    swapped = true;
                }
            }
            // IF no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }


        public void insertionSort(int[] inputArray) {
        int n = inputArray.length;
        for (int i = 1; i < n; ++i) {
            int key = inputArray[i];
            int j = i - 1;

            /* Move elements of inputArray, that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && inputArray[j] > key) {
                inputArray[j + 1] = inputArray[j];
                j = j - 1;
            }
            inputArray[j + 1] = key;
        }
    }

    public void selectionSort(int[] inputArray)  {
        int n = inputArray.length;

        // One by one move boundary of unsorted sub array
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (inputArray[j] < inputArray[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = inputArray[min_idx];
            inputArray[min_idx] = inputArray[i];
            inputArray[i] = temp;
        }
    }

    public void mergeSort(int[] inputArray, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        System.arraycopy(inputArray, 0, l, 0, mid);
        if (n - mid >= 0) System.arraycopy(inputArray, mid, r, 0, n - mid);
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(inputArray, l, r, mid, n - mid);
    }

    public void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public void countSort(int[] inputArray) {
        int k = inputArray.length+1;
        // create an integer array of size n to store sorted array
        int[] output = new int[inputArray.length];
        // create an integer array of size k, initialized by all zero
        int[] freq = new int[k];
        // using value of integer in the input array as index,
        // store count of each integer in freq[] array
        for (int i: inputArray) {
            freq[i]++;
        }
        // calculate the starting index for each integer
        int total = 0;
        for (int i = 0; i < k; i++) {
            int oldCount = freq[i];
            freq[i] = total;
            total += oldCount;
        }
        // copy to output array, preserving order of inputs with equal keys
        for (int i: inputArray) {
            output[freq[i]] = i;
            freq[i]++;
        }
        // copy the output array back to the input array
        System.arraycopy(output, 0, inputArray, 0, inputArray.length);
    }

}

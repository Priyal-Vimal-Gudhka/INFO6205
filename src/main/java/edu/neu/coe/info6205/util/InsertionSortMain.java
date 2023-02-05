package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Random;

public class InsertionSortMain {

    public static void main(String[] args) {

        for (int n = 200; n <= 20000; n = n * 2) {
            System.out.println("n = " + n);

            Integer[] randomlyGeneratedArray = generateArrayOfDifferentType("RandomArray", n);
            Integer[] ordered = generateArrayOfDifferentType("OrderedArray",n);
            Integer[] partiallyOrdered = generateArrayOfDifferentType("PartiallyOrderedArray",n);
            Integer[] reverseOrdered = generateArrayOfDifferentType("ReverseOrderedArray",n);

            InsertionSort<Integer> insertionSort = new InsertionSort<>();

            Benchmark<Boolean> benchmarkTimer = new Benchmark_Timer<>("Passing random array", b -> insertionSort.sort(randomlyGeneratedArray));
            double timeTakenForRandomArray = benchmarkTimer.run(true, 800);
            System.out.println("The insertion sort time of random number array in nanosecond is "+timeTakenForRandomArray + " for array size of length "+n);

            Benchmark<Boolean> benchmarkTimer1 = new Benchmark_Timer<>("Passing ordered array", b -> insertionSort.sort(ordered));
            double timeTakenForOrderedArray = benchmarkTimer1.run(true, 800);
            System.out.println("The insertion sort time of ordered array in nanosecond is "+timeTakenForOrderedArray + " for array size of length "+n);

            Benchmark<Boolean> benchmarkTimer3 = new Benchmark_Timer<>("Passing ordered array", b -> insertionSort.sort(partiallyOrdered));
            double timeTakenForPartiallyOrderedArray = benchmarkTimer3.run(true, 800);
            System.out.println("The insertion sort time of partially ordered array in nanosecond is "+timeTakenForPartiallyOrderedArray + " for array size of length "+n);

            Benchmark<Boolean> benchmarkTimer4 = new Benchmark_Timer<>("Passing reverse ordered array", b -> insertionSort.sort(reverseOrdered));
            double timeTakenForReverseOrderedArray = benchmarkTimer4.run(true, 800);
            System.out.println("The insertion sort time of reverse ordered array in nanosecond is "+timeTakenForReverseOrderedArray + " for array size of length "+n);

            System.out.println();
        }
    }

    public static Integer[] generateArrayOfDifferentType(String typeOfArray, int n){

        Integer[] arr = new Integer[n];
        Random random = new Random();

        switch(typeOfArray){

            case "RandomArray" :
                for (int i = 0; i < n; i++)
                    arr[i] = random.nextInt();
                break;

            case "OrderedArray" :
                for (int i = 0; i < n; i++)
                    arr[i] = i;
                break;

            case "PartiallyOrderedArray" :
                for (int i = 0; i < n/2; i++)
                    arr[i] = i;

                for (int i = n/2; i < n; i++)
                    arr[i] = random.nextInt();
                break;

            case "ReverseOrderedArray" :
                for (int i = 0; i < n; i++)
                    arr[i] = n - i - 1;
                break;
        }

        return arr;

    }
}
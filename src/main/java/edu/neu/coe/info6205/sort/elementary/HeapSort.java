package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.InstrumentedHelper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

public class HeapSort<X extends Comparable<X>> extends SortWithHelper<X> {

    public HeapSort(Helper<X> helper) {
        super(helper);
    }

    @Override
    public void sort(X[] array, int from, int to) {
        if (array == null || array.length <= 1) return;

        // XXX construction phase
        buildMaxHeap(array);

        // XXX sort-down phase
        Helper<X> helper = getHelper();
        for (int i = array.length - 1; i >= 1; i--) {
            helper.swap(array, 0, i);
            maxHeap(array, i, 0);
        }
    }

    private void buildMaxHeap(X[] array) {
        int half = array.length / 2;
        for (int i = half; i >= 0; i--) maxHeap(array, array.length, i);
    }

    private void maxHeap(X[] array, int heapSize, int index) {
        Helper<X> helper = getHelper();
        final int left = index * 2 + 1;
        final int right = index * 2 + 2;
        int largest = index;
        if (left < heapSize && helper.compare(array, largest, left) < 0) largest = left;
        if (right < heapSize && helper.compare(array, largest, right) < 0) largest = right;
        if (index != largest) {
            helper.swap(array, index, largest);
            maxHeap(array, heapSize, largest);
        }
    }

    public static void main(String[] args) {
        int numberOfElements = 320000;
        InstrumentedHelper<Integer> helper = new InstrumentedHelper<>("HeapSort", Config.setupConfig("false", "0", "0", "", ""));

        HeapSort<Integer> heapSort = new HeapSort<>(helper);
        heapSort.init(numberOfElements);

        Integer[] xs = helper.random(Integer.class, r -> r.nextInt(200000));
        Benchmark<Boolean> benchmark = new Benchmark_Timer<>("Randomly generated array", b -> heapSort.sort(xs, 0, numberOfElements));
        double timeTakenToSort = benchmark.run(true, 20);
        heapSort.sort(xs, 0, numberOfElements);

        long numberOfCompares = helper.getCompares();
        long numberOfSwaps = helper.getSwaps();
        long numberOfHits = helper.getHits();

        System.out.println("Number of elements to sort using HeapSort is : "+numberOfElements);
        System.out.println("Time taken to sort array using HeapSort in ns is : "+timeTakenToSort);
        System.out.println("Number of compares : "+numberOfCompares);
        System.out.println("Number of swaps : "+numberOfSwaps);
        System.out.println("Number of hits : "+numberOfHits);

    }
}

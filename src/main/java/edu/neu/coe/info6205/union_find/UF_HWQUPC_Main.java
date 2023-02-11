package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UF_HWQUPC_Main {

    public static void main(String args[]) {

        for (int number = 100; number <= 500000; number *= 2) {
            System.out.println("For " + number + " objects (n) " + "the total number of pairs (m) are : " + count(number));
            System.out.println("1/2*number*log(number) is : " + (number * Math.log(number)) / 2);
        }
    }

    public static int count(int n) {
        int noOfConnections = 0;
        Random rand = new Random();
        UF_HWQUPC unionFind = new UF_HWQUPC(n, true);


        while (unionFind.components() != 1) {

            int p = rand.nextInt(n);
            int q = rand.nextInt(n);

            if (!unionFind.connected(p, q))
                unionFind.union(p, q);

            noOfConnections++;
        }
        return noOfConnections;
    }

}

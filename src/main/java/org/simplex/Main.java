package org.simplex;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] limit = {{"-1","1","2","2"}, {"1","1","1","3"}};
        String[] downF ={"-1","1","2","5"};
        CalculateSimlex calculateSimlex = new CalculateSimlex("", limit);
        for (int i = 0; i < limit.length; i++) {
            System.out.println(Arrays.toString(limit[i]));
        }
        System.out.println();
        System.out.println(Arrays.toString(downF));
        System.out.println();
        calculateSimlex.calculateSimplexTable(limit, downF);
        //calculateSimlex.calculateSimplexTable(calculateSimlex.getSimlexMethod().getLimitations(), calculateSimlex.getSimlexMethod().getDownFunction());
    }
}
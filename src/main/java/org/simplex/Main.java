package org.simplex;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FractionalNumber fractionalNumber = new FractionalNumber();
        String[][] limit = {{"3", "3", "4"}, {"1", "-5", "1"}, {"7", "8", "9"}};
        CalculateSimlex calculateSimlex = new CalculateSimlex("", limit);
        for (int i = 0; i < limit.length; i++) {
            System.out.println(Arrays.toString(limit[i]));
        }
        System.out.println();
        System.out.println(Arrays.toString(calculateSimlex.getSimlexMethod().getDownFunction()));
        calculateSimlex.calculateSimplexTable(calculateSimlex.getSimlexMethod().getLimitations(), calculateSimlex.getSimlexMethod().getDownFunction());
    }
}
package org.simplex;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FractionalNumber fractionalNumber = new FractionalNumber(1, 2, 1, 3);
        String[][] limit = {{"1", "2/4", "-3"}, {"-4", "+5", "-6"}, {"-7", "-8", "-9"}};
        CalculateSimlex calculateSimlex = new CalculateSimlex("", limit);
        for (int i = 0; i < limit.length; i++) {
            System.out.println(Arrays.toString(limit[i]));
        }
        System.out.println();
        System.out.println(Arrays.toString(calculateSimlex.getSimlexMethod().getDownFunction()));
    }
}
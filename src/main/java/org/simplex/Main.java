package org.simplex;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] limit = {{"8","2","-3","9", "9", "30"}, {"5","1","2","5", "6", "19"}, {"1","1","0","-3", "0","3"}};
        String[] downF ={"2","1","-1","3","-2"};
        SimlexMethod simlexMethod = new SimlexMethod();
        CalculateSimlex calculateSimlex = new CalculateSimlex(downF, limit);
        for (int i = 0; i < limit.length; i++) {
            System.out.println(Arrays.toString(limit[i]));
        }
        simlexMethod.setDownFunction(calculateSimlex.calcualteDownFunction(limit));
        System.out.println(Arrays.toString(simlexMethod.getDownFunction()));
        ArtificialBasisMethod artificialBasisMethod = new ArtificialBasisMethod(downF, limit);
        System.out.println(calculateSimlex.getSimlexMethod().getAnswer());
        //artificialBasisMethod.getSimplexTable(downF,limit);
//        System.out.println();
//        System.out.println(Arrays.toString(downF));
//        System.out.println();
//        calculateSimlex.calculateSimplexTable(limit, downF);
//        System.out.println(calculateSimlex.getSimlexMethod().getAnswer());
        //calculateSimlex.calculateSimplexTable(calculateSimlex.getSimlexMethod().getLimitations(), calculateSimlex.getSimlexMethod().getDownFunction());
    }
}
package org.simplex;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] limit = {{"-2","1","-1","-1","0","1"},{"1","-1","2","1","1","4"},{"-1","1","0","0","-1","4"}} ;
        String[] downF ={"2","-2","-1","-2","3"};
        String[] function = {"-2","-1","3","1"};

//        SimlexMethod simlexMethod = new SimlexMethod();
        CalculateSimlex calculateSimlex = new CalculateSimlex(function, limit);
//        for (int i = 0; i < limit.length; i++) {
//            System.out.println(Arrays.toString(limit[i]));
//        }
//        simlexMethod.setDownFunction(calculateSimlex.calcualteDownFunction(limit));
//        System.out.println(Arrays.toString(simlexMethod.getDownFunction()));
//        ArtificialBasisMethod artificialBasisMethod = new ArtificialBasisMethod(downF, limit, calculateSimlex);
//
//        System.out.println(calculateSimlex.getSimlexMethod().getAnswer());
        String[][] matrix = {{"1","2","5","-1","4"},{"1","-1","-1","2","1"}};

        int[] basis = {0,0,1,1};
        Gauss gauss = new Gauss(matrix, basis, calculateSimlex, function);
        System.out.println(gauss.getSimlexMethod().getAnswer());

        //artificialBasisMethod.getSimplexTable(downF,limit);
//        System.out.println();
//        System.out.println(Arrays.toString(downF));
//        System.out.println();
//        calculateSimlex.calculateSimplexTable(limit, downF);
//        System.out.println(calculateSimlex.getSimlexMethod().getAnswer());
        //calculateSimlex.calculateSimplexTable(calculateSimlex.getSimlexMethod().getLimitations(), calculateSimlex.getSimlexMethod().getDownFunction());
    }
}
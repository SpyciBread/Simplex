package org.simplex;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //String[][] limit = {{"3","-2","4","12"},{"3","4","-2","6"},{"-2","1","1","9"}} ;
        //String[] downF ={"2","-2","-1","-2","3"};
        FractionalNumber fractionalNumber = new FractionalNumber();
        System.out.println(fractionalNumber.calculate(-3,1,-2,-1, "/"));

        String[][] matrix ={{"1","3","1","2","5"},{"2","0","-1","1","1"}};
        String[] function ={"-1","-1","-1","-1"};
        int[] basis = {0,1,0,1};

//        SimlexMethod simlexMethod = new SimlexMethod();
        CalculateSimlex calculateSimlex = new CalculateSimlex(function, matrix);
//        for (int i = 0; i < limit.length; i++) {
//            System.out.println(Arrays.toString(limit[i]));
//        }
//        simlexMethod.setDownFunction(calculateSimlex.calcualteDownFunction(limit));
//        System.out.println(Arrays.toString(simlexMethod.getDownFunction()));
//        ArtificialBasisMethod artificialBasisMethod = new ArtificialBasisMethod(downF, limit, calculateSimlex);
//
//        System.out.println(calculateSimlex.getSimlexMethod().getAnswer());



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
package org.simplex;

import java.util.Arrays;

public class ArtificialBasisMethod {
    String[][] limit = {{"-1","1","2","2"}, {"1","1","1","3"}};
    String[] downF ={"-1","1","-2","5"};
    private CalculateSimlex calculateSimlex;
    private int nubmersOfBasicEl;
    private SimlexMethod simlexMethod;
    private String[] downFunction;
    private FractionalNumber fractionalNumber;
    public ArtificialBasisMethod(String[] function, String[][] limit){
        calculateSimlex = new CalculateSimlex(function, limit);
        fractionalNumber = new FractionalNumber();
        simlexMethod = calculateSimlex.getSimlexMethod();
        getSimplexTable(function, limit);
        nubmersOfBasicEl = limit.length;
        //вызов метода getSim...
    }
    public String[][] getSimplexTable(String[] function, String[][] limit){
        simlexMethod.setDownFunction(calculateSimlex.calcualteDownFunction(limit));
        simlexMethod.setSimplexTable(calculateSimlex.calculateSimplexTable(limit, calculateSimlex.calcualteDownFunction(limit)));
        if(!checkAnswer(simlexMethod.getSimplexTable(), simlexMethod.getBasis()).equals("Ok")){
            System.out.println(checkAnswer(simlexMethod.getSimplexTable(), simlexMethod.getBasis()));
            return simlexMethod.getSimplexTable();
        }
        System.out.println(checkAnswer(simlexMethod.getSimplexTable(), simlexMethod.getBasis()));
        //simlexMethod.setDownFunction(downFunction);
        simlexMethod.setSimplexTable(calculateSimlex.calculateSimplexTable(newSimplexTable(simlexMethod.getSimplexTable()), downFunction));
        return simlexMethod.getSimplexTable();
        //запись в нижнюю функцию
        // счет
    }

    public String checkAnswer(String[][] simplexTable, String[] basis){
        String[] startBasis = simlexMethod.getStartBasis();
        for (int i = 0; i < simplexTable[0].length - 2; i++){
            if(simplexTable[simplexTable.length - 1][i].charAt(0) == '-'){
                simlexMethod.setAnswer("Функция неограничена");
                return "Функция неограничена";
            }
        }
        for (int i = 0; i < simlexMethod.getStartBasis().length; i++)
            for(int j = 0; j < simlexMethod.getStartBasis().length; j++)
                if(basis[i].equals(startBasis[j])){
                    simlexMethod.setAnswer("Система несовместна");
                    return "Система несовместна";
                }
        //simlexMethod.setSimplexTable(Arrays.copyOfRange(simplexTable, 0, simplexTable.length - 1));
        simlexMethod.setSimplexTable(simplexTable);
       return "Ok";
    }

    public String[][] newSimplexTable(String[][] simplexTable){
        int col = 0;
        for(int i = 0; i < simplexTable[0].length; i++){
            if(simplexTable[simplexTable.length - 1][i].equals("0")){
                col++;
            }
        }
        String[] notBasis = simlexMethod.getNotBasis();
        String[] newNotBasis = new String[col - 1];
        String[] Basis = simlexMethod.getBasis();
        String[] function = simlexMethod.getFunction();
        int j = 0;
        String[][] newSimplexTable = new String[simplexTable.length][col];
        for(int i = 0; i < simplexTable[0].length; i++){
            if(simplexTable[simplexTable.length - 1][i].equals("0")){
                for(int k = 0; k < simplexTable.length; k++)
                    System.arraycopy(simplexTable[k], i, newSimplexTable[k], j, 1);
                j++;
            }
        }
        j = 0;
        for(int i = 0; i < simplexTable[0].length - 1; i++){
            if(simplexTable[simplexTable.length - 1][i].equals("0")){
                newNotBasis[j] = notBasis[i];
                j++;
            }
        }

        String[] downF = newSimplexTable[newSimplexTable.length - 1];
        for(int i = 0; i < newSimplexTable.length - 1; i++){
            for(int k = 0; k < newSimplexTable[0].length; k++){
                int indexX = Integer.parseInt(Basis[i].substring(1))-1;
                String tmpNumber;
                if(k != newSimplexTable[0].length - 1)
                    tmpNumber = operationWithTwoNumbers(replacingTheSign(newSimplexTable[i][k]), function[indexX], "*");
                else
                    tmpNumber = operationWithTwoNumbers(newSimplexTable[i][k], function[indexX], "*");
                downF[k] = operationWithTwoNumbers(downF[k], tmpNumber, "+");
            }
        }

        for(int i = 0; i < newNotBasis.length; i++){
            int indexX = Integer.parseInt(newNotBasis[i].substring(1))-1;
            downF[i] = operationWithTwoNumbers(downF[i], function[indexX], "+");
        }

        newSimplexTable = Arrays.copyOfRange(newSimplexTable, 0, newSimplexTable.length - 1);
        downFunction = downF;
        for (int i = 0; i < newSimplexTable.length; i++) {
            System.out.println(Arrays.toString(newSimplexTable[i]));
        }
        simlexMethod.setLimitations(newSimplexTable);
        return newSimplexTable;
    }

    private String replacingTheSign(String replacingNumber){

        if(replacingNumber.charAt(0) == '-'){
            replacingNumber = replacingNumber.substring(1);
        }
        else
        {
            if(replacingNumber.charAt(0) != '0')
                replacingNumber = "-" + replacingNumber;
        }
        return replacingNumber;
    }
    public String operationWithTwoNumbers(String first, String second, String operation){
        int a, b, c, d;
        simlexMethod.getFractionalNumber().convertNumber(first);
        a = simlexMethod.getFractionalNumber().getA();
        b = simlexMethod.getFractionalNumber().getB();
        simlexMethod.getFractionalNumber().convertNumber(second);
        c = simlexMethod.getFractionalNumber().getA();
        d = simlexMethod.getFractionalNumber().getB();
        return simlexMethod.getFractionalNumber().calculate(a,b,c,d, operation);
    }
}

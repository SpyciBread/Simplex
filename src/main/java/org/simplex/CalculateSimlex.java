package org.simplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculateSimlex {
    private SimlexMethod simlexMethod = new SimlexMethod();


    public CalculateSimlex(String function, String[][] limitations){
        simlexMethod.setFunction(function);
        simlexMethod.setLimitations(limitations);
        simlexMethod.setDownFunction(calcualteDownFunction(limitations));
    }

    public SimlexMethod getSimlexMethod() {
        return simlexMethod;
    }

    public String[] calcualteDownFunction(String[][] limitations){
        int columnSize = limitations[0].length;
        int rowSize = limitations.length;
        String[] downFunction = new String[columnSize];
        StringBuilder calucateString = new StringBuilder();

        for(int i = 0; i < columnSize; i++){
            for(int j = 0; j < rowSize; j++){
                if(limitations[j][i].charAt(0) != '-' && j != 0)
                    calucateString.append("+").append(limitations[j][i]);
                else
                    calucateString.append(limitations[j][i]);
            }
            downFunction[i] = simlexMethod.getFractionalNumber().convertString(calucateString.toString());
            calucateString = new StringBuilder();
        }


        return replacingTheSign(downFunction);
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

    public String[][] transitionSimplexTable(String[][] simplexTable, int row, int col){
        simplexTable[row][col] = operationWithTwoNumbers("1", simplexTable[row][col], "/");
        String[] oldNumbers = new String[simplexTable.length];

        for (int i = 0; i < simplexTable[row].length; i++){
            if(i != col){
                simplexTable[row][i] = operationWithTwoNumbers(simplexTable[row][i], simplexTable[row][col], "*");
            }
        }

        for (int i = 0; i < simplexTable.length; i++){
            oldNumbers[i] = simplexTable[i][col];
            if(i != row){
                simplexTable[i][col] = operationWithTwoNumbers(simplexTable[i][col], simplexTable[row][col], "*");
                simplexTable[i][col] = operationWithTwoNumbers(simplexTable[i][col], "-1", "*");
            }
        }
        String tmp;
        for(int i = 0; i < simplexTable.length; i++){
            if(i != row)
                for (int j = 0; j < simplexTable[row].length; j++){
                    if(j != col){
                        tmp = operationWithTwoNumbers(oldNumbers[i], simplexTable[row][j], "*");
                        simplexTable[i][j] = operationWithTwoNumbers(simplexTable[i][j], tmp, "-");
                    }
                }
        }
        for (int i = 0; i < simplexTable.length; i++) {
            System.out.println(Arrays.toString(simplexTable[i]));
        }
        return simplexTable;
    }

    public String[][] calculateSimplexTable(String[][] limit, String[] downFunction){
        int numRows = limit.length;
        int numCols = limit[0].length;

        String[][] simlexTable = new String[numRows + 1][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                simlexTable[i][j] = limit[i][j];
            }
        }
        simlexTable[numRows] = downFunction;
        String[][] wrongSolution = {{"0"},{"0"}};
        String[][] noSolution = {{"n"},{"n"}};
        while (true){
//            String minEl = findMinEl(Arrays.copyOfRange(downFunction, 0, downFunction.length - 1));
//
//            int indexOfMinEl = 0;
//            if(minEl.equals("Calculated")){
//                return simlexTable;
//            }
//
//            for(int i = 0; i < downFunction.length - 1; i++) {
//                if(minEl.equals(downFunction[i]) && simlexMethod.getNegativeElementIndex() == i){
//                    indexOfMinEl = i;
//                    break;
//                }
//            }

            if(Arrays.deepEquals(helpCalculateSimplexTable(simlexTable, downFunction), wrongSolution)){
                while (true){
                    if(Arrays.deepEquals(helpCalculateSimplexTable(simlexTable, downFunction), wrongSolution)){
                        helpCalculateSimplexTable(simlexTable, downFunction);
                    }
                    else {
                        if (Arrays.deepEquals(helpCalculateSimplexTable(simlexTable, downFunction), noSolution)){
                            return simlexTable;
                        }
                        else break;
                    }
                }
            }
            return helpCalculateSimplexTable(simlexTable, downFunction);
        }
    }
    public String[][] helpCalculateSimplexTable(String[][] simlexTable, String[] downFunction){
        String minEl = findMinEl(Arrays.copyOfRange(downFunction, 0, downFunction.length - 1));

        int indexOfMinEl = 0;
        if(minEl.equals("Calculated")){
            return simlexTable;
        }

        if(minEl.equals("Функция неограничена")){
            return new String[][]{{"n"}, {"n"}};
        }

        for(int i = 0; i < downFunction.length - 1; i++) {
            if(minEl.equals(downFunction[i]) && simlexMethod.getNegativeElementIndex() == i){
                indexOfMinEl = i;
                break;
            }
        }
        return findReferenceE(simlexTable, indexOfMinEl);
    }
    private String[][] findReferenceE(String[][] simlexTable, int indexOfMinEl) {
        int numRows = simlexTable.length;
        List<String> potentialReferenceElList = new ArrayList<>();
        for(int i = 0; i < numRows - 1; i++){
            String potentialReference;
            if(simlexTable[i][indexOfMinEl].charAt(0) != '-' && simlexTable[i][indexOfMinEl].charAt(0) != '0'){
                potentialReference = operationWithTwoNumbers(simlexTable[i][simlexTable[0].length - 1], simlexTable[i][indexOfMinEl], "/");
                potentialReferenceElList.add(potentialReference);
            }
        }
        if(potentialReferenceElList.size() == 0){
            //поиск в другом отрицательном элементе
            simlexMethod.getNegativeElements().add(indexOfMinEl);
            return new String[][]{{"0"}, {"0"}};
        }

        String[] potentialReferenceArray = potentialReferenceElList.toArray(new String[potentialReferenceElList.size()]);
        String referenceEl = findReferenceElementInRow(potentialReferenceArray);
        System.out.println(referenceEl);


        for(int i = 0; i < numRows - 1; i++) {
            String el = operationWithTwoNumbers(simlexTable[i][simlexTable[0].length - 1], simlexTable[i][indexOfMinEl], "/");
            if(referenceEl.equals(el)){
                System.out.println(simlexTable[i][indexOfMinEl]);
                return transitionSimplexTable(simlexTable, i, indexOfMinEl);
            }
        }

        return simlexTable;
    }

    private String[] replacingTheSign(String[] replacingArray){
        for (int i = 0; i < replacingArray.length; i++){
            if(replacingArray[i].charAt(0) == '-'){
                replacingArray[i] = replacingArray[i].substring(1);
            }
            else
            {
                if(replacingArray[i].charAt(0) != '0')
                    replacingArray[i] = "-" + replacingArray[i];
            }
        }
        return replacingArray;
    }
    public String findReferenceElementInRow(String[] list){
        if(list.length == 1){
            return list[0];
        }
        int a, b, c, d, minNumber, znamenatel;
        simlexMethod.getFractionalNumber().convertNumber(list[0]);
        a = simlexMethod.getFractionalNumber().getA();
        b = simlexMethod.getFractionalNumber().getB();
        simlexMethod.getFractionalNumber().convertNumber(list[1]);
        c = simlexMethod.getFractionalNumber().getA();
        d = simlexMethod.getFractionalNumber().getB();
        znamenatel = simlexMethod.getFractionalNumber().findLCM(b, d);
        a = a * (simlexMethod.getFractionalNumber().findLCM(b,d)/ b);
        c = c * (simlexMethod.getFractionalNumber().findLCM(b,d) / d);
        minNumber = Math.min(a,c);
        if(list.length > 2){
            for(int i = 2; i < list.length; i++){
                String min = simlexMethod.getFractionalNumber().toNormalNumber(minNumber, znamenatel);

                simlexMethod.getFractionalNumber().convertNumber(min);
                a = simlexMethod.getFractionalNumber().getA();
                b = simlexMethod.getFractionalNumber().getB();

                simlexMethod.getFractionalNumber().convertNumber(list[i]);
                c = simlexMethod.getFractionalNumber().getA();
                d = simlexMethod.getFractionalNumber().getB();

                znamenatel = simlexMethod.getFractionalNumber().findLCM(b, d);
                a = a * (simlexMethod.getFractionalNumber().findLCM(b,d)/ b);
                c = c * (simlexMethod.getFractionalNumber().findLCM(b,d) / d);
                minNumber = Math.min(a,c);
            }
        }

        return  simlexMethod.getFractionalNumber().toNormalNumber(minNumber, znamenatel);
    }
    public String findMinEl(String[] list){
        List<String> negativeEl = new ArrayList<>();
        List<Integer> negativeElIndex = new ArrayList<>();
        boolean isWrongEl = false;
        int kolvoMinEl = 0;
        String minNegativEl;
        for (int i = 0; i < list.length; i++) {
            if (list[i].charAt(0) == '-'){
                kolvoMinEl++;
                for(int j: simlexMethod.getNegativeElements())
                    if(i == j){
                        isWrongEl = true;
                        break;
                    }
                if(!isWrongEl){
                    negativeEl.add(list[i]);
                    negativeElIndex.add(i);
                }
            }
        }
        if(simlexMethod.getNegativeElements().size() == kolvoMinEl){
            return "Функция неограничена";
        }

        if(negativeEl.size() == 0){
            return "Calculated";
        }

        //simlexMethod.setNegativeElements(negativeEl.toArray(new Integer[negativeEl.size()]));

        if(negativeEl.size() == 1){
            simlexMethod.setNegativeElementIndex(negativeElIndex.get(0));
            return negativeEl.get(0);
        }

        int a, b, c, d, maxNegativNumber, znamenatel;

        simlexMethod.getFractionalNumber().convertNumber(negativeEl.get(0));
        a = simlexMethod.getFractionalNumber().getA();
        b = simlexMethod.getFractionalNumber().getB();
        simlexMethod.getFractionalNumber().convertNumber(negativeEl.get(1));
        c = simlexMethod.getFractionalNumber().getA();
        d = simlexMethod.getFractionalNumber().getB();
        znamenatel = simlexMethod.getFractionalNumber().findLCM(b, d);
        a = a * (simlexMethod.getFractionalNumber().findLCM(b,d)/ b);
        c = c * (simlexMethod.getFractionalNumber().findLCM(b,d) / d);
        maxNegativNumber = Math.min(a,c);
        if(maxNegativNumber == a)
            simlexMethod.setNegativeElementIndex(negativeElIndex.get(0));
        else
            simlexMethod.setNegativeElementIndex(negativeElIndex.get(1));

        if(negativeEl.size() > 2){
            for(int i = 2; i < negativeEl.size(); i++){
                minNegativEl = simlexMethod.getFractionalNumber().toNormalNumber(maxNegativNumber, znamenatel);

                simlexMethod.getFractionalNumber().convertNumber(minNegativEl);
                a = simlexMethod.getFractionalNumber().getA();
                b = simlexMethod.getFractionalNumber().getB();

                simlexMethod.getFractionalNumber().convertNumber(negativeEl.get(i));
                c = simlexMethod.getFractionalNumber().getA();
                d = simlexMethod.getFractionalNumber().getB();

                znamenatel = simlexMethod.getFractionalNumber().findLCM(b, d);
                a = a * (simlexMethod.getFractionalNumber().findLCM(b,d)/ b);
                c = c * (simlexMethod.getFractionalNumber().findLCM(b,d) / d);

                maxNegativNumber = Math.min(a,c);
                if(maxNegativNumber == a)
                    simlexMethod.setNegativeElementIndex(negativeElIndex.get(i-1));
                else
                    simlexMethod.setNegativeElementIndex(negativeElIndex.get(i));
            }
        }

        return simlexMethod.getFractionalNumber().toNormalNumber(maxNegativNumber, znamenatel);
    }
}

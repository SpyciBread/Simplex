package org.simplex;

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
        String[] downFunction = new String[limitations.length];
        StringBuilder calucateString = new StringBuilder();

        for(int i = 0; i < columnSize; i++){
            for(int j = 0; j < rowSize; j++){
                calucateString.append(limitations[j][i]);
            }
            downFunction[i] = simlexMethod.getFractionalNumber().convertNumber(calucateString.toString());
            calucateString = new StringBuilder();
        }


        return replacingTheSign(downFunction);
    }

    private String[] replacingTheSign(String[] replacingArray){
        for (int i = 0; i < replacingArray.length; i++){
            if(replacingArray[i].charAt(0) == '-'){
                replacingArray[i] = replacingArray[i].substring(1);
            }
            else
            {
                replacingArray[i] = "-" + replacingArray[i];
            }
        }
        return replacingArray;
    }
}

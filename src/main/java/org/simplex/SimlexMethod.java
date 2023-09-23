package org.simplex;

public class SimlexMethod {
    private FractionalNumber fractionalNumber = new FractionalNumber();
    private String[][] limitations;

    private String function;
    private String[] downFunction;
    private int min;

    public FractionalNumber getFractionalNumber() {
        return fractionalNumber;
    }

    public void setDownFunction(String[] downFunction) {
        this.downFunction = downFunction;
    }

    public void setLimitations(String[][] limitations) {
        this.limitations = limitations;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String[][] getLimitations() {
        return limitations;
    }

    public String getFunction() {
        return function;
    }

    public String[] getDownFunction() {
        return downFunction;
    }

    public int isMin() {
        return min;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}

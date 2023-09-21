package org.simplex;

public class SimlexMethod {
    private FractionalNumber fractionalNumber = new FractionalNumber(1,1,1,1);
    private String[][] limitations;

    private String function;
    private String[] downFunction;
    private boolean isMin;

    public FractionalNumber getFractionalNumber() {
        return fractionalNumber;
    }

    public void setDownFunction(String[] downFunction) {
        this.downFunction = downFunction;
    }

    public void setLimitations(String[][] limitations) {
        this.limitations = limitations;
    }

    public void setMin(boolean min) {
        isMin = min;
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

    public boolean isMin() {
        return isMin;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}

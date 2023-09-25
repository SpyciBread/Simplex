package org.simplex;

import java.util.ArrayList;
import java.util.List;

public class SimlexMethod {
    private FractionalNumber fractionalNumber = new FractionalNumber();
    private String[][] limitations;

    private String function;
    private String[] downFunction;
    private List<Integer> negativeElements = new ArrayList<>();
    private int quantityLimit;
    private int negativeElementIndex;
    private int min;

    public FractionalNumber getFractionalNumber() {
        return fractionalNumber;
    }

    public int getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(int quantityLimit) {
        this.quantityLimit = quantityLimit;
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

    public int getNegativeElementIndex() {
        return negativeElementIndex;
    }

    public void setNegativeElementIndex(int negativeElementIndex) {
        this.negativeElementIndex = negativeElementIndex;
    }

    public List<Integer> getNegativeElements() {
        return negativeElements;
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

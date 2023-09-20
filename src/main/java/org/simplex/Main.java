package org.simplex;

public class Main {
    public static void main(String[] args) {
        FractionalNumber fractionalNumber = new FractionalNumber(1, 2, 1, 3);
        System.out.println(fractionalNumber.convertNumber("32/3+1/2-2/5"));
    }
}
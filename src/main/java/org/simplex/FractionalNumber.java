package org.simplex;

import java.util.ArrayList;
import java.util.List;

public class FractionalNumber {
    private int a, b, c, d; // a/b c/d
    private String operation;

    private List<String> stringToArray(String string){
        List<String> result = new ArrayList<>();

        StringBuilder current = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                if (current.length() > 0) {
                    result.add(current.toString());
                    current = new StringBuilder();
                }
                result.add(String.valueOf(c));
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) {
            result.add(current.toString());
        }
        return result;
    }

    public void convertNumber(String number){
        if(number.contains("/")){
            String[] firstNumber = number.split("/");
            setA(Integer.parseInt(firstNumber[0]));
            setB(Integer.parseInt(firstNumber[1]));
        }
        else{
            setA(Integer.parseInt(number));
            setB(1);
        }
        //return toNormalNumber(getA(), getB());
    }

    public String convertString(String mathematicalExpression){
        String strWithoutSpaces = mathematicalExpression.replaceAll("\\s+", "");

        List<String> result = stringToArray(strWithoutSpaces);

        StringBuilder finalResult = new StringBuilder();
        String tmp;
        int shift = 0;
        if(result.get(0).equals("-")){
            shift = 1;
            tmp = "-";
            tmp += result.get(shift);
        }
        else
            tmp = result.get(shift);
        for (int i = 2 + shift; i < result.size(); i += 2) {
            if (!result.get(i).isEmpty()) {
                if(tmp.contains("/")){
                    String[] firstNumber = tmp.split("/");
                    setA(Integer.parseInt(firstNumber[0]));
                    setB(Integer.parseInt(firstNumber[1]));
                }
                else{
                    setA(Integer.parseInt(tmp));
                    setB(1);
                }
                setOperation(result.get(i-1));
                if(result.get(i).contains("/")){
                    String[] firstNumber = result.get(i).split("/");
                    setC(Integer.parseInt(firstNumber[0]));
                    setD(Integer.parseInt(firstNumber[1]));
                }
                else{
                    setC(Integer.parseInt(result.get(i)));
                    setD(1);
                }
                tmp = (calculate(a, b, c, d, operation));

            }

        }
        finalResult.append(tmp);
        return finalResult.toString();
    }

    public String calculate(int a, int b, int c, int d, String operation){
        String result = "";
        int chislitel;
        int znamenatel;
        switch (operation){
            case "+" :
                chislitel = a * (findLCM(b,d)/ b) + c * (findLCM(b,d) / d);
                znamenatel = findLCM(b,d);
                result = toNormalNumber(chislitel, znamenatel);
                break;
            case "-" :
                chislitel = a * (findLCM(b,d)/ b) - c * (findLCM(b,d) / d);
                znamenatel = findLCM(b,d);
                result = toNormalNumber(chislitel, znamenatel);
                break;
            case "*" :
                chislitel =a*c;
                znamenatel = b*d;
                result = toNormalNumber(chislitel, znamenatel);
                break;
            case "/" :
                chislitel = a*d;
                znamenatel = b*c;
                result = toNormalNumber(chislitel, znamenatel);
                break;
        }
        return result;
    }

    public String toNormalNumber(int chislitel, int znamenatel){
        int gcd = gcd(chislitel, znamenatel);
        while (Math.abs(gcd) != 1){
            chislitel /= Math.abs(gcd);
            znamenatel /= Math.abs(gcd);
            gcd = gcd(chislitel, znamenatel);
        }
        if(znamenatel == 1)
            return "" + chislitel;
        if(chislitel == 0)
            return "0";

        return chislitel + "/" + Math.abs(znamenatel);
    }

    public int findLCM(int num1, int num2) {
        num1 = Math.abs(num1);
        num2 = Math.abs(num2);
        int max = Math.max(num1, num2);
        int min = Math.min(num1, num2);
        int lcm = max;
        while (lcm % min != 0) {
            lcm += max;
        }
        return lcm;
    }
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}

package org.simplex;

import java.util.ArrayList;
import java.util.List;

public class FractionalNumber {
    private int a, b, c, d; // a/b c/d

    public FractionalNumber(int a, int b, int c, int d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public String convertNumber(String mathematicalExpression){
        String strWithoutSpaces = mathematicalExpression.replaceAll("\\s+", "");

        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < strWithoutSpaces.length(); i++) {
            char c = strWithoutSpaces.charAt(i);

            if (c == '+' || c == '-') {
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

        StringBuilder finalResult = new StringBuilder();
        String tmp = result.get(0);
        for (int i = 2; i < result.size(); i += 2) {
            if (!result.get(i).isEmpty()) {
                if(tmp.contains("/")){
                    String[] firstNumber = tmp.split("/");
                    a = Integer.parseInt(firstNumber[0]);
                    b = Integer.parseInt(firstNumber[1]);
                }
                else{
                    a = Integer.parseInt(tmp);
                    b = 1;
                }
                String operation = result.get(i-1);
                if(result.get(i).contains("/")){
                    String[] firstNumber = result.get(i).split("/");
                    c = Integer.parseInt(firstNumber[0]);
                    d = Integer.parseInt(firstNumber[1]);
                }
                else{
                    c = Integer.parseInt(result.get(i));
                    d = 1;
                }
                tmp = (calculate(a, b, c, d, operation));
            }

        }
        finalResult.append(tmp);
        return finalResult.toString();
    }

    public String calculate(int a, int b, int c, int d, String operation){
        String result = "";
        switch (operation){
            case "+" :
                result = a * (findLCM(b,d)/ b) + c * (findLCM(b,d) / d) + "/" + findLCM(b,d);
                break;
            case "-" :
                result = a * (findLCM(b,d)/ b) - c * (findLCM(b,d) / d) + "/" + findLCM(b,d);
                break;
            case "*" :
                result = a*c + "/" + b*d;
                break;
            case "/" :
                result = a*d + "/" + b*c;
                break;
        }
        return result;
    }

    public int findLCM(int num1, int num2) {
        int max = Math.max(num1, num2);
        int min = Math.min(num1, num2);
        int lcm = max;
        while (lcm % min != 0) {
            lcm += max;
        }
        return lcm;
    }
}

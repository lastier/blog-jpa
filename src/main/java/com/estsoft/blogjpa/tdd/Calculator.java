package com.estsoft.blogjpa.tdd;

public class Calculator {
    public static void main(String[] args) {
        //계산기 - 덧셈
        Calculator calculator = new Calculator();
        boolean result = calculator.sum(1, 2) == 3; //true
        System.out.println("SUM : "+result);

    }

    private int sum(int i, int j){
        return i+j;
    }

}

package com.estsoft.blogjpa.tdd;

public class Account {
    private int balance;

    public Account(int seed) {
        balance = seed;
    }

    public int getBalance(){
        return balance;
    }

    public void deposit(int money) {
        balance += money;
    }

    public void withdraw(int money){
        balance -= money;
    }
}

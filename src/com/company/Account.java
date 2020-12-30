package com.company;

//CLASS FOR ACCOUNT

public class Account {
    private long balance;

    public Account(int bal) {
        this.balance = bal;
    }

    public long getBalance() {
        return this.balance;
    }

    public void withdraw(long amt) {
        if (amt < this.balance) {
            this.balance -= amt;
        } else {
            System.out.println("You do not have enough to withdraw $"+amt);
        }
    }

    public void deposit(long amt) {
        this.balance += amt;
    }
}

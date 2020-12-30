package com.company;

//CLASS FOR USER, WHICH HAS TWO ACCOUNT OBJS

public class User {
    private long card;
    private int pin;
    private Account checking;
    private Account savings;

    public User(int c, int p, int c_bal, int s_bal) {
        this.card = c;
        this.pin = p;
        this.checking = new Account(c_bal);
        this.savings = new Account(s_bal);
    }

    public Account getChecking() {
        return this.checking;
    }

    public Account getSavings() {
        return this.savings;
    }

    public long getCard() {
        return this.card;
    }

    // normally would get verification from bank api, but here we check equality
    public boolean verify (int p) {
        return p == this.pin;
    }
}

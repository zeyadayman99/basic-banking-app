package com.example.bankingsystem_sparks;

public class Users_transactions {
    String from_user;
    String to_user;
    long amount;

    public Users_transactions(String from_user, String to_user, long amount) {
        this.from_user = from_user;
        this.to_user = to_user;
        this.amount = amount;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}

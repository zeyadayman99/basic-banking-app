package com.example.bankingsystem_sparks;

import java.io.Serializable;

public class Users implements Serializable {
    private int user_id;
    private long user_balance;
    private String user_name;
    private String user_job;
    private String user_email;

    public Users(int user_id, long user_balance, String user_name, String user_job, String user_email) {
        this.user_id = user_id;
        this.user_balance = user_balance;
        this.user_name = user_name;
        this.user_job = user_job;
        this.user_email = user_email;
    }

    public Users(long user_balance, String user_name, String user_job, String user_email) {
        this.user_balance = user_balance;
        this.user_name = user_name;
        this.user_job = user_job;
        this.user_email = user_email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(long user_balance) {
        this.user_balance = user_balance;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_job() {
        return user_job;
    }

    public void setUser_job(String user_job) {
        this.user_job = user_job;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public String toString(){
        return getUser_name();
    }

    public boolean withdraw_balance(long balance){
        setUser_balance(getUser_balance() - balance);
        return true;
    }
    public boolean transfer_balance(long balance){
        setUser_balance(getUser_balance() + balance);
        return true;
    }
}

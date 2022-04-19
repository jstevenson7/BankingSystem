package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;

public class Checking {
    @CsvBindByName
    private String accountNumber;
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private int accountType;
    @CsvBindByName
    private String customerID;
    public Checking(String accountNumber, double balance, int accountType, String customerID) {
        setAccountNumber(accountNumber);
        setBalance(balance);
        setAccountType(accountType);
        setCustomerID(customerID);
    }
    public Checking() {
        // No arg constructor for opencsv
    }

    // Getters
    public String getAccountNumber() {return accountNumber;}
    public double getBalance() {return balance;}
    public int getAccountType() {return accountType;}
    public String getCustomerID() {return customerID;}
    // Setters
    public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
    public void setBalance(double balance) {this.balance = balance;}
    public void setAccountType(int accountType) {this.accountType = accountType;}
    public void setCustomerID(String customerID) {this.customerID = customerID;}
}

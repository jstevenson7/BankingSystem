package edu.missouriwestern.csc406.bankingsystem;

public class Transaction {
    private String transactionNum;
    private String SSN;
    private String accountType;
    private String accountNum;
    private double amount;
    private String date;
    private String checkNum;
    // Constructors
    public Transaction() {
        // no arg constructor for opencsv
    }
    public Transaction(String transactionNum, String SSN, String accountType, String accountNum,
                       double amount, String date, String checkNum) {
        setTransactionNum(transactionNum);
        setSSN(SSN);
        setAccountType(accountType);
        setAccountNum(accountNum);
        setAmount(amount);
        setDate(date);
        setCheckNum(checkNum);
    }
    // Getters
    public String getTransactionNum() {return transactionNum;}
    public String getSSN() {return SSN;}
    public String getAccountType() {return accountType;}
    public String getAccountNum() {return accountNum;}
    public double getAmount() {return amount;}
    public String getDate() {return date;}
    public String getCheckNum() {return checkNum;}
    // Setters
    public void setTransactionNum(String transactionNum) {this.transactionNum = transactionNum;}
    public void setSSN(String SSN) {this.SSN = SSN;}
    public void setAccountType(String accountType) {this.accountType = accountType;}
    public void setAccountNum(String accountNum) {this.accountNum = accountNum;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setDate(String date) {this.date = date;}
    public void setCheckNum(String checkNum) {this.checkNum=checkNum;}
}

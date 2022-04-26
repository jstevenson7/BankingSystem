package edu.missouriwestern.csc406.bankingsystem;
public class Check {
    // Data Fields
    private String checkNum;
    private double amount;
    private String date;
    private String recipient;
    private String description;
    private String accountNumber;
    private String routingNumber;
    // Constructors
    public Check(String checkNum, double amount, String date, String recipient, String description, String accountNumber, String routingNumber) {
        setCheckNum(checkNum);
        setAmount(amount);
        setDate(date);
        setRecipient(recipient);
        setDescription(description);
        setAccountNumber(accountNumber);
        setRoutingNumber(routingNumber);
    }
    public Check() {
        // No arg constructor for opencsv
    }
    // Getters
    public String getCheckNum() {return checkNum;}
    public double getAmount() {return amount;}
    public String getDate() {return date;}
    public String getRecipient() {return recipient;}
    public String getDescription() {return description;}
    public String getAccountNumber() {return accountNumber;}
    public String getRoutingNumber() {return routingNumber;}
    // Setters
    public void setCheckNum(String checkNum) {this.checkNum = checkNum;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setDate(String date) {this.date = date;}
    public void setRecipient(String recipient) {this.recipient = recipient;}
    public void setDescription(String description) {this.description = description;}
    public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
    public void setRoutingNumber(String routingNumber) {this.routingNumber = routingNumber;}

    @Override
    public String toString() {
        return ""+ checkNum +","+amount+","+date+","+recipient+","+description+","+accountNumber+","+routingNumber;
    }
}

package edu.missouriwestern.csc406.bankingsystem;

public class Check {
    // Data Fields
    private String checkID;
    private double amount;
    private String date;
    private String recipient;
    private String description;
    // Constructors
    public Check(String checkID, double amount, String date, String recipient, String description) {
        setCheckID(checkID);
        setAmount(amount);
        setDate(date);
        setRecipient(recipient);
        setDescription(description);
    }
    public Check() {
        // No arg constructor for opencsv
    }
    // Getters
    public String getCheckID() {return checkID;}
    public double getAmount() {return amount;}
    public String getDate() {return date;}
    public String getRecipient() {return recipient;}
    public String getDescription() {return description;}
    // Setters
    public void setCheckID(String checkID) {this.checkID = checkID;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setDate(String date) {this.date = date;}
    public void setRecipient(String recipient) {this.recipient = recipient;}
    public void setDescription(String description) {this.description = description;}
}

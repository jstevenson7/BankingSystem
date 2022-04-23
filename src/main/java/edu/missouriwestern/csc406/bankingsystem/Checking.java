package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Checking {
    @CsvBindByName
    private String accountNumber;
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private int accountType;
    @CsvBindByName
    private String customerID;

    private ArrayList<Check> depositchecklist = new ArrayList<Check>();
    private ArrayList<Check> withdrawchecklist = new ArrayList<Check>();

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

    public void testaccount()
    {
        if (balance >= 1000.00)
        {
            accountType = 1;
        }
        else if (balance < 1000.00)
        {
            accountType = 0;
        }
    }

    public void deposit(Double amount)
    {
        testaccount();
        DecimalFormat f = new DecimalFormat("##.00");
        setBalance(Double.parseDouble(f.format((balance += amount))));

        if (accountType==0)
        {
            setBalance(Double.parseDouble(f.format((balance -= .5))));
        }
        testaccount();
    }

    public void withdraw(Double amount)
    {
        // check accountType
        testaccount();

        DecimalFormat f = new DecimalFormat("##.00");
        setBalance(Double.parseDouble(f.format((balance -= amount))));

        if (accountType==0)
        {
            setBalance(Double.parseDouble(f.format((balance -= .5))));
        }
        testaccount();

    }
    public void writecheck (Check check)
    {
        double withdrawamt = check.getAmount();
        balance -= withdrawamt;
        withdrawchecklist.add(check);
        testaccount();
        if (accountType==0)
        {
            balance-=.5;
        }
    }

    public void transfer (double transferamt, Account account)
    {
        account.setBalance(account.getBalance()+transferamt);
        balance -= transferamt;
        if (accountType==0)
        {
            balance-=.75;
        }

    }
}

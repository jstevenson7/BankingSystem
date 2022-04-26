package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;
import java.text.DecimalFormat;

public class Checking {

    @CsvBindByName
    private String accountNumber;
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private int accountType;
    @CsvBindByName
    private String SSN;

    //int hasOverdraft if == 1 has, if == 0 doesn't have


    public Checking(String accountNumber, double balance, int accountType, String SSN) {
        setAccountNumber(accountNumber);
        setBalance(balance);
        setAccountType(accountType);
        setSSN(SSN);
    }

    public Checking() {
        // No arg constructor for opencsv
    }

    // Getters
    public String getAccountNumber() {return accountNumber;}
    public double getBalance() {return balance;}
    public int getAccountType() {return accountType;}
    public String getSSN() {return SSN;}
    // Setters
    public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
    public void setBalance(double balance) {this.balance = balance;}
    public void setAccountType(int accountType) {this.accountType = accountType;}
    public void setSSN(String SSN) {this.SSN = SSN;}

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

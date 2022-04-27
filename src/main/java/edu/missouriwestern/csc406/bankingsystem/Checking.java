package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;
import java.text.DecimalFormat;

public class Checking {

    @CsvBindByName
    private String checkingAcctNum;
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private int accountType;
    @CsvBindByName
    private String SSN;
    @CsvBindByName
    private String overdraftAccountNumber;


    public Checking(String checkingAcctNum, double balance, int accountType, String SSN) {
        setCheckingAcctNum(checkingAcctNum);
        setBalance(balance);
        setAccountType(accountType);
        setSSN(SSN);
        setOverdraftAccountNumber("0");
    }

    public Checking() {
        // No arg constructor for opencsv
    }

    // Getters
    public String getCheckingAcctNum() {return checkingAcctNum;}
    public double getBalance() {return balance;}
    public int getAccountType() {return accountType;}
    public String getSSN() {return SSN;}
    public String getOverdraftAccountNumber() {return overdraftAccountNumber;}
    // Setters
    public void setCheckingAcctNum(String checkingAcctNum) {this.checkingAcctNum = checkingAcctNum;}
    public void setBalance(double balance) {this.balance = balance;}
    public void setAccountType(int accountType) {this.accountType = accountType;}
    public void setSSN(String SSN) {this.SSN = SSN;}
    public void setOverdraftAccountNumber(String overdraftAccountNumber) {this.overdraftAccountNumber=overdraftAccountNumber;}

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
        // make sure
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
    public void deduct(Double amount) {
        DecimalFormat f = new DecimalFormat("##.00");
        setBalance(Double.parseDouble(f.format((balance -= amount))));
    }
    public void add(Double amount) {
        DecimalFormat f = new DecimalFormat("##.00");
        setBalance(Double.parseDouble(f.format((balance += amount))));
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

package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Savings {

    @CsvBindByName
    private String savingsID; //account num
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private double interestRate;
    @CsvBindByName
    private String startDate;
    @CsvBindByName
    private String customerID;

    private String currentDate;

    private ArrayList<Check> depositchecklist = new ArrayList<Check>();
    private ArrayList<Check> withdrawchecklist = new ArrayList<Check>();

    public Savings()
    {

    }

    public Savings(String savingsID, double balance, double interestRate, String startDate, String customerID) throws ParseException {
        this.savingsID = savingsID;
        this.balance = balance;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.customerID = customerID;
        this.currentDate = "04-20-2022";
        dailyInterest();

    }

    public void dailyInterest () throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date startingdate = sdf.parse(startDate);
        Date currentdate = sdf.parse(currentDate);

        long diffInTime = currentdate.getTime() - startingdate.getTime();
        long diffInDay = (diffInTime/(1000*60*60*24));
        int count = (int) diffInDay;

        while (count > 0)
        {
            balance = balance + balance*(interestRate/365);
            count--;
        }

    }



    public void deposit(Double amount)
    {
        balance+= amount;
    }

    public void withdraw(Double amount)
    {
        balance-= amount;
    }

    public void writecheck (Check check)
    {
        if (check.getAmount() <= balance)
        {
            balance-=check.getAmount();
            withdrawchecklist.add(check);
        }
        else if(check.getAmount() >= balance)
        {
            System.out.println("Account balance exceeded, cannot process check");
        }


    }
    public void depositcheck(Check check)
    {
        balance+=check.getAmount();
        depositchecklist.add(check);
    }

    public String getSavingsID() {
        return savingsID;
    }

    public void setSavingsID(String savingsID) {
        this.savingsID = savingsID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public ArrayList<Check> getDepositchecklist() {
        return depositchecklist;
    }

    public void setDepositchecklist(ArrayList<Check> depositchecklist) {
        this.depositchecklist = depositchecklist;
    }

    public ArrayList<Check> getWithdrawchecklist() {
        return withdrawchecklist;
    }

    public void setWithdrawchecklist(ArrayList<Check> withdrawchecklist) {
        this.withdrawchecklist = withdrawchecklist;
    }
}

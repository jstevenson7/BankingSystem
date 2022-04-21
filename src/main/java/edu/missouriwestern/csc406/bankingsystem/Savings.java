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
    private String savingsID;
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

    public Savings(String savingsID, double balance, double interestRate, String startDate, String customerID) {
        this.savingsID = savingsID;
        this.balance = balance;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.customerID = customerID;
        this.currentDate = "04-21-2022";
    }

    public void dailyInterest () throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date startingdate = sdf.parse(startDate);
        Date currentdate = sdf.parse(currentDate);

        long diffInTime = Math.abs(currentdate.getTime() - startingdate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInTime, TimeUnit.DAYS);

        while (diff > 0) {


            balance += (interestRate / 365) * balance;
            diff--;
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
        balance-=check.getAmount();
        withdrawchecklist.add(check);

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

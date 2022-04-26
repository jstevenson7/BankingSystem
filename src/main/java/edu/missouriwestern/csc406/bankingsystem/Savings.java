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
    private String savingsAccNumber; //account num
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private double interestRate;
    @CsvBindByName
    private String startDate;
    @CsvBindByName
    private String SSN;

    //private String currentDate;

    public Savings()
    {

    }

    public Savings(String savingsAccNumber, double balance, double interestRate, String SSN) throws ParseException {
        //LocalDate date
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        setSavingsAccNumber(savingsAccNumber);
        setBalance(balance);
        setInterestRate(interestRate);
        setStartDate(formatter.format(date));
        setSSN(SSN);
        dailyInterest();

    }

    public void dailyInterest () throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        Date startingdate = sdf.parse(startDate);
        Date currentdate = sdf.parse(calcCurrentDate());

        long diffInTime = currentdate.getTime() - startingdate.getTime();
        long diffInDay = (diffInTime/(1000*60*60*24));
        int count = (int) diffInDay;

        while (count > 0)
        {
            balance = balance + balance*(interestRate/365);
            count--;
        }

    }
    // returns string of current date
    public String calcCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(date);
    }
    public void deposit(Double amount)
    {
        balance+= amount;
    }

    public void withdraw(Double amount)
    {
        balance-= amount;
    }

    // Getters and Setters
    public String getSavingsAccNumber() {
        return savingsAccNumber;
    }
    public void setSavingsAccNumber(String savingsAccNumber) {
        this.savingsAccNumber = savingsAccNumber;
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
    public String getSSN() {
        return SSN;
    }
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }
    @Override
    public String toString() {
        return "Savings{" +
                "savingsAccNumber='" + savingsAccNumber + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                ", startDate='" + startDate + '\'' +
                ", SSN='" + SSN + '\'' +
                '}';
    }
}

package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CD {

    @CsvBindByName
    private String cdAcctNum; //account num
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private double interestRate;
    @CsvBindByName
    private String startDate;
    @CsvBindByName
    private String endDate;
    @CsvBindByName
    private String SSN;

    public CD()
    {

    }

    public CD(String cdAcctNum, double balance, double interestRate, String startDate, String endDate, String SSN) {
        this.cdAcctNum = cdAcctNum;
        this.balance = balance;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.SSN = SSN;
    }

    public int withdrawamt (Double amount)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startingdate = LocalDate.parse(startDate, formatter);
        LocalDate endingdate = LocalDate.parse(endDate, formatter);
        LocalDate withdrawingDate = LocalDate.now();

        if (withdrawingDate.isEqual(endingdate) || withdrawingDate.isAfter(endingdate))
        {
            if (getBalance() >= amount) {
                DecimalFormat f = new DecimalFormat("##.00");
                setBalance(Double.parseDouble(f.format((balance -= amount))));
                return 0;
            } else {
                return 1; // insufficient funds
            }
        }
        else if (withdrawingDate.isBefore(endingdate))
        {
            DecimalFormat f = new DecimalFormat("##.00");
            if (getBalance() >= (Double.parseDouble(f.format((amount))) + Double.parseDouble(f.format(getBalance() * getInterestRate())))) {
                setBalance(Double.parseDouble(f.format((balance -= amount))));
                setBalance(Double.parseDouble(f.format(balance -= balance * interestRate)));
                return 2; // success with fee
            } else {
                return 1;
            }
        }
        return 9; // weird error
    }

    public String getCdAcctNum() {
        return cdAcctNum;
    }

    public void setCdAcctNum(String cdAcctNum) {
        this.cdAcctNum = cdAcctNum;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }
}

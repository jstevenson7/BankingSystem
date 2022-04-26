package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CertificateofDeposit {

    @CsvBindByName
    private String CDID; //account num
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private double interestRate;
    @CsvBindByName
    private String startDate;
    @CsvBindByName
    private String endDate;
    @CsvBindByName
    private String withdrawDate;

    public CertificateofDeposit()
    {

    }

    public CertificateofDeposit(String CDID, double balance, double interestRate, String startDate, String endDate, String withdrawDate) {
        this.CDID = CDID;
        this.balance = balance;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.withdrawDate = withdrawDate;
        withdrawamt();
    }

    public void withdrawamt ()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate startingdate = LocalDate.parse(startDate, formatter);
        LocalDate endingdate = LocalDate.parse(endDate, formatter);
        LocalDate withdrawingDate = LocalDate.parse(withdrawDate, formatter);

        if (withdrawingDate.isEqual(endingdate) || withdrawingDate.isAfter(endingdate))
        {
            balance += balance * interestRate;
        }
        else if (withdrawingDate.isBefore(endingdate))
        {
            balance -= balance * interestRate;
        }
    }

    public String getCDID() {
        return CDID;
    }

    public void setCDID(String CDID) {
        this.CDID = CDID;
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

    public String getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(String withdrawDate) {
        this.withdrawDate = withdrawDate;
    }
}

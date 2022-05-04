package edu.missouriwestern.csc406.bankingsystem;


import com.opencsv.bean.CsvBindByName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Loans  {

    @CsvBindByName
    private String LoanID;
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
    @CsvBindByName
    private double monthlyPayment;


    private int problem;//identifies whether the account is a problem or not.  1 is problem.

    private Date paymentdate;


    private double initialbalance;

    private double paymentplan;

    private double totalloan;

    private double paymentdue;

    public Loans() {

    }

    public Loans(String loanAcctNum, double balance, double interestRate, String startDate, String endDate, double monthlyPayment, String SSN) {
        this.LoanID = loanAcctNum;
        this.balance = balance;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialbalance = balance;
        this.SSN = SSN;
        this.monthlyPayment = monthlyPayment;
        problem = 0;//accounts aren't created as problem accounts, unless specified.
    }



    public void interest () throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date startingdate = sdf.parse(startDate);
        Date enddate = sdf.parse(endDate);

        double diffInTime = enddate.getTime() - startingdate.getTime();
        System.out.println("enddate = " + enddate.getTime());
        System.out.println("startdate = " + startingdate.getTime());
        double diffInMonth = (diffInTime/(2592000000.00));
        System.out.println("diffinmonth = " + diffInTime);
        int count = (int) diffInMonth;
        System.out.println("diffinmonth = " + diffInMonth);
        totalloan = initialbalance + (initialbalance/2) * ((count/12) * interestRate);
        paymentplan = totalloan/diffInMonth;  // <- THIS IS WHAT HE WANTS
        paymentdue = paymentplan;
    }


    public void makepayment(double payment) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = new Date();
        paymentdate = now;


        if (paymentontime(paymentdate) == true)
        {
            System.out.println("Thank you for your on-time payment.");
        }
        else if(paymentontime(paymentdate) == false && payment >= paymentdue)
        {
            paymentdue +=75;
            problem = 1;

        }

        if (payment >= paymentdue)
        {
            paymentdue = 0;
            balance -= payment;
            System.out.println("Your balance on your loan is now: " + balance);
        }
        else if (payment < paymentdue)
        {
            paymentdue -= payment;
            balance -= payment;
            System.out.println("Unfortunately, you did not pay the entire amount for your loan this month.  You still owe: $" + paymentdue + " this month");

        }
    }

    public boolean paymentontime(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date startingdate = sdf.parse(startDate);

        Date duedate = new Date();
        duedate.setTime((long) (startingdate.getTime() + 252000000.00));


        if(date.after(duedate))
        {
            return false;
        }

        return true;


    }


    @Override
    public String toString() {
        return "Loans{" +
                "loanAcctNum='" + LoanID + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", problem=" + problem +
                ", paymentdate=" + paymentdate +
                ", initialbalance=" + initialbalance +
                ", paymentplan=" + paymentplan +
                ", totalloan=" + totalloan +
                ", paymentdue=" + paymentdue +
                '}';
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getLoanID() {
        return LoanID;
    }

    public void setLoanID(String loanID) {
        LoanID = loanID;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getLoanAcctNum() {
        return LoanID;
    }

    public void setLoanAcctNum(String LoanID) {
        this.LoanID = LoanID;
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

    public int getProblem() {
        return problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }

    public Date getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    public double getInitialbalance() {
        return initialbalance;
    }

    public void setInitialbalance(double initialbalance) {
        this.initialbalance = initialbalance;
    }

    public double getPaymentplan() {
        return paymentplan;
    }

    public void setPaymentplan(double paymentplan) {
        this.paymentplan = paymentplan;
    }

    public double getTotalloan() {
        return totalloan;
    }

    public void setTotalloan(double totalloan) {
        this.totalloan = totalloan;
    }

    public double getPaymentdue() {
        return paymentdue;
    }

    public void setPaymentdue(double paymentdue) {
        this.paymentdue = paymentdue;
    }
}

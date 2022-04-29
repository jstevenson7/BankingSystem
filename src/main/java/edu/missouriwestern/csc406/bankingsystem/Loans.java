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
    private String loanAcctNum;
    @CsvBindByName
    private double balance;
    @CsvBindByName
    private double interestRate;
    @CsvBindByName
    private String startDate;
    @CsvBindByName
    private String endDate;
    @CsvBindByName
    private int loanType;//identifies whether it's a long-term or short-term loan.  long-term = 3, short-term = 2, credit card = 1
    @CsvBindByName
    private int loanlength;
    @CsvBindByName
    private double principal;
    @CsvBindByName
    private double paid;

    private ArrayList<Date> datelist = new ArrayList<>();

    private double payment;

    private int problem;//identifies whether the account is a problem or not.  1 is problem.

    private Date paymentdate;

    private int recurringpaymentdate;

    private double initialbalance;

    private double paymentplan;

    private double totalloan;

    private double paymentdue;

    public Loans() {

    }

    public Loans(String loanAcctNum, double balance, double interestRate, String startDate, String endDate, int loanType) throws ParseException {
        this.loanAcctNum = loanAcctNum;
        this.balance = balance;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanType = loanType;
        this.initialbalance = balance;
        problem = 0;//accounts aren't created as problem accounts, unless specified.
        interest();

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
        paymentplan = totalloan/diffInMonth;
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
        else if(paymentontime(paymentdate) == false)
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



    public void amountpaid()
    {
        balance -= this.paid;
    }


    @Override
    public String toString() {
        return "Loans{" +
                "loanAcctNum='" + loanAcctNum + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", loanType=" + loanType +
                ", initialbalance=" + initialbalance +
                ", paymentplan=" + paymentplan +
                ", totalloan=" + totalloan +
                '}';
    }

    public String getLoanAcctNum() {
        return loanAcctNum;
    }

    public void setLoanAcctNum(String loanAcctNum) {
        this.loanAcctNum = loanAcctNum;
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

    public int getLoanType() {
        return loanType;
    }

    public void setLoanType(int loanType) {
        this.loanType = loanType;
    }

    public static Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public void ccpurchase (double amount)
    {
        balance+= amount;
    }
}

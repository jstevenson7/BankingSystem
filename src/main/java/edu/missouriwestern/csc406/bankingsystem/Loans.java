package edu.missouriwestern.csc406.bankingsystem;


import com.opencsv.bean.CsvBindByName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private String currentDate;

    private double payment;

    private int problem;//identifies whether the account is a problem or not.  1 is problem.

    private Date paymentdate;


    private double paymentplan;

    public Loans() {

    }

    public Loans(String loanAcctNum, double balance, double interestRate, String startDate, String endDate, int loanType) throws ParseException {
        this.loanAcctNum = loanAcctNum;
        this.balance = balance;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanType = loanType;
        problem = 0;//accounts aren't created as problem accounts, unless specified.
        this.currentDate = "04-20-2022";
        interest();
        paymentplan();

    }

    public void interest () throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date startingdate = sdf.parse(startDate);
        Date currentdate = sdf.parse(currentDate);

        long diffInTime = currentdate.getTime() - startingdate.getTime();
        long diffInMonth = (diffInTime/(1000*60*60*24*30));
        int count = (int) diffInMonth;

        while (count > 0)
        {
            balance = balance + principal *(interestRate/12);
            count--;
        }

    }

    public void paymentplan()
    {
        paymentplan = (principal/loanlength)/12;
    }

    public void makepayment()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        balance -= payment;
        Date now = new Date();
        paymentdate = now;
        paymentontime();
    }

    public void paymentontime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = new Date();
        if (paymentdate.before(getFirstDateOfMonth(now)))
        {
            problem = 0;
        }
        else if(paymentdate.after((getFirstDateOfMonth(now))))
        {
            problem = 1;
            balance+=75;
        }


    }

    public void amountpaid()
    {
        balance -= this.paid;
    }


    @Override
    public String toString() {
        return "Loans{" +
                "LoanID='" + loanAcctNum + '\'' +
                ", balance=" + balance +
                ", interestRate=" + interestRate +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", loanType=" + loanType +
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

    public static Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public void ccpurchase (double amount)
    {
        balance+= amount;
    }
}

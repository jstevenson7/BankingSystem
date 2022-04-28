package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.Date;

public class CreditCards {

    @CsvBindByName
    private String creditcardnumber;
    @CsvBindByName
    private double creditcardlimit;
    @CsvBindByName
    private double interestRate;
    @CsvBindByName
    private String SSN;

    private Date paymentdate;

    private double paymentplan;

    private double balance;

    private String purchaseinfo;

    private ArrayList<String> purchasehistory = new ArrayList<String>();

    public CreditCards()
    {

    }

    public CreditCards(String creditcardnumber, double creditcardlimit, double interestRate, String SSN) {
        this.creditcardnumber = creditcardnumber;
        this.creditcardlimit = creditcardlimit;
        this.interestRate = interestRate;
        this.SSN = SSN;
    }

    public void makepurchase (double purchaseamnt, String purchaseinformation)
    {
        if ((balance + purchaseamnt) > creditcardlimit)
        {
            System.out.println("Insufficient funds");
        }
        else if ((balance + purchaseamnt) <= creditcardlimit)
        {
            balance += purchaseamnt;
            purchasehistory.add(purchaseamnt + " " + purchaseinformation);
        }
    }

    public void makepayment (double paymentamt)
    {
        if ((balance - paymentamt) < 0)
        {
            System.out.println("Invalid amount, can't have a balance of less than 0");

        }
        else if((balance - paymentamt) >= 0)
        {

            balance -= paymentamt;

        }
    }

    public String getCreditcardnumber() {
        return creditcardnumber;
    }


    @Override
    public String toString() {
        return "CreditCards{" +
                "creditcardnumber='" + creditcardnumber + '\'' +
                ", creditcardlimit=" + creditcardlimit +
                ", interestRate=" + interestRate +
                ", SSN='" + SSN + '\'' +
                ", paymentdate=" + paymentdate +
                ", paymentplan=" + paymentplan +
                ", balance=" + balance +
                ", purchaseinfo='" + purchaseinfo + '\'' +
                ", purchasehistory=" + purchasehistory +
                '}';
    }

    public void setCreditcardnumber(String creditcardnumber) {
        this.creditcardnumber = creditcardnumber;
    }

    public double getCreditcardlimit() {
        return creditcardlimit;
    }

    public void setCreditcardlimit(double creditcardlimit) {
        this.creditcardlimit = creditcardlimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public Date getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    public double getPaymentplan() {
        return paymentplan;
    }

    public void setPaymentplan(double paymentplan) {
        this.paymentplan = paymentplan;
    }

    public String getPurchaseinfo() {
        return purchaseinfo;
    }

    public void setPurchaseinfo(String purchaseinfo) {
        this.purchaseinfo = purchaseinfo;
    }

    public ArrayList<String> getPurchasehistory() {
        return purchasehistory;
    }

    public void setPurchasehistory(ArrayList<String> purchasehistory) {
        this.purchasehistory = purchasehistory;
    }

    public void purchaseHistory (String purchaseinfo)
    {
        purchasehistory.add(purchaseinfo);
    }

    
}

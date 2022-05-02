package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private double balance;

    private LocalDate datedue;

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
        String now = calcCurrentDate();
        if ((balance + purchaseamnt) > creditcardlimit)
        {
            System.out.println("Insufficient funds");
            System.out.println("Your current balance is " + balance + " and your credit card limit is " + creditcardlimit);
        }
        else if ((balance + purchaseamnt) <= creditcardlimit)
        {
            balance += purchaseamnt;
            purchasehistory.add("$" + purchaseamnt + " for a " + purchaseinformation + " on " + now );
        }


    }

    public void makepayment (double paymentamt)
    {
        if ((balance - paymentamt) < 0)
        {
            System.out.println("Invalid amount, can't have a balance of less than.  You currently have a balance of: " + balance);

        }
        else if((balance - paymentamt) >= 0)
        {

            balance -= paymentamt;
            System.out.println("Payment processed in the amount of " + paymentamt);

        }
    }

    public void paymentdue ()
    {

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        System.out.println(date.getMonthValue());


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
                ", balance=" + balance +
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

    public String calcCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(date);
    }




}

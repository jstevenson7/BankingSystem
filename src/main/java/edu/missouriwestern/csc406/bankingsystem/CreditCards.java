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
    private String SSN;
    @CsvBindByName
    private double balance;

    private LocalDate datedue;

    public ArrayList<String> purchasehistory = new ArrayList<String>();

    public CreditCards() {

    }

    public CreditCards(String creditcardnumber, double creditcardlimit,  String SSN) {
        this.creditcardnumber = creditcardnumber;
        this.creditcardlimit = creditcardlimit;
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

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getDatedue() {
        return datedue;
    }

    public void setDatedue(LocalDate datedue) {
        this.datedue = datedue;
    }

    public ArrayList<String> getPurchasehistory() {
        return purchasehistory;
    }

    public void setPurchasehistory(ArrayList<String> purchasehistory) {
        this.purchasehistory = purchasehistory;
    }

    public String calcCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(date);
    }




}

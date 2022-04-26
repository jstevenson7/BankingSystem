package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.Date;

public class CreditCards {

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

    private String purchaseinfo;

    private ArrayList<String> purchasehistory = new ArrayList<String>();

    public CreditCards()
    {

    }

    
}

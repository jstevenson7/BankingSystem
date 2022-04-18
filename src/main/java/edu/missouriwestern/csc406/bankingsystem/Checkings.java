package edu.missouriwestern.csc406.bankingsystem;

import java.util.ArrayList;

public class Checkings extends Account
{
    private String accounttype;

    private ArrayList<Check> checklist = new ArrayList<Check>();

    public Checkings(int custID, double balance) {
        super(custID, balance);
    }

    public void testaccount()
    {
        if (balance >= 1000.00)
        {
            accounttype = "Gold/Diamond Account";
        }
        else if (balance < 1000.00)
        {
            accounttype = "That's My Bank";
        }
    }

    public void deposit (double deposit)
    {
        balance += deposit;
        testaccount();
    }

    public void depositcheck (Check check)
    {
        double depositamt = check.getAmount();
        balance += depositamt;
        checklist.add(check);

    }

    public void withdrawalcheck (Check check)
    {
        double withdrawamt = check.getAmount();
        balance -= withdrawamt;
        checklist.add(check);

    }

    public void withdraw (double withdraw)
    {
        balance -= withdraw;
        testaccount();
    }

}

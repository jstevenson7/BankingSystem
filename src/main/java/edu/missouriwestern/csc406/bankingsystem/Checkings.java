package edu.missouriwestern.csc406.bankingsystem;

import java.util.ArrayList;

public class Checkings extends Account
{
    private String accounttype;

    private ArrayList<Check> depositchecklist = new ArrayList<Check>();
    private ArrayList<Check> withdrawchecklist = new ArrayList<Check>();


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

    public void depositcash (double deposit)
    {
        balance += deposit;
        testaccount();
        if (accounttype.equals("That's My Bank"))
        {
            balance-=.5;
        }
    }

    public void depositcheck (Check check)
    {
        double depositamt = check.getAmount();
        balance += depositamt;
        depositchecklist.add(check);
        testaccount();
        if (accounttype.equals("That's My Bank"))
        {
            balance-=.5;
        }
    }

    public void withdrawalcheck (Check check)
    {
        double withdrawamt = check.getAmount();
        balance -= withdrawamt;
        withdrawchecklist.add(check);
        testaccount();
        if (accounttype.equals("That's My Bank"))
        {
            balance-=.5;
        }
    }

    public void withdrawcash (double withdraw)
    {
        balance -= withdraw;
        testaccount();
        if (accounttype.equals("That's My Bank"))
        {
            balance-=.5;
        }
    }

    public void transfer (double transferamt, Account account)
    {
        account.setBalance(account.getBalance()+transferamt);
        balance -= transferamt;
        if (accounttype.equals("That's My Bank"))
        {
            balance-=.75;
        }

    }

}

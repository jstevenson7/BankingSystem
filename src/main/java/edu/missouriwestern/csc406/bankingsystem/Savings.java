package edu.missouriwestern.csc406.bankingsystem;

public class Savings extends Account
{
    String accountType;
    
    public Savings(int custID, double balance) {
        super(custID, balance);
    }
    
    public void testaccount()
    {
        if (balance >= 1000.00)
        {
            accountType = "Gold/Diamond Account";
        }
        else if (balance < 1000.00)
        {
            accountType = "That's My Bank";
        }
    }
    
    public void deposit (double deposit)
    {
        balance += deposit;
        testaccount();
    }
    
    public void withdraw (double withdraw)
    {
        balance -= withdraw;
        testaccount();
    }
    
}

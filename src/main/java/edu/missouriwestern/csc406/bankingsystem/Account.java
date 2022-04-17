package edu.missouriwestern.csc406.bankingsystem;

public abstract class Account
{
    int custID;
    double balance;

    public Account( int custID, double balance)
    {
        setCustID(custID);
        setBalance(balance);
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void Withdrawl(int withdraw)
    {

    }

    public void Deposit(int deposit)
    {
        
    }
}

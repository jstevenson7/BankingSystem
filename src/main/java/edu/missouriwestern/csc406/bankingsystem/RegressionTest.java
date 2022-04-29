package edu.missouriwestern.csc406.bankingsystem;

import java.io.IOException;
import java.text.ParseException;

public class RegressionTest {

    public static void main(String[] args) throws IOException, ParseException {

        Savings savings = new Savings("678227166", 400, .006, "111-11-1111");
        System.out.println(savings.getBalance());
        Check check = new Check("001", 399.33, "4-10-2022", "Grace Smith", "Job", "9992", "111");
        System.out.println(savings.toString());
        /**
        savings.writecheck(check);
        System.out.println(savings.getWithdrawchecklist().toString());
        System.out.println(savings.getBalance());
        savings.depositcheck(check);
        System.out.println(savings.getDepositchecklist().toString());
        System.out.println(savings.getBalance());
        **/
        CD CD = new CD("29863",20000,.039,"02-02-2015", "02-02-2020", "02-02-2020");
        System.out.println("CD Balance =" + CD.getBalance());
        
        CreditCards cc = new CreditCards("111", 2000, .05, "111-11-1111");
        cc.makepurchase(1000, "New laptop computer");
        System.out.println(cc.toString());
        cc.makepurchase(4000, "nothing i'd ever need");
        System.out.println(cc.toString());
        cc.makepurchase(150, "New guitar");
        System.out.println(cc.toString());
        cc.makepayment(750);
        System.out.println(cc.toString());
        
        
        Loans loan = new Loans("111", 20000, .04, "02-29-2022", "02-02-2027", 2);
        System.out.println(loan.toString());
        loan.makepayment(372.40487536218);

    }
}

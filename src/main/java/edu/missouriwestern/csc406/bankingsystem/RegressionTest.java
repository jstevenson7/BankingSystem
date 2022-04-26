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

    }
}

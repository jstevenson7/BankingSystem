package edu.missouriwestern.csc406.bankingsystem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class DBMS {//TODO convert into record objects and trim repetition, update filepath, finish edit process
    public static Scanner x;
    public String Search;//use clients SSN
    String filepath = "C:\\Users\\Justice\\Documents\\College\\csc406\\BankBackups\\customers.csv";//filepath for csv, change for system

    public void readRecord(String search, String filepath) {
        boolean found = false;
        String SSN = "";
        String Address = "";
        String State = "";
        String Zip = "";
        String FirstName = "";
        String LastName = "";
        String CustomerID = "";
        String CurrBal = "";
        String CurrIntRate = "";
        String DatePayDue = "";
        String DateNotifiedofPayment = "";
        String CurrPayDue = "";
        String DateSinceLastPay = "";
        String MissedPayFlag = "";   //database fields
        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter(",\n");

            while (x.hasNext() && !found) {
                SSN = x.next();
                if (SSN.equals(Search)) {//SSN is first field in record
                    found = true;
                    Address = x.next();
                    State = x.next();
                    Zip = x.next();
                    FirstName = x.next();
                    LastName = x.next();
                    CustomerID = x.next();
                    CurrBal = x.next();
                    CurrIntRate = x.next();
                    DatePayDue = x.next();
                    DateNotifiedofPayment = x.next();
                    CurrPayDue = x.next();
                    DateSinceLastPay = x.next();
                    MissedPayFlag = x.next();
                }//end of if Search=true

                if (found) {
                    System.out.println(SSN + " " + Address + " " + State + " " + Zip + " " + FirstName + " " + LastName + " " + CustomerID + " " + CurrBal + " " + CurrIntRate
                            + " " + DatePayDue + " " + DateNotifiedofPayment + " " + CurrPayDue + " " + DateSinceLastPay + " " + MissedPayFlag);
                } else {
                    System.out.println("Record not found");
                }
            }//end of while loop
        }//end of try
        catch (Exception e) {

        }//end of catch
    }//end of readRecord

    public void editRecord(String filepath, String search, String newedit) {
        String tempFile = "temp.csv";
        File original = new File(filepath);
        File newfile = new File(tempFile);
        String SSN = "";
        String Address = "";
        String State = "";
        String Zip = "";
        String FirstName = "";
        String LastName = "";
        String CustomerID = "";
        String CurrBal = "";
        String CurrIntRate = "";
        String DatePayDue = "";
        String DateNotifiedofPayment = "";
        String CurrPayDue = "";
        String DateSinceLastPay = "";
        String MissedPayFlag = "";   //database fields
        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File(filepath));
            x.useDelimiter(",\n");

            while (x.hasNext()) {
                SSN = x.next();
                Address = x.next();
                State = x.next();
                Zip = x.next();
                FirstName = x.next();
                LastName = x.next();
                CustomerID = x.next();
                CurrBal = x.next();
                CurrIntRate = x.next();
                DatePayDue = x.next();
                DateNotifiedofPayment = x.next();
                CurrPayDue = x.next();
                DateSinceLastPay = x.next();
                MissedPayFlag = x.next();

                if (SSN.equals(Search)) {//found record to edit
                    pw.println();

                }//end of if Search=true
            }//end of while
            x.close();
            pw.flush();
            pw.close();
            original.delete();
            File customers=new File(filepath);
            newfile.renameTo(customers);
        }//end of try
        catch(Exception e){
            System.out.println("Record not found");
            }//end of catch
        }//end of editRecord

    }//end of dbms

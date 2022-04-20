package extras;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class  DBMS {
    public static void readAll(){
        Map<String,String> mapping = new HashMap<String, String>();
        mapping.put("ssn","SSN");
        mapping.put("address","Address");
        mapping.put("state","State");
        mapping.put("zip","Zip");                        //Map CSV to Customer attributes
        mapping.put("firstName","FirstName");
        mapping.put("lastName","LastName");
        mapping.put("customerID","CustomerID");
        mapping.put("currentBalance","CurrentBalance");
        mapping.put("interestRates","InterestRates");
        mapping.put("datePaymentDue","DatePaymentDue");
        mapping.put("dateNotifiedofPayment","DateNotifiedofPayment");
        mapping.put("currentPaymentDue","CurrentPaymentDue");
        mapping.put("dateLastPaymentMade","DateLastPaymentMade");
        mapping.put("missedPaymentFlag","MissedPaymentFlag");
        mapping.put("loanType","LoanType");
        mapping.put("creditLimit","CreditLimit");
        mapping.put("monthsLeft","MonthsLeft");

        HeaderColumnNameTranslateMappingStrategy<Customer> customer = new HeaderColumnNameTranslateMappingStrategy<Customer>();
        customer.setType(Customer.class);        //Column Name Mapping Strategy for Customer Class
        customer.setColumnMapping(mapping);

        CSVReader csvReader=null;
        try {
            csvReader= new CSVReader(new FileReader("C:\\Users\\Justice\\Documents\\College\\csc406\\BankBackups\\customers.csv"));
        }//end of try
        catch (FileNotFoundException e){e.printStackTrace();}//end of catch

        CsvToBean csvToBean = new CsvToBean();//still figuring out this parsing
        List<Customer> list = csvToBean.parse();
    }//end of readAll
    public static void writeAll(List<String[]> StringArray){
        File update = new File("C:\\Users\\Justice\\Documents\\College\\csc406\\BankBackups\\customers.csv");
        try{
            FileWriter updatewriter = new FileWriter(update);
            CSVWriter writer = new CSVWriter(updatewriter);

            writer.writeAll(StringArray);
            writer.close();
        }//end of try
        catch(Exception e){e.printStackTrace();}//end of catch
    }//end of writeAll



//end of dbms
public class Customer{//customer object for opencsv to read into
    //customer attributes
    public String SSN, Address, State, Zip, FirstName, LastName,CustomerID,CurrentBalance,InterestRates,DatePaymentDue,
            DateNotifiedofPayment,CurrentPaymentDue,DateLastPaymentMade,MissedPaymentFlag,LoanType,CreditLimit,MonthsLeft;
    //getters/setters for attributes
    public String getSSN()
    {
        return SSN;
    }

    public void setSSN(String ssn)
    {
        SSN = ssn;
    }

    public String getAddress()
    {
        return Address;
    }

    public void setAddress(String address)
    {
        Address = address;
    }

    public String getState()
    {
        return State;
    }

    public void setState(String state)
    {
        State = state;
    }

    public String getZip()
    {
        return Zip;
    }

    public void setZip(String zip)
    {
        Zip = zip;
    }

    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        FirstName = firstName;
    }
    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        LastName = lastName;
    }

    public String getCustomerID()
    {
        return CustomerID;
    }

    public void setCustomerID(String customerID)
    {
        CustomerID = customerID;
    }

    public String getCurrentBalance()
    {
        return CurrentBalance;
    }

    public void setCurrentBalance(String currentBalance)
    {
        CurrentBalance = currentBalance;
    }

    public String getInterestRates()
    {
        return InterestRates;
    }

    public void setInterestRates(String interestRates)
    {
        InterestRates = interestRates;
    }

    public String getDatePaymentDue()
    {
        return DatePaymentDue;
    }

    public void setDatePaymentDue(String datePaymentDue)
    {
        DatePaymentDue = datePaymentDue;
    }
    public String getDateNotifiedofPayment()
    {
        return DateNotifiedofPayment;
    }

    public void setDateNotifiedofPayment(String dateNotifiedofPayment)
    {
        DateNotifiedofPayment = dateNotifiedofPayment;
    }

    public String getCurrentPaymentDue()
    {
        return CurrentPaymentDue;
    }

    public void setCurrentPaymentDue(String currentPaymentDue)
    {
        CurrentPaymentDue = currentPaymentDue;
    }

    public String getDateLastPaymentMade()
    {
        return DateLastPaymentMade;
    }

    public void setDateLastPaymentMade(String dateLastPaymentMade)
    {
        DateLastPaymentMade = dateLastPaymentMade;
    }

    public String getMissedPaymentFlag()
    {
        return MissedPaymentFlag;
    }

    public void setMissedPaymentFlag(String missedPaymentFlag)
    {
        MissedPaymentFlag = missedPaymentFlag;
    }

    public String getLoanType()
    {
        return LoanType;
    }

    public void setLoanType(String loanType)
    {
        LoanType = loanType;
    }
    public String getCreditLimit()
    {
        return CreditLimit;
    }

    public void setCreditLimit(String creditLimit)
    {
        CreditLimit = creditLimit;
    }

    public String getMonthsLeft()
    {
        return MonthsLeft;
    }

    public void setMonthsLeft(String monthsLeft)
    {
        MonthsLeft = monthsLeft;
    }

}//end of Customer
}//end of DBMS



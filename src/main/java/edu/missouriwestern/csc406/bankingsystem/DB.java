package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DB {
    public DB () {
        // No arg constructor
    }
    /** Reading and Writing to POJOs **/
    /*
    * This is a hard coded method to read from the customers.csv file (located in src) and returns
    * an arrayList of customer objects.
    *
    * EXAMPLE:
    *
    * ArrayList<Customer> customers = DB.readCustomersCSV();ArrayList<Customer> customers = DB.readCustomersCSV();
    *
    * The arrayList now has customer objects from the csv file.
    * */
    public static ArrayList<Customer> readCustomersCSV() throws IOException {
        // Arraylist to hold customer objects
        ArrayList<Customer> customers = new ArrayList<>();
        // Create reader to the customers.csv file
        Reader reader = Files.newBufferedReader(Path.of("src/customers.csv"));
        // Create a column mapping strategy for constructing the objects from the csv columns
        ColumnPositionMappingStrategy<Customer> strat = new ColumnPositionMappingStrategyBuilder<Customer>().build();
        // Set class type for strategy
        strat.setType(Customer.class);
        // Set columns
        strat.setColumnMapping(new String[]{"SSN", "Address", "City", "State", "Zip", "firstName", "lastName","atmNumber","atmPin","creditCardPin"});
        // Create bean of type customer
        CsvToBean<Customer> bean = new CsvToBeanBuilder<Customer>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through clients bean and add to list
        Iterator<Customer> clientsIterator = bean.iterator();
        while (clientsIterator.hasNext()) {
            Customer c = clientsIterator.next();
            customers.add(c);
        }
        reader.close();
        // return arraylist of customer objects
        return customers;
    }
    /*
     * This is a hard coded method to write to the customers.csv file (located in src) from an arrayList
     * of customer objects. The method takes an arrayList of type Customer and writes the arrayList to
     * the customers.csv file.
     *
     * EXAMPLE:
     * DB.writeCustomerCSV(CustomerArrayList);
     *
     * The customers.csv file now has the contents of the customers arrayList.
     * */
    public static void writeCustomerCSV(ArrayList<Customer> customers) throws IOException {
        // Create a writer to write to the customers.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/customers.csv"));
        // Set mapping strategy for the columns
        ColumnPositionMappingStrategy<Customer> strat = new ColumnPositionMappingStrategyBuilder<Customer>().build();
        // Set class type for the mapping strategy
        strat.setType(Customer.class);
        // Set column names for the mapping strategy
        strat.setColumnMapping(new String[]{"SSN", "Address", "City", "State", "Zip", "firstName", "lastName","atmNumber", "atmPin","creditCardPin"});
        // Create bean of type Customers with the created mapping strategy
        StatefulBeanToCsv<Customer> beanToCsv = new StatefulBeanToCsvBuilder<Customer>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write the header first
            writer.write("SSN,Address,City,State,Zip,firstName,lastName,atmNumber,atmPin,creditCartPin\n");
            // Write the customers arrayList to the customers.csv file
            beanToCsv.write(customers);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }
    /*
     * This is a hard coded method to read from the employee.csv file (located in src) and returns
     * an arrayList of employee objects.
     *
     * EXAMPLE:
     *
     * ArrayList<Employee> Employees = DB.readEmployeeCSV();
     *
     * The arrayList now has customer objects from the csv file.
     * */
    public static ArrayList<Employee> readEmployeeCSV() throws IOException {
        // Create an arrayList to hold employee objects
        ArrayList<Employee> employees = new ArrayList<>();
        // Create reader to read from the employee.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/employee.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<Employee> strat = new ColumnPositionMappingStrategyBuilder<Employee>().build();
        // Set strategy class type
        strat.setType(Employee.class);
        // Set column names
        strat.setColumnMapping(new String[]{"employeeID", "lastName", "firstName", "isManager", "password"});
        // Create bean of type employee
        CsvToBean<Employee> bean = new CsvToBeanBuilder<Employee>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through clients bean and add to list
        Iterator<Employee> employeeIterator = bean.iterator();
        while (employeeIterator.hasNext()) {
            Employee e = employeeIterator.next();
            employees.add(e);
        }
        reader.close();
        // return arrayList of employees
        return employees;
    }
    /*
     * This is a hard coded method to write to the employee.csv file (located in src) from an arrayList
     * of employee objects. The method takes an arrayList of type Employee and writes the arrayList to
     * the employee.csv file.
     *
     * EXAMPLE:
     *
     * DB.writeEmployeeCSV(EmployeeArrayList);
     *
     * The Employee.csv file now has the contents of the employee arrayList.
     * */
    public static void writeEmployeeCSV(ArrayList<Employee> employees) throws IOException {
        // Create writer to write to the employee.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/employee.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<Employee> strat = new ColumnPositionMappingStrategyBuilder<Employee>().build();
        // Set mapping strategy class type
        strat.setType(Employee.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"employeeID", "lastName", "firstName", "isManager", "password"});
        // Create bean of type employee
        StatefulBeanToCsv<Employee> beanToCsv = new StatefulBeanToCsvBuilder<Employee>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("employeeID,lastName,firstName,isManager,password\n");
            // Write contents of employee arrayList to employee.csv
            beanToCsv.write(employees);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }
    /*
     * This method takes an employeeID (String) and an ArrayList of employees
     * and return the employee object of the employeeID passed in
     * EXAMPLE:
     * Employee emp = DB.searchEmployee("someID", empArrayList");
     *
     * NOTE: Returns NULL if employee not found
     * */
    // Method for reading from checking.csv to ArrayList
    public static ArrayList<Checking> readCheckingCSV() throws IOException {
        // Create an arrayList to hold checking objects
        ArrayList<Checking> checkings = new ArrayList<>();
        // Create reader to read from the checking.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/checking.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<Checking> strat = new ColumnPositionMappingStrategyBuilder<Checking>().build();
        // Set strategy class type
        strat.setType(Checking.class);
        // Set column names
        strat.setColumnMapping(new String[]{"checkingAcctNum", "balance", "accountType", "SSN", "overdraftAccountNumber"});
        // Create bean of type
        CsvToBean<Checking> bean = new CsvToBeanBuilder<Checking>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through bean and add to list
        Iterator<Checking> checkingIterator = bean.iterator();
        while (checkingIterator.hasNext()) {
            Checking checking = checkingIterator.next();
            checkings.add(checking);
        }
        reader.close();
        // return arrayList
        return checkings;
    }
    // Method for writing checking ArrayList to checking.csv
    public static void writeCheckingCSV(ArrayList<Checking> checkings) throws IOException {
        // Create writer to write to the checking.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/checking.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<Checking> strat = new ColumnPositionMappingStrategyBuilder<Checking>().build();
        // Set mapping strategy class type
        strat.setType(Checking.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"checkingAcctNum", "balance", "accountType", "SSN", "overdraftAccountNumber"});
        // Create bean of type employee
        StatefulBeanToCsv<Checking> beanToCsv = new StatefulBeanToCsvBuilder<Checking>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("checkingAcctNum,balance,accountType,SSN,overdraftAccountNumber\n");
            // Write contents of employee arrayList to employee.csv
            beanToCsv.write(checkings);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }
    // Method for reading from checks.csv to ArrayList
    public static ArrayList<Check> readProcessedCheckCSV() throws IOException {
        // Create an arrayList to hold check objects
        ArrayList<Check> checks = new ArrayList<>();
        // Create reader to read from the checks.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/processedChecks.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<Check> strat = new ColumnPositionMappingStrategyBuilder<Check>().build();
        // Set strategy class type
        strat.setType(Check.class);
        // Set column names
        strat.setColumnMapping(new String[]{"checkNum","amount","date","recipient","description","accountNumber","routingNumber"});
        // Create bean of type
        CsvToBean<Check> bean = new CsvToBeanBuilder<Check>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through bean and add to list
        Iterator<Check> checkingIterator = bean.iterator();
        while (checkingIterator.hasNext()) {
            Check check = checkingIterator.next();
            checks.add(check);
        }
        reader.close();
        // return arrayList
        return checks;
    }
    // Method for writing check ArrayList to checks.csv
    public static void writeProcessedCheckCSV(ArrayList<Check> checks) throws IOException {
        // Create writer to write to the checks.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/processedChecks.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<Check> strat = new ColumnPositionMappingStrategyBuilder<Check>().build();
        // Set mapping strategy class type
        strat.setType(Check.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"checkNum","amount","date","recipient","description","accountNumber","routingNumber"});
        // Create bean of type employee
        StatefulBeanToCsv<Check> beanToCsv = new StatefulBeanToCsvBuilder<Check>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("checkNum,amount,date,recipient,description,accountNumber,routingNumber\n");
            // Write contents of employee arrayList to employee.csv
            beanToCsv.write(checks);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }
    // Method for reading from UNPROCESSED checks.csv to ArrayList
    public static ArrayList<Check> readUnprocessedCheckCSV() throws IOException {
        // Create an arrayList to hold check objects
        ArrayList<Check> checks = new ArrayList<>();
        // Create reader to read from the checks.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/unprocessedChecks.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<Check> strat = new ColumnPositionMappingStrategyBuilder<Check>().build();
        // Set strategy class type
        strat.setType(Check.class);
        // Set column names
        strat.setColumnMapping(new String[]{"checkNum","amount","date","recipient","description","accountNumber","routingNumber"});
        // Create bean of type
        CsvToBean<Check> bean = new CsvToBeanBuilder<Check>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through bean and add to list
        Iterator<Check> checkingIterator = bean.iterator();
        while (checkingIterator.hasNext()) {
            Check check = checkingIterator.next();
            checks.add(check);
        }
        reader.close();
        // return arrayList
        return checks;
    }
    // Method for writing check UNPROCESSED ArrayList to checks.csv
    public static void writeUnprocessedCheckCSV(ArrayList<Check> checks) throws IOException {
        // Create writer to write to the checks.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/unprocessedChecks.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<Check> strat = new ColumnPositionMappingStrategyBuilder<Check>().build();
        // Set mapping strategy class type
        strat.setType(Check.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"checkNum","amount","date","recipient","description","accountNumber","routingNumber"});
        // Create bean of type check
        StatefulBeanToCsv<Check> beanToCsv = new StatefulBeanToCsvBuilder<Check>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("checkNum,amount,date,recipient,description,accountNumber,routingNumber\n");
            // Write contents of checks to check.csv
            beanToCsv.write(checks);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }
    public static ArrayList<Savings> readSavingsCSV() throws IOException {
        // Create an arrayList to hold savings objects
        ArrayList<Savings> savings = new ArrayList<>();
        // Create reader to read from the savings.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/savings.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<Savings> strat = new ColumnPositionMappingStrategyBuilder<Savings>().build();
        // Set strategy class type
        strat.setType(Savings.class);
        // Set column names
        strat.setColumnMapping(new String[]{"savingsAcctNum","balance","interestRate","startDate","SSN"});
        // Create bean of type savings
        CsvToBean<Savings> bean = new CsvToBeanBuilder<Savings>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through bean and add to list
        Iterator<Savings> checkingIterator = bean.iterator();
        while (checkingIterator.hasNext()) {
            Savings saving= checkingIterator.next();
            savings.add(saving);
        }
        reader.close();
        // return arrayList
        return savings;
    }
    public static void writeSavingsCSV(ArrayList<Savings> savings) throws IOException {
        // Create writer to write to the savings.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/savings.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<Savings> strat = new ColumnPositionMappingStrategyBuilder<Savings>().build();
        // Set mapping strategy class type
        strat.setType(Savings.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"savingsAcctNum","balance","interestRate","startDate","SSN"});
        // Create bean of type savings
        StatefulBeanToCsv<Savings> beanToCsv = new StatefulBeanToCsvBuilder<Savings>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("savingsAcctNum,balance,interestRate,startDate,SSN\n");
            // Write contents of savings to savings.csv
            beanToCsv.write(savings);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }
    public static ArrayList<Transaction> readTransactionsCSV() throws IOException {
        // Create an arrayList to hold check objects
        ArrayList<Transaction> transactions = new ArrayList<>();
        // Create reader to read from the checks.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/transactions.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<Transaction> strat = new ColumnPositionMappingStrategyBuilder<Transaction>().build();
        // Set strategy class type
        strat.setType(Transaction.class);
        // Set column names
        strat.setColumnMapping(new String[]{"transactionNum","SSN","accountType","accountNum","amount","memo","date","checkNum"});
        // Create bean of type
        CsvToBean<Transaction> bean = new CsvToBeanBuilder<Transaction>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through bean and add to list
        Iterator<Transaction> checkingIterator = bean.iterator();
        while (checkingIterator.hasNext()) {
            Transaction transaction = checkingIterator.next();
            transactions.add(transaction);
        }
        reader.close();
        // return arrayList
        return transactions;
    }public static void writeTransactionsCSV(ArrayList<Transaction> transactions) throws IOException {
        // Create writer to write to the savings.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/transactions.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<Transaction> strat = new ColumnPositionMappingStrategyBuilder<Transaction>().build();
        // Set mapping strategy class type
        strat.setType(Transaction.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"transactionNum","SSN","accountType","accountNum","amount","memo","date","checkNum"});
        // Create bean of type savings
        StatefulBeanToCsv<Transaction> beanToCsv = new StatefulBeanToCsvBuilder<Transaction>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("transactionNum,SSN,accountType,accountNum,amount,memo,date,checkNum\n");
            // Write contents of savings to savings.csv
            beanToCsv.write(transactions);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }


    public static ArrayList<CD> readCDCSV() throws IOException {
        // Create an arrayList to hold check objects
        ArrayList<CD> cds = new ArrayList<>();
        // Create reader to read from the checks.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/cd.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<CD> strat = new ColumnPositionMappingStrategyBuilder<CD>().build();
        // Set strategy class type
        strat.setType(CD.class);
        // Set column names
        strat.setColumnMapping(new String[]{"cdAcctNum","balance","interestRate","startDate","endDate","SSN"});
        // Create bean of type
        CsvToBean<CD> bean = new CsvToBeanBuilder<CD>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through bean and add to list
        Iterator<CD> checkingIterator = bean.iterator();
        while (checkingIterator.hasNext()) {
            CD cd = checkingIterator.next();
            cds.add(cd);
        }
        reader.close();
        // return arrayList
        return cds;
    }

    public static void writeCDCSV(ArrayList<CD> cds) throws IOException {
        // Create writer to write to the savings.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/cd.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<CD> strat = new ColumnPositionMappingStrategyBuilder<CD>().build();
        // Set mapping strategy class type
        strat.setType(CD.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"cdAcctNum","balance","interestRate","startDate","endDate","SSN"});
        // Create bean of type savings
        StatefulBeanToCsv<CD> beanToCsv = new StatefulBeanToCsvBuilder<CD>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("cdAcctNum,balance,interestRate,startDate,endDate,SSN\n");
            // Write contents of savings to savings.csv
            beanToCsv.write(cds);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }

    public static ArrayList<CreditCards> readCreditCardCSV() throws IOException {
        // Create an arrayList to hold check objects
        ArrayList<CreditCards> cds = new ArrayList<>();
        // Create reader to read from the checks.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/creditcards.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<CreditCards> strat = new ColumnPositionMappingStrategyBuilder<CreditCards>().build();
        // Set strategy class type
        strat.setType(CreditCards.class);
        // Set column names
        strat.setColumnMapping(new String[]{"creditcardnumber","creditcardlimit","balance","SSN"});
        // Create bean of type
        CsvToBean<CreditCards> bean = new CsvToBeanBuilder<CreditCards>(reader).withMappingStrategy(strat).withSkipLines(1).build();
        // iterate through bean and add to list
        Iterator<CreditCards> checkingIterator = bean.iterator();
        while (checkingIterator.hasNext()) {
            CreditCards cd = checkingIterator.next();
            cds.add(cd);
        }
        reader.close();
        // return arrayList
        return cds;
    }


    public static void writeCreditCardCSV(ArrayList<CreditCards> cds) throws IOException {
        // Create writer to write to the savings.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/creditcards.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<CreditCards> strat = new ColumnPositionMappingStrategyBuilder<CreditCards>().build();
        // Set mapping strategy class type
        strat.setType(CreditCards.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"creditcardnumber","creditcardlimit","balance","SSN"});
        // Create bean of type savings
        StatefulBeanToCsv<CreditCards> beanToCsv = new StatefulBeanToCsvBuilder<CreditCards>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("creditcardnumber,creditcardlimit,balance,SSN\n");
            // Write contents of savings to savings.csv
            beanToCsv.write(cds);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }



    public static ArrayList<Loans> readLoansCSV() throws IOException {
        // Create an arrayList to hold check objects
        ArrayList<Loans> cds = new ArrayList<>();
        // Create reader to read from the checks.csv file located in src
        Reader reader = Files.newBufferedReader(Path.of("src/loans.csv"));
        // Set column mapping strategy
        ColumnPositionMappingStrategy<Loans> strat = new ColumnPositionMappingStrategyBuilder<Loans>().build();
        // Set strategy class type
        strat.setType(Loans.class);
        // Set column names
        strat.setColumnMapping(new String[]{"LoanID","balance", "interestRate", "startDate", "endDate","monthlyPayment", "SSN"});
        // Create bean of type
        CsvToBean<Loans> bean = new CsvToBeanBuilder<Loans>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through bean and add to list
        Iterator<Loans> checkingIterator = bean.iterator();
        while (checkingIterator.hasNext()) {
            Loans cd = checkingIterator.next();
            cds.add(cd);
        }
        reader.close();
        // return arrayList
        return cds;
    }


    public static void writeLoansCSV(ArrayList<Loans> cds) throws IOException {
        // Create writer to write to the savings.csv file located in src
        Writer writer = Files.newBufferedWriter(Paths.get("src/loans.csv"));
        // Create mapping strategy for columns
        ColumnPositionMappingStrategy<Loans> strat = new ColumnPositionMappingStrategyBuilder<Loans>().build();
        // Set mapping strategy class type
        strat.setType(Loans.class);
        // Set mapping strategy column names
        strat.setColumnMapping(new String[]{"LoanID","balance", "interestRate", "startDate", "endDate","monthlyPayment", "SSN"});
        // Create bean of type savings
        StatefulBeanToCsv<Loans> beanToCsv = new StatefulBeanToCsvBuilder<Loans>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("cdAcctNum,balance,interestRate,startDate,endDate,monthlyPayment,SSN\n");
            // Write contents of savings to savings.csv
            beanToCsv.write(cds);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }














    /**
     *   Search Methods
     */
    public static Employee searchEmployee(String employeeID, ArrayList<Employee> employees) {
        for (Employee e: employees) {
            if (e.getEmployeeID().equals(employeeID)) {
                return e;
            }
        }
        return null;
    }
    public static Transaction searchTransactions(String transactionNum, ArrayList<Transaction> transactions) {
        for (Transaction t: transactions) {
            if (t.getTransactionNum().equals(transactionNum)) {
                return t;
            }
        }
        return null;
    }
    /*
     * This method takes an SSN (String) and an ArrayList of customers
     * and return the customer object of the SSN passed in
     * EXAMPLE:
     * Customer cst = DB.searchCustomer("SSN", customerArrayList");
     *
     * NOTE: Returns NULL if customer not found
     * */
    public static Customer searchCustomer(String SSN, ArrayList<Customer> customers) {
        for (Customer c: customers) {
            if (c.getSSN().equals(SSN)) {
                return c;
            }
        }
        return null;
    }
    // Searches through Checking arraylist and return checking where matches SSN
    public static Checking searchChecking(String SSN, ArrayList<Checking> checkings) {
        for (Checking c: checkings) {
            if (c.getSSN().equals(SSN)) {
                return c;
            }
        }
        return null;
    }
    // Searches through checks arraylist and returns check where checkID matches
    public static Check searchChecks(String checkNum, String accountNumber, ArrayList<Check> checks) {
        for (Check c: checks) {
            if (c.getCheckNum().equals(checkNum) && c.getAccountNumber().equals(accountNumber)) {
                return c;
            }
        }
        return null;
    }
    public static Customer searchATMCustomer(String atmNumber, ArrayList<Customer> customers) {
        for (Customer c: customers) {
            if (c.getAtmNumber().equals(atmNumber)) {
                return c;
            }
        }
        return null;
    }
    public static Customer searchAtmNumberCustomer(String atmNumber, ArrayList<Customer> customers) {
        for (Customer c: customers) {
            if (c.getAtmNumber().equals(atmNumber)) {
                return c;
            }
        }
        return null;
    }
    public static Savings searchSavings(String SSN, ArrayList<Savings> savings) {
        for (Savings s: savings) {
            if (s.getSSN().equals(SSN)) {
                return s;
            }
        }
        return null;
    }
    public static CD searchCD(String SSN, ArrayList<CD> cds) {
        for (CD cd: cds) {
            if (cd.getSSN().equals(SSN)) {
                return cd;
            }
        }
        return null;
    }
    public static CreditCards searchCreditCards(String SSN, ArrayList<CreditCards> ccs) {
        for (CreditCards c: ccs) {
            if (c.getSSN().equals(SSN)) {
                return c;
            }
        }
        return null;
    }
    /**
     *  Verify Methods
     */
    public static Boolean verifyAccountNumber(String accountNumber, ArrayList<Checking> checkings) {
        for (Checking c: checkings) {
            if (c.getCheckingAcctNum().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifySavingsAccountNumber(String savingsAccountNumber, ArrayList<Savings> savings) {
        for (Savings s: savings) {
            if (s.getSavingsAcctNum().equals(savingsAccountNumber)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyCDNumber(String cdNumber, ArrayList<CD> cds) {
        for (CD cd: cds) {
            if (cd.getCdAcctNum().equals(cdNumber)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyCreditCardNumber(String ccNum, ArrayList<CreditCards> creditCards) {
        for (CreditCards c: creditCards) {
            if (c.getCreditcardnumber().equals(ccNum)) {
                return true;
            }
        }
        return false;
    }
    // Searches through unprocessedChecks and processedChecks arraylist and returns true if check exists
    public static Boolean verifyChecks(String checkID, String accountNumber, ArrayList<Check> unprocessedChecks, ArrayList<Check> processedChecks) {
        for (Check c: unprocessedChecks) {
            if (c.getCheckNum().equals(checkID) && c.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        for (Check c: processedChecks) {
            if (c.getCheckNum().equals(checkID) && c.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyAtmNumberCustomer(String atmNumber, ArrayList<Customer> customers) {
        for (Customer c: customers) {
            if (c.getAtmNumber().equals(atmNumber)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyBalance(Double amount, Checking checking) {
        if (checking.getBalance() >= amount) {
            return true;
        }
        return false;
    }
    public static Boolean verifyBalance(Double amount, Savings savings) {
        if (savings.getBalance() >= amount) {
            return true;
        }
        return false;
    }
    public static Boolean verifyCustomer(String SSN, ArrayList<Customer> customers) {
        for (Customer c: customers) {
            if (c.getSSN().equals(SSN)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyEmployee(String empID, ArrayList<Employee> employees) {
        for (Employee emp: employees) {
            if (emp.getEmployeeID().equals(empID)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyCustomerSSN(String SSN, ArrayList<Customer> customers) {
        for (Customer c: customers) {
            if (c.getSSN().equals(SSN)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyCheckingSSN(String SSN, ArrayList<Checking> checkings) {
        for (Checking c: checkings) {
            if (c.getSSN().equals(SSN)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifySavingsSSN(String SSN, ArrayList<Savings> savings) {
        for (Savings s: savings) {
            if (s.getSSN().equals(SSN)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyCDSSN(String SSN, ArrayList<CD> cd) {
        for (CD c: cd) {
            if (c.getSSN().equals(SSN)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifyTransaction(String transactionNum, ArrayList<Transaction> transactions) {
        for (Transaction t: transactions) {
            if (t.getTransactionNum().equals(transactionNum)) {
                return true;
            }
        }
        return false;
    }
    public static String verifyNewAccountNumber() throws IOException {
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        ArrayList<Savings> savings = DB.readSavingsCSV();
        ArrayList<CD> cds = DB.readCDCSV();
        ArrayList<CreditCards> creditCards = DB.readCreditCardCSV();
        while(true) {
            String num = generateAccountNumber();
            // WILL NEED TO ADD VERIFYACCOUNT FOR SAVINGS AND LOANS
            if(!DB.verifyAccountNumber(num, checkings) && !DB.verifySavingsAccountNumber(num, savings) && !DB.verifyCDSSN(num, cds)
            && !DB.verifyCreditCardNumber(num, creditCards)) {
                return num;
            }
        }
    }
    public static String generateNewTransactionNumber() throws IOException {
        ArrayList<Transaction> transactions = DB.readTransactionsCSV();
        while(true) {
            String num = generateTransactionNumber();
            // WILL NEED TO ADD VERIFYACCOUNT FOR SAVINGS AND LOANS
            if(!DB.verifyTransaction(num, transactions)) {
                return num;
            }
        }
    }
    public static Boolean verifyOverdraft(Checking checking) {
        if (!checking.getOverdraftAccountNumber().equals("0")) {
            return true;
        }
        return false;
    }
    /**
     * Other Utility Methods
     */
    public static String generateAccountNumber() {
        // This method will return a random 9 digit number.
        Random rnd = new Random();
        int num = rnd.nextInt(999999999);
        return String.format("%09d", num);
    }
    public static String generateTransactionNumber() {
        // This method will return a random 9 digit number.
        Random rnd = new Random();
        char[] digits = new char[12];
        digits[0] = (char) (rnd.nextInt(9) + '1');
        for (int i = 1; i < 12; i++) {
            digits[i] = (char) (rnd.nextInt(10) + '0');
        }
        return new String(digits);
    }
    public static ArrayList<Transaction> readCustomerTransactions(String SSN) throws IOException {
        ArrayList<Transaction> transactions = DB.readTransactionsCSV();
        ArrayList<Transaction> sorted = new ArrayList<Transaction>();
        for (Transaction t: transactions) {
            if (t.getSSN().equals(SSN)) {
                sorted.add(t);
            }
        }
        return sorted;
    }
    public static String getTodayDate() {
        SimpleDateFormat dft = new SimpleDateFormat("MM/dd/YYYY");
        Calendar cal = Calendar.getInstance();
        Date dateobj = cal.getTime();
        return dft.format(dateobj);
    }
    public static String getEndDate() {
        SimpleDateFormat dft = new SimpleDateFormat("MM/dd/YYYY");
        Calendar cal = Calendar.getInstance();
        Date dateobj = cal.getTime();
        String modDate = dft.format(dateobj);
        int last = Integer.parseInt(modDate.substring(9,10));
        last += 5;
        String endDate = modDate.substring(0,9) + Integer.toString(last);
        return endDate;
    }

    /**
     * Following methods are for removing customers from the CSV file
     * Since we commit during each switch of the screen, we can remove accounts and write easy
     *
     * Options:
     * 0 - remove just checking account
     * 1 - remove just savings account
     * 2 - remove both accounts
     * 3 - remove both accounts and remove customer account
     * 4 - remove loans
     * 5 - remove checking, savings, and loans
     * 6 - remove checking, savings, loans, and customer account
     *
     * -RM
     */
    public static double CustomerManagers(String SSN, ArrayList<Customer> customers, ArrayList<Checking> checkings, ArrayList<Savings> savings, ArrayList<Loans> loans, int option)
    {
        double balance = 0;

        try
        {
            switch (option)
            {
                case 0:
                    balance = balance + removeChecking(checkings, SSN);
                    writeCheckingCSV(checkings);
                    return balance;
                case 1:
                    balance = balance + removeSavings(savings, SSN);
                    writeSavingsCSV(savings);
                    return balance;
                case 2:
                    balance = balance + removeChecking(checkings, SSN);
                    writeCheckingCSV(checkings);
                    balance = balance + removeSavings(savings, SSN);
                    writeSavingsCSV(savings);
                    return balance;
                case 3:
                    balance = balance + removeChecking(checkings, SSN);
                    writeCheckingCSV(checkings);
                    balance = balance + removeSavings(savings, SSN);
                    writeSavingsCSV(savings);
                    removeCustomer(customers, SSN);
                    writeCustomerCSV(customers);
                    return balance;
                case 4:
                    removeLoans(loans, SSN);
                    writeLoansCSV(loans);
                case 5:
                    balance = balance + removeChecking(checkings, SSN);
                    writeCheckingCSV(checkings);
                    balance = balance + removeSavings(savings, SSN);
                    writeSavingsCSV(savings);
                    removeLoans(loans, SSN);
                    writeLoansCSV(loans);
                    return balance;
                case 6:
                    balance = balance + removeChecking(checkings, SSN);
                    writeCheckingCSV(checkings);
                    balance = balance + removeSavings(savings, SSN);
                    writeSavingsCSV(savings);
                    removeLoans(loans, SSN);
                    writeLoansCSV(loans);
                    removeCustomer(customers, SSN);
                    writeCustomerCSV(customers);
                    return balance;
                default:
                    //invalid option, no action taken
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(); //will make more robust here in coming days
        }//end of catch

        return balance;
    }//end of Customer Managers

    public static double removeChecking(ArrayList<Checking> checkings, String SSN)
    {
        double balance = 0;

        for(int i = 0; i < checkings.size(); i++)
        {
            if(checkings.get(i).getSSN().equals(SSN))
            {
                balance = balance + checkings.get(i).getBalance();
                checkings.remove(i);
            }//end of if
        }//end of for

        return balance;
    }//end of removeChecking

    public static double removeSavings(ArrayList<Savings> savings, String SSN)
    {
        double balance = 0;

        for(int i = 0; i < savings.size(); i++)
        {
            if(savings.get(i).getSSN().equals(SSN))
            {
                balance = balance + savings.get(i).getBalance();
                savings.remove(i);
            }//end of if
        }//end of for

        return balance;
    }//end of removeSavings

    public static void removeLoans(ArrayList<Loans> loans, String SSN)
    {
        for(int i = 0; i < loans.size(); i++)
        {
            if(loans.get(i).getSSN().equals(SSN))
            {
                loans.remove(i);
            }//end of if
        }//end of for
    }//end of removeLoans

    public static void removeCustomer(ArrayList<Customer> customers, String SSN)
    {

        for(int i = 0; i < customers.size(); i++)
        {
            if(customers.get(i).getSSN().equals(SSN))
            {
                customers.remove(i);
            }//end of if
        }//end of for
    }//end of removeCustomer

    public static int overdraft(Checking checking, Double amount) throws IOException {
        DecimalFormat f = new DecimalFormat("##.00");
        // if checking has an overdraft account set
        if (!checking.getOverdraftAccountNumber().equals("0")) {
            // empty checking balance
            // if TMB
            if (checking.getAccountType()==0) {
                Double checkingCurrentBalance = Double.valueOf(f.format(checking.getBalance()-.5));
                checking.withdraw(checkingCurrentBalance);
                ArrayList<Savings> savings = DB.readSavingsCSV();
                Savings savings1 = DB.searchSavings(checking.getSSN(), savings);
                if (savings1.getBalance() < (Double.parseDouble(f.format(amount-checkingCurrentBalance)))) {
                    return 1;
                } else {
                    // withdraw remaining balance
                    savings1.withdraw(Double.valueOf(f.format(amount - checkingCurrentBalance)));
                    DB.writeSavingsCSV(savings);
                    return 0;
                }
            } else {
                // if Gold/Diamond
                Double checkingCurrentBalance = checking.getBalance();
                checking.withdraw(checkingCurrentBalance);
                // Read in savings accounts
                ArrayList<Savings> savings = DB.readSavingsCSV();
                Savings savings1 = DB.searchSavings(checking.getSSN(), savings);
                if (savings1.getBalance() < Double.parseDouble(f.format(amount-checkingCurrentBalance))) {
                    return 1;
                } else {
                    // withdraw remaining balance
                    savings1.withdraw(Double.valueOf(f.format(amount - checkingCurrentBalance)));
                    DB.writeSavingsCSV(savings);
                    return 0;
                }
            }
        }
        return 1;
    }
}

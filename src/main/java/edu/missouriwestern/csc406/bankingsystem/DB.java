package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class DB {
    public DB () {
        // No arg constructor
    }
    /*
    * This is a hard coded method to read from the customers.csv file (located in src) and returns
    * an arrayList of customer objects.
    *
    * EXAMPLE:
    *
    * ArrayList<Customer> customers = DB.readCustomersCSV();
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
        strat.setColumnMapping(new String[]{"accountNumber", "balance", "accountType", "SSN"});
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
        strat.setColumnMapping(new String[]{"accountNumber", "balance", "accountType", "SSN"});
        // Create bean of type employee
        StatefulBeanToCsv<Checking> beanToCsv = new StatefulBeanToCsvBuilder<Checking>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("accountNumber,balance,accountType,SSN\n");
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
        strat.setColumnMapping(new String[]{"checkID","amount","date","recipient","description","accountNumber","routingNumber"});
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
        strat.setColumnMapping(new String[]{"checkID","amount","date","recipient","description","accountNumber","routingNumber"});
        // Create bean of type employee
        StatefulBeanToCsv<Check> beanToCsv = new StatefulBeanToCsvBuilder<Check>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("checkID,amount,date,recipient,description,accountNumber,routingNumber\n");
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
        strat.setColumnMapping(new String[]{"checkID","amount","date","recipient","description","accountNumber","routingNumber"});
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
        strat.setColumnMapping(new String[]{"checkID","amount","date","recipient","description","accountNumber","routingNumber"});
        // Create bean of type check
        StatefulBeanToCsv<Check> beanToCsv = new StatefulBeanToCsvBuilder<Check>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write header column names
            writer.write("checkID,amount,date,recipient,description,accountNumber,routingNumber\n");
            // Write contents of checks to check.csv
            beanToCsv.write(checks);
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
    public static Check searchChecks(String checkID, String accountNumber, ArrayList<Check> checks) {
        for (Check c: checks) {
            if (c.getCheckID().equals(checkID) && c.getAccountNumber().equals(accountNumber)) {
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

    /**
     *  Verify Methods
     */
    public static Boolean verifyAccountNumber(String accountNumber, ArrayList<Checking> checkings) {
        for (Checking c: checkings) {
            if (c.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }
    public static Boolean verifySavingsIDNumber(String savingsID, ArrayList<Savings> savings) {
        for (Savings s: savings) {
            if (s.getSavingsID().equals(savingsID)) {
                return true;
            }
        }
        return false;
    }
    // Searches through unprocessedChecks and processedChecks arraylist and returns true if check exists
    public static Boolean verifyChecks(String checkID, String accountNumber, ArrayList<Check> unprocessedChecks, ArrayList<Check> processedChecks) {
        for (Check c: unprocessedChecks) {
            if (c.getCheckID().equals(checkID) && c.getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        for (Check c: processedChecks) {
            if (c.getCheckID().equals(checkID) && c.getAccountNumber().equals(accountNumber)) {
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
    public static String verifyNewAccountNumber(ArrayList<Checking> checkings, ArrayList<Savings> savings, ArrayList<Loans> loans) {
        while(true) {
            String num = generateAccountNumber();
            // WILL NEED TO ADD VERIFYACCOUNT FOR SAVINGS AND LOANS
            if(!DB.verifyAccountNumber(num, checkings) && !DB.verifySavingsIDNumber(num, savings)) {
                return num;
            }
        }
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

}

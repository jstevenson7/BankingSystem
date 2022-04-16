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

public class DB {
    public DB () {
        // No arg constructor
    }
    /*
    * This is a hard coded method to read from the customers.csv file (located in src) and returns
    * an arrayList of customer objects.
    *
    * EXAMPLE:
    * DB db = new DB();
    * Arraylist<Customer> customers = db.readCustomersCSV();
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
        strat.setColumnMapping(new String[]{"SSN", "Address", "City", "State", "Zip", "firstName", "lastName", "customerID"});
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
     * DB db = new DB();
     * writeCustomerCSV(CustomerArrayList);
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
        strat.setColumnMapping(new String[]{"SSN", "Address", "City", "State", "Zip", "firstName", "lastName", "customerID"});
        // Create bean of type Customers with the created mapping strategy
        StatefulBeanToCsv<Customer> beanToCsv = new StatefulBeanToCsvBuilder<Customer>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            // Write the header first
            writer.write("SSN,Address,City,State,Zip,firstName,lastName,customerID\n");
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
     * DB db = new DB();
     * Arraylist<Employee> Employees = db.readEmployeeCSV();
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
     * DB db = new DB();
     * writeEmployeeCSV(EmployeeArrayList);
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
}

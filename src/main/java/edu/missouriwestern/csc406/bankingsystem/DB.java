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
    public static ArrayList<Customer> readCustomersCSV() throws IOException {
        ArrayList<Customer> customers = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Path.of("src/customers.csv"));
        ColumnPositionMappingStrategy<Customer> strat = new ColumnPositionMappingStrategyBuilder<Customer>().build();
        strat.setType(Customer.class);
        strat.setColumnMapping(new String[]{"SSN", "Address", "City", "State", "Zip", "firstName", "lastName", "customerID"});
        CsvToBean<Customer> bean = new CsvToBeanBuilder<Customer>(reader).withMappingStrategy(strat).withSkipLines(1).build();

        // iterate through clients bean and add to list
        Iterator<Customer> clientsIterator = bean.iterator();
        while (clientsIterator.hasNext()) {
            Customer c = clientsIterator.next();
            customers.add(c);
        }
        reader.close();
        return customers;
    }
    public static void writeCustomerCSV(ArrayList<Customer> customers) throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get("src/customers.csv"));
        ColumnPositionMappingStrategy<Customer> strat = new ColumnPositionMappingStrategyBuilder<Customer>().build();
        strat.setType(Customer.class);
        strat.setColumnMapping(new String[]{"SSN", "Address", "City", "State", "Zip", "firstName", "lastName", "customerID"});
        StatefulBeanToCsv<Customer> beanToCsv = new StatefulBeanToCsvBuilder<Customer>(writer).withApplyQuotesToAll(false).withMappingStrategy(strat).build();
        try {
            writer.write("SSN,Address,City,State,Zip,firstName,lastName,customerID\n");
            beanToCsv.write(customers);
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        writer.close();
    }
}

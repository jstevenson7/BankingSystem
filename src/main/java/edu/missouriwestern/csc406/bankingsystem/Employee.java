package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;


public class Employee {

    @CsvBindByName
     String employeeID;

    @CsvBindByName
     String lastName;

    @CsvBindByName
     String firstName;

    @CsvBindByName
     String isManager;

    @CsvBindByName
     String password;

    public Employee(){
    }

}

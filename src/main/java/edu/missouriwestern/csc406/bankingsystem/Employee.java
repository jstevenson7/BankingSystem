package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.CsvBindByName;


public class Employee {
    // Data Fields
    @CsvBindByName
     String employeeID;

    @CsvBindByName
     String lastName;

    @CsvBindByName
     String firstName;

    @CsvBindByName
     int isManager;

    @CsvBindByName
     String password;

    // Constructors
    public Employee(String employeeID, String lastName, String firstName, int isManager, String password){
        setEmployeeID(employeeID);
        setLastName(lastName);
        setFirstName(firstName);
        setIsManager(isManager);
        setPassword(password);
    }
    public Employee() {
        // No arg constructor for opencsv
    }
    // Getters
    public String getEmployeeID() {return employeeID;}
    public String getLastName() {return lastName;}
    public String getFirstName() {return firstName;}
    public int getIsManager() {return isManager;}
    public String getPassword() {return password;}
    // Setters
    public void setEmployeeID(String employeeID) {this.employeeID = employeeID;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setIsManager(int isManager) {this.isManager = isManager;}
    public void setPassword(String password) {this.password = password;}
}

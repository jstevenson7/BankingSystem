package edu.missouriwestern.csc406.bankingsystem;

public class Customer {
    // Data fields
    private String SSN;
    private String Address;
    private String City;
    private String State;
    private int Zip;
    private String firstName;
    private String lastName;
    private String atmNumber;
    private int atmPin;
    private int creditCardPin;
    private String customerID;
    // Constructor
    public Customer(String SSN, String Address, String City, String State, int Zip, String firstName, String lastName,
                    String atmNumber, int atmPin, int creditCardPin, String customerID) {
        setSSN(SSN);
        setAddress(Address);
        setCity(City);
        setState(State);
        setZip(Zip);
        setFirstName(firstName);
        setLastName(lastName);
        setAtmNumber(atmNumber);
        setAtmPin(atmPin);
        setCreditCardPin(creditCardPin);
        setCustomerID(customerID);
    }
    public Customer() {
        // no arg constructor for opencsv
    }
    // Getters
    public String getSSN() {return SSN;}
    public String getAddress() {return Address;}
    public String getCity() {return City;}
    public String getState() {return State;}
    public int getZip() {return Zip;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getAtmNumber() {return atmNumber;}
    public int getAtmPin() {return atmPin;}
    public int getCreditCardPin() {return creditCardPin;}
    public String getCustomerID() {return customerID;}
    // Setters
    public void setSSN(String SSN) {this.SSN = SSN;}
    public void setAddress(String address) {Address = address;}
    public void setCity(String city) {City = city;}
    public void setState(String state) {State = state;}
    public void setZip(int zip) {Zip = zip;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setAtmNumber(String atmNumber) {this.atmNumber = atmNumber;}
    public void setAtmPin(int atmPin) {this.atmPin = atmPin;}
    public void setCreditCardPin(int creditCardPin) {this.creditCardPin = creditCardPin;}
    public void setCustomerID(String customerID) {this.customerID = customerID;}


    @Override
    public String toString() {
        return "Customer{" +
                "SSN='" + SSN + '\'' +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", Zip=" + Zip +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", atmNumber='" + atmNumber + '\'' +
                ", atmPin=" + atmPin +
                ", creditCardPin=" + creditCardPin +
                ", customerID='" + customerID + '\'' +
                '}';
    }
}



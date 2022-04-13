package edu.missouriwestern.csc406.bankingsystem;

public class customerPOJO {
    // Data fields
    private String SSN;
    private String Address;
    private String State;
    private int Zip;
    private String firstName;
    private String lastName;
    private String customerID;
    private double currentBalance;
    private double interestRates;
    private String datePaymentDue;
    private String dateNotifiedOfPayment;
    // Constructor
    public customerPOJO(String SSN, String Address, String State, int Zip, String firstName, String lastName, String customerID,
                        double currentBalance, double interestRates, String datePaymentDue, String dateNotifiedOfPayment) {
        setSSN(SSN);
        setAddress(Address);
        setState(State);
        setZip(Zip);
        setFirstName(firstName);
        setLastName(lastName);
        setCustomerID(customerID);
        setCurrentBalance(currentBalance);
        setInterestRates(interestRates);
        setDatePaymentDue(datePaymentDue);
        setDateNotifiedOfPayment(dateNotifiedOfPayment);
    }
    // Getters
    public String getSSN() {return SSN;}
    public String getAddress() {return Address;}
    public String getState() {return State;}
    public int getZip() {return Zip;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getCustomerID() {return customerID;}
    public double getCurrentBalance() {return currentBalance;}
    public double getInterestRates() {return interestRates;}
    public String getDatePaymentDue() {return datePaymentDue;}
    public String getDateNotifiedOfPayment() {return dateNotifiedOfPayment;}
    // Setters
    public void setSSN(String SSN) {this.SSN = SSN;}
    public void setAddress(String address) {Address = address;}
    public void setState(String state) {State = state;}
    public void setZip(int zip) {Zip = zip;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setCustomerID(String customerID) {this.customerID = customerID;}
    public void setCurrentBalance(double currentBalance) {this.currentBalance = currentBalance;}
    public void setInterestRates(double interestRates) {this.interestRates = interestRates;}
    public void setDatePaymentDue(String datePaymentDue) {this.datePaymentDue = datePaymentDue;}
    public void setDateNotifiedOfPayment(String dateNotifiedOfPayment) {this.dateNotifiedOfPayment = dateNotifiedOfPayment;}
}

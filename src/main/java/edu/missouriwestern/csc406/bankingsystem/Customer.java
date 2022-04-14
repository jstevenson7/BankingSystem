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
    private String customerID;
    private double currentBalance;
    private double interestRates;
    private String datePaymentDue;
    private String dateNotifiedOfPayment;
    private double currentPaymentDue;
    private String dateLastPayementMade;
    private int missedPaymentFlag;
    private String loanType;
    private double creditLimit;
    private int monthsLeft;
    // Constructor
    public Customer(String SSN, String Address, String City, String State, int Zip, String firstName, String lastName, String customerID,
                    double currentBalance, double interestRates, String datePaymentDue, String dateNotifiedOfPayment, double currentPaymentDue,
                    String dateLastPayementMade, int missedPaymentFlag, String loanType, double creditLimit, int monthsLeft) {
        setSSN(SSN);
        setAddress(Address);
        setCity(City);
        setState(State);
        setZip(Zip);
        setFirstName(firstName);
        setLastName(lastName);
        setCustomerID(customerID);
        setCurrentBalance(currentBalance);
        setInterestRates(interestRates);
        setDatePaymentDue(datePaymentDue);
        setDateNotifiedOfPayment(dateNotifiedOfPayment);
        setCurrentPaymentDue(currentPaymentDue);
        setDateLastPayementMade(dateLastPayementMade);
        setMissedPaymentFlag(missedPaymentFlag);
        setLoanType(loanType);
        setCreditLimit(creditLimit);
        setMonthsLeft(monthsLeft);
    }
    // Getters
    public String getSSN() {return SSN;}
    public String getAddress() {return Address;}
    public String getCity() {return City;}
    public String getState() {return State;}
    public int getZip() {return Zip;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getCustomerID() {return customerID;}
    public double getCurrentBalance() {return currentBalance;}
    public double getInterestRates() {return interestRates;}
    public String getDatePaymentDue() {return datePaymentDue;}
    public String getDateNotifiedOfPayment() {return dateNotifiedOfPayment;}
    public double getCurrentPaymentDue() {return currentPaymentDue;}
    public String getDateLastPayementMade() {return dateLastPayementMade;}
    public int getMissedPaymentFlag() {return missedPaymentFlag;}
    public String getLoanType() {return loanType;}
    public double getCreditLimit() {return creditLimit;}
    public int getMonthsLeft() {return monthsLeft;}
    // Setters
    public void setSSN(String SSN) {this.SSN = SSN;}
    public void setAddress(String address) {Address = address;}
    public void setCity(String city) {City = city;}
    public void setState(String state) {State = state;}
    public void setZip(int zip) {Zip = zip;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setCustomerID(String customerID) {this.customerID = customerID;}
    public void setCurrentBalance(double currentBalance) {this.currentBalance = currentBalance;}
    public void setInterestRates(double interestRates) {this.interestRates = interestRates;}
    public void setDatePaymentDue(String datePaymentDue) {this.datePaymentDue = datePaymentDue;}
    public void setDateNotifiedOfPayment(String dateNotifiedOfPayment) {this.dateNotifiedOfPayment = dateNotifiedOfPayment;}
    public void setCurrentPaymentDue(double currentPaymentDue) {this.currentPaymentDue = currentPaymentDue;}
    public void setDateLastPayementMade(String dateLastPayementMade) {this.dateLastPayementMade = dateLastPayementMade;}
    public void setMissedPaymentFlag(int missedPaymentFlag) {this.missedPaymentFlag = missedPaymentFlag;}
    public void setLoanType(String loanType) {this.loanType = loanType;}
    public void setCreditLimit(double creditLimit) {this.creditLimit = creditLimit;}
    public void setMonthsLeft(int monthsLeft) {this.monthsLeft = monthsLeft;}
}

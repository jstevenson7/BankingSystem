package edu.missouriwestern.csc406.bankingsystem;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class z_Manager_ReviewLoans {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<Customer> customers;
    ArrayList<Loans> loans;
    ArrayList<CreditCards> creditCards;

    /*--- CUSTOMERS TABLE ---*/
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private Button loadCustomersButton;
    @FXML
    private TableColumn<Customer, String> ssn;
    @FXML
    private TableColumn<Customer,String> address;
    @FXML
    private TableColumn<Customer,String> city;
    @FXML
    private TableColumn<Customer,String> state;
    @FXML
    private TableColumn<Customer,Integer> zip;
    @FXML
    private TableColumn<Customer,String> firstName;
    @FXML
    private TableColumn<Customer,String> lastName;
    @FXML
    private TableColumn<Customer,String> atmNumber;
    @FXML
    private TableColumn<Customer,Integer> atmPin;
    @FXML
    private TableColumn<Customer,Integer> creditCardPin;

    public void readToCustomersTable(ActionEvent event) {
        // Create reader to the customers.csv file
        try {
            ArrayList<Customer> read = DB.readCustomersCSV();
            customers = read;
            customerTable.setItems(FXCollections.observableArrayList(customers));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void toReviewAllCust(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        reviewAllCustAnchor.setVisible(true);
        loanAnchor.setVisible(false);
        ccAnchor.setVisible(false);
    } //End of toTeller.

    @FXML
    private AnchorPane reviewAllCustAnchor;
    @FXML
    private Button allCustomersButton;



    public void initialize() {
        ssn.setCellValueFactory(new PropertyValueFactory<Customer, String>("SSN"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("Address"));
        city.setCellValueFactory(new PropertyValueFactory<Customer, String>("City"));
        state.setCellValueFactory(new PropertyValueFactory<Customer, String>("State"));
        zip.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("Zip"));
        firstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        atmNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("atmNumber"));
        atmPin.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("atmPin"));
        creditCardPin.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("creditCardPin"));

        returnTellerButton.setOnMouseEntered(event -> returnTellerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnTellerButton.setOnMouseExited(event -> returnTellerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        allCustomersButton.setOnMouseEntered(event -> allCustomersButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        allCustomersButton.setOnMouseExited(event -> allCustomersButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        loanButton.setOnMouseEntered(event -> loanButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        loanButton.setOnMouseExited(event -> loanButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        ccButton.setOnMouseEntered(event -> ccButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        ccButton.setOnMouseExited(event -> ccButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        loanID.setCellValueFactory(new PropertyValueFactory<Loans, String>("LoanID"));
        loanbalance.setCellValueFactory(new PropertyValueFactory<Loans, Double>("balance"));
        loaninterestRate.setCellValueFactory(new PropertyValueFactory<Loans, Double>("interestRate"));
        loanstartDate.setCellValueFactory(new PropertyValueFactory<Loans, String>("startDate"));
        loanendDate.setCellValueFactory(new PropertyValueFactory<Loans, String>("endDate"));
        lSSN.setCellValueFactory(new PropertyValueFactory<Loans, String>("SSN"));
        lmonthlyPayment.setCellValueFactory(new PropertyValueFactory<Loans, Double>("monthlyPayment"));

        ccNum.setCellValueFactory(new PropertyValueFactory<CreditCards, String>("creditcardnumber"));
        ccLimit.setCellValueFactory(new PropertyValueFactory<CreditCards, Double>("creditcardlimit"));
        ccSSN.setCellValueFactory(new PropertyValueFactory<CreditCards, String>("SSN"));
        ccBalance.setCellValueFactory(new PropertyValueFactory<CreditCards, Double>("balance"));

    }


    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button returnTellerButton;

    /* --- NAV FUNCTIONS --- */
    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    @FXML
    private AnchorPane loanAnchor;
    @FXML
    private Button loanButton;
    @FXML
    private Button loadLoansButton;
    @FXML
    private TableView<Loans> loanTable;
    @FXML
    private  TableColumn<Loans,String> loanID;
    @FXML
    private  TableColumn<Loans,Double> loanbalance;
    @FXML
    private  TableColumn<Loans,Double> loaninterestRate;
    @FXML
    private  TableColumn<Loans,String> loanstartDate;
    @FXML
    private  TableColumn<Loans,String> loanendDate;
    @FXML
    private  TableColumn<Loans,String> lSSN;
    @FXML
    private  TableColumn<Loans,Double> lmonthlyPayment;

    public void toLoans(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        reviewAllCustAnchor.setVisible(false);
        loanAnchor.setVisible(true);
        ccAnchor.setVisible(false);
    } //End of toTeller.


    public void readToLoanTable(ActionEvent event) {
        // Create reader to the customers.csv file
        try {
            ArrayList<Loans> read = DB.readLoansCSV();
            loans = read;
            loanTable.setItems(FXCollections.observableArrayList(loans));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @FXML
    private AnchorPane ccAnchor;
    @FXML
    private Button ccButton;
    @FXML
    private Button loadCCButton;
    @FXML
    private TableView<CreditCards> ccTable;
    @FXML
    private  TableColumn<CreditCards,String> ccNum;
    @FXML
    private  TableColumn<CreditCards,Double> ccLimit;
    @FXML
    private  TableColumn<CreditCards,String> ccSSN;
    @FXML
    private  TableColumn<CreditCards,Double> ccBalance;

    public void toCC(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        reviewAllCustAnchor.setVisible(false);
        loanAnchor.setVisible(false);
        ccAnchor.setVisible(true);
    } //End of toTeller.

    public void readToCCTable(ActionEvent event) {
        // Create reader to the customers.csv file
        try {
            ArrayList<CreditCards> read = DB.readCreditCardCSV();
            creditCards = read;
            ccTable.setItems(FXCollections.observableArrayList(creditCards));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

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


public class z_Teller_ReviewCustomer {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<Customer> customers;
    ArrayList<Checking> checking;
    ArrayList<Savings> savings;
    ArrayList<CD> cd;
    ArrayList<Transaction> transactions;

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
        reviewCheckingAcctsAnchor.setVisible(false);
        reviewSavingsAcctsAnchor.setVisible(false);
        reviewCDAcctsAnchor.setVisible(false);
        transactionsAnchor.setVisible(false);
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
        reviewSavingsButton.setOnMouseEntered(event -> reviewSavingsButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        reviewSavingsButton.setOnMouseExited(event -> reviewSavingsButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        reviewCheckingButton.setOnMouseEntered(event -> reviewCheckingButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        reviewCheckingButton.setOnMouseExited(event -> reviewCheckingButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        reviewCDButton.setOnMouseEntered(event -> reviewCDButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        reviewCDButton.setOnMouseExited(event -> reviewCDButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        transactionsButton.setOnMouseEntered(event -> transactionsButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        transactionsButton.setOnMouseExited(event -> transactionsButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));


        checkingAcctNum.setCellValueFactory(new PropertyValueFactory<Checking, String>("checkingAcctNum"));
        checkingBalance.setCellValueFactory(new PropertyValueFactory<Checking, Double>("balance"));
        checkingAccountType.setCellValueFactory(new PropertyValueFactory<Checking, Integer>("accountType"));
        checkingSSN.setCellValueFactory(new PropertyValueFactory<Checking, String>("SSN"));
        overdraftAccountNumber.setCellValueFactory(new PropertyValueFactory<Checking, String>("overdraftAccountNumber"));

        savingsAcctNum.setCellValueFactory(new PropertyValueFactory<Savings, String>("savingsAcctNum"));
        savingsBalance.setCellValueFactory(new PropertyValueFactory<Savings, Double>("balance"));
        savingsInterestRate.setCellValueFactory(new PropertyValueFactory<Savings, Double>("interestRate"));
        savingsDate.setCellValueFactory(new PropertyValueFactory<Savings, String>("startDate"));
        savingsSSN.setCellValueFactory(new PropertyValueFactory<Savings, String>("SSN"));

        cdAcctNum.setCellValueFactory(new PropertyValueFactory<CD, String>("cdAcctNum"));
        cdBalance.setCellValueFactory(new PropertyValueFactory<CD, Double>("balance"));
        cdInterestRate.setCellValueFactory(new PropertyValueFactory<CD, Double>("interestRate"));
        cdStartDate.setCellValueFactory(new PropertyValueFactory<CD, String>("startDate"));
        cdEndDate.setCellValueFactory(new PropertyValueFactory<CD, String>("endDate"));
        cdSSN.setCellValueFactory(new PropertyValueFactory<CD, String>("SSN"));

        transactionNum.setCellValueFactory(new PropertyValueFactory<Transaction,String>("transactionNum"));
        transactionsSSN.setCellValueFactory(new PropertyValueFactory<Transaction,String>("SSN"));
        transactionsAcctType.setCellValueFactory(new PropertyValueFactory<Transaction,String>("accountType"));
        transactionsAcctNum.setCellValueFactory(new PropertyValueFactory<Transaction,String>("accountNum"));
        transactionsAmount.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("amount"));
        transactionsMemo.setCellValueFactory(new PropertyValueFactory<Transaction,String>("memo"));
        transactionsDate.setCellValueFactory(new PropertyValueFactory<Transaction,String>("date"));
        transactionsCheckNum.setCellValueFactory(new PropertyValueFactory<Transaction,String>("checkNum"));

    }


    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button returnTellerButton;

    /* --- NAV FUNCTIONS --- */
    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    /*--- CHECKING TABLE ---*/
    public void toChecking(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        reviewAllCustAnchor.setVisible(false);
        reviewCheckingAcctsAnchor.setVisible(true);
        reviewSavingsAcctsAnchor.setVisible(false);
        reviewCDAcctsAnchor.setVisible(false);
        transactionsAnchor.setVisible(false);
    } //End of toTeller.

    @FXML
    private AnchorPane reviewCheckingAcctsAnchor;
    @FXML
    private Button reviewCheckingButton;
    @FXML
    private Button c_LoadAcctsButton;

    @FXML
    private TableView<Checking> checkingTable;
    @FXML
    private TableColumn<Checking, String> checkingAcctNum;
    @FXML
    private TableColumn<Checking, Double> checkingBalance;
    @FXML
    private TableColumn<Checking, Integer> checkingAccountType;
    @FXML
    private TableColumn<Checking, String> checkingSSN;
    @FXML
    private TableColumn<Checking, String> overdraftAccountNumber;



    public void readToCheckingTable(ActionEvent event) {
        // Create reader to the customers.csv file
        try {
            ArrayList<Checking> read = DB.readCheckingCSV();
            checking  = read;
            checkingTable.setItems(FXCollections.observableArrayList(checking));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*--- SAVINGS TABLE ---*/
    public void toSavings(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        reviewAllCustAnchor.setVisible(false);
        reviewCheckingAcctsAnchor.setVisible(false);
        reviewSavingsAcctsAnchor.setVisible(true);
        reviewCDAcctsAnchor.setVisible(false);
        transactionsAnchor.setVisible(false);
    } //End of toTeller.

    @FXML
    private AnchorPane reviewSavingsAcctsAnchor;
    @FXML
    private Button reviewSavingsButton;
    @FXML
    private Button s_LoadAcctsButton;

    @FXML
    private TableView<Savings> savingsTable;
    @FXML
    private TableColumn<Savings, String> savingsAcctNum;
    @FXML
    private TableColumn<Savings, Double> savingsBalance;
    @FXML
    private TableColumn<Savings, Double> savingsInterestRate;
    @FXML
    private TableColumn<Savings, String> savingsDate;
    @FXML
    private TableColumn<Savings, String> savingsSSN;


    public void readToSavingsTable(ActionEvent event) {
        // Create reader to the customers.csv file
        try {
            ArrayList<Savings> read = DB.readSavingsCSV();
            savings  = read;
            savingsTable.setItems(FXCollections.observableArrayList(savings));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*--- CD TABLE ---*/
    public void toCD(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        reviewAllCustAnchor.setVisible(false);
        reviewCheckingAcctsAnchor.setVisible(false);
        reviewSavingsAcctsAnchor.setVisible(false);
        reviewCDAcctsAnchor.setVisible(true);
        transactionsAnchor.setVisible(false);
    } //End of toTeller.

    @FXML
    private AnchorPane reviewCDAcctsAnchor;
    @FXML
    private Button reviewCDButton;
    @FXML
    private Button cd_LoadAcctsButton;

    @FXML
    private TableView<CD> cdTable;
    @FXML
    private TableColumn<CD, String> cdAcctNum;
    @FXML
    private TableColumn<CD, Double> cdBalance;
    @FXML
    private TableColumn<CD, Double> cdInterestRate;
    @FXML
    private TableColumn<CD, String> cdStartDate;
    @FXML
    private TableColumn<CD, String> cdEndDate;
    @FXML
    private TableColumn<CD, String> cdSSN;


    public void readToCDTable(ActionEvent event) {
        // Create reader to the customers.csv file
        try {
            ArrayList<CD> read = DB.readCDCSV();
            cd  = read;
            cdTable.setItems(FXCollections.observableArrayList(cd));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*--- TRANSACTIONS TABLE ---*/
    public void toTransactions(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        reviewAllCustAnchor.setVisible(false);
        reviewCheckingAcctsAnchor.setVisible(false);
        reviewSavingsAcctsAnchor.setVisible(false);
        reviewCDAcctsAnchor.setVisible(false);
        transactionsAnchor.setVisible(true);

    } //End of toTeller.

    @FXML
    private AnchorPane transactionsAnchor;
    @FXML
    private Button transactionsButton;
    @FXML
    private Button loadTransactionsButton;
    @FXML
    private TextField transactionsSSNTF;
    @FXML
    private Label transactionsLabel;

    @FXML
    private TableView<Transaction> transactionsTable;
    @FXML
    private TableColumn<Transaction, String> transactionNum;
    @FXML
    private TableColumn<Transaction, String> transactionsSSN;
    @FXML
    private TableColumn<Transaction, String> transactionsAcctType;
    @FXML
    private TableColumn<Transaction, String> transactionsAcctNum;
    @FXML
    private TableColumn<Transaction, Double> transactionsAmount;
    @FXML
    private TableColumn<Transaction, String> transactionsMemo;
    @FXML
    private TableColumn<Transaction, String> transactionsDate;
    @FXML
    private TableColumn<Transaction, String> transactionsCheckNum;

    public void readToTransactionsTable(ActionEvent event) {
        // Create reader to the customers.csv file
        try {
            if(transactionsSSNTF.getText().isBlank()) {
                transactionsLabel.setText("SSN field is required!");
                transactionsLabel.setTextFill(Color.RED);
            } else {
                int found = 0;
                ArrayList<Customer> customers = DB.readCustomersCSV();
                for (int i = 0; i < customers.size(); i++) {
                    if (transactionsSSNTF.getText().equals(customers.get(i).getSSN())) {
                        found++;
                    }
                }
                if (found == 0) {
                    transactionsLabel.setText("Customer with SSN '" + transactionsSSNTF.getText() + "' does not exist.");
                    transactionsLabel.setTextFill(Color.RED);
                } else {
                    ArrayList<Transaction> read = DB.readCustomerTransactions(transactionsSSNTF.getText());
                    transactions = read;
                    transactionsTable.setItems(FXCollections.observableArrayList(transactions));
                    transactionsLabel.setText("Success!");
                    transactionsLabel.setTextFill(Color.GREEN);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

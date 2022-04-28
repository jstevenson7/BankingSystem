package edu.missouriwestern.csc406.bankingsystem;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.ColumnPositionMappingStrategyBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class z_Teller_ReviewCustomer {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<Customer> customers;
    ArrayList<Checking> checking;
    ArrayList<Savings> savings;
    ArrayList<CD> cd;

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
        cdWithdrawDate.setCellValueFactory(new PropertyValueFactory<CD, String>("withdrawDate"));
        cdSSN.setCellValueFactory(new PropertyValueFactory<CD, String>("SSN"));
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
    private TableColumn<CD, String> cdWithdrawDate;
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


}

package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class z_Teller_ManageAccounts {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button returnTellerButton;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button deleteAccountButton;


    /* --- CREATE ANCHOR DATA --- */
    @FXML
    private AnchorPane createAnchor;
    @FXML
    private ComboBox<String> cAccountBox;
    @FXML
    private TextField cSSNText;
    @FXML
    private Button cCreateButton;
    @FXML
    private Label cMessage;
    @FXML
    private TextField cAmount;

    /* --- DELETE ANCHOR DATA --- */
    @FXML
    private AnchorPane deleteAnchor;
    @FXML
    private Button dDeleteButton;
    @FXML
    private TextField dSSNText;
    @FXML
    private ComboBox<String> dDeleteOptions;
    @FXML
    private Label dMessage;

    /* --- MAIN ANCHOR DATA --- */
    @FXML
    private AnchorPane mainAnchor;

    /* --- NAV FUNCTIONS --- */
    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    public void displayCreate(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        deleteAnchor.setVisible(false);
        createAnchor.setVisible(true);
    } //End of displayCreate.

    public void displayDelete(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        createAnchor.setVisible(false);
        deleteAnchor.setVisible(true);
    } //End of displayDelete.

    /* --- Logic Functions --- */
    public void createAccount(ActionEvent event) throws IOException, ParseException {
        // verify SSN exists in customers

        if (cSSNText.getText().isBlank() || cAccountBox.getValue().isBlank() ||
                cAmount.getText().isBlank()) {
            cMessage.setText("All fields required!");
            cMessage.setTextFill(Color.RED);
            cMessage.setVisible(true);
        } else {

            if (DB.verifyCustomerSSN(cSSNText.getText(), DB.readCustomersCSV())) {
                if (cAccountBox.getValue().equals("Checking - TMB") || cAccountBox.getValue().equals("Checking - Gold/Diamond")) {
                    // if checking is selected
                    // read in current checking accounts
                    ArrayList<Checking> checkings = DB.readCheckingCSV();
                    // if customer already has checking
                    if (!DB.verifyCheckingSSN(cSSNText.getText(), checkings)) {
                        // create new checking account, check type of checking to create
                        if (cAccountBox.getValue().equals("Checking - Gold/Diamond")) {
                            if (Double.parseDouble(cAmount.getText()) >= 1000) {
                                Checking checking = new Checking(DB.generateAccountNumber(), Double.parseDouble(cAmount.getText()), 1, cSSNText.getText());
                                // add to arraylist
                                checkings.add(checking);
                                // write to database
                                DB.writeCheckingCSV(checkings);
                                cMessage.setVisible(true);
                                cMessage.setText("Success!");
                                cMessage.setTextFill(Color.GREEN);
                                cSSNText.clear();
                                cAmount.clear();
                            } else {
                                cMessage.setVisible(true);
                                cMessage.setText("Must be at least $1000");
                                cMessage.setTextFill(Color.RED);
                            }
                        } else {
                            if (Double.parseDouble(cAmount.getText()) < 1000) {
                                Checking checking = new Checking(DB.generateAccountNumber(), Double.parseDouble(cAmount.getText()), 0, cSSNText.getText());
                                // add to arraylist
                                checkings.add(checking);
                                // write to database
                                DB.writeCheckingCSV(checkings);
                                cMessage.setVisible(true);
                                cMessage.setText("Success!");
                                cMessage.setTextFill(Color.GREEN);
                                cSSNText.clear();
                                cAmount.clear();
                            } else {
                                cMessage.setVisible(true);
                                cMessage.setText("Must be less then $1000");
                                cMessage.setTextFill(Color.RED);
                            }
                        }
                    } else {
                        cMessage.setVisible(true);
                        cMessage.setText("Checking already exist!");
                        cMessage.setTextFill(Color.RED);
                    }
                } // end of if Checking
                else if (cAccountBox.getValue().equals("Savings - Simple")) {
                    // read in current savings accounts
                    ArrayList<Savings> savings = DB.readSavingsCSV();
                    // check to see if savings already exists
                    if (!DB.verifySavingsSSN(cSSNText.getText(), savings)) {
                        Savings savings1 = new Savings(DB.generateAccountNumber(), Double.parseDouble(cAmount.getText()), 0, cSSNText.getText());
                        savings.add(savings1);
                        DB.writeSavingsCSV(savings);
                        cMessage.setVisible(true);
                        cMessage.setText("Success!");
                        cMessage.setTextFill(Color.GREEN);
                        cSSNText.clear();
                        cAmount.clear();
                    } else {
                        cMessage.setVisible(true);
                        cMessage.setText("Savings already exist!");
                        cMessage.setTextFill(Color.RED);
                    }
                } // end of if Savings
                else if (cAccountBox.getValue().equals("CD")) {
                    ArrayList<CD> cds = DB.readCDCSV();
                    if (!DB.verifyCDSSN(cSSNText.getText(), cds)) {
                        CD cd = new CD(DB.generateAccountNumber(), Double.parseDouble(cAmount.getText()), .1, DB.getTodayDate(), DB.getEndDate(), cSSNText.getText());
                        cds.add(cd);
                        DB.writeCDCSV(cds);
                        cMessage.setVisible(true);
                        cMessage.setText("Success!");
                        cMessage.setTextFill(Color.GREEN);
                        cSSNText.clear();
                        cAmount.clear();
                    } else {
                        cMessage.setVisible(true);
                        cMessage.setText("CD already exist!");
                        cMessage.setTextFill(Color.RED);
                    }
                }
            } else {
                // If SSN is not found
                cMessage.setText("SSN not found!");
                cMessage.setTextFill(Color.RED);
            }
        }
    }

    public void deleteingCustomerAccounts(ActionEvent event) throws IOException {

        //read in customers from the DB
        ArrayList<Customer> customers = DB.readCustomersCSV();
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        ArrayList<Savings> savings = DB.readSavingsCSV();
        ArrayList<Loans> loans = DB.readLoansCSV();

    }


    /* --- INITIALIZE --- */
    @FXML
    private void initialize(){
        // Customer Button
        returnTellerButton.setOnMouseEntered(event -> returnTellerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnTellerButton.setOnMouseExited(event -> returnTellerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        createAccountButton.setOnMouseEntered(event -> createAccountButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        createAccountButton.setOnMouseExited(event -> createAccountButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        deleteAccountButton.setOnMouseEntered(event -> deleteAccountButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        deleteAccountButton.setOnMouseExited(event -> deleteAccountButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        cAccountBox.getItems().addAll("Checking - TMB", "Checking - Gold/Diamond", "Savings - Simple", "CD");
        cAccountBox.setVisibleRowCount(5);
        dDeleteOptions.getItems().addAll("Delete Checking", "Delete Savings", "Delete Loans", "Delete Checking and Savings", "Delete Checking, Savings, and Loans","Delete Checking, Savings, and Customer", "Delete Checking, Savings, Loans, and Customer");
        dDeleteOptions.setVisibleRowCount(4);
    }
}

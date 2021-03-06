package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class z_Teller_MoreOptions {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button returnTellerButton;
    @FXML
    private Button stopPaymentButton;
    @FXML
    private Button overdraftButton;

    /* --- MAIN ANCHOR DATA --- */
    @FXML
    private AnchorPane mainAnchor;

    /* --- STOP PAYMENT ANCHOR --- */
    @FXML
    private AnchorPane stopPaymentAnchor;
    @FXML
    private Button s_stopPaymentButton;
    @FXML
    private TextField s_ssnTF;
    @FXML
    private TextField s_checkNumTF;
    @FXML
    private Label s_message;
    @FXML
    private TextField s_processSSNTF;
    @FXML
    private TextField s_processsCheckNumTF;
    @FXML
    private Button s_processPayment;


    /* --- OVERDRAFT PROTECTION ANCHOR --- */
    @FXML
    private AnchorPane overdraftAnchor;
    @FXML
    private Button o_overdraftButton;
    @FXML
    private TextField o_ssnTF;
    @FXML
    private TextField o_savingsAcctNumTF;
    @FXML
    private TextField o_checkingAcctNumTF;
    @FXML
    private Label o_message;

    /* --- NAV FUNCTIONS --- */
    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    public void displayStopPayment(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        stopPaymentAnchor.setVisible(true);
        overdraftAnchor.setVisible(false);
    } //End of displayCreate.

    public void displayAutoPay(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        stopPaymentAnchor.setVisible(false);
        overdraftAnchor.setVisible(false);
    } //End of displayDelete.

    public void displayOverdraft(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        stopPaymentAnchor.setVisible(false);
        overdraftAnchor.setVisible(true);
    } //End of displayDelete.

    /* --- Logic Functions --- */
    public void setOverdraft() throws IOException {
        // read in current checking accounts
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        ArrayList<Savings> savings = DB.readSavingsCSV();
        // search for checking account with matching SSN from customer
        Checking checking = DB.searchChecking(o_ssnTF.getText(), checkings);
        Savings savings1 = DB.searchSavings(o_ssnTF.getText(), savings);
        // if found
        if (checking!=null && savings1!=null) {
            // check to make sure the checking account number matches the one entered
            if (checking.getCheckingAcctNum().equals(o_checkingAcctNumTF.getText())) {
                if (savings1.getSavingsAcctNum().equals(o_savingsAcctNumTF.getText())) {
                    // set the overdraft account number and write
                    checking.setOverdraftAccountNumber(o_savingsAcctNumTF.getText());
                    DB.writeCheckingCSV(checkings);
                    o_message.setText("Success!");
                    o_message.setTextFill(Color.GREEN);
                    o_ssnTF.clear();
                    o_savingsAcctNumTF.clear();
                    o_checkingAcctNumTF.clear();
                } else {
                    o_message.setText("Savings Account not found!");
                    o_message.setTextFill(Color.RED);
                }
            } else {
                o_message.setText("Checking Account not found!");
                o_message.setTextFill(Color.RED);
            }
        } else {
            o_message.setText("Customer not found!");
            o_message.setTextFill(Color.RED);
        }
    }
    public void stopPayment(ActionEvent event) throws IOException {
        // Read in current unprocessed checks
        ArrayList<Check> unproccessedChecks = DB.readUnprocessedCheckCSV();
        // Read in checking accounts
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        // find the checking account
        Checking checking = DB.searchChecking(s_ssnTF.getText(), checkings);

        if (checking!=null) {
            // find the check
            Check check = DB.searchChecks(s_checkNumTF.getText(), checking.getCheckingAcctNum(), unproccessedChecks);
            if (check!=null) {
                if (checking.getBalance() >= 15.0) {
                    // remove the check from the arraylist
                    unproccessedChecks.remove(check);
                    // write the change to the database
                    DB.writeUnprocessedCheckCSV(unproccessedChecks);
                    // charge fee
                    checking.deduct(15.0);
                    DB.writeCheckingCSV(checkings);
                    // show success
                    s_message.setText("Success!");
                    s_message.setTextFill(Color.GREEN);
                    s_ssnTF.clear();
                    s_checkNumTF.clear();
                } else {
                    s_message.setText("Insufficient funds!");
                    s_message.setTextFill(Color.RED);
                }
            } else {
                s_message.setText("Unknown check!");
                s_message.setTextFill(Color.RED);
            }
        } else {
            s_message.setText("Unknown account!");
            s_message.setTextFill(Color.RED);
        }
    }
    public void processPayment(ActionEvent event) throws IOException {
        // Read in current unprocessed and processed checks
        ArrayList<Check> unprocessedChecks = DB.readUnprocessedCheckCSV();
        ArrayList<Check> processedChecks = DB.readProcessedCheckCSV();
        // Read in checking accounts
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        // Find the checking account
        Checking checking = DB.searchChecking(s_processSSNTF.getText(), checkings);

        if (checking!=null) {
            Check check = DB.searchChecks(s_processsCheckNumTF.getText(), checking.getCheckingAcctNum(),unprocessedChecks);
            if (check!=null) {
                checking.withdraw(check.getAmount());
                processedChecks.add(check);
                unprocessedChecks.remove(check);
                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                Transaction transaction = new Transaction(DB.generateTransactionNumber(), checking.getSSN(),
                        "Checking", checking.getOverdraftAccountNumber(), (0-check.getAmount()), check.getDescription(),
                        check.getDate(), check.getCheckNum());
                transactions.add(transaction);
                DB.writeTransactionsCSV(transactions);
                DB.writeUnprocessedCheckCSV(unprocessedChecks);
                DB.writeProcessedCheckCSV(processedChecks);
                DB.writeCheckingCSV(checkings);
                s_message.setText("Success!");
                s_message.setTextFill(Color.GREEN);
                s_processSSNTF.clear();
                s_processsCheckNumTF.clear();
            } else {
                s_message.setText("Unknown check!");
                s_message.setTextFill(Color.RED);
            }
        } else {
            s_message.setText("Unknown account!");
            s_message.setTextFill(Color.RED);
        }
    }

    @FXML
    private void initialize() {
        // Customer Button
        returnTellerButton.setOnMouseEntered(event -> returnTellerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnTellerButton.setOnMouseExited(event -> returnTellerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        stopPaymentButton.setOnMouseEntered(event -> stopPaymentButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        stopPaymentButton.setOnMouseExited(event -> stopPaymentButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        overdraftButton.setOnMouseEntered(event -> overdraftButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        overdraftButton.setOnMouseExited(event -> overdraftButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
    }

}

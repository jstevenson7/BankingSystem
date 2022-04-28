package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class z_Customer_ATM {

    /* --- STAGE/SCENE DATA --- */
    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button returnCustomerButton;

    /* --- SCENE ATTRIBUTES --- */
    @FXML
    private TextField cardNumText;
    @FXML
    private PasswordField pinText;
    @FXML
    private Label atmLabel;
    @FXML
    private TextField withdrawAmtText;
    @FXML
    private Pane receiptButtonPane;
    @FXML
    private DatePicker datePicker;
    String fDatePicker;
    int overdraft;

    /* --- NAV METHODS --- */
    public void toCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        renewScene(event);
    } //End of toCustomer.

    /* --- INITIALIZE --- */
    @FXML
    private void initialize(){
        // Return Customer Screen Button.
        returnCustomerButton.setOnMouseEntered(event -> returnCustomerButton.setStyle("-fx-background-color: #ffffff"));
        returnCustomerButton.setOnMouseExited(event -> returnCustomerButton.setStyle("-fx-background-color:  #000000"));

        viewReceiptButton.setOnMouseEntered(event -> viewReceiptButton.setStyle("-fx-background-color: #787878; -fx-opacity: 0.3;"));
        viewReceiptButton.setOnMouseExited(event -> viewReceiptButton.setStyle("-fx-background-color: #787878; -fx-opacity: 0;"));

        r_CloseButton.setOnMouseEntered(event -> r_CloseButton.setStyle("-fx-background-color: #f7bebe; -fx-border-color: grey"));
        r_CloseButton.setOnMouseExited(event -> r_CloseButton.setStyle("-fx-background-color:  #f5dada; -fx-border-color: grey"));

        rOV_CloseButton.setOnMouseEntered(event -> rOV_CloseButton.setStyle("-fx-background-color: #f7bebe; -fx-border-color: grey"));
        rOV_CloseButton.setOnMouseExited(event -> rOV_CloseButton.setStyle("-fx-background-color:  #f5dada; -fx-border-color: grey"));

        cardNumText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        pinText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        withdrawAmtText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        datePicker.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
    } //End of initialize.

    public void viewReceipt(ActionEvent event) throws IOException {
        if(overdraft == 0) {
            receiptAnchor.setVisible(true);
        } else {
            receiptOverdraftAnchor.setVisible(true);
        }
    } //End of viewReceipt.

    public void closeReceipt(ActionEvent event) throws IOException {
        renewScene(event);
    } //End of viewReceipt.

    public void renewScene(ActionEvent event) throws IOException {
        atmLabel.setText("Please enter your ATM card number.");
        cardNumText.clear();
        fDatePicker = null;
        datePicker.getEditor().clear();
        pinText.clear();
        withdrawAmtText.clear();
        pinText.setVisible(true);
        withdrawAmtText.setVisible(false);
        withdrawAmtText.setDisable(false);
        cardNumText.setDisable(false);
        atmLabel.setTextFill(Color.BLACK);
        receiptOverdraftAnchor.setVisible(false);
        receiptAnchor.setVisible(false);
        receiptButtonPane.setVisible(false);
    } //End of renewScene.


    @FXML
    private Button viewReceiptButton;

    //No overdraft
    @FXML
    private AnchorPane receiptAnchor;
    @FXML
    private Label r_DateLabel;
    @FXML
    private Label r_WithdrawAmtLabel;
    @FXML
    private Label r_AcctNumLabel;
    @FXML
    private Button r_CloseButton;
    @FXML
    private Label r_AmtWithdrawn;
    @FXML
    private Label r_StartBalLabel;
    @FXML
    private Label r_EndBalLabel;
    @FXML
    private Label r_CheckingAcctTypeLabel;


    //overdraft
    @FXML
    private AnchorPane receiptOverdraftAnchor;
    @FXML
    private Label rOV_DateLabel;
    @FXML
    private Label rOV_WithdrawAmtLabel;
    @FXML
    private Label rOV_AcctNumLabel;
    @FXML
    private Button rOV_CloseButton;
    @FXML
    private Label rOV_AmtWithdrawnLabel;
    @FXML
    private Label rOV_StartBalLabel;
    @FXML
    private Label rOV_EndBalLabel;
    @FXML
    private Label rOV_ovAcctNumLabel;
    @FXML
    private Label rOV_sAmtWithdrawnLabel;
    @FXML
    private Label rOV_sStartBalLabel;
    @FXML
    private Label rOV_sEndBalLabel;
    @FXML
    private Label rOV_AcctTypeLabel;


    public void getDateText(ActionEvent event) {
            String[] dateArr = String.valueOf(datePicker.getValue()).split("-");
            if(dateArr.length == 3) {
                fDatePicker = dateArr[1] + "/" + dateArr[2] + "/" + dateArr[0];
            }
    } //End of getDateText.

    /** -------------------------------------- LOGIC METHODS ----------------------------------------------- */

    /* --- DATABASE DATA --- */
    ArrayList<Customer> customers;
    ArrayList<Checking> checkings;
    ArrayList<Savings> savings;
    Customer customer;
    Checking checking;
    Savings saving;
    int checkingType;

    public void validatePIN(ActionEvent event) throws IOException {
        // Read in customers
        customers = DB.readCustomersCSV();
        // Check for valid atmNumber
        if (DB.verifyAtmNumberCustomer(cardNumText.getText(), customers)) {
            // if valid atm Number
            // set customer to customer with login credentials
            customer = DB.searchAtmNumberCustomer(cardNumText.getText(), customers);
            assert customer != null;
            // verify pin
            if (customer.getAtmPin() == Integer.parseInt(pinText.getText())) {
                // valid pin
                atmLabel.setText("Please enter the amount you would like to withdraw.");
                atmLabel.setTextFill(Color.BLACK);
                pinText.setVisible(false);
                withdrawAmtText.setVisible(true);
                cardNumText.setDisable(true);
            } else {
                // invalid pin
                atmLabel.setText("Invalid PIN. Please try again.");
                atmLabel.setTextFill(Color.RED);
                pinText.clear();
            }
        } else {
            // invalid account Number
            atmLabel.setText("Invalid Account Number. Please try again.");
            atmLabel.setTextFill(Color.RED);
            cardNumText.clear();
            pinText.clear();
        }
    }

    public void validateWithdrawAmt(ActionEvent event) throws IOException {

        //Getting withdraw amount.
        Double withdrawAmt = Double.parseDouble(withdrawAmtText.getText());
        //Read in the checking accounts to the ArrayList.
        checkings = DB.readCheckingCSV();
        //Finding the customer with the entered SSN.
        checking = DB.searchChecking(customer.getSSN(), checkings);
        //Read in savings for overdraft case.
        savings = DB.readSavingsCSV();
        //Finding the customer with the entered SSN.
        saving = DB.searchSavings(customer.getSSN(), savings);
        checkingType = checking.getAccountType();

        double startSavings = saving.getBalance();
        double startChecking = checking.getBalance();
        rOV_StartBalLabel.setText(String.format("$%.2f", startChecking));
        rOV_sStartBalLabel.setText(String.format("$%.2f",startSavings));

        // verify sufficient funds
        if (DB.verifyBalance(withdrawAmt, checking)) {
            if(fDatePicker == null) {
                atmLabel.setText("Please enter the date before continuing.");
                atmLabel.setTextFill(Color.RED);
            } else {
                r_DateLabel.setText(fDatePicker);
                //Getting before balance.
                r_StartBalLabel.setText("$" + checking.getBalance());
                // withdraw amount
                checking.withdraw(withdrawAmt);
                // Getting other info for receipt
                r_WithdrawAmtLabel.setText(String.format("$%.2f", withdrawAmt));
                r_AmtWithdrawn.setText(String.format("%.2f", withdrawAmt));
                r_EndBalLabel.setText(String.format("$%.2f", checking.getBalance()));
                r_AcctNumLabel.setText("*****" + checking.getCheckingAcctNum().substring(checking.getCheckingAcctNum().length() - 4));
                if(checkingType == 0) {
                    r_CheckingAcctTypeLabel.setText("TMB");
                    r_CheckingAcctTypeLabel.setStyle("-fx-background-color: transparent ");
                } else {
                    r_CheckingAcctTypeLabel.setText("G/D");
                    r_CheckingAcctTypeLabel.setStyle("-fx-background-color: #ffbb00 ");
                }
                // Display Success
                atmLabel.setText("Success! You may now view your receipt!");
                atmLabel.setTextFill(Color.GREEN);
                //Show receipt Pane
                receiptButtonPane.setVisible(true);
                // write checking back to database/csv
                DB.writeCheckingCSV(checkings);
                withdrawAmtText.setDisable(true);
                overdraft = 0;
            }

        } else if (DB.verifyOverdraft(checking)) {
            int exitCode = DB.overdraft(checking, withdrawAmt);
            if (exitCode==0) {

                if(fDatePicker == null) {
                    atmLabel.setText("Please enter the date before continuing.");
                    atmLabel.setTextFill(Color.RED);
                } else {
                    rOV_DateLabel.setText(fDatePicker);
                    rOV_WithdrawAmtLabel.setText(String.format("$%.2f", withdrawAmt));
                    //Checking
                    rOV_AcctNumLabel.setText("*****" + checking.getCheckingAcctNum().substring(checking.getCheckingAcctNum().length() - 4));
                    rOV_EndBalLabel.setText(String.format("$%.2f", checking.getBalance()));
                    rOV_AmtWithdrawnLabel.setText(String.format("$%.2f", startChecking));
                    //Savings
                    rOV_ovAcctNumLabel.setText("*****" + saving.getSavingsAcctNum().substring(saving.getSavingsAcctNum().length() - 4));
                    if(checkingType == 0) {
                        rOV_sEndBalLabel.setText(String.format("$%.2f", (startSavings - (withdrawAmt - startChecking)) - 0.5));
                        rOV_AcctTypeLabel.setText("TMB");
                        rOV_AcctTypeLabel.setStyle("-fx-background-color: transparent ");
                    } else {
                        rOV_sEndBalLabel.setText(String.format("$%.2f", (startSavings - (withdrawAmt - startChecking))));
                        rOV_AcctTypeLabel.setText("G/D");
                        rOV_AcctTypeLabel.setStyle("-fx-background-color: #ffbb00 ");
                    }
                    rOV_sAmtWithdrawnLabel.setText(String.format("$%.2f", (withdrawAmt - startChecking)));
                    // Display Success
                    atmLabel.setText("Success [overdraft]! You may now view your receipt!");
                    atmLabel.setTextFill(Color.GREEN);
                    //Show receipt Pane
                    receiptButtonPane.setVisible(true);
                    // write checking back to database/csv
                    DB.writeCheckingCSV(checkings);
                    withdrawAmtText.setDisable(true);
                    overdraft = 1;
                }
            } else {
                // error message
                atmLabel.setText("Insufficient funds. Please try again. (overdraft)");
                atmLabel.setTextFill(Color.RED);
                withdrawAmtText.clear();
            }
        } else {
            // error message
            atmLabel.setText("Insufficient funds. Please try again.");
            atmLabel.setTextFill(Color.RED);
            withdrawAmtText.clear();
        }
    }




}

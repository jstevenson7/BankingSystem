package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class z_Customer_CC {


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnCustomerButton;
    @FXML
    private Label machineLabel;
    @FXML
    private TextField cardNumText;
    @FXML
    private TextField zipText;
    @FXML
    private TextField purchaseAmtText;
    @FXML
    private Button receiptButton;
    @FXML
    private TextField purchaseNoteText;


    String cardNum;
    String matchingSSN;
    ArrayList<CreditCards> ccs;


    public void toCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void validateCard(ActionEvent event) throws IOException {
        //Read in credit cards from database.
        ccs = DB.readCreditCardCSV();
        //Retrieve card Number from interface.
        cardNum = cardNumText.getText();
        //Verify interface text field is not empty.
        if(cardNumText.getText().isBlank()) {
            //If it is blank display message.
            machineLabel.setText("Credit Card number must be entered to continue.");
            machineLabel.setTextFill(Color.RED);
        //Otherwise, it is not blank, validate card number entered.
        } else {
            //If valid continue to enter pin
            int found = 0;
            for (int i=0; i < ccs.size(); i++) {
                if(ccs.get(i).getCreditcardnumber().equals(cardNum)) {
                    found++;
                    matchingSSN = ccs.get(i).getSSN();
                }
            }
            //If it is found continue
            if (found > 0) {
                machineLabel.setText("Please enter your zip code.");
                machineLabel.setTextFill(Color.BLACK);
                zipText.setVisible(true);
                cardNumText.setDisable(true);
            //Else, stay at position red message pops up on screen.
            } else if (found == 0) {
                machineLabel.setText("Card not recognized. Please try again.");
                machineLabel.setTextFill(Color.RED);
            }
        }
    }

    public void validatePIN(ActionEvent event) throws IOException {
        //Retrieve input from interface.
        int zip = Integer.parseInt(zipText.getText());
        int customersZip = 0;
        //Read Customers table to validate zip.
        ArrayList<Customer> customers = DB.readCustomersCSV();
        //Loop through the customer table and retrieve zip of customer.
        for (int i =0; i< customers.size(); i++) {
            if (matchingSSN.equals(customers.get(i).getSSN())) {
                customersZip = customers.get(i).getZip();
            }
        }
        //If valid continue to enter zip
        if (zip == customersZip) {
            machineLabel.setText("Please enter the purchase amount.");
            machineLabel.setTextFill(Color.BLACK);
            zipText.setVisible(false);
            purchaseAmtText.setVisible(true);
        } else {
            //Else stay at position red message pops up on screen.
            machineLabel.setText("Invalid ZIP. Please try again.");
            machineLabel.setTextFill(Color.RED);
        }
    }

    double withdrawAmt =0;
    int cust = 0;

    public void validateWithdrawAmt(ActionEvent event) throws IOException {
        //Need to validate.
        withdrawAmt = Double.parseDouble(purchaseAmtText.getText());
        double custBalance = 0;
        double custLimit = 0;
        //Retrieve customers limit and current balance.
        for (int i =0; i< ccs.size(); i++) {
            if (matchingSSN.equals(ccs.get(i).getSSN())) {
                custBalance = ccs.get(i).getBalance();
                custLimit = ccs.get(i).getCreditcardlimit();
                cust = i;
            }
        }
        //If customer is within limit.
        if (withdrawAmt+custBalance <= custLimit) {
            machineLabel.setText("Purchase Approved. Enter note.");
            machineLabel.setTextFill(Color.BLACK);
            purchaseAmtText.setVisible(false);
            receiptButton.setVisible(false);
            purchaseNoteText.setVisible(true);
            datePicker.setVisible(true);
            ccs.get(cust).setBalance(withdrawAmt+custBalance);
            DB.writeCreditCardCSV(ccs);
        //Else they are outside the limit.
        } else {
            machineLabel.setText("Purchase Declined. You cannot purchase over your limit.");
            machineLabel.setTextFill(Color.RED);
            zipText.setVisible(false);
            cardNumText.setDisable(true);
        }
    }


    @FXML
    private DatePicker datePicker;
    String fDatePicker;

    public void getDateText(ActionEvent event) {
        String[] dateArr = String.valueOf(datePicker.getValue()).split("-");
        if(dateArr.length == 3) {
            fDatePicker = dateArr[1] + "/" + dateArr[2] + "/" + dateArr[0];
        }
    } //End of getDateText.


    public void enterNote(ActionEvent event) throws IOException {
            if(purchaseNoteText.getText().isBlank() || fDatePicker == null ) {
                machineLabel.setText("Purchase memo and date are both required");
                machineLabel.setTextFill(Color.RED);
             } else {
                machineLabel.setText("Withdraw was a success! Please view receipt.");
                machineLabel.setTextFill(Color.BLACK);
                purchaseNoteText.setVisible(false);
                receiptButton.setVisible(true);
                datePicker.setVisible(true);
                // Read in current transactions
                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                // Create a new transaction
                Transaction transaction = new Transaction(DB.generateTransactionNumber(), (ccs.get(cust).getSSN()), ("Credit Card"),
                        (ccs.get(cust).getCreditcardnumber()), (0-withdrawAmt), (purchaseNoteText.getText()), (fDatePicker), ("0"));
                // Add transaction to arraylist
                transactions.add(transaction);
                // Write arraylist to csv
                DB.writeTransactionsCSV(transactions);
            }
    }


    public void viewReceipt(ActionEvent event) throws IOException {

        Alert receiptPopup = new Alert(Alert.AlertType.CONFIRMATION);
        double withdrawAmt = 0;
        String name = "Jimmy";
        double newBalance = 0;
        int acc = 123;
        String s = String.format("See you next time %s!\nAmount spent: %f\n" +
                "From Account: %d \nNew Balance: %f", name, withdrawAmt, acc, newBalance);
        receiptPopup.setContentText(s);
        receiptPopup.showAndWait();
        renewScene(event);
        receiptButton.setVisible(false);
    }

    public void renewScene(ActionEvent event) throws IOException {
        machineLabel.setText("Please enter your ATM card number.");
        cardNumText.clear();
        zipText.clear();
        purchaseAmtText.clear();
        zipText.setVisible(false);
        purchaseAmtText.setVisible(false);
        cardNumText.setDisable(false);
        //Set visible receipt off when I make new pop up with close button.
    }


    @FXML
    private void initialize(){

        returnCustomerButton.setOnMouseEntered(event -> returnCustomerButton.setStyle("-fx-background-color: #ffffff"));
        returnCustomerButton.setOnMouseExited(event -> returnCustomerButton.setStyle("-fx-background-color:  #000000"));

    }
}

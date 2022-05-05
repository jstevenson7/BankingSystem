package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
        int ind;
    ArrayList<Customer> customers;
    //Made me add the try lol.
    {
        try {
            customers = DB.readCustomersCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validatePIN(ActionEvent event) throws IOException {
        //Retrieve input from interface.
        int zip = Integer.parseInt(zipText.getText());
        int customersZip = 0;
        //Read Customers table to validate zip.
        //Loop through the customer table and retrieve zip of customer.
        for (int i =0; i< customers.size(); i++) {
            if (matchingSSN.equals(customers.get(i).getSSN())) {
                customersZip = customers.get(i).getZip();
                ind = i;
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
    double beforeBal = 0;

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
            machineLabel.setText("Purchase Approved. Enter memo and date.");
            machineLabel.setTextFill(Color.BLACK);
            purchaseAmtText.setVisible(false);
            receiptButton.setVisible(false);
            purchaseNoteText.setVisible(true);
            datePicker.setVisible(true);
            beforeBal = ccs.get(cust).getBalance();
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
                datePicker.setVisible(false);
                returnCustomerButton.setDisable(true);
                // Read in current transactions
                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                // Create a new transaction
                Transaction transaction = new Transaction(DB.generateTransactionNumber(), (ccs.get(cust).getSSN()), ("Credit Card"),
                        (ccs.get(cust).getCreditcardnumber()), (0-withdrawAmt), (purchaseNoteText.getText()), (fDatePicker), ("0"));
                // Add transaction to arraylist
                transactions.add(transaction);
                // Write arraylist to csv
                DB.writeTransactionsCSV(transactions);

                //Receipt setting
                r_dateLabel.setText(fDatePicker);
                r_acctLabel.setText("*****"+ccs.get(cust).getCreditcardnumber().substring(ccs.get(cust).getCreditcardnumber().length()-4));
                r_zipLabel.setText("" +customers.get(ind).getZip());
                r_amtLabel.setText(String.format("$%.2f",withdrawAmt));
                r_memoLabel.setText(purchaseNoteText.getText());
                r_newBalLabel.setText(String.format("$%.2f",ccs.get(cust).getBalance()));
                r_limitLabel.setText(String.format("$%.2f",ccs.get(cust).getCreditcardlimit()));
                r_untilLimitLabel.setText(String.format("$%.2f",(ccs.get(cust).getCreditcardlimit())-ccs.get(cust).getBalance()));
                r_amt2Label.setText(String.format("$%.2f",withdrawAmt));
                r_beforeBalLabel.setText(String.format("$%.2f",beforeBal));
            }
    }





    public void closeReceipt(ActionEvent event) throws IOException {
        renewScene(event);
    } //End of viewReceipt.


    @FXML
    private AnchorPane receiptAnchor;
    @FXML
    private Label r_dateLabel;
    @FXML
    private Label r_acctLabel;
    @FXML
    private Label r_zipLabel;
    @FXML
    private Label r_amtLabel;
    @FXML
    private Label r_memoLabel;
    @FXML
    private Label r_newBalLabel;
    @FXML
    private Label r_limitLabel;
    @FXML
    private Button r_closeButton;
    @FXML
    private Label r_untilLimitLabel;
    @FXML
    private Label r_beforeBalLabel;
    @FXML
    private Label r_amt2Label;

    public void viewReceipt(ActionEvent event) throws IOException {
        receiptAnchor.setVisible(true);
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
        datePicker.setVisible(false);
        datePicker.getEditor().clear();
        receiptAnchor.setVisible(false);
        returnCustomerButton.setDisable(false);
        //Set visible receipt off when I make new pop up with close button.
    }


    @FXML
    private void initialize(){

        returnCustomerButton.setOnMouseEntered(event -> returnCustomerButton.setStyle("-fx-background-color: #ffffff"));
        returnCustomerButton.setOnMouseExited(event -> returnCustomerButton.setStyle("-fx-background-color:  #000000"));

        r_closeButton.setOnMouseEntered(event -> r_closeButton.setStyle("-fx-background-color: #f7bebe; -fx-border-color: grey"));
        r_closeButton.setOnMouseExited(event -> r_closeButton.setStyle("-fx-background-color:  #f5dada; -fx-border-color: grey"));

        r_closeButton.setOnMouseEntered(event -> r_closeButton.setStyle("-fx-background-color: #f7bebe; -fx-border-color: grey"));
        r_closeButton.setOnMouseExited(event -> r_closeButton.setStyle("-fx-background-color:  #f5dada; -fx-border-color: grey"));

        receiptButton.setOnMouseEntered(event -> receiptButton.setStyle("-fx-background-color: #f7bebe;"));
        receiptButton.setOnMouseExited(event -> receiptButton.setStyle("-fx-background-color:  red;"));

        cardNumText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        zipText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        purchaseAmtText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        purchaseNoteText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        datePicker.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");

    }


}

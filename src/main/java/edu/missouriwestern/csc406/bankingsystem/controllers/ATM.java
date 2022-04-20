package edu.missouriwestern.csc406.bankingsystem.controllers;

import edu.missouriwestern.csc406.bankingsystem.Customer;
import edu.missouriwestern.csc406.bankingsystem.DB;
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

public class ATM {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnCustomerButton;
    @FXML
    private TextField cardNumText;
    @FXML
    private PasswordField pinText;
    @FXML
    private Label atmLabel;
    @FXML
    private TextField withdrawAmtText;
    @FXML
    private Button receiptButton;


    public void toCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        renewScene(event);
    }

    public void validateCard(ActionEvent event) throws IOException {
        ArrayList<Customer> customers = DB.readCustomersCSV();
        Customer customer = DB.searchCreditCardCustomer(Integer.parseInt(cardNumText.getText()), customers);
        //atmLabel.setText("Welcome "+ customer.getFirstName()+"!");

        //Need to validate.
        int cardNum = Integer.parseInt(cardNumText.getText());

        //If valid continue to enter pin
        if (cardNum == 1) {
            atmLabel.setText("Please enter your pin.");
            atmLabel.setTextFill(Color.BLACK);
            pinText.setVisible(true);
            cardNumText.setDisable(true);
        } else if(cardNum == 0) {
            //Else stay at position red message pops up on screen.
            atmLabel.setText("Card not recognized. Please try again.");
            atmLabel.setTextFill(Color.RED);
            cardNumText.clear();
        }
    }

    public void validatePIN(ActionEvent event) throws IOException {
        //Need to validate.
        int pin = Integer.parseInt(pinText.getText());

        //If valid continue to enter pin
        if (pin == 1) {
            atmLabel.setText("Please enter the amount you would like to withdraw.");
            atmLabel.setTextFill(Color.BLACK);
            pinText.setVisible(false);
            withdrawAmtText.setVisible(true);
        } else if(pin == 0) {
            //Else stay at position red message pops up on screen.
            atmLabel.setText("Invalid PIN. Please try again.");
            atmLabel.setTextFill(Color.RED);
            pinText.clear();
        } else {
            //Valid but reached daily withdraw limit. ->  ALSO LIMIT ***
            atmLabel.setText("You cannot withdraw anymore today. You have reached the daily limit.");
            atmLabel.setTextFill(Color.RED);
            cardNumText.clear();
            pinText.clear();
            withdrawAmtText.clear();
            pinText.setVisible(false);
            withdrawAmtText.setVisible(false);
            cardNumText.setDisable(false);
        }
    }

    public void validateWithdrawAmt(ActionEvent event) throws IOException {
        //Need to validate.
        int withdrawAmt = Integer.parseInt(withdrawAmtText.getText());

        //If valid continue to enter withdraw amount.
        if (withdrawAmt == 1) {
            atmLabel.setText("Withdraw was a success! Please view receipt.");
            atmLabel.setTextFill(Color.BLACK);
            withdrawAmtText.setVisible(false);
            receiptButton.setVisible(true);
        } else if(withdrawAmt == 0) {
            //Else stay at position red message pops up on screen.
            atmLabel.setText("Insufficient funds. Please try again.");
            atmLabel.setTextFill(Color.RED);
            withdrawAmtText.clear();
        }
    }

    public void viewReceipt(ActionEvent event) throws IOException {

        Alert receiptPopup = new Alert(Alert.AlertType.CONFIRMATION);
        double withdrawAmt = 0;
        String name = "Jimmy";
        double newBalance = 0;
        int acc = 123;
        String s = String.format("See you next time %s!\nAmount withdrawn: %f\n" +
                "From Account: %d \nNew Balance: %f", name, withdrawAmt, acc, newBalance);
        receiptPopup.setContentText(s);
        receiptPopup.showAndWait();
        renewScene(event);
        receiptButton.setVisible(false);
    }

    public void renewScene(ActionEvent event) throws IOException {
       atmLabel.setText("Please enter your ATM card number.");
       cardNumText.clear();
       pinText.clear();
       withdrawAmtText.clear();
       pinText.setVisible(false);
       withdrawAmtText.setVisible(false);
       cardNumText.setDisable(false);
       //Set visible receipt off when I make new pop up with close button.
    }

    @FXML
    private void initialize(){
        returnCustomerButton.setOnMouseEntered(event -> returnCustomerButton.setStyle("-fx-background-color: #ffffff"));
        returnCustomerButton.setOnMouseExited(event -> returnCustomerButton.setStyle("-fx-background-color:  #000000"));
    }


}

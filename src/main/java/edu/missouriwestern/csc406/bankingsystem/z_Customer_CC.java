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



    public void toCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void validateCard(ActionEvent event) throws IOException {
       // ArrayList<Customer> customers = DB.readCustomersCSV();
       // Customer customer = DB.searchATMCustomer(Integer.parseInt(cardNumText.getText()), customers);
       // machineLabel.setText("Welcome "+ customer.getFirstName()+"!");

        //Need to validate.
        int cardNum = Integer.parseInt(cardNumText.getText());
        //If valid continue to enter pin
        if (cardNum == 1) {
            machineLabel.setText("Please enter your pin.");
            machineLabel.setTextFill(Color.BLACK);
            zipText.setVisible(true);
            cardNumText.setDisable(true);
        } else if(cardNum == 0) {
            //Else stay at position red message pops up on screen.
            machineLabel.setText("Card not recognized. Please try again.");
            machineLabel.setTextFill(Color.RED);
            cardNumText.clear();
        }
    }

    public void validatePIN(ActionEvent event) throws IOException {
        //Need to validate.
        int pin = Integer.parseInt(zipText.getText());
        //If valid continue to enter pin
        if (pin == 1) {
            machineLabel.setText("Please enter the amount you would like to withdraw.");
            machineLabel.setTextFill(Color.BLACK);
            zipText.setVisible(false);
            purchaseAmtText.setVisible(true);
        } else if(pin == 0) {
            //Else stay at position red message pops up on screen.
            machineLabel.setText("Invalid ZIP. Please try again.");
            machineLabel.setTextFill(Color.RED);
            zipText.clear();
        }
    }

    public void validateWithdrawAmt(ActionEvent event) throws IOException {
        //Need to validate.
        int withdrawAmt = Integer.parseInt(purchaseAmtText.getText());

        //If valid continue to enter withdraw amount.
        if (withdrawAmt == 1) {
            machineLabel.setText("Purchase Approved. Enter note.");
            machineLabel.setTextFill(Color.BLACK);
            purchaseAmtText.setVisible(false);
            receiptButton.setVisible(true);
        } else if(withdrawAmt == 0) {
            //Else stay at position red message pops up on screen.
            //Valid but reached purchase limit. ->  ALSO LIMIT ***
            machineLabel.setText("Purchase Declined. You cannot purchase over your limit.");
            machineLabel.setTextFill(Color.RED);
            cardNumText.clear();
            zipText.clear();
            purchaseAmtText.clear();
            zipText.setVisible(false);
            purchaseAmtText.setVisible(false);
            cardNumText.setDisable(false);
        }
    }

    public void enterNote(ActionEvent event) throws IOException {
            machineLabel.setText("Withdraw was a success! Please view receipt.");
            machineLabel.setTextFill(Color.BLACK);
            purchaseNoteText.setVisible(false);
            receiptButton.setVisible(true);
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

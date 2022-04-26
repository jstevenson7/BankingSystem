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
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class z_Customer_InsertCheck {

    /* --- STAGE/SCENE DATA --- */
    private Stage stage;
    private Scene scene;
    private Parent root;


    /* --- NAV BUTTONS --- */
    @FXML
    private Button returnCustomerButton;

    /* --- SCENE ATTRIBUTES --- */
    @FXML
    private TextField payToText;
    @FXML
    private TextField amtText;
    @FXML
    private TextField dateText;
    @FXML
    private TextField noteText;
    @FXML
    private TextField accNumText;
    @FXML
    private TextField routNumText;
    @FXML
    private TextField checkNumText;
    @FXML
    private Button insertCheckButton;
    @FXML
    private Button receiptButton;
    @FXML
    private Label checkLabel;

    /* --- NAV METHODS --- */
    public void toCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toCustomer.

    /* --- INITIALIZE --- */
    @FXML
    private void initialize(){

        returnCustomerButton.setOnMouseEntered(event -> returnCustomerButton.setStyle("-fx-background-color: #ffffff"));
        returnCustomerButton.setOnMouseExited(event -> returnCustomerButton.setStyle("-fx-background-color:  #000000"));

        insertCheckButton.setOnMouseEntered(event -> insertCheckButton.setStyle("-fx-background-color: #ffffff"));
        insertCheckButton.setOnMouseExited(event -> insertCheckButton.setStyle("-fx-background-color:  #000000"));

        receiptButton.setOnMouseEntered(event -> receiptButton.setStyle("-fx-background-color: #ffffff"));
        receiptButton.setOnMouseExited(event -> receiptButton.setStyle("-fx-background-color:  #000000"));


        receiptCloseButton.setOnMouseEntered(event -> receiptCloseButton.setStyle("-fx-background-color: #EA6E6E"));
        receiptCloseButton.setOnMouseExited(event -> receiptCloseButton.setStyle("-fx-background-color: #ebebeb; -fx-border-color: grey"));
    } //End of initialize.

    /* --- OTHER METHODS --- */
    @FXML
    private AnchorPane receiptAnchor;
    @FXML
    private Label receiptDateLabel;
    @FXML
    private Label receiptCheckNumLabel;
    @FXML
    private Label receiptPayToLabel;
    @FXML
    private Label receiptAmtLabel;
    @FXML
    private Label receiptAcctLabel;
    @FXML
    private Label receiptRoutLabel;
    @FXML
    private Label receiptForLabel;

    public void viewReceipt(ActionEvent event) {
        receiptAnchor.setVisible(true);
        receiptDateLabel.setText(dateText.getText());
        receiptCheckNumLabel.setText(checkNumText.getText());
        receiptPayToLabel.setText(payToText.getText());
        receiptAmtLabel.setText("$" + amtText.getText());
        receiptAcctLabel.setText(accNumText.getText());
        receiptRoutLabel.setText(routNumText.getText());
        receiptForLabel.setText(noteText.getText());
    }

    @FXML
    private Button receiptCloseButton;

    public void closeReceipt(ActionEvent event) {
        renewScene();
    }

    public void renewScene() {
        payToText.clear();
        dateText.clear();
        amtText.clear();
        accNumText.clear();
        noteText.clear();
        routNumText.clear();
        checkNumText.clear();
        receiptAnchor.setVisible(false);
        checkLabel.setText("Please insert the complete check.");
        insertCheckButton.setDisable(false);
        receiptButton.setDisable(true);
    } //End of renewScene.


    /** -------------------------------------- LOGIC METHODS ----------------------------------------------- */

    public void validateCheck(ActionEvent event) throws IOException {
        // Read in checks to ArrayList from csv file
        ArrayList<Check> unprocessedChecks = DB.readUnprocessedCheckCSV();
        ArrayList<Check> processedChecks = DB.readProcessedCheckCSV();
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        // Need to verify that all boxes are filled
        if(checkNumText.getText().isBlank() || amtText.getText().isBlank() ||
                dateText.getText().isBlank() || payToText.getText().isBlank() ||
                noteText.getText().isBlank() || accNumText.getText().isBlank() ||
                routNumText.getText().isBlank()) {
            checkLabel.setText("All fields are required. Please try again.");
            checkLabel.setTextFill(Color.RED);
        } else if(!DB.verifyAccountNumber(accNumText.getText(), checkings)) {
            // If accountNumber is not in database
            checkLabel.setText("Invalid Account Number!");
            checkLabel.setTextFill(Color.RED);
        } else if(DB.verifyChecks(checkNumText.getText(), accNumText.getText(), unprocessedChecks, processedChecks)) {
            // If checkID is invalid/already used
            checkLabel.setText("Invalid Check Number!");
            checkLabel.setTextFill(Color.RED);
        } else {
            // create check from textFields
            Check check = new Check(checkNumText.getText(), Double.parseDouble(amtText.getText()),
                dateText.getText(), payToText.getText(), noteText.getText(),
                accNumText.getText(), routNumText.getText());
            // add new check
            unprocessedChecks.add(check);
            // Write back to csv
            DB.writeUnprocessedCheckCSV(unprocessedChecks);
            receiptButton.setDisable(false);
            checkLabel.setTextFill(Color.BLACK);
            checkLabel.setText("Check is valid you can now view your check submission receipt!");
            insertCheckButton.setDisable(true);
            // If boxes are not all filled
        }
    }


} //End of InsertCheck Controller Class.

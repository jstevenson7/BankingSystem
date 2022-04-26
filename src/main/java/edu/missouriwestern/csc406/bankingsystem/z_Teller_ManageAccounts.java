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

    /* --- DELETE ANCHOR DATA --- */
    @FXML
    private AnchorPane deleteAnchor;
    @FXML
    private Button dDeleteButton;
    @FXML
    private TextField dSSNText;
    @FXML
    private TextField dAccountNumText;
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
    public void createAccount(ActionEvent event) throws IOException {
        // verify SSN exists in customers
        if (DB.verifyCustomerSSN(cSSNText.getText(), DB.readCustomersCSV())) {
            if (cAccountBox.getValue().equals("Checking - TMB") || cAccountBox.getValue().equals("Checking - Gold/Diamond")) {
                // if checking is selected
                // read in current checking accounts
                ArrayList<Checking> checkings = DB.readCheckingCSV();
                // if customer already has checking
                if (!DB.verifyCheckingSSN(cSSNText.getText(), checkings)) {
                    // create new checking account, check type of checking to create
                    if (cAccountBox.getValue().equals("Checking - Gold/Diamond")) {
                        Checking checking = new Checking(DB.generateAccountNumber(), 1000, 1, cSSNText.getText());
                        // add to arraylist
                        checkings.add(checking);
                    } else {
                        Checking checking = new Checking(DB.generateAccountNumber(), 0, 0, cSSNText.getText());
                        // add to arraylist
                        checkings.add(checking);
                    }
                    // write to database
                    DB.writeCheckingCSV(checkings);
                    cMessage.setText("Success!");
                    cMessage.setTextFill(Color.GREEN);
                    cSSNText.clear();
                } else {
                    cMessage.setText("Checking already exist!");
                    cMessage.setTextFill(Color.RED);
                }
            } // end of if Checking

        } else {
            // If SSN is not found
            cMessage.setText("SSN not found!");
            cMessage.setTextFill(Color.RED);
        }
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
        cAccountBox.getItems().addAll("Checking - TMB","Checking - Gold/Diamond", "Saving", "Loan", "CD");
        cAccountBox.setVisibleRowCount(5);
    }
}

package edu.missouriwestern.csc406.bankingsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageAccounts {

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
    private TextField cSSNText;
    @FXML
    private TextField cFNameText;
    @FXML
    private TextField cLNameText;
    @FXML
    private TextField cAddressText;
    @FXML
    private TextField cCityText;
    @FXML
    private ComboBox<String> cStateBox;
    @FXML
    private TextField cZipText;
    @FXML
    private ComboBox<String> cAccountBox;

    /* --- DELETE ANCHOR DATA --- */
    @FXML
    private AnchorPane deleteAnchor;
    @FXML
    private Button dDeleteButton;

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
        cStateBox.getItems().addAll("AL","AK","AZ","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI",
                "MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX",
                "UT","VT","VA","WA","WV","WI","WY");
        cStateBox.setVisibleRowCount(9);
        cAccountBox.getItems().addAll("Checking", "Saving", "Loan", "CD");
        cAccountBox.setVisibleRowCount(5);
    }
}

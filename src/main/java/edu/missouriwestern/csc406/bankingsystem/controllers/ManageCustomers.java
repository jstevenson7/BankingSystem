package edu.missouriwestern.csc406.bankingsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageCustomers {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnTellerButton;
    @FXML
    private Button createACustomerButton;
    @FXML
    private Button deleteACustomerButton;
    @FXML
    private Button reviewACustomerButton;
    @FXML
    private TextField ssnText;
    @FXML
    private TextField fNameText;
    @FXML
    private TextField lNameText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField cityText;
    @FXML
    private ComboBox<String> stateBox;
    @FXML
    private TextField zipText;
    @FXML
    private ComboBox<String> accountBox;
    @FXML
    private AnchorPane createAnchor;

    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void displayCreate(ActionEvent event) throws IOException {
        createAnchor.setVisible(true);
    }
    public void displayDelete(ActionEvent event) throws IOException {
        createAnchor.setVisible(true);
    }
    public void displayReview(ActionEvent event) throws IOException {
        createAnchor.setVisible(true);

    }

    @FXML
    private void initialize(){
        // Customer Button
        returnTellerButton.setOnMouseEntered(event -> returnTellerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnTellerButton.setOnMouseExited(event -> returnTellerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        createACustomerButton.setOnMouseEntered(event -> createACustomerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        createACustomerButton.setOnMouseExited(event -> createACustomerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        deleteACustomerButton.setOnMouseEntered(event -> deleteACustomerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        deleteACustomerButton.setOnMouseExited(event -> deleteACustomerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        reviewACustomerButton.setOnMouseEntered(event -> reviewACustomerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        reviewACustomerButton.setOnMouseExited(event -> reviewACustomerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        stateBox.getItems().addAll("AL","AK","AZ","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI",
                "MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX",
                "UT","VT","VA","WA","WV","WI","WY");
        stateBox.setVisibleRowCount(9);

        accountBox.getItems().addAll("Checking", "Saving", "Loans");
        accountBox.setVisibleRowCount(5);

    }

}

package edu.missouriwestern.csc406.bankingsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class InsertCheck {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnCustomerButton;
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
    private TextField insertCheckButton;
    @FXML
    private Label checkLabel;
    @FXML
    private Button receiptButton;

    public void toCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void validateCheck(ActionEvent event) throws IOException {
        int valid = Integer.parseInt(accNumText.getText());
        if(valid == 1) {
            receiptButton.setDisable(false);
            checkLabel.setTextFill(Color.BLACK);
            checkLabel.setText("Check is valid you can now view your check submission receipt!");
        } else if(valid == 0) {
            checkLabel.setText("Check invalid. Please try again.");
            checkLabel.setTextFill(Color.RED);
        }

        //May also check for if all fields are submitted.
    }

    @FXML
    private void initialize(){

        returnCustomerButton.setOnMouseEntered(event -> returnCustomerButton.setStyle("-fx-background-color: #ffffff"));
        returnCustomerButton.setOnMouseExited(event -> returnCustomerButton.setStyle("-fx-background-color:  #000000"));

    }
}

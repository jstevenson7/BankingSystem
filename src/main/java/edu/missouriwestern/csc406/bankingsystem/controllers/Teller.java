package edu.missouriwestern.csc406.bankingsystem.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Teller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnEmployeeButton;
    @FXML
    private Button insertCheckButton;
    @FXML
    private Button useATMButton;
    @FXML
    private Button useCCButton;

    public void toEmployee(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EmployeeLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void initialize(){
        // Customer Button
        returnEmployeeButton.setOnMouseEntered(event -> returnEmployeeButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnEmployeeButton.setOnMouseExited(event -> returnEmployeeButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
    }

}

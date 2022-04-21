package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class z_SystemMain {

     Stage stage;
     Scene scene;
     Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button logoButton;
    @FXML
    private Button selectActorButton;
    @FXML
    private Button checkPointButton;
    @FXML
    private Button runTestsButton;
    @FXML
    private Button loadDataButton;


    /* --- MAIN ANCHOR --- */
    @FXML
    private AnchorPane mainAnchor;


    /* --- SELECT ACTOR ANCHOR --- */
    @FXML
    private AnchorPane selectActorAnchor;
    @FXML
    private Button customerButton;
    @FXML
    private Button employeeButton;


    /* --- OPTIONAL ANCHORS --- */
    @FXML
    private AnchorPane checkPointAnchor;
    @FXML
    private AnchorPane runTestsAnchor;
    @FXML
    private AnchorPane loadDataAnchor;

    /* --- NAV FUNCTIONS --- */
    public void toSystemMain(ActionEvent event) throws IOException {
        mainAnchor.setVisible(true);
        selectActorAnchor.setVisible(false);
        checkPointAnchor.setVisible(false);
        runTestsAnchor.setVisible(false);
        loadDataAnchor.setVisible(false);
    }

    public void toSelectActor(ActionEvent event) throws IOException {
       mainAnchor.setVisible(false);
       selectActorAnchor.setVisible(true);
       checkPointAnchor.setVisible(false);
       runTestsAnchor.setVisible(false);
       loadDataAnchor.setVisible(false);
    }

    public void toCheckPoint(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        selectActorAnchor.setVisible(false);
        checkPointAnchor.setVisible(true);
        runTestsAnchor.setVisible(false);
        loadDataAnchor.setVisible(false);
    }

    public void toRunTests(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        selectActorAnchor.setVisible(false);
        checkPointAnchor.setVisible(false);
        runTestsAnchor.setVisible(true);
        loadDataAnchor.setVisible(false);
    }

    public void toLoadData(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        selectActorAnchor.setVisible(false);
        checkPointAnchor.setVisible(false);
        runTestsAnchor.setVisible(false);
        loadDataAnchor.setVisible(true);
    }

    public void toCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

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
        /* --- SYSTEM --- */
        // Actor Button
        selectActorButton.setOnMouseEntered(event -> selectActorButton.setStyle("-fx-background-color: #cc0000"));
        selectActorButton.setOnMouseExited(event -> selectActorButton.setStyle("-fx-background-color:  #990000"));
        // Load Data Button
        loadDataButton.setOnMouseEntered(event -> loadDataButton.setStyle("-fx-background-color: #cc0000"));
        loadDataButton.setOnMouseExited(event -> loadDataButton.setStyle("-fx-background-color: #990000"));
        // Run Tests Button
        runTestsButton.setOnMouseEntered(event -> runTestsButton.setStyle("-fx-background-color: #cc0000"));
        runTestsButton.setOnMouseExited(event -> runTestsButton.setStyle("-fx-background-color:  #990000"));
        // Check Point Button
        checkPointButton.setOnMouseEntered(event -> checkPointButton.setStyle("-fx-background-color: #cc0000"));
        checkPointButton.setOnMouseExited(event -> checkPointButton.setStyle("-fx-background-color:  #990000"));

        logoButton.setOnMouseEntered(event -> logoButton.setStyle("-fx-background-color: #cc0000"));
        logoButton.setOnMouseExited(event -> logoButton.setStyle("-fx-background-color:  #990000"));

        // Customer Button
        customerButton.setOnMouseEntered(event -> customerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        customerButton.setOnMouseExited(event -> customerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        // Employee Button
        employeeButton.setOnMouseEntered(event -> employeeButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color:#000000;"));
        employeeButton.setOnMouseExited(event -> employeeButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0;"));
    }


}

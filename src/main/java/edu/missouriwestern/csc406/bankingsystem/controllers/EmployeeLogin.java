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

public class EmployeeLogin {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnActorButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button loginButtonT;

    public void toActor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Actor.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void initialize(){
        // Customer Button
        returnActorButton.setOnMouseEntered(event -> returnActorButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnActorButton.setOnMouseExited(event -> returnActorButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        // Employee Button
        loginButton.setOnMouseEntered(event -> loginButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color:#000000;"));
        loginButton.setOnMouseExited(event -> loginButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0;"));
        // Employee Button
        loginButtonT.setOnMouseEntered(event -> loginButtonT.setStyle("-fx-background-color: #E8ADAD; -fx-border-color:#000000;"));
        loginButtonT.setOnMouseExited(event -> loginButtonT.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0;"));
    }

}

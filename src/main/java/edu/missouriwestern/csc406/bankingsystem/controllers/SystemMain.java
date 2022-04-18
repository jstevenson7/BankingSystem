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

public class SystemMain {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- SYSTEM BUTTONS & METHODS --- */
    @FXML
    private Button selectActorButton;
    @FXML
    private Button runTestsButton;
    @FXML
    private Button loadDataButton;
    @FXML
    private Button checkPointButton;
    @FXML
    private Button logoButton;

    public void toSelectActor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Actor.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toRunTests(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RunTests.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toLoadData(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoadData.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toCheckPoint(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CheckPoint.fxml"));
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
    }


}

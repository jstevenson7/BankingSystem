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

public class CustomerScene {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- ACTOR BUTTONS & METHODS --- */
    @FXML
    private Button returnActorButton;
    @FXML
    private Button insertCheckButton;
    @FXML
    private Button useATMButton;
    @FXML
    private Button useCCButton;


    public void toActor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Actor.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toATM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ATM.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toInsertCheck(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("InsertCheck.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toUseCC(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CC.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    private void initialize(){

        returnActorButton.setOnMouseEntered(event -> returnActorButton.setStyle("-fx-background-color: #E8ADAD"));
        returnActorButton.setOnMouseExited(event -> returnActorButton.setStyle("-fx-background-color:  #DCDCDC; -fx-border-color:  #C0C0C0;"));

        insertCheckButton.setOnMouseEntered(event -> insertCheckButton.setStyle("-fx-background-color: #E8ADAD"));
        insertCheckButton.setOnMouseExited(event -> insertCheckButton.setStyle("-fx-background-color:  #DCDCDC; -fx-border-color:  #C0C0C0;"));

        useATMButton.setOnMouseEntered(event -> useATMButton.setStyle("-fx-background-color: #E8ADAD"));
        useATMButton.setOnMouseExited(event -> useATMButton.setStyle("-fx-background-color:  #DCDCDC; -fx-border-color:  #C0C0C0;"));

        useCCButton.setOnMouseEntered(event -> useCCButton.setStyle("-fx-background-color: #E8ADAD"));
        useCCButton.setOnMouseExited(event -> useCCButton.setStyle("-fx-background-color:  #DCDCDC; -fx-border-color:  #C0C0C0;"));
    }
}

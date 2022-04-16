package edu.missouriwestern.csc406.bankingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Stage_SystemActor {

    /* --- DATA --- */
    private Stage stage;

    @FXML
    private Button setActor;
    @FXML
    private Button runTests;
    @FXML
    private Button loadData;
    @FXML
    private Button checkPoint;
    @FXML
    private Button customer;
    @FXML
    private Button employee;

    /* --- CONSTRUCTOR --- */
    public Stage_SystemActor(Stage_SystemMain stage_systemMain){
        this.stage = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Actor.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load(),600,400));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /* --- METHODS --- */
    public void showStage(){
        stage.showAndWait();
    }


    @FXML
    private void initialize(){
        /* --- ACTION ---*/

        /* --- STYLE --- */
        // Actor Button
        setActor.setOnMouseEntered(event -> setActor.setStyle("-fx-background-color: #cc0000"));
        setActor.setOnMouseExited(event -> setActor.setStyle("-fx-background-color:  #990000"));
        // Load Data Button
        loadData.setOnMouseEntered(event -> loadData.setStyle("-fx-background-color: #cc0000"));
        loadData.setOnMouseExited(event -> loadData.setStyle("-fx-background-color: #990000"));
        // Run Tests Button
        runTests.setOnMouseEntered(event -> runTests.setStyle("-fx-background-color: #cc0000"));
        runTests.setOnMouseExited(event -> runTests.setStyle("-fx-background-color:  #990000"));
        // Check Point Button
        checkPoint.setOnMouseEntered(event -> checkPoint.setStyle("-fx-background-color: #cc0000"));
        checkPoint.setOnMouseExited(event -> checkPoint.setStyle("-fx-background-color:  #990000"));
    }

}

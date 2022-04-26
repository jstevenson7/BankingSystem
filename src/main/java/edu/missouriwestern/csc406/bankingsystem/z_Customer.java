package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class z_Customer {

    /* --- STAGE/SCENE DATA --- */
    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button returnMainButton;
    @FXML
    private Button toInsertCheckButton;
    @FXML
    private Button toUseATMButton;
    @FXML
    private Button toUseCCButton;

    @FXML
    private void initialize(){
        // Return Main Button
        returnMainButton.setOnMouseEntered(event -> returnMainButton.setStyle("-fx-background-color: #E8ADAD"));
        returnMainButton.setOnMouseExited(event -> returnMainButton.setStyle("-fx-background-color:  #DCDCDC; -fx-border-color:  #C0C0C0;"));
        // To Insert Check Button
        toInsertCheckButton.setOnMouseEntered(event -> toInsertCheckButton.setStyle("-fx-background-color: #E8ADAD"));
        toInsertCheckButton.setOnMouseExited(event -> toInsertCheckButton.setStyle("-fx-background-color:  #DCDCDC; -fx-border-color:  #C0C0C0;"));
        // To Use ATM Button
        toUseATMButton.setOnMouseEntered(event -> toUseATMButton.setStyle("-fx-background-color: #E8ADAD"));
        toUseATMButton.setOnMouseExited(event -> toUseATMButton.setStyle("-fx-background-color:  #DCDCDC; -fx-border-color:  #C0C0C0;"));
        // To Use CC Button
        toUseCCButton.setOnMouseEntered(event -> toUseCCButton.setStyle("-fx-background-color: #E8ADAD"));
        toUseCCButton.setOnMouseExited(event -> toUseCCButton.setStyle("-fx-background-color:  #DCDCDC; -fx-border-color:  #C0C0C0;"));
    } //End of initialize.

    /* --- NAV METHODS --- */
    public void toSystemMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SystemMain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }  //End of toSystemMain.

    public void toATM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer_ATM.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }  //End of toATM.

    public void toInsertCheck(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer_InsertCheck.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toInsertCheck.

    public void toUseCC(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer_CC.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toUseCC.

} //End of Customer Controller Class.

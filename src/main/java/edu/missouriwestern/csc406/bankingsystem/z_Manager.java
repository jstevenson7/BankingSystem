package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class z_Manager {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnEmployeeButton;
    @FXML
    private Button manageEmployeesButton;
    @FXML
    private Button manageLoansButton;
    @FXML
    public static Label welcomeLabel;


    public void toEmployee(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EmployeeLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toManageEmployees(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager_ManageEmployees.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toManageLoans(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager_ManageLoans.fxml"));
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

        manageEmployeesButton.setOnMouseEntered(event -> manageEmployeesButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        manageEmployeesButton.setOnMouseExited(event -> manageEmployeesButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        manageLoansButton.setOnMouseEntered(event -> manageLoansButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        manageLoansButton.setOnMouseExited(event -> manageLoansButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));


    }
}

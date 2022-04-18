package edu.missouriwestern.csc406.bankingsystem.controllers;

import edu.missouriwestern.csc406.bankingsystem.DB;
import edu.missouriwestern.csc406.bankingsystem.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeLogin {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnActorButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField employeeID;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginLabel;


    private ArrayList<Employee> employees;

    public void login(ActionEvent event) throws IOException {
        employees = DB.readEmployeeCSV();
        for (Employee e : employees) {
            if (e.getEmployeeID().equals(employeeID.getText()) && e.getPassword().equals(password.getText())) {
                System.out.println(e.getFirstName());
                if (e.getIsManager() == 1){
                    toManager(event);
                } else toTeller(event);
                break;
            } else loginLabel.setText("Incorrect Login Credentials");
        }
    }
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
        // Actor Button
        returnActorButton.setOnMouseEntered(event -> returnActorButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnActorButton.setOnMouseExited(event -> returnActorButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        // Login Button
        loginButton.setOnMouseEntered(event -> loginButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color:#000000;"));
        loginButton.setOnMouseExited(event -> loginButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0;"));
    }

}

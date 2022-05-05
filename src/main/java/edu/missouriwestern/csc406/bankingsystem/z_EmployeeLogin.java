package edu.missouriwestern.csc406.bankingsystem;

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
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class z_EmployeeLogin {

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

    public static String curEmpLoggedIn = null;

    private ArrayList<Employee> employees;

    public void login(ActionEvent event) throws IOException {
        employees = DB.readEmployeeCSV();
        Employee emp = DB.searchEmployee(employeeID.getText(), employees);


        if(employeeID.getText().isBlank() || password.getText().isBlank()) {
            loginLabel.setText("All fields are required!");
            loginLabel.setTextFill(Color.RED);
        } else if(emp == null) {
            loginLabel.setText("Incorrect Login Credentials");
            loginLabel.setTextFill(Color.RED);
            employeeID.clear();
            password.clear();
        } else {
            if (emp.getEmployeeID().equals(employeeID.getText()) && emp.getPassword().equals(password.getText())) {
                //System.out.println(emp.getFirstName());
                curEmpLoggedIn = emp.getFirstName();
                if (emp.getIsManager() == 1){
                    toManager(event);
                   // z_Manager.welcomeLabel.setText("Hello, " + curEmpLoggedIn +". Welcome to the Manager Portal!");
                } else {
                    toTeller(event);
                   // z_Teller.welcomeLabel.setText("Hello, " + curEmpLoggedIn +". Welcome to the Teller Portal!");
                }
            } else {
                loginLabel.setText("Incorrect Login Credentials");
                loginLabel.setTextFill(Color.RED);
                employeeID.clear();
                password.clear();
            }
        }
    }
    public void toActor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SystemMain.fxml"));
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

        employeeID.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        password.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
    }

}

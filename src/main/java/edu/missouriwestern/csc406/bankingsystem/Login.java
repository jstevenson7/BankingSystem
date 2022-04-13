package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    public Login(){}

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField lastName;
    @FXML
    private PasswordField ssn;

    public void userLogin(ActionEvent event) throws Exception {
        if(lastName.getText().equalsIgnoreCase("Stone")&&ssn.getText().equals("111223333")){
            wrongLogin.setText("Logging in...");
            System.out.println("Credentials are correct!");
            App a = new App();

            a.userPage();
        }else{
            wrongLogin.setText("Incorrect Credentials.");
        }
    }
}

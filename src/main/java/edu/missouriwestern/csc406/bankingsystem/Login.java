package edu.missouriwestern.csc406.bankingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    private final Stage stage;

    @FXML
    private Button button;

    @FXML
    private Label wrongLogin;

    @FXML
    private TextField employeeID;

    @FXML
    private PasswordField password;

    @FXML
    private Button atmButton;

    public Login(){

        stage = new Stage();



        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            System.out.println(fxmlLoader.getLocation());
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setTitle("Login");

            stage.getIcons().add(new Image("icon.png"));
            stage.setResizable(false);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showStage(){
        stage.showAndWait();
    }

    @FXML
    private void initialize(){

        button.setOnAction(event -> userLogin());
        atmButton.setOnAction(event -> toAtm());

    }



    public void userLogin(){
        if(employeeID.getText().equalsIgnoreCase("1001")&&password.getText().equals("111223333")){
            wrongLogin.setText("Logging in...");
            System.out.println("Credentials are correct!");
            UserPage userPage = new UserPage(this);
            stage.close();
            userPage.showStage();
        }else{
            wrongLogin.setText("Incorrect Credentials.");
        }
    }
    public void toAtm(){
        ATM atm = new ATM(this);
        stage.close();
        atm.showStage();
    }
}

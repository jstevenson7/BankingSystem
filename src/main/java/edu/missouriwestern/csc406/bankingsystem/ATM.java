package edu.missouriwestern.csc406.bankingsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ATM {

    private final Stage stage;
    private final Login login;

    public ATM(Login login){
        this.login = login;

        this.stage = new Stage();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ATM.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load(),600,400));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showStage(){
        stage.showAndWait();
    }
}

package edu.missouriwestern.csc406.bankingsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserPage {

    private final Stage stage;
    private final Login login;

    UserPage(Login login){

        this.login = login;

        this.stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UserPage.class.getResource("userPage.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load(), 920, 600));
            stage.setResizable(false);
            stage.setTitle("UserPage");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showStage(){
        stage.showAndWait();
    }

}

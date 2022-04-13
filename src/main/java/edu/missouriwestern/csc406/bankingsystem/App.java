package edu.missouriwestern.csc406.bankingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 *
 *
 *
 */
public class App extends Application {

    public static void main(String[] args){
        /*
        Main Line will launch program and will need to initialize databases, (Employee Database and Customer Database)

         */
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Login login = new Login();

        login.showStage();

    }

}

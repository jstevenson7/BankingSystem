package edu.missouriwestern.csc406.bankingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    } //End of main.

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("System.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

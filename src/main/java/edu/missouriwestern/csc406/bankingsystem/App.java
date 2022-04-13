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

    static Stage stg;

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        System.out.println(fxmlLoader.getLocation());
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("icon.png"));
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void userPage() throws Exception {
        Stage stage = stg;
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(UserPage.class.getResource("userPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920,600);
        stage.setResizable(false);
        stage.setTitle("UserPage");
        stage.setScene(scene);
        stage.show();
    }

}

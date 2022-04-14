package edu.missouriwestern.csc406.bankingsystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class Stage_SystemMain {

    /* --- DATA --- */
    private final Stage stage;

    @FXML
    private Button setActor;
    @FXML
    private Button runTests;
    @FXML
    private Button loadData;
    @FXML
    private Button checkPoint;



    /* --- CONSTRUCTOR --- */
    public Stage_SystemMain() {
        this.stage = new Stage();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Stage_SystemMain.fxml"));
            fxmlLoader.setController(this);
            stage.setScene(new Scene(fxmlLoader.load(),600,400));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /* --- METHODS --- */
    public void showStage(){
        stage.showAndWait();
    }

    @FXML
    private void initialize(){


        /* --- STYLE --- */
        // Actor Button
        setActor.setOnMouseEntered(event -> setActor.setStyle("-fx-background-color: #c2c2c2"));
        setActor.setOnMouseExited(event -> setActor.setStyle("-fx-background-color: #ffffff"));
        // Load Data Button
        loadData.setOnMouseEntered(event -> loadData.setStyle("-fx-background-color: #c2c2c2"));
        loadData.setOnMouseExited(event -> loadData.setStyle("-fx-background-color: #ffffff"));
        // Run Tests Button
        runTests.setOnMouseEntered(event -> runTests.setStyle("-fx-background-color: #c2c2c2"));
        runTests.setOnMouseExited(event -> runTests.setStyle("-fx-background-color: #ffffff"));
        // Check Point Button
        checkPoint.setOnMouseEntered(event -> checkPoint.setStyle("-fx-background-color: #c2c2c2"));
        checkPoint.setOnMouseExited(event -> checkPoint.setStyle("-fx-background-color: #ffffff"));
    }

}



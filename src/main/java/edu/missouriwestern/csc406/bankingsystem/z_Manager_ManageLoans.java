package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class z_Manager_ManageLoans {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnManagerButton;

    /* --- NAV FUNCTIONS --- */
    public void toManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    @FXML
    private void initialize(){
        // Customer Button
        returnManagerButton.setOnMouseEntered(event -> returnManagerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnManagerButton.setOnMouseExited(event -> returnManagerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
    }
}

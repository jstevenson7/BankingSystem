package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class z_Manager_ManageLoans {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnManagerButton;
    @FXML
    private Button toCCButton;
    @FXML
    private Button toLoansButton;


    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane ccPane;
    @FXML
    private TextField ccSSNTF;
    @FXML
    private TextField ccLimitTF;


    @FXML
    private AnchorPane establishLoanPane;







    /* --- NAV FUNCTIONS --- */
    public void toManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    public void toCC(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        ccPane.setVisible(true);
        establishLoanPane.setVisible(false);
    } //End of toTeller.

    public void toLoan(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        ccPane.setVisible(false);
        establishLoanPane.setVisible(true);
    } //End of toTeller.


    @FXML
    private void initialize(){
        // Customer Button
        returnManagerButton.setOnMouseEntered(event -> returnManagerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnManagerButton.setOnMouseExited(event -> returnManagerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
    }
}

package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class z_Teller_ManageTransactions {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button returnTellerButton;
    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button transferButton;

    /* --- MAIN ANCHOR DATA --- */
    @FXML
    private AnchorPane mainAnchor;

    /* --- DEPOSIT ANCHOR DATA --- */
    @FXML
    private AnchorPane depositAnchor;

    /* --- WITHDRAW ANCHOR DATA --- */
    @FXML
    private AnchorPane withdrawAnchor;

    /* --- TRANSFER ANCHOR DATA --- */
    @FXML
    private AnchorPane transferAnchor;

    /* --- NAV FUNCTIONS --- */
    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    public void displayDeposit(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(true);
        withdrawAnchor.setVisible(false);
        transferAnchor.setVisible(false);
    } //End of displayCreate.

    public void displayWithdraw(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
        withdrawAnchor.setVisible(true);
        transferAnchor.setVisible(false);
    } //End of displayDelete.

    public void displayTransfer(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
        withdrawAnchor.setVisible(false);
        transferAnchor.setVisible(true);
    } //End of displayDelete.
    @FXML
    private void initialize() {
        // Customer Button
        returnTellerButton.setOnMouseEntered(event -> returnTellerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnTellerButton.setOnMouseExited(event -> returnTellerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        depositButton.setOnMouseEntered(event -> depositButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        depositButton.setOnMouseExited(event -> depositButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        withdrawButton.setOnMouseEntered(event -> withdrawButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        withdrawButton.setOnMouseExited(event -> withdrawButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        transferButton.setOnMouseEntered(event -> transferButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        transferButton.setOnMouseExited(event -> transferButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
    }
}

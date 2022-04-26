package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
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
    @FXML
    private Button processChecksButton;

    /* --- MAIN ANCHOR DATA --- */
    @FXML
    private AnchorPane mainAnchor;

    /* --- DEPOSIT ANCHOR DATA --- */
    @FXML
    private AnchorPane depositAnchor;
    @FXML
    private Button depositAnchorButton;
    @FXML
    private TextField ssnText;
    @FXML
    private ComboBox acctTypeBox;
    @FXML
    private TextField acctNumText;
    @FXML
    private TextField checkNumText;
    @FXML
    private Text checkNumT;
    @FXML
    private TextField depositAmtText;
    @FXML
    private CheckBox checkBox;

    /* --- WITHDRAW ANCHOR DATA --- */
    @FXML
    private AnchorPane withdrawAnchor;

    /* --- TRANSFER ANCHOR DATA --- */
    @FXML
    private AnchorPane transferAnchor;

    /* --- PROCESS CHECKS ANCHOR DATA --- */
    @FXML
    private AnchorPane processChecksAnchor;

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
        processChecksAnchor.setVisible(false);
    } //End of displayCreate.

    public void displayChecks(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
        withdrawAnchor.setVisible(false);
        transferAnchor.setVisible(false);
        processChecksAnchor.setVisible(true);
    } //End of displayCreate.

    public void displayWithdraw(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
        withdrawAnchor.setVisible(true);
        transferAnchor.setVisible(false);
        processChecksAnchor.setVisible(false);
    } //End of displayDelete.

    public void displayTransfer(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
        withdrawAnchor.setVisible(false);
        transferAnchor.setVisible(true);
        processChecksAnchor.setVisible(false);
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

        processChecksButton.setOnMouseEntered(event -> processChecksButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        processChecksButton.setOnMouseExited(event -> processChecksButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        acctTypeBox.getItems().addAll("Checking - TMB", "Checking - Gold/Diamond", "Savings - Simple", "CD");
        acctTypeBox.setVisibleRowCount(5);

    }

    public void depositCheck(ActionEvent event) {
        if(checkBox.isSelected()){
            checkNumText.setDisable(false);
            checkNumT.setVisible(true);
        } else {
            checkNumText.setDisable(true);
            checkNumT.setVisible(false);
            checkNumText.clear();
        }
    }







}

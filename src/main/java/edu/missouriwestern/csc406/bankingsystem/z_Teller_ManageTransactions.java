package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class z_Teller_ManageTransactions {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* ------------- NAV BUTTONS ------------- */
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

    /* ------------- NAV FUNCTIONS ------------- */
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
        payBillsAnchor.setVisible(false);
    } //End of displayCreate.

    public void displayChecks(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
        withdrawAnchor.setVisible(false);
        transferAnchor.setVisible(false);
        payBillsAnchor.setVisible(true);
    } //End of displayCreate.

    public void displayWithdraw(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
        withdrawAnchor.setVisible(true);
        transferAnchor.setVisible(false);
        payBillsAnchor.setVisible(false);
    } //End of displayDelete.

    public void displayTransfer(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
        withdrawAnchor.setVisible(false);
        transferAnchor.setVisible(true);
        payBillsAnchor.setVisible(false);
    } //End of displayDelete.


    /* ------------- MAIN ANCHOR ------------- */
    @FXML
    private AnchorPane mainAnchor;

    /* ------------- DEPOSIT ANCHOR ------------- */
    // DEPOSIT ANCHOR DATA
    @FXML
    private AnchorPane depositAnchor;
    @FXML
    private DatePicker d_dateDP;
    @FXML
    private Button d_depositButton;
    @FXML
    private TextField d_ssnTF;
    @FXML
    private ComboBox d_acctTypeCB;
    @FXML
    private TextField d_acctNumTF;
    @FXML
    private TextField d_checkNumTF;
    @FXML
    private Text d_checkNumT;
    @FXML
    private TextField d_amountTF;
    @FXML
    private CheckBox d_checkCKB;

    // DEPOSIT ANCHOR FUNCTIONS
    public void depositCheck(ActionEvent event) {
        if(d_checkCKB.isSelected()){
            d_checkNumTF.setDisable(false);
            d_checkNumT.setVisible(true);
        } else {
            d_checkNumTF.setDisable(true);
            d_checkNumT.setVisible(false);
            d_checkNumTF.clear();
        } //End of else.
    } //End of depositCheck.

    /* ------------- WITHDRAW ANCHOR DATA ------------- */
    // WITHDRAW ANCHOR DATA
    @FXML
    private AnchorPane withdrawAnchor;
    @FXML
    private DatePicker w_dateDP;
    @FXML
    private Button w_withdrawButton;
    @FXML
    private TextField w_ssnTF;
    @FXML
    private ComboBox w_acctTypeCB;
    @FXML
    private TextField w_acctNumTF;
    @FXML
    private TextField w_amountTF;

    // WITHDRAW ANCHOR FUNCTIONS


    /* ------------- TRANSFER ANCHOR ------------- */
    @FXML
    private AnchorPane transferAnchor;
    @FXML
    private Button t_transferButton;
    @FXML
    private ComboBox t_toAcctTypeCB;
    @FXML
    private TextField t_toAcctNumTF;
    @FXML
    private ComboBox t_fromAcctTypeCB;
    @FXML
    private TextField t_fromAcctNumTF;
    @FXML
    private TextField t_amountTF;
    @FXML
    private DatePicker t_dateDP;


    /* ------------- PAY BILLS ANCHOR ------------- */
    @FXML
    private AnchorPane payBillsAnchor;


    /* ------------- INITIALIZE ------------- */
    @FXML
    private void initialize() {

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
        d_acctTypeCB.getItems().addAll("Checking - TMB", "Checking - Gold/Diamond", "Savings - Simple", "CD");
        d_acctTypeCB.setVisibleRowCount(5);

    } //End of initialize.









}

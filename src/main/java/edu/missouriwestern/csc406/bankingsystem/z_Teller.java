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

public class z_Teller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button returnEmployeeButton;
    @FXML
    private Button manageCustomersButton;
    @FXML
    private Button manageAccountsButton;
    @FXML
    private Button manageTransactionsButton;
    @FXML
    private Button moreOptionsButton;
    @FXML
    private Button reviewCustomerButton;


    public void toEmployee(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EmployeeLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toManageCustomers(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller_ManageCustomers.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void toManageAccounts(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller_ManageAccounts.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void toManageTransactions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller_ManageTransactions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void toMoreOptions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller_MoreOptions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void toReviewCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller_ReviewCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void initialize(){
        // Customer Button
        returnEmployeeButton.setOnMouseEntered(event -> returnEmployeeButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnEmployeeButton.setOnMouseExited(event -> returnEmployeeButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        manageCustomersButton.setOnMouseEntered(event -> manageCustomersButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        manageCustomersButton.setOnMouseExited(event -> manageCustomersButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        manageAccountsButton.setOnMouseEntered(event -> manageAccountsButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        manageAccountsButton.setOnMouseExited(event -> manageAccountsButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        manageTransactionsButton.setOnMouseEntered(event -> manageTransactionsButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        manageTransactionsButton.setOnMouseExited(event -> manageTransactionsButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        moreOptionsButton.setOnMouseEntered(event -> moreOptionsButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        moreOptionsButton.setOnMouseExited(event -> moreOptionsButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        reviewCustomerButton.setOnMouseEntered(event -> reviewCustomerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        reviewCustomerButton.setOnMouseExited(event -> reviewCustomerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
    }

}

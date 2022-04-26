package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class z_SystemMain {

     Stage stage;
     Scene scene;
     Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button logoButton;
    @FXML
    private Button selectActorButton;
    @FXML
    private Button checkPointButton;
    @FXML
    private Button runTestsButton;
    @FXML
    private Button loadDataButton;


    /* --- MAIN ANCHOR --- */
    @FXML
    private AnchorPane mainAnchor;


    /* --- SELECT ACTOR ANCHOR --- */
    @FXML
    private AnchorPane selectActorAnchor;
    @FXML
    private Button customerButton;
    @FXML
    private Button employeeButton;


    /* --- CHECK POINT ANCHOR --- */
    @FXML
    private AnchorPane checkPointAnchor;

    /* --- RUN TESTS ANCHOR --- */
    @FXML
    private AnchorPane runTestsAnchor;
    @FXML
    private Button runtTestButton;

    /* --- VIEW TABLES/ LOAD DATA ANCHOR --- */
    @FXML
    private AnchorPane loadDataAnchor;

    /* --- NAV FUNCTIONS --- */
    public void toSystemMain(ActionEvent event) throws IOException {
        mainAnchor.setVisible(true);
        selectActorAnchor.setVisible(false);
        checkPointAnchor.setVisible(false);
        runTestsAnchor.setVisible(false);
        loadDataAnchor.setVisible(false);
        generalTestsAnchor.setVisible(false);
        testTitleLabel.setText("Please select the tests you would like to run.");
    }

    public void toSelectActor(ActionEvent event) throws IOException {
       mainAnchor.setVisible(false);
       selectActorAnchor.setVisible(true);
       checkPointAnchor.setVisible(false);
       runTestsAnchor.setVisible(false);
       loadDataAnchor.setVisible(false);
        generalTestsAnchor.setVisible(false);
        testTitleLabel.setText("Please select the tests you would like to run.");
    }

    public void toCheckPoint(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        selectActorAnchor.setVisible(false);
        checkPointAnchor.setVisible(true);
        runTestsAnchor.setVisible(false);
        loadDataAnchor.setVisible(false);
        generalTestsAnchor.setVisible(false);
        testTitleLabel.setText("Please select the tests you would like to run.");
    }

    public void toRunTests(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        selectActorAnchor.setVisible(false);
        checkPointAnchor.setVisible(false);
        runTestsAnchor.setVisible(true);
        loadDataAnchor.setVisible(false);
        generalTestsAnchor.setVisible(false);
        testTitleLabel.setText("Please select the tests you would like to run.");
    }

    public void toLoadData(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        selectActorAnchor.setVisible(false);
        checkPointAnchor.setVisible(false);
        runTestsAnchor.setVisible(false);
        loadDataAnchor.setVisible(true);
        generalTestsAnchor.setVisible(false);
        testTitleLabel.setText("Please select the tests you would like to run.");
    }

    public void toCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        testTitleLabel.setText("Please select the tests you would like to run.");
    }

    public void toEmployee(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EmployeeLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        testTitleLabel.setText("Please select the tests you would like to run.");
    }

    @FXML
    private AnchorPane generalTestsAnchor;
    @FXML
    private Button generalTestsButton;
    @FXML
    private Label testTitleLabel;
    @FXML
    private Label createCustLabel;
    @FXML
    private ImageView createCustSuccess;
    @FXML
    private ImageView createCustFail;
    @FXML
    private Label deleteCustLabel;
    @FXML
    private ImageView deleteCustSuccess;
    @FXML
    private ImageView deleteCustFail;


    public void runGeneralTests(ActionEvent event) {
        // General Tests Include:
        testTitleLabel.setText("Running general tests...");
        createCustLabel.setVisible(true);
        deleteCustLabel.setVisible(true);
        generalTestsAnchor.setVisible(true);
        // - Create/Deleting Customers
        ArrayList<Customer> testingList = null;
        Customer cust = null;
        int found = 0;
        try {
            //Initializing ArrayList and test Customer.
            ArrayList<Customer> customers = DB.readCustomersCSV();
            Customer testCustomer = new Customer("000-00-0000", "0000 Address St", "Saint Joseph","MO",00000,
                    "Jane","Doe","000000000",0000,0000);
            //Ensuring we are not adding a duplicate.
            int duplicate = 0;
            for(int i =0; i < customers.size(); i++) {
                if (customers.get(i).getSSN().equals(testCustomer.getSSN())) {
                    duplicate++;
                } //End of if.
            }
            //If there was not a duplicate and customer was added...
            if(duplicate == 0) {
                customers.add(testCustomer);
                DB.writeCustomerCSV(customers);
                //Ensure the customer is in the file using SSN.
                testingList = DB.readCustomersCSV();
                for (int i = 0; i < testingList.size(); i++) {
                    if (testingList.get(i).getSSN().equals(testCustomer.getSSN())) {
                        cust = testingList.get(i);
                        found++;
                    } //End of if.
                } //End of for.
                //Write result to interface.
                if (found < 1) {
                    createCustLabel.setText("Customer not found in customers.csv.");
                    createCustFail.setVisible(true);
                } else {
                    createCustLabel.setText("Customer successfully created and added!");
                    createCustSuccess.setVisible(true);
                } //End of else.
            } else {
                createCustLabel.setText("Could not add customer. A customer with the test SSN already exists.");
                createCustFail.setVisible(true);
            } //End of else.
        } catch (IOException e) {
            createCustLabel.setText("Error reading/writing to customers.csv.");
            createCustFail.setVisible(true);
        } //End of catch.

        //Now delete the created Customer.
        if(found == 1) {
            testingList.remove(cust);
            try {
                DB.writeCustomerCSV(testingList);
                found = 0;
                for (int i = 0; i < testingList.size(); i++) {
                    if (testingList.get(i).getSSN().equals(cust.getSSN())) {
                        found++;
                    } //End of if.
                    if (found != 0) {
                        deleteCustLabel.setText("Customer still found in customers.csv");
                        deleteCustFail.setVisible(true);
                    } else {
                        deleteCustLabel.setText("Customer successfully deleted from customers.csv");
                        deleteCustSuccess.setVisible(true);
                    }
                } //End of for.
            } catch (IOException e) {
                deleteCustLabel.setText("Error reading/writing to customers.csv.");
                deleteCustFail.setVisible(true);
            }
        } else {
            deleteCustLabel.setText("Customer to delete was not found.");
            deleteCustFail.setVisible(true);
        }
        // - Creating/Deleting Employees
        // - Reviewing a Customer and all accounts
    }

    public void runCheckingTests(ActionEvent event) {

    }

    public void runSavingsTests(ActionEvent event) {

    }

    public void runLoanTests(ActionEvent event) {

    }

    @FXML
    private void initialize(){
        /* --- SYSTEM --- */
        // Actor Button
        selectActorButton.setOnMouseEntered(event -> selectActorButton.setStyle("-fx-background-color: #cc0000"));
        selectActorButton.setOnMouseExited(event -> selectActorButton.setStyle("-fx-background-color:  #990000"));
        // Load Data Button
        loadDataButton.setOnMouseEntered(event -> loadDataButton.setStyle("-fx-background-color: #cc0000"));
        loadDataButton.setOnMouseExited(event -> loadDataButton.setStyle("-fx-background-color: #990000"));
        // Run Tests Button
        runTestsButton.setOnMouseEntered(event -> runTestsButton.setStyle("-fx-background-color: #cc0000"));
        runTestsButton.setOnMouseExited(event -> runTestsButton.setStyle("-fx-background-color:  #990000"));
        // Check Point Button
        checkPointButton.setOnMouseEntered(event -> checkPointButton.setStyle("-fx-background-color: #cc0000"));
        checkPointButton.setOnMouseExited(event -> checkPointButton.setStyle("-fx-background-color:  #990000"));

        logoButton.setOnMouseEntered(event -> logoButton.setStyle("-fx-background-color: #cc0000"));
        logoButton.setOnMouseExited(event -> logoButton.setStyle("-fx-background-color:  #990000"));
        // Customer Button
        customerButton.setOnMouseEntered(event -> customerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        customerButton.setOnMouseExited(event -> customerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        // Employee Button
        employeeButton.setOnMouseEntered(event -> employeeButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color:#000000;"));
        employeeButton.setOnMouseExited(event -> employeeButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0;"));

    }


}

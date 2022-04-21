package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class z_Manager_ManageEmployees {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button returnTellerButton;
    @FXML
    private Button createEmployeeButton;
    @FXML
    private Button deleteEmployeeButton;


    /* --- CREATE ANCHOR DATA --- */
    @FXML
    private AnchorPane createAnchor;
    @FXML
    private TextField idText;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField fNameText;
    @FXML
    private TextField lNameText;
    @FXML
    private ComboBox<String> empTypeBox;
    @FXML
    private Button cCreateButton;

    /* --- DELETE ANCHOR DATA --- */
    @FXML
    private AnchorPane deleteAnchor;
    @FXML
    private Button dDeleteButton;
    @FXML
    private TextField dIDButton;
    @FXML
    private Label deleteMessage;

    /* --- MAIN ANCHOR DATA --- */
    @FXML
    private AnchorPane mainAnchor;

    /* --- NAV FUNCTIONS --- */
    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    public void displayCreate(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        deleteAnchor.setVisible(false);
        createAnchor.setVisible(true);

    } //End of displayCreate.

    public void displayDelete(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        createAnchor.setVisible(false);
        deleteAnchor.setVisible(true);
    } //End of displayDelete.

    public void createEmployee (ActionEvent event) throws IOException {
        ArrayList<Employee> employees = DB.readEmployeeCSV();
        int empType;
        if (empTypeBox.getValue().equals("Manager")) {
            empType = 1;
        } else {
            empType = 0;
        }
        Employee employee = new Employee(idText.getText(), lNameText.getText(),
                fNameText.getText(), empType, passwordText.getText());
        employees.add(employee);
        DB.writeEmployeeCSV(employees);
        idText.clear();
        lNameText.clear();
        fNameText.clear();
        passwordText.clear();
    }
    public void deleteEmployee (ActionEvent event) throws IOException {
        ArrayList<Employee> employees = DB.readEmployeeCSV();
        // find customer with matching ssn

        if (DB.verifyEmployee(dIDButton.getText(), employees)) {
            for (Employee emp: employees) {
                if (emp.getEmployeeID().equals(dIDButton.getText())) {
                    // remove customer if matches
                    employees.remove(emp);
                    deleteMessage.setText("Deleted!");
                    deleteMessage.setTextFill(Color.GREEN);
                    break;
                }
            }
        } else {
            deleteMessage.setText("Invalid ID!");
            deleteMessage.setTextFill(Color.RED);
        }
        DB.writeEmployeeCSV(employees);
        dIDButton.clear();
    }

    @FXML
    private void initialize(){
        // Customer Button
        returnTellerButton.setOnMouseEntered(event -> returnTellerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnTellerButton.setOnMouseExited(event -> returnTellerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        createEmployeeButton.setOnMouseEntered(event -> createEmployeeButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        createEmployeeButton.setOnMouseExited(event -> createEmployeeButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        deleteEmployeeButton.setOnMouseEntered(event -> deleteEmployeeButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        deleteEmployeeButton.setOnMouseExited(event -> deleteEmployeeButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));

        empTypeBox.getItems().addAll("Manager","Teller");
        empTypeBox.setVisibleRowCount(2);
    }
}

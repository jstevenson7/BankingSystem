package edu.missouriwestern.csc406.bankingsystem;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Tables  {

    //CUSTOMER TABLE
   /* @FXML
    private TableView<Customer> customerTable;

   @FXML
    private TableColumn<Customer, String> ssn;
    @FXML
    private TableColumn<Customer,String> address;
    @FXML
    private TableColumn<Customer,String> city;
    @FXML
    private TableColumn<Customer,String> state;
    @FXML
    private TableColumn<Customer,Integer> zip;
    @FXML
    private TableColumn<Customer,String> firstName;
    @FXML
    private TableColumn<Customer,String> lastName;
    @FXML
    private TableColumn<Customer,String> atmNumber;
    @FXML
    private TableColumn<Customer,Integer> atmPin;
    @FXML
    private TableColumn<Customer,Integer> creditCardPin;

    ObservableList<Customer> customersList = FXCollections.observableArrayList(
            new Customer("000-00-0000", "0000 Address St", "Saint Joseph","MO",00000,
                    "Jane","Doe","000000000",0000,0000)
    );

    *//* private String SSN;
    private String Address;
    private String City;
    private String State;
    private int Zip;
    private String firstName;
    private String lastName;
    private String atmNumber;
    private int atmPin;
    private int creditCardPin;*//*

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ssn.setCellValueFactory(new PropertyValueFactory<Customer, String>("SSN"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("Address"));
        city.setCellValueFactory(new PropertyValueFactory<Customer, String>("City"));
        state.setCellValueFactory(new PropertyValueFactory<Customer, String>("State"));
        zip.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("Zip"));
        firstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        atmNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("atmNumber"));
        atmPin.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("atmPin"));
        creditCardPin.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("creditCardPin"));
        customerTable.setItems(customersList);
    }
*/





}

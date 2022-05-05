package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.ParseException;
import java.util.ArrayList;
import java.io.IOException;

public class z_Teller_ManageCustomers {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* --- NAV BUTTONS --- */
    @FXML
    private Button returnTellerButton;
    @FXML
    private Button createACustomerButton;
    @FXML
    private Button deleteACustomerButton;

    /* --- CREATE ANCHOR DATA --- */
    @FXML
    private AnchorPane createAnchor;
    @FXML
    private TextField cSSNText;
    @FXML
    private TextField cFNameText;
    @FXML
    private TextField cATMPinText;
    @FXML
    private TextField cCCPinText;
    @FXML
    private TextField cLNameText;
    @FXML
    private TextField cAddressText;
    @FXML
    private TextField cCityText;
    @FXML
    private ComboBox<String> cStateBox;
    @FXML
    private TextField cZipText;
    @FXML
    private ComboBox<String> cAccountBox;
    @FXML
    private Button cCreateButton;
    @FXML
    private TextField deleteCustomerSSN;
    @FXML
    private Label messageLabel;

    /* --- DELETE ANCHOR DATA --- */

    @FXML
    private AnchorPane deleteAnchor;
    @FXML
    private Button dDeleteButton;
    @FXML
    private Label deleteMessage;

    /* --- MAIN ANCHOR DATA --- */
    @FXML
    private AnchorPane mainAnchor;


    /* --- NAV FUNCTIONS --- */
    public void toTeller(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Teller.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    } //End of toTeller.

    public void createCustomer(ActionEvent event) throws IOException, ParseException {
        //Load customers
        ArrayList<Customer> customers = DB.readCustomersCSV();
        // if SSN doesn't already exist
        if (cSSNText.getText().isBlank() || cAddressText.getText().isBlank() || cCityText.getText().isBlank() ||
                cStateBox.getValue().isBlank() || cZipText.getText().isBlank() || cFNameText.getText().isBlank() ||
                cLNameText.getText().isBlank() || cATMPinText.getText().isBlank() ||
                cCCPinText.getText().isBlank()) {
            messageLabel.setText("All fields are required!");
            messageLabel.setTextFill(Color.RED);
        } else if (cSSNText.getText().length() < 11 || cSSNText.getText().length() > 11){
            messageLabel.setText("Invalid SSN!");
            messageLabel.setTextFill(Color.RED);
        } else if(DB.verifyCustomerSSN(cSSNText.getText(), customers)){
            messageLabel.setText("Customer already exists!");
            messageLabel.setTextFill(Color.RED);
        }else if (!DB.verifyCustomer(cSSNText.getText(), customers)) {
            // Create customer
            try {
                Customer newCust = new Customer(cSSNText.getText(), cAddressText.getText(), cCityText.getText(),
                        cStateBox.getValue(), Integer.parseInt(cZipText.getText()), cFNameText.getText(),
                        cLNameText.getText(), DB.generateAccountNumber(), Integer.parseInt(cATMPinText.getText()),
                        Integer.parseInt(cCCPinText.getText()));
                // Add new customer to the ArrayList
                customers.add(newCust);
                messageLabel.setText("Success!");
                messageLabel.setTextFill(Color.GREEN);
                DB.writeCustomerCSV(customers);
                // check for type of account in cAccountBox
                // if Checking - TMB = create new checking account of balance $0
                if (cAccountBox.getValue().equals("Checking - TMB")) {
                    // Read in current checking accounts
                    ArrayList<Checking> checkings = DB.readCheckingCSV();
                    // create new checking account
                    Checking checking = new Checking(DB.generateAccountNumber(), 0, 0, cSSNText.getText());
                    // add checking account
                    checkings.add(checking);
                    // write checking accounts
                    DB.writeCheckingCSV(checkings);
                }
                // if Checking - Gold/Diamond = create new checking account of balance $1000
                if (cAccountBox.getValue().equals("Checking - Gold/Diamond")) {
                    // Read in current checking accounts
                    ArrayList<Checking> checkings = DB.readCheckingCSV();
                    // create new checking account
                    Checking checking = new Checking(DB.generateAccountNumber(), 1000, 1, cSSNText.getText());
                    // add checking account
                    checkings.add(checking);
                    // write checking accounts
                    DB.writeCheckingCSV(checkings);
                }
                // if Savings - Simple = new Savings of balance $0
                if (cAccountBox.getValue().equals("Savings - Simple")){
                    ArrayList<Savings> savings = DB.readSavingsCSV();
                    Savings savings1 = new Savings(DB.generateAccountNumber(), 0, 0, cSSNText.getText());
                    savings.add(savings1);
                    DB.writeSavingsCSV(savings);
                }
                // if CD = create new CD of balance $1000
                cSSNText.clear();
                cAddressText.clear();
                cCityText.clear();
                cZipText.clear();
                cFNameText.clear();
                cLNameText.clear();
                cATMPinText.clear();
                cCCPinText.clear();
                cAccountBox.setValue("");
                cStateBox.setValue("");
            } catch (NumberFormatException e) {
                messageLabel.setText("Error: Verify correct values are entered.");
                messageLabel.setTextFill(Color.RED);
            }
        }





    }
    public void deleteCustomer(ActionEvent event) throws IOException {
        // Read customers from database/csv
        ArrayList<Customer> customers = DB.readCustomersCSV();
        // find customer with matching ssn
        if (DB.verifyCustomer(deleteCustomerSSN.getText(), customers)) {
            for (Customer cust: customers) {
                if (cust.getSSN().equals(deleteCustomerSSN.getText())) {
                    // remove customer if matches
                    customers.remove(cust);
                    deleteMessage.setText("Deleted!");
                    deleteMessage.setTextFill(Color.GREEN);
                    break;
                }
            }
        } else {
            deleteMessage.setText("Invalid SSN!");
            deleteMessage.setTextFill(Color.RED);
        }

        // write customers back to database/csv
        DB.writeCustomerCSV(customers);
        // clear box
        deleteCustomerSSN.clear();
    }
/*
    //method for setting up and rolling out a deletingCustomers and their accounts
    public void deleteingCustomerAccounts(ActionEvent event) throws IOException {
        //read in customers from the DB
        ArrayList<Customer> customers = DB.readCustomersCSV();
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        ArrayList<Savings> savings = DB.readSavingsCSV();
        ArrayList<Loans> loans = DB.readLoansCSV();

        double balanceToReturn = 0;

        //(?) get from options(Need to create drop down method, gonna give a stab at it -RM)
        if(DB.verifyCustomer(deleteCustomerSSN.getText(), customers))
        {
            for(Customer cust: customers)
            {
                if(cust.getSSN().equals(deleteCustomerSSN.getText()))
                {
                     //Holds this for now, still getting used to JavaFX/Need to make the drop menu
                     switch(dDeleteOptions.getValue())
                     {
                         case "Delete Checking":
                             balanceToReturn = balanceToReturn + DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, loans,0);
                             deleteMessage.setText("Deleted Checking!");
                             deleteMessage.setTextFill(Color.GREEN);
                         case "Delete Savings":
                             balanceToReturn = balanceToReturn + DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, loans, 1);
                             deleteMessage.setText("Deleted Savings");
                             deleteMessage.setTextFill(Color.GREEN);
                         case "Delete Checking and Savings":
                             balanceToReturn = balanceToReturn + DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, loans, 2);
                             deleteMessage.setText("Deleted Checking and Savings!");
                             deleteMessage.setTextFill(Color.GREEN);
                         case "Delete Checking, Savings, and Customer":
                             balanceToReturn = balanceToReturn + DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, loans, 3);
                             deleteMessage.setText("Deleted Checking, Savings, and Customer!");
                             deleteMessage.setTextFill(Color.GREEN);
                         case "Delete Loans":
                             balanceToReturn = balanceToReturn + DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, loans, 4);
                             deleteMessage.setText("Deleted Loans!");
                             deleteMessage.setTextFill(Color.GREEN);
                         case "Delete Checking, Savings, and Loans":
                             balanceToReturn = balanceToReturn + DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, loans, 5);
                             deleteMessage.setText("Deleted Checking, Savings, and Loans!");
                             deleteMessage.setTextFill(Color.GREEN);
                         case "Delete Checking, Savings, Loans, and Customer":
                             balanceToReturn = balanceToReturn + DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, loans, 6);
                             deleteMessage.setText("Deleted Checking, Savings, Loans, and Customer!");
                             deleteMessage.setTextFill(Color.GREEN);
                         default:
                             deleteMessage.setText("Invalid Delete Option!");
                             deleteMessage.setTextFill(Color.RED);
                     }
                    //DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, 7);
                }
            }
        }
        else
        {
            deleteMessage.setText("Invalid SSN!");
            deleteMessage.setTextFill(Color.RED);
        }

        // write customers back to database/csv
        DB.writeCustomerCSV(customers);
        //write checking back
        DB.writeCheckingCSV(checkings);
        //write savings back
        DB.writeSavingsCSV(savings);
        // clear box
        deleteCustomerSSN.clear();
    }
*/
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


    public void deleteingCustomerAccounts(ActionEvent event) throws IOException {
        //read in customers from the DB
        ArrayList<Customer> customers = DB.readCustomersCSV();
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        ArrayList<Savings> savings = DB.readSavingsCSV();
        ArrayList<Loans> loans = DB.readLoansCSV();

        if(deleteCustomerSSN.getText().isBlank()) {
            deleteMessage.setText("All fields required!");
            deleteMessage.setTextFill(Color.RED);
        } else {
             if(DB.verifyCustomer(deleteCustomerSSN.getText(), customers)) {

                DB.CustomerManagers(deleteCustomerSSN.getText(), customers, checkings, savings, loans, 6);
                deleteMessage.setText("Success! Customer and all linked accounts deleted!");
                deleteMessage.setTextFill(Color.GREEN);
                deleteCustomerSSN.clear();
            } else {
                 deleteMessage.setText("Customer not found!");
                 deleteMessage.setTextFill(Color.RED);
            }
        }
    }


    /* --- INITIALIZE --- */
    @FXML
    private void initialize(){
        // Customer Button
        returnTellerButton.setOnMouseEntered(event -> returnTellerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        returnTellerButton.setOnMouseExited(event -> returnTellerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        createACustomerButton.setOnMouseEntered(event -> createACustomerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        createACustomerButton.setOnMouseExited(event -> createACustomerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        deleteACustomerButton.setOnMouseEntered(event -> deleteACustomerButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        deleteACustomerButton.setOnMouseExited(event -> deleteACustomerButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        cStateBox.getItems().addAll("AL","AK","AZ","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI",
                "MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX",
                "UT","VT","VA","WA","WV","WI","WY");
        cStateBox.setVisibleRowCount(9);
        cAccountBox.getItems().addAll("Checking - TMB", "Checking - Gold/Diamond", "Savings - Simple", "CD");
        cAccountBox.setVisibleRowCount(5);

        cSSNText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cFNameText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cLNameText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cAddressText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cCityText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cStateBox.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cZipText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cAccountBox.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cATMPinText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");
        cCCPinText.setStyle(" -fx-focus-color: #ffc7c7; -fx-faint-focus-color: #ffc7c7");

        dDeleteButton.setOnMouseEntered(event -> dDeleteButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color:#000000;"));
        dDeleteButton.setOnMouseExited(event -> dDeleteButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0;"));
        cCreateButton.setOnMouseEntered(event -> cCreateButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color:#000000;"));
        cCreateButton.setOnMouseExited(event -> cCreateButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0;"));
    }
}

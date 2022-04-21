package edu.missouriwestern.csc406.bankingsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private TextField cCustIDText;
    @FXML
    private TextField cATMNumText;
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

    /* --- DELETE ANCHOR DATA --- */
    @FXML
    private AnchorPane deleteAnchor;
    @FXML
    private Button dDeleteButton;

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

    public void displayCreate(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        deleteAnchor.setVisible(false);
        createAnchor.setVisible(true);

        //Load customers
        ArrayList<Customer> customers = DB.readCustomersCSV();
        //Placeholder for future new cust, but stands in now - RM 4/20/22 17:50
        Customer newCust = new Customer(cSSNText.getText(), cAddressText.getText(), cCityText.getText(), cStateBox.getValue(), Integer.parseInt(cZipText.getText()), cFNameText.getText(), cLNameText.getText(), customers.get(customers.size() - 1).getAtmNumber() + 1, customers.get(customers.size() - 1).getAtmPin() + 1, customers.get(customers.size() - 1).getCreditCardPin() + 1, customers.get(customers.size() - 1).getCustomerID() + 1);
        customers.add(newCust);
        DB.writeCustomerCSV(customers);
    } //End of displayCreate.

    public void displayDelete(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        createAnchor.setVisible(false);
        deleteAnchor.setVisible(true);
    } //End of displayDelete.

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
        cAccountBox.getItems().addAll("Checking", "Saving", "Loan", "CD");
        cAccountBox.setVisibleRowCount(5);
    }
}

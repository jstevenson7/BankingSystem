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
import java.io.IOException;
import java.util.ArrayList;

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
    private Label ccLabel;
    @FXML
    private Button ccCreateButton;



    public void createCC(ActionEvent event) {
        try {
            if(ccSSNTF.getText().isBlank() || ccLimitTF.getText().isBlank()) {
                ccLabel.setText("All fields are required!");
                ccLabel.setTextFill(Color.RED);
            } else {
                ArrayList<Customer> customers = DB.readCustomersCSV();
                int found = 0;
                for (int i = 0; i < customers.size(); i++) {
                    if(customers.get(i).getSSN().equals(ccSSNTF.getText())) {
                        found =1;
                    }
                }
                if(found < 1) {
                    ccLabel.setText("All fields are required!");
                    ccLabel.setTextFill(Color.RED);
                } else {
                    ccLabel.setText("Success!");
                    ccLabel.setTextFill(Color.GREEN);
                    ArrayList<CreditCards> ccs = DB.readCreditCardCSV();
                    CreditCards cc = new CreditCards(DB.generateAccountNumber(),Double.parseDouble(ccLimitTF.getText()),ccSSNTF.getText(),0.0);
                    ccs.add(cc);
                    DB.writeCreditCardCSV(ccs);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    private AnchorPane establishLoanPane;
    @FXML
    private TextField loanSSNTF;
    @FXML
    private TextField loaninterestTF;
    @FXML
    private TextField loanAmtTF;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox loansCombo;  //Will determine end date
    @FXML
    private  Label loanLabel;
    @FXML
    private Button createLoanButton;

    String fDatePicker = null;
    public void getDateText(ActionEvent event) {
        String[] dateArr = String.valueOf(datePicker.getValue()).split("-");
        fDatePicker = dateArr[1] + "/" + dateArr[2] + "/" + dateArr[0];
    }

    public void createLoan(ActionEvent event) {
        try {
            ArrayList<Loans> loans = DB.readLoansCSV();
            ArrayList<Customer> customers = DB.readCustomersCSV();
            //Ensure all fields have been filled.
            loanLabel.setVisible(true);
            if (loanSSNTF.getText().isBlank() || loaninterestTF.getText().isBlank() || loanAmtTF.getText().isBlank() ||
                    datePicker.getEditor().getText().isBlank()) {
                loanLabel.setText("All fields are required!");
                loanLabel.setTextFill(Color.RED);
            } else {
                int found = 0;
                getDateText(event);
                for (int i = 0; i < customers.size(); i++) {
                    if (loanSSNTF.getText().equals(customers.get(i).getSSN())) {
                        found = 1;
                    }
                }
                if (found < 1) {
                    loanLabel.setText("Customer with SSN '" + loanSSNTF.getText() + "' not found! Try again.");
                    loanLabel.setTextFill(Color.RED);
                } else {
                    int years = 0;
                    if (loansCombo.getValue().equals("Short Term - 5 Years")) {
                        years = 5;
                    } else if(loansCombo.getValue().equals("Long Term - 15 Years")) {
                        years = 15;
                    }
                    Loans loan = new Loans(DB.generateAccountNumber(),  Double.parseDouble(loanAmtTF.getText()),
                            Double.parseDouble(loaninterestTF.getText()), fDatePicker, addYearsDate(fDatePicker,years),
                            calculateMonthlyPay( Double.parseDouble(loanAmtTF.getText()), Double.parseDouble(loaninterestTF.getText()),years),loanSSNTF.getText());
                    loans.add(loan);
                    loanLabel.setText("Success!");
                    loanLabel.setTextFill(Color.GREEN);
                    DB.writeLoansCSV(loans);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String addYearsDate(String fDatePicker, int loanType) {
        String rest = fDatePicker.substring(0,fDatePicker.length()-4);
       int year = Integer.parseInt(fDatePicker.substring(fDatePicker.length()-4));
       switch (loanType) {
           case 5:
               year += 5;
               break;
           case 15:
               year += 15;
               break;
       }
       String putBack = rest + year;
       return putBack;
    }


    public double calculateMonthlyPay(double loanAmt, double interest, int loanType) {
        double monthlyPayment = 0;
        switch (loanType) {
            case 5:
                monthlyPayment =  (loanAmt + (loanAmt/2) * (5 * interest))/60;
                break;
            case 15:
                monthlyPayment = (loanAmt + (loanAmt/2) * (15 * interest))/60;
                break;
        }
        return  monthlyPayment;
    }




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
        toCCButton.setOnMouseEntered(event -> toCCButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        toCCButton.setOnMouseExited(event -> toCCButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        toLoansButton.setOnMouseEntered(event -> toLoansButton.setStyle("-fx-background-color: #E8ADAD; -fx-border-color: #000000"));
        toLoansButton.setOnMouseExited(event -> toLoansButton.setStyle("-fx-background-color: #d4d4d4; -fx-border-color:  #b0b0b0"));
        loansCombo.getItems().addAll("Short Term - 5 Years", "Long Term - 15 Years");
    }
}
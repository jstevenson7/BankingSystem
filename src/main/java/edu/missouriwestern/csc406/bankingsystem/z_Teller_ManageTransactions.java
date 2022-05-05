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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

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
    } //End of displayCreate.

    public void displayChecks(ActionEvent event) throws IOException {
        mainAnchor.setVisible(false);
        depositAnchor.setVisible(false);
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


    /* ------------- MAIN ANCHOR ------------- */
    @FXML
    private AnchorPane mainAnchor;

    /* ------------- DEPOSIT ANCHOR ------------- */
    // DEPOSIT ANCHOR DATA
    @FXML
    private AnchorPane depositAnchor;
    @FXML
    private DatePicker d_dateDP;
    String dfDatePicker;
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
    @FXML
    private Label d_message;

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
    String wfDatePicker;
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
    @FXML
    private Label w_message;

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
    private TextField t_ssnTF;
    @FXML
    private ComboBox t_fromAcctTypeCB;
    @FXML
    private TextField t_fromAcctNumTF;
    @FXML
    private TextField t_amountTF;
    @FXML
    private DatePicker t_dateDP;
    String tfDatePicker;
    @FXML
    private Label t_message;


    /* ------------ Logic ------------- */
    public void deposit(ActionEvent event) throws IOException {
        if (!d_ssnTF.getText().isBlank() && dfDatePicker!=null &&!d_acctNumTF.getText().isBlank() && !d_amountTF.getText().isBlank()) {
            ArrayList<Customer> customers = DB.readCustomersCSV();
            // valid SSN in customers
            if (DB.verifyCustomerSSN(d_ssnTF.getText(), customers)) {
                // get customer and read in checkings accounts and customers checking account
                Customer customer = DB.searchCustomer(d_ssnTF.getText(), customers);
                ArrayList<Checking> checkings = DB.readCheckingCSV();
                Checking checking = DB.searchChecking(d_ssnTF.getText(), checkings);
                // if it is a TMB account
                if (d_acctTypeCB.getValue().equals("Checking - TMB") && checking.getAccountType()==0) {
                    if (checking.getCheckingAcctNum().equals(d_acctNumTF.getText())) {
                        if (d_checkCKB.isSelected()) {
                                // deposit
                                checking.deposit(Double.valueOf(d_amountTF.getText()));
                                // add transaction
                                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                                Transaction transaction = new Transaction(DB.generateTransactionNumber(), d_ssnTF.getText(), (String) d_acctTypeCB.getValue(),
                                        d_acctNumTF.getText(), Double.parseDouble(d_amountTF.getText()), "Deposit", dfDatePicker, d_checkNumTF.getText());
                                transactions.add(transaction);
                                DB.writeTransactionsCSV(transactions);
                                DB.writeCheckingCSV(checkings);
                                d_message.setText("Success!");
                                d_message.setTextFill(Color.GREEN);
                                d_ssnTF.clear();
                                d_acctNumTF.clear();
                                d_amountTF.clear();
                        } else {
                                // No check transaction
                                // deposit
                                checking.deposit(Double.valueOf(d_amountTF.getText()));
                                // add transaction
                                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                                Transaction transaction = new Transaction(DB.generateTransactionNumber(), d_ssnTF.getText(), (String) d_acctTypeCB.getValue(),
                                        d_acctNumTF.getText(), Double.parseDouble(d_amountTF.getText()),"Deposit", dfDatePicker, "0");
                                transactions.add(transaction);
                                DB.writeTransactionsCSV(transactions);
                                DB.writeCheckingCSV(checkings);
                                d_message.setText("Success!");
                                d_message.setTextFill(Color.GREEN);
                                d_ssnTF.clear();
                                d_acctNumTF.clear();
                                d_amountTF.clear();
                        }
                    } else {
                        d_message.setText("Unknown Account Number!");
                        d_message.setTextFill(Color.RED);
                    }
                } else if (d_acctTypeCB.getValue().equals("Checking - Gold/Diamond")) {
                    if (checking.getCheckingAcctNum().equals(d_acctNumTF.getText())) {
                        if (d_checkCKB.isSelected()) {
                            // deposit
                            checking.deposit(Double.valueOf(d_amountTF.getText()));
                            // add transaction
                            ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                            Transaction transaction = new Transaction(DB.generateTransactionNumber(), d_ssnTF.getText(), (String) d_acctTypeCB.getValue(),
                                    d_acctNumTF.getText(), Double.parseDouble(d_amountTF.getText()),"Deposit", dfDatePicker, d_checkNumTF.getText());
                            transactions.add(transaction);
                            DB.writeTransactionsCSV(transactions);
                            DB.writeCheckingCSV(checkings);
                            d_message.setText("Success!");
                            d_message.setTextFill(Color.GREEN);
                            d_ssnTF.clear();
                            d_acctNumTF.clear();
                            d_amountTF.clear();
                        } else {
                            // deposit
                            checking.deposit(Double.valueOf(d_amountTF.getText()));
                            // add transaction
                            ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                            Transaction transaction = new Transaction(DB.generateTransactionNumber(), d_ssnTF.getText(), (String) d_acctTypeCB.getValue(),
                                    d_acctNumTF.getText(), Double.parseDouble(d_amountTF.getText()),"Deposit", dfDatePicker, "0");
                            transactions.add(transaction);
                            DB.writeTransactionsCSV(transactions);
                            DB.writeCheckingCSV(checkings);
                            d_message.setText("Success!");
                            d_message.setTextFill(Color.GREEN);
                            d_ssnTF.clear();
                            d_acctNumTF.clear();
                            d_amountTF.clear();
                        }
                    } else {
                        d_message.setText("Unknown Account Number!");
                        d_message.setTextFill(Color.RED);
                    }
                } else if (d_acctTypeCB.getValue().equals("Savings - Simple")) {
                    ArrayList<Savings> savings = DB.readSavingsCSV();
                    Savings savings1 = DB.searchSavings(d_ssnTF.getText(), savings);
                    if (savings1.getSavingsAcctNum().equals(d_acctNumTF.getText())) {
                        // deposit
                        savings1.deposit(Double.valueOf(d_amountTF.getText()));
                        // add transaction
                        ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                        Transaction transaction = new Transaction(DB.generateTransactionNumber(), d_ssnTF.getText(), (String) d_acctTypeCB.getValue(),
                                d_acctNumTF.getText(), Double.parseDouble(d_amountTF.getText()),"Deposit", dfDatePicker, "0");
                        transactions.add(transaction);
                        DB.writeTransactionsCSV(transactions);
                        DB.writeSavingsCSV(savings);
                        d_message.setText("Success!");
                        d_message.setTextFill(Color.GREEN);
                        d_ssnTF.clear();
                        d_acctNumTF.clear();
                        d_amountTF.clear();
                    } else {
                        d_message.setText("Unknown Account Number!");
                        d_message.setTextFill(Color.RED);
                    }
                } else if (d_acctTypeCB.getValue().equals("CD")) {
                    // once we get cd working
                } else {
                    d_message.setText("Unknown Account Type!");
                    d_message.setTextFill(Color.RED);
                }
            } else {
                d_message.setText("Unknown SSN");
                d_message.setTextFill(Color.RED);
            }
        } else {
            d_message.setText("All fields are required!");
            d_message.setTextFill(Color.RED);
        }
    }
    public void withDraw(ActionEvent event) throws IOException {
        // Read in customers, checking and savings accounts from database/csv
        ArrayList<Customer> customers = DB.readCustomersCSV();
        ArrayList<Checking> checkings = DB.readCheckingCSV();
        ArrayList<Savings> savings = DB.readSavingsCSV();
        Savings savings1 = DB.searchSavings(w_ssnTF.getText(), savings);
        // If fields are filled
        if (!w_ssnTF.getText().isBlank() && wfDatePicker!=null && !w_amountTF.getText().isBlank() && !w_acctNumTF.getText().isBlank() && w_acctTypeCB.getValue()!=null) {
            if (DB.verifyCustomerSSN(w_ssnTF.getText(), customers)) {
                Customer customer = DB.searchCustomer(w_ssnTF.getText(), customers);
                // Verify account type and check against customer account type
                Checking checking = DB.searchChecking(w_ssnTF.getText(), checkings);
                if (w_acctTypeCB.getValue().equals("Checking - TMB") && checking.getAccountType() == 0) {
                    // verify account numbers
                    if (checking.getCheckingAcctNum().equals(w_acctNumTF.getText())) {
                        // check for sufficient balance or overdraft
                        if (DB.verifyBalance(Double.valueOf(w_amountTF.getText()), checking)) {
                            checking.withdraw(Double.valueOf(w_amountTF.getText()));
                            ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                            Transaction transaction = new Transaction(DB.generateTransactionNumber(), w_ssnTF.getText(), (String) w_acctTypeCB.getValue(),
                                    w_acctNumTF.getText(), (0 - Double.parseDouble(w_amountTF.getText())), "Withdrawal", wfDatePicker, "0");
                            transactions.add(transaction);
                            DB.writeTransactionsCSV(transactions);
                            DB.writeCheckingCSV(checkings);
                            w_message.setText("Success!");
                            w_message.setTextFill(Color.GREEN);
                            w_ssnTF.clear();
                            w_acctNumTF.clear();
                            w_amountTF.clear();
                        } else if (DB.verifyOverdraft(checking)) {
                            int exitCode = DB.overdraft(checking, Double.valueOf(w_amountTF.getText()));
                            if (exitCode==0) {
                                // Success
                                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                                Transaction transaction = new Transaction(DB.generateTransactionNumber(), w_ssnTF.getText(), (String) w_acctTypeCB.getValue(),
                                        w_acctNumTF.getText(), (0 - Double.parseDouble(w_amountTF.getText())), "Withdrawal", wfDatePicker, "0");
                                transactions.add(transaction);
                                DB.writeTransactionsCSV(transactions);
                                DB.writeCheckingCSV(checkings);
                                w_message.setText("Success [overdraft]!");
                                w_message.setTextFill(Color.GREEN);
                                w_ssnTF.clear();
                                w_acctNumTF.clear();
                                w_amountTF.clear();
                            } else {
                                // Failure
                                // error message
                                w_message.setText("Insufficient funds. Please try again. (overdraft)");
                                w_message.setTextFill(Color.RED);
                                w_amountTF.clear();
                            }
                        } else {
                            // error message
                            w_message.setText("Insufficient funds. Please try again.");
                            w_message.setTextFill(Color.RED);
                            w_amountTF.clear();
                        }
                    } else {
                        w_message.setText("Unknown Account Number!");
                        w_message.setTextFill(Color.RED);
                    }
                } else if (w_acctTypeCB.getValue().equals("Checking - Gold/Diamond") && checking.getAccountType() == 1) {
                    // verify account numbers
                    if (checking.getCheckingAcctNum().equals(w_acctNumTF.getText())) {
                        // check for sufficient balance or overdraft
                        if (DB.verifyBalance(Double.valueOf(w_amountTF.getText()), checking)) {
                            checking.withdraw(Double.valueOf(w_amountTF.getText()));
                            ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                            Transaction transaction = new Transaction(DB.generateTransactionNumber(), w_ssnTF.getText(), (String) w_acctTypeCB.getValue(),
                                    w_acctNumTF.getText(), (0 - Double.parseDouble(w_amountTF.getText())), "Withdrawal", wfDatePicker, "0");
                            transactions.add(transaction);
                            DB.writeTransactionsCSV(transactions);
                            DB.writeCheckingCSV(checkings);
                            w_message.setText("Success!");
                            w_message.setTextFill(Color.GREEN);
                            w_ssnTF.clear();
                            w_acctNumTF.clear();
                            w_amountTF.clear();
                        } else if (DB.verifyOverdraft(checking)) {
                            int exitCode = DB.overdraft(checking, Double.valueOf(w_amountTF.getText()));
                            if (exitCode==0) {
                                // Success
                                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                                Transaction transaction = new Transaction(DB.generateTransactionNumber(), w_ssnTF.getText(), (String) w_acctTypeCB.getValue(),
                                        w_acctNumTF.getText(), (0 - Double.parseDouble(w_amountTF.getText())), "Withdrawal", wfDatePicker, "0");
                                transactions.add(transaction);
                                DB.writeTransactionsCSV(transactions);
                                DB.writeCheckingCSV(checkings);
                                w_message.setText("Success [overdraft]!");
                                w_message.setTextFill(Color.GREEN);
                                w_ssnTF.clear();
                                w_acctNumTF.clear();
                                w_amountTF.clear();
                            } else {
                                // Failure
                                // error message
                                w_message.setText("Insufficient funds. Please try again. (overdraft)");
                                w_message.setTextFill(Color.RED);
                                w_amountTF.clear();
                            }
                        } else {
                            // error message
                            w_message.setText("Insufficient funds. Please try again.");
                            w_message.setTextFill(Color.RED);
                            w_amountTF.clear();
                        }
                    } else {
                        w_message.setText("Unknown Account Number!");
                        w_message.setTextFill(Color.RED);
                    }
                } else if (w_acctTypeCB.getValue().equals("Savings - Simple")) {
                    // verify account number
                    if (savings1.getSavingsAcctNum().equals(w_acctNumTF.getText())) {
                        // check for sufficient balance
                        if (DB.verifyBalance(Double.valueOf(w_amountTF.getText()), savings1)) {
                            savings1.withdraw(Double.valueOf(w_amountTF.getText()));
                            // add transaction
                            ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                            Transaction transaction = new Transaction(DB.generateTransactionNumber(), w_ssnTF.getText(), (String) w_acctTypeCB.getValue(),
                                    w_acctNumTF.getText(), (0 - Double.parseDouble(w_amountTF.getText())), "Withdrawal", wfDatePicker, "0");
                            transactions.add(transaction);
                            DB.writeTransactionsCSV(transactions);
                            DB.writeSavingsCSV(savings);
                            w_message.setText("Success!");
                            w_message.setTextFill(Color.GREEN);
                            w_ssnTF.clear();
                            w_acctNumTF.clear();
                            w_amountTF.clear();
                        } else {
                            // error message
                            w_message.setText("Insufficient funds. Please try again.");
                            w_message.setTextFill(Color.RED);
                            w_amountTF.clear();
                        }
                    } else {
                        w_message.setText("Unknown Account Number!");
                        w_message.setTextFill(Color.RED);
                    }
                } else if (w_acctTypeCB.getValue().equals("CD")) {
                    ArrayList<CD> cds = DB.readCDCSV();
                    if (DB.verifyCDSSN(w_ssnTF.getText(), cds)) {
                        CD cd = DB.searchCD(w_ssnTF.getText(), cds);
                        int exitCode = cd.withdrawamt(Double.valueOf(w_amountTF.getText()));
                        if (exitCode==0) {
                            // Transaction
                            ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                            Transaction transaction = new Transaction(DB.generateNewTransactionNumber(), w_ssnTF.getText(),
                                    "CD", w_acctNumTF.getText(), (0-Double.parseDouble(w_amountTF.getText())), "Withdrawal",
                                    wfDatePicker, "0");
                            transactions.add(transaction);
                            DB.writeTransactionsCSV(transactions);
                            DB.writeCDCSV(cds);
                            w_message.setText("Success!");
                            w_message.setTextFill(Color.GREEN);
                            w_ssnTF.clear();
                            w_acctNumTF.clear();
                            w_amountTF.clear();
                        } else if (exitCode==2) {
                            // Transaction
                            ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                            Transaction transaction = new Transaction(DB.generateNewTransactionNumber(), w_ssnTF.getText(),
                                    "CD", w_acctNumTF.getText(), (0-Double.parseDouble(w_amountTF.getText())), "Withdrawal (Early)",
                                    wfDatePicker, "0");
                            transactions.add(transaction);
                            DB.writeTransactionsCSV(transactions);
                            DB.writeCDCSV(cds);
                            w_message.setText("Success! (with early fee)");
                            w_message.setTextFill(Color.GREEN);
                            w_ssnTF.clear();
                            w_acctNumTF.clear();
                            w_amountTF.clear();
                        } else if (exitCode==1) {
                            w_message.setText("Insufficient Funds!");
                            w_message.setTextFill(Color.RED);
                        } else {
                            w_message.setText("Error!");
                            w_message.setTextFill(Color.RED);
                        }
                    } else {
                        w_message.setText("No CD account found!");
                        w_message.setTextFill(Color.RED);
                    }
                } else {
                    w_message.setText("Unknown Account Type!");
                    w_message.setTextFill(Color.RED);
                }
            } else {
                w_message.setText("Unknown SSN");
                w_message.setTextFill(Color.RED);
            }
        } else {
            w_message.setText("All fields are required!");
            w_message.setTextFill(Color.RED);
        }
    }
    public void transfer(ActionEvent event) throws IOException {
        // verify all fields are entered
        if (tfDatePicker!=null && !t_ssnTF.getText().isBlank() && !t_toAcctNumTF.getText().isBlank() &&
            !t_fromAcctNumTF.getText().isBlank() && !t_amountTF.getText().isBlank()) {
            // read in all current customers and verify
            ArrayList<Customer> customers = DB.readCustomersCSV();
            if (DB.verifyCustomerSSN(t_ssnTF.getText(), customers)) {
                // create customer instance
                Customer customer = DB.searchCustomer(t_ssnTF.getText(), customers);
                // read in checking and savings accounts
                ArrayList<Checking> checkings = DB.readCheckingCSV();
                ArrayList<Savings> savings = DB.readSavingsCSV();
                // verify TO account
                if (DB.verifyCheckingSSN(t_ssnTF.getText(), checkings) && DB.verifySavingsSSN(t_ssnTF.getText(),savings)) {
                    Checking checking = DB.searchChecking(t_ssnTF.getText(),checkings);
                    // verify checking account type
                    if (checking.getAccountType() == 1 && t_toAcctTypeCB.getValue().equals("Checking - Gold/Diamond")) {
                        // verify FROM account
                        if (DB.verifySavingsSSN(t_ssnTF.getText(), savings) && t_fromAcctTypeCB.getValue().equals("Savings - Simple")) {
                            Savings savings1 = DB.searchSavings(t_ssnTF.getText(), savings);
                            if (savings1.getSavingsAcctNum().equals(t_fromAcctNumTF.getText())) {
                                if (DB.verifyBalance(Double.valueOf(t_amountTF.getText()), savings1)) {
                                    checking.deposit(Double.valueOf(t_amountTF.getText()));
                                    savings1.withdraw(Double.valueOf(t_amountTF.getText()));
                                    ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                                    Transaction transaction = new Transaction(DB.generateTransactionNumber(), t_ssnTF.getText(), "Checking - Gold/Diamond",
                                            checking.getCheckingAcctNum(), Double.parseDouble(t_amountTF.getText()), "Transfer from Checking to Savings", tfDatePicker, "0");
                                    transactions.add(transaction);
                                    DB.writeTransactionsCSV(transactions);
                                    DB.writeCheckingCSV(checkings);
                                    DB.writeSavingsCSV(savings);
                                    t_ssnTF.clear();
                                    t_amountTF.clear();
                                    t_fromAcctNumTF.clear();
                                    t_toAcctNumTF.clear();
                                    t_message.setText("Success!");
                                    t_message.setTextFill(Color.GREEN);
                                } else {
                                    t_message.setText("Insufficient Funds!");
                                    t_message.setTextFill(Color.RED);
                                }
                            }
                        } else {
                            t_message.setText("Unknown FROM Account!");
                            t_message.setTextFill(Color.RED);
                        }
                    } else if (checking.getAccountType() == 0 && t_toAcctTypeCB.getValue().equals("Checking - TMB")) {
                        // verify FROM account
                        if (DB.verifySavingsSSN(t_ssnTF.getText(), savings) && t_fromAcctTypeCB.getValue().equals("Savings - Simple")) {
                            Savings savings1 = DB.searchSavings(t_ssnTF.getText(), savings);
                            if (savings1.getSavingsAcctNum().equals(t_fromAcctNumTF.getText())) {
                                if (DB.verifyBalance(Double.valueOf(t_amountTF.getText()), savings1)) {
                                    checking.deposit(Double.valueOf(t_amountTF.getText()));
                                    savings1.withdraw(Double.valueOf(t_amountTF.getText()));
                                    ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                                    Transaction transaction = new Transaction(DB.generateTransactionNumber(), t_ssnTF.getText(), "Checking - TMB",
                                            checking.getCheckingAcctNum(), Double.parseDouble(t_amountTF.getText()), "Transfer from Checking to Savings", tfDatePicker, "0");
                                    transactions.add(transaction);
                                    DB.writeTransactionsCSV(transactions);
                                    DB.writeCheckingCSV(checkings);
                                    DB.writeSavingsCSV(savings);
                                    t_ssnTF.clear();
                                    t_amountTF.clear();
                                    t_fromAcctNumTF.clear();
                                    t_toAcctNumTF.clear();
                                    t_message.setText("Success!");
                                    t_message.setTextFill(Color.GREEN);
                                } else {
                                    t_message.setText("Insufficient Funds!");
                                    t_message.setTextFill(Color.RED);
                                }
                            }
                        } else {
                            t_message.setText("Unknown FROM Account!");
                            t_message.setTextFill(Color.RED);
                        }
                    } else if (t_toAcctTypeCB.getValue().equals("Savings - Simple")) {
                        Savings savings1 = DB.searchSavings(t_ssnTF.getText(), savings);
                        // verify FROM account
                        if (DB.verifyCheckingSSN(t_ssnTF.getText(), checkings) && checking.getAccountType()==1 && t_fromAcctTypeCB.getValue().equals("Checking - Gold/Diamond")) {
                            if (DB.verifyBalance(Double.valueOf(t_amountTF.getText()), checking)) {
                                savings1.deposit(Double.valueOf(t_amountTF.getText()));
                                checking.withdraw(Double.valueOf(t_amountTF.getText()));
                                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                                Transaction transaction = new Transaction(DB.generateTransactionNumber(), t_ssnTF.getText(), "Savings",
                                        savings1.getSavingsAcctNum(), Double.parseDouble(t_amountTF.getText()), "Transfer from Savings to Checking", tfDatePicker, "0");
                                transactions.add(transaction);
                                DB.writeTransactionsCSV(transactions);
                                DB.writeCheckingCSV(checkings);
                                DB.writeSavingsCSV(savings);
                                t_ssnTF.clear();
                                t_amountTF.clear();
                                t_fromAcctNumTF.clear();
                                t_toAcctNumTF.clear();
                                t_message.setText("Success!");
                                t_message.setTextFill(Color.GREEN);
                            } else {
                                t_message.setText("Insufficient Funds!");
                                t_message.setTextFill(Color.RED);
                            }
                        } else if (DB.verifyCheckingSSN(t_ssnTF.getText(), checkings) && checking.getAccountType()==0 && t_fromAcctTypeCB.getValue().equals("Checking - TMB")) {
                            if (DB.verifyBalance(Double.valueOf(t_amountTF.getText()), checking)) {
                                savings1.deposit(Double.valueOf(t_amountTF.getText()));
                                checking.withdraw(Double.valueOf(t_amountTF.getText()));
                                ArrayList<Transaction> transactions = DB.readTransactionsCSV();
                                Transaction transaction = new Transaction(DB.generateTransactionNumber(), t_ssnTF.getText(), "Savings",
                                        savings1.getSavingsAcctNum(), Double.parseDouble(t_amountTF.getText()), "Transfer from Savings to Checking", tfDatePicker, "0");
                                transactions.add(transaction);
                                DB.writeTransactionsCSV(transactions);
                                DB.writeCheckingCSV(checkings);
                                DB.writeSavingsCSV(savings);
                                t_ssnTF.clear();
                                t_amountTF.clear();
                                t_fromAcctNumTF.clear();
                                t_toAcctNumTF.clear();
                                t_message.setText("Success!");
                                t_message.setTextFill(Color.GREEN);
                            } else {
                                t_message.setText("Insufficient Funds!");
                                t_message.setTextFill(Color.RED);
                            }
                        } else {
                            t_message.setText("Unknown FROM Account!");
                            t_message.setTextFill(Color.RED);
                        }
                    } else {
                        t_message.setText("Unknown Account Type!");
                        t_message.setTextFill(Color.RED);
                    }
                } else {
                    t_message.setText("Unknown TO Account!");
                    t_message.setTextFill(Color.RED);
                }
            } else {
                t_message.setText("Unknown SSN!");
                t_message.setTextFill(Color.RED);
            }
        } else {
            t_message.setText("All fields are required!");
            t_message.setTextFill(Color.RED);
        }
    }
    public void getDepositDateText(ActionEvent event) {
        String[] dateArr = String.valueOf(d_dateDP.getValue()).split("-");
        if(dateArr.length == 3) {
            dfDatePicker = dateArr[1] + "/" + dateArr[2] + "/" + dateArr[0];
        }
    }
    public void getWithdrawDateText(ActionEvent event) {
        String[] dateArr = String.valueOf(w_dateDP.getValue()).split("-");
        if(dateArr.length == 3) {
            wfDatePicker = dateArr[1] + "/" + dateArr[2] + "/" + dateArr[0];
        }
    }
    public void getTransferDateText(ActionEvent event) {
        String[] dateArr = String.valueOf(t_dateDP.getValue()).split("-");
        if(dateArr.length == 3) {
            tfDatePicker = dateArr[1] + "/" + dateArr[2] + "/" + dateArr[0];
        }
    }

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


        d_acctTypeCB.getItems().addAll("Checking - TMB", "Checking - Gold/Diamond", "Savings - Simple");
        w_acctTypeCB.getItems().addAll("Checking - TMB", "Checking - Gold/Diamond", "Savings - Simple", "CD");
        t_toAcctTypeCB.getItems().addAll("Checking - TMB", "Checking - Gold/Diamond", "Savings - Simple");
        t_fromAcctTypeCB.getItems().addAll("Checking - TMB", "Checking - Gold/Diamond", "Savings - Simple");
        d_acctTypeCB.setVisibleRowCount(5);

    } //End of initialize.









}

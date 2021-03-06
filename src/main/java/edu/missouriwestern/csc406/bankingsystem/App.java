package edu.missouriwestern.csc406.bankingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 *
 *
 */
public class App extends Application {

    public static void main(String[] args) throws IOException {
        /**
        ArrayList<Customer> customers = DB.readCustomersCSV();
        Customer customer = DB.searchCustomer(customers.get(0).getSSN(), customers);
        System.out.println(customer.getFirstName()+" "+customer.getLastName()+" "+customer.getSSN()+" "+customer.getAtmPin());

        ArrayList<Checking> checkings = DB.readCheckingCSV();
        Checking checking = DB.searchChecking(customer.getSSN(), checkings);
        System.out.println(checking.getCheckingAcctNum()+" "+checking.getBalance()+" "+checking.getSSN());

        ArrayList<Check> checks = DB.readUnprocessedCheckCSV();
        Check check = DB.searchChecks("001", checking.getCheckingAcctNum(), checks);
        System.out.println(check.getCheckNum()+" "+check.getAmount());

        ArrayList<Savings> savings = DB.readSavingsCSV();
        Savings savings1 = DB.searchSavings(customer.getSSN(), savings);
        System.out.println(savings1.getSavingsAcctNum()+" "+savings1.getBalance());

        ArrayList<Transaction> transactions = DB.readTransactionsCSV();
        Transaction transaction = DB.searchTransactions("111111111111", transactions);
        System.out.println(transaction.getTransactionNum()+" "+transaction.getCheckNum());
        **/
        launch();
    } //End of main.

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SystemMain.fxml"));
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("file:src/main/resources/Images/icon.png"));
            stage.setScene(scene);
            stage.show();
            stage.setAlwaysOnTop(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

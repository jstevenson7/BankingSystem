package edu.missouriwestern.csc406.bankingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


/**
 *
 *
 *
 */
public class App extends Application {

    public static void main(String[] args) throws IOException {
        /**
        ArrayList<Customer> customers = DB.readCustomersCSV();
        Customer customer = DB.searchCustomer(customers.get(0).getCustomerID(), customers);
        System.out.println(customer.getFirstName()+" "+customer.getLastName()+" "+customer.getCustomerID()+" "+customer.getAtmPin());

        ArrayList<Checking> checkings = DB.readCheckingCSV();
        Checking checking = DB.searchChecking(customer.getCustomerID(), checkings);
        System.out.println(checking.getAccountNumber()+" "+checking.getBalance());

        ArrayList<Check> checks = DB.readUnprocessedCheckCSV();
        Check check = DB.searchChecks("001", checking.getAccountNumber(), checks);
        System.out.println(check.getCheckID()+" "+check.getAmount());
        **/
        launch();
    } //End of main.

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SystemMain.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}

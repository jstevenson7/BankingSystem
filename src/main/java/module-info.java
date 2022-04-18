module BankingSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires com.opencsv;

    opens edu.missouriwestern.csc406.bankingsystem to javafx.fxml;
    exports edu.missouriwestern.csc406.bankingsystem;
    opens edu.missouriwestern.csc406.bankingsystem.controllers to javafx.fxml;
    exports edu.missouriwestern.csc406.bankingsystem.controllers;
}

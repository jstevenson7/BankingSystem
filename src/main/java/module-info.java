module edu.missouriwestern.csc406{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens edu.missouriwestern.csc406.bankingsystem to javafx.fxml;
    exports edu.missouriwestern.csc406.bankingsystem;
}
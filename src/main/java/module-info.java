module com.example.examples {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examples to javafx.fxml;
    exports com.example.examples;
}
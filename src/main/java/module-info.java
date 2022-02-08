module com.example.examples {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbox2d.library;


    opens com.example.examples to javafx.fxml;
    exports com.example.examples;
}
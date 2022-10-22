module com.example.multithreading2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.multithreading2 to javafx.fxml;
    exports com.example.multithreading2;
}
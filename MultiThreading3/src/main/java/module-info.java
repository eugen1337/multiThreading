module com.example.multithreading3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.multithreading3 to javafx.fxml;
    exports com.example.multithreading3;
}
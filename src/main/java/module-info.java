module com.example.p2gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.p2gui to javafx.fxml;
    exports com.example.p2gui;
}
module tuition {
    requires javafx.controls;
    requires javafx.fxml;

    opens tuition to javafx.fxml;
    exports tuition;
}
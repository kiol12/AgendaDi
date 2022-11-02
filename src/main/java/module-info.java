module com.example.agendadi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.agendadi to javafx.fxml;
    exports com.example.agendadi;
}
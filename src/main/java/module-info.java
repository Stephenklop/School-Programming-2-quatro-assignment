module com.example.quatroopdracht {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.quatroopdracht to javafx.fxml;
    exports com.example.quatroopdracht;
}
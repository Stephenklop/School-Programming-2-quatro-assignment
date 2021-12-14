module com.example.quatroopdracht {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quatroopdracht to javafx.fxml;
    exports com.example.quatroopdracht;
}
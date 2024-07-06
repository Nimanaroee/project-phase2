module com.example.phase2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens view to javafx.fxml;
    exports view;
    exports controller;
    exports model;
    opens controller to javafx.fxml;
}
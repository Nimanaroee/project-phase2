module com.example.phase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires javafx.graphics;
    requires com.google.gson;
    requires java.base;

    exports model;
    opens model to com.google.gson;
    exports view;
    opens view to javafx.fxml, com.google.gson;
    exports controller;
    opens controller to javafx.fxml, com.google.gson;
}

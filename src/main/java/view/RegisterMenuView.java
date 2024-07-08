package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Data;
import model.GraphicData;
import model.GsonHandler;

import java.io.IOException;

public class RegisterMenuView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Data.gsonHandler = new GsonHandler();
        Data.gsonHandler.readUserGSON();
        Data.gsonHandler.readCardGSON();

        GraphicData.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterMenuView.class.getResource("register-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GraphicData.V, GraphicData.H);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
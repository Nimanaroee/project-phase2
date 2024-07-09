package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.io.IOException;

public class RegisterMenuView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /// loading json
        Data.gsonHandler = new GsonHandler();
        Data.gsonHandler.readUserGSON();
        Data.gsonHandler.readCardGSON();

        /// loading music
        GraphicData.backgroundSound = new MediaPlayer(new Media(new File("src/main/resources/music/1.mp3").toURI().toString()));
        GraphicData.backgroundSound.play();

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

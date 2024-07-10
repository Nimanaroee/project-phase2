package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.*;
import javafx.application.Application;
import javafx.stage.Stage;
import game.Game;
import javafx.stage.Stage;
import controller.GameMenuController;

import java.io.IOException;

import static model.GraphicData.*;

public class GameMenuView extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize GraphicData.stage with primaryStage
        GraphicData.stage = primaryStage;

        // Temporary initialization
        GsonHandler gsonHandler = new GsonHandler();
        gsonHandler.readUserGSON();
        gsonHandler.readCardGSON();
        Data.setLoggedInUser1(Data.getAllUsers().get(0));
        Data.setLoggedInUser2(Data.getAllUsers().get(1));
        User user1 = Data.getLoggedInUser1();
        User user2 = Data.getLoggedInUser2();
        Game game = new Game(user1, user2);
        GameMenuController.game = game;

        FXMLLoader fxmlLoader = new FXMLLoader(RegisterMenuView.class.getResource("game-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1250, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

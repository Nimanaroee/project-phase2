package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.*;
import javafx.application.Application;
import javafx.stage.Stage;
import game.Game;
import javafx.stage.Stage;
import controller.GameMenuController;

import java.io.IOException;

import static model.GraphicData.*;

public class GameMenuView extends Application {
    private Pane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        User user1 = Data.getLoggedInUser1();
        User user2 = Data.getLoggedInUser2();
        Game game = new Game(user1, user2);
        GameMenuController.game = game;


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-menu.fxml"));
        root = fxmlLoader.load();

        // Apply the saved background CSS class
        root.getStyleClass().add(GraphicData.backgroundTheme);

        Scene scene = new Scene(root, 1250, 650);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/css/gameMenu.css")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updateBackground(String theme) {
        root.getStyleClass().clear();
        root.getStyleClass().add(theme);
    }

}

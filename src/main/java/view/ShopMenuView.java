package view;

import controller.ShopMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GraphicData;

import java.io.IOException;

public class ShopMenuView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(RegisterMenuView.class.getResource("shop-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GraphicData.V, GraphicData.H);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Shop Menu");
        primaryStage.show();
    }
}

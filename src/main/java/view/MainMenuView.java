package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GraphicData;

public class MainMenuView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GraphicData.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(RegisterMenuView.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GraphicData.V, GraphicData.H);
        stage.setScene(scene);
        stage.show();
    }
}

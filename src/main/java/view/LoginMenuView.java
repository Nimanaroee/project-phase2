package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GraphicData;

public class LoginMenuView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterMenuView.class.getResource("login-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GraphicData.V, GraphicData.H);
        stage.setScene(scene);
        stage.show();
    }
}
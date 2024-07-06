package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import model.GraphicData;
import view.MainMenuView;

public class SettingMenuController {
    public Slider volumeBar;
    public Button saveButton;
    public ChoiceBox<String> soundChoice;
    public ChoiceBox<String> backgroundChoice;

    public void initialize() {
        volumeBar = new Slider(0, 1, 0.5);

        ////// add background and sound and other stuff of box

    }
    public void onClickSaveButton(ActionEvent actionEvent) {
        //// search volume bar
        GraphicData.backgroundSound.volumeProperty().bind(volumeBar.valueProperty());
        GraphicData.soundTheme = soundChoice.getValue();
        GraphicData.backgroundTheme = backgroundChoice.getValue();
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setContentText("save shod!");
        errorAlert.showAndWait();
    }
    public void onClickBackButton() throws Exception {
        new MainMenuView().start(GraphicData.stage);
    }
}

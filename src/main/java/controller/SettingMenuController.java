package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.GraphicData;
import view.MainMenuView;

import java.io.File;

public class SettingMenuController {
    public Slider volumeBar;
    public Button saveButton;
    public ChoiceBox<String> soundChoice;
    public ChoiceBox<String> backgroundChoice;

    public void initialize() {
        volumeBar.setMin(0);
        volumeBar.setMax(1);
        volumeBar.setValue(GraphicData.backgroundSound.getVolume());

        // Add background and sound options
        backgroundChoice.getItems().addAll("background1", "background2", "background3", "background4", "background5");
        backgroundChoice.setValue("background1");


        soundChoice.getItems().addAll("sound track 1", "sound track 2");
        soundChoice.setValue("sound track 1");

        // Add listener to soundChoice
        soundChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (GraphicData.backgroundSound != null) {
                GraphicData.backgroundSound.stop();
            }
            if ("sound track 1".equals(newValue)) {
                GraphicData.backgroundSound = new MediaPlayer(new Media(new File("src/main/resources/music/1.mp3").toURI().toString()));
            } else if ("sound track 2".equals(newValue)) {
                GraphicData.backgroundSound = new MediaPlayer(new Media(new File("src/main/resources/music/2.mp3").toURI().toString()));
            }
            GraphicData.backgroundSound.volumeProperty().bind(volumeBar.valueProperty());
            GraphicData.backgroundSound.play();
        });

        // Initialize volume slider to match the current volume of the media player
        if (GraphicData.backgroundSound != null) {
            volumeBar.setValue(GraphicData.backgroundSound.getVolume());
        }

        // Add listener to volumeBar
        volumeBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (GraphicData.backgroundSound != null) {
                GraphicData.backgroundSound.setVolume(newValue.doubleValue());
            }
        });

        // Add listener to backgroundChoice
        backgroundChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateBackgroundImage(newValue);
        });

        // Set initial background image
        updateBackgroundImage(backgroundChoice.getValue());
    }

    private void updateBackgroundImage(String choice) {
        GraphicData.backgroundTheme = choice;
    }

    public void onClickSaveButton(ActionEvent actionEvent) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setContentText("Saved changes!");
        errorAlert.showAndWait();
    }

    public void onClickBackButton() throws Exception {
        new MainMenuView().start(GraphicData.stage);
    }
}

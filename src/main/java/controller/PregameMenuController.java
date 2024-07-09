package controller;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Data;
import model.GraphicData;
import model.User;
import view.MainMenuView;
import view.RegisterMenuView;

public class PregameMenuController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button validityButton;
    public ChoiceBox<String> choiceBoxFirst;
    public TextField gambleFirst;
    public ChoiceBox<String> choiceBoxSecond;
    public Button startButton;
    public TextField gambleSecond;
    public ImageView imageFirst;
    public ImageView imageSecond;

    private Image manImage;
    private Image womanImage;

    public void initialize() {
        // Load images
        try {
            manImage = new Image(String.valueOf(PregameMenuController.class.getResource("/images/Man.png")));
            womanImage = new Image(String.valueOf(PregameMenuController.class.getResource("/images/Woman.png")));
        } catch (Exception e) { e.printStackTrace(); }

        // Define initial values
        choiceBoxFirst.getItems().add("Man");
        choiceBoxFirst.getItems().add("Woman");

        choiceBoxFirst.setValue("Man");

        choiceBoxSecond.getItems().add("Man");
        choiceBoxSecond.getItems().add("Woman");

        choiceBoxSecond.setValue("Woman");

        // Set initial image
        imageFirst.setImage(manImage);
        imageSecond.setImage(womanImage);

        // Add listener to choiceBoxFirst
        choiceBoxFirst.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Man".equals(newValue)) {
                imageFirst.setImage(manImage);
            } else if ("Woman".equals(newValue)) {
                imageFirst.setImage(womanImage);
            }
        });

        // Add listener to choiceBoxSecond
        choiceBoxSecond.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Man".equals(newValue)) {
                imageSecond.setImage(manImage);
            } else if ("Woman".equals(newValue)) {
                imageSecond.setImage(womanImage);
            }
        });
    }

    public void onClickValidityCheckButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = Data.getUserByUsername(username);
        if (user == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("invalid username!");
            errorAlert.showAndWait();
            return;
        }
        if (!user.getPassword().equals(password)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong password!");
            errorAlert.showAndWait();
            return;
        }
        if (user.getUsername().equals(Data.getLoggedInUser1().getUsername())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("this user has been logged in!");
            errorAlert.showAndWait();
            return;
        }
        Data.setLoggedInUser2(user);
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setContentText("second user logged in successfully!");
        errorAlert.showAndWait();
    }

    public void onClickStartGameButton() {
        onClickValidityCheckButton();

        String firstCharacter = choiceBoxFirst.getValue();
        String secondCharacter = choiceBoxSecond.getValue();

        int firstGold = Integer.parseInt(gambleFirst.getText());
        int secondGold = Integer.parseInt(gambleSecond.getText());
        int minimum = Math.min(firstGold, secondGold);
        if (minimum > Data.getLoggedInUser1().getGold() || minimum > Data.getLoggedInUser2().getGold()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("not enough gold to gamble! \n first user max gold : " + Data.getLoggedInUser1().getGold() + "\n " + "second user max gold : " + Data.getLoggedInUser2().getGold());
            errorAlert.showAndWait();
            return;
        }

        setCharacter(firstCharacter, 1);
        setCharacter(secondCharacter, 2);
    }

    public void onClickBackButton() throws Exception {
        new MainMenuView().start(GraphicData.stage);
    }

    private void setCharacter(String characterName, int turn) {
        User user;
        if (turn == 1) {
            user = Data.getLoggedInUser1();
        } else {
            user = Data.getLoggedInUser2();
        }
    }
}

package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Data;
import model.User;

public class PregameMenuController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Button validityButton;
    public ChoiceBox<String> choiceBoxFirst;
    public TextField gambleFirst;
    public ChoiceBox<String> choiceBoxSecond;
    public Button startButton;
    public TextField gambleSecond;

    public void initialize() {
        /// تعریف متغیر های اولیه
        choiceBoxFirst.getItems().add("");
        choiceBoxFirst.getItems().add("");
        choiceBoxFirst.getItems().add("");
        choiceBoxFirst.getItems().add("");

        choiceBoxFirst.setValue("");


        choiceBoxSecond.getItems().add("");
        choiceBoxSecond.getItems().add("");
        choiceBoxSecond.getItems().add("");
        choiceBoxSecond.getItems().add("");

        choiceBoxSecond.getItems().add("");





    }
    public void onClickValidityCheckButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = Data.getUserByUsername(username);
        if(user == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("invalid username!");
            errorAlert.showAndWait();
            return;
        }
        if(!user.getPassword().equals(password)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong password!");
            errorAlert.showAndWait();
            return;
        }
        if(user.getUsername().equals(Data.getLoggedInUser1().getUsername())) {
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
        if(minimum > Data.getLoggedInUser1().getGold() || minimum > Data.getLoggedInUser2().getGold()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("not enouph gold to gambling! \n first user max gold : "+Data.getLoggedInUser1().getGold()+"\n "+"second user max gold : "+Data.getLoggedInUser2().getGold());
            errorAlert.showAndWait();
            return;
        }

        setCharacter(firstCharacter, 1);
        setCharacter(secondCharacter, 2);


    }
    private void setCharacter(String characterName, int turn) {
        User user;
        if(turn == 1) {
            user = Data.getLoggedInUser1();
        } else {
            user = Data.getLoggedInUser2();
        }
        ////// اضافه کردن کارت های مربوط به ان کاراکتر
    }
}

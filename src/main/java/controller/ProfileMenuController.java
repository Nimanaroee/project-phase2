package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Data;
import model.GraphicData;
import model.User;
import view.MainMenuView;

/*
        update :
                change user and save json
                update all users

 */
public class ProfileMenuController {

    public Button usernameButton;
    public TextField usernameField;
    public Button nicknameButton;
    public TextField nicknameField;
    public Button passwordButton;
    public TextField passwordField;
    public Button emailButton;
    public TextField emailField;
    public Button questionButton;
    public ChoiceBox<String> questionBox;
    public Button answerButton;
    public TextField answerField;

    public void onClickUsernameButton(ActionEvent actionEvent) {
        String username = usernameField.getText();
        User user = Data.getUserByUsername(Data.getLoggedInUser1().getUsername());
        user.setUsername(username);
    }

    public void onClickNicknameButton(ActionEvent actionEvent) {
        String nickName = nicknameField.getText();
        User user = Data.getUserByUsername(Data.getLoggedInUser1().getUsername());
        user.setNickname(nickName);
    }

    public void onClickPasswordButton(ActionEvent actionEvent) {
        String password = passwordField.getText();
        User user = Data.getUserByUsername(Data.getLoggedInUser1().getUsername());
        user.setPassword(password);
    }

    public void onClickEmailButton(ActionEvent actionEvent) {
        String email = emailField.getText();
        User user = Data.getUserByUsername(Data.getLoggedInUser1().getUsername());
        user.setEmail(email);
    }

    public void onClickQuestionButton(ActionEvent actionEvent) {
        String question = questionBox.getValue();
        User user = Data.getUserByUsername(Data.getLoggedInUser1().getUsername());
        user.setQuestion(question);
    }

    public void onClickAnswerButton(ActionEvent actionEvent) {
        String answer = answerField.getText();
        User user = Data.getUserByUsername(Data.getLoggedInUser1().getUsername());
        user.setAnswer(answer);
    }

    public void onClickBackButton(ActionEvent actionEvent) throws Exception {
        new MainMenuView().start(GraphicData.stage);
    }
}

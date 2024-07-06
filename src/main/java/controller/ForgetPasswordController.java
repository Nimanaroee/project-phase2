package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Data;
import model.GraphicData;
import model.User;
import view.LoginMenuView;

public class ForgetPasswordController {

    @FXML
    protected Button questionButton;
    @FXML
    protected Button submitAnswerButton;
    @FXML
    protected Button backButton;
    @FXML
    protected TextField usernameField;
    @FXML
    protected TextField answerField;
    @FXML
    protected void onClickQuestionButton() {
        String username = usernameField.getText();
        User user = Data.getUserByUsername(username);
        if(user == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong username!");
            errorAlert.showAndWait();
            return;
        }

        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setContentText(user.getQuestion());
        errorAlert.showAndWait();

    }
    @FXML
    protected void onClickSubmitAnswer() {
        String username = usernameField.getText();
        String answer = answerField.getText();

        User user = Data.getUserByUsername(username);
        if(user == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong username!");
            errorAlert.showAndWait();
            return;
        }
        if(!answer.equals(user.getAnswer())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong answer!");
            errorAlert.showAndWait();
            return;
        }
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setContentText("your password : " + user.getPassword());
        errorAlert.showAndWait();
    }
    @FXML
    protected void onClickBackButton() throws Exception {
        new LoginMenuView().start(GraphicData.stage);
    }
}

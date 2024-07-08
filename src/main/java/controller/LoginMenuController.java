package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Data;
import model.GraphicData;
import model.User;
import view.ForgetPasswordView;
import view.MainMenuView;

public class LoginMenuController {

    @FXML
    protected Button loginButton;
    @FXML
    protected Button forgetPassword;
    @FXML
    protected TextField usernameField;
    @FXML
    protected PasswordField passwordField;
    @FXML
    protected void onClickLoginButton() throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = Data.getUserByUsername(username);
        if(user == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong username!");
            errorAlert.showAndWait();
            return;
        }
        if(!user.getPassword().equals(password)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong password!");
            errorAlert.showAndWait();
            return;
        }
        System.out.println(user.getUsername());
        Data.setLoggedInUser1(user);

        new MainMenuView().start(GraphicData.stage);
    }
    @FXML
    protected void onClickForgetPasswordButton() throws Exception {
        new ForgetPasswordView().start(GraphicData.stage);
    }
}

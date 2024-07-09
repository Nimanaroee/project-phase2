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

    private long lastTime = -1;
    private int attempt = 0;

    @FXML
    protected void onClickLoginButton() throws Exception {
        long now = System.currentTimeMillis();
        if(now < lastTime+ attempt* 5000L) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("try "+ (int)(lastTime+ attempt* 5000L - now)/1000 +" seconds later !");
            errorAlert.showAndWait();
            return;
        }
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

            lastTime = System.currentTimeMillis();
            attempt++;
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

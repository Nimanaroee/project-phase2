package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import view.LoginMenuView;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class RegisterMenuController {
    @FXML
    protected TextField usernameField;
    @FXML
    protected TextField nicknameField;
    @FXML
    protected PasswordField passwordField;
    @FXML
    protected PasswordField passwordConfirmField;
    @FXML
    protected TextField emailField;
    @FXML
    protected TextField answerQuestionField;
    @FXML
    protected ChoiceBox<String> questionBoxField;
    @FXML
    protected TextField answerCaptchaField;
    @FXML
    protected Button loginButton;
    @FXML
    protected Button registerButton;
    @FXML
    protected Button randomPasswordButton;
    @FXML
    protected Label randomPasswordLabel;
    @FXML
    protected Label captcha;

    private String captchaString;

    @FXML
    public void initialize() throws IOException {
        //// initial captcha and other stuff
        /// address :
        /// src/main/resources/images/captcha.png
        questionBoxField.getItems().add("• 1-What is your father’s name ?");
        questionBoxField.getItems().add("• 2-What is your favourite color ?");
        questionBoxField.getItems().add("• 3-What was the name of your first pet?");
        questionBoxField.setValue("• 1-What is your father’s name ?");

        buildCaptcha();

    }

    @FXML
    protected void onClickRegisterButton() throws Exception {
        String username = usernameField.getText();
        String nickname = nicknameField.getText();
        String password = passwordField.getText();
        String passwordConfirm = passwordConfirmField.getText();
        String email = emailField.getText();
        String answerQuestion = answerQuestionField.getText();
        String question = questionBoxField.getValue();
        String answerCaptcha = answerCaptchaField.getText();

        System.out.println(answerCaptcha + " " + captchaString);

        if (username == null || nickname == null || password == null || passwordConfirm == null || email == null || answerQuestion == null || question == null || answerCaptcha == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("please fill boxes completely!");
            errorAlert.showAndWait();
            return;
        }
        if (!Regex.VALID_USERNAME.matches(username)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("username can be only letter and number");
            errorAlert.showAndWait();
            return;
        }
        if (!Regex.VALID_NICKNAME.matches(nickname)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("nick can be only letter");
            errorAlert.showAndWait();
            return;
        }
        if (!Regex.STRONG_PASSWORD.matches(password)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            ///// complete conditionssssssssss
            errorAlert.setContentText("password condition : 1-at least 6 character, 2-at least one small letter, 3- at least one special character");
            errorAlert.showAndWait();
            return;
        }
        if (!password.equals(passwordConfirm)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("check your password again!");
            errorAlert.showAndWait();
            return;
        }
        if (!Regex.VALID_EMAIL.matches(email)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong email address!");
            errorAlert.showAndWait();
            return;
        }
        if (!captchaString.equals(answerCaptcha)) {
            buildCaptcha();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("wrong captcha!");
            errorAlert.showAndWait();
            return;
        }

        User user = new User(username, password, email, nickname, question, answerQuestion);
        user.addCard(CardModel.getStarterPack());
        Data.addUser(user);

        new LoginMenuView().start(GraphicData.stage);
    }

    @FXML
    protected void onClickLoginButton() throws Exception {
        new LoginMenuView().start(GraphicData.stage);
    }

    @FXML
    protected void onClickRandomPasswordButton() {
        randomPasswordLabel.setText(passwordBuilder());
    }

    private String passwordBuilder() {
        char[] SYMBOLS = "!@#$%^&*".toCharArray();
        char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] NUMBERS = "0123456789".toCharArray();
        char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*".toCharArray();
        int length = (int) (Math.random() * 10 + 4);
        Random rand = new SecureRandom();
        char[] password = new char[length];
        //get the requirements out of the way
        password[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[3] = SYMBOLS[rand.nextInt(SYMBOLS.length)];
        //populate rest of the password with random chars
        for (int i = 4; i < length; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }
        //shuffle it up
        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }
        StringBuilder ret = new StringBuilder();
        for (char c : password)
            ret.append(c);
        return ret.toString();
    }

    private void buildCaptcha() throws IOException {
        captchaString = String.valueOf(Ascii.asciiArt());
        captcha.setText(captchaString);
        System.out.println(captchaString + " --------");
    }
}

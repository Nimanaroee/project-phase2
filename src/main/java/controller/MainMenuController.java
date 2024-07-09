package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import model.Data;
import model.GraphicData;
import view.*;
import java.util.Random;
import javafx.scene.image.ImageView;


public class MainMenuController {
    @FXML
    protected Button gameButton;
    @FXML
    protected Button historyButton;
    @FXML
    protected Button shopButton;
    @FXML
    protected Button profileButton;
    @FXML
    protected Button logoutButton;
    @FXML
    protected Button settingButton;
    @FXML
    protected Label heartLabel;
    @FXML
    protected Label scoreLabel;
    @FXML
    public ImageView profileAvatar;
    private Image avatarImage;



    @FXML
    protected void initialize() {
        try {
            String[] strings = {"1", "2", "5", "3", "4"};
            avatarImage = new Image(String.valueOf(PregameMenuController.class.getResource("/images/avatar/"+strings[new Random().nextInt(strings.length)]+".jpg")));

        } catch (Exception e) { e.printStackTrace();}

        profileAvatar.setImage(avatarImage);

        scoreLabel.setText("Level : " + Data.getLoggedInUser1().getLevel());
        heartLabel.setText("HP : " + Data.getLoggedInUser1().getHp() + "| XP : " + Data.getLoggedInUser1().getXp());
    }
    @FXML
    protected void onClickGameButton() throws Exception {
        new PregameMenuView().start(GraphicData.stage);
    }
    @FXML
    protected void onClickHistoryButton() throws Exception {
        new HistoryMenuView().start(GraphicData.stage);
    }
    @FXML
    protected void onClickShopButton() throws Exception {
        new ShopMenuView().start(GraphicData.stage);
    }
    @FXML
    protected void onClickProfileButton() throws Exception {
        new ProfileMenuView().start(GraphicData.stage);
    }
    @FXML
    protected void onClickSettingButton() throws Exception {
        new SettingMenuView().start(GraphicData.stage);
    }
    @FXML
    protected void onClickLogoutButton() throws Exception {
        new LoginMenuView().start(GraphicData.stage);
    }
    @FXML
    protected void onAvatarClick() throws Exception {
        new ProfileMenuView().start(GraphicData.stage);
    }
}

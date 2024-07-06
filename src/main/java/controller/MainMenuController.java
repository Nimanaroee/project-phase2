package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Data;
import model.GraphicData;
import view.*;

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
}

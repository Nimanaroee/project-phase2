package Controller;

import Model.*;
import Veiw.Out;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu extends Menu {
    public MainMenu(Scanner scanner) {
        super(scanner, "Main Menu", "logout");
        addCommand(Regex.MAIN_START_GAME, this::startGame);
        addCommand(Regex.MAIN_SHOW_CARDS, this::showCards);
        addCommand(Regex.MAIN_SHOW_HISTORY, this::showHistory);
        addCommand(Regex.MAIN_SHOW_USER_INFO, this::showUserInfo);
        addCommand(Regex.MAIN_ENTER_SHOPMENU, this::enterShopMenu);
        addCommand(Regex.MAIN_ENTER_PROFILEMENU, this::enterProfileMenu);
        /// add commands
    }
    private void startGame(Matcher matcher) {
        new PreGameMenu(scan, "Pregame Menu").run();
    }
    private void enterShopMenu(Matcher matcher) {
        new ShopMenu(scan, "Shop Menu").run();
    }
    private void enterProfileMenu(Matcher matcher) {
        new ProfileMenu(scan, "Profile Menu").run();
    }
    private void showHistory(Matcher matcher) {
        new HistoryMenu(scan, "History Menu").run();
    }
    private void showUserInfo(Matcher matcher) {
        Out.showInfoOfUser(Data.getLoggedInUser1());
    }

    private void showCards(Matcher matcher) {
        ArrayList <Card> cards= Data.getLoggedInUser1().getCards();
        for(Card card : cards) {
            Out.showInfoOfCard(card);
        }
    }


}

package Controller;

import Model.*;
import Veiw.Out;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu extends Menu {
    public ShopMenu(Scanner scanner, String menuName) {
        super(scanner, menuName, "back");
        addCommand(Regex.SHOP_SHOW_ALL_CARDS, this::showAllCard);
        addCommand(Regex.SHOP_BUY_CARD, this::buyCard);
        addCommand(Regex.SHOP_UPGRADE_CARD, this::upgradeCard);
    }
    private void showAllCard(Matcher matcher) {
        ArrayList<Card> cards = Data.getAllCards();
        for(Card card : cards) {
            Out.showInfoOfCard(card);
        }
    }
    private void buyCard(Matcher matcher) {
        Card card = Data.getCardByCardName(matcher.group("name"));
        if(card == null ) {
            Out.print("wrong card name !");
            return;
        }
        if(Data.getLoggedInUser1().getGold() < card.getPrice()) {
            Out.print("not enouph money!");
            return;
        }
        User user = Data.getLoggedInUser1();
        user.addCard(card);
        Out.print("card "+card.getName()+" bought successfully!");
    }
    private void upgradeCard(Matcher matcher) {
        Card card = Data.getLoggedInUser1().getCardByName(matcher.group("name"));
        if(card == null) {
            Out.print("wrong card name !");
            return;
        }
        if(Data.getLoggedInUser1().getGold() < card.getUpgradeCoast()) {
            Out.print("not enouph money!");
            return;
        }
        /////// upgrade card (((:
        /////// idea for upgrading cards //////////
    }
}
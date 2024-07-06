package Controller;

import Model.*;
import Veiw.Out;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Admin extends Menu {
    public Admin(Scanner scanner) {
        super(scanner, "Admin Menu", "logout");
        /// add commands
        addCommand(Regex.ADMIN_ADD_CARD, this::addCard);
        addCommand(Regex.ADMIN_UPDATE_CARD, this::updateCard);
        addCommand(Regex.ADMIN_DELETE_CARD, this::deleteCard);
        addCommand(Regex.ADMIN_SHOW_PLAYERS, this::showPlayers);
    }
    private void addCard(Matcher matcher) {
        String name = matcher.group("name");
        int attack = Integer.parseInt(matcher.group("attack")),
                duration = Integer.parseInt(matcher.group("duration")),
                damage = Integer.parseInt(matcher.group("damage")),
                upgradeLevel = Integer.parseInt(matcher.group("upgradeLevel")),
                upgradeCost = Integer.parseInt(matcher.group("upgradeCost"));
        if(!Card.validCard(attack, duration, damage)) {
            Out.print("invalid card!");
            return;
        }
        Card card = new Card(name, attack, duration, damage, upgradeLevel, upgradeCost);
        Data.addCard(card);
        ////// tamam ?!
    }
    private void updateCard(Matcher matcher) {
        String name = matcher.group("name");
        Card card = Data.getCardByCardName(name);
        if(card == null) {
            Out.print("invalid name!");
            return;
        }
        int attack = Integer.parseInt(matcher.group("attack")),
                duration = Integer.parseInt(matcher.group("duration")),
                damage = Integer.parseInt(matcher.group("damage")),
                upgradeLevel = Integer.parseInt(matcher.group("upgradeLevel")),
                upgradeCost = Integer.parseInt(matcher.group("upgradeCost"));

        card = new Card(name, attack, duration, damage, upgradeLevel, upgradeCost);
        Data.updateCard(card);
        ////// tamam ?!
    }
    private void deleteCard(Matcher matcher) {
        String name = matcher.group("name");
        Card card = Data.getCardByCardName(name);
        if(card == null) {
            Out.print("invalid card name!");
            return;
        }
        Data.removeCardByName(name);
        ////// tamam ?!
    }
    private void showPlayers(Matcher matcher) {
        ArrayList<User> users = Data.getAllUser();
        for(User user : users) {
            Out.showInfoOfUser(user);
        }
    }
}

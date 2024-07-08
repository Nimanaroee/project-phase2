package model;

import javafx.stage.Stage;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class Data {
    ////// logic
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();
    private static final ArrayList<DataHistory> histories = new ArrayList<>();
    private static String loggedInUser1 = null;
    private static String loggedInUser2 = null;
    public static GsonHandler gsonHandler = new GsonHandler();


    //// users
    public static User getUserByUsername(String username) {
        for(User user : Data.users) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
    public static void removeUserByUsername(String username) {
        Data.users.remove(Data.getUserByUsername(username));
        gsonHandler.saveUserGson();
    }
    public static void addUser(User user) {
        Data.users.add(user);
        gsonHandler.saveUserGson();
    }
    public static void updateUser(User user) { Data.removeUserByUsername(user.getUsername()); Data.addUser(user); }
    public static ArrayList<User> getAllUser() {return Data.users;}
    public static void setAllUsers(ArrayList<User> users) {
        Data.users = users;
    }

    //// logged in usernames
    public static User getLoggedInUser1() { return Data.getUserByUsername(loggedInUser1);}
    public static User getLoggedInUser2() { return Data.getUserByUsername(loggedInUser2); }
    public static void setLoggedInUser1(User user) { loggedInUser1 = user.getUsername(); }
    public static void setLoggedInUser2(User user) { loggedInUser2 = user.getUsername(); }

    //// card
    public static Card getCardByCardName(String name) {
        for(Card card : Data.cards) {
            if(card.getName().equals(name))
                return card;
        }
        return null;
    }
    public static void removeCardByName(String name) {
        Data.cards.remove(Data.getCardByCardName(name));
        gsonHandler.saveCardGson();
    }
    public static void addCard(Card card) {
        Data.cards.add(card);
        gsonHandler.saveCardGson();
    }
    public static void updateCard(Card card) { Data.removeCardByName(card.name); Data.addCard(card); }
    public static ArrayList<Card> getAllCards() { return Data.cards; }

    //// data history
    public static ArrayList<DataHistory> getHistories() {
        return Data.histories;
    }
    public static void setAllCards(ArrayList<Card> cards) {
        Data.cards = cards;
    }
}

package model;

import java.util.ArrayList;

public class Data {
    private static final ArrayList<DataHistory> histories = new ArrayList<>();
    public static GsonHandler gsonHandler = new GsonHandler();
    ////// logic
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<CardModel> cardModels = new ArrayList<>();
    private static String loggedInUser1 = null;
    private static String loggedInUser2 = null;

    //// users
    public static User getUserByUsername(String username) {
        for (User user : Data.users) {
            if (user.getUsername().equals(username))
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

    public static void updateUser(User user) {
        Data.removeUserByUsername(user.getUsername());
        Data.addUser(user);
    }

    public static ArrayList<User> getAllUser() {
        return Data.users;
    }

    public static void setAllUsers(ArrayList<User> users) {
        Data.users = users;
    }

    //// logged in usernames
    public static User getLoggedInUser1() {
        return Data.getUserByUsername(loggedInUser1);
    }

    public static void setLoggedInUser1(User user) {
        loggedInUser1 = user.getUsername();
    }

    public static User getLoggedInUser2() {
        return Data.getUserByUsername(loggedInUser2);
    }

    public static void setLoggedInUser2(User user) {
        loggedInUser2 = user.getUsername();
    }

    //// card
    public static CardModel getCardByCardName(String name) {
        for (CardModel cardModel : Data.cardModels) {
            if (cardModel.getName().equals(name))
                return cardModel;
        }
        return null;
    }

    public static void removeCardByName(String name) {
        Data.cardModels.remove(Data.getCardByCardName(name));
        gsonHandler.saveCardGson();
    }

    public static void addCard(CardModel cardModel) {
        Data.cardModels.add(cardModel);
        gsonHandler.saveCardGson();
    }

    public static void updateCard(CardModel cardModel) {
        Data.removeCardByName(cardModel.name);
        Data.addCard(cardModel);
    }

    public static ArrayList<CardModel> getAllCards() {
        return Data.cardModels;
    }

    public static void setAllCards(ArrayList<CardModel> cardModels) {
        Data.cardModels = cardModels;
    }

    //// data history
    public static ArrayList<DataHistory> getHistories() {
        return Data.histories;
    }
}

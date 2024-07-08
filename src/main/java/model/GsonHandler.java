package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;

public class GsonHandler {
    private Gson gsonUser;
    private Gson gsonCard;

    public void readUserGSON() {
        GsonBuilder builder = new GsonBuilder();
        gsonUser = builder.create();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/Gson/users.json"));
            ArrayList<User> users = new ArrayList<>();
            String line = null;
            while (true) {
                line = bufferedReader.readLine();
                if(line == null)
                    break;
                users.add(gsonUser.fromJson(line, User.class));
            }
            Data.setAllUsers(users);
        } catch (IOException e) { e.printStackTrace(); }
    }
    public void saveUserGson() {
        GsonBuilder builder = new GsonBuilder();
        gsonUser = builder.create();
        try {
            FileWriter writer = new FileWriter("src/main/resources/Gson/users.json");
            BufferedWriter out = new BufferedWriter(writer);
            writer.flush();
            ArrayList<User> users = Data.getAllUser();
            for(User user : users) {
                out.write(gsonUser.toJson(user));
                out.newLine();
            }
            out.close();
            writer.close();
        } catch (IOException e) {e.printStackTrace();}

    }
    public void readCardGSON() {
        GsonBuilder builder = new GsonBuilder();
        gsonCard = builder.create();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/Gson/cards.json"));
            ArrayList<Card> cards = new ArrayList<>();
            String line = null;
            while (true) {
                line = bufferedReader.readLine();
                if(line == null)
                    break;
                cards.add(gsonCard.fromJson(line, Card.class));
            }
            Data.setAllCards(cards);
        } catch (IOException e ) { e.printStackTrace();}
    }
    public void saveCardGson() {
        GsonBuilder builder = new GsonBuilder();
        gsonCard = builder.create();
        try {
            FileWriter writer = new FileWriter("src/main/resources/Gson/cards.json");
            BufferedWriter out = new BufferedWriter(writer);
            ArrayList<Card> cards = Data.getAllCards();
            for(Card card : cards) {
                out.write(gsonCard.toJson(card));
                out.newLine();
            }
            out.close();
            writer.close();
        } catch (IOException e) { e.printStackTrace(); };
    }
}

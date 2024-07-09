package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class GsonHandler {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void readUserGSON() {
        try {
            String content = Files.readString(Paths.get("src/main/resources/Gson/users.json"));
            Type userListType = new TypeToken<ArrayList<User>>() {
            }.getType();
            List<User> users = gson.fromJson(content, userListType);
            Data.setAllUsers((ArrayList<User>) users);
            Data.getAllUser().forEach(user -> {
                System.out.println(user.getNickname());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUserGson() {
        try {
            List<User> users = Data.getAllUser();
            String json = gson.toJson(users);
            Files.writeString(Paths.get("src/main/resources/Gson/users.json"), json, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCardGSON() {
        try {
            String content = Files.readString(Paths.get("src/main/resources/Gson/cards.json"));
            Type cardListType = new TypeToken<ArrayList<CardModel>>() {
            }.getType();
            List<CardModel> cardModels = gson.fromJson(content, cardListType);
            Data.setAllCards((ArrayList<CardModel>) cardModels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCardGson() {
        try {
            List<CardModel> cardModels = Data.getAllCards();
            String json = gson.toJson(cardModels);
            Files.writeString(Paths.get("src/main/resources/Gson/cards.json"), json, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
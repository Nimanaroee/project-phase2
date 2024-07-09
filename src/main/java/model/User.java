package model;

import java.util.ArrayList;

public class User {
    private final int level = 1;
    private final int hp = 100;
    private final int xp = 0;
    private final int gold = 0;
    private final ArrayList<CardModel> cardModels;
    private final ArrayList<DataHistory> histories;
    private String username, nickname, password, email, question, answer;
    private int character = 1;

    public User(String username, String password, String email, String nickname, String question, String answer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.question = question;
        this.answer = answer;
        this.cardModels = new ArrayList<>();
        this.histories = new ArrayList<>();
    }

    public void addCard(CardModel cardModel) {
        this.cardModels.add(cardModel);
        Data.updateUser(this);
    }

    public void addCard(ArrayList<CardModel> cardModels) {
        this.cardModels.addAll(cardModels);
        Data.updateUser(this);
    }

    public void updateCard(CardModel cardModel) {
        this.cardModels.remove(cardModel);
        this.addCard(cardModel.upgrade());
        Data.updateUser(this);
    }

    public void addHistory(DataHistory dataHistory) {
        this.histories.add(dataHistory);
        Data.updateUser(this);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        Data.updateUser(this);
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        Data.updateUser(this);
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        Data.updateUser(this);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
        Data.updateUser(this);
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
        Data.updateUser(this);
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        Data.updateUser(this);
    }

    public int getLevel() {
        return this.level;
    }

    public int getGold() {
        return this.gold;
    }

    public int getHp() {
        return this.hp;
    }

    public int getXp() {
        return this.xp;
    }


    public ArrayList<DataHistory> getHistories() {
        return this.histories;
    }

    public CardModel getCardByName(String name) {
        for (CardModel cardModel : this.cardModels) {
            if (cardModel.getName().equals(name)) return cardModel;
        }
        return null;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public ArrayList<CardModel> getCards() {
        return cardModels;
    }
}

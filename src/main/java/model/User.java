package model;

import java.util.ArrayList;

public class User {
    private String username, nickname, password, email, question, answer;
    private final int level = 1;
    private final int hp = 100;
    private final int xp = 0;
    private final int gold = 0;
    private final ArrayList<Card> cards;
    private final ArrayList<DataHistory> histories;

    public User(String username, String password, String email, String nickname, String question, String answer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.question = question;
        this.answer = answer;
        this.cards = new ArrayList<>();
        this.histories = new ArrayList<>();
    }

    public void setUsername(String username) { this.username = username; Data.updateUser(this); }
    public void setNickname(String nickname) { this.nickname = nickname; Data.updateUser(this); }
    public void setPassword(String password) { this.password = password; Data.updateUser(this); }
    public void setEmail(String email) { this.email = email; Data.updateUser(this); }
    public void setQuestion(String question) { this.question = question; Data.updateUser(this); }
    public void setAnswer(String answer) { this.answer = answer; Data.updateUser(this); }
    public void addCard(Card card) { this.cards.add(card); Data.updateUser(this); }
    public void updateCard(Card card) {
        this.cards.remove(card);
        this.addCard(card.upgrade());
        Data.updateUser(this);
    }
    public void addHistory(DataHistory dataHistory) { this.histories.add(dataHistory); Data.updateUser(this); }
    public String getUsername() { return this.username; }
    public String getNickname() { return this.nickname; }
    public String getPassword() { return this.password; }
    public String getEmail() { return this.email; }
    public String getQuestion() { return this.question; }
    public String getAnswer() { return this.answer; }
    public int getLevel() { return this.level; }
    public int getGold() { return this.gold; }
    public int getHp() { return this.hp; }
    public int getXp() { return this.xp; }
    public ArrayList<Card> getCards() { return this.cards; }
    public ArrayList<DataHistory> getHistories() { return this.histories; }
    public Card getCardByName(String name) {
        for(Card card : this.cards) {
            if(card.getName().equals(name)) return card;
        }
        return null;
    }
}

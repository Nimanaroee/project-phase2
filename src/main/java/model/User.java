package model;

import java.util.ArrayList;
import java.util.Spliterator;

public class User {
    private String username, nickname, password, email, question, answer;
    private final int Level=1;
    private final int hp = 100;
    private final int xp = 0;
    private final int gold = 0; ///// set value of gold
    private final ArrayList<Card> cards;

    public User(String username, String password, String email, String nickname, String question, String answer) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setNickname(nickname);
        setQuestion(question);
        setAnswer(answer);
        cards = new ArrayList<Card>();
    }

    public void setUsername(String username) { this.username = username; Data.updateUser(this); }
    public void setNickname(String nickname) { this.nickname = nickname; Data.updateUser(this); }
    public void setPassword(String password) { this.password = password; Data.updateUser(this); }
    public void setEmail(String email) { this.email = email; Data.updateUser(this); }
    public void setQuestion(String question) { this.question = question; Data.updateUser(this); }
    public void setAnswer(String answer) { this.answer = answer; Data.updateUser(this); }
    public void addCard(Card card) { this.cards.add(card); Data.updateUser(this);}

    ///////// after game update //////////
    public void updateLevel() {}
    public void updateXp() {}
    public void updateHp() {}
    public void updateGold() {}
    public String getUsername() {
        return this.username;
    }
    public String getNickname() {
        return this.nickname;
    }
    public String getPassword() {
        return this.password;
    }
    public String getEmail() { return this.email; }
    public String getQuestion() { return this.question; }
    public String getAnswer() { return this.answer; }
    public int getLevel() { return this.Level; }
    public int getGold() { return this.gold; }
    public int getHp() { return this.hp; }
    public int getXp() { return this.xp; }
    public ArrayList<Card> getCards() { return this.cards; }
    public Card getCardByName(String name) {
        for(Card card : this.cards) {
            if(card.getName().equals(name))
                return card;
        }
        return null;
    }
    public void updateCard(Card card) {
        this.cards.remove(card);
        this.updateCard(card.upgrade());
    }
}

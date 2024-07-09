package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;

    }

    public Card destroy() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }


    public void addCard(Card card) {
        cards.add(card);
    }

}

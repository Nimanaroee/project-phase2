package model;

import javafx.scene.shape.Rectangle;

public class CardGraphic extends Rectangle{
    Card card;
    public CardGraphic(Card card) {
        super(100, 150);
        this.card = card;
    }
    public Card getCard() {
        return this.card;
    }
}

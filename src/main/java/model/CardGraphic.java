package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CardGraphic extends Rectangle{
    Card card;
    public CardGraphic(Card card) {
        super(100, 150);
        this.card = card;
        try {
            String imageName = card.getName().substring(1);
            Image image = new Image("file:src/main/resources/images/cards/"+imageName+".jpg");        //imageView.setSmooth(true);
            ImagePattern imagePattern = new ImagePattern(image);
            setFill(imagePattern);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    public Card getCard() {
        return this.card;
    }
}

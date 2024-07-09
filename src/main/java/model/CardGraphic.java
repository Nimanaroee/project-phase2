package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CardGraphic extends Rectangle {
    public String name;
    CardModel cardModel;

    public CardGraphic(CardModel cardModel) {
        super(100, 150);
        this.cardModel = cardModel;
        this.name = cardModel.getName();
        try {
            String imageName = cardModel.getName().substring(1);
            Image image = new Image("file:src/main/resources/images/cards/" + imageName + ".jpg");        //imageView.setSmooth(true);
            ImagePattern imagePattern = new ImagePattern(image);
            setFill(imagePattern);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public CardModel getCard() {
        return this.cardModel;
    }
}

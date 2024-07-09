package model;

import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
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
            if (cardModel.getName().equals("Block")) {
                setFill(Color.RED);
            } else {
                String imageName = cardModel.getName().substring(1);
                Image image = new Image("file:src/main/resources/images/cards/" + imageName + ".jpg");        //imageView.setSmooth(true);
                ImagePattern imagePattern = new ImagePattern(image);
                setFill(imagePattern);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        setOnDragDetected(event -> {
            Dragboard db = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(name);
            db.setContent(content);
            event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public CardGraphic(String empty) {
        super(100, 150);
        cardModel = new CardModel("empty", 0, 1, 0, 0, 0);
        setFill(Color.WHITE);
    }

    public CardModel getCard() {
        return this.cardModel;
    }
}

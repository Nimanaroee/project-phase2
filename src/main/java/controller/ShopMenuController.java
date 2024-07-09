package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Card;
import model.CardGraphic;
import model.Data;
import model.GraphicData;
import view.MainMenuView;
import java.util.ArrayList;
import java.util.Optional;

public class ShopMenuController {

    private ArrayList<CardGraphic> lockedCards;
    private ArrayList<CardGraphic> unlockedCards;

    @FXML
    private ScrollPane lockedScrollPane;
    @FXML
    private ScrollPane unlockedScrollPane;
    @FXML
    private HBox lockedHBox;
    @FXML
    private HBox unlockedHBox;

    @FXML
    public void initialize() {
        this.lockedCards = new ArrayList<>();
        this.unlockedCards = new ArrayList<>();

        // Add some sample cards
        ArrayList<Card> cards = Data.getAllCards();
        for (Card card : cards) {
            if (Data.getLoggedInUser1().getCardByName(card.getName()) == null)
                lockedCards.add(new CardGraphic(card));
            else
                unlockedCards.add(new CardGraphic(Data.getLoggedInUser1().getCardByName(card.getName())));
        }

        lockedHBox.getChildren().clear();
        lockedHBox.getChildren().addAll(createCardViews(lockedCards, true));
        lockedHBox.setSpacing(30);
        lockedScrollPane.setContent(lockedHBox);

        unlockedHBox.getChildren().clear();
        unlockedHBox.getChildren().addAll(createCardViews(unlockedCards, false));
        unlockedHBox.setSpacing(30);
        unlockedScrollPane.setContent(unlockedHBox);
    }

    public ArrayList<VBox> createCardViews(ArrayList<CardGraphic> cards, boolean isLocked) {
        ArrayList<VBox> cardViews = new ArrayList<>();
        for (CardGraphic card : cards) {
            VBox cardBox = new VBox(card, createCardDetails(card));
            cardBox.setOnMouseClicked(event -> handleMouseEvent(event, card, isLocked));
            cardViews.add(cardBox);
        }
        return cardViews;
    }

    public VBox createCardDetails(CardGraphic card) {
        VBox detailsBox = new VBox();
        detailsBox.getChildren().add(new Label("Price: " + card.getCard().getPrice()));
        return detailsBox;
    }

    public void handleMouseEvent(MouseEvent event, CardGraphic card, boolean isLocked) {
        if (event.getButton() == MouseButton.SECONDARY) {
            ContextMenu contextMenu = new ContextMenu();
            MenuItem actionItem = isLocked ? new MenuItem("Buy") : new MenuItem("Upgrade");

            actionItem.setOnAction(e -> {
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText(isLocked ? "Do you want to buy this card?" : "Do you want to upgrade this card?");

                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if (isLocked) {
                        card.setFill(Color.YELLOW); // Indicate the card is unlocked
                        lockedCards.remove(card);
                        unlockedCards.add(card);
                        Data.getLoggedInUser1().addCard(card.getCard());
                        // Refresh the UI to move the card from locked to unlocked
                    } else {
                        Data.getLoggedInUser1().updateCard(card.getCard());
                    }
                    initialize();
                }
            });

            contextMenu.getItems().add(actionItem);
            contextMenu.show(card, event.getScreenX(), event.getScreenY());
        } else if (event.getButton() == MouseButton.PRIMARY) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Card Information");
            alert.setHeaderText(card.getCard().getName());
            alert.setContentText(
                    "Attack: " + card.getCard().getDefence() + "\n" +
                            "Damage: " + card.getCard().getDamage() + "\n" +
                            "Duration: " + card.getCard().getDuration() + "\n" +
                            "Upgrade Level: " + card.getCard().getUpgradeLevel() + "\n" +
                            "Upgrade Cost: " + card.getCard().getUpgradeCoast() + "\n" +
                            "Price: " + card.getCard().getPrice()
            );
            alert.showAndWait();
        }
    }

    @FXML
    public void onClickBackButton() throws Exception {
        new MainMenuView().start(GraphicData.stage);
    }
}

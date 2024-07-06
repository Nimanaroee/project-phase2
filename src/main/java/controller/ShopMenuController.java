package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Card;
import model.Data;
import model.GraphicData;
import view.MainMenuView;
import java.util.ArrayList;
import java.util.Optional;

public class ShopMenuController {

    private ArrayList<Card> lockedCards;
    private ArrayList<Card> unlockedCards;
    public ScrollPane lockedScrollPane;
    public ScrollPane unlockedScrollPane;


    private void initialize() {

        this.lockedCards = new ArrayList<>();
        this.unlockedCards = new ArrayList<>();

        // Add some sample cards
        ArrayList<Card> cards = Data.getAllCards();
        for(Card card : cards) {
            if(Data.getLoggedInUser1().getCardByName(card.getName()) == null)
                lockedCards.add(card);
            else
                unlockedCards.add(Data.getCardByCardName(card.getName()));
        }

        FlowPane lockedFlowPane = new FlowPane();
        lockedFlowPane.getChildren().addAll(createCardViews(lockedCards, true));
        lockedScrollPane = new ScrollPane(lockedFlowPane);
        lockedScrollPane.setFitToWidth(true);

        FlowPane unlockedFlowPane = new FlowPane();
        unlockedFlowPane.getChildren().addAll(createCardViews(unlockedCards, false));
        unlockedScrollPane = new ScrollPane(unlockedFlowPane);
        unlockedScrollPane.setFitToWidth(true);
    }

    public FlowPane getLockedCardsPane() {
        FlowPane lockedFlowPane = new FlowPane();
        lockedFlowPane.getChildren().addAll(createCardViews(lockedCards, true));
        return lockedFlowPane;
    }

    public FlowPane getUnlockedCardsPane() {
        FlowPane unlockedFlowPane = new FlowPane();
        unlockedFlowPane.getChildren().addAll(createCardViews(unlockedCards, false));
        return unlockedFlowPane;
    }

    private ArrayList<VBox> createCardViews(ArrayList<Card> cards, boolean isLocked) {
        ArrayList<VBox> cardViews = new ArrayList<>();
        for (Card card : cards) {
            VBox cardBox = new VBox(card, createCardDetails(card));
            cardBox.setOnMouseClicked(event -> handleMouseEvent(event, card, isLocked));
            cardViews.add(cardBox);
        }
        return cardViews;
    }

    private VBox createCardDetails(Card card) {
        VBox detailsBox = new VBox();
        detailsBox.getChildren().add(new Label("Name: " + card.getName()));
        detailsBox.getChildren().add(new Label("Price: " + card.getPrice()));
        return detailsBox;
    }

    private void handleMouseEvent(MouseEvent event, Card card, boolean isLocked) {
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
                        Data.getLoggedInUser1().addCard(card);
                        // Refresh the UI to move the card from locked to unlocked
                    } else {
                        Data.getLoggedInUser1().updateCard(card);
                    }
                    initialize();
                }
            });

            contextMenu.getItems().add(actionItem);
            contextMenu.show(card, event.getScreenX(), event.getScreenY());
        } else if (event.getButton() == MouseButton.PRIMARY) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Card Information");
            alert.setHeaderText(card.getName());
            alert.setContentText(
                    "Attack: " + card.getAttack() + "\n" +
                            "Damage: " + card.getDamage() + "\n" +
                            "Duration: " + card.getDuration() + "\n" +
                            "Upgrade Level: " + card.getUpgradeLevel() + "\n" +
                            "Upgrade Cost: " + card.getUpgradeCoast() + "\n" +
                            "Price: " + card.getPrice()
            );
            alert.showAndWait();
        }
    }

    public void onClickBackButton(ActionEvent actionEvent) throws Exception {
        new MainMenuView().start(GraphicData.stage);
    }
}

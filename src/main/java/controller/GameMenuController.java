package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import game.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.CardGraphic;
import model.Data;

import java.util.ArrayList;
import java.util.Optional;

public class GameMenuController {
    public static Game game;
    ArrayList<CardGraphic> hand1 = new ArrayList<>();
    ArrayList<CardGraphic> hand2 = new ArrayList<>();
    ArrayList<CardGraphic> board1 = new ArrayList<>();
    ArrayList<CardGraphic> board2 = new ArrayList<>();

    @FXML
    private ScrollPane player2hand;

    @FXML
    private HBox player2handHbox;

    @FXML
    private Button endTurnButton;

    @FXML
    private ScrollPane player1hand;

    @FXML
    private HBox player1handHbox;


    @FXML
    private ScrollPane player1board;

    @FXML
    private HBox player2boardHbox;

    @FXML
    private HBox player1boardHbox;

    @FXML
    private ScrollPane player2board;

    @FXML
    private Label player2HP;

    @FXML
    private Label player1HP1;
    @FXML
    private Label Round;

    @FXML
    private void initialize() {
        ArrayList<Card> player1Hand = (ArrayList<Card>) game.getPlayer1Hand();
        for (Card card : player1Hand) {
            hand1.add(new CardGraphic(CardToCardConvertor.convertCardToCardModel(card)));
        }
        player1handHbox.getChildren().clear();
        player1handHbox.getChildren().addAll(createCardViews(hand1, true));
        player1handHbox.setSpacing(10);
        player1hand.setContent(player1handHbox);
        ArrayList<Card> player2Hand = (ArrayList<Card>) game.getPlayer2Hand();
        for (Card card : player2Hand) {
            hand2.add(new CardGraphic(CardToCardConvertor.convertCardToCardModel(card)));
        }
        player2handHbox.getChildren().clear();
        player2handHbox.getChildren().addAll(createCardViews(hand2, true));
        player2handHbox.setSpacing(10);
        player2hand.setContent(player2handHbox);
        ArrayList<Card> player1Board = (ArrayList<Card>) game.getPlayer1Board();
        for (Card card : player1Board) {
            if (card == null) {
                board1.add(new CardGraphic("empty"));
                continue;
            }
            board1.add(new CardGraphic(CardToCardConvertor.convertCardToCardModel(card)));
        }
        player1boardHbox.getChildren().clear();
        player1boardHbox.getChildren().addAll(createCardViews(board1, true));
        player1boardHbox.setSpacing(10);
        player1board.setContent(player1boardHbox);
        ArrayList<Card> player2Board = (ArrayList<Card>) game.getPlayer2Board();
        for (Card card : player2Board) {
            if (card == null) {
                board2.add(new CardGraphic("empty"));
                continue;
            }
            board2.add(new CardGraphic(CardToCardConvertor.convertCardToCardModel(card)));
        }
        player2boardHbox.getChildren().clear();
        player2boardHbox.getChildren().addAll(createCardViews(board2, true));
        player2boardHbox.setSpacing(10);
        player2board.setContent(player2boardHbox);
        player1HP1.setText("HP: " + game.getPlayer1().getHealth());
        player2HP.setText("HP: " + game.getPlayer2().getHealth());
        Round.setText("Round: " + game.getCurrentRound());


    }

    @FXML
    private void onClickEndTurnButton() {
        System.out.println("End Turn button clicked");
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
        if (CardToCardConvertor.convertCardModelToCard(card.getCard()).getType() == Card.SpecialCardType.NORMAL) {
            detailsBox.getChildren().add(new Label("duration: " + card.getCard().getDuration()));
            detailsBox.getChildren().add(new Label("damage: " + card.getCard().getDamage()));
            detailsBox.getChildren().add(new Label("defence: " + card.getCard().getDefence()));
        } else {
            detailsBox.getChildren().add(new Label("Special Card"));
            detailsBox.getChildren().add(new Label("Type: " + (CardToCardConvertor.convertCardModelToCard(card.getCard())).getType().toString()));
        }
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
}
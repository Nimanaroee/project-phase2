package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import game.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;
import javafx.animation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.util.Duration;

public class GameMenuController {
    public static Player playingPlayer;
    public static Game game;
    ArrayList<CardGraphic> hand1 = new ArrayList<>();
    ArrayList<CardGraphic> hand2 = new ArrayList<>();
    ArrayList<CardGraphic> board1 = new ArrayList<>();
    ArrayList<CardGraphic> board2 = new ArrayList<>();

    @FXML
    private AnchorPane gamePane;

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
    private Label player1HP;
    @FXML
    private Label Round;
    @FXML
    private Rectangle timelineIndicator;

    @FXML
    private void initialize() {

        playingPlayer = game.getPlayer1();
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
        player1boardHbox.getChildren().addAll(createCardViewsForBoard(board1, true));
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
        player2boardHbox.getChildren().addAll(createCardViewsForBoard(board2, true));
        player2boardHbox.setSpacing(10);
        player2board.setContent(player2boardHbox);
        player1HP.setText("HP: " + game.getPlayer1().getHealth());
        player2HP.setText("HP: " + game.getPlayer2().getHealth());
        Round.setText("Round: " + game.getCurrentRound());
        double stepSize = player1boardHbox.getWidth() / game.getPlayer1Board().size();
        timelineIndicator.toFront();
    }

    @FXML
    private void onClickEndTurnButton() {
        if (!game.isGameOver() && playingPlayer == game.getPlayer2()) {
            if (game.getCurrentRound() < 3) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("End of the round");
                alert.setHeaderText("End of the round");
                alert.setContentText("Round ended");
                alert.showAndWait();
                game.setCurrentRound(game.getCurrentRound() + 1);
                game.getPlayer1().drawCard();
                game.getPlayer2().drawCard();
            } else {
                endOfTheTurn();
                game.setCurrentRound(1);
                resetTimelineIndicator();
            }
        }
        if (playingPlayer == game.getPlayer1()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("End of the players 1 turn");
            alert.setHeaderText("End of the players 1 turn");
            alert.setContentText("Player 1 turn ended");
            alert.showAndWait();
            playingPlayer = game.getPlayer2();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("End of the players 2 turn");
            alert.setHeaderText("End of the players 2 turn");
            alert.setContentText("Player 2 turn ended");
            alert.showAndWait();
            playingPlayer = game.getPlayer1();
        }
        if (game.isGameOver()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Player winner = game.getPlayer1().getHealth() > game.getPlayer2().getHealth() ? game.getPlayer1() : game.getPlayer2();
            alert.setTitle("Game Over");
            alert.setHeaderText("Game Over");
            alert.setContentText("Player" + winner.getName() + " wins!");
            alert.showAndWait();
            endGame();
        }
        updateAll();

    }

    private void endGame() {
        if (game.getPlayer1().getHealth() <= 0) {
            Data.getLoggedInUser1().addHistory(new DataHistory("date", Data.getLoggedInUser2().getNickname() + " Won", Data.getLoggedInUser2().getNickname(), ((Integer) Data.getLoggedInUser2().getLevel()).toString(), "100 coins"));
            Data.getLoggedInUser2().addHistory(new DataHistory("date", Data.getLoggedInUser2().getNickname() + " Won", Data.getLoggedInUser1().getNickname(), ((Integer) Data.getLoggedInUser1().getLevel()).toString(), "100 coins"));
        } else {
            Data.getLoggedInUser2().addHistory(new DataHistory("date", Data.getLoggedInUser1().getNickname() + " Won", Data.getLoggedInUser1().getNickname(), ((Integer) Data.getLoggedInUser1().getLevel()).toString(), "100 coins"));
            Data.getLoggedInUser1().addHistory(new DataHistory("date", Data.getLoggedInUser1().getNickname() + " Won", Data.getLoggedInUser2().getNickname(), ((Integer) Data.getLoggedInUser2().getLevel()).toString(), "100 coins"));
        }
        GsonHandler gsonHandler = new GsonHandler();
        gsonHandler.saveUserGson();
        //temp
        System.exit(0);
    }


    private void endOfTheTurn() {
        moveTimelineIndicator();

    }

    public ArrayList<VBox> createCardViews(ArrayList<CardGraphic> cards, boolean isLocked) {
        ArrayList<VBox> cardViews = new ArrayList<>();
        for (CardGraphic card : cards) {
            VBox cardBox = new VBox(card, createCardDetails(card));
            cardBox.setOnMouseClicked(event -> handleMouseEvent(event, card, isLocked));
            cardBox.setOnDragOver(event -> handleDragOver(event, card));
            cardBox.setOnDragDropped(event -> handleDragDropped(event, card));
            cardViews.add(cardBox);
        }
        return cardViews;
    }

    public ArrayList<VBox> createCardViewsForBoard(ArrayList<CardGraphic> cards, boolean isLocked) {
        ArrayList<VBox> cardViews = new ArrayList<>();
        for (CardGraphic card : cards) {
            VBox cardBox = new VBox(card, createCardDetailsForBoard(card));
            cardBox.setOnMouseClicked(event -> handleMouseEvent(event, card, isLocked));
            cardBox.setOnDragOver(event -> handleDragOver(event, card));
            cardBox.setOnDragDropped(event -> handleDragDropped(event, card)); // Ensure this is set for all cards
            cardViews.add(cardBox);
        }
        return cardViews;
    }


    public VBox createCardDetails(CardGraphic card) {
        VBox detailsBox = new VBox();
        if (CardToCardConvertor.convertCardModelToCard(card.getCard()).getType() == Card.SpecialCardType.NORMAL) {
            detailsBox.getChildren().add(new Label("dur: " + card.getCard().getDuration()));
            detailsBox.getChildren().add(new Label("dam: " + card.getCard().getDamage()));
            detailsBox.getChildren().add(new Label("def: " + card.getCard().getDefence()));
        } else {
            detailsBox.getChildren().add(new Label("Special"));
            detailsBox.getChildren().add(new Label("Type: "));
            detailsBox.getChildren().add(new Label((CardToCardConvertor.convertCardModelToCard(card.getCard())).getType().toString()));
        }
        return detailsBox;
    }

    public VBox createCardDetailsForBoard(CardGraphic card) {
        VBox detailsBox = new VBox();
        if (CardToCardConvertor.convertCardModelToCard(card.getCard()).getType() == Card.SpecialCardType.NORMAL) {
            detailsBox.getChildren().add(new Label("dam: " + card.getCard().getDamage()));
            detailsBox.getChildren().add(new Label("def: " + card.getCard().getDefence()));
        }
        return detailsBox;
    }

    public void handleMouseEvent(MouseEvent event, CardGraphic card, boolean isLocked) {


        if (event.getButton() == MouseButton.PRIMARY) {
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

    public void handleDragOver(DragEvent event, CardGraphic targetCard) {
        if (event.getGestureSource() != targetCard && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void handleDragDropped(DragEvent event, CardGraphic targetCard) {
        System.out.println("fuckin help me");
        System.out.println(targetCard.getCard().getName());
        Dragboard db = event.getDragboard();
        boolean success = false;

        String draggedCardName = db.getString();
        CardGraphic draggedCard = findCardByName(draggedCardName);
        boolean durationIsOkay = true;
        int board = 0;
        int targetIndex;
        targetIndex = board1.indexOf(targetCard);
        board = 1;
        if (targetIndex == -1) {
            targetIndex = board2.indexOf(targetCard);
            board = 2;
        }
        for (int i = 0; i < draggedCard.getCard().getDuration(); i++) {
            if (board == 1) {
                if (!board1.get(targetIndex + i).getCard().getName().contains("empty")) {
                    durationIsOkay = false;
                    break;
                }
            } else {
                if (!board2.get(targetIndex + i).getCard().getName().contains("empty")) {
                    System.out.println("!" + board2.get(targetIndex + i).getCard().getName());
                    durationIsOkay = false;
                    break;
                }
            }
        }
        boolean isNormal = true;
        if (!(CardToCardConvertor.convertCardModelToCard(draggedCard.getCard()).getType() == Card.SpecialCardType.NORMAL)) {
            isNormal = false;
        }
        if (isNormal) {
            if (db.hasString() && targetCard.getCard().getName().equals("empty")) {
                if (durationIsOkay && draggedCard != null) {
                    for (int i = 0; i < draggedCard.getCard().getDuration(); i++) {
                        if (board == 1 && playingPlayer == game.getPlayer1()) {
                            board1.set(targetIndex + i, draggedCard);
                            game.getPlayer1Board().set(targetIndex + i, CardToCardConvertor.convertCardModelToCard(draggedCard.getCard()));
                        } else if (board == 2 && playingPlayer == game.getPlayer2()) {
                            board2.set(targetIndex + i, draggedCard);
                            game.getPlayer2Board().set(targetIndex + i, CardToCardConvertor.convertCardModelToCard(draggedCard.getCard()));
                        }
                    }
                    if (board == 1 && playingPlayer == game.getPlayer1()) {
                        for (int i = 0; i < game.getPlayer1Hand().size(); i++) {
                            if (game.getPlayer1Hand().get(i).getName().equals(draggedCard.getCard().getName())) {
                                game.getPlayer1Hand().remove(i);
                                break;
                            }
                        }


                    } else if (board == 2 && playingPlayer == game.getPlayer2()) {
                        for (int i = 0; i < game.getPlayer2Hand().size(); i++) {
                            if (game.getPlayer2Hand().get(i).getName().equals(draggedCard.getCard().getName())) {
                                game.getPlayer2Hand().remove(i);
                                break;
                            }
                        }
                    }
                    updateAll();
                    success = true;
                }
            }
        }
        if (!isNormal) {
            System.out.println("special");
            SpecialCard playedCard = (SpecialCard) CardToCardConvertor.convertCardModelToCard(draggedCard.getCard());
            if (playingPlayer == game.getPlayer1()) {
                playedCard.play(game.getGameBoard(), targetIndex, game.getPlayer1(), game.getPlayer2());
                for (int i = 0; i < game.getPlayer1Hand().size(); i++) {
                    if (game.getPlayer1Hand().get(i).getName().equals(draggedCard.getCard().getName())) {
                        game.getPlayer1Hand().remove(i);
                        break;
                    }
                }
            } else if (playingPlayer == game.getPlayer2()) {
                playedCard.play(game.getGameBoard(), targetIndex, game.getPlayer2(), game.getPlayer1());
                for (int i = 0; i < game.getPlayer2Hand().size(); i++) {
                    if (game.getPlayer2Hand().get(i).getName().equals(draggedCard.getCard().getName())) {
                        game.getPlayer2Hand().remove(i);
                        break;
                    }
                }
            }

            updateAll();
            success = true;
        }

        event.setDropCompleted(success);
        event.consume();
    }

    private void updateAll() {
        board1.clear();
        board2.clear();
        player1boardHbox.getChildren().clear();
        player2boardHbox.getChildren().clear();
        hand1.clear();
        hand2.clear();
        player1handHbox.getChildren().clear();
        player2handHbox.getChildren().clear();
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
        player1boardHbox.getChildren().addAll(createCardViewsForBoard(board1, true));
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
        player2boardHbox.getChildren().addAll(createCardViewsForBoard(board2, true));
        player2boardHbox.setSpacing(10);
        player2board.setContent(player2boardHbox);
        player1HP.setText("HP: " + game.getPlayer1().getHealth());
        player2HP.setText("HP: " + game.getPlayer2().getHealth());
        Round.setText("Round: " + game.getCurrentRound());
        timelineIndicator.toFront();
    }


    private CardGraphic findCardByName(String name) {
        for (CardGraphic card : hand1) {
            if (card.getCard().getName().equals(name)) {
                return card;
            }
        }
        for (CardGraphic card : hand2) {
            if (card.getCard().getName().equals(name)) {
                return card;
            }
        }
        return null;
    }

    private void moveTimelineIndicator() {
        double stepSize = player1boardHbox.getWidth() / game.getPlayer1Board().size();
        Timeline timeline = new Timeline();
        for (int i = 0; i < game.getPlayer1Board().size(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i), event -> {
                timelineIndicator.setX(stepSize * index);
                updatePlayerStats(index);
                updateAll();
            });
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setOnFinished(event -> {
            resetTimelineIndicator();
            updateAll();
        });
        timeline.play();
    }

    private void updatePlayerStats(int index) {
        if (game.getPlayer1Board().get(index) != null && game.getPlayer2Board().get(index) != null) {
            if (game.getPlayer1Board().get(index).getDamage() >= game.getPlayer2Board().get(index).getDefense()) {
                game.getPlayer2().setHealth(game.getPlayer2().getHealth() - game.getPlayer1Board().get(index).getDamage() + game.getPlayer2Board().get(index).getDefense());
                game.getPlayer2Board().set(index, null);
            } else if (game.getPlayer2Board().get(index).getDamage() >= game.getPlayer1Board().get(index).getDefense()) {
                game.getPlayer1().setHealth(game.getPlayer1().getHealth() - game.getPlayer2Board().get(index).getDamage() + game.getPlayer1Board().get(index).getDefense());
                game.getPlayer1Board().set(index, null);
            } else {
                game.getPlayer1Board().get(index).setDefense(game.getPlayer1Board().get(index).getDefense() - game.getPlayer2Board().get(index).getDamage());
                game.getPlayer2Board().get(index).setDefense(game.getPlayer2Board().get(index).getDefense() - game.getPlayer1Board().get(index).getDamage());
            }
        } else if (game.getPlayer1Board().get(index) != null && game.getPlayer2Board().get(index) == null) {
            game.getPlayer2().setHealth(game.getPlayer2().getHealth() - game.getPlayer1Board().get(index).getDamage());
        } else if (game.getPlayer2Board().get(index) != null && game.getPlayer1Board().get(index) == null) {
            game.getPlayer1().setHealth(game.getPlayer1().getHealth() - game.getPlayer2Board().get(index).getDamage());
        }
    }

    private void resetTimelineIndicator() {
        timelineIndicator.setX(0);
    }


}
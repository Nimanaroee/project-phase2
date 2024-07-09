package game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.*;

public class Player {
    public boolean hide = false;
    private int roundPoisened = 0;
    private String name;
    private List<Card> hand = new ArrayList<>();
    //fix 5 cards in hand
    private List<SpecialCard> specialCards = new ArrayList<>();
    private Deck deck;
    private List<Card> availableCards;
    private List<Card> discardPile;
    private int coins;
    private int health;
    private Game game;

    private int character;

    public Player(String name, List<Card> availableCards) {
        this.name = name;
        this.availableCards = availableCards;
        deck = new Deck(availableCards);
        discardPile = new ArrayList<>();
        coins = 0;
        health = 100;
    }

    public Player(User user) {
        this.name = user.getUsername();
        this.availableCards = CardToCardConvertor.convertCardModelListToCardList(user.getCards());
        deck = new Deck(availableCards);
        discardPile = new ArrayList<>();
        coins = 0;
        health = 100;
        this.character = user.getCharacter();
    }

//    public Deck chooseDeck() {
//        availableCards.forEach(card -> {
//            Card.showCard(card);
//            this.chooseCardForDeck(card);
//        });
//        return deck;
//    }

    public void drawCard() {
        deck.shuffle();
        int howManyShouldBeAdded = 5 - hand.size();
        for (int i = 0; i < howManyShouldBeAdded; i++) {
            Card card = deck.destroy();
            if (card == null) {
                deck = new Deck(discardPile);
                discardPile = new ArrayList<>();
                card = deck.destroy();
            }
            hand.add(card);
        }
    }

    public void discardCard(Card card) {
        discardPile.add(card);
    }

    public void chooseCardForDeck() {
//        System.out.println("Do you want to add this card to your deck? (y/n)");
//        String input;
//        Scanner scanner = new Scanner(System.in);
//        input = scanner.nextLine();
//        if (input.equals("y")) {
//            deck.addCard(card);
//        } else {
//            return;
//        }
//        this.deck=availableCards;

    }

    public void placeCard(Card card, int position, GameBoard board) {
        board.placeCard(card, this, position);
    }

    public void heal(int i) {
        health += i;
    }

    public void takeDamage(int i) {
        health -= i;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int i) {
        health = i;
    }

    public void playTurn(GameBoard gameBoard, CoinManager coinManager) {
        if (hide == false) {
            gameBoard.showBoard();
        }
        specialCards = new ArrayList<>();
        this.hand.forEach(card -> {
            if ((card instanceof SpecialCard)) {
                specialCards.add((SpecialCard) card);
            }
        });
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIt's " + name + "'s turn.");
        System.out.println("You have " + coins + " coins.");
        System.out.println("You have " + health + " health.");
        System.out.println("-----------------------");
        if (hide == false)
            this.showHand();
        System.out.println("-----------------------");
        System.out.println("Do you want to play a normal card? (y/n)");
        String input = scanner.nextLine();
        if (input.equals("y")) {
            playNormalCard(gameBoard, coinManager);
        }
        System.out.println("-----------------------");
        System.out.println("Do you want to play a special card? (y/n)");
        input = scanner.nextLine();
        if (input.equals("y")) {
            playSpecialCard(gameBoard, coinManager);
        }
        System.out.println("-----------------------");
        this.resetHide();

    }

    private void playSpecialCard(GameBoard gameBoard, CoinManager coinManager) {
        if (specialCards.isEmpty()) {
            System.out.println("You don't have any special cards to play.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        SpecialCard specialCard = selectSpecialCardToPlay();
        if (coinManager.canAfford(this, specialCard)) {
            coinManager.spendCoins(this, specialCard);
            System.out.println("Select a position to play the card: ");
            int position = scanner.nextInt();
            specialCard.play(gameBoard, position, this, gameBoard.getOpossingPlayer(this));
        } else {
            System.out.println("You can't afford that card.");
            selectSpecialCardToPlay();
        }
    }

    private SpecialCard selectSpecialCardToPlay() {

        System.out.println("Select a special card to play: ");
        specialCards.forEach(card -> {
            System.out.println(specialCards.indexOf(card) + ": " + card.getName());
            System.out.println("<><><><><><><><><><>");
        });
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the index of the card you want to play: ");
        int cardIndex = scanner.nextInt();
        try {
            SpecialCard card = (SpecialCard) specialCards.get(cardIndex);
        } catch (Exception e) {
            System.out.println("Invalid index");
            selectSpecialCardToPlay();
        }
        hand.remove(cardIndex);
        return (SpecialCard) specialCards.remove(cardIndex);

    }

    private void playNormalCard(GameBoard gameBoard, CoinManager coinManager) {
        boolean handHasNormalCards = false;
        for (Card card : hand) {
            if (!(card instanceof SpecialCard)) {
                handHasNormalCards = true;
                break;
            }
        }
        if (!handHasNormalCards) {
            System.out.println("You don't have any normal cards to play.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        Card card = selectCardToPlay();

        if (coinManager.canAfford(this, card)) {
            coinManager.spendCoins(this, card);
            System.out.println("Select a position to play the card: ");
            int position = scanner.nextInt();
            placeCard(card, position, gameBoard);
        } else {
            System.out.println("You can't afford that card.");
            selectCardToPlay();
        }

    }

    private Card selectCardToPlay() {
        System.out.println("Select a card to play: ");
        hand.forEach(card -> {
            if (!(card instanceof SpecialCard)) {
                Card.showCard(card);
                System.out.println(hand.indexOf(card) + ": " + card.getName());
                System.out.println("<><><><><><><><><><>");
            }
        });
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the index of the card you want to play: ");
        int cardIndex = scanner.nextInt();
        try {
            Card card = hand.get(cardIndex);
        } catch (Exception e) {
            System.out.println("Invalid index");
            selectCardToPlay();
        }
        if (hand.get(cardIndex).getName().equals(hand.get(2))) {
            // with probability p=1/15, buff the card
            Random random = new Random();
            if (random.nextInt() % 15 == 0) {
                hand.get(cardIndex).buffDefence(10);
                hand.get(cardIndex).buffDamage(10);
            }
        }
        return hand.remove(cardIndex);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public void showHand() {
        hand.forEach(card -> {
            Card.showCard(card);
            System.out.println("<><><><><><><><><><><><>");
        });
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void resetHide() {
        hide = false;
    }

    public int getRoundPoisened() {
        return roundPoisened;
    }

    public void setRoundPoisened(int roundPoisened) {
        this.roundPoisened = roundPoisened;
    }

    public void addACardToHand() {
        deck.shuffle();
        Card card = deck.destroy();
        if (card == null) {
            deck = new Deck(discardPile);
            discardPile = new ArrayList<>();
            card = deck.destroy();
        }
        hand.add(card);
    }
}


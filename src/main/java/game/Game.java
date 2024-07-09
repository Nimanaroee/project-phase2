package game;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.*;


public class Game {
    private Player player1, player2;
    private GameBoard gameBoard;
    private CoinManager coinManager;
    private int currentRound;


    public Game(User user1, User user2) {
        initializePlayers(user1, user2);
        gameBoard = new GameBoard(player1, player2);
        coinManager = new CoinManager(player1, player2);
        currentRound = 1;
    }

    public void start() {
        while (!isGameOver()) {
            playRound();
        }
        endGame();
    }


    private void initializePlayers(User user1, User user2) {

        this.player1 = new Player(user1);
        this.player2 = new Player(user2);
        this.player1.setGame(this);
        this.player2.setGame(this);
        player1.drawCard();
        player2.drawCard();
        this.gameBoard = new GameBoard(player1, player2);
        this.coinManager = new CoinManager(player1, player2);
    }


    private boolean isGameOver() {
        return player1.getHealth() <= 0 || player2.getHealth() <= 0;
    }

    private void endGame() {
        if (player1.getHealth() <= 0) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println(player1.getName() + " wins!");
        }
        User user1 = Data.getLoggedInUser1();
        User user2 = Data.getLoggedInUser2();


    }

    private void playRound() {

        System.out.println("-----------------------");
        System.out.println("current round: " + currentRound);
        System.out.println("-----------------------");

        System.out.println("\n-----------------------");
        player1.drawCard();
        player2.drawCard();
        player1.playTurn(gameBoard, coinManager);
        player2.playTurn(gameBoard, coinManager);
        if (currentRound >= 3) {
            endOfTheRound();
        }
        currentRound++;
    }

    private void endOfTheRound() {
        System.out.println("End of round!");
        endOfTurn(player1, player2, gameBoard);
        currentRound = 0;
    }

    public void endOfTurn(Player player1, Player player2, GameBoard gameBoard) {
        gameBoard.getBoard(player1).forEach(card -> {
            if (card == null) {
            } else {
                Card player2card = gameBoard.getOpossingCard(player1, player2, card);
                if (player2card == null) {
                    player2.setHealth(player2.getHealth() - card.getDamage());
//                    System.out.println(player2.getHealth() + " " + card.getDamage());
                } else {
                    player2card.setDefense(player2card.getDefense() - card.getDamage());
                    if (player2card.getDefense() <= 0) {
                        gameBoard.getBoard(player2).remove(player2card);
                        player2.setHealth(player2.getHealth() - card.getDamage() + player2card.getDefense());
                        player1.addACardToHand();
                        //bonus for killing enemy card
//                        System.out.println(player2.getHealth() + " " + card.getDamage() + " " + player2card.getDefense()  );
                    }
                }
            }
        });
        gameBoard.getBoard(player2).forEach(card -> {
            if (card == null) {
            } else {
                Card player1card = gameBoard.getOpossingCard(player2, player1, card);
                if (player1card == null) {
                    player1.setHealth(player1.getHealth() - card.getDamage());
//                    System.out.println(player1.getHealth() + " " + card.getDamage());
                } else {
                    player1card.setDefense(player1card.getDefense() - card.getDamage());
                    if (player1card.getDefense() <= 0) {
                        gameBoard.getBoard(player1).remove(player1card);
                        player1.setHealth(player1.getHealth() - card.getDamage() + player1card.getDefense());
                        player2.addACardToHand();

//                        System.out.println(player1.getHealth() + " " + card.getDamage() + " " + player1card.getDefense()  );
                    }
                }
            }
        });
        if (player1.getRoundPoisened() > 0) {
            player1.setHealth(player1.getHealth() - 5);
            player1.setRoundPoisened(player1.getRoundPoisened() - 1);
        }
        if (player2.getRoundPoisened() > 0) {
            player2.setHealth(player2.getHealth() - 5);
            player2.setRoundPoisened(player2.getRoundPoisened() - 1);
        }
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public List<Card> getPlayer1Hand() {
        return player1.getHand();
    }

    public List<Card> getPlayer2Hand() {
        return player2.getHand();
    }

    public List<Card> getBoardCards(Player player) {
        return gameBoard.getBoard(player);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<Card> getPlayer1Board() {
        return gameBoard.getBoard(player1);
    }

    public ArrayList<Card> getPlayer2Board() {
        return gameBoard.getBoard(player2);
    }

}

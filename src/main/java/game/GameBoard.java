package game;

import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {
    Player player1;
    Player player2;

    private ArrayList<Card> player1Board;
    private ArrayList<Card> player2Board;

    public GameBoard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Board = new ArrayList<>(20);
        player2Board = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            player1Board.add(null);
            player2Board.add(null);
        }

        int randomBlock = (int) (Math.random() * 20);
        player1Board.set(randomBlock, new Card("Block", 0, 0, 10000000, 1, 1));
        randomBlock = (int) (Math.random() * 20);
        player2Board.set(randomBlock, new Card("Block", 0, 0, 10000000, 1, 1));

    }

    public ArrayList<Card> getBoard(Player player) {
        if (player == player1) {
            return player1Board;
        } else {
            return player2Board;
        }

    }

    public void placeCard(Card card, Player player, int position) {
        Scanner scanner = new Scanner(System.in);
        boolean allOfCardCanBePlaced = true;
        if (player == player1) {
            for (int i = 0; i < card.getWidth(); i++) {
                if (position + i >= 20) {
                    allOfCardCanBePlaced = false;
                } else {
                    if (player1Board.get(position + i) != null) {
                        allOfCardCanBePlaced = false;
                        break;
                    }
                }
            }

            if (!allOfCardCanBePlaced) {
                System.out.println("There is already a card in this position");
                System.out.println("Please enter a new position");
                int newPosition = scanner.nextInt();
                placeCard(card, player, newPosition);
            } else {
                for (int i = 0; i < card.getWidth(); i++) {
                    player1Board.set(position + i, card);
                }
            }
        } else {
            for (int i = 0; i < card.getWidth(); i++) {
                if (position + i >= 20) {
                    allOfCardCanBePlaced = false;
                } else {
                    if (player2Board.get(position + i) != null) {
                        allOfCardCanBePlaced = false;
                        break;
                    }
                }
            }

            if (!allOfCardCanBePlaced) {
                System.out.println("There is already a card in this position");
                System.out.println("Please enter a new position");
                int newPosition = scanner.nextInt();
                placeCard(card, player, newPosition);
            } else {
                for (int i = 0; i < card.getWidth(); i++) {
                    player2Board.set(position + i, card);
                }
            }
        }


    }

    public Card getCard(Player player, int position) {
        if (player == player1) {
            return player1Board.get(position);
        } else {
            return player2Board.get(position);
        }
    }


    public Card getOpossingCard(Player player1, Player player2, Card card) {
        if (player1 == this.player1) {
            return player2Board.get(player1Board.indexOf(card));
        } else {
            return player1Board.get(player2Board.indexOf(card));
        }

    }

    public Player getOpossingPlayer(Player player) {
        if (player == player1) {
            return player2;
        } else {
            return player1;
        }
    }


    public void showBoard() {
        System.out.println("Player 1:");
        for (int i = 0; i < 20; i++) {
            if (player1Board.get(i) != null) {
                System.out.print(player1Board.get(i).getName() + "||");
            } else {
                System.out.print("empty||");
            }
        }
        System.out.println("\nPlayer 2:");
        for (int i = 0; i < 20; i++) {
            if (player2Board.get(i) != null) {
                System.out.print(player2Board.get(i).getName() + "||");
            } else {
                System.out.print("empty||");
            }
        }
    }
}

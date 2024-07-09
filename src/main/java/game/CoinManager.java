package game;

public class CoinManager {
    private Player player1;
    private Player player2;

    public CoinManager(Player player11, Player player22) {
        this.player1 = player11;
        this.player2 = player22;
        player1.setCoins(10);
        player2.setCoins(10);
    }

    public void addCoins(Player player, int coins) {
        if (player == player1) {
            player1.setCoins(player1.getCoins() + coins);
        } else {
            player2.setCoins(player2.getCoins() + coins);
        }
    }

    public void playCard(Player player, Card card) {
        if (player == player1) {
            player1.setCoins(player1.getCoins() - card.getCost());
        } else {
            player2.setCoins(player2.getCoins() - card.getCost());
        }
    }

    public boolean canAfford(Player player, Card card) {
        if (player == player1) {
            return player1.getCoins() >= card.getCost();
        } else {
            return player2.getCoins() >= card.getCost();
        }
    }

    public void spendCoins(Player player, Card card) {
        if (player == player1) {
            player1.setCoins(player1.getCoins() - card.getCost());
        } else {
            player2.setCoins(player2.getCoins() - card.getCost());
        }
    }

    public int getCoins(Player player) {
        if (player == player1) {
            return player1.getCoins();
        } else {
            return player2.getCoins();
        }
    }


    public boolean canAfford(Player player, SpecialCard specialCard) {
        if (player == player1) {
            return player1.getCoins() >= specialCard.getCost();
        } else {
            return player2.getCoins() >= specialCard.getCost();
        }
    }

    public void spendCoins(Player player, SpecialCard card) {
        if (player == player1) {
            player1.setCoins(player1.getCoins() - card.getCost());
        } else {
            player2.setCoins(player2.getCoins() - card.getCost());
        }
    }

}

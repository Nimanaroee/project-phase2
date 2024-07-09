package game;

public class SpecialCard extends Card {

    private final String name;
    private final String description;
    private final int cost;
    private final int attack;
    private final int defense;

    public SpecialCard(String name, String description, int cost, int attack, int defense, SpecialCardType type) {
        super(name, cost, 1, 1, cost, 1);
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.attack = attack;
        this.defense = defense;
        this.type = type;
    }

    public void play(GameBoard board, int position, Player playingPlayer, Player effectedPlayer) {
        switch (type) {
            case BOMB:
                for (int k = position; k < this.getWidth() + position; k++) {
                    Card card = board.getCard(effectedPlayer, k);

                    if (card == null) {
                        continue;
                    }

                    if (card.getDefense() <= 0)
                        board.getBoard(effectedPlayer).remove(k);
                }
                break;
            case SHIELD:
                for (int k = position; k < position + this.getWidth(); k++) {
                    Card card = board.getCard(playingPlayer, k);
                    if (card == null) {
                        continue;
                    }
                    card.buffDefence(this.defense);
                }
                break;
            case HEAL:
                playingPlayer.heal(10);
                break;
            case ROUNDSETBACKER:
                playingPlayer.getGame().setCurrentRound(playingPlayer.getGame().getCurrentRound() - 1);
                break;
            case ROUNDADVANCER:
                playingPlayer.getGame().setCurrentRound(playingPlayer.getGame().getCurrentRound() + 1);
                break;
            case CARDDELETER:
                effectedPlayer.getHand().remove(position);
                break;
            case CARDSTEALER:
                Card stolenCard = effectedPlayer.getHand().remove(position);
                playingPlayer.getHand().add(stolenCard);
                break;
            case DAMAGER:
                effectedPlayer.setHealth(effectedPlayer.getHealth() - attack);
                break;
            case HIDER:
                effectedPlayer.hide = true;
                break;
            case BLOCKER:
                board.getBoard(effectedPlayer).set(position, new Card("Block", 0, 0, 100000000, 0, 0));
                break;
            case POISONER:
                effectedPlayer.setRoundPoisened(3);
                break;
            case CHANGEBLOCKPOSITION:
                for (int i = 0; i < board.getBoard(playingPlayer).size(); i++) {
                    if (board.getBoard(playingPlayer).get(i).getName().equals("Block")) {
                        board.getBoard(playingPlayer).set(i, null);
                        break;
                    }
                }

                board.getBoard(playingPlayer).set(position, new Card("Block", 0, 0, 100000000, 0, 0));
                break;
        }

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void showCard() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Cost: " + cost);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
    }


}

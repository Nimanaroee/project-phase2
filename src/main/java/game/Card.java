package game;

public class Card {


    private final int initialDamage;
    private final int initialCost;
    private final int initialDefense;

    private final int width;
    private final int shopCost = 10;
    protected SpecialCardType type = SpecialCardType.NORMAL;
    private String name;
    private int damage;
    private int defense;
    //Defence is just health here
    private int cost;

    public Card(String name, int initialCost, int initialDamage, int initialDefense, int shopCost, int width) {
        this.name = name;
        this.initialCost = initialCost;
        this.initialDamage = initialDamage;
        this.initialDefense = initialDefense;
        this.cost = initialCost;
        this.damage = initialDamage / (width + 1);

        this.defense = initialDefense;
        this.width = width;
//        System.out.println("Card created: " + name);
    }

    public static void showCard(Card card) {
        System.out.println("Name: " + card.name);
        System.out.println("Damage: " + card.damage);
        System.out.println("Defense: " + card.defense);
        System.out.println("Width: " + card.width);
        System.out.println("Cost: " + card.cost);

    }

    public SpecialCardType getType() {
        return type;
    }

    public void setType(SpecialCardType type) {
        this.type = type;
    }

    public void play(GameBoard board, int position) {

    }

    public void buffDefence(int percentage) {
        defense += (int) (initialDefense * percentage / 100);
    }

    public void buffDamage(int percentage) {
        damage += (int) (initialDamage * percentage / 100);
    }

    public void nerfDefence(int percentage) {
        defense -= (int) (initialDefense * percentage / 100);
    }

    public void nerfDamage(int percentage) {
        damage -= (int) (initialDamage * percentage / 100);
    }

    public void reset() {
        cost = initialCost;
        damage = initialDamage;
        defense = initialDefense;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getShopCost() {
        return shopCost;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public enum SpecialCardType {
        BOMB,
        SHIELD,
        HEAL,
        ROUNDSETBACKER,
        ROUNDADVANCER,
        CARDDELETER,
        CARDSTEALER,
        HIDER,
        BLOCKER,
        POISONER,
        DAMAGER,
        NORMAL, CHANGEBLOCKPOSITION
    }
}

package model;

public class Card {
    String name;
    int defence;
    int duration;
    int damage;
    int upgradeLevel;
    int upgradeCost;
    int price;
    int percentage;

    public Card(String name, int defence, int duration, int damage, int upgradeLevel, int upgradeCoast) {
        this.name = name;
        this.defence = defence;
        this.duration = duration;
        this.damage = damage;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCoast;
        this.percentage = 100;
    }

    public static boolean validCard(int attack, int duration, int damage) {
        return (validDefenceAttack(attack) & validDuration(duration) & validDamage(damage));
    }
    public static boolean validDefenceAttack(int attack) {
        return (10 <= attack && attack <= 100);
    }
    public static boolean validDuration(int duration) {
        return (1 <= duration && duration <= 5);
    }
    public static boolean validDamage(int damage) {
        return (10 <= damage && damage <= 50);
    }
    public void setName(String name) {
        this.name = name;
        Data.updateCard(this);
    }
    public void setDefence(int defence) {
        this.defence = defence;
        Data.updateCard(this);
    }
    public void setDuration(int duration) {
        this.duration = duration;
        Data.updateCard(this);
    }
    public void setDamage(int damage) {
        this.damage = damage;
        Data.updateCard(this);
    }
    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
        Data.updateCard(this);
    }
    public void setUpgradeCoast(int upgradeCost) {
        this.upgradeCost = upgradeCost;
        Data.updateCard(this);
    }
    public void setPrice(int price) {
        this.price = price;
        Data.updateCard(this);
    }
    public void setUpgradeCost() {
        this.upgradeCost += DataGame.updateCost;
        Data.updateCard(this);
    }
    public void setPercentage() {
        this.price += 10;
        Data.updateCard(this);
    }
    public String getName() {
        return this.name;
    }
    public int getDefence() {
        return defence;
    }
    public int getDamage() {
        return damage;
    }
    public int getDuration() {
        return duration;
    }
    public int getUpgradeCoast() {
        return upgradeCost;
    }
    public int getUpgradeLevel() {
        return upgradeLevel;
    }
    public int getPrice() {
        return price;
    }
    public int getUpgradeCost() { return upgradeCost; }
    public int getPercentage() { return percentage; }

    public Card upgrade() {
        if(this.percentage == 150)
            return this;
        this.percentage += DataGame.updatePercentage;
        this.upgradeCost += DataGame.updateCost;
        this.damage = (int)(this.damage*(double)this.percentage/100.0);
        this.defence = (int)(this.defence *(double)this.percentage/100.0);

        return this;
    }
}

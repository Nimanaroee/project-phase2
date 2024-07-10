package model;

import java.util.ArrayList;

public class DataGame {
    public static final int updateCost = 10;
    public static final int updatePercentage = 10;
    public static int gamble = 0;
    public static String result;
    ArrayList<CardModel> firstDeck = new ArrayList<>();
    ArrayList<CardModel> secondDeck = new ArrayList<>();

    ArrayList<CardModel> firstBoard = new ArrayList<>(21);
    ArrayList<CardModel> secondBoard = new ArrayList<>(21);

}

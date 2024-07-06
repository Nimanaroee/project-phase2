package model;

public class DataHistory {
    String date, result, opponentName, opponentLevel, prize;

    public DataHistory(String date, String result, String opponentName, String opponentLevel, String prize) {
        this.date = date;
        this.result = result;
        this.opponentLevel = opponentLevel;
        this.opponentName = opponentName;
        this.prize = prize;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getOpponentLevel() {
        return opponentLevel;
    }

    public void setOpponentLevel(String opponentLevel) {
        this.opponentLevel = opponentLevel;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}

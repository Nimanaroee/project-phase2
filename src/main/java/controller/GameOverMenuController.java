package controller;

import javafx.scene.control.Label;
import model.Data;
import model.DataGame;
import model.DataHistory;
import model.GraphicData;
import view.GameMenuView;
import view.MainMenuView;

import java.io.IOException;
import java.time.LocalDateTime;

public class GameOverMenuController {
    public Label result;
    public Label gambleResult;

    public void initialize() {
        System.out.println(Data.getLoggedInUser1() + " " + Data.getLoggedInUser2());
        result.setText("Winner is "+ DataGame.result);
        if(DataGame.gamble > 0)
            gambleResult.setText(DataGame.result + " gets " + DataGame.gamble + " golds");
        else
            gambleResult.setText("");

        String date = LocalDateTime.now().toString();
        if(DataGame.result.equals(Data.getLoggedInUser1().getNickname())) {
            Data.getLoggedInUser1().addHistory(new DataHistory(date, Data.getLoggedInUser1().getNickname() + " Won", Data.getLoggedInUser2().getNickname(), ((Integer) Data.getLoggedInUser2().getLevel()).toString(), Integer.toString(DataGame.gamble)));
            Data.getLoggedInUser2().addHistory(new DataHistory(date, Data.getLoggedInUser2().getNickname() + " Lost", Data.getLoggedInUser1().getNickname(), ((Integer) Data.getLoggedInUser1().getLevel()).toString(), Integer.toString(-1*DataGame.gamble)));
        } else {
            Data.getLoggedInUser1().addHistory(new DataHistory(date, Data.getLoggedInUser2().getNickname() + " Lost", Data.getLoggedInUser2().getNickname(), ((Integer) Data.getLoggedInUser2().getLevel()).toString(), Integer.toString(-1*DataGame.gamble)));
            Data.getLoggedInUser2().addHistory(new DataHistory(date, Data.getLoggedInUser1().getNickname() + " Won", Data.getLoggedInUser1().getNickname(), ((Integer) Data.getLoggedInUser1().getLevel()).toString(), Integer.toString(DataGame.gamble)));
        }
    }

    public void onClickRematchButton() throws IOException {
        new GameMenuView().start(GraphicData.stage);
    }

    public void onClickBackButton() throws Exception {
        new MainMenuView().start(GraphicData.stage);
    }
}

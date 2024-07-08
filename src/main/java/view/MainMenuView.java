package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Card;
import model.Data;
import model.DataHistory;
import model.GraphicData;

import java.util.ArrayList;

public class MainMenuView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GraphicData.stage = stage;
        for(int i=0 ; i<10 ; i++) {
            Data.getLoggedInUser1().addHistory(new DataHistory(Integer.toString(i), "-"+Integer.toString(i), Integer.toString(i), Integer.toString(i), Integer.toString(i)));
        }
        Data.getLoggedInUser1().addCard(Data.getCardByCardName("1stealth−helper"));
        ArrayList<Card> cards = Data.getLoggedInUser1().getCards();
        for(int i=0 ; i<cards.size() ; i++) {
            Card card = cards.get(i);
            System.out.println(i + " " + card.getName() + "  " + card.getDamage());
        }

        FXMLLoader fxmlLoader = new FXMLLoader(RegisterMenuView.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), GraphicData.V, GraphicData.H);
        stage.setScene(scene);
        stage.show();
    }
}

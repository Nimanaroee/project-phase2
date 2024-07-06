package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Data;
import model.DataHistory;
import model.GraphicData;
import view.MainMenuView;
import java.util.ArrayList;
import java.util.Comparator;

public class HistoryMenuController {

    @FXML
    protected Button previousButton;
    @FXML
    protected Button nextButton;
    @FXML
    protected Button sortButton;
    @FXML
    protected ChoiceBox<String> sortByBox;
    @FXML
    protected TableView<DataHistory> table;

    ArrayList<DataHistory> gameHistory = new ArrayList<>();
    final static int LENGTH = 5;
    int startPoint = 0;

    @FXML
    private void initialize() {
        //// load game history
        sortByBox.getItems().add("date");
        sortByBox.getItems().add("result");
        sortByBox.getItems().add("opponent-name");
        sortByBox.getItems().add("opponent-level");
        sortByBox.setValue("date");

        gameHistory = Data.getHistories();

        startPoint = 0;

        updatePage();
    }
    @FXML
    protected void onClickPreviousButton() {
        if(startPoint-LENGTH >= 0)
            startPoint -= LENGTH;
        updatePage();
    }
    @FXML
    protected void onClickNextButton() {
        if(startPoint+LENGTH < gameHistory.size())
            startPoint += LENGTH;
        updatePage();
    }
    @FXML
    protected void onClickSortButton() {
        if(gameHistory == null)
            return;
        String type = sortByBox.getValue();
        switch (type) {
            case "result" -> gameHistory.sort(Comparator.comparing(DataHistory::getResult));
            case "opponent-name" -> gameHistory.sort(Comparator.comparing(DataHistory::getOpponentName));
            case "opponent-level" -> gameHistory.sort(Comparator.comparing(DataHistory::getOpponentLevel));
            case "prize" -> gameHistory.sort(Comparator.comparing(DataHistory::getPrize));
            default -> gameHistory.sort(Comparator.comparing(DataHistory::getDate));
        }

        startPoint = 0;
        updatePage();
    }
    @FXML
    protected void onClickBackButton() throws Exception {
        new MainMenuView().start(GraphicData.stage);
    }
    private void updatePage() {
        table.getItems().clear();
        table.getColumns().clear();

        TableColumn<DataHistory, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<DataHistory, String> opponentNameColumn = new TableColumn<>("Opponent Name");
        opponentNameColumn.setCellValueFactory(new PropertyValueFactory<>("opponentName"));

        TableColumn<DataHistory, String> opponentLevelColumn = new TableColumn<>("Opponent Level");
        opponentLevelColumn.setCellValueFactory(new PropertyValueFactory<>("opponentLevel"));

        TableColumn<DataHistory, String> prizeColumn = new TableColumn<>("Prize");
        prizeColumn.setCellValueFactory(new PropertyValueFactory<>("prize"));

        TableColumn<DataHistory, String> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));

        // Add columns to the table
        table.getColumns().add(dateColumn);
        table.getColumns().add(opponentNameColumn);
        table.getColumns().add(opponentLevelColumn);
        table.getColumns().add(prizeColumn);
        table.getColumns().add(resultColumn);

        // Add data to the table
        ObservableList<DataHistory> data = FXCollections.observableArrayList();

        for (int i = startPoint; i < Math.min(startPoint + LENGTH, gameHistory.size()); i++) {
            data.add(gameHistory.get(i));
        }

        table.setItems(data);

        //System.out.println(data.size() + " " + data.get(0).getDate());///////////////
    }
}

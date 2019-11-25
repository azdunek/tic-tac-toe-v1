package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MojController implements Initializable {
    @FXML
    Button field00;
    @FXML
    Button field01;
    @FXML
    Button field02;
    @FXML
    Button field10;
    @FXML
    Button field11;
    @FXML
    Button field12;
    @FXML
    Button field20;
    @FXML
    Button field21;
    @FXML
    Button field22;
    private int playerNumber = 1;
    @FXML
    Label nowPlayer;
    private int roundCounter = 0;
    @FXML
    GridPane gridPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void markButton(ActionEvent event) {
        Button button = (Button) event.getSource();

        printPlayerName();
        if (playerNumber == 1) {
            button.setText("X");
            button.getStyleClass().add("x-button");
            playerNumber = 0;
        } else {
            button.setText("O");
            button.getStyleClass().add("o-button");
            playerNumber = 1;
        }
        roundCounter++;
        button.setDisable(true);
        if (roundCounter > 4) {
            checkScore();
        }
    }

    private void printPlayerName() {
        if (playerNumber == 1) {
            nowPlayer.setText("Player ONE");
        } else {
            nowPlayer.setText("Player TWO");
        }
    }

    private void checkScore() {
        if (field00.getText().equals(field11.getText()) && field00.getText().equals(field22.getText()) && field00.getText().length() > 0 ||
                field20.getText().equals(field11.getText()) && field20.getText().equals(field02.getText()) && field20.getText().length() > 0 ||
                field00.getText().equals(field01.getText()) && field00.getText().equals(field02.getText()) && field00.getText().length() > 0 ||
                field10.getText().equals(field11.getText()) && field10.getText().equals(field12.getText()) && field10.getText().length() > 0 ||
                field20.getText().equals(field21.getText()) && field20.getText().equals(field22.getText()) && field20.getText().length() > 0 ||
                field00.getText().equals(field10.getText()) && field00.getText().equals(field20.getText()) && field00.getText().length() > 0 ||
                field01.getText().equals(field11.getText()) && field01.getText().equals(field21.getText()) && field01.getText().length() > 0 ||
                field02.getText().equals(field12.getText()) && field02.getText().equals(field22.getText()) && field02.getText().length() > 0) {
            nowPlayer.setText("The winner is " + nowPlayer.getText());
            disableAllButtons();
            showDoYouWantToExitDialog();
        }
    }

    private void showDoYouWantToExitDialog (){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,nowPlayer.getText()+"\nDo you want to exit?",ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if(alert.getResult()==ButtonType.YES) {
            Stage window = (Stage) gridPane.getScene().getWindow();
            window.close();
        }
    }

    private void disableAllButtons() {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node child : children) {
            child.setDisable(true);
        }
    }

}

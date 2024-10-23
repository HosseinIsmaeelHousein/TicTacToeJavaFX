package org.example.tictactoejavafx.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.tictactoejavafx.controller.GameController;
import org.example.tictactoejavafx.model.TicTacToeModel;
import org.example.tictactoejavafx.view.TicTacToeView;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Create an initial instance of TicTacToeView for the start screen
            TicTacToeView view = new TicTacToeView(stage);

            // Set up the initial start button action
            view.getStartGameButton().setOnAction(event -> {
                boolean isVsComputer = view.getVsComputerButton().isSelected();
                String player1Name = view.getPlayer1NameField().getText().isEmpty() ? "Player 1" : view.getPlayer1NameField().getText();
                String player2Name = view.getPlayer2NameField().getText().isEmpty() ? (isVsComputer ? "Computer" : "Player 2") : view.getPlayer2NameField().getText();

                // Create the model
                TicTacToeModel model = new TicTacToeModel(player1Name, player2Name, isVsComputer);

                // Create the controller with the model and view
                new GameController(model, view, isVsComputer, player1Name, player2Name);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

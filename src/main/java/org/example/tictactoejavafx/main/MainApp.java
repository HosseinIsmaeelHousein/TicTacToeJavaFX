package org.example.tictactoejavafx.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.tictactoejavafx.controller.GameController;
import org.example.tictactoejavafx.model.TicTacToeModel;
import org.example.tictactoejavafx.view.TicTacToeView;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create Model and View instances
            TicTacToeModel model = new TicTacToeModel();
            TicTacToeView view = new TicTacToeView(primaryStage);

            // Create Controller instance
            GameController controller = new GameController(model, view);

            // Scene setup is now handled in TicTacToeView
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

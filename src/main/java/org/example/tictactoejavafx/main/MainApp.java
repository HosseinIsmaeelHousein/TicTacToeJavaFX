package org.example.tictactoejavafx.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.tictactoejavafx.controller.GameController;
import org.example.tictactoejavafx.TicTacToeModel;
import org.example.tictactoejavafx.view.TicTacToeView;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Initialize the model, view, and controller
        TicTacToeModel model = new TicTacToeModel();
        TicTacToeView view = new TicTacToeView();
        GameController controller = new GameController(model, view);

        // Set up the main scene with the view
        Scene scene = new Scene(view, 400, 400); // Width and height for the window

        // Configure the stage
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}

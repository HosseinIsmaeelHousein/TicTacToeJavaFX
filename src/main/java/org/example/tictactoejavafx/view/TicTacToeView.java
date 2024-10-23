package org.example.tictactoejavafx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToeView {

    // UI Elements
    private Stage stage;
    private Scene scene;
    private GridPane board;
    private Button[][] cells;
    private Button resetButton;
    private Label scoreLabelX;
    private Label scoreLabelO;
    private TextField player1NameField;
    private TextField player2NameField;
    private RadioButton vsComputerButton;
    private RadioButton vsPlayerButton;
    private Button startGameButton;

    public TicTacToeView(Stage stage) {
        this.stage = stage;
        initializeUI();
    }

    // Initialize the main UI elements
    private void initializeUI() {
        // Initial setup screen components
        Label welcomeLabel = new Label("Welcome to Tic-Tac-Toe!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Radio buttons for choosing game mode
        ToggleGroup modeGroup = new ToggleGroup();
        vsComputerButton = new RadioButton("Play against Computer");
        vsComputerButton.setToggleGroup(modeGroup);
        vsPlayerButton = new RadioButton("Play against Another Player");
        vsPlayerButton.setToggleGroup(modeGroup);
        vsPlayerButton.setSelected(true);  // Default selection

        // Input fields for player names
        player1NameField = new TextField();
        player1NameField.setPromptText("Enter Player 1 Name");
        player2NameField = new TextField();
        player2NameField.setPromptText("Enter Player 2 Name");

        // Start game button
        startGameButton = new Button("Start Game");
        startGameButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px 20px;");

        // Layout for the start screen
        VBox startScreenLayout = new VBox(10, welcomeLabel, vsComputerButton, vsPlayerButton, player1NameField, player2NameField, startGameButton);
        startScreenLayout.setAlignment(Pos.CENTER);
        startScreenLayout.setPadding(new Insets(20));

        // Set up the start screen scene
        scene = new Scene(startScreenLayout, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    // Method to show the game board after starting the game
    public void showGameBoard(String player1Name, String player2Name) {
        // Create the game board (3x3 GridPane)
        board = new GridPane();
        board.setPadding(new Insets(10, 10, 10, 10));
        board.setHgap(5);
        board.setVgap(5);

        // Create the 3x3 grid buttons
        cells = new Button[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button cell = new Button();
                cell.setPrefSize(100, 100);  // Set preferred size for each cell
                cell.getStyleClass().add("button-cell");  // CSS style class
                cells[row][col] = cell;
                board.add(cell, col, row);  // Add button to the grid
            }
        }

        // Score labels and reset button
        scoreLabelX = new Label(player1Name + ": 0");
        scoreLabelX.getStyleClass().add("score-label");
        scoreLabelO = new Label(player2Name + ": 0");
        scoreLabelO.getStyleClass().add("score-label");
        resetButton = new Button("Reset Game");
        resetButton.getStyleClass().add("reset-button");

        // Layout to organize score labels and reset button
        HBox scoreBox = new HBox(20, scoreLabelX, scoreLabelO, resetButton);
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setPadding(new Insets(10));

        // BorderPane to organize everything together
        BorderPane gameLayout = new BorderPane();
        gameLayout.setCenter(board);
        gameLayout.setBottom(scoreBox);
        gameLayout.setPadding(new Insets(10));

        // Set the game scene
        scene = new Scene(gameLayout, 400, 500);
        stage.setScene(scene);
        stage.show();
    }

    // Getters for UI components
    public Button[][] getCells() {
        return cells;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Label getScoreLabelX() {
        return scoreLabelX;
    }

    public Label getScoreLabelO() {
        return scoreLabelO;
    }

    public Button getStartGameButton() {
        return startGameButton;
    }

    public RadioButton getVsComputerButton() {
        return vsComputerButton;
    }

    public RadioButton getVsPlayerButton() {
        return vsPlayerButton;
    }

    public TextField getPlayer1NameField() {
        return player1NameField;
    }

    public TextField getPlayer2NameField() {
        return player2NameField;
    }
}

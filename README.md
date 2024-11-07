
Tic-Tac-Toe Game

Overview
--------

This project is a simple yet interactive Tic-Tac-Toe game built with Java, implementing the Model-View-Controller (MVC) design pattern. The game provides a graphical user interface (GUI) using JavaFX, allowing two players to play Tic-Tac-Toe on a 3x3 grid.

The goal of the game is for players to take turns marking squares on the grid. The first player to align three of their symbols (either vertically, horizontally, or diagonally) wins. If all squares are filled without a winner, the game ends in a draw.

Project Structure
-----------------

This project uses an MVC architecture to separate concerns and make the application modular and easy to understand. Here is an overview of each component:

1. Model (TicTacToeModel.java) - Handles the game logic, including player turns, checking for a winner, and tracking the game state.
2. View (TicTacToeView.java) - Manages the graphical user interface using JavaFX, displaying the game board and updating it based on player actions.
3. Controller (GameController.java) - Acts as the intermediary between the model and the view. It receives player inputs from the view, updates the model accordingly, and refreshes the view to reflect the new game state.
4. Main Application (MainApp.java) - Launches the JavaFX application and initializes the model, view, and controller.

Features
--------

- Graphical User Interface (GUI): The game has a GUI created with JavaFX, making it user-friendly and interactive.
- Two-Player Gameplay: Supports two players, alternating turns on a single device.
- Win Detection and Draw Handling: Automatically detects winning conditions and declares the winner. If all cells are filled without a winner, the game ends in a draw.
- Restart Game Option: Allows players to reset the board and play a new game without restarting the application.

Requirements
------------

- Java JDK 8 or above
- JavaFX Library

How to Run the Game
-------------------

1. Clone the Repository:
   git clone <repository_url>

2. Navigate to the Project Directory:
   cd tic-tac-toe-game

3. Compile the Project:
   Make sure JavaFX is included in your classpath if it’s not automatically configured.

   javac -classpath .;path/to/javafx-sdk/lib/*.jar MainApp.java

4. Run the Game:
   java -classpath .;path/to/javafx-sdk/lib/*.jar MainApp

Replace path/to/javafx-sdk/lib with the actual path to the JavaFX SDK library on your machine.

How to Play
-----------

1. Launch the game following the steps above.
2. The game will display a 3x3 grid representing the Tic-Tac-Toe board.
3. Player 1 starts the game, using the "X" symbol.
4. Players take turns clicking on empty cells to mark their symbol.
5. The game checks for a win or a draw after each turn.
   - If a player achieves three symbols in a row (horizontally, vertically, or diagonally), they win, and a message is displayed.
   - If the board is full and no player has won, the game declares a draw.
6. Players can reset the game by pressing the "Restart" button.

Project Files and Classes
-------------------------

- MainApp.java: Initializes and starts the JavaFX application.
- TicTacToeModel.java: Defines the game's core logic, handling players' moves, win conditions, and board status.
- TicTacToeView.java: Manages the game's visual components using JavaFX, displaying the game board and messages.
- GameController.java: Coordinates actions between the model and view, interpreting player moves and updating the view.

Future Enhancements
-------------------

Potential enhancements to the project could include:
- Single-player mode with an AI opponent.
- Difficulty Levels for varying AI challenges.
- Scorekeeping to track wins, losses, and draws across multiple games.
- Sound Effects to make gameplay more engaging.

Conclusion
----------

This Tic-Tac-Toe game is a simple yet robust application demonstrating key programming concepts, including GUI development with JavaFX and the MVC architectural pattern. It serves as an excellent foundational project for expanding into more complex games or applications with enhanced user interactivity.

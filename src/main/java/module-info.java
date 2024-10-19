module org.example.tictactoejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    // Opens packages to allow JavaFX to access FXML controllers
    opens org.example.tictactoejavafx.main to javafx.fxml;
    opens org.example.tictactoejavafx.controller to javafx.fxml;

    // Exports packages to allow them to be used outside the module
    exports org.example.tictactoejavafx.main;
    exports org.example.tictactoejavafx.controller;
    exports org.example.tictactoejavafx.view;
    exports org.example.tictactoejavafx.model;
}

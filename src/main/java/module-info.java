module org.example.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.tictactoe to javafx.fxml;
    exports org.example.tictactoe;
}
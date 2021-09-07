module SudokuSolverGUI {
    requires javafx.controls;
    requires javafx.fxml;

    opens core to javafx.fxml;
    exports core;
}
/**
 * @author Mateusz Koscielniak
 * This is a main class, responsible for starting the program
 */
package core;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);

        // Create a blank panel to use initially
        Group root = new Group();
        Scene scene = new Scene(root, 750, 500);

        // Set up the window
        stage.setTitle("Sudoku Solver");
        stage.setScene(scene);
        stage.show();

        // Switch to the main menu
        SceneManager.setScene(SceneManager.GRID_BOARD);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

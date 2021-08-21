/**
 * @author Mateusz Koscielniak
 * This is a Controller class for GridBoard
 */
package core;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GridBoardController {
   @FXML
   private Pane mainPane;

   @FXML
   private TilePane cellsPane;
   private ArrayList<Cell> cells;

   /**
    * This is a method that generates a new sudoku
    */
   public void generateNewSudoku() throws IOException {
      Grid puzzle = RandomPuzzleGen.getRandomPuzzle(); // getting random grid

      // Printing grids to the console
      System.out.println("UNSOLVED SUDOKU: ");
      System.out.println(puzzle.toString()); // Printing randomly generated grid
      Grid temp = puzzle.getGridCopy();

      Grid solvedPuzzle = temp.getSolvedGrid();// solving new grid
      System.out.println("SOLVED SUDOKU: ");
      System.out.println(solvedPuzzle.toString()); // Printing solved grid

      cells = new ArrayList<Cell>();

      //Loading each grid cell
      for (int y = 0; y < 9; y++) {
         for (int x = 0; x < 9; x++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Cell.fxml"));

            // Add the panel
            Parent panel = loader.load();

            Cell cell = (Cell) loader.getController();

            if(puzzle.get(x, y) == 0)
               cell.setEditable(true);
            else{
               cell.setEditable(false);
               cell.setValueLabel(puzzle.get(x, y));
            }

            cell.setCorrectValue(solvedPuzzle.get(x, y));
            cells.add(cell);
            cellsPane.getChildren().add(panel);
         }
      }
   }

   /**
    * This is a method that is called after changing scene to GridBoardController, it calls generateNewSudoku
    */
   @FXML
   public void initialize()
   throws IOException {
      generateNewSudoku();
   }

   /**
    * This is a method which quits the game
    */
   public void quit() {
      // Fetch the stage from the main panel, and close it
      Stage stage = (Stage) mainPane.getScene().getWindow();
      stage.close();
   }

   /**
    * This is a method that checks all the answers, if given cell is valid it's highlighted in green, otherwise it's red
    */
   public void validateAnswer() {
      boolean sudokuSolved = true;
      for (Cell c : cells) {
         if (c.isEditable())
            if(!c.validate()) sudokuSolved = false;
      }
      if(sudokuSolved){
         displayCongrats();
      }
   }

   /**
    * This is a method that displays a popup window with congratulations, called only when the entire sudoku is valid
    */
   public void displayCongrats(){
      Stage stage = new Stage();

      // Create a simple scene to congratulate
      Pane pane = new Pane();
      Label description = new Label("Congratulations, You solved the Sudoku!");
      description.setTextFill(Paint.valueOf("#315b63"));
      pane.getChildren().add(description);

      description.setPadding(new Insets(5));
      pane.setPadding(new Insets(5));
      pane.setStyle("-fx-background-color:   #cf9730; -fx-font-weight: bold;");

      description.setStyle("  -fx-font-size: 20;");
      Button bt = new Button("Close");
      bt.setOnAction((ActionEvent event) -> stage.close());

      bt.setLayoutX(165);
      bt.setLayoutY(75);

      bt.setPrefWidth(65);
      bt.setPrefHeight(40);
      bt.setTextFill(Paint.valueOf("#cf9730"));
      bt.setStyle("-fx-background-color: #315b63; ");

      pane.getChildren().add(bt);
      Scene scene = new Scene(pane);
      stage.setScene(scene);
      stage.show();
   }

   /**
    * This is a method that resets the cells - clears user input and changes cells' color back to white
    */
   public void restart(){
      for(Cell c: cells){
         c.reset();
      }
   }

   /**
    * This is a method that reloads the scene, it starts a new game
    */
   public void newGame(){
      SceneManager.setScene(SceneManager.GRID_BOARD);
   }

}

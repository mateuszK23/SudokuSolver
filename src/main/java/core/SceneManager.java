/**
 * @author Mateusz Koscielniak
 * This is a class that manages(changes) the scenes
 */

package core;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
   public static final String GRID_BOARD = "GridBoard.fxml";

   @FXML
   private static Stage mainStage;

   /**
    * This is a method that sets the stage
    */
   public static void setStage(Stage stage) {
      mainStage = stage;
   }

   /**
    * This is a method that based on menuFile changes the scene
    * @param menuFile String with file name of wanted fxml
    */
   public static Object setScene(String menuFile) {
      try {
         // Load the main menu
         FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(menuFile));
         Parent root = loader.load();
         mainStage.getScene().setRoot(root);
         return loader.getController();
      } catch (IOException e) {
         System.out.println("Unable to load menu file: " + menuFile);
         e.printStackTrace();
      }
      return null;
   }
}

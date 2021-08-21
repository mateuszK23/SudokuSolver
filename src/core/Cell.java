/**
 * @author Mateusz Koscielniak
 * This is a cell class, it is responsible for managing each cell
 */
package core;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Cell {
   @FXML
   private Label valueLabel;
   @FXML
   private TextField textField;

   private int correctValue;
   private boolean isEditable = false;
   /**
    * This is a method that sets a cell's correct value
    */
   public void setCorrectValue(int correct_value) {
      this.correctValue = correct_value;
   }

   /**
    * This is a method that sets cell's label's text
    */
   public void setValueLabel(int val){
      valueLabel.setText(String.valueOf(val));
   }

   /**
    * This is a method that validates the cell, if it's valid it's highlighted in green, otherwise in red
    */
   public boolean validate(){
      boolean isValueCorrect;
      if(!textField.getText().isEmpty()){
        isValueCorrect = correctValue == Integer.parseInt(textField.getText());
      }else isValueCorrect = false;

      if(isValueCorrect){
         textField.setStyle("-fx-background-color: #00ff08");
      }else{
         textField.setStyle("-fx-background-color: #ff0000");
      }
      return isValueCorrect;
   }

   /**
    * This is method returns the value of isEditable
    */
   public boolean isEditable(){
      return isEditable;
   }

   /**
    * This is method sets whether the cell is editable or not
    */
   public void setEditable(boolean isEditable){
      this.isEditable = isEditable;
      valueLabel.setVisible(!isEditable);
      textField.setVisible(isEditable);
   }
   /**
    * This method resets the cell, clears user input and changes the colour back to white
    */
   public void reset(){
      textField.clear();
      textField.setStyle("-fx-background-color: #ffffff");
   }
}

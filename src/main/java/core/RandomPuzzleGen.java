/**
 * @author Mateusz Koscielniak
 * This is a class used for generating random sudoku puzzle
 */
package core;

public class RandomPuzzleGen {
    /**
     * This is a method that return random value from range begin - end
     *
     * @param begin lowest value the method might return
     * @param end   highest value the method might return
     * @return random number from range begin - end
     */
    static int getRandomNumberInRange(int begin, int end) {
        return begin + (int) (Math.random() * ((end - begin + 1)));
    }

    /**
     * This is a method that creates a random sudoku puzzle
     *
     * @return random sudoku puzzle
     */
    static public Grid getRandomPuzzle() {
        Grid grid = new Grid();
        grid = grid.getSolvedGrid();
        int[] temp = new int[9];
        for (int a = 0; a < 9; a += 3) {
            // Swapping rows
            int randRows = getRandomNumberInRange(0, 2); // random offset for indexes of rows soon to be swapped
            if (randRows != 0) {
                // If chosen row is different than current one
                // then swap current row with row with index = current row + randRows
                for (int i = 0; i < 9; i++) {
                    temp[i] = grid.get(a, i);
                    grid.set(a, i, grid.get(a + randRows, i));
                    grid.set(a + randRows, i, temp[i]);
                }
            }
            // Swapping columns
            int randCol = getRandomNumberInRange(0, 2);   // random offset for indexes of columns soon to be swapped
            if (randCol != 0) {
                // If chosen column is different than current one
                // then swap current column with column with index = current column + randCol
                for (int i = 0; i < 9; i++) {
                    temp[i] = grid.get(i, a);
                    grid.set(i, a, grid.get(i, a + randCol));
                    grid.set(i, a + randCol, temp[i]);
                }
            }
        }
        for (int i = 0; i < 9; i++) { // number of rows which is ALWAYS 9
            int randValue = getRandomNumberInRange(1, 9); // 1 - 9 number of cells to be removed for the next row
            for (int j = 0; j < randValue; j++) { // number of cells to be removed random for every row
                int randCell = getRandomNumberInRange(0, 8); // random index of cell 0-8
                if (grid.get(i, randCell) != 0) grid.set(i, randCell, 0); // setting random cell to zero(empty)
            }
        }
        return grid;
    }
}

/**
 * @author Mateusz Koscielniak
 * This is a Grid class, it is responsible for managing the grid
 */
package core;


public class Grid {
    private final int[][] table;
    /**
     * This is a standard constructor, creates a grid and fills it with zeros
     */
    public Grid() {
        table = new int[9][9];
        // Initializing initial table 9x9 and setting every cell to 0
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                table[i][j] = 0;
            }
        }
    }
    /**
     * This is method sets the table
     */
    private void setTable(int [][] newTable){
        for (int i = 0; i < 9; i++) {
            System.arraycopy(newTable[i], 0, table[i], 0, 9);
        }
    }
    /**
     * This is a method that returns a cell's value based on x and y
     */
    public int get(int x, int y){
        return table[x][y];
    }

    /**
     * This is a method that sets a cell's value
     */
    public void set(int x, int y, int val) {
        table[x][y] = val;
    }

    /**
     * This is a method that checks whether given cell is valid or not, returns either true or false
     */
    public boolean isValidCell(int x, int y) {
                int temp = table[x][y];
                if (temp != 0) {
                    for (int i = 0; i < 9; i++) {
                        // Checking row
                        if (table[x][i] == temp && i != y) return false; // if temp value is found twice return false
                        // Checking column
                        if (table[i][y] == temp && i != x) return false; // if temp value is found twice return false
                    }
                    // Checking 3x3 square
                    // x - x%3 calculates starting point of the square for rows
                    // by adding 3 to the previous equation it calculates the end point for rows
                    // start and end point for columns are calculated exactly the same way as for the rows
                    int rowStartPoint = x - x % 3;
                    int columnStartPoint = y - y % 3;
                    for (int a = rowStartPoint; a < rowStartPoint + 3; a++) {
                        for (int b = columnStartPoint; b < columnStartPoint + 3; b++) {
                            if (table[a][b] == temp && a != x && b != y) return false;
                        }
                    }
                }
        return true;
    }

    /**
     * This is a method that solves a grid, returns either true or false
     */
    public boolean solve() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int cell = get(x, y);
                if (cell == 0) {
                    for (int i = 1; i <= 9; i++) {
                        set(x, y, i);
                        if (isValidCell(x, y)) {
                            boolean result = solve();
                            if (result) return true;
                        }
                    }
                    set(x, y, 0);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This is a method that returns a solved grid
     *
     * @return solved grid
     */
    public Grid getSolvedGrid() {
        solve();
        return this;
    }

    /**
     * This is a method that displays the grid to the console
     */
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                b.append(get(x, y)).append(" ");
            }
            b.append("\n");
        }
        return b.toString();
    }

    /**
     * This is a method that returns a copy of the grid
     */
    public Grid getGridCopy(){
        Grid temp = new Grid();
        temp.setTable(this.table);
        return temp;
    }
}

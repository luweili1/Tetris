package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

/**
 * A class representing the tetris board.
 */
public class TetrisBoard extends Grid<Character> {

    /**
     * Constructs a TetrisBoard with the specified number of rows and columns.
     * 
     * @param row
     * @param col
     */
    public TetrisBoard(int row, int col) {
        super(row, col, '-');
    }

    /**
     * A string reprensenting the grid.
     * 
     * @return A string representation of the grid where each cell value is on a
     *         separate line.
     */
    public String prettyString() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < this.rows(); row++) {
            for (int col = 0; col < this.cols(); col++) {
                result.append(get(new CellPosition(row, col)));
            }
            result.append(System.lineSeparator());
        }

        return result.toString().substring(0, result.length() - 1);
    }

    /**
     * Checks if an element exists in the specified row of the grid.
     * 
     * @param row The row to be checked.
     * @param c   The element to seach for.
     * @return True if the element exist in the row, false otherwise.
     */

    public boolean elementExistsInRow(int row, Character c) {
        for (int i = 0; i < this.cols(); i++) {
            Character value = get(new CellPosition(row, i));

            if (value == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the value of each cell in the specified row.
     * 
     * @param row The given row.
     * @param c   The character to set in the cells of the row.
     */
    public void setRowValue(int row, Character c) {
        for (int i = 0; i < this.cols(); i++) {
            this.set(new CellPosition(row, i), c);
        }
    }

    /**
     * Copies the values of cells from one row to another row in the same grid.
     * 
     * @param copyRow  The index of the row to copy from.
     * @param pasteRow The index of the row to paste into.
     */
    public void copyRow(int copyRow, int pasteRow) {
        for (int i = 0; i < this.cols(); i++) {
            this.set(new CellPosition(pasteRow, i), this.get(new CellPosition(copyRow, i)));
        }
    }

    /**
     * Removes full rows in the grid and shifts the rows above them down.
     * 
     * @return The number of rows that were removed.
     */
    public int removeFullRows() {
        int removedRows = 0;
        int a = this.rows() - 1;
        int b = this.rows() - 1;
        while (a >= 0) {
            while (b >= 0 && !elementExistsInRow(b, '-')) {
                removedRows++;
                b--;
            }
            if (b >= 0) {
                copyRow(b, a);
            } else {
                setRowValue(a, '-');
            }
            a--;
            b--;
        }
        return removedRows;
    }

    /**
     * Clears the grid by setting all cells to contain '-'.
     */
    public void clear() {
        for (int row = 0; row < this.rows(); row++) {
            for (int col = 0; col < this.cols(); col++) {
                set(new CellPosition(row, col), '-');
            }
        }
    }

    /**
     * Removes any full rows in the grid, except for the bottom row.
     * 
     * @return The number of rows that were removed.
     */
    public int removeFullRowsKeepBottomRow() {
        int removedRows = 0;
        int a = this.rows() - 1;
        int b = this.rows() - 1;
        while (a > 0) {
            while (b >= 0 && !elementExistsInRow(b, '-')) {
                removedRows++;
                b--;
            }
            if (b >= 0) {
                copyRow(b, a);
            } else {
                setRowValue(a, '-');
            }
            a--;
            b--;
        }
        return removedRows;
    }

}

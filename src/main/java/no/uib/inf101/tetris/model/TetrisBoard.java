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

    public String prettyString() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < this.rows(); row++) {
            for (int col = 0; col < this.cols(); col++) {
                result.append(get(new CellPosition(row, col)));
            }
            result.append(System.lineSeparator()); // Legg til linjeskift mellom hver rad
        }

        return result.toString().substring(0, result.length() - 1);
    }

    public boolean elementExistsInRow(int row, Character c) {
        for (int i = 0; i < this.cols(); i++) {
            Character value = get(new CellPosition(row, i));

            if (value == c) {
                return true;
            }
        }
        return false;
    }

    public void setRowValue(int row, Character c) {
        for (int i = 0; i < this.cols(); i++) {
            this.set(new CellPosition(row, i), c);
        }
    }

    public void copyRow(int copyRow, int pasteRow) {
        for (int i = 0; i < this.cols(); i++) {
            this.set(new CellPosition(pasteRow, i), this.get(new CellPosition(copyRow, i)));
        }
    }

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

    public void clear() {
        for (int row = 0; row < this.rows(); row++) {
            for (int col = 0; col < this.cols(); col++) {
                set(new CellPosition(row, col), '-');
            }
        }
    }

}

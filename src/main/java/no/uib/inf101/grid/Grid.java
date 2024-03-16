package no.uib.inf101.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representing a grid for tetris.
 * Using the generic type E and implements the interface IGrid.
 */
public class Grid<E> implements IGrid<E> {

    int row;
    int col;
    public final List<List<E>> grid;

    /**
     * Creates a grid with given number of rows and columns.
     * Each cell of the grid is initialized with a default value E.
     * 
     * @param row
     * @param col
     * @param defaultValue
     */

    public Grid(int row, int col, E defaultValue) {
        this.row = row;
        this.col = col;
        grid = new ArrayList<>();

        for (int r = 0; r < this.row; r++) {
            List<E> rowList = new ArrayList<>();

            for (int c = 0; c < this.col; c++) {
                rowList.add(defaultValue);

            }
            grid.add(rowList);
        }
    }

    /**
     * Creates a grid with given number of rows and columns.
     * 
     * @param row
     * @param col
     */

    public Grid(int row, int col) {
        this(row, col, null);

    }

    @Override
    public int rows() {
        return this.row;
    }

    @Override
    public int cols() {
        return this.col;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {

        List<GridCell<E>> cells = new ArrayList<>();

        for (int row = 0; row < this.row; row++) {
            for (int col = 0; col < this.col; col++) {

                CellPosition pos = new CellPosition(row, col);
                E value = this.get(pos);
                GridCell<E> cell = new GridCell<>(pos, value);
                cells.add(cell);

            }
        }
        return cells.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position is not on the grid.");
        }
        grid.get(pos.row()).set(pos.col(), value);

    }

    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position is not on the grid.");

        }
        return grid.get(pos.row()).get(pos.col());
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        boolean isOnRow = pos.row() >= 0 && pos.row() < this.row;
        boolean isOnCol = pos.col() >= 0 && pos.col() < this.col;
        return isOnRow && isOnCol;

    }

    @Override
    public Integer get(int i, int j) {
        return this.get(i, j);
    }

}

package no.uib.inf101.tetris.model.tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;

/**
 * Class representing tetrominos.
 */
public final class Tetromino implements Iterable<GridCell<Character>> {
    private final char symbol;
    private final boolean[][] shape;
    public CellPosition cellPosition;

    /**
     * Constructorfor the tetromino class.
     * 
     * @param symbol       The symbol representing the tetromino.
     * @param shape        The shape of the tetromino.
     * @param cellPosition The cellposition of the tetromino.
     */
    private Tetromino(char symbol, boolean[][] shape, CellPosition cellPosition) {
        this.symbol = symbol;
        this.shape = shape;
        this.cellPosition = cellPosition;
    }

    /**
     * Creating tetrominos with specific symbols.
     * 
     * @param symbol The symbol representing the tetromino.
     * @return A tetromino with the specific symbols.
     */
    public static Tetromino newTetromino(char symbol) {
        boolean[][] shape;
        switch (symbol) {
            case 'L':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, true },
                        { true, false, false }
                };

                break;

            case 'J':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, true },
                        { false, false, true }
                };

                break;
            case 'S':
                shape = new boolean[][] {
                        { false, false, false },
                        { false, true, true },
                        { true, true, false }
                };

                break;
            case 'Z':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, false },
                        { false, true, true }
                };

                break;
            case 'T':
                shape = new boolean[][] {
                        { false, false, false },
                        { true, true, true },
                        { false, true, false }
                };

                break;
            case 'I':
                shape = new boolean[][] {
                        { false, false, false, false },
                        { true, true, true, true },
                        { false, false, false, false },
                        { false, false, false, false }
                };

                break;
            case 'O':
                shape = new boolean[][] {
                        { false, false, false, false },
                        { false, true, true, false },
                        { false, true, true, false },
                        { false, false, false, false }
                };

                break;

            default:
                throw new IllegalArgumentException("Unknown type: " + symbol);
        }

        return new Tetromino(symbol, shape, new CellPosition(0, 0));
    }

    /**
     * Returns the shape of the tetromino.
     * 
     * @return Returns the shape in form of a boolean 2D array.
     */
    public boolean[][] getShape() {
        return shape;
    }

    /**
     * Returning the symbol representing the tetromino.
     * 
     * @return The symbol representing the tetrominos.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * New tetromino that is created and is shifted by the specified amount in rows
     * and columns.
     * 
     * @param deltaRow The amount to shift the tetromino in rows.
     * @param deltaCol The amount to shift the tetromino in cols.
     * @returnA new Tetromino object
     */
    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(cellPosition.row() + deltaRow, cellPosition.col() + deltaCol);
        return new Tetromino(symbol, shape, newPosition);
    }

    /**
     * Creating a new Tetromino that is shifted to the top center.
     * 
     * @param gridDimension The grid dimension to shift the tetromino to.
     * @return A new Tetromino object that is shifted to the top center of the grid
     *         dimension.
     */
    public Tetromino shiftedToTopCenterOf(GridDimension gridDimension) {
        int col = (gridDimension.cols() - shape[0].length) / 2;
        return shiftedBy(-1, col);
    }

    /**
     * Rotates the tetromino.
     * 
     * @return A new Tetromino object that is rotated by 90 degrees clockwise.
     */
    public Tetromino rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        boolean[][] newShape = new boolean[cols][rows];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                newShape[col][rows - 1 - row] = shape[row][col];
            }
        }
        return new Tetromino(symbol, newShape, cellPosition);
    }

    /**
     * Returns the cellposition of the cell.
     * 
     * @return The cellposition of the cell.
     */
    public CellPosition getCellPosition() {
        return cellPosition;
    }

    /**
     * Checks if a tetromino placement is a legal move on the grid.
     * 
     * @param grid               The grid.
     * @param tetrominoCandidate The tetromino to check.
     * @return True if the move is legal, false otherwise.
     */
    public boolean isLegalMove(Grid<Character> grid, Tetromino tetrominoCandidate) {
        int startRow = tetrominoCandidate.cellPosition.row();
        int startCol = tetrominoCandidate.cellPosition.col();

        ArrayList<CellPosition> tetrominoPositions = new ArrayList<>();
        for (int row = 0; row < tetrominoCandidate.shape.length; row++) {
            for (int col = 0; col < tetrominoCandidate.shape[0].length; col++) {
                if (tetrominoCandidate.shape[row][col]) {
                    tetrominoPositions.add(new CellPosition(row + startRow, col + startCol));
                }
            }
        }

        for (CellPosition tetrominoPosition : tetrominoPositions) {
            int gridRow = tetrominoPosition.row();
            int gridCol = tetrominoPosition.col();

            if (gridRow < 0 || gridRow >= grid.rows() || gridCol < 0 || gridCol >= grid.cols()
                    || !grid.get(tetrominoPosition).equals('-')) {

                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> cells = new ArrayList<GridCell<Character>>();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {
                    cells.add(new GridCell<Character>(new CellPosition(cellPosition.row() + i,
                            cellPosition.col() + j), symbol));
                }
            }
        }
        return cells.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Tetromino tetromino = (Tetromino) obj;
        return symbol == tetromino.symbol &&
                Arrays.deepEquals(shape, tetromino.shape) &&
                Objects.equals(cellPosition, tetromino.cellPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, Arrays.deepHashCode(shape), cellPosition);
    }

}
package no.uib.inf101.tetris.view;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;

/**
 * An interface representing a viewable Tetris model.
 */
public interface ViewableTetrisModel {

    /**
     * Returns the dimension of the Tetris grid.
     * 
     * @return The dimension of the grid.
     */
    GridDimension getDimension();

    /**
     * Returns an iterable of grid cells representing the tiles on the board.
     * 
     * @return An iterable of grid cells representing the tiles on the board.
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * Returns an iterable of grid cells representing the cells of the falling
     * piece.
     * 
     * @return An iterable of grid cells representing the cells of the falling
     *         piece.
     */
    Iterable<GridCell<Character>> getFallingPieceCells();

    /**
     * Returns the current game state.
     * 
     * @return Returns the current game state.
     */
    GameState getGameState();

}

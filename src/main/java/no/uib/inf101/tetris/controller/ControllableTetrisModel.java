package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;

/*
 * Functionality purposes; controlling piecees, moving pieces, rotating pieces, dropping pieces.
 */
public interface ControllableTetrisModel {

    /**
     * Functionality purposes; move a tetromino-piece.
     * 
     * @param deltaRow, new row-value.
     * @param deltaCol, new column-value.
     * @return true if the tetromino is being moved, false otherwise
     */
    boolean moveTetromino(int deltaRow, int deltaCol);

    /**
     * Functionality purposes; rotating a tetromino-piece.
     * 
     * @return true if the tetromino is being rotated, false otherwise
     */
    boolean rotateTetromino();

    /**
     * Functionality purposes; drop a tetromino-piece.
     * void: no return value.
     */
    void dropTetromino();

    /**
     * Functionality purposes; Get game screen.
     * 
     * @return Gamescreen object.
     */
    GameState getGameState();

    /**
     * Functionality purposes; Set a game state.
     * 
     * @param gameState
     */
    void setGameState(GameState gameState);

    /**
     * A method which locks a tetromino
     */

    void lockTetrominoAndSpawnNew();

    /**
     * A timer.
     * 
     * @return Seconds.
     */
    int getSeconds();

    /**
     * A method being called every time the clock ticks.
     */

    void clockTick();

    /**
     * A method that resets the game to the initial state.
     */

    void resetGame();

}

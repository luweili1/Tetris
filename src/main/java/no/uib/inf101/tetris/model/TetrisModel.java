package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    private TetrominoFactory randomTetromino;
    private TetrisBoard board;
    private Tetromino currentTetromino;
    private Tetromino tetromino;
    private GameState gameState;

    public TetrisModel(TetrisBoard board, TetrominoFactory randomTetromino) {
        this.board = board;
        this.randomTetromino = randomTetromino;
        tetromino = randomTetromino.getNext().shiftedToTopCenterOf(board);
        gameState = GameState.WELCOME_SCREEN;

    }

    /**
     * Rotates the current tetromino if possible and updates it.
     * The method creates a rotated tetromino candidate from the current tetromino.
     * If the tetriscandidate can be moved on the game board, then it becomes the
     * new current tetromino.
     * If the rotation is successful, the method returns true. Otherwise, it returns
     * false.
     */
    public boolean rotateTetromino() {
        Tetromino tetrisCandidate = tetromino.rotate();
        if (tetromino.isLegalMove(board, tetrisCandidate)) {
            tetromino = tetrisCandidate;
            return true;
        }
        return false;
    }

    /**
     * Glues the falling piece to the game board.
     * Afterward, it calls the "lockTetrominoAndSpawnNew" method to lock the
     * tetromino in place and spawn a new tetromino.
     */
    public void glueFallingPiece() {
        for (GridCell<Character> cell : tetromino) {
            board.set(cell.pos(), cell.value());
        }
        lockTetrominoAndSpawnNew();
    }

    /**
     * Locks the current tetromino in place on the game board and spawns a new
     * tetromino.
     * If the game state is ACTIVE_GAME: (1) the method locks each individual grid
     * cell of the tetromino on the game board.
     * (2) It then removes any full rows from the board
     */
    public void lockTetrominoAndSpawnNew() {
        if (gameState == GameState.ACTIVE_GAME) {
            for (GridCell<Character> cell : tetromino) {
                board.set(cell.pos(), cell.value());
            }
            int removedRows = board.removeFullRows();

            Tetromino newTetromino = randomTetromino.getNext().shiftedToTopCenterOf(board);
            if (isPositionPossible(newTetromino)) {
                tetromino = newTetromino;
            } else {
                setGameState(GameState.GAME_OVER);
            }
        }
    }

    /**
     * Resets the game by clearing the board, setting the gameState to
     * WELCOME_SCREEN, and starting a new game.
     */
    public void resetGame() {
        board.clear();
        gameState = GameState.WELCOME_SCREEN;
        startNewGame();
    }

    /**
     * Starts a new game by generating a new random tetromino and placing it at the
     * top center of the board.
     */
    private void startNewGame() {
        tetromino = randomTetromino.getNext().shiftedToTopCenterOf(board);
    }

    /**
     * Checks if the given tetromino can be placed on the board.
     * 
     * @param tetromino The tetromino to check.
     * @return True if the position is possible, false otherwise.
     */
    private boolean isPositionPossible(Tetromino tetromino) {
        for (GridCell<Character> cell : tetromino) {

            if (!board.positionIsOnGrid(cell.pos())) {
                currentTetromino = tetromino;
                return false;
            }
            if (board.get(cell.pos()) != '-')
                return false;
        }
        return true;

    }

    /**
     * Move the tetromino by specific amounts of row and cols.
     * 
     * @param deltaRow The amount of row to move.
     * @param deltaCol The amount of col to move.
     * @return True if the tetromino is succesfully moved, false othervise.
     */
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino newTetromino = tetromino.shiftedBy(deltaRow, deltaCol);
        if (tetromino.isLegalMove(board, newTetromino)) {
            tetromino = newTetromino;
            return true;
        }
        return false;

    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getFallingPieceCells() {
        return tetromino;
    }

    @Override
    public GridDimension getDimension() {
        return board;
    }

    @Override
    public void dropTetromino() {
        while (moveTetromino(1, 0)) {

        }
        lockTetrominoAndSpawnNew();
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public int getSeconds() {
        return 1000;
    }

    @Override
    public void clockTick() {
        if (moveTetromino(1, 0)) {
        } else {
            glueFallingPiece();
            if (gameState == GameState.WELCOME_SCREEN) {
                setGameState(GameState.ACTIVE_GAME);
            }
        }
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

}

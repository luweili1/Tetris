package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    TetrominoFactory randomTetromino;
    TetrisBoard board;
    Tetromino currentTetromino;
    Tetromino tetromino;

    private GameState gameState;
    private int score;
    private int level;

    public void setGameState(GameState state) {
        gameState = state;
    }

    public TetrisModel(TetrisBoard board, TetrominoFactory randomTetromino) {
        this.board = board;
        this.randomTetromino = randomTetromino;
        tetromino = randomTetromino.getNext().shiftedToTopCenterOf(board);
        gameState = GameState.WELCOME_SCREEN;
        score = 0;
        level = 0;

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
            if (removedRows > 0) {
                score += calculateScore(removedRows);
                if (removedRows % 10 == 0) {
                    level++;
                }
            }

            Tetromino newTetromino = randomTetromino.getNext().shiftedToTopCenterOf(board);
            if (isPositionPossible(newTetromino)) {
                tetromino = newTetromino;
            } else {
                setGameState(GameState.GAME_OVER);
            }
        }
    }

    private int calculateScore(int clearedRows) {
        int baseScore = 0;
        switch (clearedRows) {
            case 1:
                baseScore = 100;
                break;
            case 2:
                baseScore = 300;
                break;
            case 3:
                baseScore = 500;
                break;
            case 4:
                baseScore = 800;
                break;
            default:
                break;
        }
        return baseScore * level;
    }

    private void updateScoreAndLevel(int clearedRows) {
        score += calculateScore(clearedRows);
        // Increase level every 10 cleared rows
        level = Math.min(score / 1000 + 1, 10); // Adjust level-up conditions as needed
    }

    public void resetGame() {
        // Tilbakestill brettet
        board.clear();

        // Tilbakestill score
        score = 0;

        // Tilbakestill spilltilstanden til velkomstskjermen
        gameState = GameState.WELCOME_SCREEN;

        // Start et nytt spill
        startNewGame();
    }

    private void startNewGame() {
        // Opprett et nytt tetromino
        tetromino = randomTetromino.getNext().shiftedToTopCenterOf(board);
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return board;
    }

    @Override
    public Iterable<GridCell<Character>> getFallingPieceCells() {
        return tetromino;
    }

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

    @Override
    public GridDimension getDimension() {
        return board;
    }

    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino newTetromino = tetromino.shiftedBy(deltaRow, deltaCol);
        if (tetromino.isLegalMove(board, newTetromino)) {
            tetromino = newTetromino;
            return true;
        }
        return false;

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

}

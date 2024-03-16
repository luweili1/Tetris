package no.uib.inf101.tetris.model.tetromino;

public interface TetrominoFactory {
    /**
     * Functionality purposes; Retrice a new tetromino.
     * 
     * @return A tetromino object.
     */
    Tetromino getNext();

}

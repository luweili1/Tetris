package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;

/**
 * The class implement the TetrominoFactory interface that generates tetrominos
 * randomly
 */
public class RandomTetrominoFactory implements TetrominoFactory {

    @Override
    public Tetromino getNext() {
        String tetrominoTypes = "LJSZTIO";
        char randomeType = tetrominoTypes.charAt(new Random().nextInt((tetrominoTypes.length())));

        return Tetromino.newTetromino(randomeType);

    }

}

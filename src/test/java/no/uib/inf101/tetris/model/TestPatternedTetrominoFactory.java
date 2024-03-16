package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import org.junit.jupiter.api.Test;

public class TestPatternedTetrominoFactory {

    @Test
    public void sanityTestPatternedTetrominoFactory() {
        TetrominoFactory factory = new PatternedTetrominoFactory("TSZ");

        assertEquals(Tetromino.newTetromino('T'), factory.getNext());
        assertEquals(Tetromino.newTetromino('S'), factory.getNext());
        assertEquals(Tetromino.newTetromino('Z'), factory.getNext());
        assertEquals(Tetromino.newTetromino('T'), factory.getNext());
        assertEquals(Tetromino.newTetromino('S'), factory.getNext());
    }

}

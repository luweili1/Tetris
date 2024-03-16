package no.uib.inf101.tetris.model.tetromino;

/**
 * Implementation of the TetrominoFactory interface that generates tetrominos
 * based on a patterned string.
 */
public class PatternedTetrominoFactory implements TetrominoFactory {

    private String pattern;
    private int currentPosition;

    /**
     * Constructs a PatternedTetrominoFactory with the pattern string.
     * 
     * @param pattern The string used to generate tetrominos.
     */
    public PatternedTetrominoFactory(String pattern) {
        this.pattern = pattern;
        this.currentPosition = 0;
    }

    @Override
    public Tetromino getNext() {
        char symbol = pattern.charAt(currentPosition);
        currentPosition = (currentPosition + 1) % pattern.length();

        return Tetromino.newTetromino(symbol);
    }

}

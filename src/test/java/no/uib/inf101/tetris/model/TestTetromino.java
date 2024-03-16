package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

public class TestTetromino {
    @Test
    public void testHashCodeAndEquals() {
        Tetromino t1 = Tetromino.newTetromino('T');
        Tetromino t2 = Tetromino.newTetromino('T');
        Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
        Tetromino s1 = Tetromino.newTetromino('S');
        Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

        assertEquals(t1, t2);
        assertEquals(s1, s2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(t1, t3);
        assertNotEquals(t1, s1);
    }

    @Test
    public void tetrominoIterationOfT() {
        Tetromino tetro = Tetromino.newTetromino('T');
        tetro = tetro.shiftedBy(10, 100);

        List<GridCell<Character>> objs = new ArrayList<>();
        for (GridCell<Character> gc : tetro) {
            objs.add(gc);
        }

        assertEquals(4, objs.size());
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
        assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
    }

    @Test
    public void doubleMoveDistance() {
        // Create a standard 'L' tetromino to test
        Tetromino tetro = Tetromino.newTetromino('L');

        // Move the tetromino once by (1, 2)
        Tetromino movedOnce = tetro.shiftedBy(1, 2);

        // Move the tetromino again by (1, 2)
        Tetromino movedTwice = movedOnce.shiftedBy(1, 2);

        // Calculate the distances
        int distanceOnce = movedOnce.cellPosition.row() + movedOnce.cellPosition.col();
        int distanceTwice = movedTwice.cellPosition.row() + movedTwice.cellPosition.col();

        // Check that the total distance is twice as long
        assertEquals(distanceOnce * 2, distanceTwice);
    }

}
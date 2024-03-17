package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.tetromino.PatternedTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;

import org.junit.jupiter.api.Test;

public class TestTetrisModel {

    @Test
    public void initialPositionOfO() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        ViewableTetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingPieceCells()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
    }

    @Test
    public void initialPositionOfI() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        ViewableTetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingPieceCells()) {
            tetroCells.add(gc);
        }

        assertEquals(4, tetroCells.size());
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
        assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
    }

    @Test
    public void testMoveTetromino() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("O");
        TetrisModel model = new TetrisModel(board, factory);

        assertTrue(model.moveTetromino(0, 1));
    }

    @Test
    public void testRotationTetromino() {
        Tetromino tetromino = Tetromino.newTetromino('S');
        Tetromino rotated = tetromino.rotate();

        boolean[][] expectedShape = {
                { true, false, false },
                { true, true, false },
                { false, true, false }

        };
        assertArrayEquals(expectedShape, rotated.getShape());

    }

    @Test
    public void testClocktick() {
        TetrisBoard board = new TetrisBoard(20, 10);
        TetrominoFactory factory = new PatternedTetrominoFactory("I");
        TetrisModel model = new TetrisModel(board, factory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingPieceCells()) {
            tetroCells.add(gc);

        }
        model.clockTick();

        List<GridCell<Character>> tetroCellClockTick = new ArrayList<>();
        for (GridCell<Character> gc : model.getFallingPieceCells()) {
            tetroCellClockTick.add(gc);
        }

        assertNotEquals(tetroCells, tetroCellClockTick);
        assertEquals(tetroCellClockTick.size(), 4);

        assertTrue(tetroCellClockTick.contains(new GridCell<>(new CellPosition(1, 3), 'I')));
        assertTrue(tetroCellClockTick.contains(new GridCell<>(new CellPosition(1, 4), 'I')));
        assertTrue(tetroCellClockTick.contains(new GridCell<>(new CellPosition(1, 5), 'I')));
        assertTrue(tetroCellClockTick.contains(new GridCell<>(new CellPosition(1, 6), 'I')));

    }

    @Test
    public void testDropTetromino() {
        TetrisBoard tetrisBoard = new TetrisBoard(20, 10);
        TetrominoFactory tetrisFactory = new PatternedTetrominoFactory("O");
        TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrisFactory);

        List<GridCell<Character>> tetroCells = new ArrayList<>();
        for (GridCell<Character> gc : tetrisModel.getFallingPieceCells()) {
            tetroCells.add(gc);
        }

        tetrisModel.dropTetromino();

        List<GridCell<Character>> tetroCellsAfterDrop = new ArrayList<>();
        for (GridCell<Character> gc : tetrisModel.getFallingPieceCells()) {
            tetroCellsAfterDrop.add(gc);
        }

        assertEquals(tetrisBoard.get(new CellPosition(19, 4)), '-');
        assertEquals(tetrisBoard.get(new CellPosition(19, 5)), '-');
        assertEquals(tetrisBoard.get(new CellPosition(18, 4)), '-');
        assertEquals(tetrisBoard.get(new CellPosition(18, 5)), '-');

        assertEquals(tetroCellsAfterDrop.size(), 4);
    }

    @Test
    public void testSuccessfulMove() {
        TetrisBoard board = new TetrisBoard(3, 6);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel model = new TetrisModel(board, tetrominoFactory);
        assertTrue(model.moveTetromino(0, 1));
    }

    @Test
    public void testTetrominoChangeAfterSuccessfulMove() {
        TetrisBoard board = new TetrisBoard(3, 6);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel tetrisModel = new TetrisModel(board, tetrominoFactory);

        List<GridCell<Character>> initialPositions = new ArrayList<>();
        for (GridCell<Character> position : tetrisModel.getFallingPieceCells()) {
            initialPositions.add(position);
        }

        tetrisModel.moveTetromino(0, -1);

        List<GridCell<Character>> newPositions = new ArrayList<>();
        for (GridCell<Character> position : tetrisModel.getFallingPieceCells()) {
            newPositions.add(position);
        }

        assertNotEquals(initialPositions, newPositions);
    }

    @Test
    public void testOutOfBoundsMove() {
        TetrisBoard board = new TetrisBoard(4, 4);
        TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
        TetrisModel model = new TetrisModel(board, tetrominoFactory);
        assertFalse(model.moveTetromino(0, -1));
    }

}

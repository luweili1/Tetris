package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";

  public static void main(String[] args) {
    int rows = 20;
    int cols = 10;
    TetrisBoard tetrisBoard = new TetrisBoard(rows, cols);

    // Fyll ut hjørnene på brettet med eksempelverdier
    tetrisBoard.set(new CellPosition(0, 0), '-');
    tetrisBoard.set(new CellPosition(0, cols - 1), '-');
    tetrisBoard.set(new CellPosition(rows - 1, 0), '-');
    tetrisBoard.set(new CellPosition(rows - 1, cols - 1), '-');

    TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
    TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

    TetrisView view = new TetrisView(tetrisModel);
    // Oppretter tetrisVeiw som objekt og bruker tetrismodel som argument ().

    TetrisController controller = new TetrisController(tetrisModel, view);

    JFrame frame = new JFrame("Tetris");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }

}

package no.uib.inf101.tetris;

import javax.swing.JFrame;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.TetrisView;

/**
 * The main class for the Tetris game.
 */
public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";

  /**
   * The entry point of the program.
   * 
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    int rows = 20;
    int cols = 10;
    TetrisBoard tetrisBoard = new TetrisBoard(rows, cols);

    tetrisBoard.set(new CellPosition(0, 0), '-');
    tetrisBoard.set(new CellPosition(0, cols - 1), '-');
    tetrisBoard.set(new CellPosition(rows - 1, 0), '-');
    tetrisBoard.set(new CellPosition(rows - 1, cols - 1), '-');

    TetrominoFactory tetrominoFactory = new RandomTetrominoFactory();
    TetrisModel tetrisModel = new TetrisModel(tetrisBoard, tetrominoFactory);

    TetrisView view = new TetrisView(tetrisModel);

    TetrisController controller = new TetrisController(tetrisModel, view);

    JFrame frame = new JFrame("Tetris");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);
  }

}

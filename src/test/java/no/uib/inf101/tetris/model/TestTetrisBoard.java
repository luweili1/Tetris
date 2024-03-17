package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;

public class TestTetrisBoard {
  @Test
  public void prettyStringTest() {
    TetrisBoard board = new TetrisBoard(3, 4);
    board.set(new CellPosition(0, 0), 'g');
    board.set(new CellPosition(0, 3), 'y');
    board.set(new CellPosition(2, 0), 'r');
    board.set(new CellPosition(2, 3), 'b');

    String expected = String.join("\n", new String[] {
        "g--y",
        "----",
        "r--b"
    });

    assertEquals(expected, board.prettyString());
  }

  @Test
  public void testRemoveFullRows() {

    TetrisBoard board = new TetrisBoard(5, 2);
    board.set(new CellPosition(0, 1), 'T');
    board.set(new CellPosition(1, 0), 'T');
    board.set(new CellPosition(1, 1), 'T');
    board.set(new CellPosition(2, 1), 'T');
    board.set(new CellPosition(4, 0), 'L');
    board.set(new CellPosition(4, 1), 'L');
    board.set(new CellPosition(3, 0), 'L');
    board.set(new CellPosition(2, 0), 'L');

    assertEquals(3, board.removeFullRows());

    String expected = String.join("\n", new String[] {
        "--",
        "--",
        "--",
        "-T",
        "L-"
    });
    assertEquals(expected, board.prettyString());
  }

  @Test
  public void testKeepBottomRow() {
    TetrisBoard board = getTetrisBoardWithContents(new String[] { "-T", "-T", "LT", "LT", "L-" });
    assertEquals(2, board.removeFullRows());
    String expected = String.join("\n", new String[] { "--", "--", "-T", "-T", "L-" });
    assertEquals(expected, board.prettyString());
  }

  @Test
  public void testRemoveTopRow() {
    TetrisBoard board = getTetrisBoardWithContents(new String[] { "TT", "-T", "-T", "L-", "-L" });
    assertEquals(1, board.removeFullRows());
    String expected = String.join("\n", new String[] { "--", "-T", "-T", "L-", "-L" });
    assertEquals(expected, board.prettyString());
  }

  @Test
  public void testDifferentBoardWidth() {
    TetrisBoard board = getTetrisBoardWithContents(new String[] { "-T-", "TTT", "LLL", "-L-", "L-L" });
    assertEquals(2, board.removeFullRows());
    String expected = String.join("\n", new String[] { "---", "---", "-T-", "-L-", "L-L" });
    assertEquals(expected, board.prettyString());
  }

  @Test
  public void testRemoveFullRowsButKeepBottom() {

    TetrisBoard board = new TetrisBoard(5, 2);
    board.set(new CellPosition(0, 1), 'T');
    board.set(new CellPosition(1, 0), 'T');
    board.set(new CellPosition(1, 1), 'T');
    board.set(new CellPosition(2, 1), 'T');
    board.set(new CellPosition(4, 0), 'L');
    board.set(new CellPosition(4, 1), 'L');
    board.set(new CellPosition(3, 0), 'L');
    board.set(new CellPosition(2, 0), 'L');

    assertEquals(3, board.removeFullRowsKeepBottomRow());

    String expected = String.join("\n", new String[] {
        "-T",
        "--",
        "--",
        "-T",
        "L-"
    });
    assertEquals(expected, board.prettyString());
  }

  private TetrisBoard getTetrisBoardWithContents(String[] contents) {
    int height = contents.length;
    int width = contents[0].length();
    TetrisBoard board = new TetrisBoard(height, width);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        char cellContent = contents[i].charAt(j);
        if (cellContent != '-') {
          board.set(new CellPosition(i, j), cellContent);
        }
      }
    }
    return board;
  }

}

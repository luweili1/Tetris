package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.model.TetrisModel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * A class representing the view of the Tetris game.
 */
public class TetrisView extends JPanel {
  private ViewableTetrisModel tetrisModel;
  private ColorTheme colorTheme;
  private static final double MARGIN = 7;
  private GameState gameState;

  /**
   * Sets the game state of the view.
   * 
   * @param gameState The game state.
   */
  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  /**
   * Constructs a TetrisView with the specified TetrisModel.
   * 
   * @param tetrisModel The TetrisModel connect with the view.
   */
  public TetrisView(TetrisModel tetrisModel) {
    this.tetrisModel = tetrisModel;
    this.colorTheme = new DefaultColorTheme();

    this.setBackground(colorTheme.getBackgroundColor());
    this.setPreferredSize(new Dimension(300, 600));
  }

  /**
   * Draws the game on the Graphics2D context.
   * 
   * @param g2 The Graphics2D context.
   */
  private void drawGame(Graphics2D g2) {
    Rectangle2D gridbox = new Rectangle.Double(0, 0, getWidth(), getHeight());
    g2.setColor(colorTheme.getBackgroundColor());
    g2.fill(gridbox);

    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(gridbox, tetrisModel.getDimension(),
        MARGIN);

    drawCells(g2, tetrisModel.getTilesOnBoard(), converter, colorTheme);

    drawCells(g2, tetrisModel.getFallingPieceCells(), converter, colorTheme);

  }

  /**
   * Draws the cells of the game grid on the Graphics2D context.
   * 
   * @param g2         The Graphics2D
   * @param grid       The grid.
   * @param converter  A CellPositionToPixelConverter object to convert cell
   *                   positions to pixel positions.
   * @param colorTheme A colortheme to detetermine the color of the cells.
   */
  private void drawCells(Graphics2D g2, Iterable<GridCell<Character>> grid, CellPositionToPixelConverter converter,
      ColorTheme colorTheme) {
    for (GridCell<Character> cell : grid) {
      Rectangle2D celle = converter.getBoundsForCell(cell.pos());
      g2.setColor(colorTheme.getCellColor(cell.value()));
      g2.fill(celle);

    }

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);

    if (tetrisModel.getGameState() == GameState.WELCOME_SCREEN) {
      Color overlay = colorTheme.getGameOverColor();
      Color text = colorTheme.getGameOverTextColor();
      g2.setColor(overlay);
      g2.fillRect(0, 0, this.getWidth(), this.getHeight());
      g2.setColor(text);
      g2.setFont(new Font("Italic", Font.BOLD, 28));

      String welcomeMessage = "Welcome to tetris";
      int xWelcome = (this.getWidth() - g2.getFontMetrics().stringWidth(welcomeMessage)) / 2;
      int yWelcome = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2;
      g2.drawString(welcomeMessage, xWelcome, yWelcome);

      String startMessage = "Press enter to start";

      g2.setFont(new Font("Italic", Font.BOLD, 20));
      int xStart = (this.getWidth() - g2.getFontMetrics().stringWidth(startMessage)) / 2;
      int yStart = yWelcome + g2.getFontMetrics().getHeight() + 10;
      g2.drawString(startMessage, xStart, yStart);

    } else if (tetrisModel.getGameState() == GameState.GAME_OVER) {
      Color overlay = colorTheme.getGameOverColor();
      Color text = colorTheme.getGameOverTextColor();
      g2.setColor(overlay);
      g2.fillRect(0, 0, this.getWidth(), this.getHeight());
      g2.setColor(text);
      g2.setFont(new Font("Italic", Font.BOLD, 48));
      int x = (this.getWidth() - g2.getFontMetrics().stringWidth("Game Over")) / 2;
      int y = (this.getHeight() - g2.getFontMetrics().getHeight()) / 2;
      g2.drawString("Game Over", x, y);

      String restartText = "Press Enter to play again";
      g2.setFont(new Font("Italic", Font.BOLD, 18));
      int xRestart = (this.getWidth() - g2.getFontMetrics().stringWidth(restartText)) / 2;
      int yRestart = y + g2.getFontMetrics().getHeight() + 10; // Adjusted this line
      g2.drawString(restartText, xRestart, yRestart);
    }
  }

}

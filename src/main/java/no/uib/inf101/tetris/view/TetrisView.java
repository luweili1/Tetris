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

public class TetrisView extends JPanel {
  private ViewableTetrisModel tetrisModel;
  private ColorTheme colorTheme;
  private static final double MARGIN = 1;
  private GameState gameState;

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public TetrisView(TetrisModel tetrisModel) {
    this.tetrisModel = tetrisModel;
    this.colorTheme = new DefaultColorTheme();

    this.setBackground(colorTheme.getBackgroundColor());
    this.setPreferredSize(new Dimension(300, 600));

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

      g2.setFont(new Font("Italic", Font.BOLD, 24));
      int score = tetrisModel.getScore();
      String scoreText = "Score: " + score;
      int xScore = (this.getWidth() - g2.getFontMetrics().stringWidth(scoreText)) / 2;
      int yScore = y + g2.getFontMetrics().getHeight() + 20;
      g2.drawString(scoreText, xScore, yScore);

      String restartText = "Press Enter to play again";
      g2.setFont(new Font("Italic", Font.BOLD, 18));
      int xRestart = (this.getWidth() - g2.getFontMetrics().stringWidth(restartText)) / 2;
      int yRestart = yScore + g2.getFontMetrics().getHeight() + 10;
      g2.drawString(restartText, xRestart, yRestart);
    }

  }

  private void drawGame(Graphics2D g2) {
    Rectangle2D gridbox = new Rectangle.Double(0, 0, getWidth(), getHeight());
    g2.setColor(colorTheme.getBackgroundColor());
    g2.fill(gridbox);

    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(gridbox, tetrisModel.getDimension(),
        MARGIN);

    drawCells(g2, tetrisModel.getTilesOnBoard(), converter, colorTheme);

    drawCells(g2, tetrisModel.getFallingPieceCells(), converter, colorTheme);

  }

  private void drawCells(Graphics2D g2, Iterable<GridCell<Character>> grid, CellPositionToPixelConverter converter,
      ColorTheme colorTheme) {
    for (GridCell<Character> cell : grid) {
      Rectangle2D celle = converter.getBoundsForCell(cell.pos());
      g2.setColor(colorTheme.getCellColor(cell.value()));
      g2.fill(celle);

    }

  }

}

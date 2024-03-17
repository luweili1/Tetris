package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

/**
 * A class that converts cell positions to pixel positions in a grid
 */
public class CellPositionToPixelConverter {

  private Rectangle2D box;
  private GridDimension gd;
  private double margin;
  private double cellWidth;
  private double cellHeight;

  /**
   * 
   * @param gridBox       The box of the grid.
   * @param gridDimension The dimension of the grid.
   * @param margin        the margin between cells.
   */
  public CellPositionToPixelConverter(Rectangle2D gridBox, GridDimension gridDimension, double margin) {
    this.box = gridBox;
    this.margin = margin;
    this.gd = gridDimension;

    cellWidth = (box.getWidth() - margin * (gridDimension.cols() + 1)) /
        gridDimension.cols();
    cellHeight = (box.getHeight() - margin * (gridDimension.rows() + 1)) /
        gridDimension.rows();
  }

  /**
   * Returns the bounds of the cell at the specified position.
   * 
   * @param cellPosition The position of the cell.
   * @return The bounds of the cell as a Rectangle2D.
   */
  public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
    double x = box.getY() + margin + (cellPosition.col() * (cellWidth + margin));
    double y = box.getY() + margin + (cellPosition.row() * (cellHeight + margin));

    return new Rectangle2D.Double(x, y, cellWidth, cellHeight);
  }

  /**
   * Converts a row index to the corresponding Y coordinate in pixels.
   * 
   * @param row The row index.
   * @return The Y coordinate in pixels.
   */
  public int rowToY(int row) {
    return (int) (box.getY() + margin + (row * (cellHeight + margin)));
  }

  /**
   * Converts a col index to the corresponding x coordinate in pixels.
   * 
   * @param col The col index.
   * @return The X coordinate in pixels.
   */
  public int columnToX(int col) {
    return (int) (box.getY() + margin + (col * (cellWidth + margin)));
  }

  /**
   * Returns the width of a cell in pixels.
   * 
   * @return Width of a cell.
   */
  public int cellWidth() {
    return (int) cellWidth;
  }

  /**
   * Returns the height of a cell in pixels.
   * 
   * @return Height of a cell.
   */
  public int cellHeight() {
    return (int) cellHeight;
  }
}

package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;

public class CellPositionToPixelConverter {

  // Instansvariabel Rectangle2D og box som beskriver hvilket område rutenette
  // skal tegnes.
  private Rectangle2D box;
  private GridDimension gd;
  private double margin;
  private double cellWidth;
  private double cellHeight;

  /**
   * 
   * @param gridBox
   * @param gridDimension
   * @param margin
   */

  // Konstruktør med tre parameter.
  public CellPositionToPixelConverter(Rectangle2D gridBox, GridDimension gridDimension, double margin) {
    this.box = gridBox;
    this.margin = margin;
    this.gd = gridDimension;

    cellWidth = (box.getWidth() - margin * (gridDimension.cols() + 1)) /
        gridDimension.cols();
    cellHeight = (box.getHeight() - margin * (gridDimension.rows() + 1)) /
        gridDimension.rows();

  }

  // En metode getBoundsForCell, returtype: Rectangle2D, parameter: CellPosition
  public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
    double x = box.getY() + margin + (cellPosition.col() * (cellWidth + margin));
    double y = box.getY() + margin + (cellPosition.row() * (cellHeight + margin));

    return new Rectangle2D.Double(x, y, cellWidth, cellHeight);

  }

  public int rowToY(int row) {
    // Implementer denne metoden for å konvertere radnummer til piksel Y-koordinat
    return (int) (box.getY() + margin + (row * (cellHeight + margin)));
  }

  public int columnToX(int col) {
    return (int) (box.getY() + margin + (col * (cellWidth + margin)));
  }

  public int cellWidth() {
    return (int) cellWidth;
  }

  public int cellHeight() {
    return (int) cellHeight;
  }
}

package no.uib.inf101.grid;

public interface GridDimension {

  /** Number of rows in the grid */
  int rows();

  /** Number of columns in the grid */
  int cols();

  /**
   * Retrieves the value at the specified row and column in the grid.
   * 
   * @param i, row
   * @param j, column
   * @return
   */
  Integer get(int i, int j);
}

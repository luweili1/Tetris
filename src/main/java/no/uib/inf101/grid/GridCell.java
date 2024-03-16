package no.uib.inf101.grid;

/**
 * The class GridCell represents a cell in a grid with a position and a value.
 */
public record GridCell<E>(CellPosition pos, E value) {

}

package no.uib.inf101.tetris.view;

import java.awt.Color;

public interface ColorTheme {

    /**
     * Functionality purposes; Returns the color associated with the specified cell
     * symbol.
     * 
     * @param symbol
     * @return The color corresponing to the symbol.
     */
    Color getCellColor(char symbol);

    /**
     * Functionality purposes; Returns the color of the frame.
     * 
     * @return
     */
    Color getFrameColor();

    /**
     * Functionality purposes; Returns the color of the background color.
     * 
     * @return
     */
    Color getBackgroundColor();

    /**
     * Functionality purposes; Returns the color the Gameover color.
     * 
     * @return
     */

    Color getGameOverColor();

    /**
     * Functionality purposes; Returns the color of the Gameover text.
     * 
     * @return
     */

    Color getGameOverTextColor();
}

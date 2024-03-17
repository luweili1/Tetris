package no.uib.inf101.tetris.view;

import java.awt.Color;

import no.uib.inf101.tetris.model.GameState;

/**
 * A default implementation of the ColorTheme interface.
 */
public class DefaultColorTheme implements ColorTheme {
    /**
     * Sets the color theme based on the current game state, transparent.
     */
    public static final Color TRANSPARENT = new Color(0, 0, 0, 128);

    /**
     * The current game state.
     * 
     * @param gameState The current game state.
     */
    public void setGameState(GameState gameState) {
    }

    @Override
    public Color getCellColor(char symbol) {
        Color color = switch (symbol) {
            case 'L' -> new Color(155, 95, 224);
            case 'O' -> new Color(22, 164, 216);
            case 'J' -> new Color(96, 219, 232);
            case 'S' -> new Color(139, 211, 70);
            case 'I' -> new Color(239, 223, 72);
            case 'Z' -> new Color(249, 165, 44);
            case 'T' -> new Color(214, 78, 18);

            case '-' -> Color.DARK_GRAY;
            default -> throw new IllegalArgumentException(
                    "No available color for '" + symbol + "'");
        };

        return color;
    }

    @Override
    public Color getFrameColor() {
        return Color.GRAY;
    }

    @Override
    public Color getBackgroundColor() {
        return Color.BLACK;
    }

    @Override
    public Color getGameOverColor() {
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color getGameOverTextColor() {
        return Color.WHITE;
    }

}

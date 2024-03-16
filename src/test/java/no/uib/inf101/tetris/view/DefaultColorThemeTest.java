package no.uib.inf101.tetris.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class DefaultColorThemeTest {
  @Test
  public void sanityDefaultColorThemeTest() {
    ColorTheme colors = new DefaultColorTheme();
    assertEquals(Color.BLACK, colors.getBackgroundColor());
    assertEquals(Color.GRAY, colors.getFrameColor());
    assertEquals(new Color(155, 95, 224), colors.getCellColor('L'));
    assertEquals(new Color(22, 164, 216), colors.getCellColor('O'));
    assertEquals(new Color(96, 219, 232), colors.getCellColor('J'));
    assertEquals(new Color(139, 211, 70), colors.getCellColor('S'));
    assertEquals(new Color(239, 223, 72), colors.getCellColor('I'));
    assertEquals(new Color(249, 165, 44), colors.getCellColor('Z'));
    assertEquals(new Color(214, 78, 18), colors.getCellColor('T'));

    assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
  }
}

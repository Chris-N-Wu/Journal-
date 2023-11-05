package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * test class for the theme enumeration class
 */
class ThemeTest {
  final Theme green = Theme.GREEN;
  final Theme pink = Theme.PINK;
  final Theme clear = Theme.RESET;

  /**
   * test method for changing between themes in a schedule
   */
  @Test
  public void testThemeSet() {
    WeekData weekData = new WeekData();
    weekData.setCurrentWeekData(Path.of("src/test/testFile.bujo"));
    String themeGreen = weekData.getCurrentBujoWeekData().weekData().theme();
    assertEquals(green.name(), themeGreen.toUpperCase());

    weekData.getCurrentBujoWeekData().weekData().setTheme("pink");
    String themePink = weekData.getCurrentBujoWeekData().weekData().theme();
    assertEquals(pink.name(), themePink.toUpperCase());

    weekData.getCurrentBujoWeekData().weekData().setTheme("reset");
    String themeClear = weekData.getCurrentBujoWeekData().weekData().theme();
    assertEquals(clear.name(), themeClear.toUpperCase());
  }
}
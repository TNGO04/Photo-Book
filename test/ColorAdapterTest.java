import static org.junit.Assert.assertEquals;

import album.model.shapes.component.Color;
import album.util.ColorAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Class for ColorAdapter.
 */
public class ColorAdapterTest {
  private Color color1;
  private Color color2;

  /**
   * Set up test cases.
   */
  @Before
  public void setUp() {
    color1 = new Color(1, 0, 0.5);
    color2 = new Color(1, 1, 1);
  }

  /**
   * Test toSwingFromModel() method.
   */
  @Test
  public void testToSwingFromModel() {
    assertEquals(ColorAdapter.toSwingFromModel(color1).getRed(), 255);
    assertEquals(ColorAdapter.toSwingFromModel(color1).getBlue(), 0);
    assertEquals(ColorAdapter.toSwingFromModel(color1).getGreen(), 127);

    assertEquals(ColorAdapter.toSwingFromModel(color2).getRed(), 255);
    assertEquals(ColorAdapter.toSwingFromModel(color2).getBlue(), 255);
    assertEquals(ColorAdapter.toSwingFromModel(color2).getGreen(), 255);
  }

  /**
   * Test toMarkupFromModel method.
   */
  @Test
  public void testToMarkupFromModel() {
    assertEquals(ColorAdapter.toMarkupFromModel(color1), "rgb(255,127,0)");
    assertEquals(ColorAdapter.toMarkupFromModel(color2), "rgb(255,255,255)");
  }
}
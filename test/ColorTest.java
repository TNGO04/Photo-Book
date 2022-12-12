import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import album.model.shapes.component.Color;

/**
 * JUnit test class for Color.
 */
public class ColorTest {

  private Color color1;
  private Color color2;
  private double delta = 0.0001;

  /**
   * Set up test cases.
   */
  @Before
  public void setUp() {
    color1 = new Color(1,0.5,0);
  }

  /**
   * Test bad arguments.
   */
  @Test
  public void testBadArguments() {
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Color( -1,0,0);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Color( 0,-1,0);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Color( 0,0,-1);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Color( 1.2,0,0);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Color( 0,2,0);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Color( 0,0,1.5);
      }
    });
  }

  /**
   * Test getRed().
   */
  @Test
  public void testGetRed() {
    assertEquals(color1.getRed(),1, delta);
  }

  /**
   * Test getGreen().
   */
  @Test
  public void testGetGreen() {
    assertEquals(color1.getGreen(),0.0, delta);
  }

  /**
   * Test getBlue().
   */
  @Test
  public void testGetBlue() {
    assertEquals(color1.getBlue(),0.5, delta);
  }

  /**
   * Test setRed.
   */
  @Test
  public void testSetRed() {
    color1.setRed(0.5);
    assertEquals(color1.getRed(),0.5, delta);
  }

  /**
   * Test setGreen.
   */
  @Test
  public void testSetGreen() {
    color1.setGreen(0.7);
    assertEquals(color1.getGreen(),0.7, delta);
  }

  /**
   * Test setBlue.
   */
  @Test
  public void testSetBlue() {
    color1.setBlue(1);
    assertEquals(color1.getBlue(),1, delta);
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString() {
    assertEquals(color1.toString(),"(1.0, 0.5, 0.0)");
  }

  /**
   * Test equals.
   */
  @Test
  public void testEquals() {
    assertTrue(color1.equals(color1));
    assertFalse(color1.equals(color2));

    Color color3 = new Color(1,0.5,0);
    assertTrue(color1.equals(color3));
  }

  /**
   * Test copy.
   */
  @Test
  public void testCopy() {
    Color color4 = color1.copy();
    assertTrue(color1.equals(color4));
    color1.setRed(0);
    assertFalse(color1.equals(color4));
  }
}
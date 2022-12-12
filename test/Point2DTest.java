import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import album.model.shapes.component.Point2D;


/**
 * JUnit test class for Point2D.
 */
public class Point2DTest {
  private Point2D p1;
  private Point2D p2;

  /**
   * Set up test cases.
   */
  @Before
  public void setUp() {
    p1 = new Point2D(100, 50);
    p2 = new Point2D(0.2, 1000);
  }

  /**
   * Test getX().
   */
  @Test
  public void testGetX() {
    assertEquals(p1.getX(),100, 0.001);
    assertEquals(p2.getX(),0.2, 0.001);

  }

  /**
   * Test getY().
   */
  @Test
  public void testGetY() {
    assertEquals(p1.getY(),50, 0.001);
    assertEquals(p2.getY(),1000, 0.001);
  }

  /**
   * Test setX().
   */
  @Test
  public void testSetX() {
    p1.setX(302.33);
    assertEquals(p1.getX(),302.33, 0.001);
  }

  /**
   * Test setY().
   */
  @Test
  public void testSetY() {
    p1.setY(302.33);
    assertEquals(p1.getY(),302.33, 0.001);
  }

  /**
   * Test toString().
   */
  @Test
  public void testToString() {
    assertEquals(p1.toString(), "(100.0, 50.0)");
    assertEquals(p2.toString(), "(0.2, 1000.0)");
  }

  /**
   * Test equals().
   */
  @Test
  public void testEquals() {
    assertTrue(p1.equals(p1));
    assertFalse(p1.equals(p2));

    Point2D p3 = new Point2D(100, 50);
    assertTrue(p1.equals(p3));
  }

  /**
   * Test copy().
   */
  @Test
  public void testCopy() {
    Point2D p4 = p1.copy();

    assertTrue(p1.equals(p4));

    p1.setX(20);
    assertFalse(p1.equals(p4));
  }
}
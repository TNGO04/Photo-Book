import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import album.model.shapes.IShape;
import album.model.shapes.Oval;
import album.model.shapes.Rectangle;
import album.model.shapes.Triangle;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Test class for IShape.
 */
public class IShapeTest {
  private IShape shape1;
  private IShape shape2;

  /**
   * Set up test cases.
   */
  @Before
  public void setUp() {
    shape1 = new Oval(new Color(0.1, 0, 0.4), new Point2D(100,50), 10, 5);
    shape2 = new Rectangle(new Color(1,1,1), new Point2D(20,30), 20,40);
  }

  /**
   * Test bad arguments.
   */
  @Test
  public void testBadConstruction() {
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Oval(null, new Point2D(100,50), 10, 5);
        new Rectangle(null, new Point2D(100,50), 10, 5);
        new Triangle(null, new Point2D(100,50), 10, 5);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Oval(new Color(0, 0, 0), null, 10, 5);
        new Rectangle(new Color(0, 0, 0), null, 10, 5);
        new Triangle(new Color(0, 0, 0), null, 10, 5);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Oval(new Color(0, 0, 0), new Point2D(100,50), -10, 5);
        new Rectangle(new Color(0, 0, 0), new Point2D(100,50), -10, 5);
        new Triangle(new Color(0, 0, 0), new Point2D(100,50), -10, 5);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        new Oval(new Color(0, 0, 0), new Point2D(100,50), 10, -5);
        new Rectangle(new Color(0, 0, 0), new Point2D(100,50), 10, -5);
        new Triangle(new Color(0, 0, 0), new Point2D(100,50), 10, -5);
      }
    });
  }

  /**
   * Test getCoordinate.
   */
  @Test
  public void getCoordinate() {
    assertTrue(shape1.getCoordinate().equals(new Point2D(100,50)));
    assertFalse(shape1.getCoordinate().equals(new Point2D(50,100)));
    assertTrue(shape2.getCoordinate().equals(new Point2D(20,30)));
  }

  /**
   * Test getColor.
   */
  @Test
  public void getColor() {
    assertTrue(shape1.getColor().toString().equals("(0.1, 0.0, 0.4)"));
    assertTrue(shape2.getColor().toString().equals("(1.0, 1.0, 1.0)"));
  }

  /**
   * Test move.
   */
  @Test
  public void move() {
    assertTrue(shape1.getCoordinate().equals(new Point2D(100,50)));
    shape1.move(new Point2D(50,100));
    assertTrue(shape1.getCoordinate().equals(new Point2D(50,100)));
  }

  /**
   * Test changeColor.
   */
  @Test
  public void changeColor() {
    assertTrue(shape1.getColor().toString().equals("(0.1, 0.0, 0.4)"));
    shape1.changeColor(new Color(0, 0, 0));
    assertTrue(shape1.getColor().toString().equals("(0.0, 0.0, 0.0)"));
  }

  /**
   * Test getXDimension.
   */
  @Test
  public void getXDimension() {
    assertEquals(shape1.getXDimension(),10, 0.0001);
    assertEquals(shape2.getXDimension(),20, 0.0001);
  }

  /**
   * Test getYDimension.
   */
  @Test
  public void getYDimension() {
    assertEquals(shape1.getYDimension(),5, 0.0001);
    assertEquals(shape2.getYDimension(),40, 0.0001);
  }

  /**
   * Test changeXDimension.
   */
  @Test
  public void changeXDimension() {
    assertEquals(shape1.getXDimension(),10, 0.0001);
    shape1.changeXDimension(20);
    assertEquals(shape1.getXDimension(),20, 0.0001);
  }

  /**
   * Test changeYDimension.
   */
  @Test
  public void changeYDimension() {
    assertEquals(shape1.getYDimension(),5, 0.0001);
    shape1.changeYDimension(40);
    assertEquals(shape1.getYDimension(),40, 0.0001);
  }

  /**
   * Test scaleShape.
   */
  @Test
  public void scaleShape() {
    shape1.scaleShape(2);
    assertEquals(shape1.getXDimension(),20, 0.0001);
    assertEquals(shape1.getYDimension(),10, 0.0001);

    shape1.scaleShape(0.25);
    assertEquals(shape1.getXDimension(),5, 0.0001);
    assertEquals(shape1.getYDimension(),2.5, 0.0001);
  }

  /**
   * Test bad arguments.
   */
  @Test
  public void testBadArguments() {
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        shape1.changeXDimension(-10);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        shape1.changeYDimension(-10);
      }
    });
    assertThrows(IllegalArgumentException.class, new Executable() {
      @Override
      public void execute() throws Throwable {
        shape1.scaleShape(-5);
      }
    });
  }


  /**
   * Test toString().
   */
  @Test
  public void testToString() {
    assertEquals(shape1.toString(),
            "Type: oval\n" +
                    "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, " +
                    "Color: (0.1, 0.0, 0.4)");

    assertEquals(shape2.toString(),
            "Type: rectangle\n" +
                    "Min corner: (20.0, 30.0), Width: 20.0, Height: 40.0, " +
                    "Color: (1.0, 1.0, 1.0)");
  }

  /**
   * Test equals().
   */
  @Test
  public void testEquals() {
    assertTrue(shape1.equals(shape1));
    assertFalse(shape2.equals(shape1));

    IShape shape3 = new Oval(new Color(0.1, 0, 0.4), new Point2D(100,50), 10, 5);
    assertTrue(shape1.equals(shape3));
  }

  /**
   * Test getCopy().
   */
  @Test
  public void getCopy() {
    IShape shape3 = shape1.getCopy();

    shape1.changeXDimension(300);
    assertFalse(shape1.equals(shape3));
  }
}
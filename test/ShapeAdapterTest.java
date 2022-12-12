import static org.junit.Assert.assertEquals;

import album.model.shapes.IShape;
import album.model.shapes.Oval;
import album.model.shapes.Rectangle;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;
import album.util.ShapeAdapter;
import org.junit.Before;
import org.junit.Test;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Test class for ShapeAdapter.
 */
public class ShapeAdapterTest {
  private IShape shape1;
  private IShape shape2;

  /**
   * Set up test cases.
   */
  @Before
  public void setUp() {
    shape1 = new Rectangle(new Color(1,1,1), new Point2D(100,200),20,30.5);
    shape2 = new Oval(new Color(1,1,1), new Point2D(0,0),20,30.5);
  }

  /**
   * Test static method IShapeToShape.
   */
  @Test
  public void testIShapeToShape() {
    Shape newShape1 = ShapeAdapter.IShapeToSwingShape(shape1);
    Shape newShape2 = ShapeAdapter.IShapeToSwingShape(shape2);

    assertEquals(newShape1, new Rectangle2D.Double(100, 200, 20, 30.5));
    assertEquals(newShape2, new Ellipse2D.Double(0, 0, 20, 30.5));
  }

  /**
   * Test static method IShapeToSVGMarkup().
   */
  @Test
  public void testIShapeToSVGMarkup() {
    assertEquals(ShapeAdapter.IShapeToSVGMarkup(shape1),
            "<rect x=\"100.0\" y=\"200.0\" width=\"20.0\" height=\"30.5\" "
                    + "fill=\"rgb(255,255,255)\" />\n");

    assertEquals(ShapeAdapter.IShapeToSVGMarkup(shape2),
            "<ellipse cx=\"0.0\" cy=\"0.0\" rx=\"20.0\" ry=\"30.5\" "
                    + "fill=\"rgb(255,255,255)\" />\n");
  }
}
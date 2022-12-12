import static org.junit.Assert.assertEquals;

import album.model.shapes.Oval;
import album.model.shapes.Rectangle;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;
import album.util.IShapeFactory;
import org.junit.Test;

/**
 * Test class IShapeFactory.
 */
public class IShapeFactoryTest {

  /**
   * Test create().
   */
  @Test
  public void create() {
    assertEquals(IShapeFactory.create("ReCtangle",
        1.0,0.5,0,100,200,50,60),
            new Rectangle(new Color(1, 0, 0.5), new Point2D(100, 200),
                    50, 60));

    assertEquals(IShapeFactory.create("OVAL",
        0.0,0.5,0,10,23.5,50,2000),
            new Oval(new Color(0, 0, 0.5), new Point2D(10, 23.5),
                    50, 2000));

  }
}
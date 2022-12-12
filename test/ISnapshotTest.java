import static org.junit.Assert.assertEquals;

import album.model.ISnapshot;
import album.model.SnapshotImpl;
import album.model.shapes.IShape;
import album.model.shapes.Oval;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Test class for ISnapshot.
 */
public class ISnapshotTest {
  private ISnapshot snap1;

  /**
   * Test cases.
   */
  @Before
  public void setUp() {
    Map<String, IShape> map = new LinkedHashMap<String, IShape>();
    map.put("O", new Oval(new Color(0.1, 0, 0.4), new Point2D(100, 50), 10, 5));
    snap1 = new SnapshotImpl("2022-03-30T11:51:02.247794", "30-03-2022 11:51:02", "test", map);
  }

  /**
   * Test getID.
   */
  @Test
  public void getID() {
    assertEquals(snap1.getID(), "2022-03-30T11:51:02.247794");
  }

  /**
   * Test getTimestamp.
   */
  @Test
  public void getTimestamp() {
    assertEquals(snap1.getTimestamp(), "30-03-2022 11:51:02");
  }

  /**
   * Test getDescription.
   */
  @Test
  public void getDescription() {
    assertEquals(snap1.getDescription(), "test");
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString() {
    assertEquals(snap1.toString(), "Snapshot ID: 2022-03-30T11:51:02.247794\n"
            + "Timestamp: 30-03-2022 11:51:02\n"
            + "Description: test\n"
            + "Shape Information: \n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, Color: (0.1, 0.0, 0.4)");
  }

  /**
   * Test getShapes().
   */
  @Test
  public void testGetShapes() {
    assertEquals(1, snap1.getShapes().size());
    assertEquals(snap1.getShapes().get("O").toString(),
            "Type: oval\n" + "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, "
                    + "Color: (0.1, 0.0, 0.4)");
  }
}
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import album.model.IPhotoAlbum;
import album.model.ISnapshot;
import album.model.PhotoAlbumImpl;
import album.model.shapes.component.Color;
import album.model.shapes.IShape;
import album.model.shapes.Oval;
import album.model.shapes.component.Point2D;
import album.model.shapes.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for interface IPhotoAlbum.
 */
public class IPhotoAlbumTest {
  private IPhotoAlbum album1;
  private IPhotoAlbum album2;
  private IShape shape1;
  private IShape shape2;

  /**
   * Set up test cases.
   */
  @Before
  public void setUp() {
    album1 = new PhotoAlbumImpl();
    album2 = new PhotoAlbumImpl();
    shape1 = new Oval(new Color(1, 0, 0), new Point2D(100,50), 10, 5);
    shape2 = new Rectangle(new Color(1,0,1), new Point2D(20,30), 20,40);

    album1.addShape("O", shape1);
  }

  /**
   * Test isEmpty().
   */
  @Test
  public void testIsEmpty() throws InterruptedException {
    assertTrue(album2.isEmpty());
    album1.takeSnapshot("First");
    assertFalse(album1.isEmpty());
  }

  /**
   * Test takeSnapshots.
   */
  @Test
  public void testTakeSnapShots() throws InterruptedException {
   ISnapshot snap = album1.takeSnapshot("after O");
   assertEquals(snap.toString(),
           "Snapshot ID: " + snap.getID() + "\n" +
                   "Timestamp: " + snap.getTimestamp() + "\n" +
                   "Description: " + snap.getDescription() + "\n" +
                   "Shape Information: \n" +
                   "Name: O\nType: oval\n" +
                   "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, Color: (1.0, 0.0, 0.0)");
  }

  /**
   * Test printSnapshots().
   */
  @Test
  public void testPrintSnapshots() throws InterruptedException {
    album2 = new PhotoAlbumImpl();
    album2.addShape("O", shape1);
    ISnapshot snap = album2.takeSnapshot("after O");
    assertEquals(album2.printSnapshots(),
            "Printing Snapshots\n" +
                    "Snapshot ID: " + snap.getID() + "\n" +
                    "Timestamp: " + snap.getTimestamp() + "\n" +
                    "Description: after O\n" +
                    "Shape Information: \n" +
                    "Name: O\nType: oval\n" +
                    "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, Color: (1.0, 0.0, 0.0)");
    album2.addShape("R", shape2);
    ISnapshot snap2 = album2.takeSnapshot("after R");

    assertEquals(album2.printSnapshots(),
            "Printing Snapshots\n" +
              "Snapshot ID: " + snap.getID() + "\n" +
              "Timestamp: " + snap.getTimestamp() + "\n" +
              "Description: after O\n" +
              "Shape Information: \n" +
              "Name: O\nType: oval\n" +
              "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, Color: (1.0, 0.0, 0.0)\n\n" +

              "Snapshot ID: " + snap2.getID() + "\n" +
              "Timestamp: " + snap2.getTimestamp() + "\n" +
              "Description: after R\n" +
              "Shape Information: \n" +
              "Name: O\nType: oval\n" +
              "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, Color: (1.0, 0.0, 0.0)\n" +
              "Name: R\n" +
              "Type: rectangle\n" +
              "Min corner: (20.0, 30.0), Width: 20.0, Height: 40.0, Color: (1.0, 0.0, 1.0)");
  }

  /**
   * Test removeSnapshot().
   */
  public void testRemoveSnapshot() throws InterruptedException {
    ISnapshot snap1 = album1.takeSnapshot("First");
    assertEquals(album1.getNumSnapshots(), 1, 0.0001);
    album1.removeSnapshot(snap1.getID());
    assertEquals(album1.getNumSnapshots(),0,0.0001);
  }

  /**
   * Test getSnapshot().
   */
  @Test
  public void testGetSnapshot() throws InterruptedException {
    ISnapshot snap = album1.takeSnapshot("after O");
    assertEquals(album1.getSnapshot(0).toString(),
            "Snapshot ID: " + snap.getID() + "\n" +
                    "Timestamp: " + snap.getTimestamp() + "\n" +
                    "Description: " + snap.getDescription() + "\n" +
                    "Shape Information: \n" +
                    "Name: O\nType: oval\n" +
                    "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, Color: (1.0, 0.0, 0.0)");
  }

  /**
   * Test containsShape().
   */
  @Test
  public void testContainsShape() {
    assertTrue(album1.containsShape("O"));
    assertFalse(album1.containsShape("123"));
  }

  /**
   * Test addShape().
   */
  @Test
  public void testAddShape() {
    assertFalse(album1.containsShape("R1"));
    album1.addShape("R1",shape1);
    assertTrue(album1.containsShape("R1"));
  }

  /**
   * Test removeShape().
   */
  @Test
  public void testRemoveShape() {
    album1.addShape("R",shape2);
    assertTrue(album1.containsShape("R"));
    album1.removeShape("R");
    assertFalse(album1.containsShape("R"));
  }

  /**
   * Test getShape().
   */
  @Test
  public void testGetShape() {
    assertEquals(album1.getShape("N"),null);
    assertEquals(album1.getShape("O"), shape1);
  }

  /**
   * Test moveShape().
   */
  @Test
  public void testMoveShape() {
    assertTrue(album1.moveShape("O", new Point2D(25, 30)));
    assertEquals(album1.getShape("O").getCoordinate().toString(),"(25.0, 30.0)");
  }

  /**
   * Test changeShapeColor().
   */
  @Test
  public void testChangeShapeColor() {
    assertTrue(album1.changeShapeColor("O", new Color(0, 0, 1)));
    assertEquals(album1.getShape("O").getColor().toString(),
            "(0.0, 0.0, 1.0)");
  }

  /**
   * Test changeShapeXDimension().
   */
  @Test
  public void testChangeShapeXDimension() {
    assertTrue(album1.changeShapeXDimension("O", 100.23));
    assertEquals(album1.getShape("O").getXDimension(),100.23,0.001);
  }

  /**
   * Test changeShapeYDimension().
   */
  @Test
  public void testChangeShapeYDimension() {
    assertTrue(album1.changeShapeYDimension("O", 10.23));
    assertEquals(album1.getShape("O").getYDimension(),10.23,0.001);
  }

  /**
   * Test scaleShape().
   */
  @Test
  public void testScaleShape() {
    assertTrue(album1.scaleShape("O",2));
    assertEquals(album1.getShape("O").getXDimension(),20, 0.001);
    assertEquals(album1.getShape("O").getYDimension(),10, 0.001);
  }

  /**
   * Test reset().
   */
  @Test
  public void testReset() {
    album1.reset();
    assertEquals(album1.printSnapshots(), "Printing Snapshots\n");
  }

  /**
   * Test clearShapes().
   */
  @Test
  public void testClearShapes() {
    album1.clearShapes();
    assertEquals(album1.toString(), "");
  }

  /**
   * Test toString().
   */
  @Test
  public void testToString() {
    assertEquals(album1.toString(),
            "Name: O\n" +
            "Type: oval\n" +
            "Center: (100.0, 50.0), X radius: 10.0, Y radius: 5.0, Color: (1.0, 0.0, 0.0)\n");
  }

  /**
   * Test getNumShapes().
   */
  @Test
  public void testGetNumShapes() {
    assertEquals(album1.getNumShapes(), 1);
    assertEquals(album2.getNumShapes(), 0);
  }

  /**
   * Test getNumSnapshots().
   */
  @Test
  public void testGetNumSnapshots() throws InterruptedException {
    album1.takeSnapshot("First");
    assertEquals(album1.getNumSnapshots(),1);
    assertEquals(album2.getNumSnapshots(),0);
  }

}
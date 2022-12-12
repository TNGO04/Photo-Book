import static org.junit.Assert.assertEquals;

import album.controller.IPhotoAlbumController;
import album.controller.PhotoAlbumController;
import album.model.IPhotoAlbum;
import album.model.ISnapshot;
import album.model.PhotoAlbumMock;
import album.model.SnapshotImpl;
import album.model.shapes.IShape;
import album.model.shapes.Rectangle;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;
import album.view.IPhotoAlbumView;
import album.view.PhotoAlbumViewMock;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Test IPhotoAlbumController.
 */
public class IPhotoAlbumControllerTest {

  private IPhotoAlbum model;
  private IPhotoAlbumView view;
  private String text;
  private String outputFilename;
  private IPhotoAlbumController controller;
  private File output;
  private StringBuilder modelLog;
  private StringBuilder viewLog;

  private static Map<String, IShape> map = new LinkedHashMap<String, IShape>();
  private static final ISnapshot uniqueSnapshot =
          new SnapshotImpl("test", "test", "test", map);
  private static final int uniqueInt = 12345;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  /**
   * Set up test cases.
   */
  @Before
  public void setUp() {
    modelLog = new StringBuilder();
    viewLog = new StringBuilder();
    model = new PhotoAlbumMock(modelLog,uniqueSnapshot, uniqueInt);

    outputFilename = "output.html";
    output = new File(outputFilename);
    output.delete();

    view = new PhotoAlbumViewMock(viewLog);
    text = "./resources/testFile.txt";
    controller =  new PhotoAlbumController(model, view, text);
    System.setOut(new PrintStream(outContent));
  }

  /**
   * Test execute() using PhotoAlbumMock to ensure that controller sends the correct commands and
   * inputs to model while executing.
   */
  @Test
  public void testExecute() throws Exception {
    controller.execute();
    assertEquals(modelLog.toString(),
            "(addShape) input 1: R\n" + "input 2: "
                    + "Type: rectangle\n"
                    + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0, 1.0, 1.0)\n"
                    + "(addShape) input 1: O\n" + "input 2: "
                    + "Type: oval\n"
                    + "Center: (200.0, 200.0), X radius: 50.0, Y radius: 100.0, Color: (0.0, 0.0, 0.0)\n"
                    + "(takeSnapshot) input: \n"
                    + "(moveShape) input 1: O input 2: (300.0, 300.0)\n"
                    + "(takeSnapshot) input: \n"
                    + "(removeShape) input : O\n"
                    + "(takeSnapshot) input: final snapshot\n"
                    + "(removeShape) input : O\n"
                    + "(addShape) input 1: R\n"
                    + "input 2: Type: oval\n"
                    + "Center: (200.0, 200.0), X radius: 50.0, Y radius: 100.0, Color: (0.0, 0.0, 0.0)\n"
                    + "(moveShape) input 1: O input 2: (300.0, 300.0)\n"
                    + "(changeShapeColor) input 1: O input 2: (0.1, 0.2, 0.1)\n"
                    + "(changeShapeXDimension) input 1: O input 2: 10.0\n"
                    + "(changeShapeYDimension) input 1: O input 2: 30.0\n");
  }

  /**
   * Test transmission of data between controller and view using setView() and mock view.
   */
  @Test
  public void testSetView() throws InterruptedException {
    IShape uniqueShape = new Rectangle(new Color(1,1,1),new Point2D(100, 50), 1, 10);
    map.put("test", uniqueShape);
    controller.setView();
    assertEquals(viewLog.toString(),
            "(setActionListener) input: " + controller.hashCode() + "\n"
            + "(addSnapshotToCanvas) input: " + uniqueSnapshot.getShapes().values().hashCode() + "\n"
            + "(updateLabel) input: \tID: test\t\n\tDescription: test\t\n"
            + "(refreshPanel)\n"
            + "(display) input: " + model.getAllSnapshots().values().hashCode() + "\n");
  }
}
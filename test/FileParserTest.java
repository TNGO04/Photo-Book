import org.junit.Test;

import static org.junit.Assert.assertEquals;

import album.model.IPhotoAlbum;
import album.model.ISnapshot;
import album.model.PhotoAlbumMock;
import album.model.SnapshotImpl;
import album.util.FileParser;
import album.util.FileToModelParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedHashMap;

/**
 * Test class for FileParser.
 */
public class FileParserTest {
  private IPhotoAlbum model;
  private String text;
  private File input;
  private StringBuilder log;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final ISnapshot uniqueSnapshot =
          new SnapshotImpl("test", "test", "test", new LinkedHashMap<>());
  private static final int uniqueInt = 12345;
  private FileParser parser;


  /**
   * Test parse().
   */
  @Test
  public void testParse() throws FileNotFoundException, InterruptedException {
    log  = new StringBuilder();
    model = new PhotoAlbumMock(log, uniqueSnapshot, uniqueInt);
    text = "./resources/testFile.txt";
    input = new File(text);
    parser = new FileToModelParser(model, input);
    System.setOut(new PrintStream(outContent));

    parser.parse();

    // ensure that the right command and inouts are passed to model by the FileParser class
    assertEquals(log.toString(),
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

    // test invalid syntax in file, make sure whenever lines are skipped messages
    // are printed to System.out
    assertEquals(outContent.toString(), "Line 15 skipped because shape is not found\r\n"
            + "Line 16 skipped due to invalid syntax\r\n"
            + "Line 18 skipped because shape with similar identifier already exists\r\n"
            + "Line 19 skipped due to invalid syntax\r\n"
            + "Line 21 skipped because shape is not found\r\n"
            + "Line 22 skipped due to invalid syntax\r\n"
            + "Line 24 skipped because shape is not found\r\n"
            + "Line 25 skipped due to invalid syntax\r\n"
            + "Line 27 skipped because shape is not found\r\n"
            + "Line 28 skipped due to invalid syntax\r\n");

  }
}
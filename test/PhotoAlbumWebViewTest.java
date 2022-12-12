import album.controller.IPhotoAlbumController;
import album.controller.PhotoAlbumController;
import album.model.IPhotoAlbum;
import album.model.PhotoAlbumImpl;
import album.view.IPhotoAlbumView;
import album.view.PhotoAlbumWebView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Test class for PhotoAlbumWebView.
 */
public class PhotoAlbumWebViewTest {
  private IPhotoAlbumController controller;
  private IPhotoAlbumView view;
  private IPhotoAlbum model;
  private final String outputFilename = "output.html";
  private final File output = new File(outputFilename);
  private final String inputFilename = "./resources/testFile.txt";

  /**
   * Set up test case using test input file.
   */
  @Before
  public void setUp() throws Exception {
    //delete output file if it already exists
    output.delete();
    view = new PhotoAlbumWebView(outputFilename, 800, 800);
    model = new PhotoAlbumImpl();
    controller = new PhotoAlbumController(model, view, inputFilename);
    controller.execute();
  }

  /**
   * Test that file is successfully created and content is accurate.
   * @throws IOException if file not found
   */
  @Test
  public void display() throws IOException {
    Assert.assertFalse(output.exists());
    view.display(model.getAllSnapshots().values());

    Assert.assertTrue(output.exists());
    String htmlContent = Files.readString(Paths.get("output.html"));

    Assert.assertEquals(htmlContent, "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<title>"
            + "My Photo Album"
            + "</title>\n"
            + "<head>\n"
            + "<style>\n"
            + "  div {border: 1px solid;\n"
            + "  display: flex;\n"
            + "  flex-direction: column;\n"
            + "  align-items: center;\n"
            + "  padding: 30px;\n"
            + "  margin: 20px;\n"
            + "  box-shadow: 5px 6px 4px #888888;}\n"
            + "  body {padding: 40px;}\n"
            + "  h1 {text-align: center; margin-bottom: 30px;}\n"
            + "</style>\n"
            + "</head>\n"
            + "<body>\n"
            + "<h1> My Photo Album </h1>\n"
            + "<div>\n"
            + "<h2>" + model.getSnapshot(0).getID() + "</h2>\n"
            + "<svg width=\"800\" height=\"800\">\n"
            + "<rect x=\"200.0\" y=\"200.0\" width=\"50.0\" "
            +        "height=\"100.0\" fill=\"rgb(255,255,255)\" />\n"
            + "<ellipse cx=\"200.0\" cy=\"200.0\" "
            +        "rx=\"50.0\" ry=\"100.0\" fill=\"rgb(0,0,0)\" />\n"
            + "</svg>\n" + "</div>\n" + "<div>\n"
            + "<h2>" + model.getSnapshot(1).getID() + "</h2>\n"
            + "<svg width=\"800\" height=\"800\">\n"
            + "<rect x=\"200.0\" y=\"200.0\" width=\"50.0\" "
            +         "height=\"100.0\" fill=\"rgb(255,255,255)\" />\n"
            + "<ellipse cx=\"300.0\" cy=\"300.0\" "
            +         "rx=\"50.0\" ry=\"100.0\" fill=\"rgb(0,0,0)\" />\n"
            + "</svg>\n"
            + "</div>\n"
            + "<div>\n"
            + "<h2>" + model.getSnapshot(2).getID() + "</h2>\n"
            + "<p>Description: final snapshot</p>\n"
            + "<svg width=\"800\" height=\"800\">\n"
            + "<rect x=\"200.0\" y=\"200.0\" width=\"50.0\" "
            +         "height=\"100.0\" fill=\"rgb(255,255,255)\" />\n"
            + "</svg>\n"
            + "</div>\n"
            + "</body>\n");
  }
}
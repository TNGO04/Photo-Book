package album.view;

import album.model.ISnapshot;
import album.model.shapes.IShape;
import album.util.ShapeAdapter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Class to implement web view, implements IPhotoAlbumView interface.
 */
public class PhotoAlbumWebView implements IPhotoAlbumView {
  private final File outputFile;
  private final int xMax;
  private final int yMax;


  /**
   * Constructor.
   *
   * @param outputFile file to write mark-up to
   * @param xMax       width of viewport for snapshot
   * @param yMax       height of viewport for snapshot
   * @throws IllegalArgumentException if output file is null
   */
  public PhotoAlbumWebView(String outputFile, int xMax, int yMax) throws IllegalArgumentException {
    if (outputFile == null) {
      throw new IllegalArgumentException("Output file argument not valid");
    }

    this.outputFile = new File(outputFile);
    if (this.outputFile.exists()) {
      System.out.println("File already exists.");
      System.exit(0);
    }

    this.xMax = xMax;
    this.yMax = yMax;
  }


  /**
   * Print out view, given snapshots to be printed out
   * (list of snapshots only used by static view,
   * since dynamic view starts off with first snapshot).
   *
   * @param list of snapshots to be displayed by static view
   */
  @Override
  public void display(Collection<ISnapshot> list) {
    try (PrintWriter output = new PrintWriter(this.outputFile)) {
      String s = createHTML(list);
      output.print(s);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Create and return text for HTML file.
   *
   * @param list list of snapshot
   * @return text for HTML
   */
  private String createHTML(Collection<ISnapshot> list) {
    String s = "";
    // add header
    s += createHTMLHeader("My Photo Album");
    //add body
    s += createHTMLBody(list);
    return s;
  }

  /**
   * Create and return text for HTML header, including style.
   *
   * @param title title of page
   * @return text for HTML
   */
  private String createHTMLHeader(String title) {
    return "<!DOCTYPE html>\n" + "<html>\n" + "<title>" + title + "</title>\n" + "<head>\n"
            // add some custom styling for page and each div holding a snapshot
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
            + "</head>\n";
  }

  /**
   * Create and return text for HTML body.
   *
   * @param list list of snapshot
   * @return text for HTML body
   */
  private String createHTMLBody(Collection<ISnapshot> list) {
    String s = "<body>\n";

    s += "<h1> My Photo Album </h1>\n";
    for (ISnapshot snapshot : list) {
      s += createHTMLForSnapshot(snapshot);
    }
    s += "</body>\n";
    return s;
  }

  /**
   * Create and return HTML text for snapshot.
   *
   * @param snapshot snapshot
   * @return text for HTML
   */
  private String createHTMLForSnapshot(ISnapshot snapshot) {
    String s = "<div>\n";
    s += "<h2>" + snapshot.getID() + "</h2>\n";

    if (snapshot.getDescription().length() > 0) {
      s += "<p>Description: " + snapshot.getDescription() + "</p>\n";
    }

    s += "<svg width=\"" + this.xMax + "\" height=\"" + this.yMax + "\">\n";
    s += createSVGForShapes(snapshot.getShapes().values());
    s += "</svg>\n";
    s += "</div>\n";
    return s;
  }

  /**
   * Create SVG mark-up for a list of shapes.
   * @param shapes  list of shapes
   * @return  SVG mark-up
   */
  private String createSVGForShapes(Collection<IShape> shapes) {
    String s = "";

    for (IShape shape : shapes) {
      s += ShapeAdapter.IShapeToSVGMarkup(shape);
    }
    return s;
  }

  /**
   * Set action listener for events in view.
   *
   * @param listener action listener
   */
  @Override
  public void setActionListener(ActionListener listener) {
  }

  /**
   * Add a shape to dynamic display canvas.
   *
   * @param shapes model representation of shape
   */
  @Override
  public void addSnapshotToCanvas(Collection<IShape> shapes) {
  }

  /**
   * Refresh dynamic panel.
   */
  @Override
  public void refreshPanel() {
  }

  /**
   * Update label on view (only used by dynamic views).
   *
   * @param content content of label
   */
  @Override
  public void updateLabel(String content) {
  }

  /**
   * Make label visible.
   */
  @Override
  public void displayLabel() {
  }

  /**
   * Display dialog boxes (only used by dynamic views).
   *
   * @param content content of dialog box
   */
  @Override
  public void displayDialog(String content) {
  }

  /**
   * Display a selector with given options and default choice.
   *
   * @param options       list of options
   * @param defaultChoice default choice
   * @return String representing option selected
   */
  @Override
  public String displaySelector(Object[] options, Object defaultChoice) {
    return null;
  }
}

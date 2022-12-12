package album.view;

import album.model.ISnapshot;
import album.model.shapes.IShape;

import java.awt.event.ActionListener;
import java.util.Collection;

/**
 * Mock class to test data transmission from Controller to View.
 */
public class PhotoAlbumViewMock implements IPhotoAlbumView {
  private StringBuilder log;

  /**
   * Constructor.
   * @param log command and input log.
   */
  public PhotoAlbumViewMock(StringBuilder log) {
    this.log = log;
  }

  /**
   * Print out view, given snapshots to be printed out
   * (list of snapshots only used by static view, since dynamic view starts off with
   * first snapshot).
   *
   * @param list of snapshots to be displayed by static view
   */
  @Override
  public void display(Collection<ISnapshot> list) {
    log.append("(display) input: " + list.hashCode() + "\n");
  }

  /**
   * Set action listener for events in view.
   *
   * @param listener action listener
   */
  @Override
  public void setActionListener(ActionListener listener) {
    log.append("(setActionListener) input: " + listener.hashCode() + "\n");
  }

  /**
   * Add a shape to dynamic display canvas.
   *
   * @param shapes model representation of shape
   */
  @Override
  public void addSnapshotToCanvas(Collection<IShape> shapes) {
    log.append("(addSnapshotToCanvas) input: " + shapes.hashCode() + "\n");
  }

  /**
   * Refresh dynamic panel.
   */
  @Override
  public void refreshPanel() {
    log.append("(refreshPanel)\n");
  }

  /**
   * Update label on view (only used by dynamic views).
   *
   * @param content content of label
   */
  @Override
  public void updateLabel(String content) {
    log.append("(updateLabel) input: " + content + "\n");
  }

  /**
   * Make label visible.
   */
  @Override
  public void displayLabel() {
    log.append("(displayLabel)\n");
  }

  /**
   * Display dialog boxes (only used by dynamic views).
   *
   * @param content content of dialog box
   */
  @Override
  public void displayDialog(String content) {
    log.append("(displayDialog) input: " + content + "\n");
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
    log.append("(displaySelector)\n");
    return null;
  }
}

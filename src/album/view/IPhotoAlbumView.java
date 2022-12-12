package album.view;

import album.model.ISnapshot;
import album.model.shapes.IShape;

import java.awt.event.ActionListener;
import java.util.Collection;

/**
 * Public interface for application view.
 */
public interface IPhotoAlbumView {

  /**
   * Print out view, given snapshots to be printed out
   * (list of snapshots only used by static view, since dynamic view starts off with
   * first snapshot).
   * @param list of snapshots to be displayed by static view
   */
  void display(Collection<ISnapshot> list);

  /**
   * Set action listener for events in view.
   * @param listener  action listener
   */
  void setActionListener(ActionListener listener);

  /**
   * Add a shape to dynamic display canvas.
   *
   * @param shapes model representation of shape
   */
  void addSnapshotToCanvas(Collection<IShape> shapes);

  /**
   * Refresh dynamic panel.
   */
  void refreshPanel();

  /**
   * Update label on view (only used by dynamic views).
   * @param content content of label
   */
  void updateLabel(String content);

  /**
   * Make label visible.
   */
  void displayLabel();

  /**
   * Display dialog boxes (only used by dynamic views).
   * @param content content of dialog box
   */
  void displayDialog(String content);

  /**
   * Display a selector with given options and default choice.
   * @param options list of options
   * @param defaultChoice default choice
   * @return  String representing option selected
   */
  String displaySelector(Object[] options, Object defaultChoice);
}

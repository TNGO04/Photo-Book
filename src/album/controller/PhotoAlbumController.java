package album.controller;

import album.model.IPhotoAlbum;
import album.model.ISnapshot;
import album.util.FileParser;
import album.util.FileToModelParser;
import album.view.IPhotoAlbumView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



/**
 * PhotoAlbumController implements public interface IPhotoAlbumController and ActionListener.
 */
public class PhotoAlbumController implements IPhotoAlbumController, ActionListener {
  private IPhotoAlbum model;
  private IPhotoAlbumView view;
  private String instructionFilename;
  private ISnapshot currentSnapshot;
  private int currentSnapshotIndex;

  /**
   * Constructor.
   * @param model model for album
   * @param view  view for album application
   * @throws IllegalArgumentException if input is null
   */
  public PhotoAlbumController(IPhotoAlbum model, IPhotoAlbumView view, String instructionFilename)
          throws IllegalArgumentException {
    if ((model == null) || (view == null)) {
      throw new IllegalArgumentException("Model or view object cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.currentSnapshotIndex = 0;
    this.currentSnapshot = null;
    this.instructionFilename = instructionFilename;
  }

  /**
   * Build photo album from instruction file.
   */
  @Override
  public void execute() throws Exception {
    File instructionFile = new File(this.instructionFilename);
    FileParser parser = new FileToModelParser(model, instructionFile);
    parser.parse();
  }

  /**
   * Initialize view with first snapshot and then display/output view.
   */
  @Override
  public void setView() throws InterruptedException {
    this.view.setActionListener(this);
    currentSnapshot = this.model.getSnapshot(this.currentSnapshotIndex);
    this.updateView();
    this.view.display(this.model.getAllSnapshots().values());
  }

  /**
   * Update entire view (both image pane and label) and refresh to reflect changes.
   */
  private void updateView() {
    this.updatePane();
    this.updateLabel();
    this.view.refreshPanel();
  }

  /**
   * Update drawing panel by adding snapshot shapes to canvas.
   */
  private void updatePane() {
    this.view.addSnapshotToCanvas(this.currentSnapshot.getShapes().values());
  }


  /**
   * Send snapshot details to label in view.
   */
  private void updateLabel() {
    String s = "";
    s += "\tID: " + this.currentSnapshot.getID() + "\t";
    if (this.currentSnapshot.getDescription().length() > 0) {
      s += "\n";
      s += "\tDescription: " + this.currentSnapshot.getDescription() + "\t";
    }
    this.view.updateLabel(s);
  }

  /**
   * Send command to view to display label.
   */
  private void displayLabel() {
    this.view.displayLabel();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    //call appropriate methods corresponding to action performed
    switch (e.getActionCommand()) {
      case "Previous":
        this.getPreviousSnapshot();
        this.updateView();
        break;
      case "Next":
        this.getNextSnapshot();
        this.updateView();
        break;
      case "Details":
        this.displayLabel();
        break;
      case "Select":
        this.selectSnapshots();
        this.updateView();
        break;
    }
  }

  /**
   * Change current snapshot to next, if there is one.
   */
  private void getNextSnapshot() {
    if (currentSnapshotIndex == (this.model.getNumSnapshots() - 1)) {
      this.view.displayDialog("This is the last snapshot, no more available.");
    }
    else {
      currentSnapshotIndex++;
      currentSnapshot = this.model.getSnapshot(this.currentSnapshotIndex);
    }
  }

  /**
   * Change current snapshot to previous snapshot, if there is one.
   */
  private void getPreviousSnapshot() {
    if (currentSnapshotIndex == 0) {
      this.view.displayDialog("This is the first snapshot, no previous available.");
    }
    else {
      currentSnapshotIndex--;
      currentSnapshot = this.model.getSnapshot(this.currentSnapshotIndex);
    }
  }

  /**
   * Ask view to display a selector for snapshots, then send the selected snapshot to view.
   */
  private void selectSnapshots() {
    String id = this.view.displaySelector(
            this.model.getAllSnapshots().keySet().toArray(new Object[0]),
            (Object) this.currentSnapshot.getID());

    currentSnapshotIndex = this.model.getSnapShotIndex(id);
    currentSnapshot = this.model.getSnapshot(id);
  }
}

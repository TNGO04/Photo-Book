package album.model;

import album.model.shapes.IShape;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * PhotoAlbumMock class implements IPhotoAlbum interface, but provides almost no functionality aside
 * from logging a list of shape identifier, logging whenever a function is called and its input,
 * as well as returning a unique ISnapshot or unique integer to test input and output tranmission.
 */
public class PhotoAlbumMock implements IPhotoAlbum {
  private StringBuilder log;
  private ISnapshot uniqueSnapShot;
  private int uniqueInt;
  private List<String> shapeIDList;
  private Map<String, ISnapshot> map;

  /**
   * Constructor.
   * @param log log of function calls and inputs
   * @param uniqueSnapshot  unique snapshot to return
   * @param uniqueInt unique integer to return
   */
  public PhotoAlbumMock(StringBuilder log, ISnapshot uniqueSnapshot, int uniqueInt) {
    this.log = log;
    this.uniqueSnapShot = uniqueSnapshot;
    this.uniqueInt = uniqueInt;
    shapeIDList = new ArrayList<String>();
    map = new LinkedHashMap<String, ISnapshot>();
    map.put("test", uniqueSnapShot);
  }

  /**
   * Return current shapes in album (as a snapshot).
   *
   * @param description description of snapshot
   * @return list of shapes
   */
  @Override
  public ISnapshot takeSnapshot(String description)
          throws IllegalArgumentException, InterruptedException {
    log.append("(takeSnapshot) input: " + description + "\n");
    return this.uniqueSnapShot;
  }


  /**
   * Remove snapshot with the identifier.
   *
   * @param id id of snapshot
   * @return true if remove successful, false if not
   */
  @Override
  public boolean removeSnapshot(String id) throws IllegalArgumentException {
    log.append("(removeSnapshot) input: " + id + "\n");
    return false;
  }

  /**
   * Return snapshot with ID.
   *
   * @param id identifier of snapshot
   * @return ISnapshot if found, null if not
   */
  @Override
  public ISnapshot getSnapshot(String id) throws IllegalArgumentException {
    log.append("(getSnapshot) input: " + id + "\n");
    return this.uniqueSnapShot;
  }

  /**
   * Get snapshot by index, starting at 0 for first snapshot.
   *
   * @param index index of snapshot
   * @return ISnapshot object
   * @throws IllegalArgumentException if index out of range
   */
  @Override
  public ISnapshot getSnapshot(int index) throws IllegalArgumentException {
    log.append("(getSnapshot) input: " + index + "\n");
    return this.uniqueSnapShot;
  }

  /**
   * Given an identifier, find the index.
   *
   * @param id identifier of snapshot
   * @return @return -1 if snapshot not found, index of snapshot in the LinkedHashMap if found
   * @throws IllegalArgumentException if id is null
   */
  @Override
  public int getSnapShotIndex(String id) {
    log.append("(getSnapshotIndex) input: " + id + "\n");
    return this.uniqueInt;
  }

  /**
   * Check if album contains a shape with given identifier.
   *
   * @param id identifier of shape to be checked
   * @return true if yes, false if no
   */
  @Override
  public boolean containsShape(String id) {
    return shapeIDList.contains(id);
  }

  /**
   * Add a new shape into the album, along with identifier.
   *
   * @param id    identifier
   * @param shape shape to be added
   * @return true if added successfully, false if identifier already taken or shape is null
   */
  @Override
  public boolean addShape(String id, IShape shape) {
    log.append("(addShape) input 1: " + id + "\n" + "input 2: " + shape.toString() + "\n");

    shapeIDList.add(id);
    return true;
  }

  /**
   * Remove a shape with given identifier from album.
   *
   * @param id identifier of shape
   * @return true if successfully remove, false if shape with identifier not found
   */
  @Override
  public boolean removeShape(String id) {
    log.append("(removeShape) input : " + id + "\n");
    if (shapeIDList.contains(id)) {
      shapeIDList.remove(id);
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Return shape with ID.
   *
   * @param id identifier of shape
   * @return IShape object or null if shape not found
   */
  @Override
  public IShape getShape(String id) {
    log.append("(getShape) input: " + id + "\n");
    return null;
  }

  /**
   * Move shape.
   *
   * @param id       identifier of shape to be moved
   * @param newPoint new coordinate point
   * @return true if move successful, false if shape with ID not found
   * @throws IllegalArgumentException if null object was passed in as input
   */
  @Override
  public boolean moveShape(String id, Point2D newPoint) throws IllegalArgumentException {
    log.append("(moveShape) input 1: " + id + " input 2: " + newPoint.toString() + "\n");
    return shapeIDList.contains(id);
  }

  /**
   * Change color of a shape.
   *
   * @param id    identifier of shape
   * @param color color to be changed to
   * @return true if successfully change color, false if identifier not found
   * @throws IllegalArgumentException if Color is null
   */
  @Override
  public boolean changeShapeColor(String id, Color color) throws IllegalArgumentException {
    log.append("(changeShapeColor) input 1: " + id + " input 2: " + color.toString() + "\n");
    return shapeIDList.contains(id);
  }

  /**
   * Change the x-dimension (width, x-radius, etc.) of shape
   *
   * @param id        identifier of shape
   * @param dimension dimension to be changed to
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  @Override
  public boolean changeShapeXDimension(String id, double dimension)
          throws IllegalArgumentException {
    log.append("(changeShapeXDimension) input 1: " + id + " input 2: " + dimension + "\n");
    return shapeIDList.contains(id);
  }

  /**
   * Change the y-dimension (height, y-radius, etc.) of shape
   *
   * @param id        identifier of shape
   * @param dimension dimension to be changed to
   * @return true if successfully change dimension, false if identifier not found
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  @Override
  public boolean changeShapeYDimension(String id, double dimension)
          throws IllegalArgumentException {
    log.append("(changeShapeYDimension) input 1: " + id + " input 2: " + dimension + "\n");
    return shapeIDList.contains(id);
  }

  /**
   * Scale shape.
   *
   * @param id          identifier of shape
   * @param scaleFactor factor to scale shape by
   * @return true if successfully scale shape, false if identifier not found
   * @throws IllegalArgumentException if scale factor is invalid (non-positive)
   */
  @Override
  public boolean scaleShape(String id, double scaleFactor) throws IllegalArgumentException {
    log.append("(scaleShape) input 1: " + id + " input 2: " + scaleFactor + "\n");
    return shapeIDList.contains(id);
  }

  /**
   * Check if photo album is empty.
   *
   * @return true if album is empty
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * Print all snapshots out.
   *
   * @return String representation of all snapshots taken before reset.
   */
  @Override
  public String printSnapshots() {
    return null;
  }

  /**
   * Getter for snapshot map.
   *
   * @return snapshot map
   */
  @Override
  public Map<String, ISnapshot> getAllSnapshots() {
    return map;
  }

  /**
   * Reset snapshot histories.
   */
  @Override
  public void reset() {

  }

  /**
   * Clear all shapes from album.
   */
  @Override
  public void clearShapes() {

  }

  /**
   * Get number of shapes in album.
   *
   * @return number of shapes
   */
  @Override
  public int getNumShapes() {
    return this.uniqueInt;
  }

  /**
   * Get number of snapshots in album.
   *
   * @return number of snapshots
   */
  @Override
  public int getNumSnapshots() {
    return this.uniqueInt;
  }
}

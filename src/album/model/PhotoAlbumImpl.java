package album.model;

import album.model.shapes.IShape;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Class PhotoAlbumImpl implements interface IPhotoAlbum.
 */
public class PhotoAlbumImpl implements IPhotoAlbum {
  private Map<String, IShape> shapeList;
  private Map<String, ISnapshot> snapshotList;
  private static final DateTimeFormatter formatterID = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
  private static final DateTimeFormatter formatterTimestamp
          = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  /**
   * Constructor.
   */
  public PhotoAlbumImpl() {
    this.shapeList = new LinkedHashMap<String, IShape>();
    this.snapshotList = new LinkedHashMap<String, ISnapshot>();
  }

  /**
   * Check if photo album is empty (no snapshot).
   *
   * @return true if album is empty
   */
  @Override
  public boolean isEmpty() {
    return (this.snapshotList.isEmpty());
  }

  /**
   * Return current shapes in album (as a snapshot).
   *
   * @return snapshot
   */
  @Override
  public ISnapshot takeSnapshot(String description)
          throws IllegalArgumentException, InterruptedException {
    if (description == null) {
      throw new IllegalArgumentException("Description object cannot be null");
    }

    LocalDateTime now = LocalDateTime.now();

    String id = now.format(formatterID);
    String timestamp = now.format(formatterTimestamp);

    Map<String, IShape> listCopy = new LinkedHashMap<String, IShape>();

    for (String key: shapeList.keySet()) {
      listCopy.put(key, shapeList.get(key).getCopy());
    }

    ISnapshot newSnap = new SnapshotImpl(id,timestamp,description,listCopy);
    snapshotList.put(id, newSnap);
    TimeUnit.MILLISECONDS.sleep(5);
    return newSnap;
  }

  @Override
  public String printSnapshots() {
    String s = "";

    s += "Printing Snapshots\n";

    int i = 0;
    int len = snapshotList.size();
    for (ISnapshot snapshot: snapshotList.values()) {
      s += snapshot.toString();

      if (i != (len - 1)) {
        s += "\n\n";
      }
      i++;
    }
    return s;
  }

  /**
   * Remove snapshot with the identifier.
   *
   * @param id  id of snapshot
   * @return true if remove successful, false if not
   */
  @Override
  public boolean removeSnapshot(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("ID object is null.");
    }

    if (this.snapshotList.containsKey(id)) {
      this.snapshotList.remove(id);
      return true;
    }
    else {
      return false;
    }
  }


  /**
   * Get a snapshot given its ID.
   * @param id  identifier of snapshot
   * @return  ISnapshot object if found, null if not
   */
  @Override
  public ISnapshot getSnapshot(String id) {
    if (id == null) {
      throw new IllegalArgumentException("ID object is null.");
    }

    if (this.snapshotList.containsKey(id)) {
      return this.snapshotList.get(id);
    }
    else return null;
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
    if ((index < 0) || (index >= this.snapshotList.size())) {
      throw new IllegalArgumentException("Index out of range.");
    }
    String key = (String) this.snapshotList.keySet().toArray()[index];
    return this.snapshotList.get(key);
  }

  /**
   * Given an identifier, find the index.
   *
   * @param id identifier of snapshot
   * @return -1 if snapshot not found, index of snapshot in the LinkedHashMap if found
   * @throws IllegalArgumentException if id is null
   */
  @Override
  public int getSnapShotIndex(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("ID");
    }

    int i = 0;
    for (String key: this.snapshotList.keySet()) {
      if (key.equals(id)) {
        return i;
      }
      i++;
    }
    return -1;
  }

  /**
   * Getter for snapshot map.
   * @return  snapshot map
   */
  @Override
  public Map<String, ISnapshot> getAllSnapshots() {
    return this.snapshotList;
  }

  /**
   * Add a new shape into the album, along with identifier.
   *
   * @param id    identifier
   * @param shape shape to be added
   * @return true if added successfully, false if identifier already taken or shape is null
   */
  @Override
  public boolean addShape(String id, IShape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null.");
    }
    if (this.containsShape(id)) {
      return false;
    }

    this.shapeList.put(id, shape);
    return true;
  }

  /**
   * Check if album contains a shape with given identifier.
   * @param id  identifier of shape to be checked
   * @return  true if yes, false if no
   */
  public boolean containsShape(String id) {
    return this.shapeList.containsKey(id);
  }

  /**
   * Remove a shape with given identifier from album.
   *
   * @param id identifier of shape
   * @return true if successfully remove, false if shape with identifier not found
   */
  @Override
  public boolean removeShape(String id) {
    if (!this.containsShape(id)) {
      return false;
    }
    this.shapeList.remove(id);
    return true;
  }

  /**
   * Return shape with ID.
   *
   * @param id identifier of shape
   * @return IShape object or null if shape not found
   */
  @Override
  public IShape getShape(String id) {
    if (shapeList.containsKey(id)) {
      return shapeList.get(id);
    }
    else {
      return null;
    }
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
    if (!this.containsShape(id)) {
      return false;
    }
    this.shapeList.get(id).move(newPoint);
    return true;
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
    if (!this.containsShape(id)) {
      return false;
    }
    this.shapeList.get(id).changeColor(color);
    return true;
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
    if (!this.containsShape(id)) {
      return false;
    }
    this.shapeList.get(id).changeXDimension(dimension);
    return true;
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
    if (!this.containsShape(id)) {
      return false;
    }
    this.shapeList.get(id).changeYDimension(dimension);
    return true;
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
    if (!this.containsShape(id)) {
      return false;
    }
    this.shapeList.get(id).scaleShape(scaleFactor);
    return true;
  }

  /**
   * Reset snapshot histories.
   */
  @Override
  public void reset() {
    this.snapshotList.clear();
  }

  /**
   * Clear all shapes from album.
   */
  @Override
  public void clearShapes() {
    this.shapeList.clear();
  }

  /**
   * Get number of shapes in album.
   *
   * @return number of shapes
   */
  @Override
  public int getNumShapes() {
    return this.shapeList.size();
  }

  /**
   * Get number of snapshots in album.
   *
   * @return number of snapshots
   */
  @Override
  public int getNumSnapshots() {
    return this.snapshotList.size();
  }


  /**
   * Return string representation of photo album.
   * @return  string representation
   */
  public String toString() {
    String s = "";

    for (String key: this.shapeList.keySet()) {
      s += "Name: " + key + "\n";
      s += this.shapeList.get(key).toString() + "\n";
    }
    return s;
  }
}

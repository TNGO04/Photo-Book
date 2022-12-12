package album.model;

import album.model.shapes.IShape;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

import java.util.Map;

/**
 * Interface IPhotoAlbum.
 */
public interface IPhotoAlbum {
  /**
   * Check if photo album is empty.
   * @return  true if album is empty
   */
  boolean isEmpty();

  /**
   * Return current shapes in album (as a snapshot).
   * @return  list of shapes
   */
  ISnapshot takeSnapshot(String description) throws IllegalArgumentException, InterruptedException;

  /**
   * Print all snapshots out.
   * @return  String representation of all snapshots taken before reset.
   */
  String printSnapshots();

  /**
   * Remove snapshot with the identifier.
   * @param id id of snapshot
   * @return  true if remove successful, false if not
   */
  boolean removeSnapshot(String id) throws IllegalArgumentException;

  /**
   * Return snapshot with ID.
   * @param id identifier of snapshot
   * @return  ISnapshot if found, null if not
   */
  ISnapshot getSnapshot(String id) throws IllegalArgumentException;

  /**
   * Get snapshot by index, starting at 0 for first snapshot.
   * @param index index of snapshot
   * @return  ISnapshot object
   * @throws IllegalArgumentException if index out of range
   */
  ISnapshot getSnapshot(int index) throws IllegalArgumentException;

  /**
   * Given an identifier, find the index.
   * @param id  identifier of snapshot
   * @return @return -1 if snapshot not found, index of snapshot in the LinkedHashMap if found
   * @throws IllegalArgumentException if id is null
   */
  int getSnapShotIndex(String id) ;

  /**
   * Getter for snapshot map.
   * @return  snapshot map
   */
  Map<String, ISnapshot> getAllSnapshots();

  /**
   * Check if album contains a shape with given identifier.
   * @param id  identifier of shape to be checked
   * @return  true if yes, false if no
   */
  boolean containsShape(String id);

  /**
   * Add a new shape into the album, along with identifier.
   * @param id  identifier
   * @param shape shape to be added
   * @return  true if added successfully, false if identifier already taken or shape is null
   */
  boolean addShape(String id, IShape shape);

  /**
   * Remove a shap with given identifier from album.
   * @param id  identifier of shape
   * @return  true if successfully remove, false if shape with identifier not found
   */
  boolean removeShape(String id);

  /**
   * Return shape with ID.
   * @param id  identifier of shape
   * @return  IShape object or null if shape not found
   */
  IShape getShape(String id);

  /**
   * Move shape.
   * @param id  identifier of shape to be moved
   * @param newPoint  new coordinate point
   * @return  true if move successful, false if shape with ID not found
   * @throws IllegalArgumentException if null object was passed in as input
   */
  boolean moveShape(String id, Point2D newPoint) throws IllegalArgumentException;

  /**
   * Change color of a shape.
   * @param id  identifier of shape
   * @param color color to be changed to
   * @return  true if successfully change color, false if identifier not found
   * @throws IllegalArgumentException if Color is null
   */
  boolean changeShapeColor(String id, Color color) throws IllegalArgumentException;

  /**
   * Change the x-dimension (width, x-radius, etc.) of shape
   * @param id  identifier of shape
   * @param dimension dimension to be changed to
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  boolean changeShapeXDimension(String id, double dimension) throws IllegalArgumentException;

  /**
   * Change the y-dimension (height, y-radius, etc.) of shape
   * @param id  identifier of shape
   * @param dimension dimension to be changed to
   * @return true if successfully change dimension, false if identifier not found
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  boolean changeShapeYDimension(String id, double dimension) throws IllegalArgumentException;

  /**
   * Scale shape.
   * @param id  identifier of shape
   * @param scaleFactor factor to scale shape by
   * @return  true if successfully scale shape, false if identifier not found
   * @throws IllegalArgumentException if scale factor is invalid (non-positive)
   */
  boolean scaleShape(String id, double scaleFactor) throws IllegalArgumentException;

  /**
   * Reset snapshot histories.
   */
  void reset();

  /**
   * Clear all shapes from album.
   */
  void clearShapes();

  /**
   * Get number of shapes in album.
   * @return  number of shapes
   */
  int getNumShapes();

  /**
   * Get number of snapshots in album.
   * @return  number of snapshots
   */
  int getNumSnapshots();

  /**
   * Return String that describes album.
   * @return  string representation
   */
  String toString();

}

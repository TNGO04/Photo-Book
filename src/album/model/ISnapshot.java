package album.model;

import album.model.shapes.IShape;

import java.util.Map;

/**
 * Interface ISnapshot.
 */
public interface ISnapshot {

  /**
   * Get ID.
   * @return id
   */
  String getID();

  /**
   * Get timestamp of snapshot.
   * @return timestamp
   */
  String getTimestamp();

  /**
   * Get description of snapshot.
   * @return  description
   */
  String getDescription();

  /**
   * Get list of shapes in snapshot.
   * @return collection of shapes in snapshot.
   */
  Map<String, IShape> getShapes();

  /**
   * Get string representation.
   * @return string.
   */
  String toString();


}

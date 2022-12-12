package album.model.shapes;

import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

/**
 * Interface IShape.
 */
public interface IShape {
  /**
   * Get coordinate of shape as Point2D object.
   * @return  Point2D object representing coordinate of shape
   */
  Point2D getCoordinate();

  /**
   * Get color of shape as Color object.
   * @return Color object representing color of shape.
   */
  Color getColor();

  /**
   * Move coordinate point of shape to a new coordinate.
   * @param coordinate  new coordinate object
   * @throws IllegalArgumentException if coordinate is null
   */
  void move(Point2D coordinate) throws IllegalArgumentException;

  /**
   * Change color of shape.
   * @param color color to be changed to
   * @throws IllegalArgumentException if color is null
   */
  void changeColor(Color color) throws IllegalArgumentException;

  /**
   * Get X dimension.
   * @return X dimension
   */
  double getXDimension();

  /**
   * Get Y dimension.
   * @return Y dimension
   */
  double getYDimension();

  /**
   * Change x dimension of shape.
   * @param xDimension  new x dimension
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  void changeXDimension(double xDimension) throws IllegalArgumentException;

  /**
   * Change y dimension of shape.
   * @param yDimension  new x dimension
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  void changeYDimension(double yDimension) throws IllegalArgumentException;

  /**
   * Scale shape by a factor.
   * @param scaleFactor scale factor
   * @throws IllegalArgumentException if scale factor is non-positive.
   */
  void scaleShape(double scaleFactor) throws IllegalArgumentException;

  /**
   * Return a string representation of shape.
   * @return  string representation
   */
  String toString();

  /**
   * Check if another object is equal.
   * @return true if equal, false if not
   */
  boolean equals(Object other);

  /**
   * Return a copy of IShape.
   * @return  copy of IShape
   */
  IShape getCopy();
}


package album.model.shapes;

import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

/**
 * Abstract class AbstractShape implements interface IShape.
 */
public abstract class AbstractShape implements IShape {
  protected Color color;
  protected Point2D coordinatePoint;

  /**
   * Constructor.
   * @param color color of shape
   * @param coordinatePoint coordinate point of shape
   * @param xDimension  x dimension of shape (width, x-radius, base)
   * @param yDimension  y dimension of shape (height, y-radius)
   * @throws IllegalArgumentException if objects are null
   */
  public AbstractShape(Color color, Point2D coordinatePoint, double xDimension,
                       double yDimension) throws IllegalArgumentException {
    if ((color == null) || (coordinatePoint == null)) {
      throw new IllegalArgumentException("Objects cannot be null.");
    }

    this.color = color;
    this.coordinatePoint = coordinatePoint;
  }


  /**
   * Get coordinate of shape as Point2D object.
   * @return  Point2D object representing coordinate of shape
   */
  @Override
  public Point2D getCoordinate() {
    return this.coordinatePoint;
  }

  /**
   * Get color of shape as Color object.
   * @return Color object representing color of shape.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Move coordinate point of shape to a new coordinate.
   *
   * @param coordinate new coordinate object
   * @throws IllegalArgumentException if coordinate is null
   */
  @Override
  public void move(Point2D coordinate) throws IllegalArgumentException {
    if (coordinate == null) {
      throw new IllegalArgumentException("Coordinate point object cannot be null.");
    }

    this.coordinatePoint = coordinate;
  }

  /**
   * Change color of shape.
   *
   * @param color color to be changed to
   * @throws IllegalArgumentException if color is null
   */
  @Override
  public void changeColor(Color color) throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("Color object cannot be null");
    }

    this.color = color;
  }
}

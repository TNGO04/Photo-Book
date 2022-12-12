package album.model.shapes;

import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

/**
 * Public class Triangle inherits from AbstractShape.
 */
public class Triangle extends AbstractShape {
  private double base;
  private double height;
  /**
   * Constructor.
   *
   * @param color           color of shape
   * @param coordinatePoint coordinate point of shape
   * @param xDimension      x dimension of shape (base)
   * @param yDimension      y dimension of shape (height)
   * @throws IllegalArgumentException if dimension is not positive
   */

  public Triangle(Color color, Point2D coordinatePoint, double xDimension, double yDimension)
          throws IllegalArgumentException {
    super(color, coordinatePoint, xDimension, yDimension);

    if ((xDimension <= 0) || (yDimension <= 0)) {
      throw new IllegalArgumentException("Dimensions have to be positive.");
    }

    this.base = xDimension;
    this.height = yDimension;
  }


  /**
   * Get X dimension.
   *
   * @return X dimension
   */
  @Override
  public double getXDimension() {
    return this.base;
  }

  /**
   * Get Y dimension.
   *
   * @return Y dimension
   */
  @Override
  public double getYDimension() {
    return this.height;
  }

  /**
   * Change x dimension of shape.
   *
   * @param xDimension new x dimension
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  @Override
  public void changeXDimension(double xDimension) throws IllegalArgumentException {
    if (xDimension <= 0) {
      throw new IllegalArgumentException("Dimension has to be positive");
    }

    this.base = xDimension;
  }

  /**
   * Change y dimension of shape.
   *
   * @param yDimension new y dimension
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  @Override
  public void changeYDimension(double yDimension) throws IllegalArgumentException {
    if (yDimension <= 0) {
      throw new IllegalArgumentException("Dimension has to be positive");
    }

    this.height = yDimension;
  }

  /**
   * Scale shape by a factor.
   *
   * @param scaleFactor scale factor
   * @throws IllegalArgumentException if scale factor is non-positive.
   */
  @Override
  public void scaleShape(double scaleFactor) throws IllegalArgumentException {
    if (scaleFactor <= 0) {
      throw new IllegalArgumentException("Scale factor has to be positive");
    }
    this.height *= scaleFactor;
    this.base *= scaleFactor;
  }

  /**
   * String representation of shape.
   * @return string
   */
  @Override
  public String toString() {
    String s = "";

    s += "Type: rectangle\n";
    s += "Min corner: " + this.coordinatePoint.toString();
    s += ", Base: " + this.base + ", Height: " + this.height;
    s += ", Color: " + this.color.toString();

    return s;
  }

  /**
   * Check if another object is equal.
   *
   * @return true if equal, false if not
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (other.getClass() == Triangle.class) {
      Triangle other2 = (Triangle) other;

      if (other2.toString().equals(this.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return a copy of IShape.
   *
   * @return copy of IShape
   */
  @Override
  public IShape getCopy() {
    return new Triangle(this.color.copy(), this.coordinatePoint.copy(), this.base, this.height);
  }
}

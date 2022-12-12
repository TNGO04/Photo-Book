package album.model.shapes;

import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

/**
 * Public class Rectangle inherits from AbstractShape.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  /**
   * Constructor.
   *
   * @param color           color of shape
   * @param coordinatePoint coordinate point of shape
   * @param xDimension      x dimension of shape (width)
   * @param yDimension      y dimension of shape (height)
   * @throws IllegalArgumentException if dimensions are not positive
   */
  public Rectangle(Color color, Point2D coordinatePoint, double xDimension, double yDimension)
          throws IllegalArgumentException {
    super(color, coordinatePoint, xDimension, yDimension);

    if ((xDimension <= 0) || (yDimension <= 0)) {
      throw new IllegalArgumentException("Dimensions have to be positive.");
    }

    this.width = xDimension;
    this.height = yDimension;
  }


  /**
   * Get X dimension.
   *
   * @return X dimension
   */
  @Override
  public double getXDimension() {
    return this.width;
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

    this.width = xDimension;
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
    this.width *= scaleFactor;
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
    s += ", Width: " + this.width + ", Height: " + this.height;
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

    if (other.getClass() == Rectangle.class) {
      Rectangle other2 = (Rectangle) other;

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
    return new Rectangle(this.color.copy(), this.coordinatePoint.copy(), this.width, this.height);
  }
}

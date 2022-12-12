package album.model.shapes;

import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

/**
 * Public class Oval.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;

  /**
   * Constructor.
   *
   * @param color           color of shape
   * @param coordinatePoint coordinate point of shape
   * @param xDimension      x dimension of shape (x-radius)
   * @param yDimension      y dimension of shape (y-radius)
   * @throws IllegalArgumentException if dimensions are not positive
   */

  public Oval(Color color, Point2D coordinatePoint, double xDimension, double yDimension)
          throws IllegalArgumentException {
    super(color, coordinatePoint, xDimension, yDimension);

    if ((xDimension <= 0) || (yDimension <= 0)) {
      throw new IllegalArgumentException("Dimensions have to be positive.");
    }

    this.xRadius = xDimension;
    this.yRadius = yDimension;
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

    this.xRadius = xDimension;
  }

  /**
   * Change y dimension of shape.
   *
   * @param yDimension new x dimension
   * @throws IllegalArgumentException if dimension is invalid (non-positive)
   */
  @Override
  public void changeYDimension(double yDimension) throws IllegalArgumentException {
    if (yDimension <= 0) {
      throw new IllegalArgumentException("Dimension has to be positive");
    }

    this.yRadius = yDimension;
  }

  /**
   * Get X Dimension.
   * @return x radius
   */
  @Override
  public double getXDimension() {
    return this.xRadius;
  }

  /**
   * Get Y Dimension.
   * @return y radius
   */
  @Override
  public double getYDimension() {
    return this.yRadius;
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
    this.yRadius *= scaleFactor;
    this.xRadius *= scaleFactor;
  }

  /**
   * String representation of shape.
   * @return string
   */
  @Override
  public String toString() {
    String s = "";

    s += "Type: oval\n";
    s += "Center: " + this.coordinatePoint.toString();
    s += ", X radius: " + this.xRadius + ", Y radius: " + this.yRadius;
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

    if (other.getClass() == Oval.class) {
      Oval other2 = (Oval) other;

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
    return new Oval(this.color.copy(), this.coordinatePoint.copy(), this.xRadius, this.yRadius);
  }


}

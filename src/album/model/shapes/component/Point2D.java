package album.model.shapes.component;

import java.text.DecimalFormat;

/**
 * Class for Point2D ( a coordinate).
 */
public class Point2D {
  private double x;
  private double y;
  /**
   * Constructor.
   * @param x x-coordinate
   * @param y y-coordinate
   */

  public Point2D(double x, double y) throws IllegalArgumentException {
    if ((x < 0) || (y < 0)) {
      throw new IllegalArgumentException("Coordinates have to be positive");
    }

    this.x = x;
    this.y = y;
  }

  /**
   * Return x.
   * @return  x
   */
  public double getX() {
    return x;
  }

  /**
   * Return y.
   * @return y
   */
  public double getY() {
    return y;
  }

  /**
   * Setter for x.
   * @param x x-coordinate to be set to
   */
  public void setX(double x) {
    if (x < 0) {
      throw new IllegalArgumentException("Coordinates have to be positive");
    }
    this.x = x;
  }

  /**
   * Setter for y.
   * @param y y-coordinate to be set to
   */
  public void setY(double y) {
    if (y < 0) {
      throw new IllegalArgumentException("Coordinates have to be positive");
    }
    this.y = y;
  }

  /**
   * Return string representation.
   * @return string
   */
  @Override
  public String toString() {
    DecimalFormat formatter = new DecimalFormat("0.0");
    return "(" + formatter.format(this.getX()) + ", " + formatter.format(this.getY()) + ")";
  }

  /**
   * Check if object is equal to another.
   * @param other other object to be checked
   * @return  true if equal, false if not
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }

    if (this == other) {
      return true;
    }

    if (other.getClass() == Point2D.class) {
      Point2D other2 = (Point2D) other;

      if (other2.toString().equals(this.toString())) {
        return true;
      }
    }
    return false;
  }


  /**
   * Return a copy of this Point2D.
   * @return copy
   */
  public Point2D copy() {
    return new Point2D(this.getX(), this.getY());
  }
}





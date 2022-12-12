package album.model.shapes.component;

import java.text.DecimalFormat;

/**
 * Public class for Color.
 */
public class Color {
  private double red;
  private double green;
  private double blue;

  private static final double COLOR_MAX = 1.0;

  /**
   * Constructor.
   * @param red red value
   * @param blue  blue value
   * @param green green value
   * @throws IllegalArgumentException if color value are negative
   */
  public Color(double red, double blue, double green) throws IllegalArgumentException {
    if ((red < 0) || (blue < 0) || (green < 0)) {
      throw new IllegalArgumentException("RGB value cannot be negative");
    }

    if ((red > COLOR_MAX) || (blue > COLOR_MAX) || (green > COLOR_MAX)) {
      throw new IllegalArgumentException("RGB value out of bounds");
    }

    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  /**
   * Getter for red value.
   * @return  red value
   */
  public double getRed() {
    return red;
  }

  /**
   * Getter for green value.
   * @return green value
   */
  public double getGreen() {
    return green;
  }

  /**
   * Getter for blue value.
   * @return blue value
   */
  public double getBlue() {
    return blue;
  }

  /**
   * Setter for red value.
   * @param red new red value
   */
  public void setRed(double red) throws IllegalArgumentException {
    if ((red < 0) || (red > COLOR_MAX)) {
      throw new IllegalArgumentException("Color value invalid.");
    }

    this.red = red;
  }

  /**
   * Setter for green value.
   * @param green new green value
   */
  public void setGreen(double green) throws IllegalArgumentException {
    if ((green < 0) || (green > COLOR_MAX)) {
      throw new IllegalArgumentException("Color value invalid.");
    }
    this.green = green;
  }

  /**
   * Setter for blue value.
   * @param blue new blue value
   */
  public void setBlue(double blue) throws IllegalArgumentException {
    if ((blue < 0) || (blue > COLOR_MAX)) {
      throw new IllegalArgumentException("Color value invalid.");
    }
    this.blue = blue;
  }

  /**
   * Return string representation.
   * @return string
   */
  @Override
  public String toString() {
    DecimalFormat formatter = new DecimalFormat("0.0");
    return "(" + formatter.format(this.getRed()) + ", " + formatter.format(this.getBlue())
            + ", " + formatter.format(this.getGreen()) + ")";
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

    if (other.getClass() == Color.class) {
      Color other2 = (Color) other;

      if (other2.toString().equals(this.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return a copy of this color.
   * @return copy Color object
   */
  public Color copy() {
    return new Color(this.getRed(), this.getBlue(), this.getGreen());
  }
}

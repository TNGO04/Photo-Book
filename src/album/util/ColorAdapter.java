package album.util;

/**
 * Adapter class for Model Color object to other Color objects suitable for view.
 */
public class ColorAdapter {
  private static final int RGB_COLOR_SCALE = 255;

  /**
   * Static method to adapt Color object from Model to Color object from Java Swing library.
   * @param color Model Color object
   * @return  swing color object
   */
  public static java.awt.Color toSwingFromModel(album.model.shapes.component.Color color) {
    int red = convertColorValue(color.getRed());
    int blue = convertColorValue(color.getBlue());
    int green = convertColorValue(color.getGreen());
    java.awt.Color swingColor = new java.awt.Color(red, green, blue);
    return swingColor;
  }

  /**
   * Convert Color object from Model to SVG text mark-up.
   * @param color Color object
   * @return  text mark-up for SVG color
   */
  public static String toMarkupFromModel(album.model.shapes.component.Color color) {
    int red = convertColorValue(color.getRed());
    int blue = convertColorValue(color.getBlue());
    int green = convertColorValue(color.getGreen());

    return "rgb(" + red + "," + green + "," + blue + ")";
  }

  /**
   * Convert fraction color value to 0-255 scale.
   * @param fractionalValue fractional value
   * @return  0-255 integer color value
   */
  private static int convertColorValue(double fractionalValue) {
    return (int) (fractionalValue * RGB_COLOR_SCALE);
  }
}

package album.util;

import album.model.shapes.IShape;
import album.model.shapes.Oval;
import album.model.shapes.Rectangle;
import album.model.shapes.Triangle;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

/**
 * Factory class to create IShape based on instructions.
 */
public class IShapeFactory {
  /**
   * Create and return IShape.
   * @param type  shape type
   * @param colorRed  red color value
   * @param colorGreen  green value
   * @param colorBlue blue value
   * @param xPosition x position
   * @param yPosition y position
   * @param xDimension  x dimension
   * @param yDimension  y dimension
   * @return  IShape object
   */
  public static IShape create(String type, double colorRed, double colorGreen, double colorBlue,
                       double xPosition, double yPosition, double xDimension, double yDimension) {
    type = type.toLowerCase();

    Color color = new Color(colorRed, colorBlue, colorGreen);
    Point2D coordinate = new Point2D(xPosition, yPosition);

    switch (type) {
      case "oval":
        return new Oval(color, coordinate, xDimension, yDimension);
      case "rectangle":
        return new Rectangle(color, coordinate, xDimension, yDimension);
      case "triangle":
        return new Triangle(color, coordinate, xDimension, yDimension);
      default:
        return null;
    }
  }
}

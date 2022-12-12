package album.util;

import album.model.shapes.IShape;
import album.model.shapes.Oval;
import album.model.shapes.Rectangle;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Convert IShape to other shape object.
 */
public class ShapeAdapter {

  /**
   * Convert IShape object to swing Shape object.
   * @param ishape  Ishape object
   * @return  swing Shape object
   */
  public static Shape IShapeToSwingShape(IShape ishape) {
    double xPosition = ishape.getCoordinate().getX();
    double yPosition = ishape.getCoordinate().getY();
    double xDimension = ishape.getXDimension();
    double yDimension = ishape.getYDimension();

    // get class of IShape object and return swing Shape
    if (ishape.getClass() == Rectangle.class) {
      return new Rectangle2D.Double(xPosition, yPosition, xDimension, yDimension);
    }
    else if (ishape.getClass() == Oval.class) {
      return new Ellipse2D.Double(xPosition, yPosition, xDimension, yDimension);
    }
    else return null;
  }

  /**
   * Convert IShape object to SVG mark-up.
   * @param shape IShape object
   * @return  text markup for SVG
   */
  public static String IShapeToSVGMarkup(IShape shape) {
    String s = "";

    double xPosition = shape.getCoordinate().getX();
    double yPosition = shape.getCoordinate().getY();
    double xDimension = shape.getXDimension();
    double yDimension = shape.getYDimension();

    String color = ColorAdapter.toMarkupFromModel(shape.getColor());

    if (shape.getClass() == Rectangle.class) {
      s +=   "<rect x=\"" + xPosition
              + "\" y=\"" + yPosition
              + "\" width=\"" + xDimension
              + "\" height=\"" + yDimension
              + "\" fill=\"" + color
              + "\" />\n";
    }
    else if (shape.getClass() == Oval.class) {
      s += "<ellipse cx=\"" + xPosition
              + "\" cy=\"" + yPosition
              + "\" rx=\"" + xDimension
              + "\" ry=\"" + yDimension
              + "\" fill=\"" + color
              + "\" />\n";
    }
    else {
      System.out.println("Shape not supported");
      System.exit(0);
    }
    return s;
  }
}

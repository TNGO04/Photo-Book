package album.view;

import album.model.shapes.IShape;
import album.util.ColorAdapter;
import album.util.ShapeAdapter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Collection;
import javax.swing.JPanel;

/**
 * DrawingPanel to display shapes in snapshot, extend from JPanel superclass.
 */
public class DrawingPanel extends JPanel {
  private Collection<IShape> shapes;

  /**
   * Add shapes to a drawing panel.
   * @param shapes  collection of shapes to be added
   * @throws IllegalArgumentException if input is null
   */
  public void addShapes(Collection<IShape> shapes) throws IllegalArgumentException {
    if (shapes == null) {
      throw new IllegalArgumentException("Shape collection is null.");
    }
    this.shapes = shapes;
  }

  /**
   * Override painComponent to paint shapes.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    for (IShape shape: this.shapes) {

      // convert IShape and Color to Shape and Color compatible with Swing
      java.awt.Color swingColor = ColorAdapter.toSwingFromModel(shape.getColor());
      Shape newShape = ShapeAdapter.IShapeToSwingShape(shape);

      if (newShape == null) {
        throw new IllegalArgumentException("Shape object not valid");
      }
      g2.setColor(swingColor);
      g2.fill(newShape);
    }
  }

}

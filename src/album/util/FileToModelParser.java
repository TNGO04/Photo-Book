package album.util;

import album.model.IPhotoAlbum;
import album.model.shapes.IShape;
import album.model.shapes.component.Color;
import album.model.shapes.component.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Private class implementing FileParser interface to parse content from file to change a model.
 */
public class FileToModelParser implements FileParser {
  private static final int FILE_COLOR_SCALE = 255;
  private static final int LENGTH_CREATE_COMMAND = 10;
  private static final int LENGTH_MOVE_COMMAND = 4;
  private static final int LENGTH_RESIZE_COMMAND = 4;
  private static final int LENGTH_COLOR_COMMAND = 5;
  private static final int LENGTH_REMOVE_COMMAND = 2;

  private IPhotoAlbum model;
  private File file;
  private int currentLine;


  /**
   * Constructor.
   *
   * @param model model to be built
   * @param file  instruction file
   * @throws IllegalArgumentException if input object is null
   * @throws FileNotFoundException    if instruction file not found
   */
  public FileToModelParser(IPhotoAlbum model, File file)
          throws IllegalArgumentException, FileNotFoundException {
    if ((model == null) || (file == null)) {
      throw new IllegalArgumentException("Input object cannot be null.");
    }

    this.model = model;
    this.file = file;
    currentLine = 0;
  }

  /**
   * Parse file.
   */
  public void parse() throws FileNotFoundException, InterruptedException {
    Scanner fileScan = new Scanner(this.file);
    String line;
    while (fileScan.hasNext()) {
      currentLine++;
      // get next line and trim any leading or trailing spaces
      line = fileScan.nextLine();
      line = line.trim();

      //ignore comment lines
      if (line.startsWith("#")) {
        continue;
      } else {
        String[] elementList = line.split("\\s+");

        if (elementList.length == 0) {
          continue;
        }

        // depending on command, perform action
        switch (elementList[0].toLowerCase()) {
          case "shape":
            this.addShapeToModel(elementList);
            break;
          case "move":
            this.moveModelShape(elementList);
            break;
          case "resize":
            this.resizeModelShape(elementList);
            break;
          case "color":
            this.changeColorModelShape(elementList);
            break;
          case "remove":
            this.removeShapeModel(elementList);
            break;
          case "snapshot":
            this.takeModelSnapshot(elementList);
            break;
        }
      }
    }
  }

  /**
   * Take snapshot.
   *
   * @param instruction array representing arguments in instruction.
   */
  private void takeModelSnapshot(String[] instruction) throws InterruptedException {
    if (instruction.length >= 2) {
      String description = "";

      // get description by concatenating arguments and trimming
      for (int i = 1; i < instruction.length; i++) {
        description += instruction[i] + " ";
      }
      description = description.trim();
      this.model.takeSnapshot(description);
    } else {
      this.model.takeSnapshot("");
    }
  }

  /**
   * Remove shape from model.
   *
   * @param instruction array representing arguments in instruction.
   */
  private void removeShapeModel(String[] instruction) {
    if (instruction.length < LENGTH_REMOVE_COMMAND) {
      System.out.println("Line " + this.currentLine + " skipped due to invalid syntax");
      return;
    }
    String id = instruction[1];
    if (!model.containsShape(id)) {
      System.out.println("Line " + this.currentLine + " skipped because shape is not found");
    }
    ;
    model.removeShape(id);
  }

  /**
   * Change color of a shape from model.
   *
   * @param instruction array representing arguments in instruction.
   */
  private void changeColorModelShape(String[] instruction) {
    if (instruction.length < LENGTH_COLOR_COMMAND) {
      System.out.println("Line " + this.currentLine + " skipped due to invalid syntax");
      return;
    }

    String id = instruction[1];
    if (!model.containsShape(id)) {
      System.out.println("Line " + this.currentLine + " skipped because shape is not found");
    }
    ;

    double red = convertColor(Integer.parseInt(instruction[2]));
    double green = convertColor(Integer.parseInt(instruction[3]));
    double blue = convertColor(Integer.parseInt(instruction[4]));

    model.changeShapeColor(id, new Color(red, green, blue));
  }

  /**
   * Resize shape from model.
   *
   * @param instruction array representing arguments in instruction.
   */
  private void resizeModelShape(String[] instruction) {
    if (instruction.length < LENGTH_RESIZE_COMMAND) {
      System.out.println("Line " + this.currentLine + " skipped due to invalid syntax");
      return;
    }

    String id = instruction[1];
    if (!model.containsShape(id)) {
      System.out.println("Line " + this.currentLine + " skipped because shape is not found");
    }
    ;

    double yDimension = Double.parseDouble(instruction[3]);
    double xDimension = Double.parseDouble(instruction[2]);

    model.changeShapeXDimension(id, xDimension);
    model.changeShapeYDimension(id, yDimension);
  }

  /**
   * Move shape from model.
   *
   * @param instruction array representing arguments in instruction.
   */
  private void moveModelShape(String[] instruction) {
    if (instruction.length < LENGTH_MOVE_COMMAND) {
      System.out.println("Line " + this.currentLine + " skipped due to invalid syntax");
      return;
    }
    String id = instruction[1];
    if (!model.containsShape(id)) {
      System.out.println("Line " + this.currentLine + " skipped because shape is not found");
    }
    ;

    double xPosition = Double.parseDouble(instruction[2]);
    double yPosition = Double.parseDouble(instruction[3]);

    model.moveShape(id, new Point2D(xPosition, yPosition));
  }

  /**
   * Add shape to model.
   *
   * @param instruction array representing arguments in instruction.
   */
  private void addShapeToModel(String[] instruction) {
    if (instruction.length < LENGTH_CREATE_COMMAND) {
      System.out.println("Line " + this.currentLine + " skipped due to invalid syntax");
      return;
    }

    // get arguments for creating shape
    String id = instruction[1];
    if (model.containsShape(id)) {
      System.out.println("Line " + this.currentLine
              + " skipped because shape with similar identifier already exists");
    }
    ;

    String type = instruction[2];
    double xPosition = Double.parseDouble(instruction[3]);
    double yPosition = Double.parseDouble(instruction[4]);
    double xDimension = Double.parseDouble(instruction[5]);
    double yDimension = Double.parseDouble(instruction[6]);

    double red = convertColor(Integer.parseInt(instruction[7]));
    double green = convertColor(Integer.parseInt(instruction[8]));
    double blue = convertColor(Integer.parseInt(instruction[9]));

    IShape shape = IShapeFactory.create(
            type, red, green, blue, xPosition, yPosition, xDimension, yDimension);
    model.addShape(id, shape);
  }

  /**
   * Convert color from 0-255 scale (from file) to 0-1 fractional scale (for model).
   *
   * @param fileColorValue color value from file
   * @return fractional color value
   */
  private double convertColor(int fileColorValue) {
    return (double) fileColorValue / FILE_COLOR_SCALE;
  }
}
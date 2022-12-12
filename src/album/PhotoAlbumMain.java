package album;

import album.controller.IPhotoAlbumController;
import album.controller.PhotoAlbumController;
import album.model.IPhotoAlbum;
import album.model.PhotoAlbumImpl;
import album.view.IPhotoAlbumView;
import album.view.PhotoAlbumGraphicalView;
import album.view.PhotoAlbumWebView;


/**
 * Main class for application.
 */
public class PhotoAlbumMain {
  /**
   * Main method for application.
   * @param args  arguments
   * @throws Exception  if any exception thrown
   */
  public static void main(String[] args) throws Exception {
    // command line parsing
    // if no arguments are given, exit program

    if (args.length == 0) {
      System.err.println(
              "Usage: -in command-file -view type-of-view [-out output] [xmax] [ymax]");
      System.exit(1);
    }

    IPhotoAlbumView view = null;
    String instructionFilename = "";
    String viewOutputFile = "";
    String viewType = "";
    int xMax = 1000;
    int yMax = 1000;

    // parse command line through each argument
    int i = 0;
    while ((i < args.length) && ((i + 1) < args.length)) {
      switch (args[i]) {
        case "-in":
          instructionFilename = args[i + 1];
          i += 2;
          break;

        case "-view":
        case "-v":
          if (args[i + 1].equalsIgnoreCase("graphical")) {
            viewType = "graphical";
          }
          else if (args[i + 1].equalsIgnoreCase("web")) {
            viewType = "web";
          }
          else {
            System.err.println("View tag can only be 'graphical' or 'web'.");
            System.exit(1);
          }
          i += 2;
          break;

        case "-out":
          viewOutputFile = args[i + 1];
          i += 2;
          break;

        // if tags do not match previous, check if tag is number and parse out xMax and yMax
        default:
          if (args[i].matches("\\d+")) {
            if ((args[i + 1].matches("\\d+"))) {
              xMax = Integer.parseInt(args[i]);
              yMax = Integer.parseInt(args[i + 1]);
              i += 2;
              break;
            }
            else {
              System.err.println("Follow xMax argument by yMax argument.");
              System.exit(1);
            }
          }
          else {
            System.err.println(
                    "Tag or input not recognized.");
            System.exit(1);
          }
      }
    }

    // ensure that all required inputs (view type and instruction input) are parsed
    if (instructionFilename == "") {
      System.err.println("Please specify command instruction file.");
      System.exit(1);
    }
    if (viewType == "") {
      System.err.println("Please specify view.");
      System.exit(1);
    }
    else {
      if (viewType == "web") {
        if (viewOutputFile == "") {
          System.err.println("Please specify web view output file.");
          System.exit(1);
        }
        else {
          view = new PhotoAlbumWebView(viewOutputFile, xMax, yMax);
        }
      }
      else {
        view = new PhotoAlbumGraphicalView(xMax, yMax);
      }
    }

    // piping for MVC architecture
    IPhotoAlbum model = new PhotoAlbumImpl();
    IPhotoAlbumController controller = new PhotoAlbumController(model, view, instructionFilename);

    controller.execute();
    controller.setView();
  }
}



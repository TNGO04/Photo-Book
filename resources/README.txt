HW7: 
In this project, I implemented the model for a Photo Album application using the MVC architecture. 

The Photo Album model is accessed through the public interface IPhotoAlbum, which is implemented by the PhotoAlbumImpl class. This interface has useful methods for adding, removing shapes and changing shape attributes. It also stores snapshots and has methods for taking, removing, and getting snapshots.

Each snapshot is a snapshot of the Photo Album at a current time. The snapshot is identified by an ID which is tied to when it was taken, and each snapshot contains an optional description and a copy of the list of shapes in the album at the time it was taken. 

Snapshots are accessed using the ISnapshot interface, and implemented in this project by the SnapshotImpl concrete class. This class has attributes that store data of the snapshots, and methods to print to string and access attributes of snapshots, such as identifier, timestamp, and description. The snapshots are stored in the PhotoAlbumImpl class using a LinkedHashMap of ISnapshot objects. I chose to use the Map interface to represent the collection of snapshots because Map allows for easy look-up of snapshots using identifier, instead of having to iterate over a list to find a snapshot with a specific identifier.

Each photo album has zero or more shapes that can be transformed. The shapes within the photo album is sotred within a LinkedHashMap with shape name as key and the IShape instance as value. Storing the shapes in the map allows for quick look-up using name (their unique identifier). The PhotoAlbumImpl class has several methods (such as changeColor()) that can be used to change attributes of shapes stored in the album.

Shapes are accessed through the IShape interface, which is implemented by the abstract class AbstractShape. The IShape interface has methods for changing the attributes of shapes (color, dimensions, coordinates) and getting the values for these attributes. These methods allow the client to perform transformation (moving, re-coloring, scaling, re-sizing) on the shapes. 

AbstractShape is a parent class, with classes such as Oval, Rectangle, and Triangle as children concrete classes that inherit attributes and methods relating to Color and Point2D coordinates. This is because these 2 attributes are common to all shape-related subclasses, so by introducing inheritance, the project can reuse codes regarding Color and Point2D coordinates across the shapes. 

The X and Y dimension (size) attributes are implemented by the subclasses, since each shape has different type of x and y dimension (for example for x dimension, oval has x radius, rectangle has width, and triangle has base). The X and Y dimension attributes are moved into the subclasses to make this distinction and allow for potential expansion in the future where their type-specific properties might be needed. In addition, each subclass implements some helper methods such as toString(), equals(), and getCopy() which are specific to the subtype. 

For Color and Point2D coordinates, I utilise compostion and implement them as their own classes. This allows me to package their data and ensure encapsulation, so that the shape classes do not need to manage data pertaining to color or coordinates. 

The Color class has 3 attributes for the R, G, and B values (represented as a fraction between 0.0 and 1.0), as well as a toString method and getter and setter methods for the color values.

The Point2D class can be used by shape classes to represent coordinates of shapes. The Point2D class has two attributes for x and y-coordinate, as well as setters and getters for them.  


HW8:

Refactoring:
For Color and Point2D class, I add a DecimalFormatter object to their toString() method to make sure to display to string only 1 decimal digit. In addition, I add checks in the Point2D class to make sure coordinate cannot be less than 0, since the view only take coordinates larger than (0, 0). 

For IPhotoAlbum interface, I add one new method, getAllSnapshots() to return a Map of the ISnapshot instances owned by the Photo Album, as well as their identifiers. For the takeSnapshot method, I add a 1 milisecond wait to make sure no two snapshots are created with the same timestamp (and same identifier). 

For the ISnapshot interface, I add a getShapes() method to return all the IShape objects owned by the ISnapshot object. 

MVC Application:

The MVC Application's main class is PhotoAlbumMain, which reads in command line arguments, create instances for Model, View, and Controller based on the input arguments, and call the Controller to execute the program and display the view. It essentially handles the piping of the Application. 

The Controller, View, and Model can only be accessed through public interfaces IPhotoAlbumController, IPhotoAlbumView, and IPhotoAlbum, respectively. This is to make sure we can hide their implementation from the clients.

The Controller for this application is accessed through the IPhotoAlbumController interface. It has two public methods: execute (which execute the controller and processes information to the model), and setView (which initializes and send information to View). In this project, concrete class PhotoAlbumControllerImpl implements the interface IPhotoAlbumController and also ActionListener (to listen for button clicks from the Graphical UI). It also has private methods to send update to Graphical components and navigate between different snapshots in album.

It uses concrete class FileToModelParser (which implements interface FileParser) to read the input files. FileToModelParser parses an instruction file and send commands/information to model to build the data within the Photo Album. FileToModelParse has private methods to send commands to model. 

FileToModelParse uses a utility class called IShapeFactory to create IShape instaces to be added to the model. IShapeFactory utilises the Factory design pattern, taking in the type of shape and relevant for object creation.

The Model is accessed through interface IPhotoAlbum and implemented by PhotoAlbumImpl. It is described in HW7's README.

The View is accessed through IPhotoAlbumView, and implemented by 2 concrete classes: PhotoAlbumWebView (for a static HTML display) and PhotoAlbumGraphicalView (for a dynamic Java Swing UI). It has public methods that allow the Controller to send information and send commands to the view. 

Both views utilises utility classes ColorAdapter and ShapeAdapter. This is because both views require different data types/inputs for their Shape and Color representation, as well as data representation. I use the Adapter design to convert IShape and Color objects from our model to the data format preferred by the web and Swing views. 


PhotoAlbumWebView implements a static HTML web page that display all snapshots subsequently, separated in my project by display boxes. It uses SVG to create the shapes object on a canvas. The class has private methods to generate the header, body, and SVG mark-ups that make up the web page. It implements all methods from the interface, but essentially only uses public method display() to create the output HTML file, since all the other public methods are for a dynamic view. If a file with the same output filename exists, it will exit without creating the output file. 

PhotoAlbumGraphicalView implements a dynamic UI through Java Swing. The main page for the UI is a JFrame with a scrolling panel. On the page, there are 4 JButton for navigating between snapshots and displaying details, a JTextArea for displaying details of snapshots. It has a DrawingPanel that displays the shape in a snapshot. It has public methods that allow the Controller to send information and commands that changes the view showned to the client. 

The Drawing Panel object that displays snapshots is of DrawingPanel class, a sub-class that inherits from super-class JPanel. It has an added method called addShapes that allow the view to add shapes from snapshots to the drawing panel. It overrides the paintComponent method, to make sure these shapes are drawn and displayed on the DrawingPanel whenever an interaction occurs or repaint() method is called. 

For testing the Controller, since we want to isolate the controller and only test for information transmission, I created a PhotoAlbumMock class that implements IPhotoAlbum, and a PhotoAlbumViewMock class that implements IPhotoAlbumView. These are only mock classes used for testing, so it has a log to log all function calls and inputs. It returns unique values passed through the constructor. PhotoAlbumMock keeps a list of the IDs of all shapes in the album, but aside from the above function it does not have the full functionality, unlike PhotoAlbumImpl. IPhotoAlbumControllerTest uses a PhotoAlbumMock instance and testFile.txt to test that the correct method calls to the model are made with the correct inputs. It also uses PhotoAlbumViewMock to test that correct inputs are sent to the mock view. 










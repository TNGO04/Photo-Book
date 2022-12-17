# Shape Album Application
Trang Ngo
OOD Course Final Project

## Overview
In this project, I created an application using the MVC framework to implement a photo album. The photo album consists of multiple snapshots, each of which were built using multiple simple shapes. Through the view, the user can view and navigate between pictures/snapshots within the album.

## Model
The model for this application is accessed through the public interface [IPhotoAlbum](src/album/model/IPhotoAlbum.java), which is implemented by concrete class [PhotoAlbumImpl](src/album/model/PhotoAlbumImpl.java). The model represents the state of the photo album at a given time, including the shapes and snapshots currently stored by the photo album.

![Model UML](https://user-images.githubusercontent.com/87917284/206939088-f5371b98-e460-48b1-baa4-d336cc5f978a.png)

### Shape
The class stores a list of current shapes making up a picture in `LinkedHashMap<String, IShape> shapeList`. I choose to store the shapes in a HashMap with their unique identifier as key for quick-lookup of shapes based in their ID. These shapes altogether make up an image represented by the model. 

Each shape is represented by the public interface [IShape](src/album/model/shapes/IShape.java), implemented by abstract class [AbstractShape](src/album/model/shapes/AbstractShape.java). The IShape interface has methods for changing the attributes of shapes (color, dimensions, coordinates) and getting the values for these attributes. These methods allow the client to perform transformation (moving, re-coloring, scaling, re-sizing) on the shapes. 

AbstractShape is a parent class, with classes such as [Oval](src/album/model/shapes/Oval.java), [Rectangle](src/album/model/shapes/Rectangle.java), and [Triangle](src/album/model/shapes/Triangle.java) as children concrete classes that inherit attributes and methods relating to Color and Point2D coordinates. Because these 2 attributes are common to all shape-related subclasses, by introducing inheritance, the project can reuse codes regarding Color and Point2D coordinates across the shapes. 

![Shape UML](https://user-images.githubusercontent.com/87917284/206940186-73a1bf0a-7bda-4ffc-a448-2c8aed1e5b9e.png)

The X and Y dimension (size) attributes are implemented by the subclasses, since each shape has different type of x and y dimension (for example for x dimension, oval has x radius, rectangle has width, and triangle has base). The X and Y dimension attributes are moved into the subclasses to make this distinction and allow for potential expansion in the future where their type-specific properties might be needed. In addition, each subclass implements some helper methods such as `toString()`, `equals()`, and `getCopy()` which are specific to the subtype. 

For [Color](src/album/model/Color.java) and [Point2D](src/album/model/shapes/Point2D.java) coordinates, I utilize the OOD concept of composition and implement them as their own classes. This allows me to package their data and ensure encapsulation, so that the shape classes do not need to manage data pertaining to color or coordinates. 

The Color class represents the color of a shape using R, G, and B values (represented as a fraction between 0.0 and 1.0). The Point2D class can be used by shape classes to represent coordinates/positions of shapes. The Point2D class has two attributes for x and y-coordinate. 


### Snapshot

To take a snapshot, users can call the method `takeSnapshots(String description)` from PhotoAlbumImpl. A snapshot is the representation of the model at the moment it was taken, stored by the Photo Album in `LinkedHashMap<String, ISnapshot> snapshotList`. 

Each snapshot is a "freeze frame" of the model state - think of it as a "system selfie" - the picture captured is that of the shapes in their then-current locations (and state) and is analogous to a "page" in a photo album. Each snapshot is identified by an ID which is tied to when it was taken, and each snapshot contains an optional description and a copy of the list of shapes in the album at the time it was taken. 

Snapshots are accessed using the [ISnapshot](src/album/model/ISnapshot.java) interface, and implemented in this project by the [SnapshotImpl](src/album/model/SnapshotImpl.java) concrete class. This class has attributes that store data of the snapshots, and methods to print to string and access attributes of snapshots, such as identifier, timestamp, and description.

![SnapShot UML](https://user-images.githubusercontent.com/87917284/206940250-00527c17-3601-4bff-850d-58100ed50312.png)

### Full Model UML 
![Full Model UML](https://user-images.githubusercontent.com/87917284/206940645-f16d7b92-cbc9-4d18-a5e7-1173bbdb24ae.png)


## View
Users can view the snapshots within a Photo Album and interact with the application through public interface [IPhotoAlbumView](src/album/view/IPhotoAlbumView.java). I implement two views in this project: a static web view through an HTML file ([PhotoAlbumWebView](src/album/view/PhotoAlbumWebView.java)) and a dynamic view using Java Swing ([PhotoAlbumGraphicalView](src/album/view/PhotoAlbumGraphicalView.java)).

The view interface has a `display(Collection<ISnapshot>)` method that outputs snapshot(s) to the view. It also has other public methods to allow the controller to interact with the dynamic views. The static view does not use and only implement the default for these methods. 

Utility classes [ColorAdapter](src/album/util/ColorAdapter.java) and [ShapeAdapter](src/album/util/ShapeAdapter.java) use the Adapter design pattern to convert Color  and IShape instances from the model to corresponding objects from Java Swing library or to HTML text markeup. 


![image](https://user-images.githubusercontent.com/87917284/208225761-d28ca25f-5b2e-4f5c-9e3c-6ffe3900871d.png)


### Web View

In the web view, all snapshots within the album are displayed in an HTML sequentially, delimited by container boxes. The view generates a proper HTML text file with embedded SVG (to render the images). Within each snapshot, the identifier, description, and the shapes (creating an image) are displayed. PhotoAlbumWebView classes implement public methods from IPhotoAlbumView interface, and private methods to generate HTML mark-up for the header and body. 

<img src="https://user-images.githubusercontent.com/87917284/208227306-a3a7261e-1b1f-4d65-b270-3c65dac7e851.png" height="330" width="auto" > <img src="https://user-images.githubusercontent.com/87917284/208227321-dc26e53b-553d-44b5-9be1-840f30af473e.png" height="330" width="auto" >



### Graphical View
In the graphical UI using Java Swing, I use Java Swing to build a custom UI to navigate between snapshots in the photo album. 

The [Drawing Panel](src/album/view/DrawingPanel.java) object that displays snapshots is of DrawingPanel class, a sub-class that inherits from super-class JPanel. This class adds a method called `addShapes(Collection<IShape>)` that allow the view to add shapes from snapshots to the drawing panel. It overrides the `paintComponent(Graphics)` method, to make sure these shapes are drawn and displayed on the DrawingPanel whenever an interaction occurs. 

In this view, the snapshot is drawn inside of a window, one snapshot at a time. There are interactive buttons that allow the user to:

+ View the snapshot information (unique id and description)
+ "page forward" and show the next snapshot if it exists. If no further snapshots exist, a message to the user should indicate that
+ "page backward" and show the previous snapshot, if there is a previous one, If there is no "previous" a message to the user should be shown
+ "jump" to a snapshot the user explicitly selects from a list of options




https://user-images.githubusercontent.com/87917284/208227822-0a9876bf-5c29-4bae-a79f-fcf82822e672.mp4






## Controller
Similar to the Model and View, the controller is accessed through public interface [IPhotoAlbumController](src/album/controller/IPhotoAlbumController.java), implemented by concrete class [PhotoAlbumControllerImpl](src/album/controller/PhotoAlbumControllerImpl.java). It has two public methods: `execute()` (which execute the controller and processes information to the model), and `setView()` (which initializes and send information to View). PhotoAlbumControllerImpl implements the interface IPhotoAlbumController and also ActionListener (to listen for button clicks from the Graphical UI). It also has private methods to send update to Graphical components and navigate between different snapshots in the dynamic UI View. 

![image](https://user-images.githubusercontent.com/87917284/208225521-8422ffbd-9ee9-4b8b-bacc-5f2bf05b35bd.png)

PhotoAlbumControllerImpl utilises an instance of [FileToModelParser](src/album/util/FileToModelParser.java) object, accessed through the [FileParser](src/album/util/FileParser.java) interface. FileToModelParser takes in the input File object and implement one public method `parse()` that parses information from the file. It has private methods that interact and populate the model with data, these methods are abstracted away through the use of Interface. 

To create IShape objects to populate the model, the Factory design pattern is utilized in static class [IShapeFactory](src/album/util/IShapeFactory.java). 

## Application
The entry point for the Application is [PhotoAlbumMain](src/album/PhotoAlbumMain.java). This class parses the command-line input and handles the application plumbing (creating instances for Model, View, and Controller). 

### How to Run
Run the JAR file [PhotoAlbum.jar](resources/PhotoAlbum.jar) with this command line argument:

```
java -jar PhotoAlbum.jar -in "name-of-command-file" -view "type-of-view" [-out "where-output-should-go"] [xmax] [ymax]
```

+ -in: input file containing instruction for building the PhotoAlbum model
+ -view: type of view, either `web` or `graphical`
+ -out: for web view, html file to be created to represent PhotoAlbum
+ [xmax] [ymax]: optional input describing the size of the viewing panel


## Unit Testing
JUnit tests for this application are included [here](test/).

### Mock Testing

Since I want to isolate testing the Model, View, and Controller, and only test for information transmission between these classes, I created a [PhotoAlbumMock](src/album/model/PhotoAlbumMock.java) class that implements IPhotoAlbum, and a [PhotoAlbumViewMock](src/album/view/PhotoAlbumViewMock.java) class that implements IPhotoAlbumView. These mock classes are for data transmission testing purposes only, so they have a log to log all function calls and inputs. [IPhotoAlbumControllerTest](test/IPhotoAlbumControllerTest.java) uses a PhotoAlbumMock instance and testFile.txt to test that the correct method calls to the model are made with the correct inputs. Then, it uses PhotoAlbumViewMock to test that correct inputs are sent to the mock view. 






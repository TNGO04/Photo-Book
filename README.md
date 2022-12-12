# Shape Album Application
Trang Ngo

CS5004 - Object Oriented Design

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

### Web View

In the web view, all snapshots within the album are displayed in an HTML sequentially, delimited by container boxes. The view generates a proper HTML text file with embedded SVG (to render the images). Within each snapshot, the identifier, description, and the shapes (creating an image) are displayed. 


### Graphical View
In the graphical UI using Java Swing, I use Java Swing to build a custom UI to navigate between snapshots in the photo album. 

In this view, the snapshot is drawn inside of a window, one snapshot at a time. There are interactive buttons that allow the user to:
+ View the snapshot information (unique id and description)
+ "page forward" and show the next snapshot if it exists. If no further snapshots exist, a message to the user should indicate that
+ "page backward" and show the previous snapshot, if there is a previous one, If there is no "previous" a message to the user should be shown
+ "jump" to a snapshot the user explicitly selects from a list of options
+ "autoplay" all snapshots in a loop

## Controller
Similar to the Model and View, the controller is accessed through public interface [IPhotoAlbumController](src/album/controller/IPhotoAlbumController), imeplemented by concrete class [PhotoAlbumControllerImpl](src/album/controller/PhotoAlbumControllerImpl). 


## Application

## Demos




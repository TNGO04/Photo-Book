package album.model;

import album.model.shapes.IShape;

import java.util.Map;

/**
 * SNapShotImpl implements ISnapshot interface.
 */
public class SnapshotImpl implements ISnapshot {
  private String id;
  private String timeStamp;
  private String description;
  private Map<String, IShape> shapeList;

  /**
   * Constructor.
   * @param id  identifier of snapshot
   * @param timeStamp timestamp of snapshot
   * @param description description of snapshot
   * @param shapeList shapes in snapshot
   * @throws IllegalArgumentException if any input object is null
   */
  public SnapshotImpl(String id, String timeStamp, String description,
                      Map<String, IShape> shapeList) throws IllegalArgumentException {
    if ((id == null) || (timeStamp == null) || (description == null) || (shapeList == null)) {
      throw new IllegalArgumentException("Object must not be null.");
    }

    this.id = id;
    this.description = description;
    this.timeStamp = timeStamp;
    this.shapeList = shapeList;
  }

  @Override
  public String getID() {
    return this.id;
  }

  @Override
  public String getTimestamp() {
    return this.timeStamp;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Get list of shapes in snapshot.
   * @return collection of shapes in snapshot.
   */
  @Override
  public Map<String, IShape> getShapes() {
    return this.shapeList;
  }

  @Override
  public String toString() {
    String s = "";

    s += "Snapshot ID: " + this.id + "\n";
    s += "Timestamp: " + this.timeStamp + "\n";
    s += "Description: " + this.description + "\n";
    s += "Shape Information: \n";

    int i = 0;
    int len = this.shapeList.size();
    for (String key: this.shapeList.keySet()) {
      s += "Name: " + key + "\n";
      s += this.shapeList.get(key).toString();

      if (i != (len - 1)) {
        s += "\n";
      }
      i++;
    }

    return s;
  }

}

package album.view;

import album.model.ISnapshot;
import album.model.shapes.IShape;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Graphical View implements interface IPhotoAlbumView and inherits from IFrame.
 */
public class PhotoAlbumGraphicalView extends JFrame implements IPhotoAlbumView {
  private DrawingPanel imagePanel;
  private JTextArea label;

  private JButton previousButton;
  private JButton nextButton;
  private JButton detailButton;
  private JButton chooseButton;

  /**
   * Constructor.
   * @param xMax  width of viewport for snapshot
   * @param yMax  height of viewport for snapshot
   */
  public PhotoAlbumGraphicalView(int xMax, int yMax) {
    super();

    final int WIDTH = 1200;
    final int HEIGHT = 1200;
    super.setSize(WIDTH, HEIGHT);

    setTitle("Photo Album");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(10,10);

    // create a panel to store snapshot image and a container to help center it
    JPanel imageContainer = new JPanel();
    imagePanel = new DrawingPanel() {
      @Override
      public Dimension getPreferredSize() {
        return new Dimension(xMax, yMax);
      }
    };
    imageContainer.add(imagePanel);

    // create label that displays details of snapshot and container to center it
    label = new JTextArea();
    JPanel labelContainer = new JPanel();
    labelContainer.add(label);

    // create button panel and buttons for navigating GUI
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    previousButton = new JButton("Previous");
    previousButton.setActionCommand("Previous");
    nextButton = new JButton("Next");
    nextButton.setActionCommand("Next");
    detailButton = new JButton("Details");
    detailButton.setActionCommand("Details");
    chooseButton = new JButton("Select");
    chooseButton.setActionCommand("Select");

    buttonPanel.add(previousButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(detailButton);
    buttonPanel.add(chooseButton);

    //content pane holds all content on view
    JPanel contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
    contentPane.add(Box.createRigidArea(new Dimension(50, 20)));
    contentPane.add(buttonPanel);
    contentPane.add(labelContainer);
    contentPane.add(imageContainer);
    contentPane.add(Box.createRigidArea(new Dimension(50, 20)));

    // create scroll pane
    JScrollPane jScrollPane = new JScrollPane(contentPane);
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    this.getContentPane().add(jScrollPane);
    this.pack();
  }

  /**
   * Print out view, given snapshots to be printed out
   * (only used by static view, since dynamic view starts off with first snapshot).
   *
   * @param list of snapshots to be displayed by static view
   */
  @Override
  public void display(Collection<ISnapshot> list) {
    this.setVisible(true);
  }

  @Override
  public void setActionListener(ActionListener listener) {
    previousButton.addActionListener(listener);
    nextButton.addActionListener(listener);
    detailButton.addActionListener(listener);
    chooseButton.addActionListener(listener);
  }

  @Override
  public void addSnapshotToCanvas(Collection<IShape> shapes) throws IllegalArgumentException {
    imagePanel.addShapes(shapes);
  }

  /**
   * Update snapshot being displayed by view (only used by dynamic views).
   */
  @Override
  public void refreshPanel() {
    this.label.setVisible(false);
    this.imagePanel.repaint();
  }

  @Override
  public void updateLabel(String content) {
    this.label.setText(content);
  }

  /**
   * Make label visible.
   */
  @Override
  public void displayLabel() {
    this.label.setVisible(true);
  }


  /**
   * Display dialog boxes (only used by dynamic views).
   *
   * @param content content of dialog box
   */
  @Override
  public void displayDialog(String content) {
    JOptionPane.showMessageDialog(this,
            content);
  }

  @Override
  public String displaySelector(Object[] options, Object defaultChoice) {
    String s = (String) JOptionPane.showInputDialog(
            this,
            "Choose snapshot to display\n",
            "Select snapshot",
            JOptionPane.PLAIN_MESSAGE, null,
            options, defaultChoice);
    return s;
  }
}

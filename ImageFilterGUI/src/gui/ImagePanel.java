package gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 12.05.2017, 00:24.
 */
public class ImagePanel extends JPanel {

  private JLabel imageLabel;
  private BufferedImage image;

  /**
   * constructor method to create the new image panel.
   */
  public ImagePanel() {
    super(new BorderLayout(MainGUI.LAYOUT_GAP, MainGUI.LAYOUT_GAP));
    this.imageLabel = new JLabel("");
    this.image = null;
    this.add(imageLabel, "Center");
  }

  /**
   * method to load an image onto the panel.
   * removes the current JLabel from the panel and adds the image to the JLabel as an image icon.
   * adds the label afterwards.
   *
   * @param image BufferedImage that needs to be presented on panel.
   */
  public void loadImageToPanel(BufferedImage image) {
    this.remove(imageLabel);
    this.image = image;
    this.imageLabel = new JLabel(new ImageIcon(image));
    this.add(imageLabel, "Center");
  }

  // Getter and setter methods.
  public JLabel getImageLabel() {
    return imageLabel;
  }

  public void setImageLabel(JLabel imageLabel) {
    this.imageLabel = imageLabel;
  }

  public BufferedImage getImage() {
    return image;
  }
}

package test;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 22.05.2017, 16:52.
 */
public class ColorSelectPanel extends JPanel implements MouseInputListener{

  private static final String COLOR_SPECTRUM_FILE_PATH = "src/test/colors.png";

  private JLabel imageLabel;
  private BufferedImage image;
  private Dimension imageSize;
  private Dimension panelSize;    // Might be junk, depending on how the panel will be implemented!

  /**
   * default constructor to get the color select panel up and going.
   */
  public ColorSelectPanel(){
    // TODO finish empty constructor and handle the better constructor afterwards.
  }

  // TODO adjust mouse event handling methods.
  // Overridden methods that got inherited by implementing the mouse input listener.
  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {

  }
}

package test;

import gui.ImagePanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import logic.Core;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 17.05.2017, 20:36.
 */
public class TestGUI extends JFrame {

  private static final String IMAGE_PATH_1 = "src/res/rawImg/Zimmer.png";
  private static final String IMAGE_PATH_2 = "src/res/rawImg/EMail.png";

  private JPanel flowPanel;
  private ImagePanel imagePanel;
  private JButton button1, button2;

  /**
   * constructor method to create a new TestGUI.
   * was needed to sort out, how to use the image panel in a correct way.
   *
   * works fine!
   */
  private TestGUI(){
    super("TestGUI");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(10, 10));
    setResizable(true);

    imagePanel = new ImagePanel();
    BufferedImage image1 = Core.readImageFromPath(IMAGE_PATH_1);
    BufferedImage image2 = Core.readImageFromPath(IMAGE_PATH_2);
    imagePanel.loadImageToPanel(image1);
    flowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    this.add(imagePanel, "Center");
    button1 = new JButton("image1");
    button2 = new JButton("image2");
    button1.addActionListener(e -> {
      imagePanel.loadImageToPanel(image1);
      imagePanel.revalidate();
      imagePanel.repaint();
    });
    button2.addActionListener(e -> {
      imagePanel.loadImageToPanel(image2);
      imagePanel.revalidate();
      imagePanel.repaint();
    });
    this.add(flowPanel, "South");
    flowPanel.add(button1);
    flowPanel.add(button2);

    setSize(600, 400);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * main method to get everything going. starts the gui within the designated thread.
   *
   * @param args .
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException |
        UnsupportedLookAndFeelException | IllegalAccessException e) {
      e.printStackTrace();
    }
    JComponent.setDefaultLocale(Locale.ENGLISH);
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new TestGUI();
      }
    });
  }

}

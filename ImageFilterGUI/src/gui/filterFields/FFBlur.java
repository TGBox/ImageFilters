package gui.filterFields;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 17.05.2017, 21:07.
 */
public class FFBlur extends JPanel {

  private JPanel buttonPanel, labelPanel;
  private JButton blurButton;
  private JLabel label;

  /**
   * constructor method to create the filter field for the blur filter.
   */
  public FFBlur() {
    super(new BorderLayout(10, 10));
    this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    this.blurButton = new JButton("Blur image");
    this.labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    this.label = new JLabel("Press the button to blur the image");
    this.add(labelPanel, "North");
    this.add(buttonPanel, "Center");
    buttonPanel.add(blurButton);
    labelPanel.add(label);
  }

  // Getter and setter methods.
  public JButton getBlurButton() {
    return blurButton;
  }

  public void setBlurButton(JButton blurButton) {
    this.blurButton = blurButton;
  }
}

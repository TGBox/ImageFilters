package gui.filterFields;

import gui.MainGUI;
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
 * on 12.05.2017, 00:50.
 */
public class FFNegative extends JPanel {

  private static final String TITLE = "Negative Filter";

  private JPanel labelPanel, buttonPanel;
  private JButton flipButton;
  private JLabel label;

  /**
   * constructor method to create a new filter field negative panel.
   */
  public FFNegative() {
    super(new BorderLayout(MainGUI.LAYOUT_GAP, MainGUI.LAYOUT_GAP));
    this.labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
        MainGUI.LAYOUT_GAP, MainGUI.LAYOUT_GAP));
    this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
        MainGUI.LAYOUT_GAP, MainGUI.LAYOUT_GAP));
    this.flipButton = new JButton("Flip");
    this.label = new JLabel(TITLE);
    add(labelPanel, "North");
    add(buttonPanel, "Center");
    this.labelPanel.add(label);
    this.buttonPanel.add(flipButton);
  }

  // Getter and setter methods.
  public JButton getFlipButton() {
    return flipButton;
  }

  public void setFlipButton(JButton flipButton) {
    this.flipButton = flipButton;
  }
}

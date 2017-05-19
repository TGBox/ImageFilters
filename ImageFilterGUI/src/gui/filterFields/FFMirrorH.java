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
 * on 19.05.2017, 12:15.
 */
public class FFMirrorH extends JPanel {

  private static final int GAP = 10;

  private JPanel labelPanel, buttonPanel;
  private JButton mirrorButton;
  private JLabel label;

  /**
   * constructor method to create a new Filter field for the mirror horizontally filter.
   */
  public FFMirrorH(){
    super(new BorderLayout(GAP, GAP));
    this.labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
    this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
    mirrorButton = new JButton("Mirror");
    label = new JLabel("Mirror image horizontally");
    this.add(labelPanel, "North");
    this.add(buttonPanel, "Center");
    labelPanel.add(label);
    buttonPanel.add(mirrorButton);
  }

  // Getter and setter methods.
  public JButton getMirrorButton() {
    return mirrorButton;
  }
  public void setMirrorButton(JButton mirrorButton) {
    this.mirrorButton = mirrorButton;
  }

}

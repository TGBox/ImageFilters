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
 * on 22.05.2017, 16:42.
 */
public class FFSortAscending extends JPanel {

  private static final int GAP = 10;

  private JPanel labelPanel, buttonPanel;
  private JLabel label;
  private JButton sortButton;

  public FFSortAscending() {
    super(new BorderLayout(GAP, GAP));
    this.labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
    this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
    this.label = new JLabel("Sort image by ascending color values");
    this.sortButton = new JButton("Sort");
    add(labelPanel, "North");
    add(buttonPanel, "Center");
    labelPanel.add(label);
    buttonPanel.add(sortButton);
  }

  // Getter and setter methods.
  public JButton getSortButton() {
    return sortButton;
  }

  public void setSortButton(JButton sortButton) {
    this.sortButton = sortButton;
  }
}

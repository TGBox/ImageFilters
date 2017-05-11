package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import logic.Constants;
import logic.Constants.Filter;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 12.05.2017, 00:30.
 */
public class FilterPanel extends JPanel {

  private static final String TITLE = "Available Filters";
  private static final int COLS = 1;

  private Constants.Filter[] filters = Constants.Filter.values();
  private JButton[] buttons;
  private JPanel gridPanel, labelPanel;
  private JLabel label;
  private JScrollPane scrollPane;

  /**
   * constructor method to create a new filter panel that holds all available filters as buttons.
   */
  public FilterPanel(){
    super(new BorderLayout(MainGUI.LAYOUT_GAP, MainGUI.LAYOUT_GAP));
    this.gridPanel = new JPanel(new GridLayout(filters.length, COLS,
        MainGUI.LAYOUT_GAP, MainGUI.LAYOUT_GAP));
    this.labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
        MainGUI.LAYOUT_GAP, MainGUI.LAYOUT_GAP));
    this.label = new JLabel(TITLE);
    this.buttons = new JButton[filters.length];
    this.scrollPane = new JScrollPane(gridPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    this.add(labelPanel, "North");
    this.add(scrollPane, "Center");
    labelPanel.add(label);
    for (int i = 0; i < filters.length; i++) {
      buttons[i] = new JButton(String.valueOf(i + 1));
      gridPanel.add(buttons[i], i);
    }
  }

  // Getter and setter methods.
  public Filter[] getFilters() {
    return filters;
  }
  public JButton[] getButtons() {
    return buttons;
  }
  public void setButtons(JButton[] buttons) {
    this.buttons = buttons;
  }
  public void setButtonAt(JButton button, int index) {
    this.buttons[index] = button;
  }

  /**
   * method to get a specific button from the button array.
   * @param index int index where the button will be searched.
   * @return the button.
   */
  public JButton getButtonAt(int index) {
    return buttons[index];
  }

  /**
   * method to get the corresponding name to any given button from the button array.
   * @param index int index of the button array where a name is needed.
   * @return String name for the button.
   */
  public String getNameOf(int index){
    return String.valueOf(filters[index]);
  }

  /**
   * method to remove all old buttons and replace them with updated ones.
   * might be useless.
   */
  public void setButtonsOnGrid(){
    gridPanel.removeAll();
    for (int i = 0; i < filters.length; i++) {
      gridPanel.add(buttons[i], i);
    }
  }

}

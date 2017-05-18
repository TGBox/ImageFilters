package gui.filterFields;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 18.05.2017, 22:20.
 */
public class FFSwapColors extends JPanel {

  // TODO adjust ticks and labels
  // TODO give additional options like swapping colors, taking colors via tool from image, setting a range for swapping the colors.

  private static final int GAP = 10;

  private JPanel labelPanel, gridPanel, color1Panel, color2Panel,
      preview1Panel, preview2Panel, slider1Panel, slider2Panel, buttonPanel, oldPanel, newPanel;
  private JSlider red1Slider, red2Slider, green1Slider, green2Slider, blue1Slider, blue2Slider;
  private JButton swapButton;
  private JLabel generalLabel, color1Label, color2Label;

  /**
   * constructor method to create the new filter field for the swap color filter.
   */
  public FFSwapColors() {
    super(new BorderLayout(GAP, GAP));
    initComponents();
    addComponentsTogether();
    addActionListeners();
  }

  /**
   * method to initialize all the components that are defined in the scope.
   */
  private void initComponents() {
    labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
    gridPanel = new JPanel(new GridLayout(1, 2, GAP, GAP));
    color1Panel = new JPanel(new BorderLayout(GAP, GAP));
    color2Panel = new JPanel(new BorderLayout(GAP, GAP));
    preview1Panel = new JPanel(new BorderLayout(GAP, GAP));
    preview2Panel = new JPanel(new BorderLayout(GAP, GAP));
    slider1Panel = new JPanel(new GridLayout(3, 1, GAP, GAP));
    slider2Panel = new JPanel(new GridLayout(3, 1, GAP, GAP));
    buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
    oldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    oldPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.DARK_GRAY));
    newPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    newPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.DARK_GRAY));
    red1Slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    red1Slider.setPaintTicks(true);
    red1Slider.setMinorTickSpacing(15);
    red2Slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    green1Slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    green2Slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    blue1Slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    blue2Slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    swapButton = new JButton("Swap colors");
    generalLabel = new JLabel("Swap colors");
    color1Label = new JLabel("Old color");
    color2Label = new JLabel("New color");
    updateColor();
  }

  /**
   * method to add all the components together.
   */
  private void addComponentsTogether() {
    this.add(labelPanel, "North");
    this.add(gridPanel, "Center");
    this.add(buttonPanel, "South");
    labelPanel.add(generalLabel);
    gridPanel.add(color1Panel, 0);
    gridPanel.add(color2Panel, 1);
    color1Panel.add(preview1Panel, "North");
    color1Panel.add(slider1Panel, "Center");
    preview1Panel.add(oldPanel, "Center");
    preview1Panel.add(color1Label, "North");
    slider1Panel.add(red1Slider, 0);
    slider1Panel.add(green1Slider, 1);
    slider1Panel.add(blue1Slider, 2);
    color2Panel.add(preview2Panel, "North");
    color2Panel.add(slider2Panel, "Center");
    preview2Panel.add(newPanel, "Center");
    preview2Panel.add(color2Label, "North");
    slider2Panel.add(red2Slider, 0);
    slider2Panel.add(green2Slider, 1);
    slider2Panel.add(blue2Slider, 2);
    buttonPanel.add(swapButton);
  }

  /**
   * method to add all necessary action listeners.
   */
  private void addActionListeners() {
    red1Slider.addChangeListener(e -> updateColor());
    red2Slider.addChangeListener(e -> updateColor());
    green1Slider.addChangeListener(e -> updateColor());
    green2Slider.addChangeListener(e -> updateColor());
    blue1Slider.addChangeListener(e -> updateColor());
    blue2Slider.addChangeListener(e -> updateColor());
  }

  /**
   * method to update the color panels with the currently selected color values.
   */
  public void updateColor() {
    Color col1 = new Color(red1Slider.getValue(), green1Slider.getValue(), blue1Slider.getValue());
    Color col2 = new Color(red2Slider.getValue(), green2Slider.getValue(), blue2Slider.getValue());
    oldPanel.setBackground(col1);
    newPanel.setBackground(col2);
    this.revalidate();
    this.repaint();
  }

  // Getter and setter methods.
  public JButton getSwapButton() {
    return swapButton;
  }
  public void setSwapButton(JButton swapButton) {
    this.swapButton = swapButton;
  }
  public Color getOldColor(){
    return this.oldPanel.getBackground();
  }
  public Color getNewColor(){
    return this.newPanel.getBackground();
  }
}

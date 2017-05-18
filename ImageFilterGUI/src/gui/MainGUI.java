package gui;

import gui.filterFields.FFBlur;
import gui.filterFields.FFNegative;
import gui.filterFields.FFSwapColors;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import logic.Constants;
import logic.Constants.Filter;
import logic.Core;
import logic.FilterMethods;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 12.05.2017, 00:13.
 */
public class MainGUI extends JFrame implements ActionListener {

  public static final int LAYOUT_GAP = 10;
  private static final String[] FILTERS = Constants.getNames(Filter.class);
  private static final int WINDOW_WIDTH = 600;
  private static final int WINDOW_HEIGHT = 400;

  // TODO make feedback dialog if image was saved or not
  private JMenuBar menuBar;
  private JMenu mainMenu;
  private JMenuItem openImageItem, saveImageItem, helpItem, aboutItem, exitItem;
  private ImagePanel imagePanel;
  private JPanel switchPanel;
  private FilterPanel filterPanel;
  private FFNegative ffNegative;
  private FFBlur ffBlur;
  private FFSwapColors ffSwap;
  private BufferedImage image;

  private MainGUI() {
    super("Image Filter GUI");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
    setResizable(true);

    initComponents();
    addComponentsTogether();
    addActionListeners();

    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
        new MainGUI();
      }
    });
  }

  /**
   * method to initialize all the components that are defined in the scope.
   */
  private void initComponents() {
    menuBar = new JMenuBar();
    mainMenu = new JMenu("Menu");
    openImageItem = new JMenuItem("Open image");
    saveImageItem = new JMenuItem("Save image");
    helpItem = new JMenuItem("Help");
    aboutItem = new JMenuItem("About");
    exitItem = new JMenuItem("Exit");

    this.imagePanel = new ImagePanel();
    this.filterPanel = new FilterPanel();
    for (int i = 0; i < FILTERS.length; i++) {
      filterPanel.getButtonAt(i).addActionListener(this);
      filterPanel.getButtonAt(i).setActionCommand(String.valueOf(FILTERS[i]));
    }
    this.switchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // Filter fields.
    this.ffNegative = new FFNegative();
    this.ffBlur = new FFBlur();
    this.ffSwap = new FFSwapColors();
  }

  /**
   * method to add all the components together.
   */
  private void addComponentsTogether() {
    this.setJMenuBar(menuBar);
    menuBar.add(mainMenu);
    mainMenu.add(openImageItem);
    mainMenu.add(saveImageItem);
    mainMenu.add(new JSeparator(JSeparator.HORIZONTAL));
    mainMenu.add(helpItem);
    mainMenu.add(aboutItem);
    mainMenu.add(new JSeparator(JSeparator.HORIZONTAL));
    mainMenu.add(exitItem);

    this.add(new JScrollPane(imagePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), "Center");
    this.add(switchPanel, "South");
    switchPanel.add(ffNegative);
    this.add(filterPanel, "East");
  }

  /**
   * method to add all necessary action listeners.
   */
  private void addActionListeners() {
    // Buttons that are directly from the GUI.
    openImageItem.addActionListener(this);
    openImageItem.setActionCommand("open");
    saveImageItem.addActionListener(this);
    saveImageItem.setActionCommand("save");
    helpItem.addActionListener(this);
    helpItem.setActionCommand("help");
    aboutItem.addActionListener(this);
    aboutItem.setActionCommand("about");
    exitItem.addActionListener(this);
    exitItem.setActionCommand("exit");

    // Buttons for ffNegative.
    this.ffNegative.getFlipButton().addActionListener(this);
    this.ffNegative.getFlipButton().setActionCommand("negative");

    // Buttons for ffBlur.
    this.ffBlur.getBlurButton().addActionListener(this);
    this.ffBlur.getBlurButton().setActionCommand("blur");

    // Buttons for ffSwap.
    this.ffSwap.getSwapButton().addActionListener(this);
    this.ffSwap.getSwapButton().setActionCommand("swapColors");
  }

  // TODO short test run showed that the implementation of the buttons does not work! test again sober!

  /**
   * method to create a new button that implements the action listener and uses it's caption as it's
   * action command. the action commands need to be caught in the action performed method with
   * corresponding behaviours.
   *
   * @param caption String that will be used as action command and for the caption of the button.
   * @return JButton that was created as planned.
   */
  private JButton makeButton(String caption) {
    JButton button = new JButton(caption);
    button.setActionCommand(caption);
    button.addActionListener(this);
    return button;
  }

  /**
   * method that overrides the actionPerformed method of the ActionListener class.
   * the action commands will be the same as the captions used to create the buttons
   *
   * @param e ActionEvent that lead to the execution of this method.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    String com = e.getActionCommand();
    if (com.equals("open")) { // Menu items.
      imagePanel.loadImageToPanel(Core.openUserActionMethod());
      imagePanel.revalidate();
      imagePanel.repaint();
    } else if (com.equals("save")) {
      if (Core.saveUserActionMethod(imagePanel.getImage())) {
        JOptionPane.showMessageDialog(null, "Image was saved correctly!",
            "Saving successful", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, "Image could not be saved!\n"
            + "Try again!", "Saving failed!", JOptionPane.ERROR_MESSAGE);
      }
    } else if (com.equals("help")) {
      Core.helpUserActionMethod();
    } else if (com.equals("about")) {
      Core.aboutUserActionMethod();
    } else if (com.equals("exit")) {
      System.exit(0);
    } else if (com.equals("negative")) {  // Filter buttons.
      image = imagePanel.getImage();
      image = FilterMethods.negativeFilter(image);
      imagePanel.loadImageToPanel(image);
      imagePanel.revalidate();
      imagePanel.repaint();
    } else if (com.equals("blur")) {
      image = imagePanel.getImage();
      image = FilterMethods.defaultBlurFilter(image);
      imagePanel.loadImageToPanel(image);
      imagePanel.revalidate();
      imagePanel.repaint();
    } else if(com.equals("swapColors")){
      image = imagePanel.getImage();
      image = FilterMethods.swapColorsFilter(image, ffSwap.getOldColor(), ffSwap.getNewColor());
      imagePanel.loadImageToPanel(image);
      imagePanel.revalidate();
      imagePanel.repaint();
    } else if (com.equals(String.valueOf(Filter.Blur))) {   // FilterField changes.
      swap(ffBlur);
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.ChannelMix))) {
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.MirrorDiagonally))) {
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.MirrorHorizontally))) {
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.MirrorVertically))) {
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.Negative))) {
      swap(ffNegative);
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.SortAscending))) {
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.SortDescending))) {
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.SwapColors))) {
      swap(ffSwap);
      System.out.println(e.getActionCommand());
    } else if (com.equals(String.valueOf(Filter.Tritone))) {
      System.out.println(e.getActionCommand());
    } else {
      System.out.println("unknown command!");
    }
    this.revalidate();
    this.repaint();
  }

  /**
   * method to swap out the filter field panels for the different filters.
   * removes all children from the switchPanel and adds the given component to it.
   * GUI gets revalued and repainted afterwards.
   *
   * @param component the JComponent that will be added to the switch panel.
   */
  private void swap(JComponent component) {
    this.switchPanel.removeAll();
    this.switchPanel.add(component);
    this.revalidate();
    this.repaint();
  }
}

package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 12.05.2017, 00:13.
 */
public class MainGUI extends JFrame implements ActionListener {

  private static final int WINDOW_WIDTH = 600;
  private static final int WINDOW_HEIGHT = 400;
  public static final int LAYOUT_GAP = 10;

  private JMenuBar menuBar;
  private JMenu mainMenu;
  private JMenuItem openImageItem, saveImageItem, helpItem, aboutItem, exitItem;
  private ImagePanel imagePanel;
  private FilterPanel filterPanel;
  private FilterFieldNegative filterFieldNegative;

  private MainGUI(){
    super("Image Filter GUI");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
    setResizable(false);

    initComponents();
    addComponentsTogether();
    addActionListeners();

    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLocationRelativeTo(null);
    setVisible(true);
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
    filterPanel.getButtonAt(0).addActionListener(this);
    filterPanel.getButtonAt(0).setActionCommand("negative");
    this.filterFieldNegative = new FilterFieldNegative();
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

    this.add(imagePanel, "Center");
    this.add(filterFieldNegative, "South");
    this.add(filterPanel, "East");
  }

  /**
   * method to add all necessary action listeners.
   */
  private void addActionListeners() {
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
  }

  /**
   * method to create a new button that implements the action listener and uses it's caption as it's
   * action command. the action commands need to be caught in the action performed method with
   * corresponding behaviours.
   * @param caption String that will be used as action command and for the caption of the button.
   * @return JButton that was created as planned.
   */
  private JButton makeButton(String caption) {
    JButton button = new JButton(caption);
    button.setActionCommand(caption);
    button.addActionListener(this);
    return button;
  }

  // TODO short test run showed that the implementation of the buttons does not work! test again sober!

  /**
   * method that overrides the actionPerformed method of the ActionListener class.
   * the action commands will be the same as the captions used to create the buttons
   *
   * @param e ActionEvent that lead to the execution of this method.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand().equals("negative")){
      imagePanel.setImageLabel(new JLabel("negative"));
    }
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
}

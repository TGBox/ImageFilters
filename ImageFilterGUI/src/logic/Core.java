package logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 16.05.2017, 18:11.
 */
public class Core {

  /**
   * method to display an about dialog for the user when the corresponding item is clicked.
   */
  public static void aboutUserActionMethod() {
    JOptionPane.showMessageDialog(null,
        "This program was written by Daniel Roesch.\n"
            + "Some of the filters are adapted from JHLabs.", "About this program",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * method to display a help dialog for the user if the corresponding menu item is selected.
   */
  public static void helpUserActionMethod() {
    JOptionPane.showMessageDialog(null,
        "This is a program to alter images with different image filters.\n"
            + "Loading and saving the images can be done via the menu, and all the\n"
            + "filters can be found on the right side. Just select one and the\n"
            + "corresponding adjustment panel will appear at the bottom of the interface.\n"
            + "Try it out, it really isn't that hard.", "Help",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * method to open a JFileChooser to let the user load an image from the file to the application.
   *
   * @return the buffered image.
   */
  public static BufferedImage openUserActionMethod() {
    JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Only image files",
        "jpg", "bmp", "png", "svg");
    fileChooser.setFileFilter(filter);
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setMultiSelectionEnabled(false);
    fileChooser.setDialogTitle("Select an image to load from your computer");
    fileChooser.showOpenDialog(null);
    if (!fileChooser.getSelectedFile().equals(null)) {
      return readImageFromPath(fileChooser.getSelectedFile().getAbsolutePath());
    } else {
      return null;
    }
  }

  /**
   * method to let the user decide via a JFileChooser where he wants to save an image.
   *
   * @param image the buffered image that will be saved.
   * @return boolean true if the image could be saved, false if not.
   */
  public static boolean saveUserActionMethod(BufferedImage image) {
    JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Only image files",
        "jpg", "bmp", "png", "svg");
    fileChooser.setFileFilter(filter);
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setMultiSelectionEnabled(false);
    fileChooser.setDialogTitle("Select a file path to save your image");
    fileChooser.showSaveDialog(null);
    if (!fileChooser.getSelectedFile().equals(null)) {
      return saveImageToFile(image, fileChooser.getSelectedFile().getAbsolutePath());
    } else {
      return false;
    }
  }

  /**
   * method to save an image to file.
   *
   * @param img the buffered image that needs to be saved.
   * @param filePath the String file path of the image.
   * @return boolean, true if the image could be saved, false if not.
   */
  public static boolean saveImageToFile(BufferedImage img, String filePath) {
    File file = new File(filePath);
    try {
      ImageIO.write(img, getExt(filePath), file);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * method to extract the file type extension as a string from a given file path. traverses the
   * file path from end to front and returns the following substring if a '.' is found.
   *
   * @param filePath the String file path.
   * @return extension as a string or the default "png";
   */
  public static String getExt(String filePath) {
    for (int i = filePath.length() - 1; i > 0; i--) {
      if (filePath.charAt(i) == '.') {
        return filePath.substring(i + 1, filePath.length());
      }
    }
    return "png";
  }

  /**
   * method to read in an image from a given file path string. if the image cannot be loaded, the
   * method will return an empty buffered image of size 69 x 69 pixel.
   *
   * @param filePath the string file path where the image is located.
   * @return the loaded buffered image, or an "error"-img with defined specs.
   */
  public static BufferedImage readImageFromPath(String filePath) {
    try {
      return ImageIO.read(new File(filePath));
    } catch (IOException e) {
      e.printStackTrace();
      return new BufferedImage(69, 69, 6);
    }
  }

  /**
   * method to change a buffered image into an int[][].
   *
   * @param img the buffered image.
   * @return image as int[][].
   */
  public static int[][] imgToArray(BufferedImage img) {
    int[][] imgArray = new int[img.getWidth()][img.getHeight()];
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        imgArray[x][y] = img.getRGB(x, y);
      }
    }
    return imgArray;
  }

  /**
   * method to retrieve a buffered image from a given int[][].
   *
   * @param imgArray the int array containing rgb values.
   * @return the buffered image created from the array.
   */
  public static BufferedImage arrayToImg(int[][] imgArray) {
    BufferedImage img = new BufferedImage(imgArray[0].length, imgArray.length,
        BufferedImage.TYPE_3BYTE_BGR);
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        img.setRGB(x, y, imgArray[x][y]);
      }
    }
    return img;
  }

  /**
   * method to get the medium Color from an array of colors.
   * takes the single red, green and blue value and adds them up.
   * then divides them by the number of colors in the array.
   *
   * @param colors color array.
   * @return the medium color.
   */
  public static Color getMediumColor(Color[] colors) {
    int r = 0, g = 0, b = 0;
    for (int i = 0; i < colors.length; i++) {
      r += colors[i].getRed();
      g += colors[i].getGreen();
      b += colors[i].getBlue();
    }
    r /= colors.length;
    g /= colors.length;
    b /= colors.length;
    return new Color(r, g, b);
  }

  /**
   * method to get the negative color from a given color.
   * takes the single rgb values and subtracts them from the maximum value of 255.
   *
   * @param c the old color.
   * @return the newly created negative color of c.
   */
  public static Color getNegativeColor(Color c) {
    int red = 255 - c.getRed(), green = 255 - c.getGreen(), blue = 255 - c.getBlue();
    return new Color(red, green, blue);
  }

  /**
   * method to return a random int ranging from 0 to 255.
   *
   * @return int.
   */
  public static int r() {
    Random random = new Random();
    return random.nextInt(256);
  }

  /**
   * method that turns all the rgb values of a given buffered image into an int array.
   *
   * @param img the buffered image.
   * @return the int[] of size img.getWidth() * img.getHeight();
   */
  public static int[] imageRGB_Array(BufferedImage img) {
    int[] rgbValues = new int[img.getHeight() * img.getWidth()];
    int count = 0;
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        rgbValues[count] = img.getRGB(x, y);
        count++;
      }
    }
    return rgbValues;
  }

  /**
   * method to sort a given int[] in ascending order.
   *
   * @param values the int[] that needs to be sorted.
   * @return the sorted array.
   */
  public static int[] sortAscending(int[] values) {
    Arrays.sort(values);
    return values;
  }

  /**
   * method to sort a given int array in descending order.
   * MESSY IMPLEMENTATION BECAUSE MIN_VALUE WILL CAUSE PROBLEMS!
   * but should be okay for this application because the rgb range is from -1 to -16000000.
   *
   * @param values int[] of values.
   * @return the sorted array in descending order.
   */
  public static int[] sortDescending(int[] values) {
    values = flipSignum(values);
    Arrays.sort(values);
    values = flipSignum(values);
    return values;
  }

  /**
   * helper method to flip the signum on every int value in a given array.
   *
   * @param values int[] values.
   * @return the flipped array.
   */
  private static int[] flipSignum(int[] values) {
    for (int i = 0; i < values.length; i++) {
      values[i] *= -1;
    }
    return values;
  }

  /**
   * method that takes an int[] and converts it to a buffered image.
   * takes the square root of the length to get the side lengths of the image.
   * traverses the image as well as the array to write the values consecutively.
   *
   * @param rgbValues the int[] with the rgb values.
   * @return the created buffered image.
   */
  public static BufferedImage rgbArrayToImg(int[] rgbValues) {
    BufferedImage img = new BufferedImage((int) Math.sqrt((double) rgbValues.length),
        (int) Math.sqrt((double) rgbValues.length), BufferedImage.TYPE_3BYTE_BGR);
    int count = 0;
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        img.setRGB(x, y, rgbValues[count]);
        count++;
      }
    }
    return img;
  }

}

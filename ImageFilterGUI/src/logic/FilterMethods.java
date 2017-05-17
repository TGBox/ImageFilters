package logic;

import static logic.Core.getMediumColor;
import static logic.Core.getNegativeColor;
import static logic.Core.imageRGB_Array;
import static logic.Core.rgbArrayToImg;
import static logic.Core.sortAscending;

import com.jhlabs.image.ChannelMixFilter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import lib.TritoneFilter;


/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 16.05.2017, 17:51.
 */
public class FilterMethods {

  /**
   * method to apply the negative filter to a given buffered image.
   *
   * @param image the BufferedImage.
   * @return the negative BufferedImage.
   */
  public static BufferedImage applyNegativeFilter(BufferedImage image) {
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        image.setRGB(x, y, getNegativeColorValue(image.getRGB(x, y)));
      }
    }
    return image;
  }

  /**
   * method that takes an int rgb value for a given color and returns the negative rgb value.
   *
   * @param rgbValue the int color value.
   * @return int rgb negative value.
   */
  private static int getNegativeColorValue(int rgbValue) {
    Color color = new Color(rgbValue);
    int red = 255 - color.getRed();
    int green = 255 - color.getGreen();
    int blue = 255 - color.getBlue();
    return new Color(red, green, blue).getRGB();
  }

  /**
   * method to apply a standard blur filter onto a given buffered image. creates a new image with
   * the same specs as img, then traverses the old image and calls the getMediumColor method to gain
   * the blurred values for the new image. implementation is quite ugly due to the fact that we
   * needed nine different cases, but it works and it's even somewhat fast.
   *
   * @param img the buffered image that needs to be blurred.
   * @return the blurred buffered image.
   */
  public static BufferedImage defaultBlurFilter(BufferedImage img) {
    BufferedImage blur = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    Color[] colors;
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        if (x == 0
            && y == 0) {                                                   // left upper corner
          colors = new Color[4];
          colors[0] = new Color(img.getRGB(x, y));
          colors[1] = new Color(img.getRGB(x + 1, y));
          colors[2] = new Color(img.getRGB(x, y + 1));
          colors[3] = new Color(img.getRGB(x + 1, y + 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        } else if (x == 0
            && y == img.getHeight() - 1) {                          // left bottom corner
          colors = new Color[4];
          colors[0] = new Color(img.getRGB(x, y));
          colors[1] = new Color(img.getRGB(x + 1, y));
          colors[2] = new Color(img.getRGB(x, y - 1));
          colors[3] = new Color(img.getRGB(x + 1, y - 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        } else if (x == img.getWidth() - 1
            && y == 0) {                           // right upper corner
          colors = new Color[4];
          colors[0] = new Color(img.getRGB(x, y));
          colors[1] = new Color(img.getRGB(x - 1, y));
          colors[2] = new Color(img.getRGB(x, y + 1));
          colors[3] = new Color(img.getRGB(x - 1, y + 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        } else if (x == img.getWidth() - 1
            && y == img.getHeight() - 1) {         // right bottom corner
          colors = new Color[4];
          colors[0] = new Color(img.getRGB(x, y));
          colors[1] = new Color(img.getRGB(x - 1, y));
          colors[2] = new Color(img.getRGB(x, y - 1));
          colors[3] = new Color(img.getRGB(x - 1, y - 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        } else if (x == 0 && y > 0 && y < img.getHeight() - 1) {                  // left side
          colors = new Color[6];
          colors[0] = new Color(img.getRGB(x, y - 1));
          colors[1] = new Color(img.getRGB(x + 1, y - 1));
          colors[2] = new Color(img.getRGB(x, y));
          colors[3] = new Color(img.getRGB(x + 1, y));
          colors[4] = new Color(img.getRGB(x, y + 1));
          colors[5] = new Color(img.getRGB(x + 1, y + 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        } else if (y == 0 && x > 0 && x < img.getWidth() - 1) {                  // top side
          colors = new Color[6];
          colors[0] = new Color(img.getRGB(x - 1, y));
          colors[1] = new Color(img.getRGB(x, y));
          colors[2] = new Color(img.getRGB(x + 1, y));
          colors[3] = new Color(img.getRGB(x - 1, y + 1));
          colors[4] = new Color(img.getRGB(x, y + 1));
          colors[5] = new Color(img.getRGB(x + 1, y + 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        } else if (x == img.getWidth() - 1 && y > 0 && y < img.getHeight() - 1) { // right side
          colors = new Color[6];
          colors[0] = new Color(img.getRGB(x, y - 1));
          colors[1] = new Color(img.getRGB(x, y));
          colors[2] = new Color(img.getRGB(x, y + 1));
          colors[3] = new Color(img.getRGB(x - 1, y - 1));
          colors[4] = new Color(img.getRGB(x - 1, y));
          colors[5] = new Color(img.getRGB(x - 1, y + 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        } else if (y == img.getHeight() - 1 && x > 0 && x < img.getWidth() - 1) { // bottom side
          colors = new Color[6];
          colors[0] = new Color(img.getRGB(x + 1, y));
          colors[1] = new Color(img.getRGB(x, y));
          colors[2] = new Color(img.getRGB(x - 1, y));
          colors[3] = new Color(img.getRGB(x + 1, y - 1));
          colors[4] = new Color(img.getRGB(x, y - 1));
          colors[5] = new Color(img.getRGB(x - 1, y - 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        } else {                                                                // generic pixel
          colors = new Color[9];
          colors[0] = new Color(img.getRGB(x - 1, y - 1));
          colors[1] = new Color(img.getRGB(x, y - 1));
          colors[2] = new Color(img.getRGB(x + 1, y - 1));
          colors[3] = new Color(img.getRGB(x - 1, y));
          colors[4] = new Color(img.getRGB(x, y));
          colors[5] = new Color(img.getRGB(x + 1, y));
          colors[6] = new Color(img.getRGB(x - 1, y + 1));
          colors[7] = new Color(img.getRGB(x, y + 1));
          colors[8] = new Color(img.getRGB(x + 1, y + 1));
          blur.setRGB(x, y, getMediumColor(colors).getRGB());
        }
      }
    }
    return blur;
  }

  public static BufferedImage swapColorsFilter(BufferedImage img, Color original,
      Color replacement) {
    // TODO method is insufficient because black and blue have similar values and get detected as the same color.
    // TODO -16777216 <-- black || blue --> -16776961 needs adjustments!
    BufferedImage swappedImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    int rgbOriginal = original.getRGB();
    int upperBound = rgbOriginal + 1000;
    int lowerBound = rgbOriginal - 1000;
    if (lowerBound < Color.BLACK.getRGB()) {
      lowerBound = Color.BLACK.getRGB();
    }
    if (upperBound > Color.WHITE.getRGB()) {
      upperBound = Color.WHITE.getRGB();
    }
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        if (img.getRGB(x, y) >= lowerBound && img.getRGB(x, y) <= upperBound) {
          swappedImg.setRGB(x, y, replacement.getRGB());
        } else {
          swappedImg.setRGB(x, y, img.getRGB(x, y));
        }
      }
    }
    return swappedImg;
  }

  /**
   * three methods to mirror images in the desired way.
   *
   * @param img the buffered image that needs to be mirrored.
   * @return the mirrored image.
   */
  public static BufferedImage mirrorDiagonally(BufferedImage img) {
    BufferedImage mirroredImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    for (int y = 0, h = img.getHeight() - 1; y < img.getHeight() && h > 0; y++, h--) {
      for (int x = 0, w = img.getWidth() - 1; x < img.getWidth() && w > 0; x++, w--) {
        mirroredImg.setRGB(x, y, img.getRGB(w, h));
      }
    }
    return mirroredImg;
  }

  public static BufferedImage mirrorHorizontally(BufferedImage img) {
    BufferedImage mirroredImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0, w = img.getWidth() - 1; x < img.getWidth() && w > 0; x++, w--) {
        mirroredImg.setRGB(x, y, img.getRGB(w, y));
      }
    }
    return mirroredImg;
  }

  public static BufferedImage mirrorVertically(BufferedImage img) {
    BufferedImage mirroredImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    for (int y = 0, h = img.getHeight() - 1; y < img.getHeight() && h > 0; y++, h--) {
      for (int x = 0; x < img.getWidth(); x++) {
        mirroredImg.setRGB(x, y, img.getRGB(x, h));
      }
    }
    return mirroredImg;
  }

  /**
   * method to apply the channel mixing filter. takes six int parameters as inputs. the first three
   * represent the two additional colors compared to a specific one. the next three parameters
   * represent the amount that the values will be weighed off against, to calculate the new values.
   * all int values must be of the range 0 - 255.
   *
   * @param img the buffered image that needs to be changed.
   * @param greenRed the amount of green and red that needs to be added to blue.
   * @param blueGreen the amount of blue and green that needs to be added to red.
   * @param redBlue the amount of red and blue that needs to be added to green.
   * @param intoR the percentage weight on which to base the calculation on for adding blueGreen.
   * @param intoG the percentage weight on which to base the calculation on for adding redBlue.
   * @param intoB the percentage weight on which to base the calculation on for adding greenRed.
   * @return the newly generated buffered image with the applied filter.
   */
  public static BufferedImage channelMixFilter(BufferedImage img, int greenRed, int blueGreen,
      int redBlue, int intoR, int intoG, int intoB) {
    BufferedImage mixedImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    // Creating the new ChannelMixFilter and setting the values of the object.
    ChannelMixFilter cmf = new ChannelMixFilter();
    cmf.setBlueGreen(blueGreen);
    cmf.setGreenRed(greenRed);
    cmf.setRedBlue(redBlue);
    cmf.setIntoR(intoR);
    cmf.setIntoG(intoG);
    cmf.setIntoB(intoB);
    cmf.filter(img, mixedImg);
    return mixedImg;
  }

  /**
   * method to apply a negative filter onto a given buffered image. calls the getNegativeColor
   * method whilst traversing the image and writes the results to the new image.
   *
   * @param img the old image that needs to get it's colors reversed.
   * @return the negative version of the img.
   */
  public static BufferedImage negativeFilter(BufferedImage img) {
    BufferedImage neg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
    for (int y = 0; y < img.getHeight(); y++) {
      for (int x = 0; x < img.getWidth(); x++) {
        neg.setRGB(x, y, getNegativeColor(new Color(img.getRGB(x, y))).getRGB());
      }
    }
    return neg;
  }

  /**
   * method to sort a given buffered image by it's ascending color values.
   *
   * @param img the buffered image that needs to be sorted.
   * @return the sorted image.
   */
  public static BufferedImage sortImageAscending(BufferedImage img) {
    int[] values = imageRGB_Array(img);
    values = sortAscending(values);
    return rgbArrayToImg(values);
  }

  /**
   * method to apply a tri tone filter to a given buffered image.
   * regenerates the image with only the three provided colors.
   *
   * @param image the image that needs to be filtered.
   * @param bright the color that shall represent the bright colors in the image.
   * @param medium the color to represent the medium colors.
   * @param shadow shadow color.
   * @return the tri tone image.
   */
  public static BufferedImage tritoneFilter(BufferedImage image, Color bright, Color medium,
      Color shadow) {
    BufferedImage tritoneImg = new BufferedImage(image.getWidth(), image.getHeight(),
        image.getType());
    TritoneFilter tF = new TritoneFilter();
    tF.setHighColor(bright.getRGB());
    tF.setMidColor(medium.getRGB());
    tF.setShadowColor(shadow.getRGB());
    tF.filter(image, tritoneImg);
    return tritoneImg;
  }

}

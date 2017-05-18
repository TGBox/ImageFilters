package logic;

import java.util.Arrays;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "ImageFilterGUI",
 * on 12.05.2017, 00:30.
 */
public class Constants {

  /**
   * method to return any given Enum as a String array.
   * works only under java 8.
   *
   * @param enumClass class representation of the enum.
   * @return String[] containing the corresponding Strings of the enumerations.
   */
  public static String[] getNames(Class<? extends Enum<?>> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toArray(String[]::new);
  }

  public enum Filter {
    Blur, SwapColors, MirrorHorizontally, MirrorVertically, MirrorDiagonally, ChannelMix,
    Negative, SortAscending, SortDescending, Tritone
  }

}

package edu.nyu.cs.pqs.connectfour.views;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This class loads up the image file based on the given color
 * 
 * @author Rachit
 *
 */
public class ViewResources {
  /**
   * Provide the correct ImageIcon based on the color of the input, if a null input is provided
   * return a Empty Connect 4 Disc slot(That is required to build the board)
   * 
   * @param color
   * @return
   */
  public static ImageIcon getConnect4Disc(Color color) {
    String path;
    if (color == null) {
      path = "images/Empty.png";
    } else if (color == Color.RED) {
      path = "images/Red.png";
    } else {
      path = "images/Yellow.png";
    }
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException ignoredExeception) {
    }
    return new ImageIcon(img.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
  }
}

package edu.nyu.cs.pqs.connectfour.model;

import java.awt.Color;

/**
 * This class contains all the constants required by the Player & Models. It's consolidated in one
 * file so that it's easy to change from one point if the parameters of the game were to be changed
 * in the future.
 * 
 * @author Rachit
 *
 */
public class ModelConstants {
  public static final int ROWS = 6;
  public static final int COLS = 7;
  public static final int winLength = 4;
  public static final Color computerDefault = Color.YELLOW;
  public static final Color humanPlayer1 = Color.RED;
  public static final Color humanPlayer2 = Color.YELLOW;
}

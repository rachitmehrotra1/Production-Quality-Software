package edu.nyu.cs.pqs.connectfour.player;

import java.awt.Color;

import edu.nyu.cs.pqs.connectfour.model.Model;

/**
 * Interface class that defines the basic method definition for different types of players
 * 
 * @author Rachit
 *
 */
public interface Player {

  /**
   * On it's turn the player makes a move by calling this, which notifies the model that a move has
   * been made by the specific player object
   * 
   * @param model
   */
  public void makeMove(Model model);

  /**
   * Set the Column on which the move was made
   * 
   * @param col
   */
  public void setColMove(int col);

  /**
   * Get the previous column on which the move was made
   * 
   * @return
   */
  public int getColMove();

  /**
   * Get the COLOR of the disc of the player object
   * 
   * @return playerColor
   */
  public Color getColor();

  /**
   * Get the type of player this object is
   * 
   * @return
   */
  public PlayerType getPlayerType();

}

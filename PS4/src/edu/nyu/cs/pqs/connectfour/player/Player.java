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

  public void makeMove(Model model);

  public void setColMove(int col);

  public int getColMove();

  public Color getColor();

  public PlayerType getPlayerType();

}

package edu.nyu.cs.pqs.connectfour.views;

import java.awt.Color;

import edu.nyu.cs.pqs.connectfour.model.GameMode;

/**
 * This is the interface class for the View and contains the skeleton definition of the methods that
 * are required for the game to start and listen to changes from the model
 * 
 * @author Rachit
 *
 */
public interface Listener {

  void gameStarted();

  void gameTied();

  void gameWon(Color color);

  void makeMove(int row, int col, Color color, GameMode mode);


}

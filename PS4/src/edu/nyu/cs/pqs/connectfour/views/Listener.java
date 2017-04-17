package edu.nyu.cs.pqs.connectfour.views;

import java.awt.Color;

import edu.nyu.cs.pqs.connectfour.model.GameMode;

/**
 * This is the interface class for the View and contains the skeleton definition of the methods 
 * that are required for the game to start and listen to changes from the model
 * 
 * @author Rachit
 *
 */
public interface Listener {

  /**
   * Setup the View for the state which is required for the game to start
   */
  void gameStarted();

  /**
   * Make changes to the view which are triggered by a game tie event
   */
  void gameTied();

  /**
   * Display the notification that the game has been won by a given color and make post game 
   * changes to the view
   * 
   * @param color
   */
  void gameWon(Color color);

  /**
   * Drop a disc in the given row,column of the given color and update the view
   * 
   * @param row
   * @param col
   * @param color
   * @param mode
   */
  void makeMove(int row, int col, Color color, GameMode mode);

  /**
   * Notify the users of a message sent by the underlying model
   * 
   * @param message
   */
  void alert(String message);

  /**
   * Reset the view for a new game to be played
   */
  void gameReset();
}

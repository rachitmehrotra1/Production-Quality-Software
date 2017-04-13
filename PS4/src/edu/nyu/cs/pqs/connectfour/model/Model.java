package edu.nyu.cs.pqs.connectfour.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.nyu.cs.pqs.connectfour.player.Player;
import edu.nyu.cs.pqs.connectfour.player.PlayerFactory;
import edu.nyu.cs.pqs.connectfour.player.PlayerType;
import edu.nyu.cs.pqs.connectfour.views.Listener;;

/**
 * The Model Class contains all the game logic and notifies the different views incase any game
 * change happens.
 * 
 * @author Rachit
 *
 */
public class Model {
  // The model has to be a singleton class to make sure only ONE model is created for a game
  // execution
  private static final Model model = new Model();

  private List<Listener> listeners = new ArrayList<Listener>();
  private Color[][] board = new Color[ModelConstants.ROWS][ModelConstants.COLS];
  // Need a Array to keep a track of discs inserted in a column to make sure that columns
  // dont overfill
  private int[] discsInCol = new int[ModelConstants.COLS];
  private GameMode gameMode;
  private Player playerRed;
  private Player playerYellow;
  private Player playerTurn;

  /*
   * Private Constructor for Model
   */
  private Model() {}

  /*
   * Method to return the instance(singleton) of the game Model
   */
  public static Model getModelInstance() {
    return model;
  }

  /**
   * Add listener to the list of listeners
   * 
   * @param listener
   */
  public void addListener(Listener listener) {
    if (!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }

  /**
   * Remove listener from the list of listeners
   * 
   * @param listener
   */
  public void removeListener(Listener listener) {
    if (!listeners.contains(listener)) {
      throw new IllegalArgumentException("Listener does not exist");
    }
    listeners.remove(listener);
  }

  /**
   * Add new players to game based on the game mode and randomly decide who goes first
   * 
   * @param mode
   */
  public void addPlayers(GameMode mode) {
    this.gameMode = mode;
    // Randomly Generate Whose turn is it
    int random = (int) Math.round(Math.random());
    playerTurn = (random == 0) ? playerRed : playerYellow;
    if (mode.equals(GameMode.SINGLE)) {
      playerYellow = PlayerFactory.addNewPlayer(PlayerType.COMPUTER, Color.YELLOW);
      playerRed = PlayerFactory.addNewPlayer(PlayerType.HUMAN, Color.RED);
    } else {
      playerYellow = PlayerFactory.addNewPlayer(PlayerType.HUMAN, Color.YELLOW);
      playerRed = PlayerFactory.addNewPlayer(PlayerType.HUMAN, Color.RED);
    }
  }
  
  /**
   * Fires up a game started event to notify all the listeners/view that the game has started
   */
  public void fireGameStartedEvent(){
    for (Listener listener : listeners){
      listener.gameStarted();
    }
  }
  
  /**
   * Fires up a game won event to notify all the listeners that game has been won by a player
   * @param color : The color that has won the game.
   */
  public void fireGameWonEvent(Color color){
    for(Listener listener : listeners){
      listener.gameWon(color);
    }
  }
  
  /**
   * Fires up a game tied event in the scenario where the baord is full and there is no spaces
   * left to play 
   */
  public void fireGameTiedEvent(){
    for(Listener listener : listeners){
      listener.gameTied();
    }
  }
  
  /**
   * Fires up a Move Made event to notify the listeners that a move has been made so that they
   * can update the board for the specific building
   * @param The row in which the disc was dropped
   * @param The column in which the disc was dropped
   * @param The color of the disc dropped
   */
  public void fireMoveMadeEvent(int row , int col , Color color){
    for(Listener listener : listeners){
      listener.makeMove(row, col, color, gameMode);
    }
    
  }
  
  /**
   * Getter for Game Mode
   * @return GameMode of current model
   */
  public GameMode getGameMode(){
    return this.gameMode;
  }
}

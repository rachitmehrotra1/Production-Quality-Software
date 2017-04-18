package edu.nyu.cs.pqs.connectfour.model;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

  private List<Listener> listeners = new CopyOnWriteArrayList<Listener>();
  private Color[][] board;
  // Need a Array to keep a track of discs inserted in a column to make sure that columns
  // dont overfill
  private int[] discsInCol;
  private GameMode gameMode;
  private Player playerRed;
  private Player playerYellow;
  private Player playerTurn;

  /**
   * Private Constructor for Model
   */
  private Model() {}

  /**
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
   * Add new players to game based on the game mode and randomly decide who goes first
   * 
   * @param mode
   */
  public void addPlayers(GameMode mode) {
    this.gameMode = mode;
    if (mode.equals(GameMode.SINGLE)) {
      playerYellow =
          PlayerFactory.addNewPlayer(PlayerType.COMPUTER, ModelConstants.computerDefault);
      playerRed = PlayerFactory.addNewPlayer(PlayerType.HUMAN, ModelConstants.humanPlayer1);
    } else {
      playerYellow = PlayerFactory.addNewPlayer(PlayerType.HUMAN, ModelConstants.humanPlayer2);
      playerRed = PlayerFactory.addNewPlayer(PlayerType.HUMAN, ModelConstants.humanPlayer1);
    }
    // Randomly Generate Whose turn is it
    int random = (int) Math.round(Math.random());
    playerTurn = random == 0 ? playerRed : playerYellow;
  }

  /**
   * Fires up a game started event to notify all the listeners/view that the game has started
   */
  private void fireGameStartedEvent() {
    for (Listener listener : listeners) {
      listener.gameStarted();
    }
  }

  /**
   * Fires up a Game Reset event to notify all the listeners that the game is being reset
   */
  private void fireGameResetEvent() {
    for (Listener listener : listeners) {
      listener.gameReset();
    }
  }

  /**
   * Fires up a Alert event which displays a notification in all the views
   * 
   * @param message
   */
  private void fireAlertEvent(String message) {
    for (Listener listener : listeners) {
      listener.alert(message);
    }
  }

  /**
   * Fires up a game won event to notify all the listeners that game has been won by a player
   * 
   * @param color : The color that has won the game.
   */
  private void fireGameWonEvent(Color color) {
    for (Listener listener : listeners) {
      listener.gameWon(color);
    }
  }

  /**
   * Fires up a game tied event in the scenario where the baord is full and there is no spaces left
   * to play
   */
  private void fireGameTiedEvent() {
    for (Listener listener : listeners) {
      listener.gameTied();
    }
  }

  /**
   * Fires up a Move Made event to notify the listeners that a move has been made so that they can
   * update the board for the specific building
   * 
   * @param The row in which the disc was dropped
   * @param The column in which the disc was dropped
   * @param The color of the disc dropped
   */
  private void fireMoveMadeEvent(int row, int column, Color color) {
    for (Listener listener : listeners) {
      listener.makeMove(row, column, color, gameMode);
    }

  }

  /**
   * Getter for Game Mode
   * 
   * @return GameMode of current model
   */
  public GameMode getGameMode() {
    return this.gameMode;
  }

  /**
   * Model updates its internal data store to store the moves made by the player and checks if they
   * have led to any player winning or game being tied
   * 
   * @param color
   * @param column
   */
  public void makeMove(Color color, int column) {
    int currRow = discsInCol[column];
    discsInCol[column] = discsInCol[column] + 1;
    fireMoveMadeEvent(currRow, column, color);
    board[currRow][column] = color;
    if (checkWinner(currRow, column)) {
      fireGameWonEvent(color);
    }
    if (isBordFull()) {
      fireGameTiedEvent();
    }
  }

  /**
   * Checks if the board is full of discs i.e no space is left to make a move
   * Note: Should be private function , but made Default for Testing
   * @return
   */
  boolean isBordFull() {
    for (int i = 0; i < ModelConstants.ROWS; i++) {
      for (int j = 0; j < ModelConstants.COLS; j++) {
        if (board[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Implements the game logic to check if the specific move has led to the player (who made the
   * move) win the game By one of the 3 methods. (Horizontally , Vertically , Diagonally)
   * 
   * @param row
   * @param column
   * @return True/False based on the status of the game
   */
  private boolean checkWinner(int row, int column) {
    if (didWinHorizontally(row, column)) {
      return true;
    } else if (didWinVertically(row, column)) {
      return true;
    } else if (didWinDiagonallyTopLeftToRightDown(row, column)) {
      return true;
    } else if (didWinDiagonallyTopRightToLeftDown(row, column)) {
      return true;
    }
    return false;
  }

  /**
   * Check if the player has won the game Diagonally
   * Note: Should be private , but made default for testing!!
   * @param row
   * @param column
   * @return
   */
  boolean didWinDiagonallyTopRightToLeftDown(int row, int column) {
    int len = 0;
    Color colorAtCheckPost = board[row][column];
    // Go towards top right from the current position
    for (int i = row, j = column; i < ModelConstants.ROWS && j < ModelConstants.COLS; i++, j++) {
      if (board[i][j] == colorAtCheckPost) {
        len++;
      } else {
        break;
      }
    }
    // Go towards bottom left from current position (excluding the current position)
    for (int i = row, j = column; i > 0 && j > 0; i--, j--) {
      if (board[i - 1][j - 1] == colorAtCheckPost) {
        len++;
      } else {
        break;
      }
    }
    if (len >= ModelConstants.winLength) {
      return true;
    }
    return false;
  }

  /**
   * Check if the player has won the game Diagonally
   * Note: Should be private , but made default for testing!!
   * @param row
   * @param column
   * @return
   */
  boolean didWinDiagonallyTopLeftToRightDown(int row, int column) {
    int len = 0;
    Color colorAtCheckPost = board[row][column];
    // Go towards top left from the current position
    for (int i = row, j = column; i < ModelConstants.ROWS && j >= 0; i++, j--) {
      if (board[i][j] == colorAtCheckPost) {
        len++;
      } else {
        break;
      }
    }
    // Go towards bottom right from current position (excluding the current position)
    for (int i = row, j = column; i > 0 && j < ModelConstants.COLS - 1; i--, j++) {
      if (board[i - 1][j + 1] == colorAtCheckPost) {
        len++;
      } else {
        break;
      }
    }
    if (len >= ModelConstants.winLength) {
      return true;
    }
    return false;
  }

  /**
   * Check if the player has won the game Vertically
   * Note: Should be private , but made default for testing!!
   * @param row
   * @param column
   * @return
   */
  boolean didWinVertically(int row, int column) {
    int len = 0;
    int i = row;
    Color colorAtCheckPos = board[row][column];
    // Check toward the right from current position
    while (i < ModelConstants.ROWS && board[i][column] == colorAtCheckPos) {
      len++;
      i++;
    }
    // Reset to check position
    i = row;
    // Check towards left from the current position
    while (i > 0 && board[i - 1][column] == colorAtCheckPos) {
      i--;
      len++;
    }
    if (len >= ModelConstants.winLength) {
      return true;
    }
    return false;
  }

  /**
   * Check if the player has won the game Horizontally
   * Note: Should be private , but made default for testing!!
   * @param row
   * @param column
   * @return
   */
  boolean didWinHorizontally(int row, int column) {
    int len = 0;
    int i = column;
    Color colorAtCheckPos = board[row][column];
    // Check toward the right from current position
    while (i < ModelConstants.COLS && board[row][i] == colorAtCheckPos) {
      len++;
      i++;
    }
    // Reset to check position
    i = column;
    // Check towards left from the current position
    while (i > 0 && board[row][i - 1] == colorAtCheckPos) {
      i--;
      len++;
    }
    if (len >= ModelConstants.winLength) {
      return true;
    }
    return false;
  }

  /**
   * Drop the disc in the selected column based on button click
   * 
   * @param column
   */
  public void dropDisc(int column) {
    if (discsInCol[column] >= ModelConstants.ROWS) {
      fireAlertEvent("Column is full");
      throw new IllegalStateException("Column is Full");
    } else {
      if (gameMode.equals(GameMode.SINGLE)) {
        // Huaman Player Move Recorded
        playerRed.setColMove(column);
        playerRed.makeMove(this);
        // Bot making a move
        playerYellow.makeMove(this);
      } else {
        // Multiplayer Game
        if (playerTurn == playerRed) {
          playerRed.setColMove(column);
          playerRed.makeMove(this);
          playerTurn = playerYellow;
        } else {
          playerYellow.setColMove(column);
          playerYellow.makeMove(this);
          playerTurn = playerRed;
        }
      }
    }
  }

  /**
   * Check if the given column has any more space to drop discs in it
   * 
   * @param column
   * @return
   */
  public boolean doesColumnHasSpace(int column) {
    if (column > ModelConstants.COLS || column < 0) {
      throw new IllegalArgumentException("Column out of bounds");
    }
    if (discsInCol[column] + 1 >= ModelConstants.ROWS) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Reset the game's internal data store and fire the game reset event
   */
  public void gameReset() {
    board = new Color[ModelConstants.ROWS][ModelConstants.COLS];
    discsInCol = new int[ModelConstants.COLS];
    fireGameResetEvent();
  }

  /**
   * Fire the game started event to notify listeners
   */
  public void gameStarted() {
    board = new Color[ModelConstants.ROWS][ModelConstants.COLS];
    // Need a Array to keep a track of discs inserted in a column to make sure that columns
    // dont overfill
    discsInCol = new int[ModelConstants.COLS];
    fireGameStartedEvent();
  }

  /**
   * This method checks if a certain move is made , will that result in a Win This is used by the AI
   * to check if a certain move will result in a win.
   * 
   * @param column
   * @param color
   * @return
   */
  public boolean checkIfWinnerIfMoveMade(int column, Color color) {
    if (column < 0 || column > ModelConstants.COLS) {
      throw new IllegalArgumentException("Column out of bounds");
    }
    int i = discsInCol[column];
    if (i >= ModelConstants.ROWS) {
      // Since column is already full , it wont result in win
      return false;
    }
    board[i][column] = color;
    if (checkWinner(i, column)) {
      board[i][column] = null;
      return true;
    } else {
      board[i][column] = null;
      return false;
    }
  }
  
  /**
   * This method is used for unit tests only
   */
  List<Listener> getListenersForTesting(){
    return listeners;
  }
  
  /**
   * This method is used for unit tests only
   */
  void setDiscsInColumnForTesting(int column, int discs){
    discsInCol[column]=discs;
  }
  
  /**
   * This method is used for unit tests only
   */
  void putDiscAtPosInBoardForTesting(int row , int column , Color color){
    this.board[row][column]= color;
  }
  
  /**
   * This method is used for unit tests only
   */
  Color getColorAtPosFromBoardForTesting(int row,int column){
    return this.board[row][column];
  }
}

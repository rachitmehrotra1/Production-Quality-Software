package edu.nyu.cs.pqs.connectfour.player;

import java.awt.Color;

import edu.nyu.cs.pqs.connectfour.model.Model;
import edu.nyu.cs.pqs.connectfour.model.ModelConstants;

/**
 * Implementation of Player type, depicts a player controlled by a BOT
 * 
 * @author Rachit
 *
 */
// TODO : EQUALS AND HASHCODE
public class ComputerPlayer implements Player {

  /**
   * Inner Builder class to make the ComputerPlayer Object Immutable
   */
  public static class ComputerPlayerBuilder {
    private final Color playerColor;

    /**
     * Builder Constructor
     */
    public ComputerPlayerBuilder(Color playerColor) {
      this.playerColor = playerColor;
    }

    public ComputerPlayer build() {
      return new ComputerPlayer(this);
    }
  }

  private final Color playerColor;
  private int moveCol;

  /**
   * Private Computer Player Contructor
   * 
   * @param humanPlayerBuilder
   */
  private ComputerPlayer(ComputerPlayerBuilder humanPlayerBuilder) {
    this.playerColor = humanPlayerBuilder.playerColor;
  }

  @Override
  public void makeMove(Model model) {
    if (model.equals(null)) {
      throw new IllegalStateException("Game Model Not Initialized");
    }
    /*
     * We will make the AI a bit better than expected, not only will it check if next step is
     * winnable and play that it will also check if the human is going to win in the next step, IF
     * YES , then STOP the Human from winning.
     */
    int column = 0;
    boolean moveMade = false;
    while (column < ModelConstants.COLS) {
      if (model.checkIfWinnerIfMoveMade(column, playerColor)) {
        this.setColMove(column);
        model.makeMove(playerColor, column);
        moveMade = true;
        break;
      }
      column++;
    }
    /*
     * This means that the AI didn't find a move that would make it win in this turn, now we check
     * if the Human can win in the next turn , if yes , we stop Human from winning
     */
    if (!moveMade) {
      column = 0;
      while (column < ModelConstants.COLS) {
        if (model.checkIfWinnerIfMoveMade(column, ModelConstants.humanPlayer1)) {
          this.setColMove(column);
          model.makeMove(playerColor, column);
          moveMade = true;
          break;
        }
        column++;
      }
    }

    /*
     * If still no move made means no one can win in the next turn, thus make a random move
     */
    if (!moveMade) {
      while (true) {
        int randomColumn = (int) (Math.random() * (ModelConstants.COLS - 1));
        if (model.doesColumnHasSpace(randomColumn)) {
          this.setColMove(randomColumn);
          model.makeMove(playerColor, randomColumn);
          break;
        }
      }
    }
  }

  @Override
  public void setColMove(int col) {
    if (col > ModelConstants.COLS || col < 0) {
      throw new IllegalArgumentException("Column out of bounds");
    }
    this.moveCol = col;
  }

  @Override
  public int getColMove() {
    return this.moveCol;
  }

  @Override
  public Color getColor() {
    return this.playerColor;
  }

  @Override
  public PlayerType getPlayerType() {
    return PlayerType.COMPUTER;
  }

}

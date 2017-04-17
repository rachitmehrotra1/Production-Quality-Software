package edu.nyu.cs.pqs.connectfour.player;

import java.awt.Color;
import java.util.Objects;

import edu.nyu.cs.pqs.connectfour.model.Model;
import edu.nyu.cs.pqs.connectfour.model.ModelConstants;

/**
 * Implementation of Player type, depicts a player controlled by a human
 * 
 * @author Rachit
 *
 */
public class HumanPlayer implements Player {

  /**
   * Inner Builder class to make the HumanPlayer Object Immutable
   */
  public static class HumanPlayerBuilder {
    private final Color playerColor;

    /**
     * Builder Constructor
     */
    public HumanPlayerBuilder(Color playerColor) {
      this.playerColor = playerColor;
    }

    public HumanPlayer build() {
      return new HumanPlayer(this);
    }
  }

  private final Color playerColor;
  private int moveCol;

  /**
   * Private Human Player Contructor
   * 
   * @param humanPlayerBuilder
   */
  private HumanPlayer(HumanPlayerBuilder humanPlayerBuilder) {
    this.playerColor = humanPlayerBuilder.playerColor;
  }

  @Override
  public void makeMove(Model model) {
    if (model == null) {
      throw new IllegalStateException("Game Model Not Initialized");
    }
    model.makeMove(playerColor, moveCol);
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
    return PlayerType.HUMAN;
  }

  @Override
  public int hashCode() {
    return playerColor.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof HumanPlayer)) {
      return false;
    }
    HumanPlayer other = (HumanPlayer) obj;
    if (moveCol == other.moveCol && playerColor.equals(other.playerColor)
        && other.getPlayerType() == this.getPlayerType()) {
      return true;
    }
    return false;
  }

}

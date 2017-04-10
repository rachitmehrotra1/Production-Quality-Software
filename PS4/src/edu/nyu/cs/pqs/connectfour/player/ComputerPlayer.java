package edu.nyu.cs.pqs.connectfour.player;

import java.awt.Color;

import edu.nyu.cs.pqs.connectfour.model.Model;
import edu.nyu.cs.pqs.connectfour.model.ModelConstants;

/**
 * Implementation of Player type, depicts a player controlled by a BOT
 * @author Rachit
 *
 */
//TODO : EQUALS AND HASHCODE
public class ComputerPlayer implements Player {
  
  /**
   * Inner Builder class to make the ComputerPlayer Object Immutable
   */
  public static class ComputerPlayerBuilder {
    private final Color playerColor;
    
    /**
     * Builder Constructor
     */
    public ComputerPlayerBuilder(Color playerColor){
      this.playerColor = playerColor;
    }
    
    public ComputerPlayer build(){
      return new ComputerPlayer(this);
    }
  }
  
  private final Color playerColor;
  private int moveCol;
  
  /**
   * Private Computer Player Contructor
   * @param humanPlayerBuilder
   */
  private ComputerPlayer(ComputerPlayerBuilder humanPlayerBuilder) {
    this.playerColor=humanPlayerBuilder.playerColor;
  }

  @Override
  public void makeMove(Model model) {
    if(model.equals(null)){
      throw new IllegalStateException("Game Model Not Initialized");
    }
    // TODO call the model to make the move
    
  }

  @Override
  public void setColMove(int col) {
    if(col>ModelConstants.COLS||col<0){
      throw new IllegalArgumentException("Column out of bounds");
    }
    this.moveCol=col;
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

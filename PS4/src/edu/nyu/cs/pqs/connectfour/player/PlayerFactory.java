package edu.nyu.cs.pqs.connectfour.player;

import java.awt.Color;

/**
 * This is a factory class used to create players based on the given PlayerType
 * 
 * @author Rachit
 *
 */
public class PlayerFactory {

  public static Player addNewPlayer(PlayerType player, Color color) {
    if (player.equals(null) || color.equals(null)) {
      throw new IllegalArgumentException("Player type or Color is null");
    }
    if (player == PlayerType.HUMAN) {
      return new HumanPlayer.HumanPlayerBuilder(color).build();
    } else {
      return new ComputerPlayer.ComputerPlayerBuilder(color).build();
    }
  }
}

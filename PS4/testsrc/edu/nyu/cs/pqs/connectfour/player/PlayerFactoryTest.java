package edu.nyu.cs.pqs.connectfour.player;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

/**
 * Player factory test class
 * 
 * @author Rachit
 *
 */
public class PlayerFactoryTest {
  private PlayerFactory factory;

  @Test
  public void testHumanPlayerCreation() {
    Player testPlayer = factory.addNewPlayer(PlayerType.HUMAN, Color.BLACK);
    assertTrue("testPlayer should be of type HUMAN", testPlayer instanceof HumanPlayer);
    assertTrue(testPlayer.getPlayerType().equals(PlayerType.HUMAN));
    assertTrue(testPlayer.getColor().equals(Color.BLACK));
  }

  @Test
  public void testComputerPlayerCreation() {
    Player testPlayer = factory.addNewPlayer(PlayerType.COMPUTER, Color.BLUE);
    assertTrue("testPlayer should be of type HUMAN", testPlayer instanceof ComputerPlayer);
    assertTrue(testPlayer.getPlayerType().equals(PlayerType.COMPUTER));
    assertTrue(testPlayer.getColor().equals(Color.BLUE));
  }
}

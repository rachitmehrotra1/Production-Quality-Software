package edu.nyu.cs.pqs.connectfour.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.connectfour.model.ModelConstants;

/**
 * Unit test class to test the functions of the ComputerPlayer Object CodeCov:60% , since max part
 * of the class is the AI function makeMove which will be tested using the model in the model Unit
 * Tests
 * 
 * @author Rachit
 *
 */
public class ComputerPlayerTest {

  private ComputerPlayer cPlayer;

  @Before
  public void setUp() {
    cPlayer = new ComputerPlayer.ComputerPlayerBuilder(ModelConstants.computerDefault).build();
  }

  @Test
  public void testEqualsIsTrue() {
    ComputerPlayer localPlayer =
        new ComputerPlayer.ComputerPlayerBuilder(ModelConstants.computerDefault).build();
    assertTrue(localPlayer.equals(cPlayer));
    cPlayer.setColMove(3);
    localPlayer.setColMove(3);
    assertTrue(localPlayer.equals(cPlayer));
    assertTrue(cPlayer.equals(cPlayer));
  }

  @Test
  public void testEqualsIsFalse() {
    ComputerPlayer localPlayer = new ComputerPlayer.ComputerPlayerBuilder(Color.BLACK).build();
    localPlayer.setColMove(5);
    assertFalse(localPlayer.equals(cPlayer));
    cPlayer.setColMove(5);
    assertFalse(localPlayer.equals(cPlayer));
    assertFalse(cPlayer.equals(null));
    assertFalse(cPlayer.equals(new Integer(1)));
  }

  @Test
  public void testGetPlayerTypeFalse() {
    PlayerType expected = PlayerType.HUMAN;
    PlayerType real = cPlayer.getPlayerType();
    assertFalse(real.equals(expected));
  }

  @Test
  public void testGetPlayerTypeTrue() {
    PlayerType expected = PlayerType.COMPUTER;
    PlayerType real = cPlayer.getPlayerType();
    assertTrue(real.equals(expected));
  }

  @Test
  public void testGetColorFalse() {
    Color expected = Color.BLACK;
    Color real = cPlayer.getColor();
    assertFalse(real.equals(expected));
  }

  @Test
  public void testGetColorTrue() {
    Color expected = ModelConstants.computerDefault;
    Color real = cPlayer.getColor();
    assertTrue(real.equals(expected));
  }

  @Test
  public void testHashCode() {
    ComputerPlayer localPlayer =
        new ComputerPlayer.ComputerPlayerBuilder(ModelConstants.computerDefault).build();
    assertTrue(cPlayer.hashCode() == localPlayer.hashCode());
  }

  @Test
  public void testSetColMove() {
    cPlayer.setColMove(2);
    int expectedColumn = 2;
    assertEquals(expectedColumn, cPlayer.getColMove());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColMoveException_Negative() {
    cPlayer.setColMove(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColMoveException_ExceedMax() {
    cPlayer.setColMove(10);
  }

  /*
   * Since make move requires a proper model for testing it, I will test the make move function of
   * this class with the Model Unit Tests
   */
  @Test(expected = IllegalStateException.class)
  public void makeMove_nullInput() {
    cPlayer.makeMove(null);
  }
}

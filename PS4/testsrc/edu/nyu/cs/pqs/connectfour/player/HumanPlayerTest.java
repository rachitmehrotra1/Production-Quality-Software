package edu.nyu.cs.pqs.connectfour.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.connectfour.model.ModelConstants;

/**
 * Unit test class to test the functions of the HumanPlayer Object CodeCov:93%
 * 
 * @author Rachit
 *
 */
public class HumanPlayerTest {

  private HumanPlayer hPlayer;

  @Before
  public void setUp() {
    hPlayer = new HumanPlayer.HumanPlayerBuilder(ModelConstants.humanPlayer1).build();
  }

  /*
   * Since make move requires a proper model for testing it, I will test the make move function of
   * this class with the Model Unit Tests
   */
  @Test(expected = IllegalStateException.class)
  public void makeMove_nullInput() {
    hPlayer.makeMove(null);
  }

  @Test
  public void testEqualsIsTrue() {
    HumanPlayer localPlayer =
        new HumanPlayer.HumanPlayerBuilder(ModelConstants.humanPlayer1).build();
    assertTrue(localPlayer.equals(hPlayer));
    hPlayer.setColMove(3);
    localPlayer.setColMove(3);
    assertTrue(localPlayer.equals(hPlayer));
    assertTrue(hPlayer.equals(hPlayer));
  }

  @Test
  public void testEqualsIsFalse() {
    HumanPlayer localPlayer = new HumanPlayer.HumanPlayerBuilder(Color.BLACK).build();
    localPlayer.setColMove(5);
    assertFalse(localPlayer.equals(hPlayer));
    hPlayer.setColMove(5);
    assertFalse(localPlayer.equals(hPlayer));
    assertFalse(hPlayer.equals(null));
    assertFalse(hPlayer.equals(new Integer(1)));
  }

  @Test
  public void testGetPlayerTypeFalse() {
    PlayerType expected = PlayerType.COMPUTER;
    PlayerType real = hPlayer.getPlayerType();
    assertFalse(real.equals(expected));
  }

  @Test
  public void testGetPlayerTypeTrue() {
    PlayerType expected = PlayerType.HUMAN;
    PlayerType real = hPlayer.getPlayerType();
    assertTrue(real.equals(expected));
  }

  @Test
  public void testGetColorFalse() {
    Color expected = Color.BLACK;
    Color real = hPlayer.getColor();
    assertFalse(real.equals(expected));
  }

  @Test
  public void testGetColorTrue() {
    Color expected = ModelConstants.humanPlayer1;
    Color real = hPlayer.getColor();
    assertTrue(real.equals(expected));
  }

  @Test
  public void testHashCode() {
    HumanPlayer localPlayer =
        new HumanPlayer.HumanPlayerBuilder(ModelConstants.humanPlayer1).build();
    assertTrue(hPlayer.hashCode() == localPlayer.hashCode());
  }

  @Test
  public void testSetColMove() {
    hPlayer.setColMove(2);
    int expectedColumn = 2;
    assertEquals(expectedColumn, hPlayer.getColMove());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColMoveException_Negative() {
    hPlayer.setColMove(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColMoveException_ExceedMax() {
    hPlayer.setColMove(10);
  }


}

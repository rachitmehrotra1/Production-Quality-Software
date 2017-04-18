package edu.nyu.cs.pqs.connectfour.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

// TODO : Test gameReset and exception in dropDisc !! 
public class ModelTest {

  Model model = Model.getModelInstance();

  @Before
  public void setUp() {
    model.gameStarted();
  }

  @Test
  public void testDoesColumnHasSpaces() {
    model.setDiscsInColumnForTesting(2, 6);
    model.setDiscsInColumnForTesting(4, 1);
    // Would return false since column 2 is full
    assertFalse(model.doesColumnHasSpace(2));
    // Would return true since column has space
    assertTrue(model.doesColumnHasSpace(4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoesColumnHasSpacesExceedMaxCol() {
    model.doesColumnHasSpace(8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoesColumnHasNegativeCol() {
    model.doesColumnHasSpace(-1);
  }

  /*
   * To check the "checkWinner" function we use the checkIfWinnerIfMoveMade function since it does
   * the exact same thing that DropDisc does which calls checkWinner and thus simulates a game Move
   * from UI
   */
  @Test
  public void testCheckWinnerTrue() {
    model.putDiscAtPosInBoardForTesting(1, 2, Color.RED);
    model.putDiscAtPosInBoardForTesting(1, 3, Color.RED);
    model.putDiscAtPosInBoardForTesting(1, 4, Color.RED);
    model.setDiscsInColumnForTesting(5, 1);
    assertTrue(model.checkIfWinnerIfMoveMade(5, Color.RED));
  }

  @Test
  public void testCheckWinnerFalse() {
    model.putDiscAtPosInBoardForTesting(1, 2, Color.RED);
    model.putDiscAtPosInBoardForTesting(1, 3, Color.BLUE);
    model.putDiscAtPosInBoardForTesting(1, 4, Color.BLUE);
    model.setDiscsInColumnForTesting(5, 1);
    assertFalse(model.checkIfWinnerIfMoveMade(5, Color.RED));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckWinnerNegativeCol() {
    model.checkIfWinnerIfMoveMade(-2, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckWinnerExceedMaxCol() {
    model.checkIfWinnerIfMoveMade(12, Color.RED);
  }

  @Test
  public void testCheckEqualsRow() {
    model.setDiscsInColumnForTesting(5, 6);
    assertFalse(model.checkIfWinnerIfMoveMade(5, Color.RED));
  }

  @Test
  public void testIsBoardFull() {
    // Since empty board thus shouold fail
    assertFalse(model.isBordFull());
    for (int i = 0; i < ModelConstants.ROWS; i++) {
      for (int j = 0; j < ModelConstants.COLS; j++) {
        model.putDiscAtPosInBoardForTesting(i, j, Color.RED);
      }
    }
    assertTrue(model.isBordFull());
  }

  @Test
  public void testDidWinVerticallyTrue() {
    model.putDiscAtPosInBoardForTesting(2, 1, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(3, 1, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(4, 1, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(5, 1, ModelConstants.computerDefault);
    assertTrue(model.didWinVertically(5, 1));
  }

  @Test
  public void testDidWinVerticallyFalse() {
    model.putDiscAtPosInBoardForTesting(2, 1, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(3, 1, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(4, 1, ModelConstants.humanPlayer1);
    model.putDiscAtPosInBoardForTesting(5, 1, ModelConstants.computerDefault);
    assertFalse(model.didWinVertically(5, 1));
  }

  @Test
  public void testDidWinHorizontallyTrue() {
    model.putDiscAtPosInBoardForTesting(1, 2, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(1, 3, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(1, 4, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(1, 5, ModelConstants.computerDefault);
    assertTrue(model.didWinHorizontally(1, 5));
  }

  @Test
  public void testDidWinHorizontallyFalse() {
    model.putDiscAtPosInBoardForTesting(1, 2, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(1, 3, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(1, 4, ModelConstants.humanPlayer1);
    model.putDiscAtPosInBoardForTesting(1, 5, ModelConstants.computerDefault);
    assertFalse(model.didWinHorizontally(1, 5));
  }

  @Test
  public void testDidWinDiagonallyTopRightToLeftDownTrue() {
    model.putDiscAtPosInBoardForTesting(4, 4, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(3, 3, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(2, 2, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(1, 1, ModelConstants.computerDefault);
    assertTrue(model.didWinDiagonallyTopRightToLeftDown(1, 1));
  }

  @Test
  public void testDidWinDiagonallyTopRightToLeftDownFalse() {
    model.putDiscAtPosInBoardForTesting(4, 4, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(3, 3, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(2, 2, ModelConstants.humanPlayer1);
    model.putDiscAtPosInBoardForTesting(1, 1, ModelConstants.computerDefault);
    assertFalse(model.didWinDiagonallyTopRightToLeftDown(1, 1));
  }

  @Test
  public void testDidWinDiagonallyTopLeftToRightDownTrue() {
    model.putDiscAtPosInBoardForTesting(3, 0, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(2, 1, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(1, 2, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(0, 3, ModelConstants.computerDefault);
    assertTrue(model.didWinDiagonallyTopLeftToRightDown(3, 0));
  }

  @Test
  public void testDidWinDiagonallyTopLeftToRightDownFalse() {
    model.putDiscAtPosInBoardForTesting(3, 0, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(2, 1, ModelConstants.computerDefault);
    model.putDiscAtPosInBoardForTesting(1, 2, ModelConstants.humanPlayer1);
    model.putDiscAtPosInBoardForTesting(0, 3, ModelConstants.computerDefault);
    assertFalse(model.didWinDiagonallyTopLeftToRightDown(3, 0));
  }

  @Test
  public void testMakeMoveForComputerPlayer() {
    model.addPlayers(GameMode.SINGLE);
    // Trigger a move by a human player, so that after that the bot makes a move
    model.dropDisc(0);
    // AS the bot will make a random move , any one of the position 1,0 or 0,2 or 0,3 or 0,4 or 0,5
    // or 0,6 or 0,1 should have the Bot disc
    assertTrue(model.getColorAtPosFromBoardForTesting(1, 0) == ModelConstants.computerDefault
        || model.getColorAtPosFromBoardForTesting(0, 1) == ModelConstants.computerDefault
        || model.getColorAtPosFromBoardForTesting(0, 2) == ModelConstants.computerDefault
        || model.getColorAtPosFromBoardForTesting(0, 3) == ModelConstants.computerDefault
        || model.getColorAtPosFromBoardForTesting(0, 4) == ModelConstants.computerDefault
        || model.getColorAtPosFromBoardForTesting(0, 5) == ModelConstants.computerDefault
        || model.getColorAtPosFromBoardForTesting(0, 6) == ModelConstants.computerDefault);
  }
  
  @Test
  public void testMakeMoveForHumanPlayers() {
    model.addPlayers(GameMode.MULTIPLAYER);
    //Since who goes first is random , we will make 2 moves and make sure that the discs are 
    //opposite in both the positions
    model.dropDisc(2);
    model.dropDisc(3);
    assertFalse(model.getColorAtPosFromBoardForTesting(0, 2) == model.getColorAtPosFromBoardForTesting(0, 3));
  }
}

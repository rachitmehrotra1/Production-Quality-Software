package edu.nyu.cs.pqs.connectfour.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.nyu.cs.pqs.connectfour.model.GameMode;
import edu.nyu.cs.pqs.connectfour.model.Model;

/**
 * The Welcome View of the game providing the user to choose between Single Player or Multiplayer
 * Mode
 * 
 * @author Rachit
 *
 */
public class WelcomeView {
  private JFrame welcomeFrame = new JFrame("Connect4 Application");
  private JButton singlePlayer = new JButton("Human vs Computer");
  private JButton multiPlayer = new JButton("Human vs Human");

  /**
   * Public constructor to create the view and display it
   */
  public WelcomeView() {
    createWelcomeView();
    addActionsToButtons();
  }

  /**
   * Create a welcome view that gives the users to choose the type of game they want to play
   */
  private void createWelcomeView() {
    JPanel buttonPanel = new JPanel();

    singlePlayer.setSize(new Dimension(150, 100));
    multiPlayer.setSize(new Dimension(150, 100));
    buttonPanel.setLayout(new BorderLayout());
    welcomeFrame.setLayout(new BorderLayout());
    welcomeFrame.setSize(500, 75);
    // To make sure that the JFrame Appears in the center
    welcomeFrame.setLocationRelativeTo(null);
    welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    buttonPanel.add(singlePlayer, BorderLayout.NORTH);
    buttonPanel.add(multiPlayer, BorderLayout.SOUTH);
    welcomeFrame.add(buttonPanel, BorderLayout.CENTER);

    welcomeFrame.setVisible(true);
  }

  /**
   * Add the required action listeners to the buttons present in the view
   */
  private void addActionsToButtons() {
    singlePlayer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        singlePlayerButtonPressed();
      }
    });
    multiPlayer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        multiPlayerButtonPressed();
      }
    });
  }

  /**
   * Steps to be performed when a single player button is pressed on the view
   */
  private void singlePlayerButtonPressed() {
    Model singlePlayerGame = Model.getModelInstance();
    new GameView(singlePlayerGame);
    singlePlayerGame.gameStarted();
    singlePlayerGame.addPlayers(GameMode.SINGLE);
    welcomeFrame.setVisible(false);
  }

  /**
   * Steps to be performed when a multi-player button is pressed on the view
   */
  private void multiPlayerButtonPressed() {
    Model multiPlayerGame = Model.getModelInstance();
    new GameView(multiPlayerGame);
    multiPlayerGame.gameStarted();
    multiPlayerGame.addPlayers(GameMode.MULTIPLAYER);
    welcomeFrame.setVisible(false);
  }

  @Override
  public int hashCode() {
    return Objects.hash(welcomeFrame.hashCode(), singlePlayer.hashCode(), multiPlayer.hashCode());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    WelcomeView other = (WelcomeView) obj;
    if (this.hashCode() == other.hashCode()) {
      return true;
    }
    return false;
  }
}

package edu.nyu.cs.pqs.connectfour.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
// TODO HashCode and Equals!!
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
  
  private void singlePlayerButtonPressed(){
    Model singlePlayerGame = Model.getModelInstance();
    new GameView(singlePlayerGame);
    singlePlayerGame.gameStarted();
    singlePlayerGame.addPlayers(GameMode.SINGLE);
    welcomeFrame.setVisible(false);
  }
  
  private void multiPlayerButtonPressed(){
    Model multiPlayerGame = Model.getModelInstance();
    new GameView(multiPlayerGame);
    multiPlayerGame.gameStarted();
    multiPlayerGame.addPlayers(GameMode.MULTIPLAYER);
    welcomeFrame.setVisible(false);
  }
}

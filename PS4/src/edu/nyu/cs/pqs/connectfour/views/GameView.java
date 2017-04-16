package edu.nyu.cs.pqs.connectfour.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import edu.nyu.cs.pqs.connectfour.model.GameMode;
import edu.nyu.cs.pqs.connectfour.model.Model;
import edu.nyu.cs.pqs.connectfour.model.ModelConstants;

/**
 * This view Contains the main game and all the listeners associated with it.It also listens for any
 * changes from the underlying model and depict those changes
 * 
 * @author Rachit
 *
 */
public class GameView implements Listener {
  private Model model;
  private JFrame gameFrame = new JFrame("Connect4 Application");
  private JPanel boardPanel = new JPanel();
  // private JPanel[][] boardPositionPanels= new JPanel[ModelConstants.ROWS][ModelConstants.COLS];
  private JLabel[][] boardPositionPanels = new JLabel[ModelConstants.ROWS][ModelConstants.COLS];
  private JPanel dropPanel = new JPanel();
  private JButton[] dropButtons = new JButton[ModelConstants.COLS];
  private JTextField gameStatusField = new JTextField();
  private JPanel infoPanel = new JPanel();
  private JButton gameReset = new JButton("Reset");



  public GameView(Model model) {
    this.model = model;
    this.model.addListener(this);
  }

  @Override
  public void gameStarted() {
    boardPanel.setLayout(new GridLayout(ModelConstants.ROWS, ModelConstants.COLS));
    dropPanel.setLayout(new GridLayout(0, ModelConstants.COLS));
    infoPanel.setLayout(new BorderLayout());

    // Create BoardPanels for Game Positions
    for (int i = 0; i < ModelConstants.ROWS; i++) {
      for (int j = 0; j < ModelConstants.COLS; j++) {
        boardPositionPanels[i][j] = new JLabel(ViewResources.getConnect4Disc(null));
        boardPanel.add(boardPositionPanels[i][j]);
      }
    }
    // Create Drop Button to drop discs
    for (int i = 0; i < ModelConstants.COLS; i++) {
      JButton drop = new JButton("drop");
      drop.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          dropButtonPressed(e.getSource());
        }
      });
      dropButtons[i] = drop;
      dropPanel.add(dropButtons[i]);
    }
    gameReset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        model.gameReset();
      }
    });

    infoPanel.add(new JScrollPane(gameStatusField), BorderLayout.CENTER);
    infoPanel.add(gameReset, BorderLayout.EAST);
    gameFrame.add(dropPanel, BorderLayout.NORTH);
    gameFrame.add(boardPanel, BorderLayout.CENTER);
    gameFrame.add(infoPanel, BorderLayout.SOUTH);
    gameFrame.setSize(700, 700);
    // To make sure that the JFrame Appears in the center
    gameFrame.setLocationRelativeTo(null);
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    gameFrame.setVisible(true);
  }

  private void dropButtonPressed(Object source) {
    for (int i = 0; i < ModelConstants.COLS; i++) {
      if (source == dropButtons[i]) {
        model.dropDisc(i);
      }
    }
  }

  @Override
  public void gameTied() {
    for (JButton button : dropButtons) {
      for (ActionListener listener : button.getActionListeners()) {
        button.removeActionListener(listener);
      }
    }
    JOptionPane.showMessageDialog(gameFrame, "Game Tied! I'll start another one for you",
        "Game Tied!", JOptionPane.INFORMATION_MESSAGE);
    model.gameReset();
  }

  @Override
  public void gameWon(Color color) {
    if (color == Color.RED) {
      JOptionPane.showMessageDialog(gameFrame,
          "Congratulations! Red Wins! Let's Play another one.", "Game Over!",
          JOptionPane.INFORMATION_MESSAGE);
    } else if (color == Color.YELLOW) {
      JOptionPane.showMessageDialog(gameFrame,
          "Congratulations! Yellow Wins! Let's Play another one.", "Game Over!",
          JOptionPane.INFORMATION_MESSAGE);
    }
    // Once the game has been won we need to disable the buttons so that drop buttons dont work
    // anymore
    // Since the actionslisteners are anonymous we need to get them from the button and remove
    for (JButton button : dropButtons) {
      for (ActionListener listener : button.getActionListeners()) {
        button.removeActionListener(listener);
      }
    }
    model.gameReset();
  }

  @Override
  public void makeMove(int row, int col, Color color, GameMode mode) {
    // If it's a Human vs Human Game we need to display in Status Text whose turn's next
    if (mode.equals(GameMode.MULTIPLAYER)) {
      if (color == Color.RED) {
        gameStatusField.setText("Yellow Goes Next");
      } else if (color == Color.YELLOW) {
        gameStatusField.setText("Red Goes Next");
      }
    }
    // Since the panel index start from top to botton, but in game rows start from botton
    // To get the exact row, we need to do TOTAL ROWS-row-1 to get the exact position where
    // disc is dropped
    // We also need to redraw the board with the new added Image , this thing was easily avoidable
    // by using
    // .setBackground, but I wanted to learn how to use ImageIcon thus I used this method to get
    // better UI
    boardPanel.removeAll();
    boardPositionPanels[ModelConstants.ROWS - 1 - row][col] =
        new JLabel(ViewResources.getConnect4Disc(color));
    // gameFrame.paint(gameFrame.getGraphics());
    for (int i = 0; i < ModelConstants.ROWS; i++) {
      for (int j = 0; j < ModelConstants.COLS; j++) {
        boardPanel.add(boardPositionPanels[i][j]);
      }
    }
    boardPanel.revalidate();
  }

  @Override
  public void alert(String message) {
    JOptionPane.showMessageDialog(gameFrame, message, "Invalid move", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void gameReset() {
    boardPanel.removeAll();
    dropPanel.removeAll();
    infoPanel.removeAll();
    for (ActionListener listener : gameReset.getActionListeners()) {
      gameReset.removeActionListener(listener);
    }
    gameStarted();
  }

  @Override
  public int hashCode() {
    return Objects.hash(gameFrame.hashCode(), boardPanel.hashCode(), dropPanel.hashCode(),
        infoPanel.hashCode());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    GameView other = (GameView) obj;
    if (this.hashCode() == other.hashCode()) {
      return true;
    }
    return false;
  }
}

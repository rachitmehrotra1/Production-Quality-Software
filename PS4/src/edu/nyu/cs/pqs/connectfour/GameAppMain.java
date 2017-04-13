package edu.nyu.cs.pqs.connectfour;

import edu.nyu.cs.pqs.connectfour.views.WelcomeView;

/**
 * Starts the Game by displaying the game type screen
 * @author Rachit
 *
 */
public class GameAppMain {
  private void begin(){
    new WelcomeView();
  }
  
  public static void main(String[] args){
    new GameAppMain().begin();
  }
}

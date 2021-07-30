package ConsoleHangman.src;

import java.util.Scanner;
import cs102.*;
import Hangman.*;

/**
 * ConsoleHangman
 *
 * @author Melisa Tanrikulu
 * @version 16.07.2021
 */

public class ConsoleHangman
{
    public static void main( String[] args)
	{
    	Scanner scan = new Scanner( System.in);

    	System.out.println( "Start of ConsoleHangman");

		// VARIABLES
    	HangmanModel		hangman;
    	String 				input;
	    boolean 			quitGame;
	    char				letter;

		// PROGRAM CODE
		hangman = new HangmanModel( );
		ConsoleHangmanView view = new ConsoleHangmanView();
		hangman.addView(view);

		// Allows user to repeatedly enter a letter and tryThis letter
		// then shows game status, until gameover. Finally reports win/lose.
	    
	    do {
	      // Initialization
	      hangman.initNewGame();
	      quitGame = true;

	      // Allows user to repeatedly enter a letter and tryThis letter
	      // until game is over
	      do {
	        letter = scan.next().charAt(0);
	        hangman.tryThis(letter);
	      }
	      while ( !hangman.isGameOver() );
	      
	      // Prints a message depending on whether the player has lost or not
	      if ( hangman.hasLost() )
	        System.out.print("\nYou have lost the game. ");
	      
	      else
	        System.out.print("\nCongratulations! You have won the game. ");
	      
	      // Asks the player if they wants to play again
	      System.out.print("Do you want to play again? (Y/N) : ");
	      input = scan.next();
	      
	      if ( input.equalsIgnoreCase("y") )
	        quitGame = false;
	    }
	    // Exits the game
	    while ( !quitGame );
	    
	    scan.close();
	    System.out.println( "Goodbye!" );


		System.out.println( "\nEnd of ConsoleHangman\n" );
	}

} // end of class ConsoleHangman

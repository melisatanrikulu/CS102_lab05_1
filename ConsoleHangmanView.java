package ConsoleHangman.src;

import cs102.Hangman;

/**
 * ConsoleHangmanView
 * 
 * @author Melisa Tanrikulu
 * @version 16.07.2021
 */

public class ConsoleHangmanView implements IHangmanView {
	
	/*
	 * Updates the view.
	 * @param hangman game
	 */
	public void updateView( Hangman hangmanModel) {
		// VARIABLES
		int trialCount;
    	int leftTrialCount;
    			
    	trialCount = hangmanModel.getNumOfIncorrectTries();
        leftTrialCount = hangmanModel.getMaxAllowedIncorrectTries() - trialCount;
        
        // Displays the view
        System.out.println();
        System.out.println( hangmanModel.getKnownSoFar() );    
        
        if ( !hangmanModel.isGameOver() ) {
        	System.out.println("Used letters: " + hangmanModel.getUsedLetters());
        	System.out.println("You have " + leftTrialCount + " many left trial(s).\n");
        	System.out.print("Please enter a letter: ");
        }
	}

}

package Hangman;

import cs102.Hangman;
import java.util.ArrayList;
import ConsoleHangman.src.*;

/**
 * HangmanModel
 * 
 * @author Melisa Tanrikulu
 * @version 16.07.2021
 */
public class HangmanModel extends Hangman {
	
	// INSTANCE DATA MEMBERS
	ArrayList<IHangmanView> views;
	
	/*
	 * Constructor
	 */
	public HangmanModel() {
		super( new BasicSetup() );
		views = new ArrayList<IHangmanView>();
	}
	
	/*
	 * Adds the view to the array list.
	 * @param view
	 */
	public void addView(IHangmanView view) {
		views.add(view);
	}
	
	/*
	 * Updates the views.
	 */
	public void update() {
		if ( views != null) {
			for ( IHangmanView view : views ) {
				if ( view != null )
					view.updateView(this);
			}
		}
	}
	
	/*
	 * Tries the letter.
	 * @param letter
	 */
	public int tryThis(char letter) {
		int numberOfOccurrences  = super.tryThis(letter);
		update();
		
		return numberOfOccurrences;
	}
	
	/*
	 * Initializes a new game.
	 */
	public void initNewGame() {
		super.initNewGame();
		update();
	}

}

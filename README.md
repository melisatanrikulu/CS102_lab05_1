# CS102_lab05_a

Lab05 - The Hangman Game GUI & MVC
In this lab, you will create a GUI version of the hangman game. Your solution will use the MVC (Model-View-Controller) design pattern. This pattern separates the core logic of the game (the model), from the way in which the user interacts with it--how the user views and controls it. There are literally hundreds of variations of this basic theme. This lab introduces you to one that is (hopefully) easy to understand, yet principled and extensible. This should allow you to write a simple program, to begin with, and later, with minimum changes/effort, adapt it to much more sophisticated situations.

VCS Git & GitHub
Before starting the lab, go to GitHub by using your account and open a private repository called CS102_lab02_hangman. Whenever you are done with one of the following parts of the lab you will need to add all of your changes, commit them, and push them to your private and remote repository. Make sure that your repository is indeed private because if it is not then anyone on the web will be able to copy your homework, which will get you into trouble. For every part of the lab, you should have at least one commit that has a clear message of what was implemented in that commit and for which part.
Note that you are going to use the same repository for both parts 1 and 2 of the lab (coding and GUI). You are advised to upload pictures of your UI to your repository's README.md, so that when you make the repository public(once you finish CS102), people could see the work you have done.

Part (a)
The basic MVC pattern allows for multiple controls to update a model, which then tells its views to update themselves to reflect the model's new state. In this assignment, the model (the core logic) is the hangman game. You are given this. It is a slightly modified implementation of the hangman homework many of you attempted at the beginning of the semester. The original design looked like this:

class Hangman

constructors
+ Hangman()  // default max 6 incorrect tries, English alphabet, chooses secretWord from fixed list.
methods
+ getAllLetters() : String   // returns the set of valid letters, i.e. the alphabet
+ getUsedLetters() : String  // returns all letters so far tried
+ getNumOfIncorrectTries() : int   // returns number of incorrect letters tried so far
+ getMaxAllowedIncorrectTries : int  // returns maximum number of incorrect letters that can be tried before game over
+ getKnownSoFar() : String // returns partial word formed with currently known letters only
+ tryThis( letter) : int   // updates knownsofar with letter and returns number of occurrences of letter in secretWord
+ isGameOver() : boolean  // returns true if game is over, i.e. too many incorrect letters tried or complete word found
+ hasLost() : boolean  // returns true is game over and word not completed
- chooseSecretWord() // private, called from the constructor, fixed list of words from which one is chosen at random
With this design, changing the set of words, using a different way of choosing the secret word, using a different alphabet, or changing the maximum number of incorrect tries, is difficult without actually editing the code. Before continuing, take a moment to think about what you would have to modify.

One solution--based on the Strategy design pattern--is shown below. We define an interface, IHangmanSetup, which includes the set of methods that might need changing. We then pass an instance of a class that implements this interface to the constructor of our Hangman class and use it to initialize everything.

    interface Hangman
+ int getMaxAllowedIncorrectTries();  // usually 6 for normal hangman game
+ char getBlankChar();   // char to be used in place of letters not yet known, usually '*'
+ String getCharsToPreserve();  // set of chars preserved in secret word, usually just space, i.e " "
+ String getAllLetters();  // alphabet, for example all letters in English alphabet
+ String chooseSecretWord();
    class Hangman
constructors
+ Hangman( IHangmanSetup)  // uses an instance of IHangmanSetup to initialise the class.
methods
+ getAllLetters() : String   // returns the set of valid letters, i.e. the alphabet
+ getUsedLetters() : String  // returns all letters so far tried
+ getNumOfIncorrectTries() : int   // returns number of incorrect letters tried so far
+ getMaxAllowedIncorrectTries : int  // returns maximum number of incorrect letters that can be tried before game over
+ getKnownSoFar() : String // returns partial word formed with currently known letters only
+ tryThis( char) : int   // updates knownsofar with letter and returns number of occurrences of letter in secretWord
+ isGameOver() : boolean  // returns true if game is over, i.e. too many incorrect letters tried or complete word found
+ hasLost() : boolean  // returns true is game over and word not completed
+ initNewGame()  // resets game with new secret word.
Download the JCreator lab05 Project and extract it into your working folder for lab05. Note that it contains three projects: Hangman, ConsoleHangman, and GUIHangman. The Hangman project contains the IHangmanSetup interface and Hangman class, both of which are in a package called cs102. Only the Java class files are made included, not the source code. Ignore the GUIHangman project, for now, your first task is to complete the ConsoleHangman project. Edit its main method so it creates an instance of the Hangman class, prints the game status (knownsofar), then allows the user to play the game by repeatedly entering a letter and again printing the game's status. When the game is over, print an appropriate message (win/lose).

Note: you will need to include the class files from the Hangman project into the ConsoleHangman project. Do this as follows...

How to include another JCreator project in the current build (e.g. cs102!)
In JCreator, go to the Project-ProjectSettings-RequiredLibraries tab. It says cs102 there, but probably shows it in red to indicate it can't find it! Select it, then click Edit, then Add Path... and browse to the folder -within the Hangman project- that contains the cs102 folder, (i.e. ...\lab05\Hangman\classes). Click Ok. Next, select the old path and Delete it. Press Ok (cs102 should no longer be red). Ensure the checkbox is ticked, then press Ok again to return to the main JCreator window. Hopefully, the project will compile nicely now.




Part (b)
The next task is to associate a view with the model and have the model tell the view to update itself whenever it is changed. You already know the advantages of Java interfaces, so rather than fixing the view type, you define an interface, IHangmanView, which has a single method updateView( Hangman) whose parameter allows the view to access the Hangman model, if necessary. The ConsoleHangman project includes this interface.

The next step is to modify the model so it updates the view(s) whenever its state changes. Since you don't have the code for the model (the cs102.Hangman class), all you can do is subclass it. Create this new subclass, calling it HangmanModel. It should have an IHangmanView property and provide a method addView( IHangmanView) that sets the view, plus another method, update(), that calls the view's updateView method. Finally, it should override any methods that affect the model's state, such that they not only perform as before but afterward call update() to tell the view to update itself. In the case of the Hangman game, only the tryThis & initNewGame methods change its state and so need overriding.

You will need a view, so write a new class ConsoleHangmanView that implements IHangmanView such that--when its updateView(Hangman) method is called--it prints out the status of the Hangman game on the console.

Finally, make a copy of your main method from part (a), so you can refer back to it if necessary, then modify the original such that it uses an instance of HangmanModel (instead of Hangman) and adds an instance of your ConsoleHangmanView to it as the view. Make your program function much the same as it did before, but now each time the user enters a letter, it simply calls tryThis(letter). This, then, automatically causes the view to display the game's status on the console--using the ConsoleHangmanView object.

Part (c)
The last task is to modify your HangmanModel class so that it can support any number of views. Change the view property to an ArrayList of IHangmanView's, called views. Initialize views in the constructor. Modify addView appropriately and modify update() so that it calls the updateView method of each IHangmanView instance in the ArrayList.

Demonstrate your new multi-view HangmanModel class by adding another ConsoleHangmanView to the Hangman instance in your main method.

Extra: Have the game use your own set of words (hint, write a new class that implements IHangmanSetup).

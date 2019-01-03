///////////////////////////////////////////////////////////////////////////////
// Title:            PortalSnake
// Files:            Game.java
//                   Apple.java
//                   Rock.java
//                   PortalPair.java
//                   Snake.java
// Semester:         Fall 2015
//
// Author:           Nawal Dua
// Email:            ndua2@wisc.edu
// CS Login:         nawal
// Lecturer's Name:  Deb Deppler
// Lab Section:      311
///////////////////////////////////////////////////////////////////////////////
import java.util.*;
/**
 * This class is where the game basically runs, the snake, rocks, apples and
 * and portal pairs are initialized for each level in this class and the game
 * is updated in the class. This class also has the main method.
 *
 * <p>Bugs: None that are known
 *
 * @author Nawal Dua
 */
public class Game 
{	
	// Store the instances of the game objects that you create for your game
	// in these member variables: 
	private Snake snake;
	private ArrayList<Apple> apples = new ArrayList<Apple>();		
	private ArrayList<Rock> rocks = new ArrayList<Rock>();
	private ArrayList<PortalPair> portals = new ArrayList<PortalPair>();	

	Random random = new Random();


	// Create member variables to track the controlType, score (ie number
	// of apples eaten by the snake), and whether the game has been won
	// or lost here.
	int controlType;
	int score;
	boolean won;
	/**
	 * 
	 * @param level - "RANDOM" or descriptions of the object to load
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 * 
	 */
	public Game(String level, int controlType) {
		// Initialize all member variables
		this.controlType = controlType;
		this.score = 0;
		this.won = false;
		// Create a random level when level contains: RANDOM
		if (level.equals("RANDOM")){
			createRandomLevel();
		}
		// Otherwise load the objects described in the string level
		else loadLevel(level);
	}
	/**
	 * Initializes the snake and random positions for the rocks, apples and 
	 * portals and then adds the appropriate arraylist.
	 *
	 * @return void
	 */
	public void createRandomLevel()
	{
		// create a snake: positioned as specified in the write-up
		snake = new Snake((Engine.getWidth()/2), (Engine.getHeight()/2));
		// create 20 randomly positioned rocks
		for (int i = 0; i < 20; i++){
			float x = (random.nextFloat()*Engine.getWidth());
			float y = (random.nextFloat()*Engine.getHeight());
			rocks.add(new Rock(x,y));

		}
		// create 8 randomly positioned apples
		for (int i = 0; i < 8; i++){
			float x = (random.nextFloat()*Engine.getWidth());
			float y = (random.nextFloat()*Engine.getHeight());
			apples.add(new Apple(x,y));
		}
		// create 3 randomly positioned portal pairs
		portals.add(new PortalPair("A", random.nextFloat()*Engine.getHeight(), 
				random.nextFloat()*Engine.getHeight(), random.nextFloat()*
				Engine.getHeight(), random.nextFloat()*Engine.getHeight()));
		portals.add(new PortalPair("B", random.nextFloat()*Engine.getHeight(), 
				random.nextFloat()*Engine.getHeight(), random.nextFloat()*
				Engine.getHeight(), random.nextFloat()*Engine.getHeight()));
		portals.add(new PortalPair("C", random.nextFloat()*Engine.getHeight(), 
				random.nextFloat()*Engine.getHeight(), random.nextFloat()*
				Engine.getHeight(), random.nextFloat()*Engine.getHeight()));
	}
	/**
	 * Loads the levels that are provided by splitting the text into lines, and
	 * lines into tokens and using those tokens to create the objects.
	 *
	 * @param Level - the level that was created and provided
	 * @return void
	 */
	public void loadLevel(String level)
	{
		// Initialize Rock, Apple, and PortalPair ArrayLists
		// Create a new scanner to read the level description
		Scanner scnr = new Scanner(level);
		// Loop through lines in the level description
		while (scnr.hasNextLine()){
			// Get the next line
			String line = scnr.nextLine();
			// Split the line into tokens
			String[] token = line.split(",");
			// Determine the type of object to add to the level

			// If it's a snake, create a new snake at the x and y
			// coordinates specified by the second and third tokens
			if (token[0].equals("Snake")){
				this.snake = new Snake(Float.valueOf(token[1]), Float.valueOf(
						token[2]));
			}
			// If it's an apple, create a new apple at the x and y
			// coordinates specified by the second and third tokens, and add
			// it to the list of apples
			else if (token[0].equals("Apple")){
				apples.add(new Apple(Float.valueOf(token[1]),Float.valueOf(
						token[2])));
			}
			// If it's a rock, create a new rock at the x and y coordinates
			// specified by the second and third tokens and add it to the
			// list of rocks
			else if (token[0].equals("Rock")){
				rocks.add(new Rock(Float.valueOf(token[1]),Float.valueOf(
						token[2])));
			}
			// If it's a portal pair, create a new PortalPair with the
			// name equal to the second token, with the first portal at the
			// x and y coordinates specified by the third and fourth
			// tokens, and the second portal at the x and y coordinates
			// specified by the fifth and sixth tokens
			else if (token[0].equals("PortalPair")){
				portals.add(new PortalPair(token[1], Float.valueOf(token[2]), 
						Float.valueOf(token[3]), Float.valueOf(token[4]), 
						Float.valueOf(token[5])));
			}
			// If it's anything else, ignore it.
		}
		// Close the scanner
		scnr.close();
	}
	/**
	 * Checks for any updates and updates the game
	 *
	 * @return boolean value
	 */
	public boolean update()
	{
		// Tell the snake to update itself
		snake.updateMoveDirection(controlType);
		// Tell the snake to die if it's colliding with itself
		snake.dieIfCollidingWithOwnBody();
		// For each rock, tell the rock to kill the snake if the two are
		// colliding
		for (int i = 0; i < rocks.size(); i++){
			rocks.get(i).killSnakeIfColliding(snake);
		}
		// For each apple, tell the apple to be eaten by the snake if the two
		// are colliding, and if so update the score
		for (int i = 0; i < apples.size(); i++){
			if (apples.get(i).getEatenBySnakeIfColliding(snake)){
				score++;
			}
		}
		// For each portal pair, tell the pair to teleport the snake if the two
		// are colliding
		for (int i = 0; i < portals.size(); i++){
			portals.get(i).teleportSnakeIfColliding(snake);

		}
		// Check for win/loss

		// If the score is equal to the number of apples, make sure playerHasWon()
		// will return true and then return false 
		if (playerHasWon()){
			return false;
		}
		// Else, if the snake is dead, make sure playerHasWon() will return false
		// and then return false
		else if (snake.isDead()){
			return false;
		}
		// If the game isn't over, return true
		return true;
	}
	/**
	 * Decides when the player has won.
	 *
	 * @return true when the player wins the game
	 */
	public boolean playerHasWon()
	{
		if (score == apples.size()){
			return true;
		}
		else return false;
	}
	/**
	 * When the score changes, it gets the score
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}	
	/**
	 * This is the main method where the game runs off of.
	 *
	 * @return the game
	 */
	public static void main(String[] args)
	{
		Application.startEngineAndGame(true);
	}
}

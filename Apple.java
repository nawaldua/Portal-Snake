///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Apple.java
// Semester:         Fall 2015
//
// Author:           Nawal Dua
// CS Login:         nawal
// Lecturer's Name:  Deb Deppler
// Lab Section:      311
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This  class has the constructor for the apple and the class that decides how
 * the apples interact with the snake when the snake collides with them.
 *
 * <p>Bugs: None that are known
 *
 * @author Nawal Dua
 */
public class Apple 
{
	private GraphicObject apple;
	// Create private field to hold the GraphicObject associated with this apple
	/**
	 * Constructor for the apple
	 *
	 * @param x - the x coordinate of the apple
	 * @param y - the y coordinate of the apple
	 * @return an apple with x and y coordinates
	 */
	public Apple(float x, float y)
	{
		this.apple = new GraphicObject("APPLE", x, y);
		// Initialize this apple's associated GraphicObject with type "APPLE" at
		// this apple's x and y coordinates
	}
	/**
	 * makes the apple disappear when the snake's head collides with and and 
	 * implements the grow() method
	 *
	 * @param snake - an instance of the snake from the Snake class
	 * @return returns true when the apple gets eaten by the snake
	 */		
	public boolean getEatenBySnakeIfColliding(Snake snake)
	{
		// If this apple is colliding with the snake's head's GraphicObject,
		// grow the snake once and destroy this apple's GraphicObject, then
		// return true
		if (apple.isCollidingWith(snake.getGraphicObject())){
			apple.destroy();
			snake.grow();
			return true;
		}
		// Otherwise, return false
		else return false;
	}	
}

///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Rock.java
// Semester:         Fall 2015
//
// Author:           Nawal Dua
// CS Login:         nawal
// Lecturer's Name:  Deb Deppler
// Lab Section:      311
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class constructs the rock and has the method that kills the snake when
 * the snake collides with the rock.
 *
 * <p>Bugs: None that are known
 *
 * @author Nawal Dua
 */
public class Rock 
{

	private GraphicObject rock;
	// Create private field to hold the GraphicObject associated with this rock
	/**
	 * constructor for the rocks
	 *
	 * @param x - the X coordinate for the rock
	 * @param y - the Y coordinate for the rock
	 */
	public Rock(float x, float y)
	{
		this.rock = new GraphicObject ("ROCK", x, y);
		// Initialize this rock's associated GraphicObject with type "ROCK" at 
		// this rock's x and y coordinates
	}
	/**
	 * Kills the snake if it collides with rock by calling the die() method
	 *
	 * @param snake - an instance of the Snake class
	 * @return void
	 */
	public void killSnakeIfColliding(Snake snake)
	{
		// If this rock is colliding with the snake's head's GraphicObject, kill
		// the snake
		if (rock.isCollidingWith(snake.getGraphicObject())){
			snake.die();
		}
	}
}
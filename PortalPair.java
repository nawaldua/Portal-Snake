///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             PortalPair.java
// Semester:         Fall 2015
//
// Author:           Nawal Dua
// CS Login:         nawal
// Lecturer's Name:  Deb Deppler
// Lab Section:      311
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class constructs the portal pair and includes the method that teleports
 * snake to the other portal.
 *
 * <p>Bugs: None that are known
 *
 * @author Nawal Dua
 */
public class PortalPair 
{
	private GraphicObject bluePortal;
	private GraphicObject orangePortal;
	// Create private field to hold the GraphicObject associated with the blue portal
	// Create private field to hold the GraphicObject associated with the orange portal
	/**
	 * Constructs the portal pair
	 *
	 * @param name - The name of the portal pair
	 * @param blueX - the x coordinate of the blue portal
	 * @param blueY - the y coordinate of the blue portal
	 * @param orangeX - the x coordinate of the orange portal
	 * @param orangeY - the y coordinate of the orange portal
	 */
	public PortalPair(String name, float blueX, float blueY, 
			                       float orangeX, float orangeY)
	{
		// Initialize the GraphicObjects associated with the blue and orange
		// portals, setting the type to "BLUE_PORTAL_name" or
		// "ORANGE_PORTAL_name", respectively, and setting their x and y
		// coordinates appropriately
		bluePortal = new GraphicObject ("BLUE_PORTAL_"+name, blueX, blueY);
		orangePortal = new GraphicObject ("ORANGE_PORTAL_"+name, orangeX, 
				orangeY);
	}
	/**
	 * teleports the snake from one of the portals to the other
	 *
	 * @param snake - an instance of the Snake class
	 * @return void
	 */	
	public void teleportSnakeIfColliding(Snake snake)
	{
		// If the blue portal is colliding with the snake's head's GraphicObject,
		// move the snake's head's GraphicObject past the orange portal
		if (bluePortal.isCollidingWith(snake.getGraphicObject())){
			snake.getGraphicObject().movePast(orangePortal);
		}
		// If the orange portal is colliding with the snake's head's 
		// GraphicObject, move the snake's head's GraphicObject past the 
		// blue portal
		if (orangePortal.isCollidingWith(snake.getGraphicObject())){
			snake.getGraphicObject().movePast(bluePortal);
		}
	}
}
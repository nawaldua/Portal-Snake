///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Snake.java
// Semester:         Fall 2015
//
// Author:           Nawal Dua
// CS Login:         nawal
// Lecturer's Name:  Deb Deppler
// Lab Section:      311
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
/**
 * This class constructs the snake, has a method to make the snake grow and
 * also has methods needed to kill the snake including when it collides with 
 * its own body.
 *
 * <p>Bugs: None that are known
 *
 * @author Nawal Dua
 */
public class Snake 
{
	private GraphicObject head;
	private GraphicObject body;
	private ArrayList<GraphicObject> bodyArray;
	// Private variables to hold the GraphicObject associated with the snake's head
	// and an ArrayList of GraphicObject associated with the snake's body
	/**
	 * constructor for the snake
	 *
	 * @param x - the X coordinate for the snake's head
	 * @param y - the Y coordinate for the snake's head
	 */
	public Snake(float x, float y)
	{
		// Initialize new ArrayList to hold body segments
		bodyArray = new ArrayList<GraphicObject>();
		// Initialize the head
		head = new GraphicObject("HEAD", x, y);
		// Set the speed of the head
		head.setSpeed(2);
		// Set the direction of the head
		head.setDirection(90);
		// Add the head to the list of body segments
		bodyArray.add(head);
		// Add four body segments (grow the snake four times)
		for (int i = 0; i < 4; i++){
			grow();
		}

	}
	/**
	 * gets the head so that it can be accessed in other classes
	 *
	 * @return the graphic object associated with the head of the snake
	 */
	public GraphicObject getGraphicObject() {
		return head;
	}
	/**
	 * grows the snake by adding another body segment to the end
	 *
	 * @return void
	 */
	public void grow()
	{
		// Create a new GraphicObject with type "BODY"
		body = new GraphicObject("BODY", 0, 0);
		// Find the last body segment in the list of body segments

		// Set the leader of the new body segment to be the last body segment

		body.setLeader(bodyArray.get(bodyArray.size()-1));
		// Add the new body segment to the end of the list of body segments
		bodyArray.add(body);
	}
	/**
	 * changes way the snake moves depending on the control type selected
	 *
	 * @param controlType - the control type that is selected
	 * @return void
	 */
	public void updateMoveDirection(int controlType)
	{
		// if controlType is one
		if (controlType == 1){
			// implementation for controlType one
			if (Engine.isKeyPressed("RIGHT")){
				head.setDirection(this.head.getDirection()-90);
			}
			else if (Engine.isKeyPressed("LEFT")){
				head.setDirection(this.head.getDirection()+90);
			}
		}

		//if controlType is two
		if (controlType == 2){
			// implementation for controlType two
			if (Engine.isKeyHeld("RIGHT")){
				head.setDirection(this.head.getDirection()-6);
			}
			else if (Engine.isKeyHeld("LEFT")){
				head.setDirection(this.head.getDirection()+6);
			}
		}	
		// if controlType is three
		if (controlType == 3){
			// implementation for controlType three
			if(Engine.isKeyHeld("SPACE")){
				head.setDirection(this.head.getDirection()+6);
			}
			else{
				head.setDirection(this.head.getDirection()-6);
			}

		}
	}
	/**
	 * kills the snake when the head collides with a body segment by calling
	 * the die() method
	 *
	 * @return void
	 */
	public void dieIfCollidingWithOwnBody()
	{
		// For each game object in the body...
		for (int i = 1; i < bodyArray.size(); i++){
			// if the head is colliding with this segment...
			if (bodyArray.get(i).isCollidingWith(head)){
				// tell the snake to die.
				die();
			}
		}
	}
	/**
	 * kills the snake by setting both the head and the body segments to "DEAD"
	 *
	 * @return void
	 */
	public void die()
	{
		// Set the head's type to "DEAD"
		head.setType("DEAD");
		// For each GraphicObject in the snake's body, set its type to "DEAD"
		for (int i = 1; i < bodyArray.size(); i++){
			bodyArray.get(i).setType("DEAD");
		}
	}
	/**
	 * if the snake is "DEAD" it kills the snake
	 *
	 * @return true if the snake is dead
	 */
	public boolean isDead() {
		if(head.getType().equals("DEAD")){
			return true;
		}
		else return false;
	}
}

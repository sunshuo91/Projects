/**
 * 
 * @author Shuo Sun ss8ee Homework 4 101
 * @author Cameron Peters cwp9fq Homework 4 101
 * @author Hexuan Liu hl6uk Homework 4 101
 * @author Josh Wang jw6dz Homework 4 101
 * 
 */
package edu.virginia.cs2110.ghosthunter;

/**
 * 
 * Interface for Possible Items that user might use in the game. They all have some common functions
 */
public interface Items {
	
	/**
	 * check if this item is a weapon
	 */
	public boolean weapon = true;
	
	/**
	 * use this item
	 */
	public void use();
	
}

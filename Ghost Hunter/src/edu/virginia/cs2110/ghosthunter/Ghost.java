/**
 * 
 * @author Shuo Sun ss8ee Homework 4 101
 * @author Cameron Peters cwp9fq Homework 4 101
 * @author Hexuan Liu hl6uk Homework 4 101
 * @author Josh Wang jw6dz Homework 4 101
 * 
 */
package edu.virginia.cs2110.ghosthunter;

import java.util.ArrayList;
import java.util.Random;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.maps.model.LatLng;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 
 *public class including all the characteristics of one Ghost
 *
 */
public class Ghost  {

	private int speed;
	private double xCoordinate;
	private double yCoordinate;

	private int level;
	private int loot;
	private boolean isAlive;
	private boolean isRepelled;
	private boolean isFrozen;
	
	private GPSTracker location;

	/**
	 * 
	 * @param ghostsKilled: number of ghost being killed
	 * @param userLat: the current latitude of the user
	 * @param userLng: the current longitude of the user
	 * The constructor of the class Ghost. Initiation of Ghost's level, condition and location
	 */
	public Ghost(int ghostsKilled, double userLat, double userLng) {
		Random randomGenerator = new Random();		
		double buffer_value = .01;
		double randomX = Math.random();
		double randomY = Math.random();
		
		this.xCoordinate = (double) (userLng + (randomX-.5) * buffer_value);
		this.yCoordinate = (double) (userLat + (randomY-.5) * buffer_value);

		// set the level based on the number of inventory that the human has
		// set the speed based on the level
		double level = Math.random();

		if (level < .5) {
			this.level = 1;
			this.speed = 5/111000;
			this.loot = randomGenerator.nextInt(20);
		} else if (level < .8) {
			this.level = 2;
			this.speed = 10/111000;
			this.loot = 30 + randomGenerator.nextInt(40);
		} else {
			this.level = 3;
			this.speed = 20/111000;
			this.loot = 100 + randomGenerator.nextInt(50);
		}

		// that the speed based on the level of the game

		this.isAlive = true;
		this.isFrozen = false;
		this.isRepelled = false;
	}

	public int getSpeed() {
		return speed;
	}

	public double getxCoordinate() {
		return xCoordinate;
	}
	public double getyCoordinate() {
		return yCoordinate;
	}

	public int getLevel() {
		return level;
	}

	public int getLoot() {
		return loot;
	}

	/**
	 * 
	 * @param location
	 * @return if the player is dangerous
	 * Have some kind of proximity alert when a ghosts and a user get close
	 * together
	 */
	public boolean isDangerous(LatLng location) {
		double range = Math.sqrt((location.longitude - xCoordinate)
				* (location.longitude - xCoordinate)
				+ (location.latitude - yCoordinate)
				* (location.latitude - yCoordinate));
		if (range < 10)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param elapsedTime
	 * @param userLocation
	 * Ghosts will try to escape from the user. It would going towards
	 * the opposite position of user's current location
	 */
	public void move(double elapsedTime, LatLng userLocation) {
		if (!isFrozen) {
			double deltaX = (xCoordinate - userLocation.longitude);
			double deltaY = (yCoordinate - userLocation.latitude);

			if (isRepelled) {
				deltaX = -deltaX;
				deltaY = -deltaY;
			}

			Double theta = Math.atan(deltaY / deltaX);
			if (deltaX > 0) {
				xCoordinate = (xCoordinate - Math.cos(theta) * speed
						* elapsedTime);
			}
			if (deltaY > 0) {
				yCoordinate = (yCoordinate - Math.sin(theta) * speed
						* elapsedTime);
			}
			if (deltaX < 0) {
				xCoordinate = (xCoordinate + Math.cos(theta) * speed
						* elapsedTime);
			}
			if (deltaX < 0) {
				xCoordinate = (xCoordinate + Math.cos(theta) * speed
						* elapsedTime);
			}
		}
	}

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	
}

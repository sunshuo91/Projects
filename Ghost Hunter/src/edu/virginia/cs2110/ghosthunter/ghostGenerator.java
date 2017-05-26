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
import java.util.Set;
import java.util.TreeSet;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ghostGenerator extends AsyncTask<Void, ArrayList<Ghost>, Integer> { //void void void

	private MainActivity myActivity;
	private ArrayList<Ghost> ghostList = new ArrayList<Ghost>();
	private int ghostsKilled = 0;
	private int spectralDust = 50;
	private ArrayList<Items> spectralSwords = new ArrayList<Items>();
	private Location currentLocation;
	
	/**
	 * 
	 * @param myActivity
	 * The construction of ghostGenerator
	 */
	public ghostGenerator(MainActivity myActivity){
		super();
		this.myActivity = myActivity;
		this.spectralSwords.add(new SpectralSword());
	}
	
	/**
	 * 
	 * The onPreExecute of the task
	 */
	@Override
	protected void onPreExecute(){
		
	}
	
	/**
	 * 
	 * The doInBackground method used to execute the background activity
	 */
	@Override
	protected Integer doInBackground(Void... nothing) {
	
		while (!isCancelled()) {
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			publishProgress(ghostList);
		}
		return null;
	}
	
	/**
	 * 
	 * The onProgessUpdate established the way that the ghost was killed when it was chased by the user
	 */
	@Override
	protected void onProgressUpdate(ArrayList<Ghost>... gList){
		ghostList = gList[0];
		int j = 0;
		int k = 0;
		double userLat = myActivity.gps.getLocation().getLatitude();
		double userLng = myActivity.gps.getLocation().getLongitude();
		while (ghostList.size() < 12) {
			ghostList.add(new Ghost(ghostsKilled, userLat, userLng));
		}
		
		while (k < ghostList.size()) {
			double ghostLat = ghostList.get(k).getyCoordinate();
			double ghostLng = ghostList.get(k).getxCoordinate();
			double distance = Math.sqrt( (userLat - ghostLat) * (userLat - ghostLat) +
					(userLng - ghostLng) * (userLng - ghostLng));
			if (distance < .0001) { //create an alert, ghost dies if human has spectralSword
				//View alert = new TextView(MainActivity.gps); //not working yet
				ghostList.remove(k);
				ghostsKilled++;
			}
			if (distance < (5/111000)) { //ghost is killable. right now the ghost will die if it gets close
				// needs a button that becomes available that uses a spectralsword to kill the nearby ghost

				if (spectralSwords.size() > 0) {
					ghostList.remove(k);
					spectralSwords.remove(0);
					spectralDust += 20; //loot from spectral dust, can change later
					ghostsKilled ++;
				}
			}
			if (distance < .01) { //gameover, needs a gameover screen
				
			}
			k++;
		}
		
		myActivity.googleMap.clear();
		while (j < ghostList.size()) {
			double ghostLat = ghostList.get(j).getyCoordinate();
			double ghostLng = ghostList.get(j).getxCoordinate();
			if (userLat >= ghostLat && userLng >= ghostLng) {
				double latMove = ghostLat - (.000001);
				double lngMove = ghostLng - (.000001);
				ghostList.get(j).setyCoordinate(latMove);
				ghostList.get(j).setxCoordinate(lngMove);
				Marker newGhost = myActivity.googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(latMove, lngMove))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.ghost1px55)));
			}
			else if (userLat >= ghostLat && userLng < ghostLng) {
				double latMove = ghostLat - (.000001);
				double lngMove = ghostLng + (.000001);
				ghostList.get(j).setyCoordinate(latMove);
				ghostList.get(j).setxCoordinate(lngMove);
				Marker newGhost = myActivity.googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(latMove, lngMove))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.ghost2px55)));
			}
			else if (userLat < ghostLat && userLng >= ghostLng) {
				double latMove = ghostLat + (.000001);
				double lngMove = ghostLng - (.000001);
				ghostList.get(j).setyCoordinate(latMove);
				ghostList.get(j).setxCoordinate(lngMove);
				Marker newGhost = myActivity.googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(latMove, lngMove))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.ghosts3px55)));
			}
			else if (userLat < ghostLat && userLng < ghostLng) {
				double latMove = ghostLat + (.000001);
				double lngMove = ghostLng + (.000001);
				ghostList.get(j).setyCoordinate(latMove);
				ghostList.get(j).setxCoordinate(lngMove);
				Marker newGhost = myActivity.googleMap.addMarker(new MarkerOptions()
				.position(new LatLng(latMove, lngMove))
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.ghost1px55)));
			}
			j++;
		}
		
		
	}

	/**
	 * 
	 * the Override onPostExecute
	 */
	@Override
	protected void onPostExecute(Integer result){
		
	}
	
	/**
	 * 
	 * the Override onCancelled
	 */
	@Override
	protected void onCancelled(){
		
	}

}

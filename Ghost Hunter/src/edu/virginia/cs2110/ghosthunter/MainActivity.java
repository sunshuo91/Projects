/**
 * 
 * @author Shuo Sun ss8ee Homework 4 101
 * @author Cameron Peters cwp9fq Homework 4 101
 * @author Hexuan Liu hl6uk Homework 4 101
 * @author Josh Wang jw6dz Homework 4 101
 * 
 */
package edu.virginia.cs2110.ghosthunter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * Public class helps to set up the main activity menu
 */
public class MainActivity extends Activity {

	public GoogleMap googleMap;
	public Location location;
	public static GPSTracker gps;

	/**
	 * 
	 * Override Method that helps to create buttons, which used to start, pause
	 * or end the game
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// find the buttons
		Button sbutton = (Button) findViewById(R.id.button1);
		Button pbutton = (Button) findViewById(R.id.button2);
		Button ebutton = (Button) findViewById(R.id.button3);

		// register start event with the first button
		sbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setContentView(R.layout.activity_ghost_field);
				try {
					initilizeMap();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// register pause event with the second button
		pbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("tempt", "you hit the pause button!");
				// pause the game
			}
		});

		ebutton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				finish();

			}
		});

	}

	/**
	 * 
	 * Inflate the menu; this adds items to the action bar if it is present.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 
	 * This method helps to initialize the map that will be used in the ghost
	 * field
	 */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			gps = new GPSTracker(this);
			location = gps.getLocation();
			LatLng userLocation = new LatLng(location.getLatitude(),
					location.getLongitude());

			googleMap.setMyLocationEnabled(true);

			ghostGenerator myGhostGen = new ghostGenerator(this);
			myGhostGen.execute();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/**
	 * 
	 * Override onPause method
	 */
	@Override
	protected void onPause() {
		googleMap = null;
		location = null;
		gps = null;
	}

}
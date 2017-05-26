/**
 * 
 * @author Shuo Sun ss8ee Homework 4 101
 * @author Cameron Peters cwp9fq Homework 4 101
 * @author Hexuan Liu hl6uk Homework 4 101
 * @author Josh Wang jw6dz Homework 4 101
 * 
 */
package edu.virginia.cs2110.ghosthunter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

/**
 * 
 * Public class helps to create the splash screen
 */
public class SplashScreen extends Activity {

	/**
	 * 
	 * Create the Content View on the splash screen
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		// thread for displaying the flash screen
		Thread splash_screen = new Thread() {
			public void run() {
				try {
					sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					startActivity(new Intent(getApplicationContext(),
							MainActivity.class));
					finish();
				}
			}
		};
		splash_screen.start();
	}

	/**
	 * 
	 * Inflate the menu; this adds items to the action bar if it is present.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}

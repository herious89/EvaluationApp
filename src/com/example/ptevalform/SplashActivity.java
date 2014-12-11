package com.example.ptevalform;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * This class creates the splash green (welcome screen) of the app
 * @author brian & lam & quang
 *
 */

public class SplashActivity extends Activity {
	
	// Store the welcome text
	TextView versionNumber;

	/**
	 * The onCreate method displays the main splash screen
	 * and the welcome text by creating a thread which
	 * will sleep after 5 seconds
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_splash_screen);
		
		// ignore top bar for splash 
		//getActionBar().hide();
		
		// shared prefs to record first time app is fired up
	    SharedPreferences settings = getSharedPreferences("prefs", 0);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putBoolean("firstRun", true);
	    editor.commit();

//	    Intent intent = new Intent(this, MainSplashScreen.class);
//	    startActivity(intent);
		
	    // timer
	    splashTimer();

	}
	
	@Override
    protected void onDestroy() {		
        super.onDestroy();      
    }
	
	@Override
	public void onResume() {
	    super.onResume();
	    SharedPreferences settings = getSharedPreferences("prefs", 0);
	    boolean firstRun = settings.getBoolean("firstRun", true);
	    if (!firstRun) {
	        Intent intent = new Intent(this, LoginActivity.class);
	            startActivity(intent);
	        Log.d("TAG1", "firstRun(false): " + Boolean.valueOf(firstRun).toString());
	    } else {
	        Log.d("TAG1", "firstRun(true): " + Boolean.valueOf(firstRun).toString());
	    }
	}
	
	public void splashTimer() {
        /****** Create Thread that will sleep after 5 seconds *************/  		
		Thread background = new Thread() {
			public void run() {				
				try {
					// Time showing splash
					sleep(1*2000);					
					// After x seconds redirect to another intent
				    Intent i=new Intent(getBaseContext(),LoginActivity.class);
					startActivity(i);					
					//Remove activity
					finish();					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};		
		// start thread
		background.start();
	}
}

package com.ttw.vrj.timetowork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class LocationActivity extends ActionBarActivity {
	private final String LOG_TAG = LocationActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		FragmentManager fm = getSupportFragmentManager();
		
		// Ask FragmentManager for Fragment w/ this container view id
		// If already in the list because of previous screen rotation; return it.
		Fragment fragment = fm.findFragmentById(R.id.container);
		
		if (fragment == null) {
			fragment = new LocationFragment();
			
			// FragmentManager class uses fluent interface so actions can be chained
			// Create new FragmentTransaction, include one add operation in it, then
			// commit
			fm.beginTransaction().add(R.id.container, fragment).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

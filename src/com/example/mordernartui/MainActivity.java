package com.example.mordernartui;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener {
	private SeekBar colorSlide = null;
	private MyView myview = null;
	static final String MOMA_URI = "http://www.moma.org/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setup your views.
		setContentView(R.layout.activity_main);
		
		forceShowActionBarOverflowMenu();
		
		// get my view
		myview = (MyView) findViewById(R.id.myView);
		// get the seek bar
		colorSlide = (SeekBar) findViewById(R.id.seekBar1);

		// assign the listener
		colorSlide.setOnSeekBarChangeListener(this);
		}

	private void forceShowActionBarOverflowMenu() {  
	    try {  
	        ViewConfiguration config = ViewConfiguration.get(this);  
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");  
	        if (menuKeyField != null) {  
	            menuKeyField.setAccessible(true);  
	            menuKeyField.setBoolean(config, false);  
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.more_information) {
			showDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void showDialog() {
		String title = "More Information";
		String message = "Inspired by the works of artists such as Piet Mondrian and Ben Nicholson. \n Click below to learn more!";
		String positive = "Visit MOMA";
		String negative = "Not Now";
		
	    new AlertDialog.Builder(this)
	    .setTitle(title)
	    .setMessage(message)
	    .setPositiveButton(positive, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // Your code
	        	Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(MOMA_URI));
	        	startActivity(i);
	        }
	     })
	    .setNegativeButton(negative, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	     .show();
	    
	}
	
	// required by the interface
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO: doSomethingWith(progress);
		myview.changeColors(progress);
	}

	// required by the interface
	public void onStartTrackingTouch(SeekBar seekBar) {
		// unused
	}

	// required by the interface
	public void onStopTrackingTouch(SeekBar seekBar) {
		// unused
	}
}

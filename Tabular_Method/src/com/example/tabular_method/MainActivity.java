package com.example.tabular_method;


import java.io.IOException;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	int counter;
	Button add, sub;
	TextView display;
	EditText in;
	MediaPlayer song;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		song = MediaPlayer.create(MainActivity.this, R.raw.music);
        song.start();
		counter = 0;
		add = (Button) findViewById(R.id.trueButton);
		display = (TextView) findViewById(R.id.tvDisplay);
		in = (EditText) findViewById(R.id.edit);
		add.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
		public void onClick(View v) {
				
				tabular t = new tabular();
				t.gui = 1;
				String s = in.getText().toString();
				t.take =  s;
				if(s.length() == 0) t.read = 1;
				try {
					t.input();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				display.setText(t.fine);
			}
		});
		
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
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		song.release();
	}
}

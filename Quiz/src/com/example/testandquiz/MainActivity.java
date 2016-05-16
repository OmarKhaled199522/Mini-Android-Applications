package com.example.testandquiz;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	int count = 1 , num_of_correct = 0 , num_of_wrong = 0;
	Button trueButton,falseButton , next , prev;
	TextView display1 , display2;
	MediaPlayer song;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        song = MediaPlayer.create(MainActivity.this, R.raw.music);
        song.start();
        trueButton = (Button)findViewById(R.id.trueButton);
        falseButton = (Button)findViewById(R.id.falseButton);
        next = (Button)findViewById(R.id.nextButton);
        prev = (Button)findViewById(R.id.prevButton);
        display1 = (TextView) findViewById(R.id.tvDisplay);
        display2 = (TextView) findViewById(R.id.tvDisplay2);
        
    	final parameters [] objects = new parameters[7];
    	objects[1]=new parameters();
    	objects[2]=new parameters();
    	objects[3]=new parameters();
    	objects[4]=new parameters();
    	objects[5]=new parameters();
    	objects[6]=new parameters();
        
        objects[1].setQuestion("London is the Capital of England?");
        objects[2].setQuestion("Alexandria is the Capital of Egypt?");
        objects[3].setQuestion("Amman is the Capital of Jordan?");
        objects[4].setQuestion("Berlin is the Capital of France?");
        objects[5].setQuestion("Jakarta is the Capital of Indonesia?");
        objects[6].setQuestion("NewYork is the Capital of UK?");
        
        trueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				if (count % 2 == 1){
					
					Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_LONG).show();
					num_of_correct++;
				}
				
				else{ 
					Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_LONG).show();
					num_of_wrong++;
					
				}
				display2.setText("                         "+count +  "/ 6" + "\n" + "Number of correct answers = " + num_of_correct
						+ "\n" + "Number of wrong answers = " + num_of_wrong);
				
			}
		});
        falseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if (count % 2 == 0){
					Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_LONG).show();
					num_of_correct++;
				
				}
				else{ 
					Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_LONG).show();
					num_of_wrong++;
				}
				display2.setText("                         "+count +  "/ 6" + "\n" + "Number of correct answers = " + num_of_correct
						+ "\n" + "Number of wrong answers = " + num_of_wrong);
				
			}
		});
        
        next.setOnClickListener(new View.OnClickListener() {
			
  			@Override
  			public void onClick(View arg0) {
  				
  				if (count == 6) count = 1;
  				else 			count++;
  				display1.setText(objects[count].getQuestion());
  				display2.setText("                         "+count +  "/ 6" + "\n" + "Number of correct answers = " + num_of_correct
						+ "\n" + "Number of wrong answers = " + num_of_wrong);
  				
  			}
  		});
        
        prev.setOnClickListener(new View.OnClickListener() {
			
  			@Override
  			public void onClick(View arg0) {
  				
  				if (count == 1)		count = 6;
  				else 				count--;
  				display1.setText(objects[count].getQuestion());
  				display2.setText("                         "+count +  "/ 6" + "\n" + "Number of correct answers = " + num_of_correct
						+ "\n" + "Number of wrong answers = " + num_of_wrong);
  				
  				
  			}
  		});
        
        
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		song.release();
	}
}




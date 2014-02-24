package com.example.bargames;

import java.util.Random;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

public class Killer extends Activity {
	
	RelativeLayout rv1;
	RelativeLayout rv2;
	ImageView kd, start;
	NumberPicker np;
	boolean showingID;
	Random rng = new Random();
	int numplayers, killer, detective;
	int index;
	RelativeLayout startrv1, startrv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_killer);
		rv1 = (RelativeLayout) findViewById(R.id.startScreen);
		rv2 = (RelativeLayout) findViewById(R.id.gameScreen);
		kd = (ImageView) findViewById(R.id.playerid);
		start = (ImageView) findViewById(R.id.startGame);
		rv2.setVisibility(View.INVISIBLE);
		start.setVisibility(View.INVISIBLE);
		showingID = false;
		startrv1 = (RelativeLayout) findViewById(R.id.killerScreen);
        startrv2 = (RelativeLayout) findViewById(R.id.description);
		
		String[] nums = new String[30];
		 
		for(int i=0; i< nums.length; i++)
		   nums[i] = Integer.toString(i + 4);
		
		np = (NumberPicker) findViewById(R.id.np);
		np.setMaxValue(nums.length-1);
		np.setMinValue(0);
		np.setWrapSelectorWheel(false);
		np.setDisplayedValues(nums);
		np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.killer, menu);
		return true;
	}
	
	public void killerStart(View arg0){
    	startrv2.setVisibility(View.INVISIBLE);
    	startrv1.setVisibility(View.VISIBLE);
    }
	
	public void startGame(View arg0){
		rv1.setVisibility(View.INVISIBLE);
		rv2.setVisibility(View.VISIBLE);
		numplayers = np.getValue() + 4;
		killer = rng.nextInt(numplayers);
		detective = rng.nextInt(numplayers);
		
		while(killer == detective){
			detective = rng.nextInt(numplayers);
		}
		
		index = 0;

	}
	
	public void showID(View arg0){
		if(!showingID){
			showingID = true;
			new CheckKiller().execute();
		}
			
	}
	
	private class CheckKiller extends AsyncTask<Void, Integer, Void>
	{
		//Before running code in separate thread  
        @Override  
        protected void onPreExecute()  
        { 
        	
        	if(index == killer){
        		kd.setImageResource(R.drawable.killer);
        	}
        	else{
        		if(index == detective){
        			kd.setImageResource(R.drawable.detective);
        		}
        		else{
        			kd.setImageResource(R.drawable.victim);
        		}
        	}
        	
        	
        	
        	index++;
        	kd.setVisibility(View.VISIBLE);
        }
    	
		//The code to be executed in a background thread.  
        @Override  
        protected Void doInBackground(Void... params)  
        {  
            try  
            {  
                //Get the current thread's token  
                synchronized (this)  
                {  
                    	this.wait(1500);  
                }  
            }  
            catch (InterruptedException e)  
            {  
                e.printStackTrace();  
            }  
            return null;  
        }
        
      //after executing the code in the thread  
        @Override  
        protected void onPostExecute(Void result)  
        {
        	showingID = false;
        	if(index == numplayers){
        		kd.setVisibility(View.INVISIBLE);
        		rv2.setVisibility(View.GONE);
        		start.setVisibility(View.VISIBLE);        		
        	}
        	
        	kd.setImageResource(R.drawable.nextplayer);
        }
        
	}
	

}

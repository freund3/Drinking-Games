package com.example.bargames;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SimonSays extends Activity {

	int[] inputs = new int[99]; // 1 == green, 2 == yellow, 3 == red, 4 == blue
	int index, counter, userInput;
	Random rng = new Random();
	boolean showPattern;
	ImageView red;
	ImageView blue;
	ImageView green;
	ImageView yellow;
	ImageView simonTurn;
	ImageView yourTurn;
	ImageView simonLoser;
	ImageView endCounter1, endCounter2;
	Button startGame;
	boolean gameInSession = false;
	Handler handler = new Handler(); 
	boolean showingPattern = false;
	int beep_id1, beep_id2, error_id;
	int timer = 250;
	SoundPool beep1 = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	SoundPool beep2 = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	SoundPool beep3 = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	SoundPool beep4 = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	SoundPool error = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	RelativeLayout rv1, rv2;
	ImageView imgCounter1;
    ImageView imgCounter2;
    LinearLayout imgCounterLayout, endCounterLayout;
    int[] d = new int[10];
	
	OnClickListener redListener = new OnClickListener() {
		  public void onClick(View v) { redClicked(); }
		};

	OnClickListener greenListener = new OnClickListener() {
		  public void onClick(View v) { greenClicked(); }
		};
		
	OnClickListener blueListener = new OnClickListener() {
		  public void onClick(View v) { blueClicked(); }
		};

	OnClickListener yellowListener = new OnClickListener() {
		  public void onClick(View v) { yellowClicked(); }
		};

		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simon_says);
		red = (ImageView) findViewById(R.id.red);
		blue = (ImageView) findViewById(R.id.blue);
		green = (ImageView) findViewById(R.id.green);
		yellow = (ImageView) findViewById(R.id.yellow);
		startGame = (Button) findViewById(R.id.startGame);
		simonTurn = (ImageView) findViewById(R.id.simonTurn);
		yourTurn = (ImageView) findViewById(R.id.playerTurn);
		simonLoser = (ImageView) findViewById(R.id.loser);
		beep_id1=beep1.load(this,R.raw.beep1,1);
		beep_id2=beep2.load(this,R.raw.beep2,1);
		error_id=error.load(this,R.raw.error,1);
		rv1 = (RelativeLayout) findViewById(R.id.gameScreen);
        rv2 = (RelativeLayout) findViewById(R.id.description);
        imgCounter1 = (ImageView) findViewById(R.id.counter1);
        imgCounter2 = (ImageView) findViewById(R.id.counter2);
        endCounter1 = (ImageView) findViewById(R.id.endCounter1);
        endCounter2 = (ImageView) findViewById(R.id.endCounter2);
        imgCounterLayout = (LinearLayout) findViewById(R.id.counterLayout);
        endCounterLayout = (LinearLayout) findViewById(R.id.endCounterLayout);
		red.setOnClickListener(redListener);
		green.setOnClickListener(greenListener);
		blue.setOnClickListener(blueListener);
		yellow.setOnClickListener(yellowListener);
		simonLoser.setVisibility(View.INVISIBLE);
		simonTurn.setVisibility(View.INVISIBLE);
		yourTurn.setVisibility(View.INVISIBLE);
		imgCounterLayout.setVisibility(View.INVISIBLE);
		setupDrawables();
		initInputs();
	}
	
	public void startGame(View arg0){
		if(!gameInSession){
			endCounterLayout.setVisibility(View.INVISIBLE);
			rv2.setVisibility(View.INVISIBLE);
			rv1.setVisibility(View.VISIBLE);
			simonLoser.setVisibility(View.INVISIBLE);
			simonTurn.setVisibility(View.INVISIBLE);
			yourTurn.setVisibility(View.INVISIBLE);
			gameInSession = true;
			startGame.setVisibility(View.INVISIBLE);
			imgCounterLayout.setVisibility(View.VISIBLE);
			showingPattern = true;
			new SimonsTurn().execute();
		}
	}
	
	public void gameOver(){
		gameInSession = false;
		imgCounterLayout.setVisibility(View.INVISIBLE);
		error.play(error_id,1.0f,1.0f,0,0,1.0f);
		counter = 0;
		index = 0;
		timer = 250;
		userInput = 0;
		initInputs();
		endCounterLayout.setVisibility(View.VISIBLE);
		simonLoser.setVisibility(View.VISIBLE);
		startGame.setVisibility(View.VISIBLE);
		
	}
	
	public void redClicked(){
		if(gameInSession && !showingPattern){
		if(inputs[userInput] == 3){
			userInput++;
			red.setImageResource(R.drawable.redpress);
			beep1.play(beep_id1,1.0f,1.0f,0,0,1.0f);
			showingPattern = true;
			new PlayerPattern().execute();
		}
		else{
			gameOver();
		}
		}
	}
	
	public void greenClicked(){
		if(gameInSession && !showingPattern){
		if(inputs[userInput] == 1){
			userInput++;
			green.setImageResource(R.drawable.greenpress);
			beep2.play(beep_id2,1.0f,1.0f,0,0,1.0f);
			showingPattern = true;
			new PlayerPattern().execute();
		}
		else{
			gameOver();
		}
		}
	}
	
	public void blueClicked(){
		if(gameInSession && !showingPattern){
		if(inputs[userInput] == 4){
			userInput++;
			blue.setImageResource(R.drawable.bluepress);
			beep1.play(beep_id1,1.0f,1.0f,0,0,1.0f);
			new PlayerPattern().execute();
			
		}
		else{
			gameOver();
		}
		}
	}
	
	public void yellowClicked(){
		if(gameInSession && !showingPattern){
		if(inputs[userInput] == 2){
			userInput++;
			yellow.setImageResource(R.drawable.yellowpress);
			beep2.play(beep_id2,1.0f,1.0f,0,0,1.0f);
			showingPattern = true;
			new PlayerPattern().execute();
		}
		else{
			gameOver();
		}
		}
	}
	
	public void showPattern(){
		showingPattern = true;
		counter = 0;
		index++;
		userInput = 0;
		new SimonsPattern().execute();
	}
	
	public void initInputs(){
		for(int i = 0; i < inputs.length; i++){
			inputs[i] = rng.nextInt(4) + 1;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simon_says, menu);
		return true;
	}
	
	// Thread that creates the pattern for the user
	private class SimonsPattern extends AsyncTask<Void, Integer, Void>  
    {  
        //Before running code in separate thread  
        @Override  
        protected void onPreExecute()  
        {  
        	if(counter < index){
        		if(inputs[counter] == 1){ 
        			beep2.play(beep_id2,1.0f,1.0f,0,0,1.0f);
        			green.setImageResource(R.drawable.greenpress);
        		}
        		if(inputs[counter] == 2){
        			beep2.play(beep_id2,1.0f,1.0f,0,0,1.0f);
        			yellow.setImageResource(R.drawable.yellowpress);
        		}
        		if(inputs[counter] == 3){
        			beep1.play(beep_id1,1.0f,1.0f,0,0,1.0f);
        			red.setImageResource(R.drawable.redpress);
        		}
        		if(inputs[counter] == 4){
        			beep1.play(beep_id1,1.0f,1.0f,0,0,1.0f);
        			blue.setImageResource(R.drawable.bluepress);
        		}
        	}
    		
        }  
  
        //The code to be executed in a background thread.  
        @Override  
        protected Void doInBackground(Void... params)  
        {  
            /* This is just a code that delays the thread execution 4 times, 
             * during 850 milliseconds and updates the current progress. This 
             * is where the code that is going to be executed on a background 
             * thread must be placed. 
             */  
            try  
            {  
                //Get the current thread's token  
                synchronized (this)  
                {  
                    		this.wait(timer);  
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
        	if(inputs[counter] == 1) green.setImageResource(R.drawable.green);
    		if(inputs[counter] == 2) yellow.setImageResource(R.drawable.yellow);
    		if(inputs[counter] == 3) red.setImageResource(R.drawable.red);
    		if(inputs[counter] == 4) blue.setImageResource(R.drawable.blue);
    		if(timer > 100)
    			timer = timer - 30;
    		
    		new Pause().execute();

        }  
    }  
	
	
		private class SimonsTurn extends AsyncTask<Void, Integer, Void>  
	    {  
	        //Before running code in separate thread  
	        @Override  
	        protected void onPreExecute()  
	        {  
	        	
	        		int second = index/10;
	        		int first = index%10;
	        		imgCounter2.setImageResource(d[second]);
	        		imgCounter1.setImageResource(d[first]);	
	        		endCounter2.setImageResource(d[second]);
	        		endCounter1.setImageResource(d[first]);	
	        		

	        		showingPattern = true;
	        		simonTurn.setVisibility(View.VISIBLE);
	        	
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
	                    		this.wait(500);  
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
	        	if(index == 99){
	        		gameOver();
	        	}
	        	else{
	        		simonTurn.setVisibility(View.INVISIBLE);
	        		showPattern();	
	        	}
	        }  
	    }
		
		// Thread that creates the pattern for the user
			private class PlayersTurn extends AsyncTask<Void, Integer, Void>  
		    {  
		        //Before running code in separate thread  
		        @Override  
		        protected void onPreExecute()  
		        {  
			    	showingPattern = true;
					yourTurn.setVisibility(View.VISIBLE);
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
		                    		this.wait(500);  
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
		    		yourTurn.setVisibility(View.INVISIBLE);	
		    		showingPattern = false;
		        }  
		    }  
			
	// Thread that creates the pattern for the user
				private class PlayerPattern extends AsyncTask<Void, Integer, Void>  
			    {  			  
			        //The code to be executed in a background thread.  
			        @Override  
			        protected Void doInBackground(Void... params)  
			        {  
			            try  
			            {  
			                //Get the current thread's token  
			                synchronized (this)  
			                {  
			                    		this.wait(150);  
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
			        	green.setImageResource(R.drawable.green);
			    		yellow.setImageResource(R.drawable.yellow);
			    		red.setImageResource(R.drawable.red);
			    		blue.setImageResource(R.drawable.blue);
			    		showingPattern = false;
				        if(userInput == index){
				        	new SimonsTurn().execute();
						}
			        }  
			    }  
				
		private class Pause extends AsyncTask<Void, Integer, Void>
		{
			//Before running code in separate thread  
	        @Override  
	        protected void onPreExecute()  
	        { 
	        	if(counter == index){
	        		new PlayersTurn().execute();
	        	}
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
	                    	this.wait(timer);  
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
	        	if(counter < index){
	        		counter++;
	        		new SimonsPattern().execute();
	        	}
	        }
		}
		
		void setupDrawables(){
	    	d[0] = R.drawable.nmb0;
	    	d[1] = R.drawable.n1;
	    	d[2] = R.drawable.n2;
	    	d[3] = R.drawable.n3;
	    	d[4] = R.drawable.n4;
	    	d[5] = R.drawable.n5;
	    	d[6] = R.drawable.n6;
	    	d[7] = R.drawable.n7;
	    	d[8] = R.drawable.n8;
	    	d[9] = R.drawable.n9;
	    }
		
		   //Clean up
	    @Override
	    protected void onPause() {
	        super.onPause();
	        beep1.pause(beep_id1);
	        beep2.pause(beep_id2);
	        error.pause(error_id);
	    }
	    protected void onDestroy() {
	        super.onDestroy();
	        beep1.pause(beep_id1);
	        beep2.pause(beep_id2);
	        error.pause(error_id);
	    }
}

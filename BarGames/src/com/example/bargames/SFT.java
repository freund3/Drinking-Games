package com.example.bargames;

import android.R.drawable;
import android.os.Bundle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SFT extends Activity {
	int counter;		//Keep track of number of ones
	int tenCounter;
    ImageView dice_picture1;        //reference to dice picture
    ImageView dice_picture2;
    ImageView dice_picture3;
    ImageView newCount1;
    ImageView newCount2;
    ImageView event;
    Random rng = new Random();    //generate random numbers
    SoundPool dice_sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
    int sound_id;        //Used to control sound stream return by SoundPool
    Handler handler;    //Post message to start roll
    Timer timer=new Timer();    //Used to implement feedback to user
    boolean rolling=false;        //Is die rolling?
    boolean pick=true;
    boolean buy=true;
    boolean drink=true;
    int[] d = new int[10]; 
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sft);
        //load dice sound
        sound_id=dice_sound.load(this,R.raw.shake_dice,1);
        //get reference to image widget
        dice_picture1 = (ImageView) findViewById(R.id.imageView1);
        dice_picture2 = (ImageView) findViewById(R.id.imageView2);
        dice_picture3 = (ImageView) findViewById(R.id.imageView3);
        //get reference to image counter widget
        newCount1 = (ImageView) findViewById(R.id.counter1);
        newCount2 = (ImageView) findViewById(R.id.counter2);
        //get reference to event image
        event = (ImageView) findViewById(R.id.event);
        //setup the counter images
        setupDrawables();
        //link handler to callback
        handler=new Handler(callback);
     }
 
    //User pressed dice, lets start
    public void HandleClick(View arg0) {
        if(!rolling) {
            rolling=true;
            //Show rolling image
            dice_picture1.setImageResource(R.drawable.diceroll2);
            dice_picture2.setImageResource(R.drawable.diceroll2);
            dice_picture3.setImageResource(R.drawable.diceroll2);
            //Start rolling sound
            dice_sound.play(sound_id,1.0f,1.0f,0,0,1.0f);
            //Pause to allow image to update
            timer.schedule(new Roll(), 400);
        }
    }
 
    //When pause completed message sent to callback
    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }
 
    //Receives message from timer to start dice roll
    Callback callback = new Callback() {
        public boolean handleMessage(Message msg) {
            //Get roll result
            //Remember nextInt returns 0 to 5 for argument of 6
            //hence + 1
        	for(int i = 0; i < 3;i++)
        	{
        		switch(rng.nextInt(6)+1) {
        		case 1:
        			if(i==0)
        				dice_picture1.setImageResource(R.drawable.one);
        			if(i==1)
        				dice_picture2.setImageResource(R.drawable.one);
        			if(i==2)
        				dice_picture3.setImageResource(R.drawable.one);
        			counter++;
        			break;
        		case 2:
        			if(i==0)
        				dice_picture1.setImageResource(R.drawable.two);
        			if(i==1)
        				dice_picture2.setImageResource(R.drawable.two);
        			if(i==2)
        				dice_picture3.setImageResource(R.drawable.two);
        			break;
        		case 3:
        			if(i==0)
        				dice_picture1.setImageResource(R.drawable.three);
        			if(i==1)
        				dice_picture2.setImageResource(R.drawable.three);
        			if(i==2)
        				dice_picture3.setImageResource(R.drawable.three);
        			break;
        		case 4:
        			if(i==0)
        				dice_picture1.setImageResource(R.drawable.four);
        			if(i==1)
        				dice_picture2.setImageResource(R.drawable.four);
        			if(i==2)
        				dice_picture3.setImageResource(R.drawable.four);
        			break;
        		case 5:
        			if(i==0)
        				dice_picture1.setImageResource(R.drawable.five);
        			if(i==1)
        				dice_picture2.setImageResource(R.drawable.five);
        			if(i==2)
        				dice_picture3.setImageResource(R.drawable.five);
        			break;
        		case 6:
        			if(i==0)
        				dice_picture1.setImageResource(R.drawable.six);
        			if(i==1)
        				dice_picture2.setImageResource(R.drawable.six);
        			if(i==2)
        				dice_picture3.setImageResource(R.drawable.six);
        			break;
        		default:
        		}
        	}
            
        	if(counter != 0)
        	{
        		int second = counter/10;
        		int first = counter%10;
        		
        		if(second == 0)
        		{
        			newCount2.setImageResource(0);
        			newCount1.setImageResource(d[first]);
        		}
        		else
        		{
        			newCount2.setImageResource(d[second]);
        			newCount1.setImageResource(d[first]);
        		}
        	}
        	
            if(counter >=7 && counter < 14 && pick){
            	event.setImageResource(R.drawable.pick);
            	pick = false;
            }
            else{
            	if(counter >= 14 && counter < 21 && buy){
            		event.setImageResource(R.drawable.pay);
            		buy = false;
            	}
            	else{
            		if(counter >= 21){
            			event.setImageResource(R.drawable.drink);
                    	pick = true;
                    	buy = true;
                    	counter = 0;
                    	newCount1.setImageResource(0);
                    	newCount2.setImageResource(0);
                    }
            		else{
            			event.setImageResource(0);
            		}
            	}
            }
            
            rolling=false;    //user can press again
            return true;
        }
        
        
    };
 
    //Clean up
    @Override
    protected void onPause() {
        super.onPause();
        dice_sound.pause(sound_id);
    }
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
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
}
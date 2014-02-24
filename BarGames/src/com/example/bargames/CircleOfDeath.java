package com.example.bargames;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.example.bargames.SFT.Roll;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CircleOfDeath extends Activity {

	Handler handler;    //Post message to start roll
	Timer timer=new Timer();    //Used to implement feedback to user
	boolean newCard=false;        //Is die rolling?
	ImageView card_picture;
	TextView card_rule;
	TextView card_description;
	Deck deck = new Deck();
	SoundPool shuffle_sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	SoundPool flip_sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	int sound_shuffle;
	int sound_flip;
	int index;
	RelativeLayout rv1, rv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sound_shuffle=shuffle_sound.load(this,R.raw.card_shuffle,1);
		shuffle_sound.play(sound_shuffle,1.0f,1.0f,0,0,1.0f);
		setContentView(R.layout.activity_circle_of_death);
		sound_flip=flip_sound.load(this,R.raw.card_flip,1);
		deck.shuffleCards();
		card_picture = (ImageView) findViewById(R.id.imageView);
		card_rule = (TextView) findViewById(R.id.cardRules);
		card_description = (TextView) findViewById(R.id.cardDescription);
		rv1 = (RelativeLayout) findViewById(R.id.gameScreen);
        rv2 = (RelativeLayout) findViewById(R.id.description);

		handler=new Handler(callback);
	}
	
	public void startGame(View arg0){
    	rv2.setVisibility(View.INVISIBLE);
    	rv1.setVisibility(View.VISIBLE);
    	shuffle_sound.play(sound_shuffle,1.0f,1.0f,0,0,1.0f);
    }
	
	public void HandleClick(View arg0) {
        if(!newCard) {
            newCard=true;
            //Pause to allow image to update
            timer.schedule(new drawCard(), 400);
        }
    }
	
	 //When pause completed message sent to callback
    class drawCard extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }
	
	//Receives message from timer to swap cards
    Callback callback = new Callback() {

		@Override
		public boolean handleMessage(Message arg0) {
			
			if(index < 52){
				flip_sound.play(sound_flip,1.0f,1.0f,0,0,1.0f);
				card_picture.setImageResource(deck.getImage(index));
				card_rule.setText(deck.getRule(index));
				card_description.setText(deck.getDescription(index));
				index++;
			}
			else{
				deck.shuffleCards();
			}
			newCard = false;
			return true;
		}
    	
    };
    
  //Clean up
    @Override
    protected void onPause() {
        super.onPause();
    }
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.circle_of_death, menu);
		return true;
	}
	

}

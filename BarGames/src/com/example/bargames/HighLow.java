package com.example.bargames;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HighLow extends Activity {
	
	Deck deck = new Deck();
	SoundPool shuffle_sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	SoundPool flip_sound = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	int sound_shuffle;
	int sound_flip;
	int index, counter;
	int curNumber;
	ImageView card;
	RelativeLayout rv1, rv2;
	ImageView endCounter1, endCounter2, imgCounter1, imgCounter2;
	LinearLayout imgCounterLayout, endCounterLayout;
	int[] d = new int[10];
	boolean drinking = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_low);
		sound_shuffle=shuffle_sound.load(this,R.raw.card_shuffle,1);
		shuffle_sound.play(sound_shuffle,1.0f,1.0f,0,0,1.0f);
		sound_flip=flip_sound.load(this,R.raw.card_flip,1);
		deck.shuffleCards();
		setupDrawables();
		card = (ImageView) findViewById(R.id.cardImg);
		card.setImageResource(deck.getImage(index));
		curNumber = deck.getNumber(index);
		index++;
		rv2 = (RelativeLayout) findViewById(R.id.description);
		rv1 = (RelativeLayout) findViewById(R.id.gameScreen);
		imgCounterLayout = (LinearLayout) findViewById(R.id.counterLayout);
		imgCounterLayout.setVisibility(View.VISIBLE);
        endCounterLayout = (LinearLayout) findViewById(R.id.endCounterLayout);
        imgCounter1 = (ImageView) findViewById(R.id.counter1);
        imgCounter2 = (ImageView) findViewById(R.id.counter2);
        endCounter1 = (ImageView) findViewById(R.id.endCounter1);
        endCounter2 = (ImageView) findViewById(R.id.endCounter2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_low, menu);
		return true;
	}
	
	public void startGame(View arg0){
    	rv2.setVisibility(View.INVISIBLE);
    	rv1.setVisibility(View.VISIBLE);
    	shuffle_sound.play(sound_shuffle,1.0f,1.0f,0,0,1.0f);
    }
	
	public void selectHigh(View arg0){
		if(drinking == true){
			drinking = false;
			endCounterLayout.setVisibility(View.INVISIBLE);
			imgCounterLayout.setVisibility(View.VISIBLE);
		}
		index++;
		card.setImageResource(deck.getImage(index));
		if(deck.getNumber(index) > curNumber){
			updateCounter();
		}
		else{
			updateCounter();
			incorrect();
		}
		curNumber = deck.getNumber(index);

		if(index == 51){
			reshuffle();
		}
	}
	
	public void selectLow(View arg0){
		if(drinking == true){
			drinking = false;
			endCounterLayout.setVisibility(View.INVISIBLE);
			imgCounterLayout.setVisibility(View.VISIBLE);
		}
		index++;
		card.setImageResource(deck.getImage(index));
		if(deck.getNumber(index) < curNumber){
			updateCounter();
		}
		else{
			updateCounter();
			incorrect();
		}
		curNumber = deck.getNumber(index);
		
		if(index == 51){
			reshuffle();
		}

	}
	
	public void reshuffle(){
		deck.shuffleCards();
		shuffle_sound.play(sound_shuffle,1.0f,1.0f,0,0,1.0f);
		index = 0;
	}
	
	public void incorrect(){
		drinking = true;
		counter = 0;
		imgCounterLayout.setVisibility(View.INVISIBLE);
		endCounterLayout.setVisibility(View.VISIBLE);
	}
	
	public void updateCounter(){
		counter++;
		int second = counter/10;
		int first = counter%10;
		imgCounter2.setImageResource(d[second]);
		imgCounter1.setImageResource(d[first]);	
		endCounter2.setImageResource(d[second]);
		endCounter1.setImageResource(d[first]);
	
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

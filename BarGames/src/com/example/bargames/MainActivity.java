package com.example.bargames;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     }
    
    public void twentyoneAces(View view){
    	Intent intent = new Intent(this, SFT.class);
    	startActivity(intent);
    }
    
    public void circleofDeath(View view){
    	Intent intent = new Intent(this,CircleOfDeath.class);
    	startActivity(intent);
    }
    
    public void simonSays(View view){
    	Intent intent = new Intent(this,SimonSays.class);
    	startActivity(intent);
    }
    
    public void killer(View view){
    	Intent intent = new Intent(this,Killer.class);
    	startActivity(intent);
    }
    
    public void highLow(View view){
    	Intent intent = new Intent(this,HighLow.class);
    	startActivity(intent);
    }
}
package com.example.bargames;

import java.util.Random;

public class Deck {
	Card[] deck = new Card[52];
	
	public Deck(){
		initDeck();
	}
	
	public void initDeck(){
		int index = 0;
		for(int number = 1; number < 14; number++){
			for(int suit = 1; suit < 5; suit++){
				deck[index] = new Card(number, suit);
				index++;
			}
		}	
	}
	
	// Shuffle cards using Fisher-Yates algorithm
		public void shuffleCards(){
			Random rnd = new Random();
			for(int i = deck.length - 1; i > 0; i--)
			{
				int index = rnd.nextInt(i + 1);
				
				//swap function
				Card a = deck[index];
				deck[index] = deck[i];
				deck[i] = a;
			}
		}
		
		public int getImage(int index){
			return deck[index].image;
		}
		
		public String getDescription(int index){
			return deck[index].description;
		}
		
		public String getRule(int index){
			return deck[index].rule;
		}
		
		public int getNumber(int index){
			return deck[index].number;
		}
}

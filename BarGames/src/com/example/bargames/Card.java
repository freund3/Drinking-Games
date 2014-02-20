package com.example.bargames;

public class Card {
	int number;
	int image;
	int suit; // 1 == clubs, 2 == diamonds, 3 == hearts, 4 == spades
	String rule;
	String description;
	
	public Card(int number, int suit){
		this.number = number;
		this.image =getImage(number, suit);
		this.suit = suit;
		this.rule = getRule(number, suit);
		this.description = getDescription(number);
	}
	
	public String getDescription(int number){
		if(number == 1) return null;
		if(number == 2) return "Chose someone to drink!";
		if(number == 3) return "You Drink!";
		if(number == 4) return "Women Drink!";
		if(number == 5) return "Start with three fingers!";
		if(number == 6) return "Men Drink!";
		if(number == 7) return "All point up, last one drinks!";
		if(number == 8) return "Everyone Drinks!";
		if(number == 9) return "Say a word, and the person to your right has to say a word that rhymes. Person that fails, drinks!";
		if(number == 10) return "You pick a category of things, and the person to your right must come up with something " +
				"that falls within that category! Person who fails, drinks!";
		if(number == 11) return "Start drinking! Each player drinks at the same time as the person to their left. " +
				"No player can stop drinking until the player before them stops.";
		if(number == 12) return "Get people to answer your questions and they have to drink!";
		if(number == 13) return "Start a one word sentence that everyone has to repeat and then add to it. Person who fails, drinks!";
		return null;
	}
	
	public String getRule(int number, int suit){
		if(number == 1) return "Make a rule!";
		if(number == 2) return "YOU";
		if(number == 3) return "ME";
		if(number == 4) return "WHORES";
		if(number == 5) return "NEVER HAVE I EVER";
		if(number == 6) return "DICKS";
		if(number == 7) return "HEAVEN";
		if(number == 8) return "SOCIAL";
		if(number == 9) return "BUST A RHYME";
		if(number == 10) return "CATEGORIES";
		if(number == 11) return "WATERFALL";
		if(number == 12) return "QUESTION MASTER";
		if(number == 13) return "SENTENCES";
		return null;
	}
	
	public int getImage(int i, int j){
		if(i == 1 && j == 1) return R.drawable.acec;
		if(i == 1 && j == 2) return R.drawable.aced;
		if(i == 1 && j == 3) return R.drawable.aceh;
		if(i == 1 && j == 4) return R.drawable.aces;
		if(i == 2 && j == 1) return R.drawable.twoc;
		if(i == 2 && j == 2) return R.drawable.twod;
		if(i == 2 && j == 3) return R.drawable.twoh;
		if(i == 2 && j == 4) return R.drawable.twoc;
		if(i == 3 && j == 1) return R.drawable.threec;
		if(i == 3 && j == 2) return R.drawable.threed;
		if(i == 3 && j == 3) return R.drawable.threeh;
		if(i == 3 && j == 4) return R.drawable.threes;
		if(i == 4 && j == 1) return R.drawable.fourc;
		if(i == 4 && j == 2) return R.drawable.fourd;
		if(i == 4 && j == 3) return R.drawable.fourh;
		if(i == 4 && j == 4) return R.drawable.fours;
		if(i == 5 && j == 1) return R.drawable.fivec;
		if(i == 5 && j == 2) return R.drawable.fived;
		if(i == 5 && j == 3) return R.drawable.fiveh;
		if(i == 5 && j == 4) return R.drawable.fives;
		if(i == 6 && j == 1) return R.drawable.sixc;
		if(i == 6 && j == 2) return R.drawable.sixd;
		if(i == 6 && j == 3) return R.drawable.sixh;
		if(i == 6 && j == 4) return R.drawable.sixs;
		if(i == 7 && j == 1) return R.drawable.sevenc;
		if(i == 7 && j == 2) return R.drawable.sevend;
		if(i == 7 && j == 3) return R.drawable.sevenh;
		if(i == 7 && j == 4) return R.drawable.sevens;
		if(i == 8 && j == 1) return R.drawable.eightc;
		if(i == 8 && j == 2) return R.drawable.eightd;
		if(i == 8 && j == 3) return R.drawable.eighth;
		if(i == 8 && j == 4) return R.drawable.eights;
		if(i == 9 && j == 1) return R.drawable.ninec;
		if(i == 9 && j == 2) return R.drawable.nined;
		if(i == 9 && j == 3) return R.drawable.nineh;
		if(i == 9 && j == 4) return R.drawable.nines;
		if(i == 10 && j == 1) return R.drawable.tenc;
		if(i == 10 && j == 2) return R.drawable.tend;
		if(i == 10 && j == 3) return R.drawable.tenh;
		if(i == 10 && j == 4) return R.drawable.tens;
		if(i == 11 && j == 1) return R.drawable.jackc;
		if(i == 11 && j == 2) return R.drawable.jackd;
		if(i == 11 && j == 3) return R.drawable.jackh;
		if(i == 11 && j == 4) return R.drawable.jacks;
		if(i == 12 && j == 1) return R.drawable.queenc;
		if(i == 12 && j == 2) return R.drawable.queend;
		if(i == 12 && j == 3) return R.drawable.queenh;
		if(i == 12 && j == 4) return R.drawable.queens;
		if(i == 13 && j == 1) return R.drawable.kingc;
		if(i == 13 && j == 2) return  R.drawable.kingd;
		if(i == 13 && j == 3) return R.drawable.kingh;
		if(i == 13 && j == 4) return R.drawable.kings;
		return 0;
	}
}

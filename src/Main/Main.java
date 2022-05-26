package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import Main.*;
import java.util.ArrayList;

public class Main {

	public static void maim() {
		new Main().runner();
	}

	public void runner() {
		int points = 0;
		Card[] cards = new GenerateCards().spawnCards();
		cards = new DeckFunucations(cards).shuffle();
		Card[] temp;
		Card[] cardsInhand = new DeckFunucations(cards).drawCards(cards, 12);
		cards = new DeckFunucations(cards).removeCards(cards, cardsInhand);

		Card[] cardMatch = new VerficationMatch().checkCardsOnBoradForMatch(cards);
		cardsInhand = new DeckFunucations(cardsInhand).removeCards(cardsInhand,
		cardMatch);
		Card[] cardUnMatch = new
		VerficationNonMatch().checkCardsOnBoradForMatch(cardsInhand);
		cardsInhand = new DeckFunucations(cardsInhand).removeCards(cardsInhand,
		cardUnMatch);

		ArrayList<Card> f = new ArrayList<Card>();
		Collections.addAll(f, cardUnMatch);
		Collections.addAll(f, cardMatch);
		// System.out.println("Matches / non-UnMatch ");
		// f.forEach(i -> System.out.print(i.getNumber() + " " + i.getColour() + " " +
		// i.getShading() + " " + i.getSymbol() + " + "));


		if (cardMatch.length > 0) points = +(cardMatch.length / 3);
		if (cardUnMatch.length > 0) points = +(cardUnMatch.length / 3);

		if (cardMatch.length == 0 && cardMatch.length == 0 && cards.length > 3) {
		temp = new DeckFunucations(cards).drawCards(cards, 3);
		cards = new DeckFunucations(cards).removeCards(cards, temp);
		cardsInhand = new DeckFunucations(cardsInhand).addCardsToHand(cardsInhand,
		temp);
		}

		// System.out.println("\npoints: " + points);
		// System.out.println(cardsInhand.length);
	}
}


class GenerateCards {


	//This is test method, to stop slow verficiation go to generate card for actual method
		//This method generates all the cards in the deck:
		public Card[] spawnCards() {

			String[] colour = { "red", "green", "blue" };
			String[] symbol = { "ovals", "squiggles", "diamonds" };
			String[] shading = { "solid", "open", "striped" };
	
			int counter = 0, max = 81;
			Card[] cards = new Card[max];
			Integer[] numStore = new Integer[max];
			String[] shadingStore = new String[max];
			String[] symbolStore = new String[max];
			String[] colourStore = new String[max];
	
			//@ loop_invariant 0 <= i && i <= numStore.length;
			//@ loop_writes i, numStore[*];
			//@ maintaining \forall int k; 0 <= k < i; numStore[k] ==  1 | numStore[k] ==  2 | numStore[k] ==  3;
			//@ decreases numStore.length - i;
			for (int i = 0; i < numStore.length; i++) {
				// @ assume 0 <= numberVal < cards.length;
				numStore[i] = (counter % 3) + 1;
				if (i == 26 || i == 53)
					counter = +1;
			}
	
			//@ loop_invariant 0 <= i && i % 3 <= colour.length;
			//@ loop_invariant 0 <= i && i <= colourStore.length;
			//@ loop_writes i, colourStore[*];
			//@ maintaining \forall int k; 0 <= k < i; colourStore[k] ==  colour[k % 3];
			//@ decreases colourStore.length - i;
			for (int i = 0; i < colourStore.length; i++) {
				int sum = i % 3;
				colourStore[i] = colour[sum];
			}
	
			//@ loop_invariant 0 <= i && i % 3 <= symbol.length;
			//@ loop_invariant 0 <= i && i <= symbolStore.length;
			//@ maintaining \forall int k; 0 <= k < i; symbolStore[k] ==  symbol[0] | symbolStore[k] ==  symbol[1] | symbolStore[k] ==  symbol[2];
			//@ loop_writes i, symbolStore[*];
			for (int i = 0; i < symbolStore.length; i++) {
				i = +2;
				symbolStore[i] = symbol[i % 3];
				symbolStore[i - 1] = symbol[i % 3];
				symbolStore[i - 2] = symbol[i % 3];
			}
	
	
			
			//@ loop_invariant 0 <= i && i <= cards.length;
			//@ loop_invariant 0 <= counter % 3 && counter % 3 <= shading.length;
			//@ loop_writes i, cards[*];
			//@ maintaining \forall int k; 0 <= k < i; shadingStore[k] ==  shading[0] |  shadingStore[k] == shading[1] |  shadingStore[k] == shading[2];
			//@ decreases cards.length - i;
			for (int i = 0; i < cards.length; i += 9) {
				//@ assume (i + 9) <= cards.length;
				shadingStore[i] = (shading[counter % 3]);
				shadingStore[i + 1] = (shading[counter % 3]);
				shadingStore[i + 2] = (shading[counter % 3]);
				shadingStore[i + 3] = (shading[counter % 3]);
				shadingStore[i + 4] = (shading[counter % 3]);
				shadingStore[i + 5] = (shading[counter % 3]);
				shadingStore[i + 6] = (shading[counter % 3]);
				shadingStore[i + 7] = (shading[counter % 3]);
				shadingStore[i + 8] = (shading[counter % 3]);
				counter++;
			}
	
			//@ loop_invariant 0 <= i && i <= cards.length;
			//@ decreases cards.length - i;
			for (int i = 0; i < cards.length; i++) {
				cards[i] = new Card();
				cards[i].setNumber(numStore[i]);
				cards[i].setShading(shadingStore[i]);
				cards[i].setSymbol(symbolStore[i]);
				cards[i].setColour(colourStore[i]);
			}
	
			return cards;
		}
	}



// @ non_null_by_default
class Card {
	/*
	This class is basically full of getters and setters so not much explainning to do, it's basically just items which constructs a card
	*/
	public String colour;
	public String symbol;
	public int number;
	public String shading;

	public Card() {
	}

	 //@ requires colours == "red" | colours == "green" | colours == "blue";
	 //@ ensures colour == "red" | colour == "green" | colour == "blue";
	 //@ requires symbols == "ovals" | symbols == "squiggles" | symbols == "diamonds";
	 //@ ensures symbol == "ovals" | symbol == "squiggles" | symbol == "diamonds";
	 //@ requires numbers >= -1;
	 //@ ensures number >= 0;
	 //@ requires shadings == "solid" | shadings == "open" | shadings == "striped";
	 //@ ensures shading == "solid" | shading == "open" | shading == "striped";
	public Card(String colours, String symbols, int numbers, String shadings) {
		this.colour = colours;
		this.symbol = symbols;
		this.number = numbers;
		this.shading = shadings;
	}

	//@ requires colour == "red" | colour == "green" | colour == "blue";
	//@ ensures colour == "red" | colour == "green" | colour == "blue";
	public /*@ pure @*/ String getColour() {
		return colour;
	}

	//@ requires colours == "red" | colours == "green" | colours == "blue";
	//@ ensures colour == "red" | colour == "green" | colour == "blue";
	public /*@ pure @*/ void setColour(String colours) {
		// @ assume colours != null;
		this.colour = colours;
	}

	 //@ requires symbol == "ovals" | symbol == "squiggles" | symbol == "diamonds";
	 //@ ensures symbol == "ovals" | symbol == "squiggles" | symbol == "diamonds";
	public /*@ pure @*/ String getSymbol() {
		return symbol;
	}

	 //@ requires symbols == "ovals" | symbols == "squiggles" | symbols == "diamonds";
	 //@ ensures symbol == "ovals" | symbol == "squiggles" | symbol == "diamonds";
	public /*@ pure @*/ void setSymbol(String symbols) {
		this.symbol = symbols;
	}

	//@ ensures \result >= 0;
	public /*@ pure @*/ int getNumber() {
		return number;
	}

	//@ requires numbers >= 0;
	//@ ensures number >= 0;
	public /*@ pure @*/ void setNumber(int numbers) {
		this.number = numbers;
	}

	 //@ requires shading == "solid" | shading == "open" | shading == "striped";
	 //@ ensures shading == "solid" | shading == "open" | shading == "striped";
	public /*@ pure @*/ String getShading() {
		return shading;
	}

	
	 
	//@ requires shadings == "solid" | shadings == "open" | shadings == "striped"; 
	//@ ensures shading == "solid" | shading == "open" | shading == "striped";
	public /*@ pure @*/ void setShading(String shadings) {
		this.shading = shadings;
	}
}

class VerficationMatch {
	public String symbolA, symbolB;

	//@ requires a.getShading() != null && a.getColour() != null && a.getSymbol() != null;
	//@ requires a.getNumber() == 1 |a.getNumber() == 2 | b.getNumber() == 3;
	//@ requires a.getColour() == "red" | a.getColour() == "green" | a.getColour() == "blue";
	//@ requires b.getShading() != null && b.getColour() != null && b.getSymbol() != null;
	//@ requires b.getNumber() == 1 |b.getNumber() == 2 | b.getNumber() == 3;
	//@ pure
	  public boolean checkVerify(Card a, Card b) {
		if (a.getNumber() != b.getNumber())
			if (a.getShading().equalsIgnoreCase(b.getShading()))
				if (a.getColour().equalsIgnoreCase(b.getColour()))
				if (a.getSymbol().equalsIgnoreCase(b.getSymbol())) {
						return true;
					}
					return false;
	}

	public Card[] checkCardsOnBoradForMatch(Card[] onBoard) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (Card i : onBoard)
			for (Card j : onBoard)
				for (Card k : onBoard)
					if (i != null && j != null && k != null) {
						if (checkVerify(i, j) && checkVerify(i, k) && checkVerify(j, k))
							if (!cards.contains(i) && !cards.contains(j) && !cards.contains(k)) {
								cards.add(i);
								cards.add(j);
								cards.add(k);
							}
					}
		Card[] arr = new Card[cards.size()];
		return cards.toArray(arr);
	}
}

class VerficationNonMatch {

	//@ requires a.getShading() != null && a.getColour() != null && a.getSymbol() != null;
	//@ requires a.getNumber() == 1 |a.getNumber() == 2 | b.getNumber() == 3;
	//@ requires b.getShading() != null && b.getColour() != null && b.getSymbol() != null;
	//@ requires b.getNumber() == 1 |b.getNumber() == 2 | b.getNumber() == 3;
	//@ pure
	public boolean checkUNVerify(Card a, Card b) {
		// checks no amount match
		if (a.getNumber() != b.getNumber())
			if (!a.getShading().equalsIgnoreCase(b.getShading()))
				if (!a.getColour().equalsIgnoreCase(b.getColour()))
					if (!a.getSymbol().equalsIgnoreCase(b.getSymbol()))
						return true;
		return false;
	}

	public Card[] checkCardsOnBoradForMatch(Card[] onBoard) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (Card i : onBoard)
			for (Card j : onBoard)
				for (Card k : onBoard)
					if (i != null && j != null && k != null) {
						if (checkUNVerify(i, j) && checkUNVerify(i, k) && checkUNVerify(j, k))
							if (!cards.contains(i) && !cards.contains(j) && !cards.contains(k)) {
								cards.add(i);
								cards.add(j);
								cards.add(k);
							}
					}
		Card[] arr = new Card[cards.size()];
		return cards.toArray(arr);
	}

}

class DeckFunucations {

	private Card[] cards;

	public DeckFunucations(Card[] cards) {
		this.cards = cards;
	}

	public Card[] shuffle() {
		List<Card> list = Arrays.asList(cards);
		Collections.shuffle(list, new Random());
		Card[] arr = new Card[list.size()];
		return list.toArray(arr);
	}

	public Card[] addCardsToHand(Card[] hand, Card[] addedToHand) {
		cards = new Card[hand.length + addedToHand.length];
		for (int i = 0; i < hand.length; i++)
			cards[i] = hand[i];
		for (int i = 0; i < addedToHand.length; i++)
			cards[i + addedToHand.length] = hand[i];
		return cards;
	}

	public Card[] drawCards(Card[] cards, int cardsBeingDrawn) {
		if (cards.length == 0)
			return null;

		// creates list for drawn cards.
		Card[] cardDrawnList = new Card[cardsBeingDrawn];
		// passes through a loop and get random card everytime if there isn't an item
		// available it roll back to previous i
		for (int i = 0; i < cardsBeingDrawn; i++) {
			// get random number
			int drawnCard = (int) (Math.random() * ((cards.length - 1) - 0 + 1) + 0);
			// int drawnCard = i;

			if (isCardAvaiable(cards, cards[drawnCard]))
				cardDrawnList[i] = cards[drawnCard];
			else
				i--;
		}
		return cardDrawnList;
	}

	public static int CardSizeAvaiable(Card[] list) {
		for (int i = 0; i < list.length; i++)
			if (list[i] == null)
				return i;
		return 0;
	}

	// removes a selected card from a deck.
	public Card[] removeCards(Card[] cards, Card[] cardsToRemove) {
		Card[] result = new Card[cards.length - cardsToRemove.length];
		for (Card i : cards)
			if (!isCardAvaiable(cardsToRemove, i))
				result[CardSizeAvaiable(result)] = i;
		return result;
	}

	// Checks if a card is in deck of cards, returns -1 if item not in the list.
	public static int isCardAvaiableReturnIndex(Card[] cards, Card card) throws Exception {
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] == card)
				return i;
		}
		return -1;
	}

	// Checks if a card is in deck of cards
	public static boolean isCardAvaiable(Card[] cards, Card card) {
		for (Card i : cards) {
			if (i == card)
				return true;
		}
		return false;
	}

}
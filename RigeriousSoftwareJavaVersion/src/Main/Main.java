package Main;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static String[] colour = { "red", "green", "blue" };
	static String[] symbol = { "ovals", "squiggles", "diamonds" };
	static String[] shading = { "solid", "open", "striped" };
	static TestCode testCode = new TestCode();

	public static void main(String... args) {
		Card[] cards = spawnCards();
		Card[] newCards = drawCards(cards, 12);
		Arrays.asList(cards).forEach(i -> System.out.println(i != null ? i.getNumber() + " " + i.getColour() + " " + i.getSymbol() + " " + i.getShading() : i));
		
		for (int i = 0; i < 100; i++) {
//		System.out.println(newCards.length);
		testCode.checkForMatch(newCards);
		}
		
		System.out.println("cards lenght: " +  (cards.length));
		
		cards = removeCards(cards, newCards);
		
		System.out.println("cards lenght: " +  (cards.length));
		//System.out.println(cards.length);
		
//		testCode.checkTwoListForMatch(cards, newCards);
		
		//Arrays.asList(cards).forEach(i -> System.out.println(i != null ? i.getNumber() + " " + i.getColour() + " " + i.getSymbol() + " " + i.getShading() : i));
		
	}
	
	
	public static int CardSizeAvaiable(Card[] list) {
		 for (int i = 0; i < list.length; i++) {
			 if (list[i] == null)
				 return i;
		 }
		return -1;
	}

	public static Card[] removeCards(Card[] cards, Card[] cardsToRemove) {
		Card[] result = new Card[cards.length - cardsToRemove.length];
		int count = 0;
		for (Card i : cards) {
			if (!isCardAvaiable(cardsToRemove, i)) {
				result[CardSizeAvaiable(result)] = i;
				System.out.println(CardSizeAvaiable(result));
			}
		}
		
		
//		for (Card i : cardsToRemove) 
//			if (isCardAvaiable(cards, i)) {
//				result[CardSizeAvaiable(result)] = i;
//			}
		return result;
	}
	
	public static boolean isCardAvaiable(Card[] cards, Card card) {
		for (Card i : cards) {
			if (i == card)
			return true;
		}
		return false;
	}

	public static Card[] drawCards(Card[] cards, int cardsBeingDrawn) {
		// creates list for drawn cards.
		Card[] cardDrawnList = new Card[cardsBeingDrawn];

		//passes through a loop and get random card everytime if there isn't an item available it roll back to previous i 
		for (int i = 0; i < cardsBeingDrawn; i++) {
			// get random number
			int drawnCard = (int) (Math.random() * ((cards.length - 1) - 0 + 1) + 0);
			
			if (isCardAvaiable(cards, cards[drawnCard])) 
			cardDrawnList[i] = 	cards[drawnCard];
			else 
			i--;
		}
		return cardDrawnList;
	}

	// This method generates all the cards.
	public static Card[] spawnCards() {
		Card[] cards = new Card[81];

		int counter = 0;

		/*
		 * This code sets all items in table.
		 */
		// creates a card, then sets every card to a number, so first 27 number will be
		// 1, 2 till 54
		for (int i = 0; i < cards.length; i++) {
			cards[i] = new Card();
			cards[i].setNumber((counter % 3) + 1);
			if (i == 26 || i == 53) counter++;
		}

		// set colours in order of red, green blue
		for (int i = 0; i < cards.length; i += 1) {
			cards[i].setColour((colour[(i % 3)]));

		}

		// sets items in 3s, so oval oval oval, squiggles, squiggles squiggles, etc
		for (int i = 0; i < cards.length; i += 3) {
			for (int j = 0; j < 3; j++)
				cards[i + j].setSymbol((symbol[counter % 3]));
			counter++;
		}

		// sets items in 9s, refer to top example except 9s
		for (int i = 0; i < cards.length; i += 9) {
			for (int j = i; j < i + 9; j++)
				cards[j].setShading((shading[counter % 3]));
			counter++;
		}

		// prints all the items
		//Arrays.asList(cards).forEach(i -> System.out.println(i.getNumber() + " " + i.getColour() + " " + i.getSymbol() + " " + i.getShading()));

		return cards;
	}
}
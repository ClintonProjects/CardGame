package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

	static String[] colour = { "red", "green", "blue" };
	static String[] symbol = { "ovals", "squiggles", "diamonds" };
	static String[] shading = { "solid", "open", "striped" };
	static TestCode testCode = new TestCode();
	static int k = 1;

	public static void main(String... args) throws Exception {
		Card[] cards = spawnCards();
		//shuffle(cards);
		Card[] cardsInhand = drawCards(cards, 12);
		
		// Arrays.asList(cards).forEach(i -> System.out.println(i != null ?
		// i.getNumber() + " " + i.getColour() + " " + i.getSymbol() + " " +
		// i.getShading() : i));
		
		for (int i = 0; i < 100; i++)
			// This is just a test and can be removed, to lazy to load junits xD
			testCode.checkForMatch(cardsInhand);
		
		cards = removeCards(cards, cardsInhand);
		System.out.println("card length: " + cards.length);
		
		

//		Arrays.asList(cards)
//				.forEach(i -> System.out.println(i != null
//						? k++ + "  " + i.getNumber() + " " + i.getColour() + " " + i.getSymbol() + " " + i.getShading()
//						: k++ + " empty"));

	}

	public static Card[] shuffle(Card[] cards) {
		List<Card> list = Arrays.asList(cards);
		Collections.shuffle(list, new Random());
		Card[] arr = new Card[list.size()];
		return list.toArray(arr);
	}

	public static boolean[] checkCardsOnBoradForMatch(Card[] onBoard) {
		boolean[] cardsMatch = new boolean[onBoard.length];
		for (int i = 0; i < onBoard.length; i++) {
			cardsMatch[i] = cardMatch(onBoard, onBoard[i]);
		}
		return cardsMatch;
	}

	public static boolean cardMatch(Card[] cards, Card checkForMatch) {
		boolean OneMatch = checkForMatch.getNumber() == 1;
		boolean TwoMatch = checkForMatch.getNumber() == 2;
		boolean threeMatch = checkForMatch.getNumber() == 3;

		for (Card i : cards) {
			if (i.getColour().equals(checkForMatch.getColour()) && i.getShading().equals(checkForMatch.getShading())
					&& i.getSymbol().equals(checkForMatch.getSymbol()) && i.getNumber() != checkForMatch.getNumber()) {
				if (OneMatch == false & i.getNumber() == 1)
					OneMatch = true;
				else if (threeMatch == false & i.getNumber() == 2)
					TwoMatch = true;
				else if (threeMatch == false & i.getNumber() == 3)
					threeMatch = true;
			}
		}

		return OneMatch && TwoMatch && threeMatch;
	}

	public static int CardSizeAvaiable(Card[] list) throws Exception {
		for (int i = 0; i < list.length; i++)
			if (list[i] == null)
				return i;
		return 0;
	}

	// removes a selected card from a deck.
	public static Card[] removeCards(Card[] cards, Card[] cardsToRemove) throws Exception {
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
		throw new Exception("Card index not avaiable");
	}

	// Checks if a card is in deck of cards
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

		// passes through a loop and get random card everytime if there isn't an item
		// available it roll back to previous i
		for (int i = 0; i < cardsBeingDrawn; i++) {
			// get random number
//			int drawnCard = (int) (Math.random() * ((cards.length - 1) - 0 + 1) + 0);
			int drawnCard = i;

			if (isCardAvaiable(cards, cards[drawnCard]))
				cardDrawnList[i] = cards[drawnCard];
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
			if (i == 26 || i == 53)
				counter++;
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
		// Arrays.asList(cards).forEach(i -> System.out.println(i.getNumber() + " " +
		// i.getColour() + " " + i.getSymbol() + " " + i.getShading()));

		return cards;
	}
}
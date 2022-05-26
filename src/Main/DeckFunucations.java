package Main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DeckFunucations {

	private Card[] cards;
	
	public DeckFunucations(Card[] cards) {
		this.cards= cards;
	}
	

	//shuffles the deck.
	public Card[] shuffle() {
		List<Card> list = Arrays.asList(cards);
		Collections.shuffle(list, new Random());
		Card[] arr = new Card[list.size()];
		return list.toArray(arr);
	}
	
	//allows the user to add a card to there hand
	public  Card[] addCardsToHand(Card[] hand, Card[] addedToHand) {
		cards = new Card[hand.length + addedToHand.length];
		for (int i = 0; i < hand.length; i++)
			cards[i] = hand[i];
		for (int i = 0; i < addedToHand.length; i++)
			cards[i + addedToHand.length] = hand[i];
		return cards;
	}
	
	
	
	//lets the user draw a card
	public Card[] drawCards(Card[] cards, int cardsBeingDrawn) {
		if (cards.length == 0) return null;

		// creates list for drawn cards.
		Card[] cardDrawnList = new Card[cardsBeingDrawn];
		// passes through a loop and get random card everytime if there isn't an item
		// available it roll back to previous i
		for (int i = 0; i < cardsBeingDrawn; i++) {
			// get random number
			int drawnCard = (int) (Math.random() * ((cards.length - 1) - 0 + 1) + 0);
//			int drawnCard = i;

			if (isCardAvaiable(cards, cards[drawnCard]))
				cardDrawnList[i] = cards[drawnCard];
			else
				i--;
		}
		return cardDrawnList;
	}
	
	//Checks if there cards avaiable
	public static int CardSizeAvaiable(Card[] list) throws Exception {
		for (int i = 0; i < list.length; i++)
			if (list[i] == null) return i;
		return 0;
	}

	// removes a selected card from a deck.
	public Card[] removeCards(Card[] cards, Card[] cardsToRemove) throws Exception {
		Card[] result = new Card[cards.length - cardsToRemove.length];
		for (Card i : cards)
			if (!isCardAvaiable(cardsToRemove, i)) result[CardSizeAvaiable(result)] = i;
		return result;
	}

	// Checks if a card is in deck of cards, returns -1 if item not in the list.
	public static int isCardAvaiableReturnIndex(Card[] cards, Card card) throws Exception {
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] == card) return i;
		}
		throw new Exception("Card index not avaiable");
	}

	// Checks if a card is in deck of cards
	public static boolean isCardAvaiable(Card[] cards, Card card) {
		for (Card i : cards) {
			if (i == card) return true;
		}
		return false;
	}
	
	
	
}

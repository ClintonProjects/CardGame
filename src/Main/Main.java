package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Card.Card;
import Card.DeckFunucations;
import Card.GenerateCards;
import Card.VerficationNonMatch;

public class Main {

	static TestCode testCode = new TestCode();
	static int k = 1;

	public static void main(String... args) throws Exception {
		playGame();
	}

	public static void playGame() throws Exception {
		int points = 0;
		Card[] cards = null;
		cards = new GenerateCards(cards).spawnCards();
		
		System.out.println("All cards: " + cards.length);
		cards = new DeckFunucations(cards).shuffle();
		Card[] cardsInhand = new DeckFunucations(cards).drawCards(cards, 12);
		
		new VerficationNonMatch().checkCardsOnBoradForMatch(cardsInhand);
		//System.out.println(test.length);
		
//		cards = new DeckFunucations(cards).removeCards(cards, cardsInhand);
//		System.out.println("cardsToAdd: " + cardsInhand.length);
//		while (cardsInhand.length != 0 || cards.length != 3) {
//			System.out.println("cards in hand: " + cards.length);
//			Card[] CardPoints = checkForUnmatchesVerify(cardsInhand);
//			System.out.println("CardPoints in hand: " + CardPoints.length);
//			if (CardPoints.length > 0) {
//				cardsInhand = new DeckFunucations(cardsInhand).removeCards(cardsInhand, CardPoints);
//				points++;
//			} else {
//				// System.out.println("cards.length " + cards.length);
//				if (cards.length == 3) {
//					points++;
//					break;
//				} else {
//					Card[] cardsToAdd = new DeckFunucations(cards).drawCards(cards, 3);
//					// System.out.println("cardsToAdd: " + cardsToAdd.length);
//					cards = new DeckFunucations(cards).removeCards(cards, cardsToAdd);
//					// System.out.println("cards.length: " + cards.length);
//					cardsInhand = new DeckFunucations(cards).addCardsToHand(cardsInhand, cardsToAdd);
//				}
//			}
//			System.out.println("Points: [" + points + "]");
//		}
	}
}
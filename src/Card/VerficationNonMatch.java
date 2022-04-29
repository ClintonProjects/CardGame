package Card;

import java.util.ArrayList;

public class VerficationNonMatch {

	public boolean checkUNVerify(Card a, Card b) {
		// checks no amount match
		if (a.getNumber() != b.getNumber()) if (!a.getShading().equalsIgnoreCase(b.getShading()))
			if (!a.getColour().equalsIgnoreCase(b.getColour())) if (!a.getSymbol().equalsIgnoreCase(b.getSymbol())) return true;
		return false;
	}

	public void checkCardsOnBoradForMatch(Card[] onBoard) {
		ArrayList<ThreeCard> card = new ArrayList<ThreeCard>();

		for (int i = 1; i < onBoard.length * onBoard.length * onBoard.length; i++) {
			System.out.println("index: " + i);
			Card cardA = onBoard[ onBoard.length * onBoard.length % i];
			Card cardB = onBoard[i / onBoard.length];
			Card cardC = onBoard[i % onBoard.length];

			if (checkUNVerify(cardA, cardB) && checkUNVerify(cardA, cardC) && checkUNVerify(cardB, cardC))
				card.add(new ThreeCard(new Card[i / onBoard.length * onBoard.length], new Card[i / onBoard.length], new Card[i % onBoard.length]));
		}
		System.out.println(card.size());

		// Card[] arr = new Card[card.size()];
		// return card.toArray(arr);
	}

}

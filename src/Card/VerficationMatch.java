package Card;

public class VerficationMatch {
	
	// Checks for match, so shape colour and symbols match and number 1,2,3
	public boolean cardMatch(Card[] cards, Card checkForMatch) {
		boolean OneMatch = checkForMatch.getNumber() == 1, TwoMatch = checkForMatch.getNumber() == 2, threeMatch = checkForMatch.getNumber() == 3;
		for (Card i : cards) {
			if (i.getColour().equals(checkForMatch.getColour()) && i.getShading().equals(checkForMatch.getShading())
					&& i.getSymbol().equals(checkForMatch.getSymbol()) && i.getNumber() != checkForMatch.getNumber()) {
				if (OneMatch == false & i.getNumber() == 1)
					OneMatch = true;
				else if (threeMatch == false & i.getNumber() == 2)
					TwoMatch = true;
				else if (threeMatch == false & i.getNumber() == 3) threeMatch = true;
			}
		}
		return OneMatch && TwoMatch && threeMatch;
	}

	public boolean[] checkCardsOnBoradForMatch(Card[] onBoard) {
		boolean[] cardsMatch = new boolean[onBoard.length];
		for (int i = 0; i < onBoard.length; i++) {
			cardsMatch[i] = cardMatch(onBoard, onBoard[i]);
		}
		return cardsMatch;
	}

}

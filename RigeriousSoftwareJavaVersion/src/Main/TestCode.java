package Main;

public class TestCode {
	
	
	public void checkForMatch(Card[] cards) {
		int count = 0;
		// check if match 55 2 red ovals solid
		for (Card i : cards) {
			for (Card j : cards) {
				if (i != j) {
					if (i.getColour() == null)
						break;

					if (i.getNumber() == j.getNumber()) {
						if (i.getColour().equalsIgnoreCase(j.getColour())) {
							if (i.getSymbol().equalsIgnoreCase(j.getSymbol())) {
								if (i.getShading().equalsIgnoreCase(j.getShading())) {
									System.out.println("MATCH: " + count);
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public void checkTwoListForMatch(Card[] cards, Card[] cardsTwo) {
		int count = 0;
		// check if match 55 2 red ovals solid
		for (Card i : cards) {
			for (Card j : cardsTwo) {
				if (i != j) {
					if (i.getColour() == null)
						break;

					if (i.getNumber() == j.getNumber()) {
						if (i.getColour().equalsIgnoreCase(j.getColour())) {
							if (i.getSymbol().equalsIgnoreCase(j.getSymbol())) {
								if (i.getShading().equalsIgnoreCase(j.getShading())) {
									System.out.println("MATCH: " + count);
								}
							}
						}
					}
				}
			}
		}
	}

}


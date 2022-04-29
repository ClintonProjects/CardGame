package Card;

public class GenerateCards {

	private Card[] cards;
	private String[] colour = { "red", "green", "blue" };
	private String[] symbol = { "ovals", "squiggles", "diamonds" };
	private String[] shading = { "solid", "open", "striped" };

	public GenerateCards(Card[] cards) {
		this.cards= cards;
	}
	
	// This method generates all the cards.
	public Card[] spawnCards() {
		cards = new Card[81];
		int counter = 0;
		// This code sets all items in table.
		// creates a card, then sets every card to a number, so first 27 number will be
		// 1, 2 till 54
		for (int i = 0; i < cards.length; i++) {
			cards[i] = new Card();
			cards[i].setNumber((counter % 3) + 1);
			if (i == 26 || i == 53) counter++;
		}
		// set colours in order of red, green blue
		for (int i = 0; i < cards.length; i += 1)
			cards[i].setColour((colour[(i % 3)]));
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

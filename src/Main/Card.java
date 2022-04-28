package Main;

public class Card {
	
//	enum COLOUR {
//		Red, Green, Blue
//	}
//	
//	enum SYMBOL {
//		OVALS, SQUIGGLES, DIAMONDS
//	}
//	
//	enum SHADING {
//		 Solid, Open, Striped
//	}
	
	private String colour;
	private String symbol;
	private int number;
	private String shading;
	
	public Card() {}

	public Card(String colour, String symbol, int number, String shading) {
		this.colour = colour;
		this.symbol = symbol;
		this.number = number;
		this.shading = shading;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getShading() {
		return shading;
	}

	public void setShading(String shading) {
		this.shading = shading;
	}
	
}
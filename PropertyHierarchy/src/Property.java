
public abstract class Property {
	
	private int listPrice;
	
	public Property(int listPrice) throws IllegalProperty {
		setListPrice(listPrice);
	}
	
	private void setListPrice(int price) throws IllegalProperty {
		if (price > 0)
			listPrice = price;
		else
			throw new IllegalProperty("Illegal list price: " + price);
	}
	
	public int getListPrice() {
		return listPrice;
	}
	
	public abstract String getPricePerArea();
	
	@Override
	public String toString() {
		return "asking price $" + listPrice + ",000.";
	}
}

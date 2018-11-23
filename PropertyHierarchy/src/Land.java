
public abstract class Land extends Property {
	
	private int hectares;
	
	public Land(int listPrice, int hectares) throws IllegalProperty {
		super(listPrice);
		setHectares(hectares);
	}
	
	private void setHectares(int hectares) throws IllegalProperty {
		if (hectares > 0)
			this.hectares = hectares;
		else
			throw new IllegalProperty("Illegal hectares: " + hectares);
	}
	
	public String getPricePerArea() {
		return "Price is $" + (super.getListPrice() * 1000) / hectares + " per hectare.";
	}
	
	@Override
	public String toString() {
		return hectares + " hectares, " + super.toString();
	}
}

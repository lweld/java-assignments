
public abstract class Developed extends Property {
	
	private int coverage;
	
	public Developed(int listPrice, int coverage) throws IllegalProperty {
		super(listPrice);
		setCoverage(coverage);
	}
	
	private void setCoverage(int coverage) throws IllegalProperty {
		if (coverage > 0)
			this.coverage = coverage;
		else
			throw new IllegalProperty("Illegal coverage supplied: " + coverage);
	}
	
	public String getPricePerBuildingArea() {
		int areaPrice = (getListPrice() * 1000) / coverage;
		return "Price is $" + areaPrice + " per square metre of building.";
	}
	
	@Override
	public String toString() {
		return coverage + " square metre building, " + super.toString();
	}
}

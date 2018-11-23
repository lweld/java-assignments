
public abstract class Residental extends Developed {
	
	private int numBedrooms;
	
	public Residental(int listPrice, int coverage, int numBdrm) throws IllegalProperty {
		super(listPrice, coverage);
		setNumBdrm(numBdrm);
	}
	
	private void setNumBdrm(int numBdrm) throws IllegalProperty {
		if (numBdrm > 0)
			numBedrooms = numBdrm;
		else
			throw new IllegalProperty("Illegal number of bedrooms supplied: " + numBdrm);
	}
	
	@Override
	public String toString() {
		return numBedrooms + " bedrooms " + super.toString();
	}
}

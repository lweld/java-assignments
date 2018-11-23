
public class Cottage extends Detached {
	
	private int lakeFrontage;
	
	public Cottage(int listPrice, int coverage, int numBdrm, int width, int depth, int lakeFront) throws IllegalProperty {
		super(listPrice, coverage, numBdrm, width, depth);
		setLakeFront(lakeFront);
	}
	
	private void setLakeFront(int lakeFront) throws IllegalProperty {
		if (lakeFront > 0)
			lakeFrontage = lakeFront;
		else
			throw new IllegalProperty("Illegal lake frontage supplied: " + lakeFront);
	}
	
	public String toString() {
		return "Cottage with " + super.toString();
	}
}

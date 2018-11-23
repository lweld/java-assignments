
public abstract class Detached extends Residental {
	
	private int width;
	private int depth;
	
	public Detached(int listPrice, int coverage, int numBdrm, int width, int depth) throws IllegalProperty {
		super(listPrice, coverage, numBdrm);
		setWidth(width);
		setDepth(depth);
	}
	
	private void setWidth(int width) throws IllegalProperty {
		if (width > 0)
			this.width = width;
		else
			throw new IllegalProperty("Illegal width supplied: " + width);
	}
	
	private void setDepth(int depth) throws IllegalProperty {
		if (depth > 0)
			this.depth = depth;
		else
			throw new IllegalProperty("Illegal depth supplied: " + depth);
	}
	
	public String getPricePerArea() {
		int areaPrice = (getListPrice() * 1000) / (width * depth);
		return "Price is $" + areaPrice + " per square metre of residential lot.";
	}
	
	@Override
	public String toString() {
		return width + "m width by " + depth + "m deep lot, " + super.toString();
	}
}


public class Condo extends Residental {
	
	public Condo(int listPrice, int coverage, int numBdrm) throws IllegalProperty {
		super(listPrice, coverage, numBdrm);
	}
	
	public String getPricePerArea() {
		return getPricePerBuildingArea();
	}
	
	public String toString() {
		return "Condominium, " + super.toString();
	}
}

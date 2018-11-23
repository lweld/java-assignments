
public class FarmLand extends Land {
	
	private String crop;
	
	public FarmLand(int listPrice, int hectares, String crop) throws IllegalProperty {
		super(listPrice, hectares);
		setCrop(crop);
	}
	
	private void setCrop(String crop) throws IllegalProperty {
		if (!(crop.equals(null)))
			this.crop = crop;
		else
			throw new IllegalProperty("Illegal crop supplied: " + crop);
		
	}
	
	@Override
	public String toString() {
		return "Farmed land, " + crop + " crop, " + super.toString();
	}
}


public class VacantLand extends Land {
		
	public VacantLand(int listPrice, int hectares) throws IllegalProperty {
		super(listPrice, hectares);
	}
	
	@Override
	public String toString() {
		return "Vacant land, " + super.toString();
	}
}

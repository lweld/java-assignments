
public class Office extends Business {
	
	public Office(int listPrice, int coverage, int width, int depth) throws IllegalProperty {
		super(listPrice, coverage, width, depth);
	}
	
	@Override
	public String toString() {
		return "Office space, " + super.toString();
	}
}


public class Industrial extends Business {
	
	public Industrial(int listPrice, int coverage, int width, int depth) throws IllegalProperty {
		super(listPrice, coverage, width, depth);
	}
	
	@Override
	public String toString() {
		return "Industrial space, " + super.toString();
	}
}


public class Home extends Detached {
	
	public Home(int listPrice, int coverage, int numBdrm, int width, int depth) throws IllegalProperty {
		super(listPrice, coverage, numBdrm, width, depth);
	}
	
	@Override
	public String toString() {
		return "Detached home, " + super.toString();
	}
}

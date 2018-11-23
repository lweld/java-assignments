
public class IllegalProperty extends Exception {
	
	private static final long serialVersionUID = -1160559441456626376L;

	public IllegalProperty() {
		super("Illegal parameter value supplied.");
	}
	
	public IllegalProperty(String message) {
		super(message);
	}
}

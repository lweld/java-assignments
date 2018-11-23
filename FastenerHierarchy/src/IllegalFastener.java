/*
 * An Exception thrown by all objects in the hierarchy if parameters are not legal.
 * 
 * Author: Liam Weld
 */

public class IllegalFastener extends Exception {

	private static final long serialVersionUID = -6710281488489204911L;
	
	// Supplies a default message.
	public IllegalFastener() {
		super("Illegal parameter value supplied.");
	}

	// Passes along the message supplied to the exception.
	public IllegalFastener(String message) {
		super(message);
	}
}
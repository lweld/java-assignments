/**
 * An Exception thrown by the Pizza and LineItem object if parameters are not legal.
 * 
 * <p>The following is true of the Pizza object:
 * <ul>
 *    <li>The size of the pizza must be either "small", "medium", or "large".</li>
 *    <li>The cheese must be either "single", "double", or "triple".</li>
 *    <li>The toppings pineapple, green pepper, and ham must be either "single" or "none".</li>
 *    <li>The default condition is a small single cheese pizza with ham.</li>
 * </ul></p>
 * 
 * <p>The following is true of the LineItem object:
 * <ul>
 *    <li>The number of pizzas must be between 1 and 100 inclusive. The default condition is 1.</li>
 * </ul></p>
 * 
 * @author Liam Weld
 * @version 1.0
 */

public class IllegalPizza extends Exception {

	private static final long serialVersionUID = -5935590159508055457L;
	
	/**
	 * Supplies a default message.
	 */
	public IllegalPizza() {
		super("Illegal parameter value supplied to Pizza object.");
	}

	/**
	 * Passes along the message supplied to the exception.
	 * @param message A more specific message.
	 */
	public IllegalPizza(String message) {
		super(message);
	}
}

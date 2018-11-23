import java.io.Serializable;

/**
 * This class creates a Pizza object that describes a single type of pizza.
 * 
 * <p>A pizza can be created with the following toppings: cheese, pineapple, 
 * green pepper, and ham. The class can be either instantiated with all toppings 
 * and the pizza's size specified or without any arguments, which by default
 * will create a small pizza with single cheese and ham.</p>
 * 
 * <p>The constructor calls a series of mutators that sets the pizza's size,
 * toppings, and cost. The user is able to get the cost of an individual pizza.</p>
 * 
 * <p>The following methods are overriden from the base class:
 * <li><code>toString()<code> will now print the contents of the the Pizza object</li>
 * <li><code>equals()<code> will now compare the contents of two objects</li>
 * <li><code>clone()<code></li></p>
 * 
 * @author Liam Weld
 * @version 1.0
 */

public class Pizza implements Serializable {

	private static final long serialVersionUID = 2253173997905103550L;
	private String pizzaSize;
	private String cheese;
	private String pineapple;
	private String greenPepper;
	private String ham;
	private float cost;
		
	/**
	 * Full parameter constructor.
	 * @param size The size of the pizza.
	 * @param cheese The amount of cheese on the pizza.
	 * @param pineapple If pineapple is on the pizza or not.
	 * @param gPepper If green pepper is on the pizza or not.
	 * @param ham If ham is on the pizza or not.
	 * @throws IllegalPizza If any of the parameters are not legal.
	 */
	// Full parameter constructor invokes mutators.
	public Pizza(String size, String cheese, String pineapple, String gPepper, String ham) throws IllegalPizza {
		catchNullPtr(size, cheese, pineapple, gPepper, ham);
		setSize(size);
		setCheese(cheese);
		setHam(ham);
		setPineapple(pineapple);
		setGreenPepper(gPepper);
		setCost();
	}
	
	/**
	 * Default constructor if no parameters are supplied.
	 * It will create a default small pizza with single cheese and ham.
	 * @throws IllegalPizza If any of the parameters being passed to the full parameter constructor are not legal.
	 */
	// No parameter constructor invokes full parameter constructor with default parameters.
	public Pizza() throws IllegalPizza {
		this("small", "single", "none", "none", "single");
	}
	
	// Catches if the constructor is supplied a null parameter.
	private void catchNullPtr(String size, String cheese, String pineapple, String gPepper, String ham) throws IllegalPizza {
		if (size == null || cheese == null || pineapple == null || gPepper == null || ham == null)
			throw new IllegalPizza("Illegal to enter a null.");
	}

	// Mutator called by the constructor to set the size of the pizza.
	private void setSize(String size) throws IllegalPizza {
		if (size.equalsIgnoreCase("small") || size.equalsIgnoreCase("medium") || size.equalsIgnoreCase("large"))
			pizzaSize = size;
		else
			throw new IllegalPizza("Illegal pizza size: " + size);
	}

	// Mutator called by the constructor to set the amount of cheese on the pizza.
	private void setCheese(String cheese) throws IllegalPizza {
		if (cheese.equalsIgnoreCase("single") || cheese.equalsIgnoreCase("double") || cheese.equalsIgnoreCase("triple"))
			this.cheese = cheese;
		else
			throw new IllegalPizza("Illegal pizza topping: " + cheese);
	}

	// Mutator called by the constructor to determine if ham will be included on the pizza.
	private void setHam(String ham) throws IllegalPizza {
		if (ham.equalsIgnoreCase("single") || ham.equalsIgnoreCase("none"))
			this.ham = ham;
		else
			throw new IllegalPizza("Illegal pizza topping: " + ham);
	}

	// Mutator called by the constructor to determine if pineapple will be included on the pizza.
	// Pineapple will not be included if ham is not also included.
	private void setPineapple(String pineapple) throws IllegalPizza {
		if (pineapple.equalsIgnoreCase("none"))
			this.pineapple = pineapple;
		else if (ham.equalsIgnoreCase("none"))
			throw new IllegalPizza("Illegal pizza topping: " + pineapple);
		else if (pineapple.equalsIgnoreCase("single"))
			this.pineapple = pineapple;
		else
			throw new IllegalPizza("Illegal pizza topping: " + pineapple);
	}

	// Mutator called by the constructor to determine if green pepper will be included on the pizza.
	// Green pepper will not be included if ham is not also included.
	private void setGreenPepper(String gPepper) throws IllegalPizza {
		if (gPepper.equalsIgnoreCase("none"))
			greenPepper = gPepper;
		else if (ham.equalsIgnoreCase("none"))
			throw new IllegalPizza("Illegal pizza topping: " + gPepper);
		else if (gPepper.equalsIgnoreCase("single"))
			greenPepper = gPepper;
		else
			throw new IllegalPizza("Illegal pizza topping: " + gPepper);
	}

	// After all the attributes of the pizza have been set, setCost() sets the cost of the pizza.
	private void setCost() {
		if (pizzaSize.equalsIgnoreCase("small"))
			cost += 7.00;
		else if (pizzaSize.equalsIgnoreCase("medium"))
			cost += 9.00;
		else
			cost += 11.00;
		if (cheese.equalsIgnoreCase("double"))
			cost += 1.50;
		else if (cheese.equalsIgnoreCase("triple"))
			cost += 3.00;
		if (ham.equalsIgnoreCase("single"))
			cost += 1.50;
		if (pineapple.equalsIgnoreCase("single"))
			cost += 1.50;
		if (greenPepper.equalsIgnoreCase("single"))
			cost += 1.50;
	}
	
	// Returns the cost of the individual Pizza object as a float.
	public float getCost() {
		return cost;
	}
	
	/**
     * A String representation of the current object.
     * @return A String representation of the contents of the object containing the values of
     * all the attributes.
     */
    // Overrides the toString method of the Object class.
	@Override
	public String toString() {
		String costResult = String.format("%.2f", cost);
		String s = pizzaSize.toLowerCase() + " pizza, " + cheese.toLowerCase() + " cheese";
		if (pineapple.equalsIgnoreCase("single"))
			s += ", pineapple";
		if (greenPepper.equalsIgnoreCase("single"))
			s += ", green pepper";
		if (ham.equalsIgnoreCase("single"))
			s += ", ham";
		s += ". Cost: $" + costResult + " each.";
		return s;
	}
	
	/**
     * Tests two Pizza objects for equality.
     * @return <code>true</code> if all the attributes of both objects are exactly equal, 
     * <code>false</code> otherwise.
     * @param otherObject The other Pizza object.
     */
    // Overrides the equals method of the Object class.
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject instanceof Pizza) {
			Pizza otherObj = (Pizza)otherObject;
			return pizzaSize == otherObj.pizzaSize && cheese == otherObj.cheese && ham == otherObj.ham
					&& pineapple == otherObj.pineapple && greenPepper == otherObj.greenPepper;
		}
		return false;
	}
	
	/**
     * Returns a copy of the of the current Pizza object.
     * @return A copy of the current object.
     */
    // Overrides the clone method of the Object class.
    @Override
    public Pizza clone() {
    	Pizza pizzaCopy = null;
    	try {
    		pizzaCopy = new Pizza(pizzaSize, cheese, pineapple, greenPepper, ham);
    	} catch (IllegalPizza e) {
    		return null;
    	}
    	return pizzaCopy;
    }
}

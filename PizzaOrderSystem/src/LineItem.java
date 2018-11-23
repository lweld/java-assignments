import java.io.Serializable;

/**
 * This class creates an object that points to a Pizza object and the number of pizzas
 * of that Pizza object were ordered.
 * 
 * <p>The constructor accepts the number of pizzas being ordered and the Pizza object
 * that these orders are being applied to. If the number of pizzas are not supplied, 
 * another constructor will be called, which accepts only the Pizza object and sets the
 * number of pizzas ordered to one.</p>
 * 
 * <p>The constructor assigns the Pizza object and calls a mutator to assign the number of
 * pizzas ordered. The user is able to modify the number of pizzas ordered, along with getting
 * the type of pizza, number of orders for that type of pizza, and total cost for all the pizzas.</p>
 * 
 * <p>The following methods are overriden from the base class:
 * <li><code>toString()<code> will now print the contents of the the Pizza object and the number of pizzas that were ordered</li>
 * <li><code>compareTo()<code> will now compare Pizza objects on only the basis of pizza cost.</li></p>
 * 
 * @author Liam Weld
 * @version 1.0
 */

public class LineItem implements Comparable<LineItem>, Serializable {

	private static final long serialVersionUID = 5538396070255971850L;
	private Pizza pizzaType;
	public int totalPizzas;
	
	/**
	 * Full parameter constructor.
	 * @param numPizzas The number of pizzas that are being ordered.
	 * @param pizzaType The Pizza object that is being referenced.
	 * @throws IllegalPizza If <code>numPizzas<code> is not legal.
	 */
	public LineItem(int numPizzas, Pizza pizzaType) throws IllegalPizza {
		catchNullPtr(pizzaType);
		this.pizzaType = pizzaType;
		setNumber(numPizzas);
	}
	
	/**
	 * One parameter constructor. The number of pizzas being ordered is not required.
	 * @param pizzaType The Pizza object that is being referenced.
	 * @throws IllegalPizza If <code>numPizzas<code> is not legal.
	 */
	public LineItem(Pizza pizzaType) throws IllegalPizza {
		this(1, pizzaType);
	}
	
	// Catches if the constructor is supplied a null parameter.
	private void catchNullPtr(Pizza pizzaType) throws IllegalPizza {
		if(pizzaType == null)
			throw new IllegalPizza("Illegal to enter a null.");
	}
	
	/**
	 * Sets the number of pizzas of this Pizza object that are being ordered.
	 * 
	 * @param numPizzas The number of pizzas that are being ordered.
	 * @throws IllegalPizza If <code>numPizzas<code> is less than 1 or greater than 100.
	 */
	public void setNumber(int numPizzas) throws IllegalPizza {
		if (numPizzas < 1 || numPizzas > 100)
			throw new IllegalPizza("Illegal number of pizzas: " + numPizzas);
		else
			totalPizzas = numPizzas;
	}
	
	/**
	 * Returns the type of pizza being pointed to.
	 * @return The Pizza object.
	 */
	public Pizza getPizza() {
		return pizzaType;
	}
	
	/**
	 * Returns the number of pizzas of this Pizza object that were ordered.
	 * @return The number of pizzas ordered as an int.
	 */
	public int getNumber() {
		return totalPizzas;
	}
	
	/**
	 * Returns the total cost of all the pizzas of this Pizza object that were ordered.
	 * @return The total cost of the orders as a double.
	 */
	public double getCost() {
		float pizzaCost = pizzaType.getCost();
		if (totalPizzas >= 10 && totalPizzas <= 20)
			return (pizzaCost * totalPizzas) * 0.9;
		else if (totalPizzas > 20)
			return (pizzaCost * totalPizzas) * 0.85;
		else
			return (pizzaCost * totalPizzas);
	}
	
	/**
    * A String representation of the current object.
    * @return A String representation of the contents of the Pizza object containing the
    * values of all the attributes and the number of this type of Pizza that were ordered.
    */
   // Overrides the toString method of the Object class.
	@Override
	public String toString() {
		String s = pizzaType.toString();
		if (totalPizzas < 10)
			s = " " + totalPizzas + " " + s;
		else
			s = totalPizzas + " " + s;
		return s;
	}

	/**
     * Compares Pizza objects on only the basis of pizza cost.
     * @param otherLine The other Pizza object.
     * @return A 1 if the supplied Pizza object has a greater cost, 0 if they have 
     * the same cost, and -1 if the current Pizza object has a greater cost.
     */
	public int compareTo(LineItem otherLine) {
		int diff = (int) (this.getCost() - otherLine.getCost());
		if (diff < 0)
			return 1;
		else if (diff > 0)
			return -1;
		else
			return 0;
	}
}

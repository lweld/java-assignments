/*
 * This concrete class is a child of the Fastener class and the Bolt class.
 * It assigns the attributes of a Carriage Bolt.
 * 
 * Author: Liam Weld
 */

import java.io.Serializable;

public class CarriageBolt extends Bolt implements Serializable {
	
	private static final long serialVersionUID = -6018613361762201344L;

	// Full constructor calls parent classes with values supplied at instantiation.
	public CarriageBolt(double length, String thread, String material, String finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(length, thread, material, finish, unitPrice, numPerUnit);
	}
	
	@Override
	public String toString() {
		return "Carriage Bolt " + super.toString();
	}
}

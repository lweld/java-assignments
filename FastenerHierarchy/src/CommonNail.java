/*
 * This concrete class is a child of the Fastener class and the Nail class.
 * It assigns the attributes of a Common Nail.
 * 
 * Author: Liam Weld
 */

import java.io.Serializable;

public class CommonNail extends Nail implements Serializable {
	
	private static final long serialVersionUID = 3334007595975573903L;

	// Full constructor calls parent classes with values supplied at instantiation.
	public CommonNail(String size, double length, double gauge, String finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(size, length, gauge, finish, unitPrice, numPerUnit);
	}

	@Override
	public String toString() {
		return "Common Nail, " + super.toString();
	}
}

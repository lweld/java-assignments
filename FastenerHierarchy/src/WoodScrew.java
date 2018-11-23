/*
 * This concrete class is a child of the Fastener class and the Screw class.
 * It assigns the attributes of a Wood Screw.
 * 
 * Author: Liam Weld
 */

import java.io.Serializable;

public class WoodScrew extends Screw implements Serializable {
	
	private static final long serialVersionUID = 1576324504089427685L;

	// Full constructor calls parent classes with values supplied at instantiation.
	public WoodScrew(double length, String thread, String material, String finish, String head, String drive, String point, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(length, thread, material, finish, head, drive, point, unitPrice, numPerUnit);
		checkPoint(point);
	}
	
	// Checks if the value of point, which was set in the Screws class, is legal.
	private void checkPoint(String point) throws IllegalFastener {
		if (!(point.equalsIgnoreCase("Double Cut") || point.equalsIgnoreCase("Sharp") || point.equalsIgnoreCase("Type 17")))
			throw new IllegalFastener("Illegal Screw Point: " + point);
	}
	
	@Override
	public String toString() {
		return "Wood Screw, " + super.toString();
	}
}

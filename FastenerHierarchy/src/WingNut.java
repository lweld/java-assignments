/*
 * This concrete class is a child of the Fastener class and the InnerThreaded class.
 * It assigns the attributes of a Wing Nut.
 * 
 * Author: Liam Weld
 */

import java.io.Serializable;

public class WingNut extends InnerThreaded implements Serializable {

	private static final long serialVersionUID = -288466255794945090L;

	// Full constructor calls parent classes with values supplied at instantiation.
	public WingNut(String thread, String material, String finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(thread, material, finish, unitPrice, numPerUnit);
	}
	
	@Override
	public String toString() {
		return "Wing Nut " + super.toString();
	}
}

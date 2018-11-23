/*
 * This abstract class is the parent of my Fastener hierarchy.
 * It assigns the common attributes of all Fasteners.
 * 
 * Author: Liam Weld
 */

import java.io.Serializable;

public abstract class Fastener implements Serializable {

	private static final long serialVersionUID = 2294117517899184354L;
	private String material;
	private String finish;
	private double unitPrice;
	private int numPerUnit;
	
	// Full parameter constructor called by child class.
	public Fastener(String material, String finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		setMaterial(material);
		this.finish = finish; // Illegal values are check in each child class.
		setUnitPrice(unitPrice);
		setNumPerUnit(numPerUnit);
	}
	
	// Sets the Fastener's material while checking for illegal values.
	private void setMaterial(String material) throws IllegalFastener {
		if (material.equalsIgnoreCase("Brass") || material.equalsIgnoreCase("Stainless Steel") || material.equalsIgnoreCase("Steel"))
			this.material = material;
		else
			throw new IllegalFastener("Illegal Fastener Material: " + material);
	}
	
	// Sets the Fastener's unit price while checking for illegal values.
	private void setUnitPrice(double unitPrice) throws IllegalFastener {
		if (unitPrice > 0)
			this.unitPrice = unitPrice;
		else
			throw new IllegalFastener("Illegal Fastener Unit Price: " + unitPrice);
	}
	
	// Sets the number of Fasteners that can be packaged together while checking for illegal values.
	private void setNumPerUnit(int numPerUnit) throws IllegalFastener {
		if (numPerUnit == 1)
			this.numPerUnit = numPerUnit;
		else if (numPerUnit > 1 && numPerUnit <= 10000 && numPerUnit % 5 == 0)
			this.numPerUnit = numPerUnit;
		else
			throw new IllegalFastener("Illegal Fastener Number per Unit: " + numPerUnit);
	}
	
	// Provides the total cost for a given order of Fasteners.
	public double getOrderCost(int orderSize) {
		return Math.ceil((double) orderSize / numPerUnit) * unitPrice;
	}
	
	@Override
	public String toString() {
		return material + ", with a " + finish + " finish. " + numPerUnit + " in a unit, $" + unitPrice + " per unit.";
	}
}

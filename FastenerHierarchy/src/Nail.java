/*
 * This abstract class is a child of the Fastener class.
 * It assigns the common attributes of all Nails.
 * 
 * Author: Liam Weld
 */

import java.io.Serializable;

public abstract class Nail extends Fastener implements Serializable {
	
	private static final long serialVersionUID = 8282900141234992575L;
	private String size;
	private double length;
	private double gauge;
	
	// Full parameter constructor called by child class.
	public Nail(String size, double length, double gauge, String finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		super("Steel", finish, unitPrice, numPerUnit);
		checkFinish(finish);
		setSize(size);
		setLength(length);
		setGauge(gauge);
	}
	
	// Checks if the value of finish, which was set in the Fastener class, is legal.
	private void checkFinish(String finish) throws IllegalFastener {
		if (!(finish.equalsIgnoreCase("Bright") || finish.equalsIgnoreCase("Hot Dipped Galvanized")))
			throw new IllegalFastener("Illegal Nail Finish: " + finish);
	}
	
	// Sets the size of a screw, while checking for illegal values.
	private void setSize(String size) throws IllegalFastener {
		if (size.equalsIgnoreCase("6D") || size.equalsIgnoreCase("8D") || size.equalsIgnoreCase("10D") || size.equalsIgnoreCase("12D") || size.equalsIgnoreCase("16D") || size.equalsIgnoreCase("60D"))
			this.size = size;
		else
			throw new IllegalFastener("Illegal Nail Size: " + size);
	}
	
	// Sets the length of a screw, while checking for illegal values.
	private void setLength(double length) throws IllegalFastener {
		if (length == 2 || length == 2.5 || length == 3 || length == 3.25 || length == 3.5 || length == 6)
			this.length = length;
		else
			throw new IllegalFastener("Illegal Screw Length: " + length);
	}
	
	// Sets the gauge of a screw, while checking for illegal values.
	private void setGauge(double gauge) throws IllegalFastener {
		if (gauge == 2 || gauge == 8 || gauge == 9 || gauge == 10.25 || gauge == 11.5)
			this.gauge = gauge;
		else
			throw new IllegalFastener("Illegal Screw Gauge: " + gauge);
	}
	
	@Override
	public String toString() {
		return size + " size, " + length + "\" long, " + gauge + " gauge, " + super.toString();
	}
}

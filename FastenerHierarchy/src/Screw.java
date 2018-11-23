/*
 * This abstract class is a child of the Fastener class.
 * It assigns the common attributes of all Screws.
 * 
 * Author: Liam Weld
 */

import java.io.Serializable;

public abstract class Screw extends Fastener implements Serializable {

	private static final long serialVersionUID = 7902791134081325874L;
	private double length;
	private String thread;
	private String head;
	private String drive;
	private String point;
	
	// Full parameter constructor called by child class.
	public Screw(double length, String thread, String material, String finish, String head, String drive, String point, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(material, finish, unitPrice, numPerUnit);
		checkFinish(material, finish);
		setLength(length);
		setThread(thread);
		setHead(head);
		setDrive(drive);
		this.point = point;
	}
	
	// Checks if the value of finish, which was set in the Fastener class, is legal.
	private void checkFinish(String material, String finish) throws IllegalFastener {
		if (material.equalsIgnoreCase("Steel")) {
			if (!(finish.equalsIgnoreCase("Chrome") || finish.equalsIgnoreCase("Hot Dipped Galvanized") || finish.equalsIgnoreCase("Plain") || finish.equalsIgnoreCase("Yellow Zinc") 
					|| finish.equalsIgnoreCase("Zinc") || finish.equalsIgnoreCase("Black Phosphate") || finish.equalsIgnoreCase("ACQ 1000 Hour") || finish.equalsIgnoreCase("Lubricated")))
				throw new IllegalFastener("Illegal Screw Finish: " + finish);
		}
		else {
			if (!(finish.equalsIgnoreCase("Plain")))
				throw new IllegalFastener("Illegal Screw Finish: " + finish);
		}
	}
	
	// Sets the length of a screw, while checking for illegal values.
	private void setLength(double length) throws IllegalFastener {
		if (length >= 0.5 && length < 6 && length % 0.25 == 0)
			this.length = length;
		else if (length >= 6 && length < 11 && length % 0.5 == 0)
			this.length = length;
		else if (length >= 11 && length <= 20 && length % 1 == 0)
			this.length = length;
		else
			throw new IllegalFastener("Illegal Screw Length: " + length);
	}
	
	// Sets the thread of a screw, while checking for illegal values.
	private void setThread(String thread) throws IllegalFastener {
		if (thread.equals("#8-13") || thread.equals("#8-15") || thread.equals("8-32") || thread.equals("#10-13") || thread.equals("#10-24") || thread.equals("#10-32") || thread.equals("1/4-20") 
				|| thread.equals("5/16-18") || thread.equals("3/8-16") || thread.equals("7/16-14") || thread.equals("1/2-13") || thread.equals("5/8-11") || thread.equals("3/4-10"))
			this.thread = thread;
		else
			throw new IllegalFastener("Illegal Screw Thread: " + thread);
	}
	
	// Sets the head of a screw, while checking for illegal values.
	private void setHead(String head) throws IllegalFastener {
		if (head.equalsIgnoreCase("Bugle") || head.equalsIgnoreCase("Flat") || head.equalsIgnoreCase("Oval") || head.equalsIgnoreCase("Pan") || head.equalsIgnoreCase("Round"))
			this.head = head;
		else
			throw new IllegalFastener("Illegal Screw Head: " + head);
	}
	
	// Sets the drive of a screw, while checking for illegal values.
	private void setDrive(String drive) throws IllegalFastener {
		if (drive.equalsIgnoreCase("6-Lobe") || drive.equalsIgnoreCase("Philips") || drive.equalsIgnoreCase("Slotted") || drive.equalsIgnoreCase("Square"))
			this.drive = drive;
		else
			throw new IllegalFastener("Illegal Screw Drive: " + drive);
	}
	
	@Override
	public String toString() {
		return point + " point, " + head + " head, " + drive + " drive, " + length + "\" long, " + thread + " thread, " + super.toString();
	}
}

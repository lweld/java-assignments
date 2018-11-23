/*
 * This abstract class is a child of the Fastener class.
 * It assigns the attributes common to all Fasteners that have internal threads (e.g., nuts).
 * 
 * Author: Liam Weld
 */

import java.io.Serializable;

public class InnerThreaded extends Fastener implements Serializable {

	private static final long serialVersionUID = -3212979652883139721L;
	private String thread;
	
	// Full parameter constructor called by child class.
	public InnerThreaded(String thread, String material, String finish, double unitPrice, int numPerUnit) throws IllegalFastener {
		super(material, finish, unitPrice, numPerUnit);
		checkFinish(material, finish);
		setThread(thread);
	}
	
	// Checks if the value of finish, which was set in the Fastener class, is legal.
	private void checkFinish(String material, String finish) throws IllegalFastener {
		if (material.equalsIgnoreCase("Steel")) {
			if (!(finish.equalsIgnoreCase("Chrome") || finish.equalsIgnoreCase("Hot Dipped Galvanized") || finish.equalsIgnoreCase("Plain") || finish.equalsIgnoreCase("Yellow Zinc") || finish.equalsIgnoreCase("Zinc")))
				throw new IllegalFastener("Illegal Bolt Finish: " + finish);
		}
		else {
			if (!(finish.equalsIgnoreCase("Plain")))
				throw new IllegalFastener("Illegal Bolt Finish: " + finish);
		}
	}
	
	// Sets the thread of a bolt, while checking for illegal values.
	private void setThread(String thread) throws IllegalFastener {
		if (thread.equals("#8-13") || thread.equals("#8-15") || thread.equals("8-32") || thread.equals("#10-13") || thread.equals("#10-24") || thread.equals("#10-32") || thread.equals("1/4-20") 
				|| thread.equals("5/16-18") || thread.equals("3/8-16") || thread.equals("7/16-14") || thread.equals("1/2-13") || thread.equals("5/8-11") || thread.equals("3/4-10"))
			this.thread = thread;
		else
			throw new IllegalFastener("Illegal Bolt Thread: " + thread);
	}
	
	@Override
	public String toString() {
		return thread + " thread, " + super.toString();
	}
}

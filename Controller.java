package FlightSimulator;

import java.util.Queue;
import javax.swing.Timer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;

/**
 * Raymond So <p>
 * 12/10/24 <p>
 * Backend | 2 in, 1 out
 */

public class Controller {
	public Queue<String> inbound = new LinkedList<String>();
	public Queue<String> outbound = new LinkedList<String>();
	public boolean handling = false; // debounce
	public boolean started = false; // handle simulation init

	/**
	 * Adds a flight number to inbound flights
	 */
	public void addIn(String plane) {
		inbound.add(plane);
		//System.out.println("in");
	}

	/**
	 * Adds a flight number to outbound flights
	 */
	public void addOut(String plane) {
		outbound.add(plane);
		//System.out.println("out");
	}
	/**
	 * Prints string representation of all inbound & outbound planes
	 */
	@Override
	public String toString() {
		return "Inbound: " + Arrays.deepToString(inbound.toArray()) + " Outbound: " + Arrays.deepToString(outbound.toArray());
	}

	/**
	 * Removes the specified flight from queue
	 */
	public void updateQueue(Queue q) {
		q.remove(); // removes flight number
		PlaneUIApp.app.refresh(); // updates display and status and animation
	}
}

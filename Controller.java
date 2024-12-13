package FlightSimulator;

import java.util.Queue;
import javax.swing.Timer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;

/*
 * Raymond So
 * 12/10/24
 * Backend
 * 2 in, 1 out
 */

public class Controller {
	public Queue<String> inbound = new LinkedList<String>();
	public Queue<String> outbound = new LinkedList<String>();
	public boolean handling = false; // debounce
	
	public void addIn(String plane) {
		inbound.add(plane);
		System.out.println("in");
	}
	
	public void removeIn() {
		inbound.remove();
	}

	public void addOut(String plane) {
		outbound.add(plane);
		System.out.println("out");
	}
	
	public void removeOut() {
		outbound.remove();
	}
	
	@Override
	public String toString() {
		return "Inbound: " + Arrays.deepToString(inbound.toArray()) + " Outbound: " + Arrays.deepToString(outbound.toArray());
	}
	
	public void updateQueue(Queue q) {
		
	}
}

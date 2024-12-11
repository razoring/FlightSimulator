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
	Queue<String> inbound = new LinkedList<String>();
	Queue<String> outbound = new LinkedList<String>();
	
	public void addIn(String plane) {
		inbound.add(plane);
	}
	
	public void removeIn() {
		inbound.remove();
	}

	public void addOut(String plane) {
		outbound.add(plane);
	}
	
	public void removeOut() {
		outbound.remove();
	}
	
	@Override
	public String toString() {
		return "Inbound: " + Arrays.deepToString(inbound.toArray()) + "Outbound: " + Arrays.deepToString(outbound.toArray());
	}
	
	public void updateQueue(Queue q) {
		
	}
}

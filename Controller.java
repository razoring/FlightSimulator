package FlightSimulator;

import java.util.Queue;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Raymond So <p>
 * 12/10/24 <p>
 * Backend | 2 in, 1 out
 */

public class Controller {
	public Queue<String> inbound = new LinkedList<String>();
	public Queue<String> outbound = new LinkedList<String>();
	public boolean handling = false; // debounce, ensure only one flight is processed at a time, preventing race conditions
	public boolean started = false; // handle simulation init
	public String timerRelay = ""; // relay instructions from timer listener to satisfy requirements for updateQueue()

	/**
	 * Helper method: Waits the specified number of seconds
	 * @param i -> Number of seconds to delay by
	 */
	private static void wait(int i) {
		try {
			ActionListener ticktock = new ActionListener() {
				public void actionPerformed(ActionEvent evnt) {
					
				}
			};
			Timer timer = new Timer(i*100, ticktock); // timer is ticking
			timer.setRepeats(false); // by using this, we are asking to off timer once
			timer.start();
			Thread.sleep(i*1000);
		} catch (InterruptedException expn) {
			
		}
	}
	
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
	public void updateQueue(Queue<String> q) {
	    if (q != null && !q.isEmpty()) {
	        String flight = q.peek();
	        if (flight != null) {
	        	if (timerRelay.equals("Departure")) { // run mid frame of departure anim ONLY
		            PlaneUIApp.app.initAnim(timerRelay);
	        	}

	            // countdown based on animation type
	            int countdown = timerRelay.equals("Departure") ? 2 : 4;
	            for (int x = 0; x < countdown; x++) {
	                String statusMessage = timerRelay.equals("Departure")?flight+" takes off in.. "+(countdown - x)+"s":flight+" enters in.. "+(countdown - x)+"s";
	                PlaneUIApp.app.status.setText(statusMessage);
	                wait(1); // simulate the countdown
	            }

	            // finalize the animation
	            String finalStatus = timerRelay.equals("Departure")?flight+" took off!":flight+" landed!";
	            PlaneUIApp.app.status.setText(finalStatus);

	            q.remove(); // removes flight number
	            PlaneUIApp.app.refresh(); // updates display and status
	            PlaneUIApp.app.finishAnim(timerRelay);

	            wait(1); // wait briefly after animation
	        }
	    }
	}

}

package FlightSimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Jiawei Chen, Raymond So <p>
 * 12/17/2024 <p>
 * Handles arrival/departure operations using timer
 */
public class TimerListener {
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
	 * Handles the inbound/outbound cycle. Follows the pattern of two depart, then one arrives. 
	 */
	public static void sharedMain() {
		System.out.print(""); // TODO: DO NOT REMOVE!!! IS REQUIRED FOR CODE TO RUN!!! IF REMOVED, SCRIPT WILL NEVER RUN
	    if (PlaneUIApp.ctrl.started) {
	        if (!PlaneUIApp.ctrl.handling) {
	            PlaneUIApp.ctrl.handling = true;
	            System.out.println(PlaneUIApp.ctrl.inbound.toString());
	            System.out.println(PlaneUIApp.ctrl.inbound.peek());

	            // if both queues are empty
	            if (!PlaneUIApp.ctrl.inbound.isEmpty() && !PlaneUIApp.ctrl.outbound.isEmpty()) {
	                PlaneUIApp.app.status.setText("Simulation is waiting for more planes");
	            }

	            // handle departures
	            for (int i = 0; i < 2; i++) {
	                if (PlaneUIApp.ctrl.outbound.peek() != null) { // check if planes ae outbound
	                	PlaneUIApp.ctrl.updateQueue(PlaneUIApp.ctrl.outbound);
		                PlaneUIApp.ctrl.timerRelay = "Departure";
	                }
	            }

	            // handle arrivals
	            if (PlaneUIApp.ctrl.inbound.peek() != null) { // check if planes are inbound
	                PlaneUIApp.ctrl.updateQueue(PlaneUIApp.ctrl.inbound);
	                PlaneUIApp.ctrl.timerRelay = "Arrival";
	            }

	            PlaneUIApp.ctrl.handling = false;
	        }
	    }
	}
	
	public static void main(String[] args) {
		System.out.println("Running on duo threads");
		while (true) {
			sharedMain();
		}
	}
	
	/**
	 * Continues operation while the timer is running
	 */
	public void runTimer() {
		while (true) {
			sharedMain();
		}
	}
}

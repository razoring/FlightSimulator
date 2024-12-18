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
	    System.out.print(""); // Required to prevent a specific bug
	    if (PlaneUIApp.ctrl.started) {
	        if (!PlaneUIApp.ctrl.handling) {
	            PlaneUIApp.ctrl.handling = true;
	            
	            // Debugging: Print queue state
	            System.out.println("Inbound Queue: " + PlaneUIApp.ctrl.inbound);
	            System.out.println("Outbound Queue: " + PlaneUIApp.ctrl.outbound);

	            // If both queues are empty
	            if (PlaneUIApp.ctrl.inbound.isEmpty() && PlaneUIApp.ctrl.outbound.isEmpty()) {
	                PlaneUIApp.app.status.setText("Simulation is waiting for more planes");
	            }

	            // Handle departures
	            for (int i = 0; i < 2; i++) {
	                if (!PlaneUIApp.ctrl.outbound.isEmpty()) { // Check if planes are outbound
	                    String departingFlight = PlaneUIApp.ctrl.outbound.peek();
	                    PlaneUIApp.ctrl.timerRelay = departingFlight + " departed";
	                    PlaneUIApp.ctrl.updateQueue(PlaneUIApp.ctrl.outbound);
	                    PlaneUIApp.app.status.setText(departingFlight + " has taken off.");
	                    wait(1); // Delay between departures
	                }
	            }

	            // Handle arrivals
	            if (!PlaneUIApp.ctrl.inbound.isEmpty()) { // Check if planes are inbound
	                String arrivingFlight = PlaneUIApp.ctrl.inbound.peek();
	                PlaneUIApp.ctrl.timerRelay = arrivingFlight + " arrived";
	                PlaneUIApp.ctrl.updateQueue(PlaneUIApp.ctrl.inbound);
	                PlaneUIApp.app.status.setText(arrivingFlight + " has landed.");
	                wait(1); // Delay before next cycle
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

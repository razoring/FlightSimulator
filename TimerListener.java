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
	public static void wait(int i) {
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
		//System.out.print(""); // TODO: DO NOT REMOVE!!!! REQUIRED TO MAKE THE WHOLE THING WORK????? WILL NOT RUN UNLESS THIS IS HERE. Might be required to keep thread active?
		if (PlaneUIApp.ctrl.started) {
			if (!PlaneUIApp.ctrl.handling) {
				for (int i = 0;i<2;i++) { // send two out
					if (PlaneUIApp.ctrl.outbound.peek() != null) { //null check if no planes are outbound
						PlaneUIApp.ctrl.handling = true;
						PlaneUIApp.app.playAnim("Departure");
						for (int x = 0;x<2;x++) {
							PlaneUIApp.app.status.setText(PlaneUIApp.ctrl.outbound.peek()+" takes off in.. "+(2-x)+"s");
							wait(1); //countdown to departure
						}
						PlaneUIApp.app.status.setText(PlaneUIApp.ctrl.outbound.peek()+" took off!");
						PlaneUIApp.ctrl.updateQueue(PlaneUIApp.ctrl.outbound); //remove departed plane from display
						wait(1);
						PlaneUIApp.ctrl.handling = false;
					}
				}

				if (PlaneUIApp.ctrl.inbound.peek() != null) { //null check if no planes are inbound
					PlaneUIApp.ctrl.handling = true;
					PlaneUIApp.app.playAnim("Arrival");
					for (int x = 0;x<4;x++) {
						PlaneUIApp.app.status.setText(PlaneUIApp.ctrl.inbound.peek()+" enters in.. "+(4-x)+"s");
						wait(1); //countdown to arrival
					}
					PlaneUIApp.app.status.setText(PlaneUIApp.ctrl.inbound.peek()+" landed!");
					PlaneUIApp.ctrl.updateQueue(PlaneUIApp.ctrl.inbound); //remove arrived plane from display
					wait(1);
					PlaneUIApp.ctrl.handling = false;
				}
				
				if (PlaneUIApp.ctrl.inbound.peek() == null && PlaneUIApp.ctrl.outbound.peek() == null) {
					PlaneUIApp.app.status.setText("Simulation is waiting for more planes"); //all files empty
				}
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

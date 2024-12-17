package FlightSimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.out;
import javax.swing.Timer;

public class TimerListener {
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
	
	public static void sharedMain() {
		//System.out.print(""); // TODO: DO NOT REMOVE!!!! REQUIRED TO MAKE THE WHOLE THING WORK????? WILL NOT RUN UNLESS THIS IS HERE. Might be required to keep thread active?
		if (PlaneUIApp.ctrl.started) {
			if (!PlaneUIApp.ctrl.handling) {
				for (int i = 0;i<2;i++) { // send two out
					if (PlaneUIApp.ctrl.outbound.peek() != null) {
						PlaneUIApp.ctrl.handling = true;
						PlaneUIApp.app.playAnim("Departure");
						for (int x = 0;x<2;x++) {
							PlaneUIApp.app.status.setText(PlaneUIApp.ctrl.outbound.peek()+" takes off in.. "+(2-x)+"s");
							wait(1);
						}
						PlaneUIApp.app.status.setText(PlaneUIApp.ctrl.outbound.peek()+" took off!");
						PlaneUIApp.ctrl.updateQueue(PlaneUIApp.ctrl.outbound);
						wait(1);
						PlaneUIApp.ctrl.handling = false;
					}
				}

				if (PlaneUIApp.ctrl.inbound.peek() != null) {
					PlaneUIApp.ctrl.handling = true;
					PlaneUIApp.app.playAnim("Arrival");
					for (int x = 0;x<4;x++) {
						PlaneUIApp.app.status.setText(PlaneUIApp.ctrl.inbound.peek()+" enters in.. "+(4-x)+"s");
						wait(1);
					}
					PlaneUIApp.app.status.setText(PlaneUIApp.ctrl.inbound.peek()+" landed!");
					PlaneUIApp.ctrl.updateQueue(PlaneUIApp.ctrl.inbound);
					wait(1);
					PlaneUIApp.ctrl.handling = false;
				}
				
				if (PlaneUIApp.ctrl.inbound.peek() == null && PlaneUIApp.ctrl.outbound.peek() == null) {
					PlaneUIApp.app.status.setText("Simulation is waiting for more planes");
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
	
	public void runTimer() {
		while (true) {
			sharedMain();
		}
	}
}

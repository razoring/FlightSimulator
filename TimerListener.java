package FlightSimulator;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	static public void main(String[] args) {
		Controller ctrl = new Controller();
		ctrl.addIn("1234");
		ctrl.addIn("1232");
		ctrl.addIn("1244");
		ctrl.addIn("1432");
		ctrl.addOut("2434");
		ctrl.addOut("3434");
		ctrl.addOut("2434");
		ctrl.addOut("3434");
		ctrl.addOut("2434");
		ctrl.addOut("3434");
		ctrl.addOut("2434");
		ctrl.addOut("3434");
		System.out.println(ctrl.toString());
		
		while (true) {
			if (!ctrl.handling) {
				for (int i = 0;i<2;i++) { // send two out
					if (ctrl.outbound.peek() != null) {
						System.out.println("working 2s");
						ctrl.handling = true;
						wait(2);
						System.out.println("cleared 2s");
						ctrl.outbound.remove();
						ctrl.handling = false;
					}
				}

				if (ctrl.inbound.peek() != null) {
					System.out.println("working 4s");
					ctrl.handling = true;
					wait(4);
					System.out.println("cleared 4s");
					ctrl.inbound.remove();
					ctrl.handling = false;
				}
			}
		}
	}
}

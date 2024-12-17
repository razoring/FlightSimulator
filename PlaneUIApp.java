package FlightSimulator;

import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * TODO:
 * !!! Normal Status Messages (Flight In/Out) *DONE: Needs to be checked*
 * !!! Error Status Messages *DONE: Needs to be checked*
 * !!! File Saving *DONE: Needs to be checked*
 * !!! 50x50 Plane Animation (must be realistic D:) [Raymond's Job]
 * !!! Function Docstrings
 * !! Comments
 * !! Remove Unused Code
 * !! CHECK FOR REDUNDANCY mashallah
 * ! Every error must have a status message either in console or in the label
 * ! Check code
 * - Optimize code [raymond's job]
 * - Remove unused imports
 */

public class PlaneUIApp {
	public static Controller ctrl = new Controller(); // global reference for synchronous processing
	public static PlaneUI app = new PlaneUI(); // global reference for synchronous processing
	private static boolean runThread = false; // decide running mode
	
	static public void main(String[] args) {
		app.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		app.setSize(500, 500); // set frame size
		app.setVisible(true); // display frame

		//Try-Catch statements for reading arrivals/departure data
		try {
			try {
				if (app.depFile.createNewFile()) {
					System.out.println("404: File not found");
					System.out.println("201: "+app.depFile.getName()+" created");
				} else {
					System.out.println("200: Found "+app.depFile.getName()+"");
				}
			} catch (IOException e) {
				System.out.println("409: Trouble saving... exiting...");
				System.exit(0); // exit to allow for full processing reset
			}

			try {
				if (app.arrFile.createNewFile()) {
					System.out.println("404: File not found");
					System.out.println("201: "+app.arrFile.getName()+" created");
				} else {
					System.out.println("200: Found "+app.arrFile.getName()+"");
				}
			} catch (IOException e) {
				System.out.println("409: Trouble saving... exiting...");
				System.exit(0); // exit to allow for full processing reset
			}
			
			Scanner arrRead = new Scanner(app.arrFile);
			Scanner depRead = new Scanner(app.depFile);

			if (arrRead.hasNext()) {
		        String arrData = arrRead.nextLine();
		        for (String val : arrData.substring(1,arrData.length()-1).split(",")) {
		        	ctrl.addIn(val.trim());
		        }
			}
			
			if (depRead.hasNext()) {
		        String depData = depRead.nextLine();
		        for (String val : depData.substring(1,depData.length()-1).split(",")) {
		        	ctrl.addOut(val.trim());
		        }
			}

		// Refreshes arrivals/departure displays
	        app.refresh();

		    // Close files to prevent memory leaks 
		    arrRead.close();
		    depRead.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("404: File not found! Cannot create, will exit...");
			System.exit(0);
		}
		
		if (runThread) { // depends on user preference, duo thread is optimal for multicore processors, single thread is default
			// create new thread so timer and planeUI can run asynchronously
			new Thread(new Runnable() {
			    public void run() {
			        TimerListener.main(args);
			    }
			}).start();
		} else {
			// mimic listener main method
			TimerListener timer = new TimerListener();
			timer.runTimer();
		}
	}
}

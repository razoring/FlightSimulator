package FlightSimulator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * Jiawei Chen, Raymond So <p>
 * 12/11/2024 <p>
 * Generates the frontend UI for the plane simulator.
 */

/* TODO:
 * + Animation panel
 * + Individual TODOs in methods/inner classes
 * + Raymond Likes Men.
 */

@SuppressWarnings("serial")
public class PlaneUI extends JFrame {
	// public global variables for synchronous processing
	public JLabel status = new JLabel("Press \'Start\' to begin the simulator.");
	public File depFile = new File("takeoffs.txt");
	public File arrFile = new File("arrivals.txt");
	public ImageIcon icon = new ImageIcon("src/FlightSimulator/PlaneAnim/Departure/pixil-frame-0.png"); // placeholders
	//public ImageIcon icon = new ImageIcon("src/FlightSimulator/PlaneAnim/placeholder.jpeg");
	//public ImageIcon icon = new ImageIcon("src/FlightSimulator/PlaneAnim/placeholder.png"); // this works
	public JLabel img = new JLabel(icon);

	//Private settings for border & status label
	private BorderLayout border = new BorderLayout();
	private DefaultListModel<String> arrModel = new DefaultListModel<>();
	private DefaultListModel<String> depModel = new DefaultListModel<>();
	private JList<String> arrDisplay = new JList<>(arrModel);
	private JList<String> depDisplay = new JList<>(depModel);

	//Input Fields/Buttons that need event handling
	JTextField arrInput = new JTextField(10); //initialize input field
	JTextField depInput = new JTextField(10);

	JButton start = new JButton("Start"); //initialize buttons
	JButton exit = new JButton("Exit");

	/**
	 * No argument constructor. Adds the different panels to the main UI
	 */
	public PlaneUI() {
		super("Plane Simulator"); //set title
		setLayout(border);
		
		//Padding for status bar
		JPanel statusBar = new JPanel(new BorderLayout());
		statusBar.add(status, BorderLayout.CENTER);
		statusBar.add(new JPanel(), BorderLayout.WEST);
		statusBar.add(new JPanel(), BorderLayout.SOUTH);
		
		add(statusBar, BorderLayout.NORTH); //add status labal to main UI
		add(ListUI(), BorderLayout.CENTER); //add planes display to main UI
		add(InteractionUI(), BorderLayout.SOUTH); //add interactive ui elements to main UI

	} //end constructor

	/**
	 * Handles the JTextField showing departure/arrivals.
	 */
	public JPanel ListUI() {
		JPanel ui = new JPanel(new BorderLayout()); //main "hub" ui
		JPanel list = new JPanel(new GridLayout(1, 2, 10, 5)); //setting a grid with 1 row, 2 columns
		
		//Initializing header & list
		JLabel arrTitle = new JLabel("Arrivals", SwingConstants.CENTER); //aligning header with swingconstants (idk if we learned this)
		JLabel depTitle = new JLabel("Departures", SwingConstants.CENTER); //aligning header with swingconstants

		JPanel arrivals = new JPanel(new BorderLayout()); //for arrivals
		arrivals.add(arrTitle, BorderLayout.NORTH); //add header
		arrivals.add(arrDisplay, BorderLayout.CENTER); //add display JList

		JPanel departures = new JPanel(new BorderLayout()); //for departures
		departures.add(depTitle, BorderLayout.NORTH); //add header
		departures.add(depDisplay, BorderLayout.CENTER); //add display JList

		list.add(arrivals); //add inbound
		list.add(departures); //add outbound

		ui.add(list, BorderLayout.CENTER);
		
		//padding
		ui.add(new JPanel(), BorderLayout.WEST);
		ui.add(new JPanel(), BorderLayout.EAST);

		return ui; //return ui
	} //end ListUI

	/**
	 * Handles interactive elements (input fields, start/exit buttons)
	 * @return interact - A GridLayout with inputFields and buttonField
	 */
	public JPanel InteractionUI() {
		JPanel ui = new JPanel(new BorderLayout()); //main "hub" panel
		JPanel interact = new JPanel(new GridLayout(2, 1)); //grid with 2 rows, 1 column
		
		JPanel arrivals = new JPanel(new FlowLayout()); //grouping arrival header & textfield together
		arrivals.add(new JLabel("Arrivals:")); //header
		arrivals.add(arrInput); //textfield

		JPanel departures = new JPanel(new FlowLayout()); //grouping header & textfield together
		departures.add(new JLabel("Departures:")); //header
		departures.add(depInput); //textfield

		JPanel buttonField = new JPanel(new GridLayout(1, 2, 10, 5)); //gridlayout for start/exit buttons
		buttonField.add(start); //add buttons
		buttonField.add(exit);

		JPanel inputFields = new JPanel((new GridLayout(1, 2))); //gridlayout for arrival & departure input fields
		inputFields.add(arrivals);
		inputFields.add(departures);
		
		interact.add(inputFields); //add to main interact ui
		interact.add(buttonField);
		
		start.setFocusable(false);
		exit.setFocusable(false);

		//ActionListeners
		TextFieldHandler handler = new TextFieldHandler(); //for arrival/departure inputs
		arrInput.addActionListener(handler);
		depInput.addActionListener(handler);

		ButtonHandler buttonHandler = new ButtonHandler(); //for start/exit buttons
		start.addActionListener(buttonHandler);
		exit.addActionListener(buttonHandler);
		ui.add(interact, BorderLayout.CENTER);
		
		//padding
		ui.add(new JPanel(), BorderLayout.WEST);
		ui.add(new JPanel(), BorderLayout.EAST);
		ui.add(new JPanel(), BorderLayout.SOUTH);
		
		ui.add(img, BorderLayout.NORTH); //add status labal to main UI

		return ui; //return ui
	} //end InteractionUI


	//Handler Inner Classes
	private class TextFieldHandler implements ActionListener {
		/* TODO: 
		 * + Duplicate handling
		 * + Connect to logic (add to queue)
		 * + Silent handle NumberFormatException
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				int flightNum = Integer.parseInt(event.getActionCommand());

				if (flightNum >= 1 && flightNum <= 9999) {
					//%04d displays "1" as "0001"
					String flight = String.format("%04d", flightNum);
					if (!PlaneUIApp.ctrl.inbound.contains(flight)) {
						if (event.getSource() == arrInput) { //handles arrivals
							PlaneUIApp.ctrl.addIn(flight);
						} else if (event.getSource() == depInput) { //handles arrivals
							PlaneUIApp.ctrl.addOut(flight);
						}
						//System.out.println(PlaneUIApp.ctrl.toString());
						refresh();
						status.setText("Press \'Start\' to begin the simulator.");
					} else {
						status.setText("409: Flight already exists");
					}
				} else {
					status.setText("400: Invalid flight number");
				}
			} catch (NumberFormatException ex) { //unable to parse
				status.setText("400: Invalid input");
			}
			
			arrInput.setText("");
			depInput.setText("");
		} //end actionPerformed
		
	} //end TextFieldHandler

	/**
	 * Handles actions for the start/exit buttons
	 */
	private class ButtonHandler implements ActionListener {
		/* TODO:
		 * + Connect 'start' event to logic circuit (start simulation)
		 * + OPT: Change 'start' to 'pause' if simulator is running
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == exit) {
				try { // write to created/found files
					FileWriter depWriter = new FileWriter(depFile);
					depWriter.write(""+Arrays.deepToString(PlaneUIApp.ctrl.outbound.toArray()));
					depWriter.close();
					
					FileWriter arrWriter = new FileWriter(arrFile);
					arrWriter.write(""+Arrays.deepToString(PlaneUIApp.ctrl.inbound.toArray()));
					arrWriter.close();
				} catch(IOException e) {
					System.out.println("409: Trouble writing... exiting...");
				}
				System.exit(ABORT); //Terminate the code (I looked this one up) congrats! :D
			} else if (event.getSource() == start) {
				PlaneUIApp.ctrl.started = true;
			}
		} //end actionPerformed

	} //end ButtonHandler
	
	/**
	 * Updates the arrivals/departures display
	 */
	public void refresh() {
	    // clear existing list data
	    arrModel.clear();
	    depModel.clear();
	    
	    for (String flight : PlaneUIApp.ctrl.inbound) { // fetch data from inbound queue
	        arrModel.addElement(flight);
	    }
	    for (String flight : PlaneUIApp.ctrl.outbound) { // fetch data from outbound queue
	        depModel.addElement(flight);
	    }
	    
		revalidate(); // refresh status
		repaint(); // repaint anim
	}
	
	public void playAnim(String type) {
		for (int i = 0;i<11;i++) {
		    icon = new ImageIcon("src/FlightSimulator/PlaneAnim/"+type+"/pixil-frame-"+i+".png");
		    img.setIcon(icon);
		}
	    //icon = new ImageIcon("src/FlightSimulator/PlaneAnim/"+type+"/pixil-frame-0.png");
	    //img.setIcon(icon);
	}
} //end class

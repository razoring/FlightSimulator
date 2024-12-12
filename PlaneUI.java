package FlightSimulator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
 */
@SuppressWarnings("serial")
public class PlaneUI extends JFrame {
	//Private settings for border & status label
	private BorderLayout border = new BorderLayout();
	private JLabel status = new JLabel("  Press \'Start\' to begin the simulator.");

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

		add(status, BorderLayout.NORTH); //add status labal to main UI
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
		JList<Integer> arrDisplay = new JList<Integer>(); //using JList to display inbound/outbound planes
		JList<Integer> depDisplay = new JList<Integer>();

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
					if (event.getSource() == arrInput) { //handles arrivals
						//%04d displays "1" as "0001"
						System.out.printf("Arriving: %04d\n", flightNum);
					} else if (event.getSource() == depInput) { //handles arrivals
						System.out.printf("Departing: %04d\n", flightNum);
					}
				} else {
					System.out.println("Invalid flight number.");
				}

			} catch (NumberFormatException ex) { //unable to parse
				JOptionPane.showMessageDialog( null, "Invalid input.", "Error", JOptionPane.WARNING_MESSAGE);
			}
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
				System.out.println("Exit");
				System.exit(ABORT); //Terminate the code (I looked this one up)
			} else if (event.getSource() == start) {
				System.out.println("Start"); //placeholder
			}
		} //end actionPerformed

	} //end ButtonHandler

} //end class

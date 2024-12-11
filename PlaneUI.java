package FlightSimulator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * Jiawei Chen, Raymond So <p>
 * 12/11/2024 <p>
 * Generates the frontend UI for the plane simulator.
 */
@SuppressWarnings("serial")
public class PlaneUI extends JFrame {
	//Private settings for border & status label
	private BorderLayout border = new BorderLayout();
	private JLabel status = new JLabel("Press \'Start\' to begin the simulator.");
	
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
		JPanel ui = new JPanel(new GridLayout(1, 3, 10, 5)); //setting a grid with 1 row, 3 columns
		
		JPanel arrivals = new JPanel(new BorderLayout()); //for arrivals
		JPanel departures = new JPanel(new BorderLayout()); //for departures

		JLabel arrTitle = new JLabel("Arrivals", SwingConstants.CENTER); //aligning header with swingconstants (idk if we learned this)
		JLabel depTitle = new JLabel("Departures", SwingConstants.CENTER); //aligning header with swingconstants
		JList<Integer> arrDisplay = new JList<Integer>(); //using JList to display inbound/outbound planes
		JList<Integer> depDisplay = new JList<Integer>();
		
		arrivals.add(arrTitle, BorderLayout.NORTH); //add header
		arrivals.add(arrDisplay, BorderLayout.CENTER); //add display JList
		
		departures.add(depTitle, BorderLayout.NORTH); //add header
		departures.add(depDisplay, BorderLayout.CENTER); //add display JList
		
		ui.add(arrivals); //add inbound
		ui.add(departures); //add outbound

		ui.add(new JPanel()); //animation panel

		return ui; //return ui
	} //end ListUI
	
	/**
	 * Handles interactive elements (input fields, start/exit buttons)
	 * @return interact - A GridLayout with inputFields and buttonField
	 */
	public JPanel InteractionUI() {
		JPanel interact = new JPanel(new GridLayout(2, 1)); //grid with 2 rows, 1 column
		JTextField arrInput = new JTextField(10); //initialize input field
		JTextField depInput = new JTextField(10);
		
		JButton start = new JButton("Start"); //initialize buttons
		JButton exit = new JButton("Exit");
		
		JPanel inputFields = new JPanel((new FlowLayout())); //using flowlayout for inputfields
		inputFields.add(new JLabel("Arrivals:")); //header
		inputFields.add(arrInput); //textfield
		inputFields.add(new JLabel("Departures:"));
		inputFields.add(depInput);
		
		JPanel buttonField = new JPanel(new FlowLayout()); //using flowlayout for buttons
		buttonField.add(start); //add buttons
		buttonField.add(exit);
		
		interact.add(inputFields); //add to main interact ui
		interact.add(buttonField);
		
		return interact; //return ui
	} //end InteractionUI
	
} //end class

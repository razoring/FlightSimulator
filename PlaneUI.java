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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
/**
 * Jiawei Chen <p>
 * 12/03/2024 <p>
 * Generates the GUI as given in 4-5 Practice, without functionality
 */
@SuppressWarnings("serial")
public class PlaneUI extends JFrame {
	//private GridLayout settings;
	private BorderLayout border = new BorderLayout();
	private JLabel status = new JLabel("[ Plane Status ]");
	
	/**
	 * No argument constructor. Adds the different panels to the main UI
	 */
	public PlaneUI() {
		super("Plane Simulator");
		setLayout(border);
		
		add(status, BorderLayout.NORTH);
		add(ListUI(), BorderLayout.CENTER);
		add(InteractionUI(), BorderLayout.SOUTH);
		
		
	} //end constructor
	

	
	/**
	 * Handles the JTextField showing departure/arrivals.
	 */
	public JPanel ListUI() {
		JPanel ui = new JPanel(new GridLayout(1, 3, 10, 5));

		JPanel arrivals = new JPanel(new BorderLayout());
		JPanel departures = new JPanel(new BorderLayout());

		JLabel arrTitle = new JLabel("Arrivals", SwingConstants.CENTER);
		JLabel depTitle = new JLabel("Departures", SwingConstants.CENTER);
		JList<Integer> arrDisplay = new JList<Integer>();
		JList<Integer> depDisplay = new JList<Integer>();
		
		arrivals.add(arrTitle, BorderLayout.NORTH);
		arrivals.add(arrDisplay, BorderLayout.CENTER);
		
		departures.add(depTitle, BorderLayout.NORTH);
		departures.add(depDisplay, BorderLayout.CENTER);
		
		ui.add(arrivals);
		ui.add(departures);

		ui.add(new JPanel()); //temporary for anim panel
	

		
		return ui;
	} //end buttonsPanel
	
	public JPanel InteractionUI() {
		JPanel interact = new JPanel(new GridLayout(2, 1));
		JTextField arrInput = new JTextField(10);
		JTextField depInput = new JTextField(10);
		
		JButton start = new JButton("Start");
		JButton exit = new JButton("Exit");
		
		JPanel inputFields = new JPanel((new FlowLayout()));
		inputFields.add(new JLabel("Arrivals:"));
		inputFields.add(arrInput);
		inputFields.add(new JLabel("Departures:"));
		inputFields.add(depInput);
		
		JPanel buttonField = new JPanel(new FlowLayout());
		buttonField.add(start);
		buttonField.add(exit);
		
		interact.add(inputFields);
		interact.add(buttonField);
		
		return interact;
	}
} //end class

package FlightSimulator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
/**
 * Jiawei Chen <p>
 * 12/03/2024 <p>
 * Generates the GUI as given in 4-5 Practice, without functionality
 */
@SuppressWarnings("serial")
public class PlaneUI extends JFrame {
	//private GridLayout settings;
	private BorderLayout border = new BorderLayout();
	private String[] qualTypes = { "High", "Medium", "Low" };
	
	/**
	 * No argument constructor. Adds the different panels to the main UI
	 */
	public PlaneUI() {
		super("Plane Simulator");
		setLayout(border);
		
		add(new JLabel("Printer : MyPrinter"), BorderLayout.NORTH);
		add(settingsPanel(), BorderLayout.CENTER);
		add(qualityPanel(), BorderLayout.SOUTH);
		add(buttonsPanel(), BorderLayout.EAST);
	} //end constructor
	
	/**
	 * Formats JCheckBoxes and JRadioButtons with a GridLayout
	 * @return Settings panel with JRadioButtons and JCheckBoxes
	 */
	public JPanel settingsPanel() {
		ButtonGroup radioGroup = new ButtonGroup();
		JPanel settings = new JPanel(new GridLayout(3, 2));
		
		JRadioButton sel = new JRadioButton("Selection");
		JRadioButton all = new JRadioButton("All");
		JRadioButton applet = new JRadioButton("Applet");
		
		radioGroup.add(sel);
		radioGroup.add(all);
		radioGroup.add(applet);
		
		settings.add(new JCheckBox("Images"));
		settings.add(sel);
		settings.add(new JCheckBox("Text"));
		settings.add(all);
		settings.add(new JCheckBox("Code"));
		settings.add(applet);
		
		return settings;
	} //end settingsPanel
	
	/**
	 * Formats the JComboBox and "Print to File" checkbox with a FlowLayout aligned to the left.
	 * @return Quality panel with Printer Quality dropdown and Print to File checkbox
	 */
	public JPanel qualityPanel() {
		JPanel quality = new JPanel(new FlowLayout(FlowLayout.LEFT));
		quality.add(new JLabel("Printer Quality"));
		quality.add(new JComboBox<String>(qualTypes));
		quality.add(new JCheckBox("Print to File"));
		
		return quality;
	} //end qualityPanel
	
	/**
	 * Formats the JButtons with a GridLayout
	 * @return Buttons panel with the 4 buttons
	 */
	public JPanel buttonsPanel() {
		JPanel buttons = new JPanel(new GridLayout(4, 1, 5, 5));
		
		buttons.add(new JButton("OK"));
		buttons.add(new JButton("Cancel"));
		buttons.add(new JButton("Setup"));
		buttons.add(new JButton("Help"));
		
		return buttons;
	} //end buttonsPanel
} //end class

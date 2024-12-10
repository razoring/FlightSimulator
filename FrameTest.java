// Testing Frame
package FlightSimulator;

import javax.swing.JFrame;
public class FrameTest
{
	public static void main( String args[] ) {
		Frame frame = new Frame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( 350, 200 ); // set frame size
		frame.setVisible( true ); // display frame
	} // end main
} // end class FrameTest
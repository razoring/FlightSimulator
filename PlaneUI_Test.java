// Testing Frame
package FlightSimulator;

import javax.swing.JFrame;
public class PlaneUI_Test
{
	public static void main( String args[] ) {
		PlaneUI planeUI = new PlaneUI();
		planeUI.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		planeUI.setSize( 400, 400 ); // set frame size
		planeUI.setVisible( true ); // display frame
	} // end main
} // end class FrameTest
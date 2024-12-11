package FlightSimulator;

public class LogicTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller ctrl = new Controller();
		ctrl.addIn("1234");
		ctrl.addIn("123");
		ctrl.addIn("12");
		ctrl.addIn("1");
		System.out.println(ctrl.toString());
	}

}

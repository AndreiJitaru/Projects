package Test;

import Controller.SimulationActivator;
import Model.Processor;
import View.View;

public class Test {
	public static void main(String args[]) {
		new SimulationActivator(new View(), new Processor());
	}
}

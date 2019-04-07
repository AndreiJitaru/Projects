package MVC;

import Controller.Controller;
import Model.Operations;
import View.View;

public class ModelViewController {
	public static void main(String[] args) {
		new Controller(new Operations(), new View());
	}
}
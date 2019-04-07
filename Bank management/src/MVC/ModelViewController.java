package MVC;

import Controller.Controller;
import Model.Bank;
import View.View;

public class ModelViewController {
	public static void main(String[] args) {
		new Controller(new View(), new Bank());
	}
}

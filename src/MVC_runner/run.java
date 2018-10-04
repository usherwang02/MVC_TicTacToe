package MVC_runner;

import controller.Controller;
import model.Model;
import view.View;

public class run {
	public static void main(String[] args) {
	  // Assemble all the pieces of the MVC
	  Model m = new Model("M", "N");
	  View v = new View();
	  Controller c = new Controller(m, v);
	  c.initController();
	 }
}

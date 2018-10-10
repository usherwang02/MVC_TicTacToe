package MVC_runner;

import java.awt.Color;

import javax.swing.JPanel;

import controller.Controller;
import model.Model;
import view.View;

public class MVCrunner {
	public Model m = new Model("X", "O");;
	public View v = new View(Color.white);
	public Controller c = new Controller(m, v, false);
	public MVCrunner(){
//		Model m = new Model("X", "O");
//		View v = new View();
//		Controller c = new Controller(m, v);
		c.initView();
	}
	public static void main(String[] args) {
	  // Assemble all the pieces of the MVC
		MVCrunner run = new MVCrunner();
	 }
}

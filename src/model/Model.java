package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Model {
	private String label1;
	private String label2;
	public Model(String label1, String label2) {
		this.label1 = label1;
		this.label2 = label2;
	}
	public String getLabel1() {
		  return label1;
	}
		 
	public void setLabel1(String label1) {
	 this.label1 = label1;
	}
	 
	public String getLabel2() {
		  return label2;
	}
		 
	public void setLabel2(String label2) {
	 this.label2 = label2;
	}
}

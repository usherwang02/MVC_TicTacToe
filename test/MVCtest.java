import org.junit.Test;

import MVC_runner.MVCrunner;
import controller.Controller;
import model.Model;
import view.View;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class MVCtest {
    @Test
    public void testNewGame() {
        MVCrunner run = new MVCrunner();
        assertEquals ("O", run.m.getLabel2()); 	//model test
        assertEquals (Color.white, run.v.getColor()); 	//view test
        assertEquals (false, run.c.getTerminalControl()); 	//controller test
    }
}

package sodoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
	

	public Window() {
		setTitle("Sodoku");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		add(panel);
		setVisible(true);
		
	}
	
}

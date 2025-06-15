package sodoku;

import javax.swing.*;

public class Panel extends JPanel{
	private JButton button;
	
	public Panel() {
		setLayout(null);
		button = new JButton("click me");
		button.setBounds(150, 300, 100, 30);
		add(button);
		
		button.addActionListener(e -> System.out.println("Click!"));
	}
}

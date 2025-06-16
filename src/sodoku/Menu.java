package sodoku;

import java.awt.*;
import javax.swing.*;

public class Menu {
	
	//startmenu
	static void start() {
	    JFrame startFrame = new JFrame("Sudoku - Start Menu");
	    startFrame.setSize(400, 350);
	    startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    startFrame.setLocationRelativeTo(null);

	    JPanel panel = new JPanel();
	    panel.setBackground(Color.BLACK);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    JLabel title = new JLabel("Sudoku Game");
	    title.setForeground(Color.WHITE);
	    title.setFont(new Font("SansSerif", Font.BOLD, 24));
	    title.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel diffLabel = new JLabel("Select Difficulty:");
	    diffLabel.setForeground(Color.WHITE);
	    diffLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

	    String[] difficulties = {"Easy (20)", "Medium (40)", "Hard (60)", "Custom"};
	    JComboBox<String> difficultyBox = new JComboBox<>(difficulties);
	    difficultyBox.setMaximumSize(new Dimension(200, 30));
	    difficultyBox.setAlignmentX(Component.CENTER_ALIGNMENT);

	    // Custom input field
	    JTextField customField = new JTextField();
	    customField.setMaximumSize(new Dimension(200, 30));
	    customField.setAlignmentX(Component.CENTER_ALIGNMENT);
	    customField.setVisible(false); // Hidden unless "Custom" is selected

	    difficultyBox.addActionListener(e -> {
	        if (difficultyBox.getSelectedItem().equals("Custom")) {
	            customField.setVisible(true);
	        } else {
	            customField.setVisible(false);
	        }
	        startFrame.revalidate();
	        startFrame.repaint();
	    });

	    JButton startButton = new JButton("Start Game");
	    startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    startButton.setBackground(Color.DARK_GRAY);
	    startButton.setForeground(Color.WHITE);

	    startButton.addActionListener(e -> {
	        int k;
	        if (difficultyBox.getSelectedItem().equals("Custom")) {
	            try {
	                k = Integer.parseInt(customField.getText());
	                if (k < 10 || k > 64) {
	                    throw new NumberFormatException();
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(startFrame, "Please enter a number between 10 and 64 for custom difficulty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        } else {
	            int index = difficultyBox.getSelectedIndex();
	            int[] kValues = {20, 40, 60};
	            k = kValues[index];
	        }

	        startFrame.dispose();
	        new Window(k); // Start game with selected or custom difficulty
	    });

	    panel.add(Box.createVerticalGlue());
	    panel.add(title);
	    panel.add(Box.createRigidArea(new Dimension(0, 20)));
	    panel.add(diffLabel);
	    panel.add(difficultyBox);
	    panel.add(Box.createRigidArea(new Dimension(0, 10)));
	    panel.add(customField);
	    panel.add(Box.createRigidArea(new Dimension(0, 20)));
	    panel.add(startButton);
	    panel.add(Box.createVerticalGlue());

	    startFrame.add(panel);
	    startFrame.setVisible(true);
	}
	
	//after the determined fail, this method brings a menu
	static void fail(int score, int k) {
	    JFrame failFrame = new JFrame("Game Over");
	    failFrame.setSize(400, 300);
	    failFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    failFrame.setLocationRelativeTo(null);

	    JPanel panel = new JPanel();
	    panel.setBackground(Color.BLACK);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    JLabel message = new JLabel("Game Over");
	    message.setForeground(Color.RED);
	    message.setFont(new Font("SansSerif", Font.BOLD, 24));
	    message.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel scoreLabel = new JLabel("Your score: " + Score.score(score, k));
	    scoreLabel.setForeground(Color.WHITE);
	    scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
	    scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JButton retryButton = new JButton("Play Again");
	    retryButton.setBackground(Color.DARK_GRAY);
	    retryButton.setForeground(Color.WHITE);
	    retryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    retryButton.addActionListener(e -> {
	        failFrame.dispose();
	        new Window(k);
	    });

	    JButton backButton = new JButton("Back to Start");
	    backButton.setBackground(Color.GRAY);
	    backButton.setForeground(Color.WHITE);
	    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    backButton.addActionListener(e -> {
	        failFrame.dispose();
	        Menu.start();
	    });

	    panel.add(Box.createVerticalGlue());
	    panel.add(message);
	    panel.add(Box.createRigidArea(new Dimension(0, 20)));
	    panel.add(scoreLabel);
	    panel.add(Box.createRigidArea(new Dimension(0, 20)));
	    panel.add(retryButton);
	    panel.add(Box.createRigidArea(new Dimension(0, 10)));
	    panel.add(backButton);
	    panel.add(Box.createVerticalGlue());

	    failFrame.add(panel);
	    failFrame.setVisible(true);
	}

    //victory!
	static void victory(int score, int k) {
	    JFrame victoryFrame = new JFrame("You Win!");
	    victoryFrame.setSize(400, 300);
	    victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    victoryFrame.setLocationRelativeTo(null);

	    JPanel panel = new JPanel();
	    panel.setBackground(Color.BLACK);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    JLabel message = new JLabel("Congratulations!");
	    message.setForeground(new Color(0, 255, 100));
	    message.setFont(new Font("SansSerif", Font.BOLD, 24));
	    message.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel scoreLabel = new JLabel("Your score: " + Score.score(score, k));
	    scoreLabel.setForeground(Color.WHITE);
	    scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
	    scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JButton retryButton = new JButton("Play Again");
	    retryButton.setBackground(Color.DARK_GRAY);
	    retryButton.setForeground(Color.WHITE);
	    retryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    retryButton.addActionListener(e -> {
	        victoryFrame.dispose();
	        new Window(k);
	    });

	    JButton backButton = new JButton("Back to Start");
	    backButton.setBackground(Color.GRAY);
	    backButton.setForeground(Color.WHITE);
	    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    backButton.addActionListener(e -> {
	        victoryFrame.dispose();
	        Menu.start();
	    });

	    panel.add(Box.createVerticalGlue());
	    panel.add(message);
	    panel.add(Box.createRigidArea(new Dimension(0, 20)));
	    panel.add(scoreLabel);
	    panel.add(Box.createRigidArea(new Dimension(0, 20)));
	    panel.add(retryButton);
	    panel.add(Box.createRigidArea(new Dimension(0, 10)));
	    panel.add(backButton);
	    panel.add(Box.createVerticalGlue());

	    victoryFrame.add(panel);
	    victoryFrame.setVisible(true);
	}

}


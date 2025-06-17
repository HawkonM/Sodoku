package sodoku;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import java.awt.*;


public class Window extends JFrame {
	private static final int SIZE = 9;
	private JButton[][] cells = new JButton[SIZE][SIZE];
	private int[][] puzzle ;
	static int[][] solution;
	
	private int score = 0;
	private int fail = 0;
	
	private JButton selectedCell = null;
	private int selectedRow = -1, selectedCol = -1;
	private int k;
	
	

	public Window(int k) {
		this.k=k;
		setTitle("Sudoku Game");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		// Generate puzzle and solution
		
		
		puzzle = Gen.sudokuGenerator(k);
		solution = Gen.deepCopy(puzzle);
		Gen.removeKDigits(puzzle, k);
		
		
		
		// Output for solution for "testing purposes"
		int[][] sodoku = Window.solution;
		for (int[] row: sodoku) {
			for (int cell:row) {
				System.out.print(cell + "  ");
				
			}
			System.out.println();
		}
		
		

		// Grid Panel
		JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
		Font font = new Font("SansSerif", Font.BOLD, 18);

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				JButton cell = new JButton();
				cell.setFont(font);
				cell.setFocusPainted(false);
				cell.setOpaque(true);
				cell.setBackground(Color.BLACK);
				cell.setForeground(Color.WHITE);
				cells[row][col] = cell;

				setCellBorder(cell, row, col);

				int value = puzzle[row][col];
				if (value != 0) {
					cell.setText(String.valueOf(value));
					cell.setEnabled(false);
					//cell.setBackground(new Color(230, 230, 230));
					cell.setBackground(Color.BLACK);
					cell.setForeground(Color.WHITE);
				} else {
					int r = row, c = col;
					cell.addActionListener(e -> selectCell(r, c));
				}

				gridPanel.add(cell);
			}
		}

		// Number Buttons
		JPanel numPanel = new JPanel(new GridLayout(1, 9, 5, 5));
		for (int i = 1; i <= 9; i++) {
			JButton numButton = new JButton(String.valueOf(i));
			numButton.setFont(font);
			numButton.setBackground(Color.BLACK);
			numButton.setForeground(Color.WHITE);
			int num = i;
			numButton.addActionListener(e -> fillSelectedCell(num));
			numPanel.add(numButton);
		}

		

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(numPanel, BorderLayout.CENTER);
		bottomPanel.setBackground(Color.BLACK);
		add(gridPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	private void setCellBorder(JButton button, int row, int col) {
		int top = (row % 3 == 0) ? 3 : 1;
		int left = (col % 3 == 0) ? 3 : 1;
		int bottom = (row == 8) ? 3 : 1;
		int right = (col == 8) ? 3 : 1;
		button.setBorder(new MatteBorder(top, left, bottom, right, Color.GRAY));
	}

	private void selectCell(int row, int col) {
		if (selectedCell != null) {
			selectedCell.setBackground(Color.BLACK);
		}
		selectedRow = row;
		selectedCol = col;
		selectedCell = cells[row][col];
		selectedCell.setBackground(new Color(50, 50, 70)); // dark blue
	}

	private void fillSelectedCell(int number) {
		if (selectedCell == null || selectedRow == -1 || selectedCol == -1) return;

		if (number == solution[selectedRow][selectedCol]) {
			selectedCell.setText(String.valueOf(number));
			selectedCell.setForeground(new Color(0, 128, 0));
			selectedCell.setEnabled(false);
			score++;
			System.out.println("Score: "+Score.score(score, k));
		} else {
			selectedCell.setText(String.valueOf(number));
			selectedCell.setForeground(Color.RED);
			fail ++;
			System.out.println("Fail: "+fail+"/3");
			if(fail >= 3) {
				dispose();
				Menu.fail(fail, k);
			}
		}

		selectedCell.setBackground(Color.DARK_GRAY);
		selectedCell = null;
		if (score == k) {
		    dispose(); // Close current game window
		    Menu.victory(score, k);
		}
		selectedRow = -1;
		selectedCol = -1;
	}


}

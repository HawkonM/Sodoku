package sodoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private int[][] solution;
    private int[][] puzzle;

    public Window() {
        setTitle("Sudoku");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Generate puzzle
        int k = 20; // difficulty (cells removed)
        solution = new int[SIZE][SIZE];
        puzzle = Gen.sudokuGenerator(k);
        copyGrid(puzzle, solution);
        Gen.fillRemaining(solution, 0, 0); // get full solution again

        JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        Font font = new Font("SansSerif", Font.BOLD, 20);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(font);
                cells[row][col] = cell;

                int value = puzzle[row][col];
                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                    cell.setBackground(new Color(220, 220, 220));
                } else {
                    int r = row, c = col;
                    cell.addActionListener(e -> checkCell(r, c));
                }

                gridPanel.add(cell);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        JButton checkButton = new JButton("Check All");
        checkButton.addActionListener(e -> checkAllCells());
        add(checkButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void copyGrid(int[][] from, int[][] to) {
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(from[i], 0, to[i], 0, SIZE);
    }

    private void checkCell(int row, int col) {
        String input = cells[row][col].getText();
        try {
            int val = Integer.parseInt(input);
            if (val == solution[row][col]) {
                cells[row][col].setForeground(Color.GREEN.darker());
                cells[row][col].setEditable(false); // lock correct input
            } else {
                cells[row][col].setForeground(Color.RED);
            }
        } catch (NumberFormatException e) {
            cells[row][col].setForeground(Color.BLACK);
        }
    }

    private void checkAllCells() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (puzzle[row][col] == 0) {
                    checkCell(row, col);
                }
            }
        }
    }
}

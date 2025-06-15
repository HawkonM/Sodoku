package sodoku;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//System.out.println("Hei!");
			Random r = new Random();
			new Window();
			int k = 20;
			int[][] sodoku = Window.solution;
			
			//output
			for (int[] row: sodoku) {
				for (int cell:row) {
					System.out.print(cell + "  ");
					
				}
				System.out.println();
			
			
			
	}

	}
}

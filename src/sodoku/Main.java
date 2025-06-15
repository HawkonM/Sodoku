package sodoku;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//System.out.println("Hei!");
			Random r = new Random();
			int k = 0;
			int[][] sodoku = Gen.sudokuGenerator(k);
			
			//output
			for (int[] row: sodoku) {
				for (int cell:row) {
					System.out.print(cell + "  ");
					
				}
				System.out.println();
			}
			
	}

}

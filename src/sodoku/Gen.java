package sodoku;
	import java.util.Random;
public class Gen {
	// returns flase if 3x3 chunk contains num
	static boolean unUsedInChunk(int[][] grid, int rowStart, int colStart,int num) {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(grid[rowStart+i][colStart+j] == num) {
					return false;
				}
			}
		}
	return true;
	}
	
	//Fills 3x3 chunk with valid numbers
	static void fillChunk(int[][] grid, int row, int col) {
		Random r = new Random();
		int num;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				do {
					num = r.nextInt(9) + 1;
				} while(!unUsedInChunk(grid, row, col, num));
					grid[row+i][col+j]=num;
				
			}
		}
	}
	
	//checks if num is unused in row i
	static boolean unUsedInRow(int[][] grid, int i, int num) {
		for(int j=0; j<9; j++) {
		if(grid[i][j] == num) {
			return false;
			}
		}
		return true;
	}	
	
	//checks if num is unused in col j
	static boolean unUsedInCol(int[][] grid, int j, int num) {
		for(int i=0; i<9; i++) {
		if(grid[i][j] == num) {
			return false;
			}
		}
		return true;
	}
	
	//checks if num is safe in (i, j)
	static boolean checkIfSafe(int[][] grid, int i, int j, int num){
		return(unUsedInRow(grid, i, num) && unUsedInCol(grid, j, num) && unUsedInChunk(grid, i-i%3, j-j%3, num));
	}
	
	//fill diagonal chuncks
	static void fillDiagonal(int[][] grid) {
        for (int i=0; i<9; i=i+3) {
            
            fillChunk(grid, i, i);
        }
    }
	
	//fill remaining chunks
	 static boolean fillRemaining(int[][] grid, int i, int j) {
	        
	        if (i==9) {
	            return true;
	        }
 
	        if (j == 9) {
	            return fillRemaining(grid, i + 1, 0);
	        }

	        if (grid[i][j] != 0) {
	            return fillRemaining(grid, i, j + 1);
	        }
	        
	        for (int num = 1; num <= 9; num++) {
	        	
	            if (checkIfSafe(grid, i, j, num)) {
	                grid[i][j] = num;
	              //System.out.print(" x"+ num+"x ");
	                if (fillRemaining(grid, i, j + 1)) {
	                    return true;
	                }
	                grid[i][j] = 0;
	            }
	        }
	        return false;
	    }
	 
	// Remove K digits randomly from the grid
	static void removeKDigits(int[][] grid, int k) {
		 	Random rand = new Random();
	        while (k > 0) {   
	        	int cellId = rand.nextInt(81);
	        	int i = cellId / 9;
	            int j = cellId % 9;
	            if (grid[i][j] != 0) {
	            	grid[i][j] = 0; 
	                k--;
	            }
	        }
		}

	 	// Generate a Sudoku grid with K empty cells
	 	static int[][] sudokuGenerator(int k) {
	    	int[][] grid = new int[9][9];
	    	fillDiagonal(grid);
	    	fillRemaining(grid, 0, 0);
	    	removeKDigits(grid, k);
	    	return grid;
	    }
	 	
}

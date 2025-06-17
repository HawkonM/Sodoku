package sodoku;

public class Score {
	static int[][] puz = Window.puzzle;
	static int[][] sol = Window.solution
			;
	//calulates score needs int s score and removed cells k, returns long
	static long score(int s, int k) {
		int cK = chunksCompleted(puz, sol);
		System.out.println("completed chuncks "+cK);
		return s*k+cK;
		
	}
	
	//counts trough how many chunks are completed
	static int chunksCompleted(int[][] puz, int[][] sol) {
	    int correctChunks = 0;

	    for (int chunk = 0; chunk < 9; chunk++) {
	        int startRow = (chunk / 3) * 3;
	        int startCol = (chunk % 3) * 3;
	        boolean isCorrect = true;

	        for (int row = startRow; row < startRow + 3; row++) {
	            for (int col = startCol; col < startCol + 3; col++) {
	                if (puz[row][col] == 0 || puz[row][col] != sol[row][col]) {
	                    isCorrect = false;
	                    break;
	                }
	            }
	            if (!isCorrect) break;
	        }

	        if (isCorrect) {
	            correctChunks++;
	        }
	    }

	    return correctChunks;
	}
}

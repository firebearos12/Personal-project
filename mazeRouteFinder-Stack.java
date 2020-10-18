import java.util.*;

// offset

/**
 * Maze Class
 * 
 */

class Maze {

	private int[][] maze;	// 2 dim array for maze
	private int[][] mark;	// 2 dim array for visit marking

	public Maze(int m, int p) {
		maze = new int[m + 2][p + 2];
		mark = new int[m + 2][p + 2];
		for(int i = 0; i < m + 2; i++)
			for(int j = 0; j < p + 2; j++) {
				maze[i][j] = 1;
				mark[i][j] = 0;
			}
	}

	// create the maze structure
	public void SetWall(int i, int j, int val) {
		maze[i][j] = val;
	}


	public void Path(int m, int p) { //y,x
		int move [][] = {{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1}}; //y , x
		int tempx;
		int tempy;		
		int x = 1;
		int y = 1;
		int mazeStuck = 0;
		int markStuck = 0;
		Stack<Integer> pathMemX = new Stack<>();
		Stack<Integer> pathMemY = new Stack<>();
		pathMemX.push(1);
		pathMemY.push(1);
		while(!(pathMemX.peek() == p && pathMemY.peek() == m)){
				markStuck = 0;
				mazeStuck = 0;
			for(int direction = 0; direction < 8; direction++){
				y = pathMemY.peek();
				x = pathMemX.peek();				
				x += move[direction][1];
				y += move[direction][0];
				if(maze[y][x] == 1){// 메이즈가 막혀있으면
					mazeStuck++;
				}
				else if(mark[y][x] == 1){ //간적있음
					markStuck++;
					
				}
				else{
					mark[pathMemY.peek()][pathMemX.peek()] = 1;
					pathMemX.push(x);
					pathMemY.push(y);
					break;
				}
			}
			if (mazeStuck == 8){
				System.out.println("No path in the maze.");
				return;
			}
			else if(mazeStuck + markStuck == 8 ){
				tempx = pathMemX.pop();
				tempy = pathMemY.pop();
				maze[tempy][tempx] = 1;
			}

		}
		if(pathMemX.peek() == p && pathMemY.peek() == m){
			int[] printArrayX = new int[pathMemX.size()];
			int[] printArrayY = new int[pathMemY.size()];
			for(int i = pathMemX.size() - 1; i >=0 ; i--){
				printArrayX[i] = pathMemX.pop();
				printArrayY[i] = pathMemY.pop();
			}
			for(int j = 0; j < printArrayX.length; j++){
				System.out.println("(" +Integer.toString(printArrayY[j]) +","+Integer.toString(printArrayX[j]) +")");
			}
		}

	}

}; 

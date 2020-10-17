import java.util.*;

// offset

/**
 * Maze Class
 * 
 */

class Maze {
	Stack<Integer> routeX = new Stack<>();
	Stack<Integer> routeY = new Stack<>();
	Stack<Integer> preX = new Stack<>();
	Stack<Integer> preY = new Stack<>();
	boolean continueWhile = true;

	private int[][] maze;	// 2 dim array for maze
	private int[][] mark;	// 2 dim array for visit marking
	
	public Maze(int m, int p) {
		maze = new int[m + 2][p + 2];
		mark = new int[m + 2][p + 2];
		routeX.push(1);
		routeY.push(1);
		for(int i = 0; i < m + 2; i++) {
			for(int j = 0; j < p + 2; j++) {
				maze[i][j] = 1;
				mark[i][j] = 0;
			}
		}
	}

	// create the maze structure
	public void SetWall(int i, int j, int val) {
		maze[i][j] = val;
	}
	
	public void canMoveQnA() {
		int[] azimuth = {0,0,0,0,0,0,0,0};
		int cnt = 0;
		int cntPop = 0;
		//초기화
		for(int i = 0; i < 8 ; i ++) {
			azimuth[i] = 0;
		}
		Stack<Integer> rX = routeX;
		Stack<Integer> rY = routeY;
		Stack<Integer> prX = preX;
		Stack<Integer> prY = preY;
		switch(direction) {
			case 12: // 0,-1
				if(maze[rY.peek() - 1][rX.peek()] == 1)// 벽에 가로 막힘
					azimuth[4] = -1;
				else if((rY.peek() - 1) == prY.peek() && (rX.peek() == prX.peek())) // 왔던 위치하고 겹침
					azimuth[4] = -2;
				else
					move(0, -1);
				break;
				
			case 2: // 1, -1
				if(maze[rY.peek() - 1][rX.peek() + 1] == 1)// 벽에 가로 막힘
					azimuth[5] = -1;
				else if((rY.peek() - 1) == prY.peek() && (rX.peek() + 1) == prX.peek()) // 왔던 위치하고 겹침
					azimuth[5] = -2;
				else
					move(1, -1);
				break;
				
				
			case 3: //1,0
				if(maze[rY.peek()][rX.peek() + 1] == 1)// 벽에 가로 막힘
					azimuth[6] = -1;
				else if((rY.peek()) == prY.peek() && (rX.peek() + 1) == prX.peek()) // 왔던 위치하고 겹침
					azimuth[6] = -2;
				else
					move(1, 0);
				break;
				
				
			case 4: // 1,1
				if(maze[rY.peek() + 1][rX.peek() + 1] == 1)// 벽에 가로 막힘
					azimuth[7] = -1;
				else if((rY.peek() + 1) == prY.peek() && (rX.peek() + 1) == prX.peek()) // 왔던 위치하고 겹침
					azimuth[7] = -2;
				else
					move(1, 1);
				break;
				
				
			case 6: //0,1
				if(maze[rY.peek() + 1][rX.peek()] == 1)// 벽에 가로 막힘
					azimuth[0] = -1;
				else if((rY.peek() + 1) == prY.peek() && (rX.peek()) == prX.peek()) // 왔던 위치하고 겹침
					azimuth[0] = -2;
				else
					move(0, 1);
				break;
				
				
			case 8: //-1,1
				if(maze[rY.peek() + 1][rX.peek() - 1] == 1)// 벽에 가로 막힘
					azimuth[1] = -1;
				else if((rY.peek() + 1) == prY.peek() && (rX.peek() - 1) == prX.peek()) // 왔던 위치하고 겹침
					azimuth[1] = -2;
				else
					move(-1, 1);
				break;
				
				
			case 9: //-1,0
				if(maze[rY.peek()][rX.peek() - 1] == 1)// 벽에 가로 막힘
					azimuth[2] = -1;
				else if((rY.peek()) == prY.peek() && (rX.peek() - 1) == prX.peek()) // 왔던 위치하고 겹침
					azimuth[2] = -2;
				else
					move(-1, 0);
				break;
				
				
			case 10://-1,-1
				if(maze[rY.peek() - 1][rX.peek() - 1] == 1)// 벽에 가로 막힘
					azimuth[3] = -1;
				else if((rY.peek() - 1) == prY.peek() && (rX.peek() - 1) == prX.peek()) // 왔던 위치하고 겹침
					azimuth[3] = -2;
				else
					move(-1, -1);
				break;				
		}
		
		for(int i = 0 ; i < 8; i ++) {
			if(azimuth[i] == -1)
				cnt++;
			else if(azimuth[i] == -2)
				cntPop++;
		}
		if(cnt == 8) // 아예 다 막힘
			endPro();
		
		else if(cnt == 7 && cntPop == 1)// 진행은 못하지만 pop으로 전 상태로 돌아갈 수 있음
			moveBackWard();
	}
	
	public void moveBackWard() {
		maze[routeX.pop()][routeY.pop()] = 1;
		routeX.pop();
		routeY.pop();
		preX.pop();
		preY.pop();
	}
	
	public void move(int moveToThisX, int moveToThisY) {
		preX.push(routeX.peek());
		preY.push(routeY.peek());
		routeX.push(routeX.peek() + moveToThisX);
		routeY.push(routeY.peek() + moveToThisY);
	}
	
	public void endPro() {
		System.out.println("No path in the maze.");
		continueWhile = false;
	}
	
	public void Path(int m, int p) {
		while(continueWhile) {
			canMoveQnA();
		}
	}

};

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
	int cnt = 0;
	int cntPop = 0;
	int direction = 0;

	private int[][] maze;	// 2 dim array for maze
	private int[][] mark;	// 2 dim array for visit marking
	
	public Maze(int m, int p) {
		maze = new int[m + 2][p + 2];
		mark = new int[m + 2][p + 2];
		routeX.push(1);
		routeY.push(1);
		preX.push(1);
		preY.push(1);
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
	
	public void canMoveQnA(int m, int p) {
		int m1 = m;
		int p2 = p;
		
		//초기화
		direction = 0;
		Stack<Integer> rX = routeX;
		Stack<Integer> rY = routeY;
		Stack<Integer> prX = preX;
		Stack<Integer> prY = preY;
		while(direction < 8) {
			switch(direction) {
			case 4: // 0,-1
				if(maze[rY.peek() - 1][rX.peek()] == 1) {// 벽에 가로 막힘
					cnt++;
					direction++;
				}
				else if((rY.peek() - 1) == prY.peek() && (rX.peek() == prX.peek())) { // 왔던 위치하고 겹침
					cntPop++;
					direction++;
				}
				else
					move(0, -1);
				break;
				
			case 5: // 1, -1
				if(maze[rY.peek() - 1][rX.peek() + 1] == 1) {// 벽에 가로 막힘
					cnt++;					
					direction++;
				}
				else if((rY.peek() - 1) == prY.peek() && (rX.peek() + 1) == prX.peek()) { // 왔던 위치하고 겹침
					cntPop++;
					direction++;
				}
				else
					move(1, -1);
				break;
				
				
			case 6: //1,0
				if(maze[rY.peek()][rX.peek() + 1] == 1) {// 벽에 가로 막힘
					cnt++;
					direction++;
				}
				else if((rY.peek()) == prY.peek() && (rX.peek() + 1) == prX.peek()) { // 왔던 위치하고 겹침
					cntPop++;
					direction++;
				}
				else
					move(1, 0);
				break;
				
				
			case 7: // 1,1
				if(maze[rY.peek() + 1][rX.peek() + 1] == 1) {// 벽에 가로 막힘
					cnt++;
					direction++;
				}
				else if((rY.peek() + 1) == prY.peek() && (rX.peek() + 1) == prX.peek()) { // 왔던 위치하고 겹침
					cntPop++;
					direction++;
				}
				else
					move(1, 1);
				break;
				
				
			case 0: //0,1
				if(maze[rY.peek() + 1][rX.peek()] == 1) {// 벽에 가로 막힘
					cnt++;
					direction++;
				}
				else if((rY.peek() + 1) == prY.peek() && (rX.peek()) == prX.peek()) { // 왔던 위치하고 겹침
					cntPop++;
					direction++;
				}
				else
					move(0, 1);
				break;
				
				
			case 1: //-1,1
				if(maze[rY.peek() + 1][rX.peek() - 1] == 1) {// 벽에 가로 막힘
					cnt++;
					direction++;
				}
				else if((rY.peek() + 1) == prY.peek() && (rX.peek() - 1) == prX.peek()) { // 왔던 위치하고 겹침
					cntPop++;
					direction++;
				}
				else
					move(-1, 1);
				break;
				
				
			case 2: //-1,0
				if(maze[rY.peek()][rX.peek() - 1] == 1) {// 벽에 가로 막힘
					cnt++;
					direction++;
				}
				else if((rY.peek()) == prY.peek() && (rX.peek() - 1) == prX.peek()) { // 왔던 위치하고 겹침
					cntPop++;
					direction++;
				}
				else
					move(-1, 0);
				break;
				
				
			case 3://-1,-1
				if(maze[rY.peek() - 1][rX.peek() - 1] == 1) {// 벽에 가로 막힘
					cnt++;
					direction++;
				}
				else if((rY.peek() - 1) == prY.peek() && (rX.peek() - 1) == prX.peek()) { // 왔던 위치하고 겹침
					cntPop++;
					direction++;
				}
				else
					move(-1, -1);
				break;				
			}
		System.out.println("cnt : " + Integer.toString(cnt)+"cnt : " + Integer.toString(cntPop) + "  dircetion : " + Integer.toString(direction));		}
		if(cnt == 8) // 아예 다 막힘
			endPro(m1,p2);
		
		else if(cnt == 7 && cntPop == 1)// 진행은 못하지만 pop으로 전 상태로 돌아갈 수 있음
			moveBackWard();
	}
	
	public void moveBackWard() {
	System.out.println("뒤로 감" + Integer.toString(routeX.peek()) + ',' + Integer.toString(routeY.peek()) + "이쪽으로 감 " + Integer.toString(preX.peek()) + ',' + Integer.toString(preY.peek()));
		maze[routeX.pop()][routeY.pop()] = 1;
		preX.pop();
		preY.pop();
	}
	
	public void move(int moveToThisX, int moveToThisY) {
		
		preX.push(routeX.peek());
		preY.push(routeY.peek());
		routeX.push(routeX.peek() + moveToThisX);
		routeY.push(routeY.peek() + moveToThisY);
	System.out.println("현재 위치" + Integer.toString(routeX.peek()) + ',' + Integer.toString(routeY.peek()) + "     전 위치" + Integer.toString(preX.peek()) + ',' + Integer.toString(preY.peek()));
	}
	
	public void endPro(int m , int p) {
		continueWhile = false;
		try{
			while(true){
				System.out.println('(' + Integer.toString(routeX.pop()) +',' + Integer.toString(routeY.pop()) + ')');
			}
		}
		catch (Exception e){
			continueWhile = false;
		}
	}
	
	public void Path(int m, int p) {
		while(continueWhile) {
			direction = 0;
			cnt = 0;
			cntPop = 0;
			canMoveQnA(m,p);
		}
	}

};

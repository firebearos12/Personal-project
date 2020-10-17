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
				maze[i][j] = 1; // 외각에 이미 벽을 세움
				mark[i][j] = 0;
			}
	}

	// create the maze structure
	public void SetWall(int i, int j, int val) {
		maze[i][j] = val;
	}
	
	public void Path(int m, int p) {
		Stack<Integer> routeX = new Stack<>();
		Stack<Integer> routeY = new Stack<>();
		Stack<Integer> preX = new Stack<>();
		Stack<Integer> preY = new Stack<>();
		int presentDirection = 0;
		boolean isMove = false;
		routeX.push(1);
		routeY.push(1);
		int[] azimuth = {6,8,9,10,12,2,3,4};
		//현재 위치는 1,1
		//이동
		while(!(routeX.peek() == m + 1 && routeY.peek() == p + 1)) {
			while(presentDirection < 8) {

				System.out.println(routeX.peek());
				System.out.println(routeY.peek());

				presentDirection = 0;
				switch(azimuth[presentDirection]) {
					case 6: // S [0,1]
						if(maze[routeY.peek() + 1][routeX.peek()] != 1 && (routeY.peek() + 1 != preY.peek())) {
							preX.push(routeX.peek());
							preY.push(routeY.peek());
							routeX.push(routeX.peek());
							routeY.push(routeY.peek() + 1);
							isMove = true;
						}
						else
							presentDirection++;
						break;
					case 8: //SW [-1,1]
						if(maze[routeY.peek() + 1][routeX.peek() - 1] != 1 && (routeY.peek() + 1 != preY.peek()) && (routeX.peek() - 1 != preX.peek())) {
							preX.push(routeX.peek());
							preY.push(routeY.peek());
							routeX.push(routeX.peek() - 1);
							routeY.push(routeY.peek() + 1);
							isMove = true;
						}
						else
							presentDirection++;
						break;
					case 9: // W [-1,0] x,y
						if(maze[routeY.peek()][routeX.peek() - 1] != 1 && (routeX.peek() - 1 != preX.peek())) {
							preX.push(routeX.peek());
							preY.push(routeY.peek());
							routeX.push(routeX.peek() - 1);
							routeY.push(routeY.peek());
							isMove = true;
						}
						else
							presentDirection++;
						break;
					case 10: // NW [-1,-1]
						if(maze[routeY.peek() - 1][routeX.peek() - 1] != 1 && (routeY.peek() - 1 != preY.peek()) && (routeX.peek() - 1 != preX.peek())) {
							preX.push(routeX.peek());
							preY.push(routeY.peek());
							routeX.push(routeX.peek() - 1);
							routeY.push(routeY.peek() - 1);
							isMove = true;
						}
						else
							presentDirection++;
						break;
					case 12: // N [0,-1]
						if(maze[routeY.peek() - 1][routeX.peek()] != 1 && (routeY.peek() - 1 != preY.peek())) {
							preX.push(routeX.peek());
							preY.push(routeY.peek());
							routeX.push(routeX.peek());
							routeY.push(routeY.peek() - 1);
							isMove = true;
						}
						else
							presentDirection++;
						break;
					case 2: // NE [1,-1]
						if(maze[routeY.peek() - 1][routeX.peek() + 1] != 1 && (routeY.peek() - 1 != preY.peek()) && (routeX.peek() + 1 != preX.peek())) {
							preX.push(routeX.peek());
							preY.push(routeY.peek());
							routeX.push(routeX.peek() + 1);
							routeY.push(routeY.peek() - 1);
							isMove = true;
						}
						else
							presentDirection++;
						break;
					case 3: //E [1,0]
						if(maze[routeY.peek()][routeX.peek() + 1] != 1 && (routeX.peek() + 1 != preX.peek())) {
							preX.push(routeX.peek());
							preY.push(routeY.peek());
							routeX.push(routeX.peek() + 1);
							routeY.push(routeY.peek());
							isMove = true;
						}
						else
							presentDirection++;
						break;
					case 4: // SE [1,1]
						if(maze[routeY.peek() + 1][routeX.peek() + 1] != 1 && (routeY.peek() + 1 != preY.peek()) && (routeX.peek() + 1 != preX.peek())) {
							preX.push(routeX.peek());
							preY.push(routeY.peek());
							routeX.push(routeX.peek() + 1);
							routeY.push(routeY.peek() + 1);
							isMove = true;
						}//연산자 우선 순위?
						else
							presentDirection++;
						break;
				}//방향 찾고 이동 완료// 스위치 종료
			}// 이동 끝
			if(!isMove) {
				// 못 움직임
				preX.pop();
				preY.pop();
				maze[routeY.pop()][routeX.pop()] = 1;				
			}
		}
		
		
	}

}; 

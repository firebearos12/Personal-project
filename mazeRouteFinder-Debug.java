import java.util.*;

// offset

/**
 * Maze Class
 * 
 */

class Maze {
	Stack<Integer> rX = new Stack<>();
	Stack<Integer> rY = new Stack<>();
	
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
	
	public void move(int moveX, int moveY) {
		System.out.println("상태 : 이동 완료    (" + Integer.toString(rX.peek()) + ',' + Integer.toString(rY.peek()) +")  ---->  ("+Integer.toString(rX.peek()+moveX)+','+ Integer.toString(rY.peek()+moveY)+')');
		rX.push(rX.peek() + moveX);
		rY.push(rY.peek() + moveY);
	}
	
	public boolean canMoveQ(int moveX, int moveY) {
		int[] rXHistory = new int[rX.size()];
		int[] rYHistory = new int[rY.size()];
		int nextX = rX.peek() + moveX;
		int nextY = rY.peek() + moveY;
		boolean result = true;
		System.out.println("상태 : 탐색중    대상 : (" + Integer.toString(nextX) +',' + Integer.toString(nextY) +')');
		for(int i = rXHistory.length - 1; i >= 0 ; i --) {
			System.out.println("Stack ["+Integer.toString(i)+"] : " +Integer.toString(rX.peek()) +','+ Integer.toString(rY.peek()));
			rXHistory[i] = rX.pop();
			rYHistory[i] = rY.pop();
		}//스택 -> 배열
		for(int i = 0; i < rXHistory.length ; i ++) {
			rX.push(rXHistory[i]);
			rY.push(rYHistory[i]);
		}//스택 다시 복구
		for(int i = 0 ; i < rXHistory.length; i++){
			System.out.println("rHistory[][](" + Integer.toString(rXHistory[i]) +','+Integer.toString(rYHistory[i])+')');

		}
		if(maze[nextY][nextX] == 1){//벽으로 막힌 길
			System.out.println("벽에 막힘");
			result = false;
		}
		else {
			for(int i = 0; i < rXHistory.length; i ++) {
				if((rXHistory[i] == nextX) && (rYHistory[i] == nextY)){
					System.out.println("갔던 길");
					result = false;
				}
			}
		}
		return result;
	}
		//갈 곳이 한곳 밖에 없으면 pop을 하고 길을 막는다.
		//if direction == 7 -> maze[rY][rX] = 1 pop() direction = 0;
		//갈 곳이 여러군데면 메모리에서 간곳을 빼고 길을 찾는다.
		//if(nextX.in(rX) and nextY.in(rY))
		//direction ++;
		//else
		//move() direction = 0; -> move() : memX.push(rX)  rX = nextX rY = nextY
	public void Path(int m, int p) {
		rX.push(1);
		rY.push(1);
		boolean continueWhile = true;
		boolean continueFor = true;
		boolean doPrintQ = true;
		while(continueWhile) {
			continueFor = true;
			for(int direction = 0; direction <= 8 && continueFor; direction ++) {
				System.out.println(direction);
				switch(direction) {
					case 0:
						if(canMoveQ(0,1)) { // 이동
							move(0,1);
							continueFor = false;
						}
						break;
					case 1:
						if(canMoveQ(-1,1)) { // 이동
							move(-1,1);
							continueFor = false;
						}
						break;
					case 2:
						if(canMoveQ(-1,0)) { // 이동
							move(-1,0);
							continueFor = false;
						}
						break;
					case 3:
						if(canMoveQ(-1,-1)) { // 이동
							move(-1,-1);
							continueFor = false;
						}
						break;
					case 4:
						if(canMoveQ(0,-1)) { // 이동
							move(0,-1);
							continueFor = false;
						}
						break;
					case 5:
						if(canMoveQ(1,-1)) { // 이동
							move(1,-1);
							continueFor = false;
						}
						break;
					case 6:
						if(canMoveQ(1,0)) { // 이동
							move(1,0);
							continueFor = false;
						}
						break;
					case 7:
						if(canMoveQ(1,1)) { // 이동
							move(1,1);
							continueFor = false;
						}
						break;
					case 8: // 막다른 길
						try {
							maze[rY.peek()][rX.peek()] = 1;
							System.out.println("상태 : 되돌아감");
							rX.pop();
							rY.pop();
							System.out.println("상태 : " + Integer.toString(rX.peek()) + ',' + Integer.toString(rY.peek()) );
							continueFor = false;
						}
						catch (Exception e) { // 처음 자리로 돌아왔는데 길이 다 막혀있는 경우
							System.out.println("No path in the maze.");
							continueWhile = false;
							doPrintQ = false;
						}
				}
			}//for문 종료 한칸 이동함
			if(rX.peek() == p && rY.peek() == m)//목적지에 도달 했는가?
				continueWhile = false;
		}//메이즈 찾기 완료
		if(doPrintQ) {
			int[] xPos = new int[rX.size()];
			int[] yPos = new int[rY.size()];
			for(int i = rX.size() - 1; i >= 0; i --) {
				xPos[i] = rX.pop();
				yPos[i] = rY.pop();
			}
			for(int i = 0; i < rX.size(); i++) {
				System.out.println('(' + Integer.toString(xPos[i]) + ',' + Integer.toString(yPos[i]) + ')');
			}
		}

	}

}; 
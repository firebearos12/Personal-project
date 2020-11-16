import java.util.*;

// ---------- Graph class -------------

class Graph {
    int numofnodes;  // the number of nodes in the graph
    private int[][] AdjMatrix; // Adjacency matrix
    private int[] visited; // mark the visitied node


    Graph() {
        // Graph constructor. 
        numofnodes = 0;
    }

    void Init(int n) {
        numofnodes = n;
        // now create 2 dimensional array of numofnodes * numofnodes
        AdjMatrix = new int[numofnodes][numofnodes];
        for(int i = 0; i < numofnodes; i++) {
            // initialize all entries to 0
            for(int j = 0; j < numofnodes; j++)
                AdjMatrix[i][j] = 0;  // initialize the adjacency matrix
        }
        
        visited = new int[numofnodes];
        for(int i = 0; i < numofnodes; i++) {
        	visited[i] = 0;
        }
        
    }


	void Edge(int n1, int n2) { 
		AdjMatrix[n1][n2] = 1;
		AdjMatrix[n2][n1] = 1;
	}

	void Bfs(int v) { 
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		int curr = 0;
		while(!q.isEmpty()) {
			curr = q.poll();
			if(visited[curr] == 0) {
				System.out.print(curr);
				System.out.print('-');
				visited[curr] = 1;
			}
			for(int i = 0; i < AdjMatrix.length; i ++) {
				if(AdjMatrix[curr][i] == 1) {
					q.add(i);
				}
			}
		}
	}


}

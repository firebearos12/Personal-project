import java.util.*;


class Graph {
	int numofnodes;  // the number of nodes in the graph
	private int[][] CostAdj; // Adjacency matrix
	private int[] dist; // dist array
	private int[] p; // p array

	final int LargeCost = 999999;

	Graph() { 
		// Graph constructor. 
		numofnodes = 0;
	}


	void Init(int n) { 
		numofnodes = n;
		// now create 2 dimensional array of numofnodes * numofnodes
		CostAdj = new int[numofnodes][numofnodes];
		dist = new int[numofnodes];
		p = new int[numofnodes];

		for(int i = 0; i < numofnodes; i++) {
			// initialize all entries to 0
			for(int j = 0; j < numofnodes; j++)
				CostAdj[i][j] = LargeCost;  // initialize the adjacency matrix
			CostAdj[i][i] = 0;
		}
	}
	public String toString() { 
		String str;
		int i = 0;
		str = "Dist : ";
		for(i = 0; i < numofnodes; i++)
			str +=  dist[i] + " ";
		str += "\n";
	
		str += "P    : ";
		for( i = 0; i < numofnodes; i++)
			str += p[i] + " ";
		str += "\n";
	
		// show the paths to all the destinations
		for( i = 0; i < numofnodes; i++) {
			str += "Path => " + i + " : ";
			str += OutPath(i);	
			str += "\n";
		}
		return str;
	}



	void Edge(int v1, int v2, int cost) {
		CostAdj[v1][v2] = cost;
	}


	String OutPath(int i) { 
		String str = "";
		int start = 0;
		while(true) {
			if(i == -1) {
				break;
			}
			else {
				str += Integer.toString(i);
				str += " ";
				i = p[i];
			}
		}
		StringBuffer temp = new StringBuffer();
		temp.append(str);
		str = temp.reverse().toString();
		return str;
	}

	void BellmanFord(int v) {
		int temp;
		int prev = -1;
		for(int i = 0 ; i < dist.length; i ++) {
			if(i == v) {
				dist[i] = 0;
			}
			else {
				dist[i] = LargeCost;
			}
			p[i] = -1;
		}
		// NEED TO IMPLEMENT
		for(int i = 0 ; i < numofnodes; i ++) {
			for(int j = 0; j < numofnodes; j ++) {
				temp = dist[j];
				prev = p[j];
				for(int k = 0; k < numofnodes; k ++) {
					if(CostAdj[k][j] < 100 && j != k) {
						if(temp > dist[k] + CostAdj[k][j]) {
							temp = dist[k] + CostAdj[k][j];
							prev = k;
						}
					}
				}
				dist[j] = temp;
				p[j] = prev;
			}
		}


	}
}

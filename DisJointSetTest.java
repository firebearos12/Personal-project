import java.util.*;


class DisjointSets {
	int numofelements;  // the total number of elements in all the disjoint sets
	private int[] parent; // maintains the parent pointer of each element in the disjoint sets
	private int[] weight; // maintains the weight of each set


	DisjointSets() { 
		// DisjointSets constructor. 
	}

	public String toString() { // Show all the element in sequence
		String str = new String();
	    // Show the array elements in parent[]

		str = "parent[]: - ";
		for(int i = 1; i <= numofelements; i++)
			str += parent[i] + " ";
		return str;
	}

	void InitSet(int n) { 
		// Initialize the array parent[] and weight[]
		numofelements = n;
		parent = new int[n+1];
		weight = new int[n+1];
		for(int i = 0; i < n+1; i++) {
			parent[i] = 0;
			weight[i] = 1;
		}
	}

	boolean Union(int i, int j) { 
	// Union the set that contains i and the set that contains j
	// the set that has larger weight is the new root of the unioned set
	// when the weights are the same, choose the root of smaller value

		int iRoot = SimpleFind(i);
		int jRoot = SimpleFind(j);
		if(iRoot == jRoot)
			return false;

		if(weight[iRoot] < weight[jRoot]) {
			parent[iRoot] = jRoot;
			weight[jRoot] += weight[iRoot];
		}
		else if(weight[iRoot] == weight[jRoot]) {
			if(iRoot > jRoot) {
				parent[jRoot] = iRoot;
				weight[iRoot] += weight[jRoot];
			}
			else {
				parent[iRoot] = jRoot;
				weight[jRoot] += weight[iRoot];
			}
		}
		else {
			parent[jRoot] = iRoot;
			weight[iRoot] += weight[jRoot];
		}
		// first find the root of i and j

		return true;
	}

	int SimpleFind(int i) { 
	// return the root node that contains the element i

		int root = i;
		while(parent[root] > 0) {
			root = parent[root];
		}
		return root;
	}
}

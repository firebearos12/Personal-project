public class BST {
	
	Node dummyroot = null;
	
	class Node{
		Node left;
		Node right;
		Node parent;
		int key;
		
		public Node(int a) {
			this.key = a;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
		
		public Node() {
			this.left = null;
			this.right = null;
			this.parent = null;
		}
	}
	
	Node getNode() {
		return new Node();
	}
	public BST() {
		this.dummyroot = new Node(-100);
	}
	void addNodeT(int input){
		if(dummyroot.right == null) {
			dummyroot.right = new Node(input);
			dummyroot.right.parent = dummyroot;
		}
		else {
			Node currentParent = new Node(-1);
			Node currentNode = dummyroot.right;
			while(true) {
				if(currentNode.key == input) {
					return;
				}
				else if(currentNode.key < input) {
					if(currentNode.right == null) {
						currentParent = currentNode;
						currentNode.right = getNode();
						currentNode.right.key = input;
						currentNode.right.parent = currentParent;
						printState();
						return;
					}
					else
						currentNode = currentNode.right;
				}
				else {
					if(currentNode.left == null) {
						currentParent = currentNode;
						currentNode.left = getNode();
						currentNode.left.key = input;
						currentNode.left.parent = currentParent;
						printState();
						return;
					}
					else
						currentNode= currentNode.left;
				}
			}
		}
		printState();
	}
	
	void inorder(Node root) {
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(root.key + " ");
		inorder(root.right);
	}
	int bigNumrtn(int a, int b) {
		int result = 0;
		if (a > b) {
			result = a;
		}
		else
			result = b;
		return result;
	}
	
	int height(Node tree) {
		if(tree == null)
			return 0;
		return bigNumrtn(height(tree.left),height(tree.right)) + 1;
	}
	
	int noNodes(Node tree) {
		int sum = 0;
		if(tree == null) {
			return 0;
		}
		sum += noNodes(tree.left);
		sum += noNodes(tree.right);
		sum++;
		return sum;
	}
	Node minNode(Node tree) {
		Node min = tree;
		while(min.left != null) {
			min = min.left;
		}
		return min;
	}
	
	Node maxNode(Node tree) {
		Node max = tree;
		while(max.right != null) {
			max = max.right;
		}
		return max;
	}
	void deleteNodeT(int deletekey) {
		Node searchNode = dummyroot.right;
		while(true) {
			if(deletekey == searchNode.key) {
				if(searchNode.right == null && searchNode.left == null) { //말단
					if(searchNode.parent.key < deletekey) {
						searchNode.parent.right = searchNode.left;
					}
					else
						searchNode.parent.left = searchNode.right;
					printState();
					return;
				}
				else if(searchNode.right == null){
					if(deletekey > searchNode.parent.key) {
						searchNode.parent.right = searchNode.left;
						searchNode.left.parent = searchNode.parent;
					}
					else {
						searchNode.parent.left = searchNode.left;
						searchNode.left.parent = searchNode.parent;
					}
					printState();
					return;
				}
				else if(searchNode.left == null) {
					if(deletekey > searchNode.parent.key) {
						searchNode.parent.right = searchNode.right;
						searchNode.right.parent = searchNode.parent;
					}
					else {
						searchNode.parent.left = searchNode.right;
						searchNode.right.parent = searchNode.parent;
					}
					printState();
					return;
				}
				else { // 차수 2
					if(searchNode.parent.key < deletekey) {
						if(height(searchNode.left) > height(searchNode.right)) {
							Node tempNode = maxNode(searchNode.left);
							tempNode.left = searchNode.left.left;
							tempNode.right = searchNode.right;
							tempNode.parent = searchNode.parent;
							searchNode.parent.right = tempNode;
							//deleteMaxNode
						}
					
						else if(height(searchNode.left) < height(searchNode.right)) {
							Node tempNode = minNode(searchNode.right);
							tempNode.left = searchNode.left;
							tempNode.right = searchNode.right.right;
							tempNode.parent = searchNode.parent;
							searchNode.parent.right = tempNode;
							//deleteMinNode
						}
						else if(noNodes(searchNode.left) >= noNodes(searchNode.right)) {
							Node tempNode = maxNode(searchNode.left);
							tempNode.left = searchNode.left.left;
							tempNode.right = searchNode.right;
							tempNode.parent = searchNode.parent;
							searchNode.parent.right = tempNode;
						}
						else {
							Node tempNode = minNode(searchNode.right);
							tempNode.left = searchNode.left;
							tempNode.right = searchNode.right.right;
							tempNode.parent = searchNode.parent;
							searchNode.parent.right = tempNode;
						}
					}
					else {
						if(height(searchNode.left) > height(searchNode.right)) {
							Node tempNode = maxNode(searchNode.left);
							tempNode.left = searchNode.left.left;//
							tempNode.right = searchNode.right;
							tempNode.parent = searchNode.parent;
							searchNode.parent.left = tempNode;
							//deleteMaxNode
						}
					
						else if(height(searchNode.left) < height(searchNode.right)) {
							Node tempNode = minNode(searchNode.right);
							tempNode.left = searchNode.left;//
							tempNode.right = searchNode.right.right;
							tempNode.parent = searchNode.parent;
							searchNode.parent.left = tempNode;
							//deleteMinNode
						}
						else if(noNodes(searchNode.left) >= noNodes(searchNode.right)) {
							Node tempNode = maxNode(searchNode.left);
							tempNode.left = searchNode.left.left;//
							tempNode.right = searchNode.right;
							tempNode.parent = searchNode.parent;
							searchNode.parent.left = tempNode;
						}
						else {
							Node tempNode = minNode(searchNode.right);
							tempNode.left = searchNode.left;//
							tempNode.right = searchNode.right.right;
							tempNode.parent = searchNode.parent;
							searchNode.parent.left = tempNode;
						}
					}
					printState();
					return;
					
				}
			}// deletekey == searchNode.key end
			else if(deletekey > searchNode.key){
				searchNode = searchNode.right;
			}
			else { // deltekey < searchNode.key
				searchNode = searchNode.left;
			}
		}
	}
	void printState() {
		System.out.print("current state : ");
		inorder(dummyroot.right);
		System.out.print("\n");
		System.out.print("number of Nodes : ");
		System.out.print(noNodes(dummyroot.right));
		System.out.print("\n");
		System.out.print("\n");
		System.out.print("\n");
	}
	public static void insertBST(BST inputTree, int value) {
		inputTree.addNodeT(value);
	}
	public static void deleteBST(BST inputTree, int value) {
		inputTree.deleteNodeT(value);
	}
	public static void main(String[] args) {
		BST T = new BST();
		insertBST(T,25);
		insertBST(T,500);
		insertBST(T,100);
		insertBST(T,999);
		deleteBST(T,25);
		deleteBST(T,500);
		deleteBST(T,100);
		deleteBST(T,999);
		insertBST(T,25);
		insertBST(T,500);
		insertBST(T,100);
		insertBST(T,999);
		deleteBST(T,999);
		deleteBST(T,100);
		deleteBST(T,500);
		deleteBST(T,25);
		
	}

}
 

public class BST {
	
	Node root = null;
	
	class Node{
		Node root = null;
		Node left;
		Node right;
		Node parent;
		int key;
		
		public Node(int a) {
			this.key = a;
			this.left = null;
			this.right = null;
		}
		
		public Node() {
			this.left = null;
			this.right = null;
		}
	}
	
	void addNodeT(int input){
		if(root == null) {
			root = new Node(input);
		}
		else {
			Node currParent = new Node(-1);
			Node currentNode = root;
			while(currentNode.left != null && currentNode.right != null) {
				if(currentNode.key < input) {
					currentNode = currentNode.right;
				}
				else {
					currentNode= currentNode.left;
				}
			}
			currParent = currentNode;
			if(currentNode.key < input) {
				currentNode.right = new Node(input);
				currentNode.right.parent = currParent;
			}
			else {
				currentNode.left = new Node(input);
				currentNode.left.parent = currParent;
			}
		}
		System.out.print("current state : ");
		inorder(root);
		System.out.print("\n");
	}
	
	void inorder(Node root) {
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(root.key + " ");
		inorder(root.right);
	}
	bool noNode(Node root) {
		while()
	}
	int bigNumrtn(int a, int b) {
		if (a > b) {
			return a;
		}
		else
			return b;
	}
	
	int height(Node tree) {
		if(tree == null)
			return 0;
		return bigNumrtn(height(tree.left),height(tree.right)) + 1;
	}
	
	
	public static void addNode(BST inputTree, int value) {
		inputTree.addNodeT(value);
	}
	
	public static void main(String[] args) {
		BST test = new BST();
		BST real = new BST();
		addNode(real,5);
		addNode(real,4);
		addNode(real,6);
	}

}

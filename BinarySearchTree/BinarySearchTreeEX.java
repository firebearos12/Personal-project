import java.util.*;

// Name : 오승택
// Student ID : 20191625

@SuppressWarnings("unchecked")
class BST <T extends KeyValue> {
	class TreeNode <U extends KeyValue> {
		U data;	// storage for data : in HW 3, T will be Item
		TreeNode<U> leftChild;	// link to the left Child
		TreeNode<U> rightChild;	// link to the right Child

		// constructors come here
		TreeNode() {
			leftChild = rightChild = null;
		}
		TreeNode(U d) {
			// data is given
			data = d;
			// the leftChild and rightChild field are null
			leftChild = rightChild = null;
		}
	};

	TreeNode <T> root;
	BST() { 
		// BST constructor. 
		root = null;
	}

	void Show() {
		System.out.print( "PRE  Order : ");
		PreOrder(root);
		System.out.println("");
		System.out.print("IN   Order : ");
		InOrder(root);
		System.out.println("");
		System.out.print("POST Order : ");
		PostOrder(root);
		System.out.println("");
		System.out.print("Number of Nodes : ");
		System.out.println( Count(root));
		System.out.print("HEIGHT : ");
		System.out.println( Height(root));
		System.out.println("");
	}

	boolean  Insert(T item)  {
		// first search the key
		if(root == null) {
			root = new TreeNode<T>(item);
			return true;
		}
		
		TreeNode<T> ptr, parent;
		int itemKey = item.GetKey();
		boolean result = true;
		
		ptr = root;
		
		while(true) {
			if(itemKey < ptr.data.GetKey()) {
				parent = ptr;
				if(ptr.leftChild == null) {
					ptr.leftChild = new TreeNode<T>(item);
					return true;
				}
				ptr = ptr.leftChild;				
			}
			
			else if (itemKey == ptr.data.GetKey()) {
				parent = ptr;
				return false;
			}
			
			else { //itemkey > ptr.data.GetKey()
				parent = ptr;
				if(ptr.rightChild == null){
					ptr.rightChild = new TreeNode<T>(item);
					return true;
				}
				ptr = ptr.rightChild;
				
			}
		}
	}

	T Get(T item)  {
		// use the key field of item and find the node
		// do not use val field of item
		TreeNode<T> ptr;
		ptr = root;
		boolean result = true;
		while(result) {
			if(item.GetKey() == ptr.data.GetKey()) {
				result = false;
			}
			else if(item.GetKey() < ptr.data.GetKey()) {
				if(ptr.leftChild == null)
					return null;
				ptr = ptr.leftChild;
			}
			else {
				if(ptr.rightChild == null)
					return null;
				ptr = ptr.rightChild;
			}
		}
		
		return ptr.data;
	} 


	boolean Delete(T item)  {
		if(root == null)
			return false;	// non existing key
		TreeNode<T> searchNode = root;
		TreeNode<T> parentNode = root;
		if(Get(item) == null) {
			return false;
		}
		int targetKey = Get(item).GetKey();
		boolean doWhileQ = true;
		
		while(doWhileQ) {
			if(targetKey == searchNode.data.GetKey()) {
				doWhileQ = false;
			}
			else if (targetKey < searchNode.data.GetKey()) {
				if(searchNode.leftChild == null)
					return false;
				parentNode = searchNode;
				searchNode = searchNode.leftChild;
			}
			else {
				if(searchNode.rightChild == null)
					return false;
				parentNode = searchNode;
				searchNode = searchNode.rightChild;
			}
		}
		//degree == 0 (not rootNode)
		if(searchNode.rightChild == null && searchNode.leftChild == null) {
			if(parentNode == searchNode) {
				root = null;
			}
			else if(parentNode.data.GetKey() < targetKey) {
				parentNode.rightChild = null;
			}
			else {
				parentNode.leftChild = null;
			}
		}
		
		
		//degree == 1
		else if(searchNode.rightChild == null) {
			if(parentNode == searchNode) {
				root = root.leftChild;
				root.leftChild = null;
			}
			else if(parentNode.data.GetKey() < targetKey) {
				parentNode.rightChild = searchNode.leftChild;
			}
			else {
				parentNode.leftChild = searchNode.leftChild;
			}
		}
		
		else if(searchNode.leftChild == null) {
			if(parentNode == searchNode) {
				root = root.rightChild;
				root.rightChild = null;
			}
			else if(parentNode.data.GetKey() < targetKey) {
				parentNode.rightChild = searchNode.rightChild;
			}
			else {
				parentNode.leftChild = searchNode.rightChild;
			}
		}
		
		
		//degree == 2
		else {
			boolean doWhileQ2 = true;
			TreeNode<T> subTreeMax = searchNode.leftChild;
			TreeNode<T> subTreeMaxParent = searchNode;
			Item temp = new Item(subTreeMax.data.GetKey());
			while(doWhileQ2) {
				if(subTreeMax.rightChild == null) {
					doWhileQ2 = false;
				}
				else {
					subTreeMaxParent = subTreeMax;
					subTreeMax = subTreeMax.rightChild;
				}
			}
			if(searchNode == root) { // 지우는게 루트노드
				if(searchNode == subTreeMaxParent) {
					subTreeMax.rightChild = root.rightChild;
					root = subTreeMax;
				}
				else {
					if(subTreeMax.leftChild == null)
						subTreeMaxParent.rightChild = null;
					else
						subTreeMaxParent.rightChild = subTreeMax.leftChild;
					
					subTreeMax.rightChild = root.rightChild;
					subTreeMax.leftChild = root.leftChild;
					root = subTreeMax;
				}
			}
			
			else if(searchNode == subTreeMaxParent) {
				subTreeMax.rightChild = searchNode.rightChild;
				if(parentNode.data.GetKey() < subTreeMax.data.GetKey())
					parentNode.rightChild = subTreeMax;
				else
					parentNode.leftChild = subTreeMax;
			}
			
			else {
				if(subTreeMax.leftChild == null)
					subTreeMaxParent.rightChild = null;
				else
					subTreeMaxParent.rightChild = subTreeMax.leftChild;
				
				subTreeMax.rightChild = searchNode.rightChild;
				subTreeMax.leftChild = searchNode.leftChild;
				
				if(parentNode.data.GetKey() < subTreeMax.data.GetKey())
					parentNode.rightChild = subTreeMax;
				else
					parentNode.leftChild = subTreeMax;
			}
		}
		
		
		return true;
	}

	void  PreOrder(TreeNode<T> t)  {
		
		if(t == null) {
			return;
		}
		System.out.print('[' +  Integer.toString(t.data.GetKey()) + '(' + Integer.toString(t.data.GetValue()) + ")]");
		PreOrder(t.leftChild);
		PreOrder(t.rightChild);
	}

	void  InOrder(TreeNode<T> t)  {
		
		if(t == null) {
			return;
		}
		InOrder(t.leftChild);
		System.out.print('[' +  Integer.toString(t.data.GetKey()) + '(' + Integer.toString(t.data.GetValue()) + ")]");
		InOrder(t.rightChild);
		
	}

	void  PostOrder(TreeNode<T> t)  {
		
		if(t == null) {
			return;
		}
		PostOrder(t.leftChild);
		PostOrder(t.rightChild);
		System.out.print('[' +  Integer.toString(t.data.GetKey()) + '(' + Integer.toString(t.data.GetValue()) + ")]");
	}

	int  Count(TreeNode<T> t)  {
		if(t == null) {
			return 0;
		}
		return Count(t.leftChild) + Count(t.rightChild) + 1;
	}

	int  Height(TreeNode<T> t)  {
		if(t == null) {
			return 0;
		}
		if(Height(t.leftChild) < Height(t.rightChild)) {
			return Height(t.rightChild) + 1;
		}
		else
			return Height(t.leftChild) + 1;
	}
}
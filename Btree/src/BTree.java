/*
 * File Processing, 2020
 * BTree.java
 * implementation of B-tree
 */
import java.util.Stack;

public class BTree {
  BTNode root;
  
  class BTNode {
    int n;
    int K[];
    BTNode P[];
    
    /**
     * constructor
     * @param m: number of branch points in B-Tree
     */
    BTNode(int m) {
      this.n = 0;
      this.K = new int[m-1];
      this.P = new BTNode[m];
    }
    
    /**
     * insert inserts newKey.
     * @param m: number of branch points in B-Tree
     * @param newKey: a key to insert
     * @return root node of B-Tree
     */
    BTNode insert(int m, int newKey) {
    	if(root.K[0] == 0) {
    		root = new BTNode(m);
    		root.n = 1;
    		root.K[0] = newKey;
    		return root;
    	}
    	Stack<BTNode> stack = new Stack<>();
    	BTNode searchNode = root;
    	int cnt;
    	do {
    		cnt = 1;
    		while(cnt <= searchNode.n && newKey > searchNode.K[cnt-1]) {
    			cnt++;
    		}
    		
    		if(cnt <= searchNode.n && newKey == searchNode.K[cnt-1]) {
    			return root;
    		}
    		
    		stack.push(searchNode);
    		searchNode = searchNode.P[cnt - 1];
    		
    	}while(searchNode != null);
    	
    	searchNode = stack.pop();
    	boolean finish = false;
    	BTNode x = null; //left
    	BTNode y = null; //right
    	BTNode tempNode = new BTNode(m+1);
	
    	int insertKey = newKey;
    	int insertPos;
    	int currentPos;
    	BTNode newRoot = null;
    	do {
    		if(searchNode.n < m -1) {
    			currentPos = 1;
    			while(currentPos <= searchNode.n && insertKey > searchNode.K[currentPos-1]) {
    				currentPos++;
    			}
    			
    			insertPos = currentPos - 1;
    			for(int i = searchNode.n-1 ; i >= insertPos; i--) {
    				searchNode.K[i+1] = searchNode.K[i];
    			}
    			searchNode.K[insertPos] = insertKey;
    			
    			for(int i = searchNode.n; i >= insertPos; i--) {
    				searchNode.P[i+1] = searchNode.P[i];
    			}
    			searchNode.P[insertPos] = x;
    			searchNode.P[insertPos+1] = y;
    			searchNode.n++;
    			finish = true;
    		}
    		
    		
    		
    		else {
    			currentPos = 1;
    			while(currentPos <= searchNode.n && insertKey > searchNode.K[currentPos-1]) {
    				currentPos++;
    			}
    			insertPos = currentPos - 1;
    			
    			for(int i = 0; i < insertPos; i++) {
    				tempNode.K[i] = searchNode.K[i];
    				tempNode.P[i] = searchNode.P[i];
    			}
    			tempNode.K[insertPos] = insertKey;
    			tempNode.P[insertPos] = x;
    			tempNode.P[insertPos + 1] = y;
    			for(int i = insertPos + 1; i < searchNode.n + 1;i++) {
    				tempNode.K[i] = searchNode.K[i-1];
    				tempNode.P[i+1] = searchNode.P[i];
    			}//copy end
    			
    			
    			insertKey = tempNode.K[(int)(m/2)];
    			x = new BTNode(m);
    			y = new BTNode(m);
    			
    			
    			//set x,y
    			for(int i = 0; i < (int)(m/2); i++) {
    				x.K[i] = tempNode.K[i];
    				x.P[i] = tempNode.P[i];
    				x.n++;
    			}
    			x.P[(int)(m/2)] = tempNode.P[(int)(m/2)];
    			//set x end
    			
    			for(int i = (int)(m/2)+1; i < searchNode.n + 1; i++) {
    				y.K[i - (int)(m/2)-1] = tempNode.K[i];
    				y.P[i - (int)(m/2)-1] = tempNode.P[i];
    				y.n++;
    			}
    			y.P[searchNode.n + 1 - (int)(m/2) - 1] = tempNode.P[searchNode.n + 1];
    			//set y end
    			
    			if(stack.empty() == false) {
    				searchNode = stack.pop();
    			}
    			else {
    				newRoot = new BTNode(m);
    				newRoot.K[0] = insertKey;
    				newRoot.P[0] = x;
    				newRoot.P[1] = y;
    				newRoot.n++;
    				finish = true;
    			}
    			
    		}    		
    	}while(!finish);
    	
    	if(newRoot == null) {
    		return root;
    	}
    	else {
    		return newRoot;
    	}
    }
    
    /**
     * delete deletes oldKey.
     * @param m: number of branch points in B-Tree
     * @param oldKey: a key to delete
     * @return root node of B-Tree
     */
    BTNode delete(int m, int oldKey) {
      /* write your code here */
    	BTNode searchNode = root;
    	Stack<BTNode> stack  = new Stack<>();
    	int cnt;
    	do {
    		cnt = 1;
    		while(cnt <= searchNode.n && oldKey > searchNode.K[cnt-1]) {
    			cnt++;
    		}
    		
    		if(cnt <= searchNode.n && oldKey == searchNode.K[cnt -1]) {
    			break;
    		}
    		stack.push(searchNode);
    		searchNode = searchNode.P[cnt-1];
    	}while(searchNode != null);
    	
    	if(searchNode == null) {
    		return root;
    	}
    	
    	boolean inleaf = true;
    	for(int i = 0; i < searchNode.n+1;i++) {
    		if(searchNode.P[i] != null) {
    			inleaf = false;
    			break;
    		}
    	}
    	
    	BTNode internalNode = searchNode; //internalNode == node where oldKey exists
    	
    	int deletePos = -1; //random value
    	if(inleaf == false) {//oldKey -> not in leaf node
        	for(int i = 0; i < internalNode.n;i++) {
        		if(internalNode.K[i] == oldKey) {
        			deletePos = i; //found
        			break;
        		}
        	}
        	stack.push(searchNode);
        	searchNode = searchNode.P[deletePos+1];
        	do {
        		stack.push(searchNode);
        		searchNode = searchNode.P[0];//go to min value node
        	}while(searchNode != null);
    	}
    	
    	
    	int temp;
    	if(searchNode == null) {
    		searchNode = stack.pop();
    		temp = internalNode.K[deletePos];
    		internalNode.K[deletePos] = searchNode.K[0];
    		searchNode.K[0] = temp;
    	}//change value complete
    	
    	//delete changed Key
    	boolean finish = false;
    	int lsPos = 0;
    	int minSize = m / 2 + ((m % 2 == 0) ? 0 : 1) - 1;
    	for(int i = 0; i < searchNode.n; i ++) {
    		if(searchNode.K[i] == oldKey) {
    			searchNode.K[i] = 0;
    			lsPos = i;
    		}
    	}
    	for(int i = lsPos; i < searchNode.n - 1;i++) {
    		searchNode.K[i] = searchNode.K[i+1];
    	}
    	searchNode.K[searchNode.n - 1] = 0;
    	searchNode.n--;
    	BTNode parentNode = null;
    	if(stack.empty() == false) {
    		parentNode = stack.pop();
    	}
    	else {
    		parentNode = root;
    	}
    	do {
    		if(root == searchNode || searchNode.n >= minSize) {
    			finish = true;
    		}
    		else{
    			int childPos = -1;
    			int childLeftPos;
    			int childRightPos;
    			int leftNum = -2;
    			int rightNum = -2;
    			for(int i = 0 ; i <= parentNode.n;i++) {
    				if(parentNode.P[i] == searchNode) {
    					childPos = i;
    					break;
    				}
    			}
    			childLeftPos = childPos - 1;
    			childRightPos = childPos + 1;
    			if(childLeftPos >= 0) {
    				leftNum = parentNode.P[childLeftPos].n;
    			}
    			if(childRightPos <= parentNode.n) {
    				rightNum = parentNode.P[childRightPos].n;
    			}
    			
    			if(leftNum != -2 && leftNum >= rightNum && leftNum -1 >= minSize) {
    				//borrow from leftNode
    				BTNode leftFriendNode = parentNode.P[childLeftPos];
    				BTNode tempStoreNode = new BTNode(leftNum+searchNode.n+2);
    				BTNode x = new BTNode(m);
    				BTNode y = new BTNode(m);
    				int centerNum;
    				for(int i = 0; i <leftNum; i++) {
    					tempStoreNode.K[i] = leftFriendNode.K[i];
    					tempStoreNode.P[i] = leftFriendNode.P[i];
    				}
    				tempStoreNode.P[leftNum] = leftFriendNode.P[leftNum];
    				tempStoreNode.K[leftNum] = parentNode.K[childPos - 1];
    				for(int i = leftNum + 1; i < leftNum+searchNode.n+1;i++) {
    					tempStoreNode.K[i] = searchNode.K[i - (leftNum + 1)];
    					tempStoreNode.P[i] = searchNode.P[i - (leftNum + 1)];
    				}
    				tempStoreNode.P[leftNum+searchNode.n+1] = searchNode.P[searchNode.n];
    				//tempStoreNode copy end
    				//build x
    				for(int i = 0 ; i < leftNum - 1; i++) {
    					x.n++;
    					x.K[i] = tempStoreNode.K[i];
    					x.P[i] = tempStoreNode.P[i];
    				}
    				x.P[leftNum - 1] = tempStoreNode.P[leftNum - 1];
    				
    				//build y
    				for(int i = leftNum; i < leftNum+searchNode.n+1;i++) {
    					y.n++;
    					y.K[i - leftNum] = tempStoreNode.K[i];
    					y.P[i - leftNum] = tempStoreNode.P[i];
    				}
    				y.P[searchNode.n+1] = tempStoreNode.P[leftNum+searchNode.n+1];
    				
    				//change parentNode <-> middleNode of tempStoreNode  (key,pointer)
    				parentNode.K[childPos - 1] = tempStoreNode.K[(leftNum+searchNode.n+1) / 2];
    				parentNode.P[childPos - 1] = x;
    				parentNode.P[childPos] = y;
    				finish = true;
    			}
    			else if(rightNum !=-2 && rightNum > leftNum && rightNum - 1 >= minSize) {
    				//borrow from rightNode
    				BTNode rightFriendNode = parentNode.P[childRightPos];
    				BTNode tempStoreNode = new BTNode(rightNum+searchNode.n+2);
    				BTNode x = new BTNode(m);
    				BTNode y = new BTNode(m);
    				int centerNum;
    				for(int i = 0; i < searchNode.n; i++) {
    					tempStoreNode.K[i] = searchNode.K[i];
    					tempStoreNode.P[i] = searchNode.P[i];
    				}
    				tempStoreNode.P[searchNode.n] = searchNode.P[searchNode.n];
    				tempStoreNode.K[searchNode.n] = parentNode.K[childPos];
    				
    				for(int i = searchNode.n+1; i < searchNode.n + rightNum + 1; i ++) {
    					tempStoreNode.K[i] = rightFriendNode.K[i - (searchNode.n+1)];
    					tempStoreNode.P[i] = rightFriendNode.P[i - (searchNode.n+1)];
    				}
    				tempStoreNode.P[searchNode.n + rightNum + 1] = rightFriendNode.P[rightNum];
    				//copy complete
    				
    				//build x
    				for(int i = 0; i < searchNode.n + 1; i++) {
    					x.K[i] = tempStoreNode.K[i];
    					x.P[i] = tempStoreNode.P[i];
    					x.n++;
    				}
    				x.P[searchNode.n+1] = tempStoreNode.P[searchNode.n+1];
    				
    				//build y
    				
    				for(int i = searchNode.n+2; i < rightNum+searchNode.n+1; i ++) {
    					y.K[i - (searchNode.n+2)] = tempStoreNode.K[i];
    					y.P[i - (searchNode.n+2)] = tempStoreNode.P[i];
    					y.n++;
    				}
    				y.P[y.n] = tempStoreNode.P[rightNum+searchNode.n+1];
    				//change parentNode <-> middleNode of tempStoreNode  (key,pointer)
    				parentNode.K[childPos] = tempStoreNode.K[searchNode.n + 1];
    				parentNode.P[childPos] = x;
    				parentNode.P[childPos + 1] = y;
    				finish = true;
    			}
    			else { //need to merge
    				BTNode bestSibling;
    				if(childPos == 0) {
    					//merge with rightFriendNode
    					bestSibling = parentNode.P[1];
    					BTNode newNode2 = new BTNode(m);
    					//1.copy searchNode -> newNode2
    					for(int i = 0; i < searchNode.n; i ++) {
    						newNode2.K[i] = searchNode.K[i];
    						newNode2.P[i] = searchNode.P[i];
    						newNode2.n++;
    					}
    					newNode2.P[searchNode.n] = searchNode.P[searchNode.n];
    					//1.end
    					
    					//2.copy parentNode Key -> newNode2
    					newNode2.K[searchNode.n] = parentNode.K[childPos];
    					newNode2.n++;
    					//2.end
    					
    					//3.copy bestSibling -> newNode2
    					for(int i = 0; i < bestSibling.n; i++) {
    						newNode2.K[searchNode.n + 1 + i] = bestSibling.K[i];
    						newNode2.P[searchNode.n + 1 + i] = bestSibling.P[i];
    						newNode2.n++;
    					}
    					newNode2.P[searchNode.n + 1 + bestSibling.n] = bestSibling.P[bestSibling.n];
    					//3.end
    					//newNode2 complete
    					
    					//connect with parentNode
    					parentNode.P[0] = newNode2;
    					//move backward
    					for(int i = 0; i < parentNode.n - 1; i++) {
    						parentNode.K[i] = parentNode.K[i+1];
    						parentNode.P[i+1] = parentNode.P[i+2];    				
    					}
    					parentNode.K[parentNode.n - 1] = 0;
    					parentNode.P[parentNode.n] = null;
    					parentNode.n -= 1;
    				}
    				else {
    					//merge with leftFriendNode
    					bestSibling = parentNode.P[childPos - 1];
    					BTNode newNode2 = new BTNode(m);
    					//1.bestSibling(left) -> newNode2
    					for(int i = 0 ; i < bestSibling.n; i++) {
    						newNode2.K[i] = bestSibling.K[i];
    						newNode2.P[i] = bestSibling.P[i];
    						newNode2.n++;
    					}
    					newNode2.P[bestSibling.n] = bestSibling.P[bestSibling.n];
    					//1.end
    					
    					//2.parentNode Key -> newNode2
    					newNode2.K[bestSibling.n] = parentNode.K[childPos - 1];
    					newNode2.n++;
    					//2.end
    					
    					//3.searchNode -> newNode2
    					for(int i = 0; i < searchNode.n; i++) {
    						newNode2.K[bestSibling.n + 1 + i] = searchNode.K[i];
    						newNode2.P[bestSibling.n + 1 + i] = searchNode.P[i];
    						newNode2.n++;
    					}
    					newNode2.P[bestSibling.n + 1 + searchNode.n] = searchNode.P[searchNode.n];
    					//3.end
    					//newNode2 complete
    					
    					//connect with parent
    					parentNode.P[childPos - 1] = newNode2;
    					//move backward
    					for(int i = childPos - 1; i < parentNode.n - 1; i++) {
    						parentNode.K[i] = parentNode.K[i+1];
    						parentNode.P[i+1] = parentNode.P[i+2];
    					}
    					parentNode.K[parentNode.n-1] = 0;
    					parentNode.P[parentNode.n] = null;
    					parentNode.n -= 1;
    				}
    				searchNode = parentNode;
    				if(stack.empty() == false) {
    					parentNode = stack.pop();
    				}
    				else {
    					finish = true;
    				}
    			}
    		}
    	}while(!finish);
    	if(parentNode.K[0] == 0) {
    		return parentNode.P[0];
    	}
    	
    	
    	
    	
    	return root;
    }
    
    /**
     * inorder implements inorder traversal.
     */
     void inorder() {
       /* write your code here */
     }
  } // end class BTNode
  
  /**
   * constructor
   * @param m: number of branch points in B-tree
   */
  BTree(int m) {
    this.root = new BTNode(m);
  }
  
  /**
   * insert inserts newKey.
   * @param m: number of branch points in B-tree
   * @param newKey: a key to insert
   */
  void insert(int m, int newKey) {
    /* write your code here */
	   root = root.insert(m, newKey);
  }
  
  /**
   * delete deletes oldKey.
   * @param m: number of branch points in B-tree
   * @param oldKey: a key to delete
   */
  void delete(int m, int oldKey) {
    /* write your code here */
	  root = root.delete(m, oldKey);
  }
  
  /**
   * inorder implements inorder traversal.
   */
  void inorder() {
    /* write your code here */
	  inorderT(root);
  }
  void inorderT(BTNode node) {
	  if(node != null) {
		  for(int i = 0; i < node.n; i++) {
			  inorderT(node.P[i]);
			  System.out.print(node.K[i]);
			  System.out.print(" ");
		  }
		  inorderT(node.P[node.n]);
	  }
  }
  
  public static void main(String []args){
    /* do not modify the code below */
    
    int insertTestCases[] = { 40, 11, 77, 33, 20, 90, 99, 70, 88, 80,
                              66, 10, 22, 30, 44, 55, 50, 60, 100, 28,
                              18, 9, 5, 17, 6, 3, 1, 4, 2, 7,
                              8, 73, 12, 13, 14, 16, 15, 25, 24, 28,
                              45, 49, 42, 43, 41, 47, 48, 46, 63, 68,
                              61, 62, 64, 69, 67, 65, 54, 59, 58, 51,
                              53, 57, 52, 56, 83, 81, 82, 84, 75, 89 };
    int deleteTestCases[] = { 66, 10, 22, 30, 44, 55, 50, 60, 100, 28,
                              18, 9, 5, 17, 6, 3, 1, 4, 2, 7,
                              8, 73, 12, 13, 14, 16, 15, 25, 24, 28,
                              40, 11, 77, 33, 20, 90, 99, 70, 88, 80,
                              45, 49, 42, 43, 41, 47, 48, 46, 63, 68,
                              53, 57, 52, 56, 83, 81, 82, 84, 75, 89,
                              61, 62, 64, 69, 67, 65, 54, 59, 58, 51 };
    
    BTree T = new BTree(3);
    
    for (int tC : insertTestCases) {
      T.insert(3, tC);
      T.inorder();
      System.out.println();
    }
    for (int tC: deleteTestCases) {
      T.delete(3, tC);
      T.inorder();
      System.out.println();
    }
    
    T = new BTree(4);
    
    for (int tC: insertTestCases) {
      T.insert(4, tC);
      T.inorder();
      System.out.println();
    }
    for (int tC: deleteTestCases) {
      T.delete(4, tC);
      T.inorder();
      System.out.println();
    }
  } // end main
  
} // end class BTree
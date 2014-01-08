package ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class BinaryTree {

	public static void main(String[] args) throws IOException{
    int value;
    Tree theTree = new Tree();

    theTree.insert(50, 1.5);
    theTree.insert(25, 1.2);
    theTree.insert(75, 1.7);
    theTree.insert(12, 1.5);
    theTree.insert(37, 1.2);
    theTree.insert(43, 1.7);
    theTree.insert(30, 1.5);
    theTree.insert(33, 1.2);
    theTree.insert(87, 1.7);
    theTree.insert(93, 1.5);
    theTree.insert(97, 1.5);

    while(true)
       {
       System.out.print("Enter first letter of show, ");
       System.out.print("insert, find, delete, or traverse: ");
       int choice = getChar();
       switch(choice)
          {
          case 's':
             theTree.displayTree();
             break;
          case 'i':
             System.out.print("Enter value to insert: ");
             value = getInt();
             theTree.insert(value, value + 0.9);
             break;
          case 'f':
             System.out.print("Enter value to find: ");
             value = getInt();
             Node found = theTree.find(value);
             if(found != null)
                {
                System.out.print("Found: ");
                found.displayNode();
                System.out.print("\n");
                }
             else
                System.out.print("Could not find ");
                System.out.print(value + '\n');
             break;
          case 'd':
             System.out.print("Enter value to delete: ");
             value = getInt();
             boolean didDelete = theTree.delete(value);
             if(didDelete)
                System.out.print("Deleted " + value + '\n');
             else
                System.out.print("Could not delete ");
                System.out.print(value + '\n');
             break;
          case 't':
             System.out.print("Enter type 1, 2 or 3: ");
             value = getInt();
             theTree.traverse(value);
             break;
          default:
             System.out.print("Invalid entry\n");
          }  // end switch
       }  // end while
    }  // end main()
	
	public static String getString() throws IOException{
	    InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(isr);
	    String s = br.readLine();
	    return s;
	}
	public static char getChar() throws IOException{
	    String s = getString();
	    return s.charAt(0);
	}
	public static int getInt() throws IOException{
	    String s = getString();
	    return Integer.parseInt(s);
	}

}

class Node
{
	public int iData;              // data item (key)
	public double dData;           // data item
	public Node leftChild;         // this node's left child
	public Node rightChild;        // this node's right child
	
	public Node(int id, double dd){
		iData = id;
		dData = dd;
	}
	public void displayNode(){      // display ourself
	   System.out.print("{" + iData + ", " + dData + "} ");
	}
}  // end class Node

class Tree{
	private Node root;
	
	public Tree(){
		root = null;
	}
	
	public Node find(int key){
		if(root == null)
			return null;
		Node current = root;
		while(current.iData != key){
			if(current.iData > key)
				current = current.leftChild;
			else
				current = current.rightChild;
			if(current == null)
				return null;
		}
		return current;
	}
	
	public void insert(int id, double dd){
		Node newNode = new Node(id,dd);
		
		if(root == null)
			root = newNode;
		else{
			Node current = root;
			Node parent = root;
			while(true){
				parent = current;
				if(id < current.iData){
					current = current.leftChild;
					if(current == null){
						parent.leftChild = newNode;
						return;
					}
				}
				else{
					current = current.rightChild;
					if(current == null){
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	
	public boolean delete(int key){
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(current.iData != key){        // search for node
	        parent = current;
	        if(key < current.iData)         // go left?
	           {
	           isLeftChild = true;
	           current = current.leftChild;
	           }
	        else                            // or go right?
	           {
	           isLeftChild = false;
	           current = current.rightChild;
	           }
	        if(current == null)             // end of the line,
	           return false;                // didn't find it
        }  // end while
		// found node to delete
		
		// current Node does not have child.
		if(current.leftChild == null && current.rightChild == null){
			if(current == root)
				root = null;
			else if(isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}
		
		// current Node has 1 child.
		else if(current.leftChild == null){
			if(current == root)
				root = current.rightChild;
			else if(isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		}
		else if(current.rightChild == null){
			if(current == root)
				root = current.leftChild;
			else if(isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		}
		
		// current Node has 2 children.
		else{
			Node successor = getSuccessor(current);
			if(current == root)
				root = successor;
			else if(isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild  = successor;
		}
		return true;
	}
	
	private Node getSuccessor(Node delNode){
		Node successor = delNode.rightChild;
		Node sParent = delNode;
		
		while(successor.leftChild != null){
			sParent = successor;
			successor = successor.leftChild;
		}
		
		if(successor == delNode.rightChild)
			successor.leftChild = delNode.leftChild;
		else{
			sParent.leftChild = successor.rightChild;
			successor.leftChild = delNode.leftChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	
    public void traverse(int traverseType){
      switch(traverseType)
         {
         case 1: System.out.println("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.println("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.println("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
    }
    
    public void preOrder(Node localRoot){
    	if(localRoot == null)
    		return;
    	else{
    		System.out.print(localRoot.iData + " ");
    		preOrder(localRoot.leftChild);
    		preOrder(localRoot.rightChild);
    	}
    }
    public void inOrder(Node localRoot){
    	if(localRoot == null)
    		return;
    	else{
    		preOrder(localRoot.leftChild);
    		System.out.print(localRoot.iData + " ");
    		preOrder(localRoot.rightChild);
    	}
    }
    public void postOrder(Node localRoot){
    	if(localRoot == null)
    		return;
    	else{
    		preOrder(localRoot.leftChild);
    		preOrder(localRoot.rightChild);
    		System.out.print(localRoot.iData + " ");
    	}
    }
    
    public void displayTree()
    {
    Stack globalStack = new Stack();
    globalStack.push(root);
    int nBlanks = 32;
    boolean isRowEmpty = false;
    System.out.println(
    "......................................................");
    while(isRowEmpty==false)
       {
       Stack localStack = new Stack();
       isRowEmpty = true;

       for(int j=0; j<nBlanks; j++)
          System.out.print(' ');

       while(globalStack.isEmpty()==false)
          {
          Node temp = (Node)globalStack.pop();
          if(temp != null)
             {
             System.out.print(temp.iData);
             localStack.push(temp.leftChild);
             localStack.push(temp.rightChild);

             if(temp.leftChild != null ||
                                 temp.rightChild != null)
                isRowEmpty = false;
             }
          else
             {
             System.out.print("--");
             localStack.push(null);
             localStack.push(null);
             }
          for(int j=0; j<nBlanks*2-2; j++)
             System.out.print(' ');
          }  // end while globalStack not empty
       System.out.println();
       nBlanks /= 2;
       while(localStack.isEmpty()==false)
          globalStack.push( localStack.pop() );
       }  // end while isRowEmpty is false
    System.out.println(
    "......................................................");
    } 
}

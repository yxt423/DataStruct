package ch12;

class Node{
	private int iData;             // data item (key)
	
	public Node(int key)           // constructor
	   { iData = key; }
	public int getKey()
	   { return iData; }
	public void setKey(int id)
	   { iData = id; }
}

public class Heap {
	private Node[] heapArray;
	private int maxSize;           // size of array
	private int currentSize;       // number of nodes in array
	
	public Heap(int size){
		maxSize = size;
		heapArray = new Node[maxSize];
		currentSize = 0;
	}
	
	public Node remove(){           // delete item with max key
		if (currentSize == 0)
			return null;
		Node root = heapArray[0];
		if(currentSize == 1){
			heapArray[0] = null;
			currentSize--;
		}
		else{
			heapArray[0] = heapArray[currentSize -1];
			heapArray[--currentSize] = null;
			trickleDown(0);
		}
		displayHeap();
		return root;
	}
	
	public void trickleDown(int index){
		int largerChild;
		Node top = heapArray[index];
		
		while(!isLeaf(index)){
			int leftChild = index*2 + 1;
			int rightChild = index*2 + 2;
			
			if(rightChild > currentSize -1 || 
					heapArray[leftChild].getKey() >= heapArray[rightChild].getKey())
				largerChild = leftChild;
			else
				largerChild = rightChild;
			
			if(top.getKey() >= heapArray[largerChild].getKey())
				break;
				
			heapArray[index] = heapArray[largerChild];
			index = largerChild;
		}
		heapArray[index] = top;
	}
	
	public boolean insert(int key){
		if(currentSize == maxSize)
			return false;
		Node newNode = new Node(key);
		currentSize++;
		heapArray[currentSize-1] = newNode;
		if(currentSize > 1)
			trickleUp(currentSize-1);
		displayHeap();
		return true;
	}
	
	public void trickleUp(int index){
		Node newNode = heapArray[index];
		while(index > 0 ){
			int parent = (index-1)/2;
			if(heapArray[parent].getKey() >= newNode.getKey())
				break;
			
			heapArray[index] = heapArray[parent];
			index = parent;
			
		}
		heapArray[index] = newNode;
	}
	
	public boolean change(int index, int newvalue){
		if(index > currentSize-1 || index < 0)
			return false;
		int oldvalue = heapArray[index].getKey();
		heapArray[index].setKey(newvalue);
		if(oldvalue > newvalue)
			trickleDown(index);
		else
			trickleUp(index);
		displayHeap();
		return true;
	}
	
	public boolean isLeaf(int index){
		if (index*2 +1 > currentSize -1)
			return true;
		else
			return false;
	}
	
	public void displayHeap()
    {
    System.out.print("heapArray: ");    // array format
    for(int m=0; m<currentSize; m++)
       if(heapArray[m] != null)
          System.out.print( heapArray[m].getKey() + " ");
       else
          System.out.print( "-- ");
    System.out.println();
                                        // heap format
    int nBlanks = 32;
    int itemsPerRow = 1;
    int column = 0;
    int j = 0;                          // current item
    String dots = "...............................";
    System.out.println(dots+dots);      // dotted top line

    while(currentSize > 0)              // for each heap item
       {
       if(column == 0)                  // first item in row?
          for(int k=0; k<nBlanks; k++)  // preceding blanks
             System.out.print(' ');
                                        // display item
       System.out.print(heapArray[j].getKey());

       if(++j == currentSize)           // done?
          break;

       if(++column==itemsPerRow)        // end of row?
          {
          nBlanks /= 2;                 // half the blanks
          itemsPerRow *= 2;             // twice the items
          column = 0;                   // start over on
          System.out.println();         //    new row
          }
       else                             // next item on row
          for(int k=0; k<nBlanks*2-2; k++)
             System.out.print(' ');     // interim blanks
       }  // end for
    System.out.println("\n"+dots+dots); // dotted bottom line
    }  // end displayHeap()
	
	public void swap(Node node1, Node node2){
		Node temp = node1;
		node1 = node2;
		node2 = temp;
	}
	
	public boolean isEmpty(){ 
		return currentSize==0; 
	}
	   
}

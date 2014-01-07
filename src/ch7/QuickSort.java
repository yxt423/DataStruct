package ch7;

public class QuickSort {

	public static void main(String[] args){
    int maxSize = 16;             // array size
    QuickS arr;
    arr = new QuickS(maxSize);  // create array

    for(int j=0; j<maxSize; j++)  // fill array with
       {                          // random numbers
       long n = (int)(java.lang.Math.random()*99);
       arr.insert(n);
       }
    arr.display();                // display items
    arr.quickSort();              // quicksort them
    arr.display();                // display them again
    } 

}

class QuickS{
	   private long[] theArray;          // ref to array theArray
	   private int nElems;               // number of data items
	   
	   public QuickS(int max)          // constructor
	      {
	      theArray = new long[max];      // create the array
	      nElems = 0;                    // no items yet
	      }
	   public void insert(long value)    // put element into array
	      {
	      theArray[nElems] = value;      // insert it
	      nElems++;                      // increment size
	      }
	   public void display()             // displays array contents
	      {
	      System.out.print("A=");
	      for(int j=0; j<nElems; j++)    // for each element,
	         System.out.print(theArray[j] + " ");  // display it
	      System.out.println("");
	      }
	   public void quickSort()
	      {
	      recQuickSort(0, nElems-1);
	      }
	   
	   public void recQuickSort(int left, int right){
		   if(right - left <= 0)
			   return;
		   else{
			   long pivot = theArray[right];
			   int partition = partitionIt(left, right,pivot);
			   recQuickSort(left,partition -1);
			   recQuickSort(partition+1,right);
		   }
	   }
	   
	   public int partitionIt(int left, int right, long pivot){
		   int leftP = left-1;
		   int rightP = right;
		   
		   while(leftP < rightP){
			   while(theArray[++leftP]<pivot) // do not need 'left<nElems', because theArray[right] is partition.
				   ;
			   while(rightP > 0 && theArray[--rightP]>pivot)
				   ;
			   if(leftP < rightP)
				   swap(leftP, rightP);
		   }
		   swap(leftP, right);
		   return leftP;
	   }
	   
	   public void swap(int left, int right){
		   long temp = theArray[left];
		   theArray[left] = theArray[right];
		   theArray[right] = temp;
	   }
}

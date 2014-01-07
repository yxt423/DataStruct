// quick sort 2, use the medium value of left, right and middle as pivot.

package ch7;

public class QuickSort2 {

	public static void main(String[] args){
    int maxSize = 16;             // array size
    QuickS2 arr;
    arr = new QuickS2(maxSize);  // create array

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

class QuickS2{
	   private long[] theArray;          // ref to array theArray
	   private int nElems;               // number of data items
	   
	   public QuickS2(int max)          // constructor
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
		   if(right - left < 3){
			   manualSort(left,right);
		   }
		   else{
			   long medium = mediumOf3(left, right);
			   int partition = partitionIt(left, right,medium);
			   recQuickSort(left,partition - 1);
			   recQuickSort(partition + 1,right);
		   }
	   }
	   
	   public int partitionIt(int left, int right, long pivot){
		   int leftP = left;
		   int rightP = right -1;
		   
		   while(leftP < rightP){
			   while(theArray[++leftP]<pivot) // do not need 'left<nElems', because theArray[right] is partition.
				   ;
			   while(theArray[--rightP]>pivot)
				   ;
			   if(leftP < rightP)
				   swap(leftP, rightP);
		   }
		   swap(leftP, right -1);
		   return leftP;
	   }
	   
	   public void manualSort(int left, int right){
		   int n = right - left;
		   if(n == 0){
			   return;
		   }
		   else if(n ==1){
			   if(theArray[left]>theArray[right])
				   swap(left, right);
		   }
		   else if(n == 2){
			   int middle = left + 1;
			   if(theArray[left]>theArray[right])
				   swap(left,right);
			   if(theArray[middle]>theArray[right])
				   swap(middle,right);
			   if(theArray[left]>theArray[middle])
				   swap(left,middle);
		   }
	   }
	   
	   public long mediumOf3(int left, int right){
		   int middle = (left + right)/2;
		   
		   if(theArray[left] > theArray[right])
			   swap(left, right);
		   if(theArray[middle] > theArray[right])
			   swap(middle, right);
		   if(theArray[left] > theArray[middle])
			   swap(left, middle);
		   
		   swap(middle, right-1);      // put pivot on right
		   return theArray[right-1];
	   }
	   
	   public void swap(int left, int right){
		   long temp = theArray[left];
		   theArray[left] = theArray[right];
		   theArray[right] = temp;
	   }
}

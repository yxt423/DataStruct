package ch7;

public class ShellSort {

	public static void main(String[] args)
    {
    int maxSize = 10;             // array size
    ShellArray arr;
    arr = new ShellArray(maxSize);   // create the array

    for(int j=0; j<maxSize; j++)  // fill array with
       {                          // random numbers
       long n = (int)(java.lang.Math.random()*99);
       arr.insert(n);
       }
    arr.display();                // display unsorted array
    arr.shellSort();              // shell sort the array
    arr.display();                // display sorted array
    }

}

class ShellArray{
	private long[] theArray;          // ref to array theArray
   private int nElems;               // number of data items
   
   public ShellArray(int max)           // constructor
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
   public void shellSort(){
	   int inner, outer;
	   long temp;
	   
	   int h = 1;
	   while(h <= nElems/3)
		   h = h*3 + 1;
	   
	   while(h>0){
		   for(outer = h; outer<nElems; outer++){
			   temp = theArray[outer];
			   inner = outer;
			   
			   while(inner > h-1 && theArray[inner-h] >= temp){
				   theArray[inner] = theArray[inner-h];
				   inner -= h;
			   }
			   theArray[inner] = temp;
		   }
		   h = (h-1)/3;
	   }
   }
}



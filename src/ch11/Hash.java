package ch11;

import java.io.*;

class DataItem{                                // (could have more data)
	private int iData;               // data item (key)
	
	public DataItem(int ii)          // constructor
	   { iData = ii; }
	public int getKey()
	   { return iData; }
}

class HashTable{
   private DataItem[] hashArray;    // array holds hash table
   private int arraySize;
   private DataItem nonItem;        // for deleted items
   
   public HashTable(int size){
	   arraySize = size;
	   hashArray = new DataItem[size];
	   nonItem = new DataItem(-1);
   }
   
   public int hashFunc(int key){
	   return key % arraySize;
   }
   
   public void insert(DataItem item){
	   int hashvalue = hashFunc(item.getKey());
	   int index = hashvalue;
	   
	   while(hashArray[index] != null && hashArray[index].getKey() != -1){
		   index = (++index) % arraySize;
		   if(index == hashvalue){
			   System.out.println("The hashtable is full.");
			   return;
		   }
	   }
	   hashArray[index] = item;
	   displayTable();
   }
   
   
   public DataItem delete(int key){
	   int hashvalue = hashFunc(key);
	   int index = hashvalue;
	   
	   while(hashArray[index] != null){
		   if(hashArray[index].getKey() == key){
			   DataItem deletedItem = hashArray[index];
			   hashArray[index] = nonItem;
			   displayTable();
			   return deletedItem;
		   }
		   index = (++index) % arraySize;
		   if(index == hashvalue)
			   return null;
	   }
	   return null;
   }
   
   public DataItem find(int key){
	   int hashvalue = hashFunc(key);
	   int index = hashvalue;
	   
	   while(hashArray[index] != null){
		   if(hashArray[index].getKey() == key)
			   return hashArray[index];
		   index = (++index) % arraySize;
		   if(index == hashvalue)
			   return null;
	   }
	   return null;
   }
   
   public void displayTable(){
	   System.out.print("Table: ");
	   for(int j=0; j<arraySize; j++)
	      {
	      if(hashArray[j] != null)
	         System.out.print(hashArray[j].getKey() + " ");
	      else
	         System.out.print("** ");
	      }
	   System.out.println("");
   }
}

public class Hash {
	public static void main(String[] args) throws IOException
    {
    DataItem aDataItem;
    int aKey, size, n, keysPerCell;
                                  // get sizes
    System.out.print("Enter size of hash table: ");
    size = getInt();
    System.out.print("Enter initial number of items: ");
    n = getInt();
    keysPerCell = 10;
                                  // make table
    HashTable theHashTable = new HashTable(size);

    for(int j=0; j<n; j++)        // insert data
       {
       aKey = (int)(java.lang.Math.random() *
                                       keysPerCell * size);
       aDataItem = new DataItem(aKey);
       theHashTable.insert(aDataItem);
       }

    while(true)                   // interact with user
       {
       System.out.print("Enter first letter of ");
       System.out.print("show, insert, delete, or find: ");
       char choice = getChar();
       switch(choice)
          {
          case 's':
             theHashTable.displayTable();
             break;
          case 'i':
          System.out.print("Enter key value to insert: ");
             aKey = getInt();
             aDataItem = new DataItem(aKey);
             theHashTable.insert(aDataItem);
             break;
          case 'd':
             System.out.print("Enter key value to delete: ");
             aKey = getInt();
             theHashTable.delete(aKey);
             break;
          case 'f':
             System.out.print("Enter key value to find: ");
             aKey = getInt();
             aDataItem = theHashTable.find(aKey);
             if(aDataItem != null)
                {
                System.out.println("Found " + aKey);
                }
             else
                System.out.println("Could not find " + aKey);
             break;
          default:
             System.out.print("Invalid entry\n");
          }  // end switch
       }  // end while
    }  // end main()
	
 public static String getString() throws IOException
    {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
    }
 public static char getChar() throws IOException
    {
    String s = getString();
    return s.charAt(0);
    }
 public static int getInt() throws IOException
    {
    String s = getString();
    return Integer.parseInt(s);
    }
}

package ch5;

public class MainApp {
	
	public static void main(String[] args) {
		 CircleList theList = new CircleList(); // make new list
		
		 theList.insertAfterCurrent(10); // insert four items
		 theList.insertAfterCurrent(20); // insert four items
		 theList.insertAfterCurrent(40); // insert four items
		 theList.insertAfterCurrent(30); // insert four items
		 theList.display(); // display list
		 
		 Link link = theList.find(40);
		 if (link != null) {
			 System.out.println("find 40!");
		 } 
		 else {
			 System.out.println("not find 40!");
		 }
		 Link aLink = theList.deleteAfterCurrent(); // delete link
		 System.out.println("Deleted " + aLink.data); // display it
		
		 theList.display(); // display list
		 } // end main()

}

package ch4;

public class MainApp {

	public static void main(String[] args) {
		Deque theQueue = new Deque(5);
		
		theQueue.insertLeft(30);
		theQueue.insertLeft(20);
		theQueue.insertLeft(10);
		theQueue.insertRight(40);
		theQueue.insertRight(50);
		theQueue.removeLeft();
		theQueue.removeRight();
		
		theQueue.display();

	}

}

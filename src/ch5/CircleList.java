package ch5;

class Link{
	public long data;
	public Link next;
	
	public Link(long d){
		data = d;
	}
	
	public void displayLink(){
		System.out.println(data + " ");
	}
}
public class CircleList {
	private Link current;
	
	public CircleList(){
		current = null;
	}
	
	public void insertAfterCurrent(long d){
		Link newLink = new Link(d);
		if(current == null){
			newLink.next = newLink;
			current = newLink;
		}
		else{
			newLink.next = current.next;
			current.next = newLink;
			current = newLink;
		}
	}
	
	public Link find(long d){
		Link temp = current;
		if(temp == null)
			return null;
		
		while(temp.data != d){
			if(temp.next != current)
				temp = temp.next;
			else
				return null;
		}
		return temp;
	}
	
	public Link deleteAfterCurrent(){
		if(current == null)
			return null;
		Link temp = current.next;
		if(temp != current){
			current.next = temp.next;
		}
		else{
			current = null;
		}
		return temp;
	}
	
	public void step(){
		if(current != null)
			current = current.next;
	}
	
	public void display(){
		if(current == null)
			return;
		Link temp = current;
		do{
			temp = temp.next;
			System.out.print(temp.data + " ");
		}
		while(temp != current);
		System.out.println();
	}

}

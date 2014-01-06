package ch4;

public class Deque{
	private int maxSize;
	private long[] queArray;
	private int left;
	private int right;
	private int nItems;
	
	public Deque(int s){
		maxSize = s;
		queArray = new long[maxSize];
		left = 0;
		right = 0;
		nItems = 0;
	}
	public void insertLeft(long j){
		if(nItems == maxSize){
			System.out.println("The Deque is full.");
			return;
		}
		if(nItems == 0){
			queArray[left] = j;
		}
		else{
			if(left == 0)
				left = maxSize - 1;
			else
				left--;
			queArray[left] = j;
		}
		nItems++;
	}
	public void insertRight(long j){
		if(nItems == maxSize){
			System.out.println("The Deque is full.");
			return;
		}
		if(nItems == 0){
			queArray[right] = j;
		}
		else{
			if(right == maxSize-1)
				right = 0;
			else
				right++;
			queArray[right] = j;
		}
		nItems++;
	}
	public long removeLeft(){
		if(nItems == 0){
			System.out.println("Deque is empty.");
			return -1;
		}
		long temp = queArray[left++];
		if(left == maxSize)
			left = 0;
		nItems--;
		return temp;
	}
	public long removeRight(){
		if(nItems == 0){
			System.out.println("Deque is empty.");
			return -1;
		}
		long temp = queArray[right--];
		if(right == -1)
			right = maxSize -1;
		nItems--;
		return temp;
	}
	public void display(){
		if(nItems == 0)
			return;
		for(int i=1,in=left; i<=nItems; i++,in++){
			if(in == maxSize)
				in = 0;
			System.out.print(queArray[in] + " ");
		}
	}
	public boolean isEmpty(){
		return (nItems==0);
    }
	public boolean isFull(){
		return (nItems==maxSize);
    }
}
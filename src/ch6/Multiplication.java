package ch6;

public class Multiplication {
	
	public static int mult(int x, int y){
		if(y == 1)
			return x;
		return x + mult(x, y-1);
	}
	
	public static void main(String[] args){
		int result = mult(6,8);
		System.out.println(result);
	}

}

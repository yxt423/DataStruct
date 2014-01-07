package ch6;

public class PowerXY {
	
	public static int power(int x, int y){ // return x^y
		if(y == 1)
			return x;
		if(y%2 == 0){
			int temp = power(x,y/2);
			return temp*temp;
		}
		else{
			int temp = power(x,(y-1)/2);
			return temp*temp*x;
		}
	}
	
	public static void main(String[] args){
		int result = power(3,18);
		System.out.println(result);
	}

}

package ch6;

public class BackPack {
	private int[] items;
	private boolean[] selects;
	private int goal;
	
	public BackPack(int[] i, int g){
		items = i;
		selects = new boolean[items.length];
		goal = g;
	}
	
	public boolean backpack(int index, int sum){
		for(int i = index; i< items.length; i++){
			int temp = sum;
			temp += items[i];
			if(temp == goal){
				selects[i] = true;
				return true;
			}
			else if(temp < goal){
				if(index == items.length-1)
					return false;
				if(backpack(index+1,temp)){
					selects[i] = true;
					return true;
				}
				else
					selects[i] = false;
			}
			else
				return false;
		}
		return false;
	}
	
	public void displayResult(){
		for(int i=0; i<selects.length;i++){
			if(selects[i] == true)
				System.out.print(items[i] + " ");
		}
	}

	public static void main(String[] args) {
		int[] items = {11,8,7,6,5};
		BackPack back = new BackPack(items,20);
		back.backpack(0, 0);
		back.displayResult();

	}

}

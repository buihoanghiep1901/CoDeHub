package algorithm;

import algorithm.Algorithm;
import items.itemArray;

public class BubbleSort implements Algorithm {
	private static BubbleSort newbubble = new BubbleSort();
	
	public static BubbleSort getBubble() {
		return newbubble;
	}
	 public void sort(itemArray array) {
	        for (int i = array.itemsLength(); i >= 1; i--) {
	            for (int j = 0; j < i - 1; j++)
	                array.CompareAndSwap(j, j + 1);
	            array.steps.addDoneStep(array.getItemNo(i-1));
	        }
	 }

}

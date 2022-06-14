package algorithm;

import items.itemArray;

public class SelectionSort implements Algorithm {

	private static SelectionSort newselection = new SelectionSort();
	
	public static SelectionSort getSelection() {
		return newselection;
	}
	
	@Override
	public void sort(itemArray array) {
        int length = array.itemsLength();
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (array.compare(j, minIndex) > 0)
                    minIndex = j;
            }
            array.swap(i, minIndex);
            // Sorted Point
            array.steps.addDoneStep(array.getItemNo(i));
        }
		
	}

}

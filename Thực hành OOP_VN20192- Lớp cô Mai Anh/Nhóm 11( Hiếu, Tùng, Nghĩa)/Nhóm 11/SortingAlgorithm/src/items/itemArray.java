package items;

import java.util.Random;

import items.item;
import items.iteminside;
import javafx.scene.layout.Pane;
import step.Steps;

public class itemArray {
	private item[] items;
	
	public Steps steps;
	
	public itemArray(int length) {
		steps = new Steps();
		items = new item[length];
		int[] giatri = new int[length];
		for(int i=0; i<length;i++) {
			Random rd = new Random();
			giatri[i] = rd.nextInt(100);
			
			items[i]= new iteminside(giatri[i],i);
			
			System.out.print(giatri[i] + " ");
		}
		System.out.println();
	}
	
    public Pane [] getAllpane () {
        Pane [] shapes = new Pane[itemsLength()];
        for (int i = 0; i < items.length; i++) {
            shapes[i] = items[i].getPane();
        }
        return shapes;
    }
    public void swap(int i, int j) {
        item tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;

        items[i].setIndex(i);
        items[j].setIndex(j);
        //
        steps.addSwapStep(items[i], items[j]);
        System.out.println("Swap "+items[i].getValue()+" va "+items[j].getValue());
    }
    public int compare(int i, int j) {
    	steps.addCompareStep(items[i], items[j]);
    	System.out.println("So sanh "+items[i].getValue()+" va "+items[j].getValue());
    	return (items[i].getValue()-items[j].getValue()); 
    	
    }
    public void CompareAndSwap(int i, int j) {
    	if (compare(i, j)>0) {
    		swap(i,j);
    	}
    }

    public item [] getAll() {
        return items;
    }
    public item getItemNo(int i) {
    	return items[i];
    }
    public int itemsLength() {
        return items.length;
    }


}

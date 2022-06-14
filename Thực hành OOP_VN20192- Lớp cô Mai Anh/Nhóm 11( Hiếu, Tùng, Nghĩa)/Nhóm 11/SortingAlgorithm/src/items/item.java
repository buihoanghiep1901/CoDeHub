package items;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public abstract class item {
	private int value;
	private int index;
	Rectangle rectangle;
	Pane pane;
	Text txt;

	
	public item(int value, int index) {
		this.value = value;
		this.index = index;
	}
	

	public int getValue() {
		return value;
	}

	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}

	
	public Pane getPane() {
		return pane;
	}

	public enum Act{
		COMPARE, SWAP, DONE, DEFAULT, BUCKET
	}
	public abstract void setActColor(Act act);


	public int compareTo(Object o) {
		return - value + ((item)o).value;
	}
}

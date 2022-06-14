package CNode;

import GUI.Animation_Abstract;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public abstract class Shapee {
	protected int value;
	protected int index;
	VBox shape;
	 	Label label;
	 	
public int getValue() {
			return value;
		}

		public Shapee(int value, int index) {
			
			this.value = value;
			this.index = index;
		
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public VBox getShape() {
			return shape;
		}

		public void setShape(VBox shape) {
			this.shape = shape;
		}

		
		public Label getLabel() {
			return label;
		}

		public void setLabel(Label label) {
			this.label = label;
		}

		public TranslateTransition moveX(int x) {
				TranslateTransition t = new TranslateTransition();
				{
				t.setNode(shape); // cho cÃ¡i this nÃ y vÃ o node
				t.setDuration(Duration.millis(Animation_Abstract.DELAY));// thá»�i gian delay
				t.setByX(x); // cho khoang cach di chuyen
				return t;
			}			
}
		public enum Type {
			COLUMN,RINK
		}
				
}
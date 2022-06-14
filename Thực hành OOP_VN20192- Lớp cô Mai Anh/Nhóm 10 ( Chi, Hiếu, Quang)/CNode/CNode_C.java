package CNode;


import GUI.Animation_Abstract;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class CNode_C  extends Shapee{
	Circle circle;
	
	public CNode_C(int value, int index) {
		super(value, index);
		 shape = new VBox(); 
	     shape.setPadding(new Insets(2,0,0,2));
	       circle= new Circle();
	        label = new Label(this.getValue() + " ");
	        label.setFont(Font.font(15));
	        shape.getChildren().addAll(circle, label);
	        shape.setAlignment(Pos.CENTER);
	}
	public void randomCNodes(int n,int value, CNode_C arr) {
		
		
			
			arr.getCircle().setRadius((Animation_Abstract.WINDOW_WIDTH / n - Animation_Abstract.XGAP) / 2);
			arr.getCircle().setCenterY(((Animation_Abstract.WINDOW_HEIGHT - Animation_Abstract.BUTTONROW_BOUNDARY) /n) * value);
			arr.getCircle().setFill(Color.CRIMSON);
		
	}
	
	public Circle getCircle() {
		return circle;
	}
}

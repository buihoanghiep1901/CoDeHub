package CNode;


import GUI.Animation_Abstract;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class CNode extends Shapee{
	Rectangle rectangle;
	public CNode(int value, int index) {
		super(value, index);
		 shape = new VBox();
		        rectangle = new Rectangle();
		        label = new Label(this.getValue() + " ");
		        label.setFont(Font.font(12));
		   	    shape.setPadding(new Insets(2,0,0,2));
		        shape.getChildren().addAll(rectangle, label);
		        shape.setAlignment(Pos.BOTTOM_CENTER);
	}
	//set size cho hinh
	public void randomCNodes(int n,int value, CNode a) {
		a.getRectangle().setWidth(Animation_Abstract.WINDOW_WIDTH / n -Animation_Abstract.XGAP);
		a.getRectangle().setHeight(((Animation_Abstract.WINDOW_HEIGHT - Animation_Abstract.BUTTONROW_BOUNDARY) / n) * value);
		a.getRectangle().setWidth(Animation_Abstract.WINDOW_WIDTH / n - Animation_Abstract.XGAP);
		a.rectangle.setFill(Color.CRIMSON);
	

}
public Rectangle getRectangle() {
	return rectangle;	
}

}

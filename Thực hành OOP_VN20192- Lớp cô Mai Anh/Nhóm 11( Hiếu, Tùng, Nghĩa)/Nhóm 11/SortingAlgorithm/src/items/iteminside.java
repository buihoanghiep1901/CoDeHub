package items;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class iteminside extends item {
	public iteminside(int value, int index) {
		super(value, index);
		pane = new Pane();
		pane.setPrefWidth(35);
		pane.setPrefHeight(220);
		pane.setLayoutX(35*index);
		pane.setLayoutY(150);
		
        rectangle = new Rectangle();
        rectangle.setLayoutX(7.5);
        rectangle.setLayoutY(200 -2*value);
        rectangle.setFill(Color.BLACK); 
        rectangle.setHeight(2*value); 
        rectangle.setWidth(20d); 
        rectangle.setArcHeight(10.0d); 
        rectangle.setArcWidth(10.0d);
        
        txt = new Text();
        txt.setText(""+value);
        txt.setFill(Color.RED);
        txt.setLayoutX(10);
        txt.setLayoutY(220);
        pane.getChildren().addAll(rectangle, txt);        
		}
	@Override
	public void setActColor(Act act) {
        Color color;

        switch (act) {
            case DONE:
                color = Color.PURPLE;
                break;
            case SWAP:
                color = Color.BLUE;
                break;
            case COMPARE:
                color = Color.YELLOWGREEN;
                break;
            case BUCKET:
                color = Color.GREEN;
                break;
            default:
                color = Color.BLACK;
                break;
        }

        rectangle.setFill(color);
	}
}

package application;

import java.io.IOException;
import java.util.LinkedList;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class QueueController {

	private LinkedList<Label> queue = new LinkedList<Label>();

	@FXML
	private AnchorPane pane;
	@FXML
	private TextField inputInt;
	@FXML
	private Label quantity, status;

	public void quit() throws IOException {
		AnchorPane back = FXMLLoader.load(getClass().getResource("Main.fxml"));
		pane.getChildren().setAll(back);
	}

	public void enqueue() {
		if (inputInt.getText().equals("") ) {
			status.setText("You have not entered anything");
		}
		else if (queue.size() >= 9) {
			status.setText("Your Queue is FULL");
		}
		else if (testInt(inputInt.getText()) ) {
			enqueueAnimation(queue.size());
			quantity.setText(Integer.toString(queue.size()));
			status.setText(inputInt.getText() + " is added");
			inputInt.clear();
		} else {
			inputInt.clear();
			status.setText("The input value must be a integer");
		}
	}

	public void enqueueAnimation(int sizeOfQueue) {
		Label label = new Label(inputInt.getText());
		queue.add(label);
		pane.getChildren().addAll(label);
		label.setFont(new Font("System", 20));
		label.setStyle("-fx-background-color: #ffbd08");
		label.setPrefSize(55, 55);
		label.setLayoutX(173);
		label.setLayoutY(285);
		label.setAlignment(Pos.CENTER);

		PathTransition transition = new PathTransition();
		Polyline polyline = new Polyline();
		switch (sizeOfQueue) {
			case 0:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 600.0, 160.0 });
				transition.setDuration(Duration.seconds(1));
				break;
			case 1:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 530.0, 160.0 });
				transition.setDuration(Duration.seconds(1));
				break;
			case 2:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 460.0, 160.0 });
				transition.setDuration(Duration.seconds(1));
				break;
			case 3:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 390.0, 160.0 });
				transition.setDuration(Duration.seconds(0.9));
				break;
			case 4:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 320.0, 160.0 });
				transition.setDuration(Duration.seconds(0.9));
				break;
			case 5:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 250.0, 160.0 });
				transition.setDuration(Duration.seconds(0.8));
				break;
			case 6:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 180.0, 160.0 });
				transition.setDuration(Duration.seconds(0.7));
				break;
			case 7:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 110.0, 160.0 });
				transition.setDuration(Duration.seconds(0.6));
				break;
			case 8:
				polyline.getPoints().addAll(new Double[] { -250.0, 160.0, 40.0, 160.0 });
				transition.setDuration(Duration.seconds(0.5));
				break;
		}
		transition.setPath(polyline);
		transition.setNode(label);
		transition.play();
	}

	public void dequeue() {
		if (queue.size() > 0) {
			Label label = queue.getFirst();
			queue.removeFirst();
			quantity.setText(Integer.toString(queue.size()));
			TranslateTransition transition = new TranslateTransition();
			transition.setDuration(Duration.seconds(0.5));
			transition.setNode(label);
			transition.setToY(130.0);
			transition.setToX(1000.0);
			transition.play();
			for (int i = 0; i < queue.size(); i++) {
				nextAnimation(i);
			}
		}
	}

	public void nextAnimation(int sizeOfQueue) {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(queue.get(sizeOfQueue));
		switch (sizeOfQueue + 1) {
			case 1:
				transition.setToX(570);
				transition.setToY(131);
				break;
			case 2:
				transition.setToX(500);
				transition.setToY(131);
				break;
			case 3:
				transition.setToX(430);
				transition.setToY(131);
				break;
			case 4:
				transition.setToX(360);
				transition.setToY(131);
				break;
			case 5:
				transition.setToX(290);
				transition.setToY(131);
				break;
			case 6:
				transition.setToX(220);
				transition.setToY(131);
				break;
			case 7:
				transition.setToX(150);
				transition.setToY(131);
				break;
			case 8:
				transition.setToX(80);
				transition.setToY(131);
				break;
		}
		transition.play();
	}

	static boolean testInt(String string) {
		for (int i = 0; i < string.length(); i++) {
			if ((i == 0) && (string.charAt(i) == '-'))
				continue;
			if (!Character.isDigit(string.charAt(i)))
				return false;
		}
		return true;
	}
}
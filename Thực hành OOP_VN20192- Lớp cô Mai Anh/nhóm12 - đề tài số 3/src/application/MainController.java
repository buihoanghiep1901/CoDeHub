package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class MainController  {

	@FXML
	private AnchorPane pane;

	public void queueSubmit() throws IOException {
		AnchorPane queuePane = FXMLLoader.load(getClass().getResource("Queue.fxml"));
		pane.getChildren().setAll(queuePane);
	}

	public void arraylistSubmit() throws IOException {
		AnchorPane arraylistPane = FXMLLoader.load(getClass().getResource("ArrayList.fxml"));
		pane.getChildren().setAll(arraylistPane);
	}
		public void hashTableSubmit() throws IOException {
		AnchorPane hashTablePane = FXMLLoader.load(getClass().getResource("NextHashTable.fxml"));
		pane.getChildren().setAll(hashTablePane);
	}
	public void exitSubmit() {
		System.exit(0);
	}
}
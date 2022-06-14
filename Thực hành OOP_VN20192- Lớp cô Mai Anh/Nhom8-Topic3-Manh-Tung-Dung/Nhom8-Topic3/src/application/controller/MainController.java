package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {

	@FXML
	private AnchorPane presentArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void homePage() throws IOException {
		System.out.println("Welcome Page");
		AnchorPane child = FXMLLoader.load(getClass().getResource("/application/Welcomepage.fxml"));
		presentArea.getChildren().setAll(child);
	}

	public void queuePage() throws IOException {
		System.out.println("Queue Page");
		AnchorPane child = FXMLLoader.load(getClass().getResource("/application/Queuepage.fxml"));
		presentArea.getChildren().setAll(child);
	}

	public void arrayListPage() throws IOException {
		System.out.println("Array List Page");
		AnchorPane child = FXMLLoader.load(getClass().getResource("/application/Arraylistpage.fxml"));
		presentArea.getChildren().setAll(child);
	}

	public void hashTablePage() throws IOException {
		System.out.println("HashTable Page");
		AnchorPane child = FXMLLoader.load(getClass().getResource("/application/Hashtablepage.fxml"));
		presentArea.getChildren().setAll(child);
	}

}
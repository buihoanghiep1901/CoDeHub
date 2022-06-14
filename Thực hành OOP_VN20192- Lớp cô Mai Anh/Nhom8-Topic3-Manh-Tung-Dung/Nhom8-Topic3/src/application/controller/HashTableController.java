package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import datastructure.hashtable.HashFunction;
import datastructure.hashtable.HashNode;
import datastructure.hashtable.HashTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HashTableController implements Initializable {

	@FXML
	private GridPane gridpane;
	@FXML
	private TextField input;
	@FXML
	private TextField value;
	
	private final static int MAX_ROW_GP = 9;
	private final static int MAX_COL_GP = 6;

	private HashTable<String, String> hashtable = new HashTable<String, String>();

	public void add() throws IOException {
		String keyStr = input.getText();
		String valStr = value.getText();
		if (!keyStr.isEmpty() && !valStr.isEmpty()) {   //xesx4r
			HashFunction<String> hashFunction = new HashFunction<String>();
			System.out.println(hashFunction.hashFunction(keyStr));
			hashtable.add(keyStr, valStr);
			System.out.println(hashtable.getSize());
		}
		this.clearInput();
		this.generateHashTable();
	}

	public void remove() throws IOException {
		String keyStr = input.getText();
		if (!keyStr.isEmpty()) {
			String value = hashtable.remove(keyStr);
			if(value == null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo !");
				alert.setHeaderText(null);
				alert.setContentText("Hashtable không chứa giá trị bạn muốn xóa!");
				alert.showAndWait();
			}
		}
		this.clearInput();
		this.generateHashTable();
	}

	public void clear() throws IOException {
		this.clearInput();
		hashtable.clear();
		this.generateHashTable();
	}

	public void find() throws IOException {
		String keyStr = input.getText();
		if (hashtable.isEmpty() || keyStr.isEmpty()) {
			this.clearInput();
			return;
		}
		String value = hashtable.get(keyStr);
		
		if (!keyStr.isEmpty() && value != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo !");
			alert.setHeaderText(null);
			alert.setContentText("Hashtable chứa giá trị bạn muốn tìm ! \n Key: " + keyStr + "    Value: " + value);
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo !");
			alert.setHeaderText(null);
			alert.setContentText("Hashtable không chứa giá trị bạn muốn tìm !");
			alert.showAndWait();
		}
		this.clearInput();
	}

	private void generateHashTable() {
		gridpane.getChildren().clear();
		ArrayList<HashNode<String, String>> buckets = hashtable.getBucket();
		if (hashtable.isEmpty())
			return;
		int row = 0;
		int col = 0;
		for (HashNode<String, String> xHashNode : buckets) {
			Label xLabel = new Label(String.valueOf(row));
			xLabel.setPadding(new Insets(0, 0, 0, 50));
			gridpane.add(xLabel, 0, row);
			if (xHashNode == null) {
				row += 1;
				continue;
			}
			HashNode<String, String> iNode = xHashNode;
			col = 1;
			if (row > MAX_ROW_GP) {
				System.out.println("Max size of gridpane!");
				break;
			}
			while (iNode != null) {
				if (col > MAX_COL_GP) {
					System.out.println("Max size of gridpane!");
					break;
				}
				Button xButton = new Button(iNode.key + " | " + iNode.value);
				xButton.setStyle("-fx-background-color: #abccba;-fx-border-color:gray; -fx-border-radius: 5");
				xButton.setPrefSize(108, 45);
				gridpane.add(xButton, col, row);
				iNode = iNode.next;
				col += 1;
			}
			row += 1;
		}
	}

	private void clearInput() {
		input.clear();
		value.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}

package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ControllerArrayList implements Initializable {

	@FXML
	private AnchorPane pane;
	@FXML
	private TextField valueAdd;
	@FXML
	private TextField indexInsert;
	@FXML
	private TextField valueInsert;
	@FXML
	private TextField indexDelete;
	@FXML
	private Button addItem;
	@FXML
	private Button insertItem;
	@FXML
	private Button deleteItem;
	@FXML
	private GridPane grid;

	public ArrayList<String> list = new ArrayList<String>();

	public void indexOn(Integer num, String str) {
		Button xButton = new Button(str);
		xButton.setPrefSize(70, 50);
		xButton.setStyle("-fx-background-color: #abccba;-fx-border-color:gray");

		Label xLabel = new Label(String.valueOf(num));
		xLabel.setPrefSize(70, 50);
		xLabel.setMaxWidth(Double.MAX_VALUE);
		xLabel.setAlignment(Pos.CENTER);

		grid.add(xButton, num, 0);
		grid.add(xLabel, num, 1);

	}

	public void addItem(ActionEvent event) {
		String str = valueAdd.getText();
		if (str.isEmpty())
			JOptionPane.showMessageDialog(null, "Please input a value !");
		else {
			valueAdd.clear();
			list.add(str);
			printList();
		}
	}

	public static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public void insertItem(ActionEvent event) {
		String index = indexInsert.getText();
		String value = valueInsert.getText();

		if (index.isEmpty() ) {
			if(value.isEmpty())
				JOptionPane.showMessageDialog(null, "Please input a index and value !");
			else JOptionPane.showMessageDialog(null, "Please input a index !");
		}
		else if(value.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please input a value !");
		}
		else {
			if (tryParse(index) == null)
				JOptionPane.showMessageDialog(null, "Please input a valid index!");
			else {
				if (tryParse(index) > list.size() || tryParse(index)<0)
					JOptionPane.showMessageDialog(null, "Wrong index!");
				else {
					indexInsert.clear();
					valueInsert.clear();
					list.add(tryParse(index), value);
					printList();
				}
			}
		}
	}

	public void deleteItem(ActionEvent event) {
		String index = indexDelete.getText();
		if (index.isEmpty())
			JOptionPane.showMessageDialog(null, "Please input a value !");
		else {
			if (tryParse(index) == null)
				JOptionPane.showMessageDialog(null, "Please input a valid index!");
			else {
				System.out.println(list.size());
				int id = tryParse(index);
				if (id >= list.size() || id < 0)
					JOptionPane.showMessageDialog(null, "Wrong index!");
				else {
					indexDelete.clear();
					list.remove(id);
					grid.getChildren().clear();
					printList();
				}
			}
		}
	}

	public void clearAll() {
		list.clear();
		grid.getChildren().clear();
	}

	public void printList() {
		Iterator<String> itr = list.iterator();
		int k = 0;
		while (itr.hasNext()) {
			indexOn(k, itr.next());
			k++;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}

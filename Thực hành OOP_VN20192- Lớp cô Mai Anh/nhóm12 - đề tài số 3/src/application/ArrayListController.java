package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ArrayListController {

	ArrayList<Label> listPane = new ArrayList<Label>();

	@FXML
	private AnchorPane arrayListPane;
	@FXML
	private TextField inputValueAdd, inputValueInsert, inputIndexInsert, inputIndexDelete;
	@FXML
	private Label status,valueQuantity;


	public void back(ActionEvent event) throws IOException {
		AnchorPane backMenu = FXMLLoader.load(getClass().getResource("Main.fxml"));
		arrayListPane.getChildren().setAll(backMenu);
	}

	// add item for arrayList
	public void addItem(ActionEvent event) {
		if (inputValueAdd.getText().isEmpty())
			status.setText("You have not entered anything");
		else if (listPane.size() > 11) {
			status.setText("Your arrayList is FULL");
		} else {
			Label label = createNote(listPane.size(), inputValueAdd.getText());
			listPane.add(label);
			arrayListPane.getChildren().add(label);
			status.setText("'" + inputValueAdd.getText() + "' was added");
			valueQuantity.setText(Integer.toString(listPane.size()));
			inputValueAdd.clear();
		}
	}

	// insert item for arrayList
	public void insertItem(ActionEvent event) {

		if (inputIndexInsert.getText().isEmpty() || !testInt(inputIndexInsert.getText())) {
			status.setText("Index must be a number ");
			inputIndexInsert.clear();
		} else if (listPane.size() > 11) {
			status.setText("Your arrayList is FULL!!!! Can't insert");
			inputIndexInsert.clear();
			inputValueInsert.clear();
		} else if (inputValueInsert.getText().isEmpty())
			status.setText("Input value, please !");
		else if (Integer.parseInt(inputIndexInsert.getText()) > listPane.size()){
			status.setText(" Index bigger than size of Array List!");
			inputIndexInsert.clear();
		}

		else {
			int index = Integer.parseInt(inputIndexInsert.getText());
			Label label = createNote(index, inputValueInsert.getText());
			for (int i = index; i < listPane.size(); i++) {
				right(listPane.get(i));
			}
			arrayListPane.getChildren().add(label);
			listPane.add(index, label);
			status.setText("'" + inputValueInsert.getText() + "' was added");
			valueQuantity.setText(Integer.toString(listPane.size()));
			inputValueInsert.clear();
			inputIndexInsert.clear();
		}
	}

	// delete item for arrayList

	public void deleteItem(ActionEvent event) {
		if (inputIndexDelete.getText().isEmpty() || !testInt(inputIndexDelete.getText())) {
			status.setText("Index must be a number!");
			inputIndexDelete.clear();
		} else {
			int index = Integer.parseInt(inputIndexDelete.getText());
			if (index > listPane.size()) {
				status.setText("Input index wrong! (index >= quantity)");
			} else {
				String valueDelete = listPane.get(index).getText();
				arrayListPane.getChildren().remove(listPane.get(index));
				listPane.remove(index);
				status.setText(valueDelete + " was deleted");
				valueQuantity.setText(Integer.toString(listPane.size()));
				for (int i = index; i < listPane.size(); i++) {
					left(listPane.get(i));
				}
			}
			inputIndexDelete.clear();
		}
	}

	public Label createNote(int x, String text) {
		Label label = new Label(text);
		label.setPrefSize(55, 55);
		label.setFont(new Font("System",20));
		label.setStyle("-fx-background-color: #ffbd08");
		label.setLayoutX(110 + x * 66);
		label.setLayoutY(360);
		label.setAlignment(Pos.CENTER);
		return label;
	}

	public void right(Label label) {
		TranslateTransition tran = new TranslateTransition();
		tran.setDuration(Duration.seconds(0.25));
		tran.setByX(66);
		tran.setNode(label);
		tran.play();
	}

	public void left(Label label) {
		TranslateTransition tran = new TranslateTransition();
		tran.setDuration(Duration.seconds(0.5));
		tran.setByX(-66);
		tran.setNode(label);
		tran.play();
	}

	static boolean testInt(String str) {
		for (int i = 0; i < str.length(); i++) {
			if ((i == 0) && (str.charAt(i) == '-'))
				continue;
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}
}
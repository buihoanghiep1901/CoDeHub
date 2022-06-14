package application;

import java.io.IOException;
import java.util.LinkedList;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class HashTableController {


	private LinkedList<Label>[] hash = new LinkedList[10];

	private LinkedList<Line>[] line = new LinkedList[10];

	private static int counts;

	@FXML
	private AnchorPane pane, pane2;
	@FXML
	private TextField valueInsert, valueFind, valueDelete;
	@FXML
	private Label status, index0, index1, index2, index3, index4,
					index5, index6, index7, index8, index9;

	public void nextHashTable() throws IOException {
		AnchorPane next = FXMLLoader.load(getClass().getResource("HashTable.fxml"));
		pane.getChildren().setAll(next);
	}

	public void insert() {
		for (int i=0; i<10; i++) {
			if (hash[i] == null) {
				hash[i] = new LinkedList<Label>();
			}
			if (line[i] == null) {
				line[i] = new LinkedList<Line>();
			}
		}
		reset();
		if ((!valueInsert.getText().equals("")) && (isInt(valueInsert.getText()))) {
			int number = Integer.parseInt(valueInsert.getText());
			if (!hasNumber(number)) {
				int index = tranIndex(number);
				status.setText(number+" has been added to the index "+index );
				switch (index) {
				case 0:
					red(index0);
					createInsert(index , number);
					break;
				case 1:
					red(index1);
					createInsert(index , number);
					break;
				case 2:
					red(index2);
					createInsert(index , number);
					break;
				case 3:
					red(index3);
					createInsert(index , number);
					break;
				case 4:
					red(index4);
					createInsert(index , number);
					break;
				case 5:
					red(index5);
					createInsert(index , number);
					break;
				case 6:
					red(index6);
					createInsert(index , number);
					break;
				case 7:
					red(index7);
					createInsert(index , number);
					break;
				case 8:
					red(index8);
					createInsert(index , number);
					break;
				case 9:
					red(index9);
					createInsert(index , number);
					break;

				}
			} else {
				int index = tranIndex(number);
				Label label = hash[index].get(counts);
				label.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(90), Insets.EMPTY)));
				label.setTextFill(Color.WHITE);
				status.setText("The value has been added earlier.");
				valueInsert.clear();
			}
		} else {
			status.setText("Invalid");
			valueInsert.clear();
		}
	}

	public void find() {
		reset();
		if ((!valueFind.getText().equals("")) && (isInt(valueFind.getText()))) {
			int number = Integer.parseInt(valueFind.getText());
			if (hasNumber(number)) {
				int index = tranIndex(number);
				status.setText(number + " belongs to index " +index);
				valueFind.clear();
				switch (index) {
				case 0:
					red(index0);
					break;
				case 1:
					red(index1);
					break;
				case 2:
					red(index2);
					break;
				case 3:
					red(index3);
					break;
				case 4:
					red(index4);
					break;
				case 5:
					red(index5);
					break;
				case 6:
					red(index6);
					break;
				case 7:
					red(index7);
					break;
				case 8:
					red(index8);
					break;
				case 9:
					red(index9);
					break;
				}
				Label label = hash[index].get(counts);
				label.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(90), Insets.EMPTY)));
				label.setTextFill(Color.WHITE);
			} else {
				status.setText("This value is not available.");
				valueFind.clear();
			}
		} else {
			status.setText("Invalid");
			valueFind.clear();
		}
	}

	public void delete() {
		reset();
		if ((!valueDelete.getText().equals("")) && (isInt(valueDelete.getText()))) {
			int number = Integer.parseInt(valueDelete.getText());
			if (hasNumber(number)) {
				int index = tranIndex(number);
				status.setText(number + " has been removed from index " + index);
				valueDelete.clear();
				switch (index) {
				case 0: red(index0);	deleteNote(0, number);	break;
				case 1: red(index1);	deleteNote(1, number); 	break;
				case 2: red(index2);	deleteNote(2, number); 	break;
				case 3: red(index3);	deleteNote(3, number); 	break;
				case 4: red(index4);	deleteNote(4, number); 	break;
				case 5: red(index5);	deleteNote(5, number); 	break;
				case 6: red(index6);	deleteNote(6, number); 	break;
				case 7: red(index7);	deleteNote(7, number); 	break;
				case 8: red(index8);	deleteNote(8, number); 	break;
				case 9: red(index9);	deleteNote(9, number); 	break;
				}
			} else {
				status.setText("This value is not available.");
				valueDelete.clear();
			}
		} else {
			status.setText("Invalid");
			valueDelete.clear();
		}
	}

	public void back() throws IOException {
		AnchorPane backMain = FXMLLoader.load(getClass().getResource("Main.fxml"));
		pane.getChildren().setAll(backMain);
	}

	private void createInsert(int index, int number/*, double Y*/) {
		if (hash[index].size()<5) {
			Label label = new Label(valueInsert.getText());
			Line lines = new Line();
			lines.setStartY(index*59+34);
			lines.setEndY(index*59+34);
			valueInsert.clear();
			hash[index].addLast(label);
			pane2.getChildren().add(label);
			pane2.getChildren().add(lines);
			label.setFont(new Font("Tahoma", 15));
			label.setPrefSize(49, 49);
			label.setAlignment(Pos.CENTER);
			label.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(90), Insets.EMPTY)));
			label.setTextFill(Color.WHITE);
			label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(90), new BorderWidths(2))));
			label.setLayoutY(10+index*59);
			switch (hash[index].size()) {
			case 1: label.setLayoutX(229);
				lines.setStartX(149);
				lines.setEndX(229); break;
			case 2: label.setLayoutX(298);
				lines.setStartX(278);
				lines.setEndX(298); break;
			case 3: label.setLayoutX(367);
				lines.setStartX(347);
				lines.setEndX(367); break;
			case 4: label.setLayoutX(436);
				lines.setStartX(416);
				lines.setEndX(436); break;
			case 5: label.setLayoutX(505);
				lines.setStartX(485);
				lines.setEndX(505); break;
			}
			lines.setStroke(Color.RED);
			line[index].add(lines);
		} else {
			status.setText("Index was full");
			valueInsert.clear();
		}
	}


	private void deleteNote(int index, int number) {
		int count = -1;
		for (int i=0; i<hash[index].size(); i++) {
			if (number == Integer.parseInt(hash[index].get(i).getText())) {
				count = i;
				break;
			}
		}
		Label label = hash[index].get(count);
		label.setText(null);
		label.setBorder(null);
		hash[index].remove(count);
		for (int i=count; i<hash[index].size(); i++) {
			TranslateTransition transition = new TranslateTransition();
			transition.setDuration(Duration.seconds(1));
			transition.setNode(hash[index].get(i));
			transition.setByX(-69);
			transition.play();
		}
		Line lines = line[index].getLast();
		lines.setStroke(Color.web("#FCF3CF"));
		line[index].removeLast();
	}

	private void red(Label label){
		label.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		label.setTextFill(Color.WHITE);
	}

	private void reset() {
		backIndexFree(index0);
		backIndexFree(index1);
		backIndexFree(index2);
		backIndexFree(index3);
		backIndexFree(index4);
		backIndexFree(index5);
		backIndexFree(index6);
		backIndexFree(index7);
		backIndexFree(index8);
		backIndexFree(index9);

		for (int i=0; i<10; i++) {
			if (hash[i] != null) {
				for (int j=0; j<hash[i].size(); j++) {
					Label label = hash[i].get(j);
					label.setBackground(null);
					label.setTextFill(Color.BLACK);
				}
			}
		}
	}

	private void backIndexFree(Label label){
		label.setBackground(new Background(new BackgroundFill(Color.web("#85C1E9"), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
		label.setTextFill(Color.BLACK);
	}

	private int tranIndex(int number) {
		int count=0, sum=0, n=0, index;
		int temp = number;
		while (temp>=1) {
	        temp/=10;
	        count++;
	    }
		Integer[] digit = new Integer[count];
		while (number>=1) {
			int mod = number%10;
			digit[n] = mod;
			number/=10;
			sum += digit[n];
			n++;
		}
		index = sum%10;
		return index;
	}

	private boolean hasNumber(int number) {
		int index = tranIndex(number);
		if (hash[index] != null) {
			for (int j = 0; j < hash[index].size(); j++) {
				if (number == Integer.parseInt(hash[index].get(j).getText())) {
					counts = j;
					return true;
				}
			}
		}
		return false;
	}

	static boolean isInt(String str) {
		for (int i=0; i<str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) return false;
		}
		return true;
	}
}
package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import datastructure.queue.Queue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerQueue implements Initializable {
	@FXML
	private TextField input; // textfield
	@FXML
	private Button enQueue; // enQueue button
	@FXML
	private Button deQueue; // deQueue button
	@FXML
	private Button no1, no2, no3, no4, no5, no6, no7, no8, no9; // 9 value of queue
	@FXML
	private Label la_head, la_message, la_display; // label to show mess, display

	private Button[] numberButton = new Button[9]; // array contains 9 buttons

	Queue q = new Queue();

	private int WIDTH = 68; // WIDTH of button

	// set array to facilitate index access
	public void setArray() {
		numberButton[0] = no1;
		numberButton[1] = no2;
		numberButton[2] = no3;
		numberButton[3] = no4;
		numberButton[4] = no5;
		numberButton[5] = no6;
		numberButton[6] = no7;
		numberButton[7] = no8;
		numberButton[8] = no9;
	}

	// set value of button
	public void setValue(Button temp, String str) {
		temp.setText(str);
		temp.setVisible(true);
		temp.setMaxWidth(WIDTH);
		temp.setMinWidth(WIDTH);
	}

	// update the queue's row
	public void updateQueue() {
		for (int i = 0; i < q.size(); i++) {
			setValue(numberButton[i], numberButton[i + 1].getText());
		}
		numberButton[q.size()].setVisible(false);
	}

	// enQueue action
	public void enQueue(ActionEvent event) {
		// set mess and display dequeue value = false
		la_message.setVisible(false);
		la_display.setText("");

		// get string from input
		String str = input.getText();
		str = str.replaceAll("\\s", ""); // input processing to take space character

		// check input if is space character don't add
		if (str.equals("") == false) {
			// if it's new queue, setArray and make head label visible
			if (no1.isVisible() == false) {
				setArray();
				la_head.setVisible(true);
			}

			// set value for head
			if (q.enQueue(str) == 1) {
				setValue(numberButton[q.size() - 1], str);
				input.clear();
			}
		}
	}

	// deQueue action
	public void deQueue(ActionEvent event) {
		// take string from head of queue
		String deQueue_String = q.deQueue();

		// if string is not NULL, show message and display value for deQueue
		if (deQueue_String != "NULL") {
			la_message.setVisible(true);
			la_display.setText(deQueue_String);
			updateQueue();
		}
		if (q.size() == 0)
			la_head.setVisible(false);
	}

	public void clearQueue(ActionEvent event) {
		for (int i = 0; i < q.size(); i++) {
			numberButton[i].setVisible(false);
		}
		la_display.setText("");
		la_head.setVisible(false);
		la_message.setVisible(false);
		q.deleteQueue();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}

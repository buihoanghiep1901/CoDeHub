package datastructure.queue;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Queue {
	public LinkedList<String> queue = new LinkedList<String>();

	// Return size of List
	public int size() {
		return queue.size();
	}

	// List is empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// List is full?
	public boolean isFull() {
		return size() == 9;
	}

	// Return 0 if Queue full and 1 if add successfully
	public int enQueue(String str) {
		if (isFull()) {
			JOptionPane.showMessageDialog(null, "Queue is FULL!");
			return 0;
		} else {
			queue.addLast(str);
			return 1;
		}
	}

	// Return NULL if queue empty unless return string of head
	public String deQueue() {
		if (isEmpty()) {
			JOptionPane.showMessageDialog(null, "Queue is EMPTY!");
			return "NULL";
		}
		return queue.remove(0);
	}

	// delete queue
	public void deleteQueue() {
		queue.clear();
	}
}

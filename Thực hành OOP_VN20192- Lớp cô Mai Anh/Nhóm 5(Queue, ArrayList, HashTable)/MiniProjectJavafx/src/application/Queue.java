package application;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Queue {
	public LinkedList <String> queue = new LinkedList <String>();
    
    public int size() {
        return queue.size();
      }
    public void enQueue(String str) {
    	if(queue.size()>=12) {
    		JOptionPane.showMessageDialog(null,"Queue is FULL!");
    	}else {
            queue.offer(str);
    	}
    } 
    public void deQueue() {
		if(queue.size() == 0) {
			JOptionPane.showMessageDialog(null,"Queue is EMPTY!");
		}else {
			queue.removeFirst();
		}
    }	 
    public void deleteQueue() {
    	queue.clear();
    }
}

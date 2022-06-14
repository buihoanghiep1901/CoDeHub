package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
	private Button no1,no2,no3,no4,no5,no6,no7,no8,no9,no10,no11,no12,no13,no14;
	@FXML
	private Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14;
	
	public ArrayList<String> list = new ArrayList<String>();
	
	public void indexOn(Integer num,String str) {
		final int WIDTH = 68;
		if(num==1) {
			no1.setText(str);
			no1.setVisible(true);
			l1.setVisible(true);
			no1.setMaxWidth(WIDTH);
			no1.setMinWidth(WIDTH);

		}
		if(num==2) {
			no2.setText(str);
			no2.setVisible(true);
			l2.setVisible(true);
			no2.setMaxWidth(WIDTH);
			no2.setMinWidth(WIDTH);
		}
		if(num==3) {
			no3.setText(str);
			no3.setVisible(true);
			l3.setVisible(true);
			no3.setMaxWidth(WIDTH);
			no3.setMinWidth(WIDTH);
		}
		if(num==4) {
			no4.setText(str);
			no4.setVisible(true);
			l4.setVisible(true);
			no4.setMaxWidth(WIDTH);
			no4.setMinWidth(WIDTH);
		}
		if(num==5) {
			no5.setText(str);
			no5.setVisible(true);
			l5.setVisible(true);
			no5.setMaxWidth(WIDTH);
			no5.setMinWidth(WIDTH);
		}
		if(num==6) {
			no6.setText(str);
			no6.setVisible(true);
			l6.setVisible(true);
			no6.setMaxWidth(WIDTH);
			no6.setMinWidth(WIDTH);
		}
		if(num==7) {
			no7.setText(str);
			no7.setVisible(true);
			l7.setVisible(true);
			no7.setMaxWidth(WIDTH);
			no7.setMinWidth(WIDTH);
		}
		if(num==8) {
			no8.setText(str);
			no8.setVisible(true);
			l8.setVisible(true);
			no8.setMaxWidth(WIDTH);
			no8.setMinWidth(WIDTH);
		}
		if(num==9) {
			no9.setText(str);
			no9.setVisible(true);
			l9.setVisible(true);
			no9.setMaxWidth(WIDTH);
			no9.setMinWidth(WIDTH);
		}
		if(num==10) {
			no10.setText(str);
			no10.setVisible(true);
			l10.setVisible(true);
			no10.setMaxWidth(WIDTH);
			no10.setMinWidth(WIDTH);
		}
		if(num==11) {
			no11.setText(str);
			no11.setVisible(true);
			l11.setVisible(true);
			no11.setMaxWidth(WIDTH);
			no11.setMinWidth(WIDTH);
		}
		if(num==12) {
			no12.setText(str);
			no12.setVisible(true);
			l12.setVisible(true);
			no12.setMaxWidth(WIDTH);
			no12.setMinWidth(WIDTH);
		}
		if(num==13) {
			no13.setText(str);
			no13.setVisible(true);
			l13.setVisible(true);
			no13.setMaxWidth(WIDTH);
			no13.setMinWidth(WIDTH);
		}
		if(num==14) {
			no14.setText(str);
			no14.setVisible(true);
			l14.setVisible(true);
			no14.setMaxWidth(WIDTH);
			no14.setMinWidth(WIDTH);
		}
	}
	
	public void indexOff(Integer num) {
		if(num==1) {
			no1.setVisible(false);
			l1.setVisible(false);
		}
		if(num==2) {
			no2.setVisible(false);
			l2.setVisible(false);
		}
		if(num==3) {
			no3.setVisible(false);
			l3.setVisible(false);

		}
		if(num==4) {
			no4.setVisible(false);
			l4.setVisible(false);
		}
		if(num==5) {
			no5.setVisible(false);
			l5.setVisible(false);
		}
		if(num==6) {
			no6.setVisible(false);
			l6.setVisible(false);
		}
		if(num==7) {
			no7.setVisible(false);
			l7.setVisible(false);
		}
		if(num==8) {
			no8.setVisible(false);
			l8.setVisible(false);
		}
		if(num==9) {
			no9.setVisible(false);
			l9.setVisible(false);
		}
		if(num==10) {
			no10.setVisible(false);
			l10.setVisible(false);
		}
		if(num==11) {
			no11.setVisible(false);
			l11.setVisible(false);
		}
		if(num==12) {
			no12.setVisible(false);
			l12.setVisible(false);
		}
		if(num==13) {
			no13.setVisible(false);
			l13.setVisible(false);
		}
		if(num==14) {
			no14.setVisible(false);
			l14.setVisible(false);
		}
	}
	
	public void addItem(ActionEvent event) {
		String str = valueAdd.getText();
		valueAdd.clear();
		list.add(str);
		printList();
	}	
	public void insertItem(ActionEvent event) {
		String index = indexInsert.getText();
		int id = Integer.parseInt(index);
		String value = valueInsert.getText();
		if(id>list.size())
			JOptionPane.showMessageDialog(null,"Wrong index!");
		indexInsert.clear();
		valueInsert.clear();
		list.add(id, value);
		
		printList();
	}
	
	public void deleteItem(ActionEvent event) {
		String index = indexDelete.getText();
		int id = Integer.parseInt(index);
		if(id>list.size()-1)
			JOptionPane.showMessageDialog(null,"Wrong index!");
		indexDelete.clear();
		list.remove(id);
		for(int i=1; i<=14;i++)
			indexOff(i);
		printList();
	}
	
	public void back() throws IOException {
		AnchorPane child = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		pane.getChildren().setAll(child);
	}
	
	public void printList() {
		Iterator<String> itr = list.iterator();
		int k=0;
	    while (itr.hasNext()) {
	       k++;
	       indexOn(k, itr.next());
	    }
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

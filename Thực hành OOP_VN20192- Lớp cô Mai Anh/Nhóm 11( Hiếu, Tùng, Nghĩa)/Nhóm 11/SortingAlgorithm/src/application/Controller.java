package application;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


import algorithm.Algorithm;
import algorithm.BubbleSort;
import algorithm.SelectionSort;
import algorithm.BucketSort;
import algorithm.MergeSort;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;

import items.itemArray;
import items.Constant;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

///////////

public class Controller implements Initializable {
	@FXML
	private GridPane fatherPane;
    @FXML
    private Pane itemPane;
	@FXML
	private JFXToggleButton setSelectionSort;
	@FXML
	private JFXToggleButton setBucketSort;
	@FXML
	private JFXToggleButton setMergeSort;
	@FXML
	private JFXToggleButton setBubbleSort;
	@FXML
	private JFXButton PlayButton;
	@FXML
	private JFXSlider setSpeedSlider;
	@FXML
	private JFXSlider setNumber;
	@FXML
	private HBox bucketbox;
	@FXML 
	private Pane numPane;
	private itemArray array;	
	private Algorithm Algo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setSelectionSort.setSelected(true);
		Algo = SelectionSort.getSelection();
        setNumber.setMax(20);
        setNumber.setMin(4);
        setNumber.setValue(12);
        setSpeedSlider.setMax(6);
        setSpeedSlider.setMin(1);
        setSpeedSlider.setValue(3);
        bucketbox.setVisible(false);
        
		
		
	}
//	public Array bucketarray;
/*	private Rectangle[] bucketarray = {bucket0,bucket1, bucket2, bucket3, bucket4, bucket5, bucket6, bucket7, bucket8, bucket9};

	public Rectangle[] getBucketArray(){
		return bucketarray.clone();	}
	public Rectangle getRec(Rectangle[] array, int i) {
		return array[i];
		
	}*/
	public void setBubble() {
		setSelectionSort.setSelected(false);
		setBucketSort.setSelected(false);
		setMergeSort.setSelected(false);
		setBubbleSort.setSelected(true);
		Algo = BubbleSort.getBubble();
		bucketbox.setVisible(false);
		//
	}
	public void setSelection() {
		setSelectionSort.setSelected(true);
		setBucketSort.setSelected(false);
		setMergeSort.setSelected(false);
		setBubbleSort.setSelected(false);
		bucketbox.setVisible(false);
		Algo = SelectionSort.getSelection();
		//
	}
	public void setMerge() {
		setSelectionSort.setSelected(false);
		setBucketSort.setSelected(false);
		setMergeSort.setSelected(true);
		setBubbleSort.setSelected(false);
		bucketbox.setVisible(false);
		Algo = MergeSort.getMerge();
		//
	}
	public void setBucket() {
		setSelectionSort.setSelected(false);
		setBucketSort.setSelected(true);
		setMergeSort.setSelected(false);
		setBubbleSort.setSelected(false);
		bucketbox.setVisible(true);
		Algo = BucketSort.getBucket();
//		bucket0.setWidth(70);
		//
	}
	
    public void ResetClicked() {
    	if (array != null) {
            itemPane.getChildren().removeAll(array.getAllpane());
            array.steps.stop();
            array = null;
        }

       int  number = (int) setNumber.getValue();
       int speed = (int)setSpeedSlider.getValue();
        Constant.TIME = 6 / speed;
        array = new itemArray(number);        
        itemPane.getChildren().addAll(array.getAllpane());
        itemPane.setLayoutX((740-number*35)/2);        
        Algo.sort(array);
        ResetState();

    	}


    public void PlayClicked() {
        if (array == null) return;

        if (array.steps.isPlaying) {
            array.steps.pause();
            PauseState();
            return;
        }

        PlayState();

        array.steps.play();
    }

    private void PauseState() {
    	PlayButton.setText("PLAY");
		
	}
	private void PlayState() {
        PlayButton.setText("PAUSE");
    }
	private void ResetState() {
		PlayButton.setText("PLAY");
		
	}
    public void close() {
    	System.exit(0);

    	}
	

}

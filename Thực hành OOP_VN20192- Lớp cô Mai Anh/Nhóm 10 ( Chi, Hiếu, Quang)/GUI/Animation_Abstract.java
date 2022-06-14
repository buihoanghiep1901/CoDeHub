package GUI;

import CNode.Random_Abstract;
import CNode.SetSize;
import CNode.Shapee;
import SortingAlgorithms.AbstractSort;
import SortingAlgorithms.InsertionSort;
import SortingAlgorithms.ShellSort;
import javafx.animation.SequentialTransition;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Animation_Abstract extends BorderPane {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 500;
	public static final int XGAP = 2;
	public static final int BUTTONROW_BOUNDARY = 80;
	public static int NO_OF_CNODES = 10;
	public static int DELAY = 40;
	public static int arrayAccesses;
	public static int comparisons;
	private HBox display;
	private HBox optionsPanel;
	private AbstractSort abstractSort;
	private Button sortButton;
	private Button randomButton;
	private Button back;
	private Slider delaySlider;
	private Label delayLbl;
	private static Label comparisonsLbl, arrayAccessesLbl;

	private Shapee[] arr;
	public static Shapee.Type Shapee_TYPE = Shapee.Type.COLUMN;

	public Animation_Abstract(int a) {
		

		if(a==0 || a ==2) { 
			SetSize.SHAPEE_TYPE=Shapee.Type.COLUMN;
		} else SetSize.SHAPEE_TYPE=Shapee.Type.RINK;
		
		this.arr =  Random_Abstract.randomNode(NO_OF_CNODES);
		this.setStyle("-fx-background-color: #1c1c1c");
		this.optionsPanel = new HBox();
		this.optionsPanel.setAlignment(Pos.CENTER);
		this.optionsPanel.setPrefHeight(BUTTONROW_BOUNDARY);
		this.optionsPanel.setSpacing(10);
		this.display = new HBox();
		this.setCenter(display);
		this.setBottom(optionsPanel);
		this.sortButton = new Button("Sort");
		this.sortButton.setPrefWidth(100);
		this.randomButton = new Button("Random");
		this.randomButton.setPrefWidth(100);

		optionsPanel.getChildren().add(sortButton);
		optionsPanel.getChildren().add(randomButton);
		back = new Button("Exit");
		back.setPrefWidth(100);
		back.setAlignment(Pos.CENTER);
		back.setOnAction(e->{
			System.exit(0);
			;
		});

		Separator sep2 = new Separator();
		sep2.setOrientation(Orientation.VERTICAL);
		optionsPanel.getChildren().add(sep2);
		
		VBox delayBox = new VBox();
		delayBox.setAlignment(Pos.CENTER);
		delaySlider = new Slider();
		delaySlider.setMin(0);
		delaySlider.setMax(500);
		delaySlider.setValue(DELAY);
		delaySlider.setShowTickLabels(true);
		delaySlider.setMajorTickUnit(100);
		delaySlider.setMaxWidth(150);
		delayLbl = new Label("Delay: " + DELAY + " ms");
		delaySlider.valueProperty().addListener((obs, oldval, newVal) -> {
			if (newVal.intValue() < 1)
				delaySlider.setValue(1);
			else
				delaySlider.setValue(newVal.intValue());
			DELAY = (int) delaySlider.getValue();
			delayLbl.setText("Delay: " + DELAY + " ms");
		});
		delayBox.getChildren().addAll(delaySlider, delayLbl);
		optionsPanel.getChildren().add(delayBox);
		Separator sep1 = new Separator();
		sep1.setOrientation(Orientation.VERTICAL);
		optionsPanel.getChildren().add(sep1);
		optionsPanel.getChildren().add(back);
		
		
		resetCounters();


		if(a== 0 || a == 2 ) {
			SetSize.SHAPEE_TYPE=Shapee.Type.COLUMN;
		} else SetSize.SHAPEE_TYPE=Shapee.Type.RINK;
		this.arr =  Random_Abstract.randomNode(NO_OF_CNODES);
		
		

		for(int i=0; i<arr.length;i++)
		{
		display.getChildren().addAll(arr[i].getShape());
		}
		sortButton.setOnAction(event -> {
			sortButton.setDisable(true);
			randomButton.setDisable(true);
			delaySlider.setDisable(true);

			if (a == 0 || a ==1) {
				abstractSort = new ShellSort();
			  } else abstractSort =(new InsertionSort());


			SequentialTransition sq = new SequentialTransition();
			sq.getChildren().addAll(abstractSort.startSort(arr));
			sq.setOnFinished(e -> {
				randomButton.setDisable(false);
				delaySlider.setDisable(false);

			});

			sq.play();

		});

		randomButton.setOnAction(event -> {
			sortButton.setDisable(false);
			display.getChildren().clear();
			if(a==0 || a == 2) { 
				SetSize.SHAPEE_TYPE=Shapee.Type.COLUMN;
			} else SetSize.SHAPEE_TYPE=Shapee.Type.RINK;
				
			this.arr =  Random_Abstract.randomNode(NO_OF_CNODES);
			for(int i=0; i<arr.length;i++)
			{
			display.getChildren().addAll(arr[i].getShape());
			}
		
		});
	}

	private void resetCounters() {
		comparisons = 0;
		arrayAccesses = 0;
	}

	public static void updateCompLbl() {
		comparisonsLbl.setText("Comparisons: " + comparisons);
	}

	public static void updateArrayAccessesLbl() {
		arrayAccessesLbl.setText("Array-Accesses: " + arrayAccesses);
	}

	public static void incrementComparisons() {
		comparisons++;
		updateCompLbl();
	}

	public static void incrementArrayAccesses() {
		arrayAccesses++;
		updateArrayAccessesLbl();
	}
}

package GUI;

import HNTower.HNTower;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	private Label NO_OF_CNODES_Lbl;
	private Scene scene, startScene;
	private Animation_Abstract animationController;
	private Button Shell;
	private Button Insert;
	private Button Tower;
	private Button Exit;
	@FXML 
	private Button Info;
	@Override
	public void start(Stage stage) {

		VBox startBox = new VBox();
		startBox.setStyle("-fx-background-color: #1c1c1c");
		startBox.setSpacing(20);
		startBox.setAlignment(Pos.CENTER);
		
		VBox amountBox = new VBox();
		amountBox.setAlignment(Pos.CENTER);
		amountBox.setSpacing(2);
	
		Slider amountSlider = new Slider();
		amountSlider.setMin(5);
		amountSlider.setMax(30);
		amountSlider.setValue(Animation_Abstract.NO_OF_CNODES);
		amountSlider.setMaxWidth(150);
		NO_OF_CNODES_Lbl = new Label("Amount: " + String.valueOf((int) amountSlider.getValue()));
		amountSlider.valueProperty().addListener((obs, oldval, newVal) -> {
			Animation_Abstract.NO_OF_CNODES = newVal.intValue();
			NO_OF_CNODES_Lbl.setText("Amount: " + Animation_Abstract.NO_OF_CNODES);
		});
		
		ChoiceBox<String> recsOrDotsSelection = new ChoiceBox<String>();
		recsOrDotsSelection.setPrefWidth(150);
		recsOrDotsSelection.setItems(FXCollections.observableArrayList("Bars", "Dots"));
		recsOrDotsSelection.getSelectionModel().select(0);
		
		amountBox.getChildren().addAll(amountSlider, NO_OF_CNODES_Lbl);
		
		Exit = new Button("Exit");
		Exit.setPrefWidth(150);
		Exit.setOnAction(e->{
			System.exit(0);
		});
		
		Shell = new Button("Shell Sort");
		Shell.setPrefWidth(150);
		Shell.setOnAction(e->{
			if(recsOrDotsSelection.getSelectionModel().getSelectedIndex() == 0) {
			
				animationController = new Animation_Abstract(0);
				
			} else if (recsOrDotsSelection.getSelectionModel().getSelectedIndex() == 1) {
	
				animationController = new Animation_Abstract(1);
			}
			scene = new Scene(animationController, Animation_Abstract.WINDOW_WIDTH, Animation_Abstract.WINDOW_HEIGHT);
			scene.getStylesheets().add("GUI/SortingVisualizerStylesheet.css");
			stage.setScene(scene);
		});
		
		Insert = new Button("Insert Sort");
		Insert.setPrefWidth(150);
		Insert.setOnAction(e->{
			if(recsOrDotsSelection.getSelectionModel().getSelectedIndex() == 0) {
				
				animationController = new Animation_Abstract(2);
				
			} else if (recsOrDotsSelection.getSelectionModel().getSelectedIndex() == 1) {
				
				animationController = new Animation_Abstract(3);
			}
			scene = new Scene(animationController, Animation_Abstract.WINDOW_WIDTH, Animation_Abstract.WINDOW_HEIGHT);	
			scene.getStylesheets().add("GUI/SortingVisualizerStylesheet.css");
			stage.setScene(scene);
			
		});
	
		Tower = new Button("Tower of HaNoi Problem");
		Tower.setPrefWidth(150);
		Tower.setOnAction(e->{
			new HNTower();
			
		});
		
		Info = new Button("Infomation");
		Info.setPrefWidth(150);
		
		Info.setOnAction(e->{
		Stage stageInf = new Stage();
					VBox root = new VBox();
			        root.setPadding(new Insets(10));
			        root.setSpacing(5);
			        Label a = new Label("PROJECT JAVA - OOP\n Giảng Viên Hướng Dẫn: TS. Bùi Thị Mai Anh\n Sinh Viên Thực Hiện:\n\t Đặng Linh Chi       - 20176700\n\t Phạm Minh Hiếu   - 20176758\n\t Trần Minh Quang  - 20176858");
			        a.setTextFill(Color.WHITE);			       
			        root.getChildren().add(a);
			        root.setStyle("-fx-background-color: #1c1c1c");
			        		 
			        Scene scene = new Scene(root, 320, 150);
			 
			        stageInf.setTitle("Infomation");
			        stageInf.setScene(scene);

					stageInf.show();		
		});
		startBox.getChildren().add(Insert);
		startBox.getChildren().add(Shell);
		startBox.getChildren().add(recsOrDotsSelection);
		startBox.getChildren().add(amountBox);
		startBox.getChildren().add(Tower);
		startBox.getChildren().add(Exit);
		startBox.getChildren().add(Info);
		startScene = new Scene(startBox, 800, 500);
		startScene.getStylesheets().add("GUI/SortingVisualizerStylesheet.css");
		stage.setResizable(false);
		stage.setX(400);
		stage.setTitle("Visual Sorting Algorithms");
		stage.setScene(startScene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}

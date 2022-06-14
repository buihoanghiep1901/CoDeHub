package controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;

import aStarAlgorithm.Settings;
import aStarAlgorithm.grid.Cell;
import aStarAlgorithm.grid.CellMark;
import aStarAlgorithm.grid.CellType;
import aStarAlgorithm.grid.Grid;
import aStarAlgorithm.grid.Wrapper;
import aStarAlgorithm.input.MouseDragGestures;
import aStarAlgorithm.input.MousePaintGestures;

public class SceneController {
	// Chuyen sang Scene Sequential Search
	public void ChangeSequentialSearchScene(ActionEvent event) throws IOException {
		// lay stage
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// tao ra loader de load LinearSeach.fxml
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/SequentialSearch.fxml"));
		Parent sequentialSeach = loader.load();
		Scene scene = new Scene(sequentialSeach, 1280, 720);
		scene.getStylesheets().add(getClass().getResource("../view/SequentialSearch.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Sequential Search");
	}

	// Chuyen sang Scene Binary Search
	public void ChangeBianrySearchScene(ActionEvent event) throws IOException {
		// lay stage
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		// tao ra loader de load LinearSeach.fxml
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/BinarySearch.fxml"));
		Parent binarySeach = loader.load();
		Scene scene = new Scene(binarySeach, 1280, 720);
		scene.getStylesheets().add(getClass().getResource("../view/SequentialSearch.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Binary Search");
	}

	// ---------------------------------------------------------------------------------------------------------

	// Code duoi day dung de hien thi AStar
	// Code kha loang ngoang, can phai doc ki

	Grid grid;

	BooleanProperty allowDiagonalsProperty = new SimpleBooleanProperty(true);
	BooleanProperty stepViewProperty = new SimpleBooleanProperty(Settings.STEP_VIEW);
	BooleanProperty showPathProperty = new SimpleBooleanProperty(true);

	MousePaintGestures mousePaintGestures = new MousePaintGestures();
	MouseDragGestures mouseDragGestures;

	boolean autoPath = true;
	boolean showSteps = true;

	Cell startCell;
	Cell endCell;

	Wrapper<Cell> aStarWrapper = new Wrapper<Cell>();

	Label status;
	Slider stepSlider;

	// Scene A Star

	public void ChangeAStarScene(ActionEvent event) throws IOException {

		// lay stage
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		BorderPane root = new BorderPane();

		// placeholder for the grid
		StackPane content = new StackPane();
		root.setCenter(content);

		// toolbar
		HBox toolbar = new HBox();
		toolbar.setAlignment(Pos.CENTER);
		toolbar.setPadding(new Insets(10, 10, 10, 10));
		toolbar.setSpacing(4);

		// goBack MainScene
		Button goBackButton = new Button("BACK");
		goBackButton.setOnAction(e -> {
			try {
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/MainScene.fxml"));
				Parent mainScene = loader.load();
				Scene scene = new Scene(mainScene);
				scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
				stage.setTitle("OOP Project");
				stage.setScene(scene);
			} catch (Exception ez) {
				// TODO: handle exception
				ez.printStackTrace();
			}
		});

		// remove obstacles on the grid
		Button removeObstaclesButton = new Button("Remove all obstacles");
		removeObstaclesButton.setOnAction(e -> {

			grid.setType(CellType.TRAVERSABLE);

			if (autoPath) {
				showPath();
			}
		});

		// fill obstacles button
		Button fillObstaclesButton = new Button("Fill with obstacles");
		fillObstaclesButton.setOnAction(e -> {

			grid.setType(CellType.OBSTACLE);

			// start and end mustn't be OBSTACLE
			grid.getCell(startCell.getColumn(), startCell.getRow()).setType(CellType.TRAVERSABLE);
			grid.getCell(endCell.getColumn(), endCell.getRow()).setType(CellType.TRAVERSABLE);

			if (autoPath) {
				showPath();
			}
		});

		// find path button
		Button findPathButton = new Button("Find Path");
		findPathButton.setOnAction(e -> {
			showPath();
		});

		// show/hide path checkbox
		CheckBox showPathCheckBox = new CheckBox("Show Path");
		showPathCheckBox.selectedProperty().bindBidirectional(showPathProperty);
		showPathProperty.addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			repaintPath();
		});

		// allow/disallow diagonals in path checkbox
		CheckBox allowDiagonalsCheckBox = new CheckBox("Allow Diagonals");
		allowDiagonalsCheckBox.selectedProperty().bindBidirectional(allowDiagonalsProperty);

		allowDiagonalsProperty.addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (autoPath) {
				showPath();
			}
		});

		// show/hide the steps view (open/closed marking)
		CheckBox stepViewCheckBox = new CheckBox("Step View");
		stepViewCheckBox.selectedProperty().bindBidirectional(stepViewProperty);

		stepViewProperty.addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (autoPath) {
				showPath();
			}
		});

		// steps need information label
		Label stepLabel = new Label();

		// snapshot slider, allows you to show the marks and cell values (f,g,h of A*)
		// per A* step
		stepSlider = new Slider();
		stepSlider.setMin(0);
		stepSlider.setMax(0);
		stepSlider.setValue(0);
		stepSlider.setShowTickLabels(true);
		stepSlider.setShowTickMarks(true);
		stepSlider.setBlockIncrement(1);
		stepSlider.setPrefWidth(300);
		stepSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				stepLabel.setText("Step " + newValue.intValue());
				showStep();
			}

		});

		toolbar.setAlignment(Pos.CENTER_LEFT);

		toolbar.getChildren().addAll(goBackButton, removeObstaclesButton, fillObstaclesButton, findPathButton,
				allowDiagonalsCheckBox, showPathCheckBox, stepViewCheckBox, stepSlider, stepLabel);

		root.setTop(toolbar);

		// status
		// ----------------------
		HBox statusBar = new HBox();

		// status text
		status = new Label();
		statusBar.getChildren().add(status);

		// info for the user
		Label infoText = new Label(
				"Left mouse button = draw obstacles, right mouse button = remove obstacles. Move start and end cells via dragging. Steps: top/left=g, top/right=h, center=f of A*");
		infoText.setAlignment(Pos.CENTER_RIGHT);
		infoText.setMaxWidth(Long.MAX_VALUE);
		HBox.setHgrow(infoText, Priority.ALWAYS);
		statusBar.getChildren().add(infoText);

		root.setBottom(statusBar);

		// create scene and stage
		// ----------------------
		Scene scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("/aStarAlgorithm/css/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("A* Algorithm");

		// fullscreen
		primaryStage.setFullScreen(Settings.STAGE_FULLSCREEN);
		primaryStage.setFullScreenExitHint("");

		// scale by factor of 2 (in settings we have half-hd) to get proper dimensions
		// in fullscreen (full-hd)
		if (primaryStage.isFullScreen()) {
			Scale scale = new Scale(Settings.STAGE_FULLSCREEN_SCALE, Settings.STAGE_FULLSCREEN_SCALE);
			scale.setPivotX(0);
			scale.setPivotY(0);
			scene.getRoot().getTransforms().setAll(scale);
		}

		primaryStage.show();

		// create grid; the dimensions depend on the content, that's why we draw it
		// after the stage is shown
		// --------------------------------------------------------------------------------------------------------------
		grid = new Grid(Settings.GRID_COLUMNS, Settings.GRID_ROWS, content.getBoundsInParent().getWidth(),
				content.getBoundsInParent().getHeight());

		// fill grid with cells
		for (int row = 0; row < Settings.GRID_ROWS; row++) {
			for (int column = 0; column < Settings.GRID_COLUMNS; column++) {

//					String text = column + "/" + row;
				String text = "";
				Cell cell = new Cell(text, column, row, CellType.TRAVERSABLE);

				// allow changing of cell type my "drawing" on it (ie move pressed mouse button
				// over it)
				mousePaintGestures.makePaintable(cell);

				grid.add(cell, column, row);

			}
		}

		root.setCenter(grid);

		// show samples
		// -------------------
		// create start and end cells
		startCell = new Cell("Start", 0, 0, CellType.TRAVERSABLE);
		endCell = new Cell("End", Settings.GRID_COLUMNS - 1, Settings.GRID_ROWS - 1, CellType.TRAVERSABLE);

		// apply styles
		startCell.getStyleClass().add("start");
		endCell.getStyleClass().add("goal");

		// show over grid
		startCell.toFront();
		endCell.toFront();

		mouseDragGestures = new MouseDragGestures(grid);
		mouseDragGestures.makeDraggable(startCell);
		mouseDragGestures.makeDraggable(endCell);

		grid.addOverlay(startCell, 0, 0);
		grid.addOverlay(endCell, Settings.GRID_COLUMNS - 1, Settings.GRID_ROWS - 1);

		// create sample obstacles
		createSampleObstacles();

		// show A* path
		showPath();

		// re-draw path after mouse has been released
		grid.addEventFilter(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);
		startCell.addEventFilter(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);
		endCell.addEventFilter(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);

	}

	EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {

		if (autoPath) {
			showPath();
		}

	};

	/**
	 * Find the path, show the path and if requested also show the steps
	 * (open/closed marks) that lead to the path.
	 */
	public void showPath() {
		findPath(grid.getCell(startCell.getColumn(), startCell.getRow()),
				grid.getCell(endCell.getColumn(), endCell.getRow()));
		showStep();
	}

	/**
	 * Fill the grid with arbitrary obstacles
	 */
	private void createSampleObstacles() {

		for (int row = 0; row < Settings.GRID_ROWS; row++) {
			for (int column = 0; column < Settings.GRID_COLUMNS; column++) {

				if (row == 1 && column > 7)
					grid.getCell(column, row).setType(CellType.OBSTACLE);

				if (row == 6 && column > 7)
					grid.getCell(column, row).setType(CellType.OBSTACLE);

				if (row > 1 && row < 6 && column == 7)
					grid.getCell(column, row).setType(CellType.OBSTACLE);

				if (row >= 0 && row < 8 && column == 2)
					grid.getCell(column, row).setType(CellType.OBSTACLE);

				if (row >= 4 && row <= 10 && column == 5)
					grid.getCell(column, row).setType(CellType.OBSTACLE);

			}
		}
	}

	private void findPath(Cell startCell, Cell endCell) {

		// stopwatch start
		long startTime = System.nanoTime();

		// determine path via A* algorithm
		List<Cell> path = aStarWrapper.findPath(grid, startCell, endCell, allowDiagonalsProperty.getValue());

		// stopwatch end & update status
		long timeUsed = System.nanoTime() - startTime;

		String text = "Calc time: " + (timeUsed / 1_000_000d) + " ms";

		if (path != null) {

			text += " ... Cells in path: " + path.size();

		}
		// no valid path found
		else {

			text += " ... Path not found!";

			System.err.println("Path not found!");

		}

		status.setText(text);

		// paint the path
		paintPath(path);

		// update step slider range
		if (showSteps) {
			int count = aStarWrapper.getSnapshotCount();
			stepSlider.setMax(count);

			if (stepViewProperty.getValue()) {
				stepSlider.setValue(count);
			} else {
				stepSlider.setValue(0);
			}
		}

	}

	private void repaintPath() {
		paintPath(aStarWrapper.getPath());
	}

	private void paintPath(List<Cell> path) {

		// remove existing highlight
		grid.removeHighlight();

		// paint path
		if (showPathProperty.getValue()) {
			if (path != null) {

				// highlight path
				for (Cell cell : path) {
					cell.highlight();
				}

			}
			// no valid path found
			else {

				System.err.println("Path not found!");

			}
		}

	}

	private void showStep() {

		if (!showSteps)
			return;

		// set cell text to empty
		grid.resetText();

		// clear marks
		for (int row = 0; row < Settings.GRID_ROWS; row++) {
			for (int col = 0; col < Settings.GRID_COLUMNS; col++) {
				grid.getCell(col, row).removeMark();
			}
		}

		// paint
		int index = (int) stepSlider.getValue();
		if (index > 0) {

			int stepIndex = index - 1;

			List<Cell> openList = aStarWrapper.getOpenSnapshot(stepIndex);
			List<Cell> closedList = aStarWrapper.getClosedSnapshot(stepIndex);

			for (Cell cell : openList) {
				// show open cell mark, but don't override highlight
				cell.setMark(CellMark.OPEN);
			}
			for (Cell cell : closedList) {
				// show closed cell mark, but don't override highlight
				cell.setMark(CellMark.CLOSED);
			}
		}

	}

}
package visual;

import algo.Bubble;
import algo.Heap;
import algo.Radix;
import algo.Quick;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import algo.Algorithm;
import element.Common;
import element.Element;
import element.ElementArray;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane fatherPane;

    @FXML
    private JFXComboBox<Algorithm> comboBox;

    @FXML
    private JFXSlider numberSlider;

    @FXML
    private JFXSlider speedSlider;

    @FXML
    private JFXButton playButton;

    @FXML
    private JFXButton backwardButton;

    @FXML
    private JFXButton forwardButton;

    @FXML
    private Label stepLabel;

//    @FXML
//    private JFXRadioButton columnRadioButton;

    @FXML
    private JFXRadioButton boxRadioButton;

    private ElementArray array;
    private ElementArray array1;
    private ElementArray array2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // ComboBox custom init
        Algorithm [] algos = {Bubble.getInstance(), Quick.getInstance(), Heap.getInstance(), Radix.getInstance()};
        Objects.requireNonNull(algos);
        ObservableList<Algorithm> algoList = FXCollections.observableArrayList(Arrays.asList(algos));
        comboBox.setItems(algoList);

        // Slider setting
        numberSlider.setMax(21);
        numberSlider.setMin(1);
        numberSlider.setMajorTickUnit(5);
        numberSlider.setMinorTickCount(5);

        speedSlider.setMax(5);
        speedSlider.setMin(1);



        // Customize
        comboBox.getSelectionModel().select(0);
        numberSlider.setValue(9);
//        speedSlider.setValue(4);
        stepLabel.setText("");
    }

    public void handleResetClicked() {
        if (array != null) {
            //fatherPane.getChildren().removeAll(array.getAllShape());
        	fatherPane.getChildren().clear();
            array.steps.stop();
            array = null;
        }


        int numberOfElement = (int)numberSlider.getValue();
        int speed = (int)speedSlider.getValue();

        Common.DURATION = Common.DURATION_MAX / speed;

        array = new ElementArray(numberOfElement);

        array.steps.setLabel(stepLabel);

        fatherPane.getChildren().addAll(array.getAllShape());


        
        if(comboBox.getValue() instanceof Radix){
            comboBox.getValue().sort(array);

            array1 = ((Radix) comboBox.getValue()).getArray1();
            fatherPane.getChildren().addAll(array1.getAllShape());
            array2 = ((Radix) comboBox.getValue()).getArray2();
            fatherPane.getChildren().addAll(array2.getAllShape());
            backwardButton.setDisable(true);
            //playButton.setDisable(true);
            forwardButton.setDisable(true);
//            stopButton.setDisable(true);
        } 
        else if(comboBox.getValue() instanceof  Heap){
            comboBox.getValue().sort(array);
            fatherPane.getChildren().addAll(array.getAllShape2());
            pressedResetState();
        }
        else {
            comboBox.getValue().sort(array);
            pressedResetState();
        }
    }

    public void handleBackwardClicked () {
        if (array == null) return;

        array.steps.backward();
    }

    public void handleForwardClicked() {
        if (array == null) return;
        array.steps.forward();
    }

    public void handlePlayClicked() {
        if (array == null) return;

        if (array.steps.isPlaying) {
            array.steps.pause();
            pressedPauseState();
            return;
        }

        pressedPlayState();

        array.steps.play();
    }

    public void handleStopClicked() {
        array.steps.stop();

        pressedPauseState();

        array.reposition();
    }


    private void pressedPauseState() {
        playButton.setText("Play");
        backwardButton.setDisable(false);
        forwardButton.setDisable(false);
    }

    private void pressedPlayState() {
        playButton.setText("Pause");
        backwardButton.setDisable(true);
        forwardButton.setDisable(true);
    }

    private void pressedResetState() {
        playButton.setText("Play");
        backwardButton.setDisable(false);
        forwardButton.setDisable(false);
    }

    public void minimize(ActionEvent e ) {
        ((Stage)((JFXButton)e.getSource()).getScene().getWindow()).setIconified(true);
    }

    public void close() {
        System.exit(0);
    }
}


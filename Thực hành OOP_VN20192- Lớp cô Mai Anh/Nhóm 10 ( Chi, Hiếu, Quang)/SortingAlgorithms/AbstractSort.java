package SortingAlgorithms;

import java.util.ArrayList;
import java.util.List;

import CNode.CNode;
import CNode.CNode_C;
import CNode.SetSize;
import CNode.Shapee;
import GUI.Animation_Abstract;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public abstract class AbstractSort {

	final Color START_COLOR = Color.CRIMSON;
	final Color SELECT_COLOR = Color.CYAN;
	final Color SORTED_COLOR = Color.ROYALBLUE;

	static int DX;
	static {
		DX = Animation_Abstract.WINDOW_WIDTH / Animation_Abstract.NO_OF_CNODES;
	}

	ParallelTransition colorCNode(Shapee[] Node, Color color, int ...a) {
		ParallelTransition pt = new ParallelTransition();

		for (int i = 0; i < a.length; i++) {
			FillTransition ft = new FillTransition();
			if(SetSize.SHAPEE_TYPE== Shapee.Type.COLUMN) 
			{
				 ft.setShape(((CNode) Node[a[i]]).getRectangle());
			}
			else 
			{
				
				ft.setShape(((CNode_C) Node[a[i]]).getCircle());
			}	
		   ft.setToValue(color);
		   ft.setDuration(Duration.millis(Animation_Abstract.DELAY));
		   pt.getChildren().add(ft);
		}
		return pt;
		
	}
	
	
	ParallelTransition colorCNode(List<Shapee> list, Color color) {
		ParallelTransition pt = new ParallelTransition();

		for (Shapee c : list) {
			FillTransition ft = new FillTransition();
			if(SetSize.SHAPEE_TYPE== Shapee.Type.COLUMN) 
			{
				 ft.setShape(((CNode) c).getRectangle());
			}
			else 
			{
				
				ft.setShape(((CNode_C) c).getCircle());
			}	
			ft.setToValue(color);
			ft.setDuration(Duration.millis(Animation_Abstract.DELAY));
			pt.getChildren().add(ft);
		}

		return pt;
	}

	ParallelTransition swap(Shapee[] arr, int i, int j) {
		ParallelTransition pt = new ParallelTransition();
		int dxFactor = j - i;
		pt.getChildren().addAll(arr[i].moveX(DX * dxFactor), arr[j].moveX(-DX * dxFactor));
		Shapee tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		return pt;
	}

	public abstract ArrayList<Transition> startSort(Shapee[] arr);
}

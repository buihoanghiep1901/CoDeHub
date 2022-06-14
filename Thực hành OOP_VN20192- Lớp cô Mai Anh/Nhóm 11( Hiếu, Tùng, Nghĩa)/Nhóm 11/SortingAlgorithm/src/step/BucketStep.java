package step;

import items.Constant;
import items.item;
import items.itemArray;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BucketStep extends Step {

    private int index;
    private int bucket;
    int num;

    BucketStep(item item1) {
		super(item1, item1);
		this.initAnimation();
		
//		array.getItemNo(i).getPane().setLayoutY(0);
		// preSort
	}
    BucketStep (item node, int index) {
        super(node, node);
        this.index = index;
        this.initAnimation();
    }
    BucketStep (int index ,item node) {
        super(node, node);
        this.index = index;
        this.initAnimation();
        this.text = "BUCKET[" + node.getValue() + "][...] -> " + "[" + node.getIndex() + "]";
    }

	BucketStep(item node, int index, int bucket, int num) {
		super(node, node);
		this.num = num;
		this.index = index;
		this.bucket = bucket;
		this.initAnimation();
		// TODO Auto-generated constructor stub
	}
	@Override
    void setItemAct() {
        item1.setActColor(item.Act.BUCKET);
        item2.setActColor(item.Act.BUCKET);
    }


    @Override
    void reverseItemAct() {
        item1.setActColor(item.Act.DEFAULT);
        item2.setActColor(item.Act.DEFAULT);
    }


    @Override
    Animation makeAnimation() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Constant.TIME));
        tt1.setNode(item1.getPane());
        tt1.setByY(200);
        tt1.setByX(-35*(index-bucket-num));
        ParallelTransition pt1 = new ParallelTransition();
        pt1.getChildren().add(tt1);

        return pt1;
    }

    @Override
    Animation makeReverse() {
        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Constant.TIME));
        tt2.setNode(item2.getPane());
        tt2.setByY(200);
        ParallelTransition pt2 = new ParallelTransition();
        pt2.getChildren().add(tt2);

        return null;
    }

}

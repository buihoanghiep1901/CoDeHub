package step;


import items.Constant;
import items.item;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class SwapStep extends Step {

    SwapStep (item item1, item item2) {
        super(item1, item2);
        this.initAnimation();
    }

    @Override
    void setItemAct() {
        item1.setActColor(item.Act.SWAP);
        item2.setActColor(item.Act.SWAP);
    }

    @Override
    void reverseItemAct() {
        item1.setActColor(item.Act.DEFAULT);
        item2.setActColor(item.Act.DEFAULT);
    }

    @Override
    Animation makeAnimation() {
        // Swap Animation
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Constant.TIME));
        tt1.setByX(35*(item1.getIndex()-item2.getIndex()));
        tt1.setNode(item1.getPane());

        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Constant.TIME));
        tt2.setByX(-35*(item1.getIndex()-item2.getIndex()));
        tt2.setNode(item2.getPane());

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1, tt2);

        return pt;
    }

    @Override
    Animation makeReverse() {
        // Swap Reverse
        TranslateTransition tt3 = new TranslateTransition();
        tt3.setDuration(Duration.seconds(Constant.TIME));
        tt3.setByX(-35*(item1.getIndex()-item2.getIndex()));
        tt3.setNode(item1.getPane());

        TranslateTransition tt4 = new TranslateTransition();
        tt4.setDuration(Duration.seconds(Constant.TIME));
        tt4.setByX(35*(item1.getIndex()-item2.getIndex()));
        tt4.setNode(item2.getPane());

        ParallelTransition pt2 = new ParallelTransition();
        pt2.getChildren().addAll(tt3, tt4);

        return pt2;
    }

}

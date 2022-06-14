package step;


import items.Constant;
import items.item;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class CompareStep extends Step {

    CompareStep (item item1, item item2) {
        super(item1, item2);
        this.initAnimation();
    }


    @Override
    void setItemAct() {
        item1.setActColor(item.Act.COMPARE);
        item2.setActColor(item.Act.COMPARE);
    }

    @Override
    void reverseItemAct() {
        item1.setActColor(item.Act.DEFAULT);
        item2.setActColor(item.Act.DEFAULT);
    }

    @Override
    Animation makeAnimation() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Constant.TIME/2));
        tt1.setCycleCount(2);
        tt1.setAutoReverse(true);
        tt1.setByY(-20);
        tt1.setNode(item1.getPane());

        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Constant.TIME/2));
        tt2.setCycleCount(2);
        tt2.setAutoReverse(true);
        tt2.setByY(-20);
        tt2.setNode(item2.getPane());

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1, tt2);

        return pt;
    }

    @Override
    Animation makeReverse() {
        return makeAnimation();
    }

}

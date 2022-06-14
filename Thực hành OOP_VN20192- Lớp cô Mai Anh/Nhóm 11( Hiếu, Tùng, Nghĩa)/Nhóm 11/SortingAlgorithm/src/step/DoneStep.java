package step;

import items.Constant;
import items.item;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class DoneStep extends Step {

    DoneStep(item node) {
        super(node, node);
        initAnimation();
    }

    @Override
    void setItemAct() {
        item1.setActColor(item.Act.DONE);
        item2.setActColor(item.Act.DONE);
    }

    @Override
    void reverseItemAct() {
        item1.setActColor(item.Act.DONE);
        item2.setActColor(item.Act.DONE);
    }

    @Override
    Animation makeAnimation() {
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Constant.TIME/2));
        tt1.setCycleCount(2);
        tt1.setAutoReverse(true);
        tt1.setByY(-20);
        tt1.setNode(item1.getPane());

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1);

        return pt;
    }

    @Override
    Animation makeReverse() {
        return makeAnimation();
    }
}

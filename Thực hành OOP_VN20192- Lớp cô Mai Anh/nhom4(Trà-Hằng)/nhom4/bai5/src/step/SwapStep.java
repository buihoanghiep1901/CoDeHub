package step;

import element.Common;
import element.Element;
import element.ElementArray;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class SwapStep extends Step {

    SwapStep (Element node1, Element node2) {
        super(node1, node2);
        this.text = "SWAP    [" + node1.getIndex() + "] & ["+ node2.getIndex() + "]";
        this.initAnimationAndReverse();
    }
    SwapStep(Element node1, Element node2, boolean isHeap) {
        super(node1, node2);
        this.text = "SWAP    [" + node1.getIndex() + "] & [" + node2.getIndex() + "]";
        this.initAnimationAndReverse2();
    }

    @Override
    void setElementState() {
        node1.setStateColor(Element.State.SWAP);
        node2.setStateColor(Element.State.SWAP);
    }

    @Override
    void reverseElementState() {
        node1.setStateColor(Element.State.DEFAULT);
        node2.setStateColor(Element.State.DEFAULT);
    }

    @Override
    Animation makeAnimation() {
        // Swap Animation
        TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        tt1.setByX((Common.WIDTH + Common.DISTANCE) * (node1.getIndex() - node2.getIndex()));
        tt1.setNode(node1.getShape());

        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Common.DURATION));
        tt2.setByX((Common.WIDTH + Common.DISTANCE) * (- node1.getIndex() + node2.getIndex()));
        tt2.setNode(node2.getShape());

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1, tt2);

        return pt;
    }

    @Override
    Animation makeReverse() {
        // Swap Reverse
        TranslateTransition tt3 = new TranslateTransition();
        tt3.setDuration(Duration.seconds(Common.DURATION));
        tt3.setByX(- (Common.WIDTH + Common.DISTANCE) * (node1.getIndex() - node2.getIndex()));
        tt3.setNode(node1.getShape());

        TranslateTransition tt4 = new TranslateTransition();
        tt4.setDuration(Duration.seconds(Common.DURATION));
        tt4.setByX(- (Common.WIDTH + Common.DISTANCE) * (- node1.getIndex() + node2.getIndex()));
        tt4.setNode(node2.getShape());

        ParallelTransition pt2 = new ParallelTransition();
        pt2.getChildren().addAll(tt3, tt4);

        return pt2;
    }
	@Override
	Animation makeAnimation2() {
		TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Common.DURATION));

        double Node1IX = ElementArray.getX(node1.getIndex());
        double Node2IX = ElementArray.getX(node2.getIndex());
        double Node1IY = ElementArray.getY(node1.getIndex());
        double Node2IY = ElementArray.getY(node2.getIndex());
        double NodeXGap = Math.abs(Node2IX - Node1IX);
        double NodeYGap = Math.abs(Node2IY - Node1IY);

        if(Node1IX<Node2IX){
            tt1.setByX(-NodeXGap*Common.DISTANCE*5);
            tt2.setByX(NodeXGap*Common.DISTANCE*5);
        }
        else{
            tt1.setByX(NodeXGap*Common.DISTANCE*5);
            tt2.setByX(-NodeXGap*Common.DISTANCE*5);
        }

        if(Node1IY<Node2IY){
            tt1.setByY(-NodeYGap*Common.DISTANCE*10);
            tt2.setByY(NodeYGap*Common.DISTANCE*10);
        }
        else{
            tt1.setByY(NodeYGap*Common.DISTANCE*10);
            tt2.setByY(-NodeYGap*Common.DISTANCE*10);

        }
        tt1.setNode(node1.getShape());
        tt2.setNode(node2.getShape());

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1, tt2);

        return pt;
	}
	@Override
	Animation makeReverse2() {
		TranslateTransition tt1 = new TranslateTransition();
        tt1.setDuration(Duration.seconds(Common.DURATION));
        TranslateTransition tt2 = new TranslateTransition();
        tt2.setDuration(Duration.seconds(Common.DURATION));

        double Node1IX = ElementArray.getX(node1.getIndex());
        double Node2IX = ElementArray.getX(node2.getIndex());
        double Node1IY = ElementArray.getY(node1.getIndex());
        double Node2IY = ElementArray.getY(node2.getIndex());
        double NodeXGap = Math.abs(Node2IX - Node1IX);
        double NodeYGap = Math.abs(Node2IY - Node1IY);

        if(Node1IX<Node2IX){
            tt1.setByX(NodeXGap*Common.DISTANCE*5);
            tt2.setByX(-NodeXGap*Common.DISTANCE*5);
        }
        else{
            tt1.setByX(-NodeXGap*Common.DISTANCE*5);
            tt2.setByX(NodeXGap*Common.DISTANCE*5);
        }

        if(Node1IY<Node2IY){
            tt1.setByY(NodeYGap*Common.DISTANCE*10);
            tt2.setByY(-NodeYGap*Common.DISTANCE*10);
        }
        else{
            tt1.setByY(-NodeYGap*Common.DISTANCE*10);
            tt2.setByY(NodeYGap*Common.DISTANCE*10);

        }
        tt1.setNode(node1.getShape());
        tt2.setNode(node2.getShape());

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt1, tt2);

        return pt;
    }

}

package step;

import items.item;
import items.itemArray;
import javafx.animation.Animation;

public abstract class Step {

    private Animation animation;

    private Step next;

    private Step previous;

    String text;

    item item1;

    item item2;
    
    item [] items;

    Step (item item1, item item2) {
        this.item1 = item1;
        this.item2 = item2;

    }

	Step(itemArray array) {
//		items
	}

	public void initAnimation() {
        this.animation = makeAnimation();
 
    }
    public void smalling() {
    	
    }
    abstract void setItemAct();

    abstract void reverseItemAct();

    abstract Animation makeAnimation();

    abstract Animation makeReverse();

    public void playOne(Closure onFinished) {
        if (previous!=null) previous.reverseItemAct();
        setItemAct();

        animation.pause();

        animation.setOnFinished(event -> {
            onFinished.process();
        });

        animation.play();
    }

/*    public void reverse(Closure onFinished) {
        if (next!=null) next.reverseItemAct();
        setItemAct();

        reverse.pause();

        reverse.setOnFinished(event -> {
            onFinished.process();

        });

        reverse.play();

    }*/

    public void play(Closure onFinished) {
        reverseItemAct();
        if (previous!=null) previous.reverseItemAct();
        animation.pause();

        animation.setOnFinished(event -> {
            onFinished.process();
            if (next != null) next.play(onFinished);
        });

        animation.play();
        setItemAct();
    }

    public void stop() {
        animation.stop();
  //      reverse.stop();
    }

    public void setNodes(item item1, item item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public void setNext(Step next) {
        this.next = next;
    }

    public void setPrevious(Step previous) {
        this.previous = previous;
    }

    public Step getNext() {
        return next;
    }

    public Step getPrevious() {
        return previous;
    }

    @Override
    public String toString() {
        return text;
    }

    @FunctionalInterface
    interface Closure {
        void process();
    }
}

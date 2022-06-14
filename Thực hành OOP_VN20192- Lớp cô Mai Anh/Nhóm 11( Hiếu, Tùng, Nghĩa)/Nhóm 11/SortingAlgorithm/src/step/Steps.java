package step;

import items.item;


import java.util.LinkedList;

public class Steps {
    private LinkedList<Step> steps;

    private Step currentStep;

    public boolean isPlaying = false;


    public Steps () {
        steps = new LinkedList<>();
    }

    public void add(Step step) {
        if (steps.size() == 0) {
            step.setNext(null);
            step.setPrevious(null);

            currentStep = step;
            steps.add(step);
            return;
        }

        step.setPrevious(steps.getLast());
        step.setNext(null);
        steps.getLast().setNext(step);
        steps.add(step);

    }

    public void addSwapStep(item item1, item item2) {

        SwapStep s = new SwapStep(item1, item2);

        add(s);
    }

    public void addCompareStep(item item1, item item2) {

        CompareStep s = new CompareStep(item1, item2);
        s.setNodes(item1, item2);

        add(s);
    }


    public void addDoneStep(item node) {
        Step s = new DoneStep(node);

        add(s);
    }


    public void play() {
        if (isPlaying) return;

        isPlaying = true;

        if (currentStep == null) return;

        currentStep.play(() -> {

            currentStep = currentStep.getNext();
        });

    }

    public void pause() {
        if (!isPlaying) return;

        isPlaying = false;

        if (currentStep == null) return;

        currentStep.playOne(() -> {
            currentStep = currentStep.getNext();
        });
    }


    public void stop() {

        if (steps.size() == 0) return;

        pause();


        if (currentStep != null) {

            currentStep.stop();
            currentStep.reverseItemAct();

            if (currentStep.getPrevious() != null)
                currentStep.getPrevious().reverseItemAct();
        } else {
            steps.getLast().reverseItemAct();
        }

        currentStep = steps.getFirst();
    }


	public void addBucketStep(item itemNo, int i) {
    	Step s = new BucketStep(itemNo,i);
    	add(s);
		
	}

	public void addBucketStep(item itemNo, int i, int bucket, int num) {
    	Step s = new BucketStep(itemNo,i,bucket, num);
    	add(s);
		
	}
}

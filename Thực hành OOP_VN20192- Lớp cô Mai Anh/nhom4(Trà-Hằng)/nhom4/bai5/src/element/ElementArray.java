package element;

import javafx.scene.layout.Pane;
import step.Steps;


public class ElementArray {
    private Element [] elements;
    private Element [] elements2;

    public Steps steps;

    public ElementArray(int length) {
        elements = new Element[length];
        steps = new Steps();

        for (int i = 0; i<length; i++) {
            int value = (int) ( Math.random() * Common.RANDOM ) + 1;
            elements[i]  = new BoxElement(value, i);

            // Position Element
            elements[i].getShape().setLayoutX(Common.SCENE_WIDTH/2 - (Common.WIDTH + Common.DISTANCE) * length/2 + i * (Common.WIDTH + Common.DISTANCE));
            elements[i].getShape().setLayoutY(Common.SCENE_HEIGHT * 0.7 - elements[i].getShape().getPrefHeight());

            System.out.print(value + " ");
        }

        System.out.println();
        
     // Create build heap

        elements2 = new Element[length];
        for (int i = 0; i < length; i++) {
            int value = (int) (elements[i].getValue());
            elements2[i] = new BoxElement(value, i);

            // Position Element
            elements2[i].getShape().setLayoutX(Common.SCENE_WIDTH / 2 + getX(i) * 5 * Common.DISTANCE);
            elements2[i].getShape().setLayoutY(Common.SCENE_HEIGHT * 0.2 + getY(i) * Common.DISTANCE * 10);
        }
    }
    
    //Radix arrays
    public ElementArray(int array[], int exp) {

        int length = array.length;
        elements = new Element[length];
        steps = new Steps();

        for (int i = 0; i < length(); ++i) {
            int value = (int) (array[i]);
            elements[i] = new BoxElement(value, i);

            // Position Element
            elements[i].getShape().setLayoutX(Common.SCENE_WIDTH / 2 - (Common.WIDTH + Common.DISTANCE) * length / 2 + i * (Common.WIDTH + Common.DISTANCE));
            elements[i].getShape().setLayoutY(Common.SCENE_HEIGHT * 0.7 - elements[i].getShape().getPrefHeight() - exp * 10 * Common.DISTANCE);

            System.out.print(value + " ");
        }
        System.out.println();
    }

    
    public static double getX(int index) {
        switch (index) {
            case 0:
                return 0;
            case 1:
                return -4;
            case 2:
                return 4;
            case 3:
                return -6;
            case 4:
                return -2;
            case 5:
                return 2;
            case 6:
                return 6;
            case 7:
                return -7;
            case 8:
                return -5;
            case 9:
                return -3;
            case 10:
                return -1;
            case 11:
                return 1;
            case 12:
                return 3;
            case 13:
                return 5;
            case 14:
                return 7;
            default:
                return 0;
        }
    }

    public static double getY(int index) {
        switch (index) {
            case 0:
                return 0;
            case 1:case 2:
                return 1;
            case 3: 
            	return 2;
            case 4: 
            	return 2;
            case 5: 
            	return 2;
            case 6:
                return 2;
            case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14:
                return 3;
            default:
                return 0;
        }
    }

    public Pane [] getAllShape () {
        Pane [] shapes = new Pane[length()];
        for (int i = 0; i < elements.length; i++) {
            shapes[i] = elements[i].getShape();
        }
        return shapes;
    }
    
    public Pane[] getAllShape2() {
        Pane[] shapes = new Pane[length()];
        for (int i = 0; i < elements2.length; i++) {
            shapes[i] = elements2[i].getShape();
        }
        return shapes;
    }

 
    public Element getElementAt(int i) {
        return elements[i];
    }

    public int length() {
        return elements.length;
    }

    public void swap(int i, int j) {
        Element tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;

        elements[i].setIndex(i);
        elements[j].setIndex(j);

        //
        steps.addSwapStep(elements[i], elements[j]);
    }
    
    public void swap(int i, int j, boolean isHeap) {
        Element tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;

        elements[i].setIndex(i);
        elements[j].setIndex(j);

        Element tmp2 = elements2[i];
        elements2[i] = elements2[j];
        elements2[j] = tmp2;

        elements2[i].setIndex(i);
        elements2[j].setIndex(j);

        //
        steps.addSwapStep(elements[i], elements[j]);
        steps.addSwapStep(elements2[i], elements2[j], true);

    }

    public int compare(int i, int j) {
        //
        steps.addCompareStep(elements[i], elements[j]);

        return elements[i].compareTo(elements[j]);
    }

    public int compare(int i, int j, boolean isHeap) {
        //
        steps.addCompareStep(elements[i], elements[j]);
        steps.addCompareStep(elements2[i], elements2[j], true);


        return elements[i].compareTo(elements[j]);
    }
    public void compareAndSwap(int i, int j) {
        if (compare(i, j) < 0) {
            swap(i, j);
        }
    }

    public Element getMax() {
        Element MAX = elements[0];
        for (Element e: elements) {
            if (MAX.compareTo(e) > 0) {
                MAX = e;
            }
        }
        return MAX;
    }
    
    public Element[] getAll(){
    	return elements;
    }

    // Those methods

    public void addDoneStep(int i, boolean isHeap) {
        steps.addDoneStep(elements[i]);
        steps.addDoneStep(elements2[i]);
    }
    //
    public void reposition() {
        for (Element element: elements) {
            element.getShape().setTranslateX(0);
            element.getShape().setTranslateY(0);
            element.getShape().setLayoutX(element.getShape().getLayoutX());
            element.getShape().setLayoutY(element.getShape().getLayoutY());
        }
    }
}

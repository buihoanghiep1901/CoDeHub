package SortingAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
//import CNode.CNode;
import CNode.Shapee;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

public class ShellSort extends AbstractSort {
	private ArrayList<Transition> transitions;
	public ShellSort() {
		this.transitions = new ArrayList<>();
	}

	@Override
	public ArrayList<Transition> startSort(Shapee[] arr) {
		 int h=1, i;
		Shapee term;
	     while(h<arr.length/2)
	  	   h=h*2+1;
	     while(h>0)
	     {
	  	   // thuc hien hoan doi giua 2 phan tu co khoang cach h
	  	for(i=h;i<arr.length; i++)
	  	{	
	  	 term= arr[i];
	  	ParallelTransition pt = new ParallelTransition();
	  	 int  j=i;
	  	 transitions.add(colorCNode(arr, SELECT_COLOR,j-h,j));
	  	transitions.add(colorCNode(arr, START_COLOR, j-h,j));
	  	   while((j>h-1)&&(arr[j-h].getValue()>term.getValue()) )
	  	   {
	  		 transitions.add(colorCNode(arr, SELECT_COLOR,j-h,j));
	  		 transitions.add(swap(arr, j-h,j));
	  		 transitions.add(colorCNode(arr, START_COLOR, j-h,j));
	  		   j-=h;
	  	   }
	  	
		transitions.add(pt);
	  	}  
	  	h=(h-1)/2;
	  	
	     }
	     transitions.add(colorCNode(Arrays.asList(arr), SORTED_COLOR));

			return transitions;
}
}



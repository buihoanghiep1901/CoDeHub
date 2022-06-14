package algorithm;

import items.item;
import items.itemArray;

public class MergeSort implements Algorithm {
	
	private static MergeSort newMerge = new MergeSort();
	
	public static MergeSort getMerge() {
		return newMerge;
	}
    public void sort(itemArray array) {
        sort(array, 0, array.itemsLength() - 1);

        for (item e: array.getAll()) {
            array.steps.addDoneStep(e);
        }
    }


    private void sort(itemArray arr,int min,int max){
        if(max-min==0){//only one element.
            //no swap
        }
        else if(max-min==1){//only two elements and swaps them
            if(arr.compare(min, max) < 0)
                arr.swap(min, max);
        }
        else{
            int mid=((int) Math.floor((min+max)/2));//The midpoint

            sort(arr,min,mid);//sort the left side
            sort(arr,mid+1,max);//sort the right side

            merge(arr, min, max, mid);//combines them
        }


    }


    private void merge(itemArray arr, int min,int max,int mid){
        int i=min;
        while(i<=mid){
            if(arr.compare(i, mid+1) < 0){
                arr.swap(i,mid+1);

                push(arr,mid+1,max);
            }
            i++;
        }

    }

    private void push(itemArray arr,int s,int e){
        for(int i=s;i<e;i++){
            if(arr.compare(i, i+1)<0) {

                arr.swap(i,i+1);

            }

        }

    }
}
